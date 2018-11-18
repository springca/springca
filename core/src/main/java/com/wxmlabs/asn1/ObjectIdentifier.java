/*
  Copyright (c) 2000-2018 The Legion of the Bouncy Castle Inc. (http://www.bouncycastle.org)
  Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
  <p>
  The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
  <p>
  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.wxmlabs.asn1;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Copyright (c) 2018 The Legion of the Bouncy Castle Inc. (http://www.bouncycastle.org)
 * GB/T 26231-2017 《信息技术 开放系统互连 对象标识符（OID）的国家编号体系和操作规程》
 * <i>Information technology—Open systems interconnection—National numbering system and operation code for object identifier(OID)</i>
 * <p>
 * 王翾旻 2018年10月14日
 */
public class ObjectIdentifier implements Comparable<ObjectIdentifier> {
    private final String identifier;

    /**
     * Create an OID based on the passed in String.
     *
     * @param identifier a string representation of an OID.
     */
    public ObjectIdentifier(String identifier) {
        if (identifier == null) {
            throw new IllegalArgumentException("'identifier' cannot be null");
        }
        if (!isValidIdentifier(identifier)) {
            throw new IllegalArgumentException("string " + identifier + " not an ObjectIdentifier");
        }
        this.identifier = identifier.intern();
    }

    private ObjectIdentifier(ObjectIdentifier oid, String branchId) {
        if (!isValidBranchID(branchId, 0)) {
            throw new IllegalArgumentException("string " + branchId + " not a valid OID branch");
        }

        this.identifier = (oid + "." + branchId).intern();
    }

    /**
     * Return an OID that creates a branch under the current one.
     *
     * @param branchId node numbers for the new branch.
     * @return the OID for the new created branch.
     */
    public ObjectIdentifier branch(String branchId) {
        return new ObjectIdentifier(this, branchId);
    }

    public String oid() {
        return identifier;
    }

    @Override
    public String toString() {
        return oid();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return identifier.equals(o);
    }

    @Override
    public int hashCode() {
        return identifier.hashCode();
    }

    @Override
    public int compareTo(ObjectIdentifier o) {
        return identifier.compareTo(o.identifier);
    }

    private static boolean isValidBranchID(String branchID, int start) {
        boolean periodAllowed = false;

        int pos = branchID.length();
        while (--pos >= start) {
            char ch = branchID.charAt(pos);

            // TODO Leading zeroes?
            if ('0' <= ch && ch <= '9') {
                periodAllowed = true;
                continue;
            }

            if (ch == '.') {
                if (!periodAllowed) {
                    return false;
                }

                periodAllowed = false;
                continue;
            }

            return false;
        }

        return periodAllowed;
    }

    private static boolean isValidIdentifier(String identifier) {
        if (identifier.length() < 3 || identifier.charAt(1) != '.') {
            return false;
        }

        char first = identifier.charAt(0);
        if (first < '0' || first > '2') {
            return false;
        }

        return isValidBranchID(identifier, 2);
    }

    /**
     * Intern will return a reference to a pooled version of this object, unless it
     * is not present in which case intern will add it.
     * <p>
     * The pool is also used by the ASN.1 parsers to limit the number of duplicated OID
     * objects in circulation.
     * </p>
     *
     * @return a reference to the identifier in the pool.
     */
    public ObjectIdentifier intern() {
        final String hdl = identifier.intern();
        ObjectIdentifier oid = pool.get(hdl);
        if (oid == null) {
            oid = pool.putIfAbsent(hdl, this);
            if (oid == null) {
                oid = this;
            }
        }
        return oid;
    }

    private static final ConcurrentMap<String, ObjectIdentifier> pool = new ConcurrentHashMap<>();
}
