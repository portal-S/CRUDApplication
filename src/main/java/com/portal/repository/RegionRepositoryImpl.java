package com.portal.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.portal.model.Region;
import com.portal.model.User;
import com.portal.repository.interfaces.RegionRepository;
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

public class RegionRepositoryImpl implements RegionRepository {


    @Override
    public Region read(Long id) {
        try(JsonReader reader = new JsonReader(new FileReader(Paths.REGION.getDirectory()));) {
            Region[] regions = Utils.GSON.fromJson(reader, Region[].class);
            return Arrays.stream(regions).filter(r -> r.getId() == id).findAny().get();
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Region> readAll() {
        try(JsonReader reader = new JsonReader(new FileReader(Paths.REGION.getDirectory()))) {
            return List.of(Utils.GSON.fromJson(reader, Region[].class));
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        Region[] regions;
        try(JsonReader reader = new JsonReader(new FileReader(Paths.REGION.getDirectory()))) {
            regions = Utils.GSON.fromJson(reader, Region[].class);
        } catch (IOException e){
            e.printStackTrace();
            return;
        }
        try(JsonWriter writer = new JsonWriter(new FileWriter(Paths.REGION.getDirectory()))) {
            writer.jsonValue(Utils.GSON.toJson(Arrays.stream(regions).filter(r -> r.getId() != id).collect(Collectors.toList())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Region create(Region obj) {
        obj.setId(Utils.lastId(Paths.REGION) + 1);
        List<Region> regions = new ArrayList<>();
        try(JsonReader reader = new JsonReader(new FileReader(Paths.REGION.getDirectory()))) {
            Region[] regionsArray = Utils.GSON.fromJson(reader, Region[].class);
            if(regionsArray != null) Collections.addAll(regions, regionsArray);
            regions.add(obj);
        } catch (IOException e){
            e.printStackTrace();
        }
        try(JsonWriter writer = new JsonWriter(new FileWriter(Paths.REGION.getDirectory()))) {
            writer.jsonValue(Utils.GSON.toJson(regions));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public Region update(Region obj) {
        List<Region> regions = new ArrayList<>();
        try(JsonReader reader = new JsonReader(new FileReader(Paths.REGION.getDirectory()))) {
            Collections.addAll(regions, Utils.GSON.fromJson(reader, Region[].class));
        } catch (IOException e){
            e.printStackTrace();
        }
        try(JsonWriter writer = new JsonWriter(new FileWriter(Paths.REGION.getDirectory()))) {
            writer.jsonValue(Utils.GSON.toJson(regions.stream().map(r -> {
                if(r.getId() == obj.getId()) r = obj;
                return r;
            }).toArray()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
