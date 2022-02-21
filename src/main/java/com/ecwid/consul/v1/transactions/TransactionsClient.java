package com.ecwid.consul.v1.transactions;


import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.transactions.model.TxnResult;

/**
 * Transactions Client
 *
 * @author Trisia Cliven (quanguanyu@qq.com)
 * @since 2019-11-11 10:24:49
 */
public interface TransactionsClient {
    /**
     * Submit transaction
     *
     * @param token token
     * @param builder parameters builder
     * @return process result report
     */
    public Response<TxnResult> commit(String token, ParamBuilder builder);
    /**
     * Submit transaction
     *
     * @param builder parameters builder
     * @return process result report
     */
    public Response<TxnResult> commit(ParamBuilder builder);
}
