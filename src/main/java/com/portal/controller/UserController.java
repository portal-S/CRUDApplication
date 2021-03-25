package com.portal.controller;

import com.portal.model.Post;
import com.portal.model.Region;
import com.portal.model.Role;
import com.portal.model.User;
import com.portal.repository.UserRepositoryImpl;
import com.portal.util.RepositoryFly;

import java.util.ArrayList;
import java.util.List;

public class UserController {

    public void read(Long id){
        System.out.println(RepositoryFly.getRepository("user").read(id));
    }

    public void readAll(){
        RepositoryFly.getRepository("user").readAll().stream().forEach(System.out::println);
    }

    public void delete(Long id){
        RepositoryFly.getRepository("user").delete(id);
        System.out.println("User with id " + id + " deleted");
    }

    public void create(String obj){
        String[] info = obj.split(",");
        List<Post> posts = new ArrayList<>();
        for(String s : info[3].split(";")){
            posts.add((Post) RepositoryFly.getRepository("post").read(Long.valueOf(s)));
        }
        User user = (User) RepositoryFly.getRepository("user")
                .create(new User(0, info[0], info[1],(Region) RepositoryFly.getRepository("region").read(Long.valueOf(info[2])), posts, Role.valueOf(info[4])));
        System.out.println(user.getFirstName() + " created");
    }

    public void update(String obj){
        String[] info = obj.split(",");
        List<Post> posts = new ArrayList<>();
        for(String s : info[4].split(";")){
            posts.add((Post) RepositoryFly.getRepository("post").read(Long.valueOf(s)));
        }
        User user = (User) RepositoryFly.getRepository("user")
                .update(new User(Integer.valueOf(info[0]), info[1], info[2],(Region) RepositoryFly.getRepository("region").read(Long.valueOf(info[3])), posts, Role.valueOf(info[5])));
        System.out.println(user.getFirstName() + " updated");
    }


}
