package com.ecwid.consul.v1.transactions.model;

/**
 * Operate item
 *
 * @author Trisia Cliven (quanguanyu@qq.com)
 */
public class Operate {

    /**
     * Specifies the type of operation to perform.
     */
    private Verb Verb;

    /**
     * Specifies the full path of the entry.
     */
    private String Key;

    /**
     * Specifies a base64-encoded blob of data. Values cannot be larger than 512kB.
     */
    private String Value;

    /**
     * Specifies an opaque unsigned integer that can be attached to each entry.
     * Clients can choose to use this however makes sense for their application.
     */
    private Integer Flags;

    /**
     * Specifies an index. See the table below for more information.
     */
    private Integer Index;

    /**
	 * Specifies a session. See the table below for more information.
     */
    private String Session;

    public Operate(Verb verb) {
        Verb = verb;
    }

    public Verb getVerb() {
        return Verb;
    }

    public Operate setVerb(Verb verb) {
        Verb = verb;
        return this;
    }

    public String getKey() {
        return Key;
    }

    public Operate setKey(String key) {
        Key = key;
        return this;
    }

    public String getValue() {
        return Value;
    }

    public Operate setValue(String value) {
        Value = value;
        return this;
    }

    public Integer getFlags() {
        return Flags;
    }

    public Operate setFlags(Integer flags) {
        Flags = flags;
        return this;
    }

    public Integer getIndex() {
        return Index;
    }

    public Operate setIndex(Integer index) {
        Index = index;
        return this;
    }

    public String getSession() {
        return Session;
    }

    public Operate setSession(String session) {
        Session = session;
        return this;
    }
}
