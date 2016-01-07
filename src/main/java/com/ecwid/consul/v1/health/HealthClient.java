package com.ecwid.consul.v1.health;

import java.util.List;

import com.ecwid.consul.UrlParameters;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.health.model.Check;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public interface HealthClient {

	public Response<List<Check>> getHealthChecksForNode(String nodeName, UrlParameters queryParams);

	public Response<List<Check>> getHealthChecksForService(String serviceName, UrlParameters queryParams);

	public Response<List<com.ecwid.consul.v1.health.model.HealthService>> getHealthServices(String serviceName, boolean onlyPassing, UrlParameters queryParams);

	public Response<List<com.ecwid.consul.v1.health.model.HealthService>> getHealthServices(String serviceName, String tag, boolean onlyPassing, UrlParameters queryParams);

	public Response<List<Check>> getHealthChecksState(UrlParameters queryParams);

	public Response<List<Check>> getHealthChecksState(Check.CheckStatus checkStatus, UrlParameters queryParams);
}
