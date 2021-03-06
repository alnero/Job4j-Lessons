package alnero;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4jViaSlf4j {
    /** Logger instance. **/
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4jViaSlf4j.class.getName());

    public static void main(String[] args) {
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");
    }
}
