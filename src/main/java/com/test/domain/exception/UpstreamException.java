package com.test.domain.exception;

/**
 * Created by Ryan Miao on 10/26/17.
 */
public class UpstreamException extends Exception {

    private Integer status;

    public UpstreamException(Integer status, String message) {
        super(message);
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "UpstreamException{" +
                "status=" + status +
                '}';
    }
}
