package com.ecwid.consul.v1.catalog;

import com.ecwid.consul.v1.QueryParams;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.catalog.model.CatalogDeregistration;
import com.ecwid.consul.v1.catalog.model.CatalogNode;
import com.ecwid.consul.v1.catalog.model.CatalogRegistration;
import com.ecwid.consul.v1.catalog.model.Node;

import java.util.List;
import java.util.Map;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public interface CatalogClient {

	Response<Void> catalogRegister(CatalogRegistration catalogRegistration);

	Response<Void> catalogRegister(CatalogRegistration catalogRegistration, String token);

	// -------------------------------------------------------------------------------

	Response<Void> catalogDeregister(CatalogDeregistration catalogDeregistration);

	Response<Void> catalogDeregister(CatalogDeregistration catalogDeregistration, String token);

	// -------------------------------------------------------------------------------

	Response<List<String>> getCatalogDatacenters();

	// -------------------------------------------------------------------------------

	/**
	 * @deprecated This method will be removed in consul-api 2.0. Use {@link #getCatalogNodes(CatalogNodesRequest catalogNodesRequest)}
	 */
	@Deprecated
	Response<List<Node>> getCatalogNodes(QueryParams queryParams);

	Response<List<Node>> getCatalogNodes(CatalogNodesRequest catalogNodesRequest);

	// -------------------------------------------------------------------------------

	/**
	 * @deprecated This method will be removed in consul-api 2.0. Use {@link #getCatalogServices(CatalogServicesRequest catalogServicesRequest)}
	 */
	@Deprecated
	Response<Map<String, List<String>>> getCatalogServices(QueryParams queryParams);

	/**
	 * @deprecated This method will be removed in consul-api 2.0. Use {@link #getCatalogServices(CatalogServicesRequest catalogServicesRequest)}
	 */
	@Deprecated
	Response<Map<String, List<String>>> getCatalogServices(QueryParams queryParams, String token);

	Response<Map<String, List<String>>> getCatalogServices(CatalogServicesRequest catalogServicesRequest);

	// -------------------------------------------------------------------------------

	/**
	 * @deprecated This method will be removed in consul-api 2.0. Use {@link #getCatalogService(String serviceName, CatalogServiceRequest catalogServiceRequest)}
	 */
	@Deprecated
	Response<List<com.ecwid.consul.v1.catalog.model.CatalogService>> getCatalogService(String serviceName, QueryParams queryParams);

	/**
	 * @deprecated This method will be removed in consul-api 2.0. Use {@link #getCatalogService(String serviceName, CatalogServiceRequest catalogServiceRequest)}
	 */
	@Deprecated
	Response<List<com.ecwid.consul.v1.catalog.model.CatalogService>> getCatalogService(String serviceName, String tag, QueryParams queryParams);

	/**
	 * @deprecated This method will be removed in consul-api 2.0. Use {@link #getCatalogService(String serviceName, CatalogServiceRequest catalogServiceRequest)}
	 */
	@Deprecated
	Response<List<com.ecwid.consul.v1.catalog.model.CatalogService>> getCatalogService(String serviceName, QueryParams queryParams, String token);

	/**
	 * @deprecated This method will be removed in consul-api 2.0. Use {@link #getCatalogService(String serviceName, CatalogServiceRequest catalogServiceRequest)}
	 */
	@Deprecated
	Response<List<com.ecwid.consul.v1.catalog.model.CatalogService>> getCatalogService(String serviceName, String tag, QueryParams queryParams, String token);

	/**
	 * @deprecated This method will be removed in consul-api 2.0. Use {@link #getCatalogService(String serviceName, CatalogServiceRequest catalogServiceRequest)}
	 */
	@Deprecated
	Response<List<com.ecwid.consul.v1.catalog.model.CatalogService>> getCatalogService(String serviceName, String[] tags, QueryParams queryParams, String token);

	Response<List<com.ecwid.consul.v1.catalog.model.CatalogService>> getCatalogService(String serviceName, CatalogServiceRequest catalogServiceRequest);

	// -------------------------------------------------------------------------------

	Response<CatalogNode> getCatalogNode(String nodeName, QueryParams queryParams);
}
