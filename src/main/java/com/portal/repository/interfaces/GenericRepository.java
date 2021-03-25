package com.portal.repository.interfaces;

import java.util.List;

public interface GenericRepository<T, ID> {
    public T read(ID id);

    public List<T> readAll();

    public void delete(ID id);

    public T create(T obj);

    public T update(T obj);
}
