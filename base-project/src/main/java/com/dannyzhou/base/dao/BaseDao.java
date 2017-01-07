package com.dannyzhou.base.dao;

import com.dannyzhou.base.model.BaseModel;
import com.dannyzhou.base.utils.Pagination;

import java.util.List;

/**
 * Created by danny on 1/7/17.
 */
public interface BaseDao<ID extends Integer, ENTITY extends BaseModel> {

    ENTITY getOneById(ID id);

    ENTITY deleteOneById(ID id);

    ENTITY update(ENTITY entity);

    ENTITY create(ENTITY entity);

    List<ENTITY> getListByPagination(Pagination pagination);
}
