package com.portal.view;

import com.portal.controller.RegionController;
import com.portal.controller.UserController;
import com.portal.view.interfaces.GenericView;

public class RegionView implements GenericView {

    private RegionController controller = new RegionController();

    public void handler(String info){

    }

    public void read(Long id){
        controller.read(id);
    }

    public void readAll(){
        controller.readAll();
    }

    public void delete(Long id){
        controller.delete(id);
    }

    public void create(String info){
        controller.create(info);
    }

    public void update(String info){
        controller.update(info);
    }
}
