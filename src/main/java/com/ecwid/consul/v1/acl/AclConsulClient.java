package com.ecwid.consul.v1.acl;

import java.util.List;
import java.util.Map;

import com.ecwid.consul.ConsulException;
import com.ecwid.consul.SingleUrlParameters;
import com.ecwid.consul.UrlParameters;
import com.ecwid.consul.json.GsonFactory;
import com.ecwid.consul.transport.RawResponse;
import com.ecwid.consul.transport.TLSConfig;
import com.ecwid.consul.v1.ConsulRawClient;
import com.ecwid.consul.v1.OperationException;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.acl.model.Acl;
import com.ecwid.consul.v1.acl.model.NewAcl;
import com.ecwid.consul.v1.acl.model.UpdateAcl;
import com.google.gson.reflect.TypeToken;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public final class AclConsulClient implements AclClient {

    private final ConsulRawClient rawClient;

    public AclConsulClient(ConsulRawClient rawClient) {
	this.rawClient = rawClient;
    }

    public AclConsulClient() {
	this(new ConsulRawClient());
    }

    public AclConsulClient(TLSConfig tlsConfig) {
	this(new ConsulRawClient(tlsConfig));
    }

    public AclConsulClient(String agentHost) {
	this(new ConsulRawClient(agentHost));
    }

    public AclConsulClient(String agentHost, TLSConfig tlsConfig) {
	this(new ConsulRawClient(agentHost, tlsConfig));
    }

    public AclConsulClient(String agentHost, int agentPort) {
	this(new ConsulRawClient(agentHost, agentPort));
    }

    public AclConsulClient(String agentHost, int agentPort, TLSConfig tlsConfig) {
	this(new ConsulRawClient(agentHost, agentPort, tlsConfig));
    }

    @Override
    public Response<String> aclCreate(NewAcl newAcl, String token) {
	UrlParameters tokenParams = token != null ? new SingleUrlParameters("token", token) : null;
	String json = GsonFactory.getGson().toJson(newAcl);
	RawResponse rawResponse = rawClient.makePutRequest("/v1/acl/create", json, tokenParams);

	if (rawResponse.getStatusCode() == 200) {
	    Map<String, String> value = GsonFactory.getGson().fromJson(rawResponse.getContent(), new TypeToken<Map<String, String>>() {
	    }.getType());
	    return new Response<String>(value.get("ID"), rawResponse);
	} else {
	    throw new OperationException(rawResponse);
	}
    }

    @Override
    public Response<Void> aclUpdate(UpdateAcl updateAcl, String token) {
	UrlParameters tokenParams = token != null ? new SingleUrlParameters("token", token) : null;
	String json = GsonFactory.getGson().toJson(updateAcl);
	RawResponse rawResponse = rawClient.makePutRequest("/v1/acl/update", json, tokenParams);

	if (rawResponse.getStatusCode() == 200) {
	    return new Response<Void>(null, rawResponse);
	} else {
	    throw new OperationException(rawResponse);
	}
    }

    @Override
    public Response<Void> aclDestroy(String aclId, String token) {
	UrlParameters tokenParams = token != null ? new SingleUrlParameters("token", token) : null;
	RawResponse rawResponse = rawClient.makePutRequest("/v1/acl/destroy/" + aclId, "", tokenParams);

	if (rawResponse.getStatusCode() == 200) {
	    return new Response<Void>(null, rawResponse);
	} else {
	    throw new OperationException(rawResponse);
	}
    }

    @Override
    public Response<Acl> getAcl(String id) {
	RawResponse rawResponse = rawClient.makeGetRequest("/v1/acl/info/" + id);

	if (rawResponse.getStatusCode() == 200) {
	    List<Acl> value = GsonFactory.getGson().fromJson(rawResponse.getContent(), new TypeToken<List<Acl>>() {
	    }.getType());

	    if (value.isEmpty()) {
		return new Response<Acl>(null, rawResponse);
	    } else if (value.size() == 1) {
		return new Response<Acl>(value.get(0), rawResponse);
	    } else {
		throw new ConsulException("Strange response (list size=" + value.size() + ")");
	    }
	} else {
	    throw new OperationException(rawResponse);
	}
    }

    @Override
    public Response<String> aclClone(String aclId, String token) {
	UrlParameters tokenParams = token != null ? new SingleUrlParameters("token", token) : null;
	RawResponse rawResponse = rawClient.makePutRequest("/v1/acl/clone/" + aclId, "", tokenParams);

	if (rawResponse.getStatusCode() == 200) {
	    Map<String, String> value = GsonFactory.getGson().fromJson(rawResponse.getContent(), new TypeToken<Map<String, String>>() {
	    }.getType());
	    return new Response<String>(value.get("ID"), rawResponse);
	} else {
	    throw new OperationException(rawResponse);
	}
    }

    @Override
    public Response<List<Acl>> getAclList(String token) {
	UrlParameters tokenParams = token != null ? new SingleUrlParameters("token", token) : null;
	RawResponse rawResponse = rawClient.makeGetRequest("/v1/acl/list", tokenParams);

	if (rawResponse.getStatusCode() == 200) {
	    List<Acl> value = GsonFactory.getGson().fromJson(rawResponse.getContent(), new TypeToken<List<Acl>>() {
	    }.getType());
	    return new Response<List<Acl>>(value, rawResponse);
	} else {
	    throw new OperationException(rawResponse);
	}
    }

}
