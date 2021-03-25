package com.portal.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.portal.model.User;
import com.portal.repository.interfaces.UserRepository;
import com.portal.util.Paths;
import com.portal.util.Utils;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class UserRepositoryImpl implements UserRepository {


    @Override
    public User read(Long id) {
        try(JsonReader reader = new JsonReader(new FileReader(Paths.USER.getDirectory()));) {
            User[] users = Utils.GSON.fromJson(reader, User[].class);
            return Arrays.stream(users).filter(u -> u.getId() == id).findAny().get();
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> readAll() {
        try(JsonReader reader = new JsonReader(new FileReader(Paths.USER.getDirectory()))) {
            return List.of(Utils.GSON.fromJson(reader, User[].class));
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        User[] users;
        try(JsonReader reader = new JsonReader(new FileReader(Paths.USER.getDirectory()))) {
            users = Utils.GSON.fromJson(reader, User[].class);
        } catch (IOException e){
            e.printStackTrace();
            return;
        }
        try(JsonWriter writer = new JsonWriter(new FileWriter(Paths.USER.getDirectory()))) {
            writer.jsonValue(Utils.GSON.toJson(Arrays.stream(users).filter(u -> u.getId() != id).collect(Collectors.toList())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User create(User obj) {
        obj.setId(Utils.lastId(Paths.USER) + 1);
        List<User> users = new ArrayList<>();
        try(JsonReader reader = new JsonReader(new FileReader(Paths.USER.getDirectory()))) {
            User[] usersArray = Utils.GSON.fromJson(reader, User[].class);
            if(usersArray != null ) Collections.addAll(users, usersArray);
            users.add(obj);
        } catch (IOException e){
            e.printStackTrace();
        }
        try(JsonWriter writer = new JsonWriter(new FileWriter(Paths.USER.getDirectory()))) {
            writer.jsonValue(Utils.GSON.toJson(users));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public User update(User obj) {
        List<User> users = new ArrayList<>();
        try(JsonReader reader = new JsonReader(new FileReader(Paths.USER.getDirectory()))) {
            Collections.addAll(users, Utils.GSON.fromJson(reader, User[].class));
        } catch (IOException e){
            e.printStackTrace();
        }
        try(JsonWriter writer = new JsonWriter(new FileWriter(Paths.USER.getDirectory()))) {
            writer.jsonValue(Utils.GSON.toJson(users.stream().map(u -> {
                if(u.getId() == obj.getId()) u = obj;
                return u;
            }).toArray()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
