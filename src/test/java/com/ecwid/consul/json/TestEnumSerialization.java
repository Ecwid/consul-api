package com.ecwid.consul.json;

import com.ecwid.consul.v1.session.model.Session;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Ensures that enumerations annotated with {@link SerializedName} can be
 * deserialized properly.
 */
public class TestEnumSerialization {

    private static final String TEST_JSON_STRING = "{" +
        "    \"Name\": \"my-service-lock\"," +
        "    \"Node\": \"foobar\"," +
        "    \"Checks\": [\"a\", \"b\", \"c\"]," +
        "    \"Behavior\": \"release\"," +
        "    \"TTL\": \"30s\"" +
        "}";

    @Test
    public void testEnumSerialization() {
        final Gson gson = GsonFactory.getGson();
        final Session session = gson.fromJson(TEST_JSON_STRING, Session.class);

        assertEquals("my-service-lock", session.getName());
        assertEquals("foobar", session.getNode());
        assertEquals("foobar", session.getNode());

        // risk prone
        final String[] checks = new String[session.getChecks().size()];
        session.getChecks().toArray(checks);
        assertArrayEquals(new String[] {"a", "b", "c"}, checks);

        assertEquals(Session.Behavior.RELEASE, session.getBehavior());
        assertEquals("30s", session.getTtl());
    }
}
