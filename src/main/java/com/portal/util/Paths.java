package com.portal.util;

public enum Paths {
    USER("C:/Users/User/IdeaProjects/CRUDApplication/src/main/resources/users.json"),
    REGION("C:/Users/User/IdeaProjects/CRUDApplication/src/main/resources/regions.json"),
    POST("C:/Users/User/IdeaProjects/CRUDApplication/src/main/resources/posts.json"),;

    private String directory;

    Paths(String directory) {
        this.directory = directory;
    }

    public String getDirectory() {
        return directory;
    }
}
