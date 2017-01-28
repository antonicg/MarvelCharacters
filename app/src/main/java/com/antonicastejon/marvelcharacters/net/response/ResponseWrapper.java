package com.antonicastejon.marvelcharacters.net.response;

import java.util.List;

/**
 * Created by Antoni Castejón on 28/01/2017.
 */

public class ResponseWrapper<T> {

    private int code;
    private String status;
    private String copyright;
    private String attributionText;
    private String etag;
    private DataContainer<T> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getAttributionText() {
        return attributionText;
    }

    public void setAttributionText(String attributionText) {
        this.attributionText = attributionText;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public DataContainer<T> getData() {
        return data;
    }

    public void setData(DataContainer<T> data) {
        this.data = data;
    }

    public static class DataContainer<T> {
        private int offset;
        private int limit;
        private int total;
        private int count;
        private List<T> results;

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<T> getResults() {
            return results;
        }

        public void setResults(List<T> results) {
            this.results = results;
        }
    }
}
