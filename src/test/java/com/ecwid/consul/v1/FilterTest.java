package com.ecwid.consul.v1;

import com.ecwid.consul.v1.Filter.Selector;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FilterTest {
	@Nested
	class EqualsAndHashCode {
		@Test
		void shouldVerify() {
			EqualsVerifier.forClass(Filter.class)
					.withPrefabValues(
							Filter.class,
							Filter.of(Filter.MatchingOperator.EQUAL, Filter.Selector.of("foo"), "bar"),
							Filter.in("baz", Filter.Selector.of("fang"))
					)
					.usingGetClass()
					.withIgnoredFields("parent")
					.verify();
		}
	}

	@Test
	public void of() {
		Filter actual = Filter.of(Filter.MatchingOperator.EQUAL, Selector.of("foo"), "bar");
		assertEquals("foo = \"bar\"", actual.toString());
		assertThrows(IllegalArgumentException.class, ()-> Filter.of(Filter.MatchingOperator.EQUAL, Selector.of("foo")));

		actual = Filter.of(Filter.MatchingOperator.NOT_EQUAL, Selector.of("foo"), "bar");
		assertEquals("foo != \"bar\"", actual.toString());
		assertThrows(IllegalArgumentException.class, ()-> Filter.of(Filter.MatchingOperator.NOT_EQUAL, Selector.of("foo")));

		actual = Filter.of(Filter.MatchingOperator.IS_EMPTY, Selector.of("foo"), null);
		assertEquals("foo is empty", actual.toString());
		actual = Filter.of(Filter.MatchingOperator.IS_EMPTY, Selector.of("foo"));
		assertEquals("foo is empty", actual.toString());
		assertThrows(IllegalArgumentException.class, ()-> Filter.of(Filter.MatchingOperator.IS_EMPTY, Selector.of("foo"), "bar"));

		actual = Filter.of(Filter.MatchingOperator.IS_NOT_EMPTY, Selector.of("foo"), null);
		assertEquals("foo is not empty", actual.toString());
		actual = Filter.of(Filter.MatchingOperator.IS_NOT_EMPTY, Selector.of("foo"));
		assertEquals("foo is not empty", actual.toString());
		assertThrows(IllegalArgumentException.class, ()-> Filter.of(Filter.MatchingOperator.IS_NOT_EMPTY, Selector.of("foo"), "bar"));

		actual = Filter.of(Filter.MatchingOperator.IN, Selector.of("foo"), "bar");
		assertEquals("\"bar\" in foo", actual.toString());
		assertThrows(IllegalArgumentException.class, ()-> Filter.of(Filter.MatchingOperator.IN, Selector.of("foo")));

		actual = Filter.of(Filter.MatchingOperator.NOT_IN, Selector.of("foo"), "bar");
		assertEquals("\"bar\" not in foo", actual.toString());
		assertThrows(IllegalArgumentException.class, ()-> Filter.of(Filter.MatchingOperator.NOT_IN, Selector.of("foo")));

		actual = Filter.of(Filter.MatchingOperator.CONTAINS, Selector.of("foo"), "bar");
		assertEquals("foo contains \"bar\"", actual.toString());
		assertThrows(IllegalArgumentException.class, ()-> Filter.of(Filter.MatchingOperator.CONTAINS, Selector.of("foo")));

		actual = Filter.of(Filter.MatchingOperator.NOT_CONTAINS, Selector.of("foo"), "bar");
		assertEquals("foo not contains \"bar\"", actual.toString());
		assertThrows(IllegalArgumentException.class, ()-> Filter.of(Filter.MatchingOperator.NOT_CONTAINS, Selector.of("foo")));

		actual = Filter.of(Filter.MatchingOperator.MATCHES, Selector.of("foo"), "bar");
		assertEquals("foo matches \"bar\"", actual.toString());
		assertThrows(IllegalArgumentException.class, ()-> Filter.of(Filter.MatchingOperator.MATCHES, Selector.of("foo")));

		actual = Filter.of(Filter.MatchingOperator.NOT_MATCHES, Selector.of("foo"), "bar");
		assertEquals("foo not matches \"bar\"", actual.toString());
		assertThrows(IllegalArgumentException.class, ()-> Filter.of(Filter.MatchingOperator.NOT_MATCHES, Selector.of("foo")));
	}

	@Test
	public void in() {
		final Filter actual = Filter.in("bar", Selector.of("foo"));
		assertEquals("\"bar\" in foo", actual.toString());
	}

	@Test
	public void notIn() {
		final Filter actual = Filter.notIn("bar", Selector.of("foo"));
		assertEquals("\"bar\" not in foo", actual.toString());
	}

	@Test
	public void and() {
		final Filter f = Filter.of(Filter.MatchingOperator.EQUAL, Selector.of("foo"), "bar");
		final Filter actual = f.and(
				Filter.of(Filter.MatchingOperator.EQUAL, Selector.of("foo1"), "bar1"),
				Filter.of(Filter.MatchingOperator.EQUAL, Selector.of("foo2"), "bar2")
		);

		assertEquals("foo = \"bar\" and foo1 = \"bar1\" and foo2 = \"bar2\"", actual.toString());

		final Filter actual2 = f
				.or(Filter.of(Filter.MatchingOperator.EQUAL, Selector.of("foo1"), "bar1"))
				.and(Filter.of(Filter.MatchingOperator.EQUAL, Selector.of("foo3"), "bar3"));
		assertEquals("(foo = \"bar\" or foo1 = \"bar1\") and foo3 = \"bar3\"", actual2.toString());

		final Filter actual3 = f.and();
		assertEquals("foo = \"bar\"", actual3.toString());
	}

	@Test
	public void addAll() {
		final Filter actual = Filter.andAll(Arrays.asList(new Filter[]{
				Filter.of(Filter.MatchingOperator.EQUAL, Selector.of("foo"), "bar"),
				Filter.of(Filter.MatchingOperator.EQUAL, Selector.of("foo1"), "bar1")
			}
		));
		assertEquals("foo = \"bar\" and foo1 = \"bar1\"", actual.toString());
	}

	@Test
	public void or() {
		final Filter f = Filter.of(Filter.MatchingOperator.EQUAL, Selector.of("foo"), "bar");
		final Filter actual = f.or(
				Filter.of(Filter.MatchingOperator.EQUAL, Selector.of("foo1"), "bar1"),
				Filter.of(Filter.MatchingOperator.EQUAL, Selector.of("foo2"), "bar2")
		);

		assertEquals("foo = \"bar\" or foo1 = \"bar1\" or foo2 = \"bar2\"", actual.toString());

		final Filter actual2 = f
				.and(Filter.of(Filter.MatchingOperator.EQUAL, Selector.of("foo1"), "bar1"))
				.or(Filter.of(Filter.MatchingOperator.EQUAL, Selector.of("foo3"), "bar3"));
		assertEquals("(foo = \"bar\" and foo1 = \"bar1\") or foo3 = \"bar3\"", actual2.toString());

		final Filter actual3 = f.or();
		assertEquals("foo = \"bar\"", actual3.toString());
	}

	@Test
	public void orAll() {
		final Filter actual = Filter.orAll(Arrays.asList(new Filter[]{
						Filter.of(Filter.MatchingOperator.EQUAL, Selector.of("foo"), "bar"),
						Filter.of(Filter.MatchingOperator.EQUAL, Selector.of("foo1"), "bar1")
				}
		));
		assertEquals("foo = \"bar\" or foo1 = \"bar1\"", actual.toString());
	}

	@Test
	public void not() {
		final Filter f = Filter.of(Filter.MatchingOperator.EQUAL, Selector.of("foo"), "bar");
		assertEquals("foo = \"bar\"", f.toString());
		assertEquals("not foo = \"bar\"", f.not().toString());
		assertEquals("foo = \"bar\"", f.not().not().toString());

		final Filter f2 = f.and(Filter.of(Filter.MatchingOperator.EQUAL, Selector.of("foo1"), "bar2"));
		assertEquals("foo = \"bar\" and foo1 = \"bar2\"", f2.toString());
		assertEquals("not (foo = \"bar\" and foo1 = \"bar2\")", f2.not().toString());
	}

	@Test
	public void toUrlParameters() {
		final Filter subject = Filter.of(Filter.MatchingOperator.EQUAL, Selector.of("foo"), "bar");
		final List<String> actual = subject.toUrlParameters();
		assertEquals(Collections.singletonList("filter=foo = \"bar\""), actual);
	}
}
