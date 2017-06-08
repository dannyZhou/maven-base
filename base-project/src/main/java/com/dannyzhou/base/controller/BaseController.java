package com.dannyzhou.base.controller;

import com.dannyzhou.base.service.BaseService;

/**
 * Created by danny on 1/7/17.
 */
public abstract class BaseController {

    protected BaseService baseService;

    public abstract void setBaseService(BaseService baseService);
}
