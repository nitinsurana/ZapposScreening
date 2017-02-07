package com.nitinsurana.ilovezappos.beans;

import java.io.Serializable;
import java.util.List;

/**
 * Created by coding_idiot on 06/02/17.
 */

public class ApiResult implements Serializable {
    private String originalTerm;
    private Integer currentResultCount;
    private Integer totalResultCount;
    private String term;
    private List<ProductBean> results;
    private Integer statusCode;

    public String getOriginalTerm() {
        return originalTerm;
    }

    public void setOriginalTerm(String originalTerm) {
        this.originalTerm = originalTerm;
    }

    public Integer getCurrentResultCount() {
        return currentResultCount;
    }

    public void setCurrentResultCount(Integer currentResultCount) {
        this.currentResultCount = currentResultCount;
    }

    public Integer getTotalResultCount() {
        return totalResultCount;
    }

    public void setTotalResultCount(Integer totalResultCount) {
        this.totalResultCount = totalResultCount;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public List<ProductBean> getResults() {
        return results;
    }

    public void setResults(List<ProductBean> results) {
        this.results = results;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }
}
