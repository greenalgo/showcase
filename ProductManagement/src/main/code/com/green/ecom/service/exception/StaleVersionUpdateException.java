package com.green.ecom.service.exception;

/**
 * Created by IntelliJ IDEA.
 * User: gaurav
 * Date: 01/09/11
 * Time: 3:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class StaleVersionUpdateException extends DbException{

   public StaleVersionUpdateException(String info,Exception rootCause){
        this.info = info;
        this.code = "DB-SVN";
        this.message = "While this entity was getting updated, some other transaction updated the same entity";
        this.rootCause = rootCause;
    }
}
