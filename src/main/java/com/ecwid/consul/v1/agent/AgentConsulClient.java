package com.ecwid.consul.v1.agent;

import java.util.List;
import java.util.Map;

import com.ecwid.consul.SingleUrlParameters;
import com.ecwid.consul.UrlParameters;
import com.ecwid.consul.json.GsonFactory;
import com.ecwid.consul.transport.RawResponse;
import com.ecwid.consul.transport.TLSConfig;
import com.ecwid.consul.v1.ConsulRawClient;
import com.ecwid.consul.v1.OperationException;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.agent.model.Check;
import com.ecwid.consul.v1.agent.model.Member;
import com.ecwid.consul.v1.agent.model.NewCheck;
import com.ecwid.consul.v1.agent.model.NewService;
import com.ecwid.consul.v1.agent.model.Self;
import com.ecwid.consul.v1.agent.model.Service;
import com.google.gson.reflect.TypeToken;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public final class AgentConsulClient implements AgentClient {

    private final ConsulRawClient rawClient;

    public AgentConsulClient(ConsulRawClient rawClient) {
	this.rawClient = rawClient;
    }

    public AgentConsulClient() {
	this(new ConsulRawClient());
    }

    public AgentConsulClient(TLSConfig tlsConfig) {
	this(new ConsulRawClient(tlsConfig));
    }

    public AgentConsulClient(String agentHost) {
	this(new ConsulRawClient(agentHost));
    }

    public AgentConsulClient(String agentHost, TLSConfig tlsConfig) {
	this(new ConsulRawClient(agentHost, tlsConfig));
    }

    public AgentConsulClient(String agentHost, int agentPort) {
	this(new ConsulRawClient(agentHost, agentPort));
    }

    public AgentConsulClient(String agentHost, int agentPort, TLSConfig tlsConfig) {
	this(new ConsulRawClient(agentHost, agentPort, tlsConfig));
    }

    @Override
    public Response<Map<String, Check>> getAgentChecks() {
	RawResponse rawResponse = rawClient.makeGetRequest("/v1/agent/checks");

	if (rawResponse.getStatusCode() == 200) {
	    Map<String, Check> value = GsonFactory.getGson().fromJson(rawResponse.getContent(), new TypeToken<Map<String, Check>>() {
	    }.getType());
	    return new Response<Map<String, Check>>(value, rawResponse);
	} else {
	    throw new OperationException(rawResponse);
	}
    }

    @Override
    public Response<Map<String, Service>> getAgentServices() {
	RawResponse rawResponse = rawClient.makeGetRequest("/v1/agent/services");

	if (rawResponse.getStatusCode() == 200) {
	    Map<String, Service> agentServices = GsonFactory.getGson().fromJson(rawResponse.getContent(),
	            new TypeToken<Map<String, Service>>() {
	            }.getType());
	    return new Response<Map<String, Service>>(agentServices, rawResponse);
	} else {
	    throw new OperationException(rawResponse);
	}
    }

    @Override
    public Response<List<Member>> getAgentMembers() {
	RawResponse rawResponse = rawClient.makeGetRequest("/v1/agent/members");

	if (rawResponse.getStatusCode() == 200) {
	    List<Member> members = GsonFactory.getGson().fromJson(rawResponse.getContent(), new TypeToken<List<Member>>() {
	    }.getType());
	    return new Response<List<Member>>(members, rawResponse);
	} else {
	    throw new OperationException(rawResponse);
	}
    }

    @Override
    public Response<Self> getAgentSelf() {
	RawResponse rawResponse = rawClient.makeGetRequest("/v1/agent/self");

	if (rawResponse.getStatusCode() == 200) {
	    Self self = GsonFactory.getGson().fromJson(rawResponse.getContent(), Self.class);
	    return new Response<Self>(self, rawResponse);
	} else {
	    throw new OperationException(rawResponse);
	}
    }

    @Override
    public Response<Void> agentJoin(String address, boolean wan) {
	UrlParameters wanParams = wan ? new SingleUrlParameters("wan", "1") : null;
	RawResponse rawResponse = rawClient.makeGetRequest("/v1/agent/join/" + address, wanParams);

	if (rawResponse.getStatusCode() == 200) {
	    return new Response<Void>(null, rawResponse);
	} else {
	    throw new OperationException(rawResponse);
	}
    }

    @Override
    public Response<Void> agentForceLeave(String node) {
	RawResponse rawResponse = rawClient.makeGetRequest("/v1/agent/force-leave/" + node);

	if (rawResponse.getStatusCode() == 200) {
	    return new Response<Void>(null, rawResponse);
	} else {
	    throw new OperationException(rawResponse);
	}
    }

    @Override
    public Response<Void> agentCheckRegister(NewCheck newCheck) {
	return agentCheckRegister(newCheck, null);
    }

    @Override
    public Response<Void> agentCheckRegister(NewCheck newCheck, String token) {
	UrlParameters tokenParam = token != null ? new SingleUrlParameters("token", token) : null;

	String json = GsonFactory.getGson().toJson(newCheck);
	RawResponse rawResponse = rawClient.makePutRequest("/v1/agent/check/register", json, tokenParam);

	if (rawResponse.getStatusCode() == 200) {
	    return new Response<Void>(null, rawResponse);
	} else {
	    throw new OperationException(rawResponse);
	}
    }

    @Override
    public Response<Void> agentCheckDeregister(String checkId) {
	RawResponse rawResponse = rawClient.makeGetRequest("/v1/agent/check/deregister/" + checkId);

	if (rawResponse.getStatusCode() == 200) {
	    return new Response<Void>(null, rawResponse);
	} else {
	    throw new OperationException(rawResponse);
	}
    }

    @Override
    public Response<Void> agentCheckPass(String checkId) {
	return agentCheckPass(checkId, null);
    }

    @Override
    public Response<Void> agentCheckPass(String checkId, String note) {
	UrlParameters noteParams = note != null ? new SingleUrlParameters("note", note) : null;
	RawResponse rawResponse = rawClient.makeGetRequest("/v1/agent/check/pass/" + checkId, noteParams);

	if (rawResponse.getStatusCode() == 200) {
	    return new Response<Void>(null, rawResponse);
	} else {
	    throw new OperationException(rawResponse);
	}
    }

    @Override
    public Response<Void> agentCheckWarn(String checkId) {
	return agentCheckWarn(checkId, null);
    }

    @Override
    public Response<Void> agentCheckWarn(String checkId, String note) {
	UrlParameters noteParams = note != null ? new SingleUrlParameters("note", note) : null;
	RawResponse rawResponse = rawClient.makeGetRequest("/v1/agent/check/warn/" + checkId, noteParams);

	if (rawResponse.getStatusCode() == 200) {
	    return new Response<Void>(null, rawResponse);
	} else {
	    throw new OperationException(rawResponse);
	}
    }

    @Override
    public Response<Void> agentCheckFail(String checkId) {
	return agentCheckFail(checkId, null);
    }

    @Override
    public Response<Void> agentCheckFail(String checkId, String note) {
	UrlParameters noteParams = note != null ? new SingleUrlParameters("note", note) : null;
	RawResponse rawResponse = rawClient.makeGetRequest("/v1/agent/check/fail/" + checkId, noteParams);

	if (rawResponse.getStatusCode() == 200) {
	    return new Response<Void>(null, rawResponse);
	} else {
	    throw new OperationException(rawResponse);
	}
    }

    @Override
    public Response<Void> agentServiceRegister(NewService newService) {
	return agentServiceRegister(newService, null);
    }

    @Override
    public Response<Void> agentServiceRegister(NewService newService, String token) {
	UrlParameters tokenParam = token != null ? new SingleUrlParameters("token", token) : null;

	String json = GsonFactory.getGson().toJson(newService);
	RawResponse rawResponse = rawClient.makePutRequest("/v1/agent/service/register", json, tokenParam);

	if (rawResponse.getStatusCode() == 200) {
	    return new Response<Void>(null, rawResponse);
	} else {
	    throw new OperationException(rawResponse);
	}
    }

    @Override
    public Response<Void> agentServiceDeregister(String serviceId) {
	RawResponse rawResponse = rawClient.makeGetRequest("/v1/agent/service/deregister/" + serviceId);

	if (rawResponse.getStatusCode() == 200) {
	    return new Response<Void>(null, rawResponse);
	} else {
	    throw new OperationException(rawResponse);
	}
    }

    @Override
    public Response<Void> agentServiceSetMaintenance(String serviceId, boolean maintenanceEnabled) {
	UrlParameters maintenanceParameter = new SingleUrlParameters("enable", Boolean.toString(maintenanceEnabled));
	RawResponse rawResponse = rawClient.makePutRequest("/v1/agent/service/maintenance/" + serviceId, "", maintenanceParameter);

	if (rawResponse.getStatusCode() == 200) {
	    return new Response<Void>(null, rawResponse);
	} else {
	    throw new OperationException(rawResponse);
	}
    }
}
