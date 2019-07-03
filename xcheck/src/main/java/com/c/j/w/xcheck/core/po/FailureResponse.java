package com.c.j.w.xcheck.core.po;

/**
 * Created by xy on 2019/2/14.
 */


public class FailureResponse {

    private int code = 5010;
    private String message;
    private boolean result = false;
    private String redirect;

    public FailureResponse(String message) {
        this.message = message;
    }



}
