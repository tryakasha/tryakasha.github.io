package com.suhwan.practice.other;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Logging {

  public static void main(String[] args) {
    Logging log = new Logging();
    log.logTest();
  }
  
  private void logTest(){
    Logger logger = LoggerFactory.getLogger("FileLog");
    logger.trace("traceLog");
    logger.debug("debugLog");
    logger.info("infoLog");
    logger.warn("warnLog");
    logger.error("errorLog");
  }

}
