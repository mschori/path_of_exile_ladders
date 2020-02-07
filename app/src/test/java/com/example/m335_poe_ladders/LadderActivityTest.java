package com.example.m335_poe_ladders;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LadderActivityTest {

    private LadderActivity SUT;

    @Before
    public void setUp() throws Exception {
        SUT = new LadderActivity();
    }

    @Test
    public void setTitleAndUrl() {
        SUT.setTitleAndUrl("metamorph_standard");
        assertEquals("https://api.pathofexile.com/ladders/Metamorph?limit=50", SUT.getJsonUrl());
        assertEquals("Metamorph Standard", SUT.getViewTitle());

        SUT.setTitleAndUrl("metamorph_hardcore");
        assertEquals("https://api.pathofexile.com/ladders/Hardcore Metamorph?limit=50", SUT.getJsonUrl());
        assertEquals("Metamorph Hardcore", SUT.getViewTitle());

        SUT.setTitleAndUrl("no valid input");
        assertEquals("https://api.pathofexile.com/ladders/Metamorph?limit=50", SUT.getJsonUrl());
        assertEquals("Metamorph Standard", SUT.getViewTitle());
    }
}