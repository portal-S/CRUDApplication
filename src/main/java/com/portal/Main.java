package com.portal;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.portal.controller.PostController;
import com.portal.controller.RegionController;
import com.portal.controller.UserController;
import com.portal.model.Region;
import com.portal.model.User;
import com.portal.repository.UserRepositoryImpl;
import com.portal.util.Paths;
import com.portal.util.Utils;
import com.portal.view.PostView;
import com.portal.view.RegionView;
import com.portal.view.UserView;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter data");
        while (scanner.hasNext()){
            handler(scanner.nextLine());
        }

    }

    public static void handler(String s){
        String s1 = s.toLowerCase();
        switch (s1.split(" ")[0]){
            case "user":
                UserView userView = new UserView();
                Utils.viewHandler(userView, "user", s);
                break;
            case "region":
                RegionView regionView = new RegionView();
                Utils.viewHandler(regionView, "region", s);
                break;
            case "post":
                PostView postView = new PostView();
                Utils.viewHandler(postView, "post", s);
                break;
            default:
                System.err.println("Invalid data");
        }
    }
}
