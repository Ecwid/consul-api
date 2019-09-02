package com.ecwid.consul.v1.agent;

import com.ecwid.consul.SingleUrlParameters;
import com.ecwid.consul.UrlParameters;
import com.ecwid.consul.json.GsonFactory;
import com.ecwid.consul.transport.HttpResponse;
import com.ecwid.consul.transport.TLSConfig;
import com.ecwid.consul.v1.ConsulRawClient;
import com.ecwid.consul.v1.OperationException;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.agent.model.*;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Map;

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
		HttpResponse httpResponse = rawClient.makeGetRequest("/v1/agent/checks");

		if (httpResponse.getStatusCode() == 200) {
			Map<String, Check> value = GsonFactory.getGson().fromJson(httpResponse.getContent(), new TypeToken<Map<String, Check>>() {
			}.getType());
			return new Response<Map<String, Check>>(value, httpResponse);
		} else {
			throw new OperationException(httpResponse);
		}
	}

	@Override
	public Response<Map<String, Service>> getAgentServices() {
		HttpResponse httpResponse = rawClient.makeGetRequest("/v1/agent/services");

		if (httpResponse.getStatusCode() == 200) {
			Map<String, Service> agentServices = GsonFactory.getGson().fromJson(httpResponse.getContent(),
					new TypeToken<Map<String, Service>>() {
					}.getType());
			return new Response<Map<String, Service>>(agentServices, httpResponse);
		} else {
			throw new OperationException(httpResponse);
		}
	}

	@Override
	public Response<List<Member>> getAgentMembers() {
		HttpResponse httpResponse = rawClient.makeGetRequest("/v1/agent/members");

		if (httpResponse.getStatusCode() == 200) {
			List<Member> members = GsonFactory.getGson().fromJson(httpResponse.getContent(), new TypeToken<List<Member>>() {
			}.getType());
			return new Response<List<Member>>(members, httpResponse);
		} else {
			throw new OperationException(httpResponse);
		}
	}

	@Override
	public Response<Self> getAgentSelf() {
		return getAgentSelf(null);
	}

	@Override
	public Response<Self> getAgentSelf(String token) {
		UrlParameters tokenParam = token != null ? new SingleUrlParameters("token", token) : null;

		HttpResponse httpResponse = rawClient.makeGetRequest("/v1/agent/self", tokenParam);

		if (httpResponse.getStatusCode() == 200) {
			Self self = GsonFactory.getGson().fromJson(httpResponse.getContent(), Self.class);
			return new Response<Self>(self, httpResponse);
		} else {
			throw new OperationException(httpResponse);
		}
	}

	@Override
	public Response<Void> agentSetMaintenance(boolean maintenanceEnabled) {
		return agentSetMaintenance(maintenanceEnabled, null);
	}

	@Override
	public Response<Void> agentSetMaintenance(boolean maintenanceEnabled, String reason) {
		UrlParameters maintenanceParameter = new SingleUrlParameters("enable", Boolean.toString(maintenanceEnabled));
		UrlParameters reasonParamenter = reason != null ? new SingleUrlParameters("reason", reason) : null;

		HttpResponse httpResponse = rawClient.makePutRequest("/v1/agent/maintenance", "", maintenanceParameter, reasonParamenter);

		if (httpResponse.getStatusCode() == 200) {
			return new Response<Void>(null, httpResponse);
		} else {
			throw new OperationException(httpResponse);
		}

	}

	@Override
	public Response<Void> agentJoin(String address, boolean wan) {
		UrlParameters wanParams = wan ? new SingleUrlParameters("wan", "1") : null;
		HttpResponse httpResponse = rawClient.makePutRequest("/v1/agent/join/" + address, "", wanParams);

		if (httpResponse.getStatusCode() == 200) {
			return new Response<Void>(null, httpResponse);
		} else {
			throw new OperationException(httpResponse);
		}
	}

	@Override
	public Response<Void> agentForceLeave(String node) {
		HttpResponse httpResponse = rawClient.makePutRequest("/v1/agent/force-leave/" + node, "");

		if (httpResponse.getStatusCode() == 200) {
			return new Response<Void>(null, httpResponse);
		} else {
			throw new OperationException(httpResponse);
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
		HttpResponse httpResponse = rawClient.makePutRequest("/v1/agent/check/register", json, tokenParam);

		if (httpResponse.getStatusCode() == 200) {
			return new Response<Void>(null, httpResponse);
		} else {
			throw new OperationException(httpResponse);
		}
	}

	@Override
	public Response<Void> agentCheckDeregister(String checkId) {
		return agentCheckDeregister(checkId, null);
	}

	@Override
	public Response<Void> agentCheckDeregister(String checkId, String token) {
		UrlParameters tokenParameter = token != null ? new SingleUrlParameters("token", token) : null;

		HttpResponse httpResponse = rawClient.makePutRequest("/v1/agent/check/deregister/" + checkId, "", tokenParameter);

		if (httpResponse.getStatusCode() == 200) {
			return new Response<Void>(null, httpResponse);
		} else {
			throw new OperationException(httpResponse);
		}
	}

	@Override
	public Response<Void> agentCheckPass(String checkId) {
		return agentCheckPass(checkId, null);
	}

	@Override
	public Response<Void> agentCheckPass(String checkId, String note) {
		return agentCheckPass(checkId, note, null);
	}

	@Override
	public Response<Void> agentCheckPass(String checkId, String note, String token) {
		UrlParameters noteParameter = note != null ? new SingleUrlParameters("note", note) : null;
		UrlParameters tokenParameter = token != null ? new SingleUrlParameters("token", token) : null;

		HttpResponse httpResponse = rawClient.makePutRequest("/v1/agent/check/pass/" + checkId, "", noteParameter, tokenParameter);
    
		if (httpResponse.getStatusCode() == 200) {
			return new Response<Void>(null, httpResponse);
		} else {
			throw new OperationException(httpResponse);
		}
	}

	@Override
	public Response<Void> agentCheckWarn(String checkId) {
		return agentCheckWarn(checkId, null);
	}

	@Override
	public Response<Void> agentCheckWarn(String checkId, String note) {
		return agentCheckWarn(checkId, note, null);
	}

	@Override
	public Response<Void> agentCheckWarn(String checkId, String note, String token) {
		UrlParameters noteParameter = note != null ? new SingleUrlParameters("note", note) : null;
		UrlParameters tokenParameter = token != null ? new SingleUrlParameters("token", token) : null;

		HttpResponse httpResponse = rawClient.makePutRequest("/v1/agent/check/warn/" + checkId, "", noteParameter, tokenParameter);

		if (httpResponse.getStatusCode() == 200) {
			return new Response<Void>(null, httpResponse);
		} else {
			throw new OperationException(httpResponse);
		}
	}

	@Override
	public Response<Void> agentCheckFail(String checkId) {
		return agentCheckFail(checkId, null);
	}

	@Override
	public Response<Void> agentCheckFail(String checkId, String note) {
		return agentCheckFail(checkId, note, null);
	}

	@Override
	public Response<Void> agentCheckFail(String checkId, String note, String token) {
		UrlParameters noteParameter = note != null ? new SingleUrlParameters("note", note) : null;
		UrlParameters tokenParameter = token != null ? new SingleUrlParameters("token", token) : null;

		HttpResponse httpResponse = rawClient.makePutRequest("/v1/agent/check/fail/" + checkId, "", noteParameter, tokenParameter);

		if (httpResponse.getStatusCode() == 200) {
			return new Response<Void>(null, httpResponse);
		} else {
			throw new OperationException(httpResponse);
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
		HttpResponse httpResponse = rawClient.makePutRequest("/v1/agent/service/register", json, tokenParam);

		if (httpResponse.getStatusCode() == 200) {
			return new Response<Void>(null, httpResponse);
		} else {
			throw new OperationException(httpResponse);
		}
	}

	@Override
	public Response<Void> agentServiceDeregister(String serviceId) {
		return agentServiceDeregister(serviceId, null);
	}

	@Override
	public Response<Void> agentServiceDeregister(String serviceId, String token) {
		UrlParameters tokenParam = token != null ? new SingleUrlParameters("token", token) : null;

		HttpResponse httpResponse = rawClient.makePutRequest("/v1/agent/service/deregister/" + serviceId, "", tokenParam);

		if (httpResponse.getStatusCode() == 200) {
			return new Response<Void>(null, httpResponse);
		} else {
			throw new OperationException(httpResponse);
		}
	}

	@Override
	public Response<Void> agentServiceSetMaintenance(String serviceId, boolean maintenanceEnabled) {
		return agentServiceSetMaintenance(serviceId, maintenanceEnabled, null);
	}

	@Override
	public Response<Void> agentServiceSetMaintenance(String serviceId, boolean maintenanceEnabled, String reason) {
		UrlParameters maintenanceParameter = new SingleUrlParameters("enable", Boolean.toString(maintenanceEnabled));
		UrlParameters reasonParameter = reason != null ? new SingleUrlParameters("reason", reason) : null;

		HttpResponse httpResponse = rawClient.makePutRequest("/v1/agent/service/maintenance/" + serviceId, "", maintenanceParameter, reasonParameter);

		if (httpResponse.getStatusCode() == 200) {
			return new Response<Void>(null, httpResponse);
		} else {
			throw new OperationException(httpResponse);
		}
	}

	@Override
	public Response<Void> agentReload() {
		HttpResponse httpResponse = rawClient.makePutRequest("/v1/agent/reload", "");

		if (httpResponse.getStatusCode() == 200) {
			return new Response<Void>(null, httpResponse);
		} else {
			throw new OperationException(httpResponse);
		}

	}
}
