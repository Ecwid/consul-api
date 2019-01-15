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

	public Response<Void> catalogRegister(CatalogRegistration catalogRegistration);

	public Response<Void> catalogRegister(CatalogRegistration catalogRegistration, String token);

	// -------------------------------------------------------------------------------

	public Response<Void> catalogDeregister(CatalogDeregistration catalogDeregistration);

	public Response<Void> catalogDeregister(CatalogDeregistration catalogDeregistration, String token);

	// -------------------------------------------------------------------------------

	public Response<List<String>> getCatalogDatacenters();

	// -------------------------------------------------------------------------------

	/**
	 * @deprecated This method will be removed in consul-api 2.0. Use {@link #getCatalogNodes(CatalogNodesRequest catalogNodesRequest)}
	 */
	@Deprecated
	public Response<List<Node>> getCatalogNodes(QueryParams queryParams);

	public Response<List<Node>> getCatalogNodes(CatalogNodesRequest catalogNodesRequest);

	// -------------------------------------------------------------------------------

	/**
	 * @deprecated This method will be removed in consul-api 2.0. Use {@link #getCatalogServices(CatalogServicesRequest catalogServicesRequest)}
	 */
	@Deprecated
	public Response<Map<String, List<String>>> getCatalogServices(QueryParams queryParams);

	/**
	 * @deprecated This method will be removed in consul-api 2.0. Use {@link #getCatalogServices(CatalogServicesRequest catalogServicesRequest)}
	 */
	@Deprecated
	public Response<Map<String, List<String>>> getCatalogServices(QueryParams queryParams, String token);

	public Response<Map<String, List<String>>> getCatalogServices(CatalogServicesRequest catalogServicesRequest);

	// -------------------------------------------------------------------------------

	/**
	 * @deprecated This method will be removed in consul-api 2.0. Use {@link #getCatalogService(String serviceName, CatalogServiceRequest catalogServiceRequest)}
	 */
	@Deprecated
	public Response<List<com.ecwid.consul.v1.catalog.model.CatalogService>> getCatalogService(String serviceName, QueryParams queryParams);

	/**
	 * @deprecated This method will be removed in consul-api 2.0. Use {@link #getCatalogService(String serviceName, CatalogServiceRequest catalogServiceRequest)}
	 */
	@Deprecated
	public Response<List<com.ecwid.consul.v1.catalog.model.CatalogService>> getCatalogService(String serviceName, String tag, QueryParams queryParams);

	/**
	 * @deprecated This method will be removed in consul-api 2.0. Use {@link #getCatalogService(String serviceName, CatalogServiceRequest catalogServiceRequest)}
	 */
	@Deprecated
	public Response<List<com.ecwid.consul.v1.catalog.model.CatalogService>> getCatalogService(String serviceName, QueryParams queryParams, String token);

	/**
	 * @deprecated This method will be removed in consul-api 2.0. Use {@link #getCatalogService(String serviceName, CatalogServiceRequest catalogServiceRequest)}
	 */
	@Deprecated
	public Response<List<com.ecwid.consul.v1.catalog.model.CatalogService>> getCatalogService(String serviceName, String tag, QueryParams queryParams, String token);

	/**
	 * @deprecated This method will be removed in consul-api 2.0. Use {@link #getCatalogService(String serviceName, CatalogServiceRequest catalogServiceRequest)}
	 */
	@Deprecated
	public Response<List<com.ecwid.consul.v1.catalog.model.CatalogService>> getCatalogService(String serviceName, String[] tags, QueryParams queryParams, String token);

	public Response<List<com.ecwid.consul.v1.catalog.model.CatalogService>> getCatalogService(String serviceName, CatalogServiceRequest catalogServiceRequest);

	// -------------------------------------------------------------------------------

	public Response<CatalogNode> getCatalogNode(String nodeName, QueryParams queryParams);
}
