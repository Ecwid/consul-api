package com.ecwid.consul.v1.transactions.model;

/**
 * Specifies the type of operation to perform.
 *
 * @author Trisia Cliven (quanguanyu@qq.com)
 */
public enum Verb {

    /**
     * Sets the Key to the given Value
     */
    set("set"),
    /**
     * 	Sets, but with CAS semantics
     */
    cas("cas"),
    /**
     * 	Lock with the given Session
     */
    lock("lock"),
    /**
     * 	Unlock with the given Session
     */
    unlock("unlock"),
    /**
     * Get the key, fails if it does not exist
     */
    get("get"),
    /**
     * Gets all keys with the prefix
     */
    get_tree("get-tree"),
    /**
	 * 	Fail if modify index != index
     */
    check_index("check-index"),
    /**
	 * Fail if not locked by session
     */
    check_session("check-session"),
    /**
	 * Fail if key exists
     */
    check_not_exists("check-not-exists"),
    /**
	 * Delete the key
     */
    delete("delete"),
    /**
	 * 	Delete all keys with a prefix
     */
    delete_tree("delete-tree"),
    /**
	 * Delete, but with CAS semantics
     */
    delete_cas("delete-cas");

    /**
     * Verb
     */
    private String verb;

    Verb(String verb) {
        this.verb = verb;
    }

    @Override
    public String toString() {
        return verb;
    }
}
