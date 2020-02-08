package com.example.m335_poe_ladders;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LadderTest {

    private Ladder SUT;

    @Before
    public void setUp() throws Exception {
        this.SUT = new Ladder("Michael", 50, 33, 42, "MyAccount");
    }

    @Test
    public void getProperties() {

        assertEquals("Michael", SUT.getName());
        assertEquals(new Integer(50), SUT.getRank());
        assertEquals(new Integer(33), SUT.getLevel());
        assertEquals(new Integer(42), SUT.getImageId());
        assertEquals("MyAccount", SUT.getAccountName());
    }
}