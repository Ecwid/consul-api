package com.ecwid.consul.v1.status;

import com.ecwid.consul.v1.Response;

import java.util.List;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public interface StatusClient {

	public Response<String> getStatusLeader();

	public Response<List<String>> getStatusPeers();
}
