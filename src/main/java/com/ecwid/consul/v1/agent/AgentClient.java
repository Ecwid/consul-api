package com.ecwid.consul.v1.agent;

import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.agent.model.*;

import java.util.List;
import java.util.Map;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public interface AgentClient {

	public Response<Map<String, Check>> getAgentChecks();

	public Response<Map<String, Service>> getAgentServices();

	public Response<List<Member>> getAgentMembers();

	public Response<Self> getAgentSelf();
	
	public Response<Self> getAgentSelf(String token);

	public Response<Void> agentSetMaintenance(boolean maintenanceEnabled);

	public Response<Void> agentSetMaintenance(boolean maintenanceEnabled, String reason);

	public Response<Void> agentJoin(String address, boolean wan);

	public Response<Void> agentForceLeave(String node);

	public Response<Void> agentCheckRegister(NewCheck newCheck);

	public Response<Void> agentCheckRegister(NewCheck newCheck, String token);

	public Response<Void> agentCheckDeregister(String checkId);

	public Response<Void> agentCheckDeregister(String checkId, String token);

	public Response<Void> agentCheckPass(String checkId);

	public Response<Void> agentCheckPass(String checkId, String note);

	public Response<Void> agentCheckPass(String checkId, String note, String token);

	public Response<Void> agentCheckWarn(String checkId);

	public Response<Void> agentCheckWarn(String checkId, String note);

	public Response<Void> agentCheckWarn(String checkId, String note, String token);

	public Response<Void> agentCheckFail(String checkId);

	public Response<Void> agentCheckFail(String checkId, String note);

	public Response<Void> agentCheckFail(String checkId, String note, String token);

	public Response<Void> agentServiceRegister(NewService newService);

	public Response<Void> agentServiceRegister(NewService newService, String token);

	public Response<Void> agentServiceDeregister(String serviceId);

	public Response<Void> agentServiceDeregister(String serviceId, String token);

	public Response<Void> agentServiceSetMaintenance(String serviceId, boolean maintenanceEnabled);

	public Response<Void> agentServiceSetMaintenance(String serviceId, boolean maintenanceEnabled, String reason);

	public Response<Void> agentReload();
}
