package com.ecwid.consul.v1.query;

import com.ecwid.consul.v1.QueryParams;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.query.model.QueryExecution;

public interface QueryClient {

	Response<QueryExecution> executePreparedQuery(String uuid, QueryParams queryParams);
}
