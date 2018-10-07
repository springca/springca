package com.wxmlabs.springca.util;

import java.lang.reflect.Method;
import java.math.BigInteger;
import java.security.spec.ECField;
import java.security.spec.ECFieldF2m;
import java.security.spec.ECFieldFp;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.EllipticCurve;
import java.util.Arrays;
import java.util.HashMap;

public class ECParameterSpecUtil {
    private final static ECParameterSpec sm2p256v1 = new ECParameterSpec(
        new EllipticCurve(
            new ECFieldFp(new BigInteger("115792089210356248756420345214020892766250353991924191454421193933289684991999")),
            new BigInteger("115792089210356248756420345214020892766250353991924191454421193933289684991996"),
            new BigInteger("18505919022281880113072981827955639221458448578012075254857346196103069175443")
        ),
        new ECPoint(
            new BigInteger("22963146547237050559479531362550074578802567295341616970375194840604139615431"),
            new BigInteger("85132369209828568825618990617112496413088388631904505083283536607588877201568")
        ),
        new BigInteger("115792089210356248756420345214020892766061623724957744567843809356293439045923"),
        1
    );

    public static boolean isSM2ECC(ECParameterSpec spec) {
        return sm2p256v1.getCurve().equals(spec.getCurve())
            && sm2p256v1.getGenerator().equals(spec.getGenerator())
            && sm2p256v1.getOrder().equals(spec.getOrder())
            && sm2p256v1.getCofactor() == spec.getCofactor();
    }

    private static HashMap<Class, Method> methodCache = new HashMap<>(2);

    static String getCurveName(ECParameterSpec spec) {
        try {
            Class clazz = spec.getClass();
            Method m, cm;
            cm = methodCache.get(clazz);
            if (cm != null) {
                m = cm;
            } else {
                //noinspection unchecked
                m = clazz.getDeclaredMethod("getName");
            }
            if (m != null) {
                Object name = m.invoke(spec);
                if (name instanceof String) {
                    if (cm == null) methodCache.put(clazz, m);
                    return (String) name;
                }
            }
        } catch (Exception ignore) {
        }
        return null;
    }

    static String toString(ECParameterSpec spec) {
        String params;
        String curveName = getCurveName(spec);
        if (curveName != null) {
            params = curveName;
        } else {
            params = String.format(
                "ECParameterSpec(curve: %s, generator: %s, order: %s, cofactor: %d)",
                toString(spec.getCurve()),
                toString(spec.getGenerator()),
                spec.getOrder().toString(),
                spec.getCofactor()
            );
        }
        return params;
    }

    static String toString(ECField field) {
        if (field instanceof ECFieldFp) {
            return String.format(
                "ECFieldFp(size: %d, p: %s)",
                field.getFieldSize(),
                ((ECFieldFp) field).getP().toString()
            );
        } else { // ECFieldF2m
            return String.format(
                "ECFieldF2m(size: %d, rp: %s)",
                field.getFieldSize(),
                ((ECFieldF2m) field).getReductionPolynomial().toString()
            );
        }
    }

    static String toString(ECPoint point) {
        return String.format(
            "ECPoint(x: %s, y: %s)",
            point.getAffineX().toString(),
            point.getAffineY().toString()
        );
    }

    static String toString(EllipticCurve curve) {
        return String.format(
            "EllipticCurve(field: %s, a: %s, b: %s, seed: %s",
            toString(curve.getField()),
            curve.getA().toString(),
            curve.getB().toString(),
            Arrays.toString(curve.getSeed())
        );
    }
}
