/*
 * Copyright (c) 2000-2018 The Legion of the Bouncy Castle Inc. (http://www.bouncycastle.org)
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.wxmlabs.asn1.x509;

import com.wxmlabs.asn1.ObjectIdentifier;

/**
 * The KeyPurposeId object.
 * <pre>
 *     KeyPurposeId ::= OBJECT IDENTIFIER
 *
 *     id-kp ::= OBJECT IDENTIFIER { iso(1) identified-organization(3)
 *          dod(6) internet(1) security(5) mechanisms(5) pkix(7) 3}
 *
 * </pre>
 * To create a new KeyPurposeId where none of the below suit, use
 * <pre>
 *     ASN1ObjectIdentifier newKeyPurposeIdOID = new ASN1ObjectIdentifier("1.3.6.1...");
 *
 *     KeyPurposeId newKeyPurposeId = KeyPurposeId.getInstance(newKeyPurposeIdOID);
 * </pre>
 */
public interface KeyPurposeId {
    ObjectIdentifier id_kp = new ObjectIdentifier("1.3.6.1.5.5.7.3");

    /**
     * { 2 5 29 37 0 }
     */
    ObjectIdentifier anyExtendedKeyUsage = new ObjectIdentifier("2.5.29.37.0").intern();

    /**
     * { id-kp 1 }
     */
    ObjectIdentifier serverAuth = id_kp.branch("1");
    /**
     * { id-kp 2 }
     */
    ObjectIdentifier clientAuth = id_kp.branch("2");
    /**
     * { id-kp 3 }
     */
    ObjectIdentifier codeSigning = id_kp.branch("3");
    /**
     * { id-kp 4 }
     */
    ObjectIdentifier emailProtection = id_kp.branch("4");
    /**
     * Usage deprecated by RFC4945 - was { id-kp 5 }
     */
    ObjectIdentifier ipsecEndSystem = id_kp.branch("5");
    /**
     * Usage deprecated by RFC4945 - was { id-kp 6 }
     */
    ObjectIdentifier ipsecTunnel = id_kp.branch("6");
    /**
     * Usage deprecated by RFC4945 - was { idkp 7 }
     */
    ObjectIdentifier ipsecUser = id_kp.branch("7");
    /**
     * { id-kp 8 }
     */
    ObjectIdentifier timeStamping = id_kp.branch("8");
    /**
     * { id-kp 9 }
     */
    ObjectIdentifier OCSPSigning = id_kp.branch("9");
    /**
     * { id-kp 10 }
     */
    ObjectIdentifier dvcs = id_kp.branch("10");
    /**
     * { id-kp 11 }
     */
    ObjectIdentifier sbgpCertAAServerAuth = id_kp.branch("11");
    /**
     * { id-kp 12 }
     */
    ObjectIdentifier scvp_responder = id_kp.branch("12");
    /**
     * { id-kp 13 }
     */
    ObjectIdentifier eapOverPPP = id_kp.branch("13");
    /**
     * { id-kp 14 }
     */
    ObjectIdentifier eapOverLAN = id_kp.branch("14");
    /**
     * { id-kp 15 }
     */
    ObjectIdentifier scvpServer = id_kp.branch("15");
    /**
     * { id-kp 16 }
     */
    ObjectIdentifier scvpClient = id_kp.branch("16");
    /**
     * { id-kp 17 }
     */
    ObjectIdentifier ipsecIKE = id_kp.branch("17");
    /**
     * { id-kp 18 }
     */
    ObjectIdentifier capwapAC = id_kp.branch("18");
    /**
     * { id-kp 19 }
     */
    ObjectIdentifier capwapWTP = id_kp.branch("19");

    //
    // microsoft key purpose ids
    //
    /**
     * { 1 3 6 1 4 1 311 20 2 2 }
     */
    ObjectIdentifier smartcardlogon = new ObjectIdentifier("1.3.6.1.4.1.311.20.2.2");


    /**
     *
     */
    ObjectIdentifier macAddress = new ObjectIdentifier("1.3.6.1.1.1.1.22");


    /**
     * Microsoft Server Gated Crypto (msSGC) see http://www.alvestrand.no/objectid/1.3.6.1.4.1.311.10.3.3.html
     */
    ObjectIdentifier msSGC = new ObjectIdentifier("1.3.6.1.4.1.311.10.3.3");

    /**
     * Netscape Server Gated Crypto (nsSGC) see http://www.alvestrand.no/objectid/2.16.840.1.113730.4.1.html
     */
    ObjectIdentifier nsSGC = new ObjectIdentifier("2.16.840.1.113730.4.1");
}
