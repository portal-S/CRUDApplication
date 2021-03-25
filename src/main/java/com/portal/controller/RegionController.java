package com.portal.controller;

import com.portal.model.Post;
import com.portal.model.Region;
import com.portal.util.RepositoryFly;

import java.util.Date;

public class RegionController {
    public void read(Long id){
        System.out.println(RepositoryFly.getRepository("region").read(id));
    }

    public void readAll(){
        RepositoryFly.getRepository("region").readAll().stream().forEach(System.out::println);
    }

    public void delete(Long id){
        RepositoryFly.getRepository("region").delete(id);
        System.out.println("User with id " + id + " deleted");
    }

    public void create(String obj){
        Region region = (Region) RepositoryFly.getRepository("region").create(new Region(0, obj));
        System.out.println(region.getId() + " created");
    }

    public void update(String obj){
        String[] info = obj.split(",");
        Region region = (Region) RepositoryFly.getRepository("region").update(new Region(Integer.valueOf(info[0]), info[1]));
        System.out.println(region.getId() + " updated");
    }
}
