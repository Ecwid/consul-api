package com.ecwid.consul.v1.transactions.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Trisia Cliven (quanguanyu@qq.com)
 * @since 2019-11-11 10:31:48
 */
public class TxnResult {
    /**
	 * Results has entries for some operations
     * if the transaction was successful.
     * To save space,
     * the Value for KV results will be null for any Verb other than "get" or "get-tree".
     * Like the /v1/kv/<key> endpoint, Value will be Base64-encoded if it is present.
     * Also, no result entries will be added for verbs that delete keys.
     */
    @SerializedName("Results")
    private List<TxnRespItem> results;

	/**
	 * Errors has entries describing which operations failed if the transaction was rolled back.
     * The OpIndex gives the index of the failed operation in the transaction,
     * and What is a string with an error message about why that operation failed.
     */
    @SerializedName("Errors")
    private List<OpError> errors;


    public List<TxnRespItem> getResults() {
        return results;
    }

    public void setResults(List<TxnRespItem> results) {
        this.results = results;
    }

    public List<OpError> getErrors() {
        return errors;
    }

    public void setErrors(List<OpError> errors) {
        this.errors = errors;
    }

    @Override
    public String toString() {
        return "TxnResult{" +
                "results=" + results +
                ", errors=" + errors +
                '}';
    }
}
