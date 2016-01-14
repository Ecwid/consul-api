package com.ecwid.consul.v1.catalog;

import java.util.List;
import java.util.Map;

import com.ecwid.consul.UrlParameters;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.catalog.model.CatalogDeregistration;
import com.ecwid.consul.v1.catalog.model.CatalogNode;
import com.ecwid.consul.v1.catalog.model.CatalogRegistration;
import com.ecwid.consul.v1.catalog.model.Node;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public interface CatalogClient {

	public Response<Void> catalogRegister(CatalogRegistration catalogRegistration);

	public Response<Void> catalogDeregister(CatalogDeregistration catalogDeregistration);

	public Response<List<String>> getCatalogDatacenters();

	public Response<List<Node>> getCatalogNodes(UrlParameters UrlParameters);

	public Response<Map<String, List<String>>> getCatalogServices(UrlParameters UrlParameters);

	public Response<List<com.ecwid.consul.v1.catalog.model.CatalogService>> getCatalogService(String serviceName, UrlParameters UrlParameters);

	public Response<List<com.ecwid.consul.v1.catalog.model.CatalogService>> getCatalogService(String serviceName, String tag, UrlParameters UrlParameters);

	public Response<CatalogNode> getCatalogNode(String nodeName, UrlParameters UrlParameters);
}
