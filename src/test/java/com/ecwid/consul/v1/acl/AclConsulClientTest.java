package com.ecwid.consul.v1.acl;

import com.ecwid.consul.ConsulTestConstants;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.acl.model.Acl;
import com.ecwid.consul.v1.acl.model.AclType;
import com.ecwid.consul.v1.acl.model.NewAcl;
import com.pszymczyk.consul.ConsulProcess;
import com.pszymczyk.consul.ConsulStarterBuilder;
import com.pszymczyk.consul.infrastructure.Ports;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class AclConsulClientTest {

    private static final String ACL_MASTER_TOKEN = "mastertoken";

    private ConsulProcess consul;
    private int port = Ports.nextAvailable();

    private AclClient aclClient = new AclConsulClient("localhost", port);

    @BeforeEach
    public void setup() {
        String customConfiguration =
                "{ \"acl_master_token\": \"" + ACL_MASTER_TOKEN + "\"" +
                ", \"acl_datacenter\": \"dc-test\"" +
                ", \"datacenter\": \"dc-test\" }";

        consul = ConsulStarterBuilder.consulStarter()
                .withConsulVersion(ConsulTestConstants.CONSUL_VERSION)
                .withHttpPort(port)
                .withCustomConfig(customConfiguration)
                .build()
                .start();
    }

    @AfterEach
    public void cleanup() throws Exception {
        consul.close();
    }

    @Test
    public void should_create_client_acl_token() {
        should_create_acl_token(AclType.CLIENT);
    }

    @Test
    public void should_create_management_acl_token() {
        should_create_acl_token(AclType.MANAGEMENT);
    }

    private void should_create_acl_token(AclType aclType) {
        // given
        NewAcl newAcl = new NewAcl();
        newAcl.setName("test-acl");
        newAcl.setType(aclType);
        newAcl.setRules("");

        // when
        Response<String> response = aclClient.aclCreate(newAcl, ACL_MASTER_TOKEN);
        String aclId = response.getValue();

        // then
        Acl createdAcl = aclClient.getAcl(aclId).getValue();
        assertThat(createdAcl.getName(), equalTo(newAcl.getName()));
        assertThat(createdAcl.getType(), equalTo(newAcl.getType()));
        assertThat(createdAcl.getRules(), equalTo(newAcl.getRules()));
    }
}
