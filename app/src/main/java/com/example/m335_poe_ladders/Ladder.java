package com.example.m335_poe_ladders;

public class Ladder {

    private String name;
    private Integer rank;
    private Integer level;
    private Integer imageId;

    public Ladder(String name, Integer rank, Integer level, Integer imageId) {
        this.name = name;
        this.rank = rank;
        this.level = level;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public Integer getRank() {
        return rank;
    }

    public Integer getLevel() {
        return level;
    }

    public Integer getImageId() {
        return imageId;
    }
}
