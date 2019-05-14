package com.app.springboot.pcf.service;

import com.app.springboot.pcf.exception.ApiException;

import java.util.List;

/**
 * @author Anish Panthi
 */
public interface BaseService<T, DT, ID> {

    List<DT> findAll() throws ApiException;

    DT findOne(ID id) throws ApiException;

    DT save(T t) throws ApiException;

    DT update(T t) throws ApiException;

    void delete(T t) throws ApiException;
}
