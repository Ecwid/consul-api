package com.ecwid.consul.v1;

import java.util.List;
import java.util.Map;

import com.ecwid.consul.UrlParameters;
import com.ecwid.consul.transport.TLSConfig;
import com.ecwid.consul.v1.acl.AclClient;
import com.ecwid.consul.v1.acl.AclConsulClient;
import com.ecwid.consul.v1.acl.model.Acl;
import com.ecwid.consul.v1.acl.model.NewAcl;
import com.ecwid.consul.v1.acl.model.UpdateAcl;
import com.ecwid.consul.v1.agent.AgentClient;
import com.ecwid.consul.v1.agent.AgentConsulClient;
import com.ecwid.consul.v1.agent.model.Member;
import com.ecwid.consul.v1.agent.model.NewCheck;
import com.ecwid.consul.v1.agent.model.NewService;
import com.ecwid.consul.v1.agent.model.Self;
import com.ecwid.consul.v1.agent.model.Service;
import com.ecwid.consul.v1.catalog.CatalogClient;
import com.ecwid.consul.v1.catalog.CatalogConsulClient;
import com.ecwid.consul.v1.catalog.model.CatalogDeregistration;
import com.ecwid.consul.v1.catalog.model.CatalogNode;
import com.ecwid.consul.v1.catalog.model.CatalogRegistration;
import com.ecwid.consul.v1.catalog.model.CatalogService;
import com.ecwid.consul.v1.catalog.model.Node;
import com.ecwid.consul.v1.event.EventClient;
import com.ecwid.consul.v1.event.EventConsulClient;
import com.ecwid.consul.v1.event.model.Event;
import com.ecwid.consul.v1.event.model.EventParams;
import com.ecwid.consul.v1.health.HealthClient;
import com.ecwid.consul.v1.health.HealthConsulClient;
import com.ecwid.consul.v1.health.model.Check;
import com.ecwid.consul.v1.health.model.HealthService;
import com.ecwid.consul.v1.kv.KeyValueClient;
import com.ecwid.consul.v1.kv.KeyValueConsulClient;
import com.ecwid.consul.v1.kv.model.GetBinaryValue;
import com.ecwid.consul.v1.kv.model.GetValue;
import com.ecwid.consul.v1.kv.model.PutParams;
import com.ecwid.consul.v1.session.SessionClient;
import com.ecwid.consul.v1.session.SessionConsulClient;
import com.ecwid.consul.v1.session.model.NewSession;
import com.ecwid.consul.v1.session.model.Session;
import com.ecwid.consul.v1.status.StatusClient;
import com.ecwid.consul.v1.status.StatusConsulClient;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public final class ConsulClient
		implements AclClient, AgentClient, CatalogClient, EventClient, HealthClient, KeyValueClient, SessionClient, StatusClient {

	private final AclClient aclClient;
	private final AgentClient agentClient;
	private final CatalogClient catalogClient;
	private final EventClient eventClient;
	private final HealthClient healthClient;
	private final KeyValueClient keyValueClient;
	private final SessionClient sessionClient;
	private final StatusClient statusClient;

	public ConsulClient(ConsulRawClient rawClient) {
		aclClient = new AclConsulClient(rawClient);
		agentClient = new AgentConsulClient(rawClient);
		catalogClient = new CatalogConsulClient(rawClient);
		eventClient = new EventConsulClient(rawClient);
		healthClient = new HealthConsulClient(rawClient);
		keyValueClient = new KeyValueConsulClient(rawClient);
		sessionClient = new SessionConsulClient(rawClient);
		statusClient = new StatusConsulClient(rawClient);
	}

	/**
	 * Consul client will connect to local consul agent on
	 * 'http://localhost:8500'
	 */
	public ConsulClient() {
		this(new ConsulRawClient());
	}

	/**
	 * Consul client will connect to local consul agent on
	 * 'http://localhost:8500'
	 *
	 * @param tlsConfig TLS configuration
	 */
	public ConsulClient(TLSConfig tlsConfig) {
		this(new ConsulRawClient(tlsConfig));
	}

	/**
	 * Connect to consul agent on specific address and default port (8500)
	 *
	 * @param agentHost Hostname or IP address of consul agent. You can specify scheme
	 *                  (HTTP/HTTPS) in address. If there is no scheme in address -
	 *                  client will use HTTP.
	 */
	public ConsulClient(String agentHost) {
		this(new ConsulRawClient(agentHost));
	}

	/**
	 * Connect to consul agent on specific address and default port (8500)
	 *
	 * @param agentHost Hostname or IP address of consul agent. You can specify scheme
	 *                  (HTTP/HTTPS) in address. If there is no scheme in address -
	 *                  client will use HTTP.
	 * @param tlsConfig TLS configuration
	 */
	public ConsulClient(String agentHost, TLSConfig tlsConfig) {
		this(new ConsulRawClient(agentHost, tlsConfig));
	}

	/**
	 * Connect to consul agent on specific address and port
	 *
	 * @param agentHost Hostname or IP address of consul agent. You can specify scheme
	 *                  (HTTP/HTTPS) in address. If there is no scheme in address -
	 *                  client will use HTTP.
	 * @param agentPort Consul agent port
	 */
	public ConsulClient(String agentHost, int agentPort) {
		this(new ConsulRawClient(agentHost, agentPort));
	}

	/**
	 * Connect to consul agent on specific address and port
	 *
	 * @param agentHost Hostname or IP address of consul agent. You can specify scheme
	 *                  (HTTP/HTTPS) in address. If there is no scheme in address -
	 *                  client will use HTTP.
	 * @param agentPort Consul agent port
	 * @param tlsConfig TLS configuration
	 */
	public ConsulClient(String agentHost, int agentPort, TLSConfig tlsConfig) {
		this(new ConsulRawClient(agentHost, agentPort, tlsConfig));
	}

	@Override
	public Response<String> aclCreate(NewAcl newAcl, String token) {
		return aclClient.aclCreate(newAcl, token);
	}

	@Override
	public Response<Void> aclUpdate(UpdateAcl updateAcl, String token) {
		return aclClient.aclUpdate(updateAcl, token);
	}

	@Override
	public Response<Void> aclDestroy(String aclId, String token) {
		return aclClient.aclDestroy(aclId, token);
	}

	@Override
	public Response<Acl> getAcl(String id) {
		return aclClient.getAcl(id);
	}

	@Override
	public Response<String> aclClone(String aclId, String token) {
		return aclClient.aclClone(aclId, token);
	}

	@Override
	public Response<List<Acl>> getAclList(String token) {
		return aclClient.getAclList(token);
	}

	@Override
	public Response<Map<String, com.ecwid.consul.v1.agent.model.Check>> getAgentChecks() {
		return agentClient.getAgentChecks();
	}

	@Override
	public Response<Map<String, Service>> getAgentServices() {
		return agentClient.getAgentServices();
	}

	@Override
	public Response<List<Member>> getAgentMembers() {
		return agentClient.getAgentMembers();
	}

	@Override
	public Response<Self> getAgentSelf() {
		return agentClient.getAgentSelf();
	}

	@Override
	public Response<Void> agentJoin(String address, boolean wan) {
		return agentClient.agentJoin(address, wan);
	}

	@Override
	public Response<Void> agentForceLeave(String node) {
		return agentClient.agentForceLeave(node);
	}

	@Override
	public Response<Void> agentCheckRegister(NewCheck newCheck) {
		return agentClient.agentCheckRegister(newCheck);
	}

	public Response<Void> agentCheckRegister(NewCheck newCheck, String token) {
		return agentClient.agentCheckRegister(newCheck, token);
	}

	@Override
	public Response<Void> agentCheckDeregister(String checkId) {
		return agentClient.agentCheckDeregister(checkId);
	}

	@Override
	public Response<Void> agentCheckPass(String checkId) {
		return agentClient.agentCheckPass(checkId);
	}

	@Override
	public Response<Void> agentCheckPass(String checkId, String note) {
		return agentClient.agentCheckPass(checkId, note);
	}

	@Override
	public Response<Void> agentCheckWarn(String checkId) {
		return agentClient.agentCheckWarn(checkId);
	}

	@Override
	public Response<Void> agentCheckWarn(String checkId, String note) {
		return agentClient.agentCheckWarn(checkId, note);
	}

	@Override
	public Response<Void> agentCheckFail(String checkId) {
		return agentClient.agentCheckFail(checkId);
	}

	@Override
	public Response<Void> agentCheckFail(String checkId, String note) {
		return agentClient.agentCheckFail(checkId, note);
	}

	@Override
	public Response<Void> agentServiceRegister(NewService newService) {
		return agentClient.agentServiceRegister(newService);
	}

	@Override
	public Response<Void> agentServiceRegister(NewService newService, String token) {
		return agentClient.agentServiceRegister(newService, token);
	}

	@Override
	public Response<Void> agentServiceDeregister(String serviceId) {
		return agentClient.agentServiceDeregister(serviceId);
	}

	@Override
	public Response<Void> catalogRegister(CatalogRegistration catalogRegistration) {
		return catalogClient.catalogRegister(catalogRegistration);
	}

	@Override
	public Response<Void> catalogDeregister(CatalogDeregistration catalogDeregistration) {
		return catalogClient.catalogDeregister(catalogDeregistration);
	}

	@Override
	public Response<List<String>> getCatalogDatacenters() {
		return catalogClient.getCatalogDatacenters();
	}

	@Override
	public Response<List<Node>> getCatalogNodes(UrlParameters UrlParameters) {
		return catalogClient.getCatalogNodes(UrlParameters);
	}

	@Override
	public Response<Map<String, List<String>>> getCatalogServices(UrlParameters UrlParameters) {
		return catalogClient.getCatalogServices(UrlParameters);
	}

	@Override
	public Response<List<CatalogService>> getCatalogService(String serviceName, UrlParameters UrlParameters) {
		return catalogClient.getCatalogService(serviceName, UrlParameters);
	}

	@Override
	public Response<List<CatalogService>> getCatalogService(String serviceName, String tag, UrlParameters UrlParameters) {
		return catalogClient.getCatalogService(serviceName, tag, UrlParameters);
	}

	@Override
	public Response<CatalogNode> getCatalogNode(String nodeName, UrlParameters UrlParameters) {
		return catalogClient.getCatalogNode(nodeName, UrlParameters);
	}

	@Override
	public Response<Event> eventFire(String event, String payload, EventParams eventParams, UrlParameters UrlParameters) {
		return eventClient.eventFire(event, payload, eventParams, UrlParameters);
	}

	@Override
	public Response<List<Event>> eventList(UrlParameters UrlParameters) {
		return eventClient.eventList(UrlParameters);
	}

	@Override
	public Response<List<Event>> eventList(String event, UrlParameters UrlParameters) {
		return eventClient.eventList(event, UrlParameters);
	}

	@Override
	public Response<List<Check>> getHealthChecksForNode(String nodeName, UrlParameters UrlParameters) {
		return healthClient.getHealthChecksForNode(nodeName, UrlParameters);
	}

	@Override
	public Response<List<Check>> getHealthChecksForService(String serviceName, UrlParameters UrlParameters) {
		return healthClient.getHealthChecksForService(serviceName, UrlParameters);
	}

	@Override
	public Response<List<HealthService>> getHealthServices(String serviceName, boolean onlyPassing, UrlParameters UrlParameters) {
		return healthClient.getHealthServices(serviceName, onlyPassing, UrlParameters);
	}

	@Override
	public Response<List<HealthService>> getHealthServices(String serviceName, String tag, boolean onlyPassing, UrlParameters UrlParameters) {
		return healthClient.getHealthServices(serviceName, tag, onlyPassing, UrlParameters);
	}

	@Override
	public Response<List<Check>> getHealthChecksState(UrlParameters UrlParameters) {
		return healthClient.getHealthChecksState(UrlParameters);
	}

	@Override
	public Response<List<Check>> getHealthChecksState(Check.CheckStatus checkStatus, UrlParameters UrlParameters) {
		return healthClient.getHealthChecksState(checkStatus, UrlParameters);
	}

	@Override
	public Response<GetValue> getKVValue(String key) {
		return keyValueClient.getKVValue(key);
	}

	@Override
	public Response<GetValue> getKVValue(String key, String token) {
		return keyValueClient.getKVValue(key, token);
	}

	@Override
	public Response<GetValue> getKVValue(String key, UrlParameters UrlParameters) {
		return keyValueClient.getKVValue(key, UrlParameters);
	}

	@Override
	public Response<GetValue> getKVValue(String key, String token, UrlParameters UrlParameters) {
		return keyValueClient.getKVValue(key, token, UrlParameters);
	}

	@Override
	public Response<GetBinaryValue> getKVBinaryValue(String key) {
		return keyValueClient.getKVBinaryValue(key);
	}

	@Override
	public Response<GetBinaryValue> getKVBinaryValue(String key, String token) {
		return keyValueClient.getKVBinaryValue(key, token);
	}

	@Override
	public Response<GetBinaryValue> getKVBinaryValue(String key, UrlParameters UrlParameters) {
		return keyValueClient.getKVBinaryValue(key, UrlParameters);
	}

	@Override
	public Response<GetBinaryValue> getKVBinaryValue(String key, String token, UrlParameters UrlParameters) {
		return keyValueClient.getKVBinaryValue(key, token, UrlParameters);
	}

	@Override
	public Response<List<GetValue>> getKVValues(String keyPrefix) {
		return keyValueClient.getKVValues(keyPrefix);
	}

	@Override
	public Response<List<GetValue>> getKVValues(String keyPrefix, String token) {
		return keyValueClient.getKVValues(keyPrefix, token);
	}

	@Override
	public Response<List<GetValue>> getKVValues(String keyPrefix, UrlParameters UrlParameters) {
		return keyValueClient.getKVValues(keyPrefix, UrlParameters);
	}

	@Override
	public Response<List<GetValue>> getKVValues(String keyPrefix, String token, UrlParameters UrlParameters) {
		return keyValueClient.getKVValues(keyPrefix, token, UrlParameters);
	}

	@Override
	public Response<List<GetBinaryValue>> getKVBinaryValues(String keyPrefix) {
		return keyValueClient.getKVBinaryValues(keyPrefix);
	}

	@Override
	public Response<List<GetBinaryValue>> getKVBinaryValues(String keyPrefix, String token) {
		return keyValueClient.getKVBinaryValues(keyPrefix, token);
	}

	@Override
	public Response<List<GetBinaryValue>> getKVBinaryValues(String keyPrefix, UrlParameters UrlParameters) {
		return keyValueClient.getKVBinaryValues(keyPrefix, UrlParameters);
	}

	@Override
	public Response<List<GetBinaryValue>> getKVBinaryValues(String keyPrefix, String token, UrlParameters UrlParameters) {
		return keyValueClient.getKVBinaryValues(keyPrefix, token, UrlParameters);
	}

	@Override
	public Response<List<String>> getKVKeysOnly(String keyPrefix) {
		return keyValueClient.getKVKeysOnly(keyPrefix);
	}

	@Override
	public Response<List<String>> getKVKeysOnly(String keyPrefix, String separator, String token) {
		return keyValueClient.getKVKeysOnly(keyPrefix, separator, token);
	}

	@Override
	public Response<List<String>> getKVKeysOnly(String keyPrefix, UrlParameters UrlParameters) {
		return keyValueClient.getKVKeysOnly(keyPrefix, UrlParameters);
	}

	@Override
	public Response<List<String>> getKVKeysOnly(String keyPrefix, String separator, String token, UrlParameters UrlParameters) {
		return keyValueClient.getKVKeysOnly(keyPrefix, separator, token, UrlParameters);
	}

	@Override
	public Response<Boolean> setKVValue(String key, String value) {
		return keyValueClient.setKVValue(key, value);
	}

	@Override
	public Response<Boolean> setKVValue(String key, String value, PutParams putParams) {
		return keyValueClient.setKVValue(key, value, putParams);
	}

	@Override
	public Response<Boolean> setKVValue(String key, String value, String token, PutParams putParams) {
		return keyValueClient.setKVValue(key, value, token, putParams);
	}

	@Override
	public Response<Boolean> setKVValue(String key, String value, UrlParameters UrlParameters) {
		return keyValueClient.setKVValue(key, value, UrlParameters);
	}

	@Override
	public Response<Boolean> setKVValue(String key, String value, PutParams putParams, UrlParameters UrlParameters) {
		return keyValueClient.setKVValue(key, value, putParams, UrlParameters);
	}

	@Override
	public Response<Boolean> setKVValue(String key, String value, String token, PutParams putParams, UrlParameters UrlParameters) {
		return keyValueClient.setKVValue(key, value, token, putParams, UrlParameters);
	}

	@Override
	public Response<Boolean> setKVBinaryValue(String key, byte[] value) {
		return keyValueClient.setKVBinaryValue(key, value);
	}

	@Override
	public Response<Boolean> setKVBinaryValue(String key, byte[] value, PutParams putParams) {
		return keyValueClient.setKVBinaryValue(key, value, putParams);
	}

	@Override
	public Response<Boolean> setKVBinaryValue(String key, byte[] value, String token, PutParams putParams) {
		return keyValueClient.setKVBinaryValue(key, value, token, putParams);
	}

	@Override
	public Response<Boolean> setKVBinaryValue(String key, byte[] value, UrlParameters UrlParameters) {
		return keyValueClient.setKVBinaryValue(key, value, UrlParameters);
	}

	@Override
	public Response<Boolean> setKVBinaryValue(String key, byte[] value, PutParams putParams, UrlParameters UrlParameters) {
		return keyValueClient.setKVBinaryValue(key, value, putParams, UrlParameters);
	}

	@Override
	public Response<Boolean> setKVBinaryValue(String key, byte[] value, String token, PutParams putParams, UrlParameters UrlParameters) {
		return keyValueClient.setKVBinaryValue(key, value, token, putParams, UrlParameters);
	}

	@Override
	public Response<Void> deleteKVValue(String key) {
		return keyValueClient.deleteKVValue(key);
	}

	@Override
	public Response<Void> deleteKVValue(String key, String token) {
		return keyValueClient.deleteKVValue(key, token);
	}

	@Override
	public Response<Void> deleteKVValue(String key, UrlParameters UrlParameters) {
		return keyValueClient.deleteKVValue(key, UrlParameters);
	}

	@Override
	public Response<Void> deleteKVValue(String key, String token, UrlParameters UrlParameters) {
		return keyValueClient.deleteKVValue(key, token, UrlParameters);
	}

	@Override
	public Response<Void> deleteKVValues(String key) {
		return keyValueClient.deleteKVValues(key);
	}

	@Override
	public Response<Void> deleteKVValues(String key, String token) {
		return keyValueClient.deleteKVValues(key, token);
	}

	@Override
	public Response<Void> deleteKVValues(String key, UrlParameters UrlParameters) {
		return keyValueClient.deleteKVValues(key, UrlParameters);
	}

	@Override
	public Response<Void> deleteKVValues(String key, String token, UrlParameters UrlParameters) {
		return keyValueClient.deleteKVValues(key, token, UrlParameters);
	}

	@Override
	public Response<String> sessionCreate(NewSession newSession, UrlParameters UrlParameters) {
		return sessionClient.sessionCreate(newSession, UrlParameters);
	}

	@Override
	public Response<Void> sessionDestroy(String session, UrlParameters UrlParameters) {
		return sessionClient.sessionDestroy(session, UrlParameters);
	}

	@Override
	public Response<Session> getSessionInfo(String session, UrlParameters UrlParameters) {
		return sessionClient.getSessionInfo(session, UrlParameters);
	}

	@Override
	public Response<List<Session>> getSessionNode(String node, UrlParameters UrlParameters) {
		return sessionClient.getSessionNode(node, UrlParameters);
	}

	@Override
	public Response<List<Session>> getSessionList(UrlParameters UrlParameters) {
		return sessionClient.getSessionList(UrlParameters);
	}

	@Override
	public Response<Session> renewSession(String session, UrlParameters UrlParameters) {
		return sessionClient.renewSession(session, UrlParameters);
	}

	@Override
	public Response<String> getStatusLeader() {
		return statusClient.getStatusLeader();
	}

	@Override
	public Response<List<String>> getStatusPeers() {
		return statusClient.getStatusPeers();
	}
}
