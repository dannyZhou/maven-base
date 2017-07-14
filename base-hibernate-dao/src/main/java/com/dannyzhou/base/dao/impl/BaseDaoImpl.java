package com.dannyzhou.base.dao.impl;

import com.dannyzhou.base.dao.BaseDao;
import com.dannyzhou.base.model.BaseModel;
import com.dannyzhou.base.utils.Pagination;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;

/**
 * Created by danny on 1/7/17.
 */
public class BaseDaoImpl<ID extends Integer, ENTITY extends BaseModel> implements BaseDao<ID, ENTITY> {

    private Class<ENTITY> clazz;

    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    public BaseDaoImpl() {
        this.clazz = (Class<ENTITY>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[1];
    }

    public ENTITY getOneById(ID id) {
        return (ENTITY) currentSession().get(clazz, id);
    }

    protected Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public ENTITY deleteOneById(ID id) {
        return null;
    }

    @Override
    public ENTITY update(ENTITY entity) {
        entity.setUpdateAt(new Date());
        currentSession().update(entity);
        return entity;
    }

    @Override
    public ENTITY create(ENTITY entity) {
        entity.setCreateAt(new Date());
        ID id = (ID) currentSession().save(entity);
        entity.setId(id);
        return entity;
    }

    @Override
    public List<ENTITY> getListByPagination(Pagination pagination) {
//        Query query = currentSession().createQuery(currentSession().getCriteriaBuilder().createQuery())
//                .setFirstResult(pagination.getFirstResult())
//                .setMaxResults(pagination.getPageSize());
//
//        return query.list();
        return null;
    }

    @Override
    public void flushAndClear() {
        currentSession().flush();
        currentSession().clear();
    }
}
