package com.ecwid.consul.v1.transactions;

import com.ecwid.consul.SingleUrlParameters;
import com.ecwid.consul.UrlParameters;
import com.ecwid.consul.json.GsonFactory;
import com.ecwid.consul.transport.HttpResponse;
import com.ecwid.consul.v1.ConsulRawClient;
import com.ecwid.consul.v1.OperationException;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.transactions.model.TxnResult;

/**
 * Transactions Client for consul
 *
 * @author Trisia Cliven (quanguanyu@qq.com)
 */
public class TransactionsConsulClient implements TransactionsClient {
    private final ConsulRawClient rawClient;

    public TransactionsConsulClient(ConsulRawClient rawClient) {
        this.rawClient = rawClient;
    }

    public TransactionsConsulClient() {
        this(new ConsulRawClient());
    }

    /**
     * Submit transaction
     *
     * @param token   token
     * @param builder parameters builder
     * @return process result report
     */
    @Override
    public Response<TxnResult> commit(String token, ParamBuilder builder) {
        UrlParameters tokenParams = token != null ? new SingleUrlParameters("token", token) : null;
        String json = GsonFactory.getGson().toJson(builder.build());
		HttpResponse httpResponse = rawClient.makePutRequest("/v1/txn", json, tokenParams);

        if (httpResponse.getStatusCode() == 200) {
            TxnResult value = GsonFactory.getGson().fromJson(httpResponse.getContent(), TxnResult.class);
            return new Response<>(value, httpResponse);
        } else {
            throw new OperationException(httpResponse);
        }
    }

    /**
     * Submit transaction
     *
     * @param builder parameters builder
     * @return process result report
     */
    @Override
    public Response<TxnResult> commit(ParamBuilder builder) {
        return this.commit(null, builder);
    }
}
