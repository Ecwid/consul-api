package com.ecwid.consul.v1.transactions.model;

import com.ecwid.consul.v1.kv.model.GetValue;

/**
 * @author Trisia Cliven (quanguanyu@qq.com)
 */
public class TxnRespItem {

    private GetValue KV;

    public GetValue getKV() {
        return KV;
    }

    public void setKV(GetValue KV) {
        this.KV = KV;
    }

    @Override
    public String toString() {
        return "TxnRespItem{" +
                "KV=" + KV +
                '}';
    }
}
