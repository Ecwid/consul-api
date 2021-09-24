package com.ecwid.consul.v1.connect.intentions.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class EqualsVerifierTest {

    @Test
    public void verifyIntention() {
        EqualsVerifier.simple().forClass(Intention.class).verify();
    }

    @Test
    public void verifyIntentionHttpHeaderPermission() {
        EqualsVerifier.simple().forClass(IntentionHttpHeaderPermission.class).verify();
    }

    @Test
    public void verifyIntentionHttpPermission() {
        EqualsVerifier.simple().forClass(IntentionHttpPermission.class).verify();
    }

    @Test
    public void verifyIntentionPermission() {
        EqualsVerifier.simple().forClass(IntentionPermission.class).verify();
    }

    @Test
    public void verifyIntentionResponse() {
        EqualsVerifier.simple().forClass(IntentionResponse.class).verify();
    }

}
