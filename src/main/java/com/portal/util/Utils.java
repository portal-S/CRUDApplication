package com.portal.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.portal.model.User;
import com.portal.view.interfaces.GenericView;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

public class Utils {
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static int lastId(Paths paths){
        Gson gson = new Gson();
        try(JsonReader reader = new JsonReader(new FileReader(paths.getDirectory()))) {
            User[] users = gson.fromJson(reader, User[].class);
            if(users.length == 0) return 0;
            return Arrays.stream(users).max(Comparator.comparingInt(User::getId)).get().getId();
        } catch (IOException e){
            e.printStackTrace();
        }
        return 0;
    }

    public static void viewHandler(GenericView view, String type, String info){
        String[] data = info.split(" ");
        switch (data[1]){
            case "read":
                view.read(Long.valueOf(info.replace(type + " read ", "")));
                break;
            case "readAll":
                view.readAll();
                break;
            case "delete":
                view.delete(Long.valueOf(info.replace(type + " delete ", "")));
                break;
            case "create":
                view.create(info.replace(type + " create ", ""));
                break;
            case "update":
                view.update(info.replace(type + " update ", ""));
                break;
        }
    }
}
