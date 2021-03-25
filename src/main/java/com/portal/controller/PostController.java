package com.portal.controller;

import com.portal.model.Post;
import com.portal.model.Region;
import com.portal.model.Role;
import com.portal.model.User;
import com.portal.util.RepositoryFly;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostController {

    public void read(Long id){
        System.out.println(RepositoryFly.getRepository("post").read(id));
    }

    public void readAll(){
        RepositoryFly.getRepository("post").readAll().stream().forEach(System.out::println);
    }

    public void delete(Long id){
        RepositoryFly.getRepository("post").delete(id);
        System.out.println("User with id " + id + " deleted");
    }

    public void create(String obj){
        Post post = (Post) RepositoryFly.getRepository("post").create(new Post(0, obj, new Date(), new Date()));
        System.out.println(post.getId() + " created");
    }

    public void update(String obj){
        String[] info = obj.split(",");
        Post post = (Post) RepositoryFly.getRepository("post")
                .update(new Post(Integer.valueOf(info[0]), info[1], ((Post) RepositoryFly.getRepository("post").read(Long.valueOf(info[0]))).getCreated(), new Date()));
        System.out.println(post.getId() + " updated");
    }
}
