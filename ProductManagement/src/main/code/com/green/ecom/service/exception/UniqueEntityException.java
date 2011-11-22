package com.green.ecom.service.exception;

/**
 * Created by IntelliJ IDEA.
 * User: gaurav
 * Date: 21/08/11
 * Time: 12:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class UniqueEntityException extends DbException{

    public UniqueEntityException(String info,Exception rootCause){
        this.info = info;
        this.code = "DB-UNQ";
        this.message = "Entity by same name already exists in the system";
        this.rootCause = rootCause;
    }


}
