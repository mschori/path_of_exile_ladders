package com.example.m335_poe_ladders;

public class Ladder {

    private String name;
    private Integer rank;
    private Integer level;
    private Integer imageId;
    private String accountName;

    public Ladder(String name, Integer rank, Integer level, Integer imageId, String accountName) {
        this.name = name;
        this.rank = rank;
        this.level = level;
        this.imageId = imageId;
        this.accountName = accountName;
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

    public String getAccountName() {
        return accountName;
    }
}
