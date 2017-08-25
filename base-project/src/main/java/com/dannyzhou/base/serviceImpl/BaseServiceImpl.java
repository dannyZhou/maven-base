package com.dannyzhou.base.serviceImpl;

import com.dannyzhou.base.dao.BaseDao;
import com.dannyzhou.base.model.BaseModel;
import com.dannyzhou.base.service.BaseService;
import com.dannyzhou.base.utils.Pagination;

import java.util.List;

/**
 * Created by danny on 1/7/17.
 */
public abstract class BaseServiceImpl<ID extends Integer, ENTITY extends BaseModel> implements BaseService<ID, ENTITY> {

    protected BaseDao<ID, ENTITY> baseDao;

    public abstract void setBaseDao(BaseDao<ID, ENTITY> baseDao);

    @Override
    public ENTITY getOneById(ID id) {
        return baseDao.getOneById(id);
    }

    @Override
    public ENTITY deleteOneById(ID id) {
        return baseDao.deleteOneById(id);
    }

    @Override
    public ENTITY update(ENTITY entity) {
        return baseDao.update(entity);
    }

    @Override
    public ENTITY create(ENTITY entity) {
        return baseDao.create(entity);
    }

    @Override
    public List<ENTITY> getListByPagination(Pagination pagination) {
        return baseDao.getListByPagination(pagination);
    }
}
