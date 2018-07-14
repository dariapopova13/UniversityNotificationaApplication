package com.university.itis.controller;

import java.util.List;

public interface BaseController<T> {

    public T saveOrUpdate(T t);

    public List<T> delete(Long id);

    public T get(Long id);
}
