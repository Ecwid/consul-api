package com.ecwid.consul.v1.transactions.model;

/**
 * Operate error info
 *
 * @author Trisia Cliven (quanguanyu@qq.com)
 */
public class OpError {

    /**
     * OpIndex gives the index of the failed operation in the transaction,
     */
    private Integer OpIndex;

    /**
     * What is a string with an error message about why that operation failed.
     */
    private String What;

    public OpError() {
    }

    public Integer getOpIndex() {
        return OpIndex;
    }

    public void setOpIndex(Integer opIndex) {
        OpIndex = opIndex;
    }

    public String getWhat() {
        return What;
    }

    public void setWhat(String what) {
        What = what;
    }

    @Override
    public String toString() {
        return "OpError{" +
                "OpIndex=" + OpIndex +
                ", What='" + What + '\'' +
                '}';
    }
}
