package com.ecwid.consul.v1.health;

import com.ecwid.consul.v1.QueryParams;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.catalog.CatalogNodesRequest;
import com.ecwid.consul.v1.health.model.Check;

import java.util.List;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public interface HealthClient {

	public Response<List<Check>> getHealthChecksForNode(String nodeName, QueryParams queryParams);

	// -------------------------------------------------------------------------------

	/**
	 * @deprecated This method will be removed in consul-api 2.0. Use {@link #getHealthChecksForService(String serviceName, HealthChecksForServiceRequest healthChecksForServiceRequest)}
	 */
	@Deprecated
	public Response<List<Check>> getHealthChecksForService(String serviceName, QueryParams queryParams);

	public Response<List<Check>> getHealthChecksForService(String serviceName, HealthChecksForServiceRequest healthChecksForServiceRequest);

	// -------------------------------------------------------------------------------

	/**
	 * @deprecated This method will be removed in consul-api 2.0. Use {@link #getHealthServices(String serviceName, HealthServicesRequest healthServicesRequest)}
	 */
	@Deprecated
	public Response<List<com.ecwid.consul.v1.health.model.HealthService>> getHealthServices(String serviceName, boolean onlyPassing, QueryParams queryParams);

	/**
	 * @deprecated This method will be removed in consul-api 2.0. Use {@link #getHealthServices(String serviceName, HealthServicesRequest healthServicesRequest)}
	 */
	@Deprecated
	public Response<List<com.ecwid.consul.v1.health.model.HealthService>> getHealthServices(String serviceName, String tag, boolean onlyPassing, QueryParams queryParams);

	/**
	 * @deprecated This method will be removed in consul-api 2.0. Use {@link #getHealthServices(String serviceName, HealthServicesRequest healthServicesRequest)}
	 */
	@Deprecated
	public Response<List<com.ecwid.consul.v1.health.model.HealthService>> getHealthServices(String serviceName, boolean onlyPassing, QueryParams queryParams, String token);

	/**
	 * @deprecated This method will be removed in consul-api 2.0. Use {@link #getHealthServices(String serviceName, HealthServicesRequest healthServicesRequest)}
	 */
	@Deprecated
	public Response<List<com.ecwid.consul.v1.health.model.HealthService>> getHealthServices(String serviceName, String tag, boolean onlyPassing, QueryParams queryParams, String token);

	/**
	 * @deprecated This method will be removed in consul-api 2.0. Use {@link #getHealthServices(String serviceName, HealthServicesRequest healthServicesRequest)}
	 */
	@Deprecated
	public Response<List<com.ecwid.consul.v1.health.model.HealthService>> getHealthServices(String serviceName, String[] tags, boolean onlyPassing, QueryParams queryParams, String token);

	public Response<List<com.ecwid.consul.v1.health.model.HealthService>> getHealthServices(String serviceName, HealthServicesRequest healthServicesRequest);

	// -------------------------------------------------------------------------------

	public Response<List<Check>> getHealthChecksState(QueryParams queryParams);

	public Response<List<Check>> getHealthChecksState(Check.CheckStatus checkStatus, QueryParams queryParams);
}
