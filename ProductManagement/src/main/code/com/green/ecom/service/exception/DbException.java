package com.green.ecom.service.exception;

/**
 * Created by IntelliJ IDEA.
 * User: gaurav
 * Date: 21/08/11
 * Time: 12:35 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class DbException extends RuntimeException{

    protected Exception rootCause;
    protected String info;

    protected String code;
    protected String message;

    public Exception getRootCause() {
        return rootCause;
    }

    public void setRootCause(Exception rootCause) {
        this.rootCause = rootCause;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getMessage(){
        return rootCause.getMessage();
    }

    @Override
    public String toString() {
        return "DbException{" +
                "info='" + info + '\'' +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
