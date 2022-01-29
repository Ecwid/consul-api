package com.ecwid.consul.v1.connect.intentions;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class EqualsVerifierTest {

    @Test
    public void verifyIntentionDeleteRequest() {
        EqualsVerifier.simple().forClass(IntentionDeleteRequest.class).verify();
    }

    @Test
    public void verifyIntentionListRequest() {
        EqualsVerifier.simple().forClass(IntentionListRequest.class).verify();
    }

    @Test
    public void verifyIntentionUpsertRequest() {
        EqualsVerifier.simple().forClass(IntentionUpsertRequest.class).verify();
    }

}
