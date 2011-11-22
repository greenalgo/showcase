package com.green.ecom.service.logging

/**
 * Created by IntelliJ IDEA.
 * User: gaurav
 * Date: 21/08/11
 * Time: 1:29 PM
 * To change this template use File | Settings | File Templates.
 */

import org.slf4j.{LoggerFactory}

trait Logging {

  /**
   * The Logger instance. Created when we need it.
   */
  private lazy val logger = LoggerFactory.getLogger(getClass)

  /**
   * Log a debug level message.
   *
   * @param msg Message to log, only evaluated if debug logging is
   * enabled.
   * @param t Throwable, optional.
   */
  protected def debug(msg: => AnyRef, t: => Throwable = null): Unit = {
    if (logger.isDebugEnabled) {
      if (t != null) {
        logger.debug(msg.toString, t);
      } else {
        logger.debug(msg.toString)
      }
    }
  }

  /**
   * Log a info level message.
   *
   * @param msg Message to log, only evaluated if debug logging is
   * enabled.
   * @param t Throwable, optional.
   */
  protected def info(msg: => AnyRef, argArray: => Array[AnyRef] = null): Unit = {
    if (logger.isInfoEnabled) {
      if (argArray != null) {
        logger.info(msg.toString, argArray);
      } else {
        logger.info(msg.toString)
      }
    }
  }

  /**
   * Log a error level message.
   *
   * @param msg Message to log, only evaluated if debug logging is
   * enabled.
   * @param t Throwable, optional.
   */
  protected def error(msg: => AnyRef, t: => Throwable = null): Unit = {
    if (logger.isErrorEnabled) {
      if (t != null) {
        logger.error(msg.toString, t);
      } else {
        logger.error(msg.toString)
      }
    }
  }
}