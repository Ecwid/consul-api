package com.ecwid.consul.v1;

import com.ecwid.consul.ConsulTestConstants;
import com.ecwid.consul.transport.TLSConfig;
import com.ecwid.consul.v1.agent.model.NewService;
import com.ecwid.consul.v1.agent.model.Service;
import com.pszymczyk.consul.ConsulProcess;
import com.pszymczyk.consul.ConsulStarterBuilder;
import com.pszymczyk.consul.infrastructure.Ports;
import org.hamcrest.collection.IsMapContaining;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;


public class ConsulClientTest {

    private ConsulProcess consul;
    private int randomHttpsPort = Ports.nextAvailable();

    @BeforeEach
    public void setup() {
        String path = "src/test/resources/ssl";
        String certRootPath = new File(path).getAbsolutePath();
        //language=JSON
        String customConfiguration =
                "{\n" +
                "  \"datacenter\": \"dc-test\",\n" +
                "  \"log_level\": \"info\",\n" +
                "  \"ports\": {\n" +
                "    \"https\": "+ randomHttpsPort+ "\n" +
                "  },\n" +
                "  \"ca_file\": \"" + certRootPath + "/ca.cert\",\n" +
                "  \"key_file\": \"" + certRootPath + "/key.key\",\n" +
                "  \"cert_file\": \"" + certRootPath + "/key.crt\"\n" +
                "}\n";

        consul = ConsulStarterBuilder.consulStarter()
                .withConsulVersion(ConsulTestConstants.CONSUL_VERSION)
                .withCustomConfig(customConfiguration)
                .build()
                .start();
    }

    @AfterEach
    public void cleanup() throws Exception {
        consul.close();
    }

    @Test
    public void agentHttpTest() throws Exception {
        String host = "http://localhost";
        int port = consul.getHttpPort();
        ConsulClient consulClient = new ConsulClient(host, port);
        serviceRegisterTest(consulClient);
    }

    @Test
    public void agentHttpsTest() throws Exception {

        String host = "https://localhost";
        //TODO make https random port in consul
        int httpsPort = randomHttpsPort;

        String path = "src/test/resources/ssl";
        String certRootPath = new File(path).getAbsolutePath();
        String certificatePath = certRootPath + "/trustStore.jks";
        String certificatePassword = "change_me";
        String keyStorePath = certRootPath + "/keyStore.jks";
        String keyStorePassword = "change_me";

        TLSConfig tlsConfig = new TLSConfig(TLSConfig.KeyStoreInstanceType.JKS,
                certificatePath, certificatePassword,
                keyStorePath, keyStorePassword);
        ConsulClient consulClient = new ConsulClient(host, httpsPort, tlsConfig);
        serviceRegisterTest(consulClient);
    }

    private void serviceRegisterTest(ConsulClient consulClient) {
        NewService newService = new NewService();
        String serviceName = "abc";
        newService.setName(serviceName);
        consulClient.agentServiceRegister(newService);

        Response<Map<String, Service>> agentServicesResponse = consulClient.getAgentServices();
        Map<String, Service> services = agentServicesResponse.getValue();
        assertThat(services, IsMapContaining.hasKey(serviceName));
    }
}
