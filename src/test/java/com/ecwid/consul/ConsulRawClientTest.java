package com.ecwid.consul;

import com.ecwid.consul.v1.ConsulRawClient;
import com.ecwid.consul.v1.QueryParams;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ConsulRawClientTest {

    private static final String ENDPOINT = "/any/endpoint";
    private static final QueryParams EMPTY_QUERY_PARAMS = QueryParams.Builder.builder().build();
    private static final String HOST = "host";
    private static final int PORT = 8888;
    private static final String PATH = "path";
    private static final String EXPECTED_AGENT_ADDRESS_NO_PATH = "http://" + HOST + ":"+ PORT + ENDPOINT;
    private static final String EXPECTED_AGENT_ADDRESS = "http://" + HOST + ":"+ PORT + "/" + PATH + ENDPOINT;

    @Test
    public void verifyDefaultUrl() throws Exception {
        // Given
        HttpClient httpClient = mock(HttpClient.class);
        ConsulRawClient client = ConsulRawClient.Builder.builder()
                .setHttpClient(httpClient)
                .setHost(HOST)
                .setPort(PORT)
                .build();

        // When
        client.makeGetRequest(ENDPOINT, EMPTY_QUERY_PARAMS);

        // Then
        ArgumentCaptor<HttpUriRequest> calledUri = ArgumentCaptor.forClass(HttpUriRequest.class);
        verify(httpClient).execute(calledUri.capture(), any(ResponseHandler.class));
        assertEquals(EXPECTED_AGENT_ADDRESS_NO_PATH, calledUri.getValue().getURI().toString());
    }

    @Test
    public void verifyUrlWithPath() throws Exception {
        // Given
        HttpClient httpClient = mock(HttpClient.class);
        ConsulRawClient client = ConsulRawClient.Builder.builder()
                .setHttpClient(httpClient)
                .setHost(HOST)
                .setPort(PORT)
                .setPath(PATH)
                .build();

        // When
        client.makeGetRequest(ENDPOINT, EMPTY_QUERY_PARAMS);

        // Then
        ArgumentCaptor<HttpUriRequest> calledUri = ArgumentCaptor.forClass(HttpUriRequest.class);
        verify(httpClient).execute(calledUri.capture(), any(ResponseHandler.class));
        assertEquals(EXPECTED_AGENT_ADDRESS, calledUri.getValue().getURI().toString());
    }
}
