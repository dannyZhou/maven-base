package com.dannyzhou.base.utils;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by danny on 2/25/17.
 */
@Getter
@Setter
public class Pagination {

    private Integer pageSize = 20;
    private Integer pageCount = 0;
    private Integer currentPage = 1;
    private Long totalCount = 0L;
    private String sortFields = "id";
    private String sortType = "asc";
    private Map<String, Object> condition = new HashMap<>();

    public Map<String, Object> setCondition(String key, Object value) {
        condition.put(key, value);
        return condition;
    }

    public Integer getPageCount() {
        if (totalCount == 0) {
            return 0;
        }
        return new Long((totalCount - 1) / pageSize + 1).intValue();
    }

    public Object getCondition(String key) {
        return condition.get(key);
    }

    public Integer getFirstResult() {
        return (currentPage - 1) * pageSize;
    }
}
