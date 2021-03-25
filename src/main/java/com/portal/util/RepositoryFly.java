package com.portal.util;

import com.portal.repository.PostRepositoryImpl;
import com.portal.repository.RegionRepositoryImpl;
import com.portal.repository.UserRepositoryImpl;
import com.portal.repository.interfaces.GenericRepository;

import java.util.HashMap;
import java.util.Map;

public class RepositoryFly {
    private static Map<String,GenericRepository> repositories = new HashMap<>();

    public static GenericRepository getRepository(String name){
        GenericRepository repository = repositories.get(name);

        if(repository == null){
            switch (name){
                case "user":
                    repository = new UserRepositoryImpl();
                    break;
                case "region":
                    repository = new RegionRepositoryImpl();
                    break;
                case "post":
                    repository = new PostRepositoryImpl();
                    break;
                default: return null;
            }
            repositories.put(name, repository);
        }
        return repository;
    }


}
