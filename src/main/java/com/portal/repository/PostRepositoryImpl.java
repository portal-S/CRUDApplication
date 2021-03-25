package com.portal.repository;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.portal.model.Post;
import com.portal.model.Post;
import com.portal.model.User;
import com.portal.repository.interfaces.PostRepository;
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

public class PostRepositoryImpl implements PostRepository {


    @Override
    public Post read(Long id) {
        try(JsonReader reader = new JsonReader(new FileReader(Paths.POST.getDirectory()));) {
            Post[] posts = Utils.GSON.fromJson(reader, Post[].class);
            return Arrays.stream(posts).filter(r -> r.getId() == id).findAny().get();
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Post> readAll() {
        try(JsonReader reader = new JsonReader(new FileReader(Paths.POST.getDirectory()))) {
            return List.of(Utils.GSON.fromJson(reader, Post[].class));
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        Post[] posts;
        try(JsonReader reader = new JsonReader(new FileReader(Paths.POST.getDirectory()))) {
            posts = Utils.GSON.fromJson(reader, Post[].class);
        } catch (IOException e){
            e.printStackTrace();
            return;
        }
        try(JsonWriter writer = new JsonWriter(new FileWriter(Paths.POST.getDirectory()))) {
            writer.jsonValue(Utils.GSON.toJson(Arrays.stream(posts).filter(r -> r.getId() != id).collect(Collectors.toList())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Post create(Post obj) {
        obj.setId(Utils.lastId(Paths.POST) + 1);
        List<Post> posts = new ArrayList<>();
        try(JsonReader reader = new JsonReader(new FileReader(Paths.POST.getDirectory()))) {
            Post[] postsArray = Utils.GSON.fromJson(reader, Post[].class);
            if(postsArray != null) Collections.addAll(posts, postsArray);
            posts.add(obj);
        } catch (IOException e){
            e.printStackTrace();
        }
        try(JsonWriter writer = new JsonWriter(new FileWriter(Paths.POST.getDirectory()))) {
            writer.jsonValue(Utils.GSON.toJson(posts));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public Post update(Post obj) {
        List<Post> posts = new ArrayList<>();
        try(JsonReader reader = new JsonReader(new FileReader(Paths.POST.getDirectory()))) {
            Collections.addAll(posts, Utils.GSON.fromJson(reader, Post[].class));
        } catch (IOException e){
            e.printStackTrace();
        }
        try(JsonWriter writer = new JsonWriter(new FileWriter(Paths.POST.getDirectory()))) {
            writer.jsonValue(Utils.GSON.toJson(posts.stream().map(r -> {
                if(r.getId() == obj.getId()) r = obj;
                return r;
            }).toArray()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
