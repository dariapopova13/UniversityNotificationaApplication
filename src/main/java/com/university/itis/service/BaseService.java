package com.university.itis.service;

import java.util.List;

public interface BaseService<T> {

    T get(Long id);

    T saveOrUdpate(T t);

    List<T> delete(Long id);

}
