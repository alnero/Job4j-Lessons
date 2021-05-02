package alnero;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class UsageLog4jParamsLogging {
    /** Logger instance. **/
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4jParamsLogging.class.getName());

    public static void main(String[] args) {
        byte byteVar = -128;
        short shortVar = -32_768;
        int intVar =  -2_147_483_648;
        long longVar =  -9_223_372_036_854_775_808L;
        float floatVar = 1.4012985e-45f;
        double doubleVar = 5e-324;
        boolean booleanVar = false;
        char charVar = '\u0001';
        LOG.debug("Primitive types min values byte: {}, short: {}, int: {}, long: {}, float: {}, double: {}, boolean: {}, char: {}",
                byteVar, shortVar, intVar, longVar, floatVar, doubleVar, booleanVar, charVar);
    }
}
