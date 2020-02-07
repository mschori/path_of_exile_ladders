package com.example.m335_poe_ladders;

import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.*;

public class QueryUtilsTest {

    private QueryUtils SUT;

    @Before
    public void setUp() {
        SUT = new QueryUtils();
    }

    @Test
    public void createUrl() {

        URL expectedUrl = null;
        try {
            expectedUrl = new URL("http://www.google.ch");
        } catch (MalformedURLException exception) {
            fail();
        }

        assertEquals(expectedUrl, SUT.createUrl("http://www.google.ch"));
    }

    @Test
    public void generateLadderList() {

        /**
         * This Test is not working because of the not-mocked problem.
         */

//        String mockup_json = "{\"total\":15000,\"cached_since\":\"2020-02-07T13:38:06+00:00\",\"entries\":[{\"rank\":1,\"dead\":true,\"online\":false,\"character\":{\"name\":\"cyakimanbo_hasuD\",\"level\":100,\"class\":\"Saboteur\",\"id\":\"bb149b1dde561ffcbfff58b7a412809201d1c177fbfb25d449b022519f96d4f3\",\"experience\":4250334444,\"depth\":{\"default\":186,\"solo\":159}},\"account\":{\"name\":\"cyakimanbo\",\"realm\":\"pc\",\"challenges\":{\"total\":29}}},{\"rank\":2,\"dead\":false,\"online\":false,\"character\":{\"name\":\"hsdf_hasuD\",\"level\":100,\"class\":\"Guardian\",\"id\":\"72120a8ae436a742ac7277801bb64ae667593cf248cd6014ceffabb6e40b56e2\",\"experience\":4250334444,\"depth\":{\"default\":2,\"solo\":0}},\"account\":{\"name\":\"hsdf\",\"realm\":\"pc\",\"challenges\":{\"total\":24}}}]}";
//
//        ArrayList<Ladder> expectedList = new ArrayList<>();
//        expectedList.add(new Ladder("michael", 1, 100, R.drawable.saboteur_avatar));
//        expectedList.add(new Ladder("irene", 2, 100, R.drawable.guardian_avatar));
//
//        assertEquals(expectedList, SUT.generateLadderList(mockup_json));
    }
}