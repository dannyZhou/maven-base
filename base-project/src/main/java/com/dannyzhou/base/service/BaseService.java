package com.dannyzhou.base.service;

import com.dannyzhou.base.utils.Pagination;
import com.dannyzhou.base.model.BaseModel;

import java.util.List;

/**
 * Created by danny on 1/7/17.
 */
public interface BaseService<ID extends Integer, ENTITY extends BaseModel> {

    public ENTITY getOneById(ID id);

    public ENTITY deleteOneById(ID id);

    public ENTITY update(ENTITY entity);

    public ENTITY create(ENTITY entity);

    List<ENTITY> getListByPagination(Pagination pagination);
}
