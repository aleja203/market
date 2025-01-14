
package com.atenea.market.Entidades;

public class BackUrls {

    private String success;
    private String failure;
    private String pending;

    public BackUrls(String success, String failure, String pending) {
        this.success = success;
        this.failure = failure;
        this.pending = pending;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getFailure() {
        return failure;
    }

    public void setFailure(String failure) {
        this.failure = failure;
    }

    public String getPending() {
        return pending;
    }

    public void setPending(String pending) {
        this.pending = pending;
    }
    
    
    
}
