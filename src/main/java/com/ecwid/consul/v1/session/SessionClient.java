package com.ecwid.consul.v1.session;

import com.ecwid.consul.UrlParameters;
import com.ecwid.consul.v1.QueryParams;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.session.model.NewSession;
import com.ecwid.consul.v1.session.model.Session;

import java.util.List;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public interface SessionClient {

	public Response<String> sessionCreate(NewSession newSession, UrlParameters queryParams);

	public Response<Void> sessionDestroy(String session, UrlParameters queryParams);

	Response<Session> getSessionInfo(String session, UrlParameters queryParams);

	Response<List<Session>> getSessionNode(String node, UrlParameters queryParams);

	Response<List<Session>> getSessionList(UrlParameters queryParams);

    Response<Session> renewSession(String session, UrlParameters queryParams);
}
