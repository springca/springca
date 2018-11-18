package com.wxmlabs.oid

import com.wxmlabs.asn1.ObjectIdentifier
import spock.lang.Specification

@SuppressWarnings("GroovyPointlessBoolean")
class ObjectIdentifierTest extends Specification {

    def testIntern() {
        expect:
        oid1.is(oid2) == checkTheSame
        (oid1 == oid2) == checkEquals

        oid1.intern().is(oidIntern) == true
        (oid1 == oidIntern) == true

        oid2.intern().is(oidIntern) == checkIntern
        (oid2 == oidIntern) == checkEquals

        where:
        oid1str             | oid2str               || checkTheSame || checkEquals || checkIntern
        "1.2.156.10197.301" | "1.2.156.10197.301"   || false        || true        || true
        "1.2.156.10197.301" | "1.2.156.10197.301.1" || false        || false       || false
        "1.2.156.10197.301" | "1.2.156.10197.302"   || false        || false       || false

        oid1 = new ObjectIdentifier(oid1str)
        oid2 = new ObjectIdentifier(oid2str)
        oidIntern = oid1.intern()
    }
}
