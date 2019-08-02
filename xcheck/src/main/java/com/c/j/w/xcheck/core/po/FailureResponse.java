package com.c.j.w.xcheck.core.po;

/**
 * Created by xy on 2019/2/14.
 */


public class FailureResponse {

    private int code = 5010;
    private String message;
    private boolean result = false;
    private String redirect = "";
    private boolean success = false;

    public FailureResponse(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean isResult() {
        return result;
    }

    public String getRedirect() {
        return redirect;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
