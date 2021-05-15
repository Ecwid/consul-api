package com.ecwid.consul.v1;

import com.ecwid.consul.UrlParameters;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Implements a query filter parameter.
 * See https://www.consul.io/api-docs/features/filtering
 */
public class Filter implements UrlParameters {
	public enum MatchingOperator {
		EQUAL("=", false),
		NOT_EQUAL("!=", false),
		IS_EMPTY("is empty", true),
		IS_NOT_EMPTY("is not empty", true),
		IN("in", false),
		NOT_IN("not in", false),
		CONTAINS("contains", false),
		NOT_CONTAINS("not contains", false),
		MATCHES("matches", false),
		NOT_MATCHES("not matches", false);

		private final String representation;
		private final boolean unary;

		MatchingOperator(final String representation, final boolean unary) {
			this.representation = representation;
			this.unary = unary;
		}

		@Override
		public String toString() {
			return representation;
		}
	}

	public static class Selector {
		private final String s;

		private Selector(String s) {
			this.s = s;
		}

		public static Selector of(final String s) {
			return new Selector(s);
		}
	}

	/**
	 * Creates a filter with a unary operator (e.g {@link MatchingOperator#IN} or {@link MatchingOperator#NOT_IN})
	 * @throws IllegalArgumentException if used with a binary matching operator
	 */
	public static Filter of(final MatchingOperator matchingOperator, final Selector selector) {
		if (!matchingOperator.unary) {
			throw new IllegalArgumentException("operator " + matchingOperator.name() + " requires a value");
		}
		return new Filter(Collections.emptyList(), BoolOp.AND, new Leaf(matchingOperator, selector.s, null), true);
	}

	/**
	 * Creates a filter with a binary operator
	 * @throws IllegalArgumentException if used with a unary matching operator ({@link MatchingOperator#IN} or
	 * {@link MatchingOperator#NOT_IN}) specifying a value, or if used with a binary matching operator w/o specifying
	 * a value.
	 */
	public static Filter of(final MatchingOperator matchingOperator, final Selector selector, final String value) {
		if (matchingOperator.unary && (value != null)) {
			throw new IllegalArgumentException("operator " + matchingOperator.name() + " does not accept a value");
		}
		if (!matchingOperator.unary && (value == null)) {
			throw new IllegalArgumentException("operator " + matchingOperator.name() + " requires a value");
		}
		if (matchingOperator == MatchingOperator.IN) {
			return in(value, selector);
		}
		if (matchingOperator == MatchingOperator.NOT_IN) {
			return notIn(value, selector);
		}
		return new Filter(Collections.emptyList(), BoolOp.AND, new Leaf(matchingOperator, selector.s, value), true);
	}

	/**
	 * Creates a {@code "<Value>" in <Selector>} query
	 */
	public static Filter in(final String value, final Selector selector) {
		return new Filter(Collections.emptyList(), BoolOp.AND, new Leaf(MatchingOperator.IN, selector.s, value), true);
	}

	/**
	 * Creates a {@code "<Value>" not in <Selector>} query
	 */
	public static Filter notIn(final String value, final Selector selector) {
		return new Filter(Collections.emptyList(), BoolOp.AND, new Leaf(MatchingOperator.NOT_IN, selector.s, value), true);
	}

	/**
	 * Returns a new filter, with the specified filters added, all joined with 'and'.
	 */
	public Filter and(final Filter ...filters) {
		return add(BoolOp.AND, filters);
	}

	/**
	 * Creates a new filter with the specified filters, all joined with 'and'.
	 */
	public static Filter andAll(final List<Filter> filters) {
		return new Filter(filters, BoolOp.AND, true);
	}

	/**
	 * Returns a new filter, with the specified filters added, all joined with 'or'.
	 */
	public Filter or(final Filter ...filters) {
		return add(BoolOp.OR, filters);
	}

	/**
	 * Creates a new filter with the specified filters, all joined with 'and'.
	 */
	public static Filter orAll(final List<Filter> filters) {
		return new Filter(filters, BoolOp.OR, true);
	}

	/**
	 * Returns a negated copy of this filter.
	 * Calling this method on a negative filter will turn it into a positive filter,
	 * i.e {@code filter.not().not().equals(filter)} is true
	 */
	public Filter not() {
		return new Filter(children, boolOp, leaf, !positive);
	}

	@Override
	public List<String> toUrlParameters() {
		return Collections.singletonList("filter=" + toString());
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final Filter filter = (Filter) o;
		return (positive == filter.positive) &&
				Objects.equals(children, filter.children) &&
				(boolOp == filter.boolOp) &&
				Objects.equals(leaf, filter.leaf);
	}

	@Override
	public int hashCode() {
		return Objects.hash(children, boolOp, leaf, positive);
	}

	@Override
	public String toString() {
		final String prefix = positive ? "" : "not ";
		if (leaf != null) {
			if (leaf.value != null) {
				if ((leaf.matchingOperator == MatchingOperator.IN) || (leaf.matchingOperator == MatchingOperator.NOT_IN)) {
					return String.format(prefix + "\"%s\" %s %s", leaf.value, leaf.matchingOperator, leaf.selector);
				}
				return String.format(prefix + "%s %s \"%s\"", leaf.selector, leaf.matchingOperator, leaf.value);
			}
			return String.format(prefix + "%s %s", leaf.selector, leaf.matchingOperator);
		}

		final String result = children.stream().map(Filter::toString).collect(Collectors.joining(" " + boolOp + " "));
		if ((parent == null) && positive) {
			return result;
		}
		return prefix + "(" + result + ")";
	}

	private enum BoolOp {
		OR("or"),
		AND("and");

		private final String representation;

		BoolOp(final String representation) {
			this.representation = representation;
		}

		@Override
		public String toString() {
			return representation;
		}

	}

	private static class Leaf {
		private final Filter.MatchingOperator matchingOperator;
		private final String selector;
		private final String value;

		private Leaf(final MatchingOperator matchingOperator, final String selector, final String value) {
			this.matchingOperator = matchingOperator;
			this.selector = selector;
			this.value = value;
		}

		@Override
		public boolean equals(final Object o) {
			if (this == o) {
				return true;
			}
			if ((o == null) || (getClass() != o.getClass())) {
				return false;
			}
			final Leaf leaf = (Leaf) o;
			return (matchingOperator == leaf.matchingOperator) &&
					selector.equals(leaf.selector) &&
					value.equals(leaf.value);
		}

		@Override
		public int hashCode() {
			return Objects.hash(matchingOperator, selector, value);
		}

	}
	private final List<Filter> children;

	private final BoolOp boolOp;
	private final Leaf leaf;
	private Filter parent;
	private final boolean positive;
	private Filter(final List<Filter> children, final BoolOp boolOp, final boolean positive) {
		this.children = children;
		this.boolOp = boolOp;
		leaf = null;
		this.positive = positive;
	}

	private Filter(final List<Filter> children, final BoolOp boolOp, final Leaf leaf, final boolean positive) {
		this.children = children;
		this.boolOp = boolOp;
		this.leaf = leaf;
		this.positive = positive;
	}

	private Filter add(final BoolOp op, final Filter... filters) {
		final List<Filter> newChildren = new LinkedList<>();
		newChildren.add(this);
		newChildren.addAll(Arrays.asList(filters));
		final Filter result = new Filter(newChildren, op, null, true);
		for (final Filter child : newChildren) {
			child.parent = result;
		}
		return result;
	}
}
