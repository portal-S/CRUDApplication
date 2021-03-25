package com.portal.view.interfaces;

public interface GenericView {

    public void read(Long id);

    public void readAll();

    public void delete(Long id);

    public void create(String info);

    public void update(String info);
}
