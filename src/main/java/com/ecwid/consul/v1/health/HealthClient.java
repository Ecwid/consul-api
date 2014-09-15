package com.ecwid.consul.v1.health;

import com.ecwid.consul.v1.QueryParams;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.health.model.Check;

import java.util.List;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public interface HealthClient {
	Response<List<Check>> getHealthChecksForNode(String nodeName, QueryParams queryParams);

	Response<List<Check>> getHealthChecksForService(String serviceName, QueryParams queryParams);

	Response<List<com.ecwid.consul.v1.health.model.HealthService>> getHealthServices(String serviceName, boolean onlyPassing, QueryParams queryParams);

	Response<List<com.ecwid.consul.v1.health.model.HealthService>> getHealthServices(String serviceName, String tag, boolean onlyPassing, QueryParams queryParams);

	Response<List<Check>> getHealthChecksState(QueryParams queryParams);

	Response<List<Check>> getHealthChecksState(Check.CheckStatus checkStatus, QueryParams queryParams);
}
