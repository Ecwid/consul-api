package com.ecwid.consul.v1.transactions;

import com.ecwid.consul.json.GsonFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParamBuilderTest {

	@Test
	void BuilderSerializedTest() {
		ParamBuilder p = ParamBuilder.getInstance()
			.kvDeleteTree("A")
			.kvCheckIndex("A", 12)
			.kvDeleteCas("B");
		String s = GsonFactory.getGson().toJson(p);
		System.out.println(s);
		String ans = "{\"operates\":[{\"KV\":{\"Verb\":\"delete-tree\",\"Key\":\"A\"}},{\"KV\":{\"Verb\":\"check-index\",\"Key\":\"A\",\"Index\":12}},{\"KV\":{\"Verb\":\"delete-cas\",\"Key\":\"B\"}}]}";
		assertEquals(ans, s);
	}
}
