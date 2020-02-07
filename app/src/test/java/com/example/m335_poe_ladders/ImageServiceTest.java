package com.example.m335_poe_ladders;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ImageServiceTest {

    private ImageService SUT;

    @Before
    public void setUp() throws Exception {
        this.SUT = new ImageService();
    }

    @Test
    public void getImageId() {

        assertEquals(new Integer(R.drawable.ascendant_avatar), SUT.getImageId("Ascendant"));
        assertEquals(new Integer(R.drawable.assassin_avatar), SUT.getImageId("Assassin"));
        assertEquals(new Integer(R.drawable.berserker_avatar), SUT.getImageId("Berserker"));
        assertEquals(new Integer(R.drawable.champion_avatar), SUT.getImageId("Champion"));
        assertEquals(new Integer(R.drawable.chieftain_avatar), SUT.getImageId("Chieftain"));
        assertEquals(new Integer(R.drawable.deadeye_avatar), SUT.getImageId("Deadeye"));
        assertEquals(new Integer(R.drawable.elementalist_avatar), SUT.getImageId("Elementalist"));
        assertEquals(new Integer(R.drawable.gladiator_avatar), SUT.getImageId("Gladiator"));
        assertEquals(new Integer(R.drawable.guardian_avatar), SUT.getImageId("Guardian"));
        assertEquals(new Integer(R.drawable.hierophant_avatar), SUT.getImageId("Hierophant"));
        assertEquals(new Integer(R.drawable.inquisitor_avatar), SUT.getImageId("Inquisitor"));
        assertEquals(new Integer(R.drawable.juggernaut_avatar), SUT.getImageId("Juggernaut"));
        assertEquals(new Integer(R.drawable.necromancer_avatar), SUT.getImageId("Necromancer"));
        assertEquals(new Integer(R.drawable.occultist_avatar), SUT.getImageId("Occultist"));
        assertEquals(new Integer(R.drawable.pathfinder_avatar), SUT.getImageId("Pathfinder"));
        assertEquals(new Integer(R.drawable.raider_avatar), SUT.getImageId("Raider"));
        assertEquals(new Integer(R.drawable.saboteur_avatar), SUT.getImageId("Saboteur"));
        assertEquals(new Integer(R.drawable.slayer_avatar), SUT.getImageId("Slayer"));
        assertEquals(new Integer(R.drawable.trickster_avatar), SUT.getImageId("Trickster"));

        assertEquals(new Integer(R.drawable.ic_launcher_foreground), SUT.getImageId("No valid input"));
    }
}