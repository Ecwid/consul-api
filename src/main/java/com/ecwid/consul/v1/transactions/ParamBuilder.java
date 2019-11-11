package com.ecwid.consul.v1.transactions;

import com.ecwid.consul.v1.transactions.model.Operate;
import com.ecwid.consul.v1.transactions.model.TxnReqItem;
import com.ecwid.consul.v1.transactions.model.Verb;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;

/**
 * Build consul Transaction parameters
 * <p>
 * https://www.consul.io/api/txn.html#tables-of-operations
 *
 * @author Trisia Cliven (quanguanyu@qq.com)
 * @since 2019-11-11 12:21:52
 */
public class ParamBuilder {

    private List<TxnReqItem> operates;

    private ParamBuilder() {
        operates = new LinkedList<>();
    }

    public static ParamBuilder getInstance() {
        return new ParamBuilder();
    }

    /**
     * Sets the Key to the given Value
     *
     * @param key   key
     * @param value value (do not base64)
     * @param flag  [option]
     * @return this
     */
    public ParamBuilder kvSet(String key, String value, int... flag) {
        Operate op = new Operate(Verb.set)
                .setKey(key)
                .setValue(Base64.getEncoder().encodeToString(value.getBytes(StandardCharsets.UTF_8)));
        if (flag != null && flag.length > 0) {
            op.setFlags(flag[0]);
        }
        operates.add(new TxnReqItem(op));
        return this;
    }

    /**
     * Sets, but with CAS semantics
     *
     * @param key   key
     * @param value value
     * @param index index
     * @param flag  [option]
     * @return this
     */
    public ParamBuilder kvCas(String key, String value, int index, int... flag) {
        Operate op = new Operate(Verb.cas)
                .setKey(key)
                .setValue(Base64.getEncoder().encodeToString(value.getBytes(StandardCharsets.UTF_8)))
                .setIndex(index);
        if (flag != null && flag.length > 0) {
            op.setFlags(flag[0]);
        }
        operates.add(new TxnReqItem(op));
        return this;
    }

    /**
     * Lock with the given Session
     *
     * @param key     key
     * @param value   value
     * @param session session id
     * @param flag    [option]
     * @return this
     */
    public ParamBuilder kvLock(String key, String value, String session, int... flag) {
        Operate op = new Operate(Verb.lock)
                .setKey(key)
                .setValue(Base64.getEncoder().encodeToString(value.getBytes(StandardCharsets.UTF_8)))
                .setSession(session);
        if (flag != null && flag.length > 0) {
            op.setFlags(flag[0]);
        }
        operates.add(new TxnReqItem(op));
        return this;
    }


    /**
     * Unlock with the given Session
     *
     * @param key     key
     * @param value   value
     * @param session session id
     * @param flag    [option]
     * @return this
     */
    public ParamBuilder kvUnlock(String key, String value, String session, int... flag) {
        Operate op = new Operate(Verb.unlock)
                .setKey(key)
                .setValue(Base64.getEncoder().encodeToString(value.getBytes(StandardCharsets.UTF_8)))
                .setSession(session);
        if (flag != null && flag.length > 0) {
            op.setFlags(flag[0]);
        }
        operates.add(new TxnReqItem(op));
        return this;
    }

    /**
     * Get the key, fails if it does not exist
     *
     * @param key key
     * @return this
     */
    public ParamBuilder kvGet(String key) {
        Operate op = new Operate(Verb.get)
                .setKey(key);
        operates.add(new TxnReqItem(op));
        return this;
    }

    /**
     * Gets all keys with the prefix
     *
     * @param key key
     * @return this
     */
    public ParamBuilder kvGetTree(String key) {
        Operate op = new Operate(Verb.get_tree)
                .setKey(key);
        operates.add(new TxnReqItem(op));
        return this;
    }

    /**
     * Fail if modify index != index
     *
     * @param key   key
     * @param index index
     * @return this
     */
    public ParamBuilder kvCheckIndex(String key, int index) {
        Operate op = new Operate(Verb.check_index)
                .setKey(key)
                .setIndex(index);
        operates.add(new TxnReqItem(op));
        return this;
    }

    /**
     * Fail if not locked by session
     *
     * @param key     key
     * @param session session id
     * @return this
     */
    public ParamBuilder kvCheckSession(String key, String session) {
        Operate op = new Operate(Verb.check_session)
                .setKey(key)
                .setSession(session);
        operates.add(new TxnReqItem(op));
        return this;
    }

    /**
     * Fail if key exists
     *
     * @param key key
     * @return this
     */
    public ParamBuilder kvCheckNotExists(String key) {
        Operate op = new Operate(Verb.check_not_exists)
                .setKey(key);
        operates.add(new TxnReqItem(op));
        return this;
    }

    /**
     * Delete the key
     *
     * @param key key
     * @return this
     */
    public ParamBuilder kvDelete(String key) {
        Operate op = new Operate(Verb.delete)
                .setKey(key);
        operates.add(new TxnReqItem(op));
        return this;
    }

    /**
     * Delete all keys with a prefix
     *
     * @param key key
     * @return this
     */
    public ParamBuilder kvDeleteTree(String key) {
        Operate op = new Operate(Verb.delete_tree)
                .setKey(key);
        operates.add(new TxnReqItem(op));
        return this;
    }

    /**
     * Delete, but with CAS semantics
     *
     * @param key key
     * @return this
     */
    public ParamBuilder kvDeleteCas(String key) {
        Operate op = new Operate(Verb.delete_cas)
                .setKey(key);
        operates.add(new TxnReqItem(op));
        return this;
    }

    /**
     * Build Request parameters
     *
     * @return parameters
     */
    public List<TxnReqItem> build() {
        return this.operates;
    }
}
