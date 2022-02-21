package com.ecwid.consul.v1.transactions.model;

/**
 * @author Trisia Cliven (quanguanyu@qq.com)
 */
public class TxnReqItem {

    private Operate KV;

    public TxnReqItem(Operate KV) {
        this.KV = KV;
    }

    public TxnReqItem() {
    }

    public Operate getKV() {
        return KV;
    }

    public void setKV(Operate KV) {
        this.KV = KV;
    }
}
