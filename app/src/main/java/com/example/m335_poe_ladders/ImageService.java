package com.example.m335_poe_ladders;

public class ImageService {

    /**
     * Get image-id based on classInfo.
     *
     * @param classInfo Character-class.
     * @return Image-Id.
     */
    public Integer getImageId(String classInfo) {

        switch (classInfo) {
            case "Ascendant":
                return R.drawable.ascendant_avatar;
            case "Assassin":
                return R.drawable.assassin_avatar;
            case "Berserker":
                return R.drawable.berserker_avatar;
            case "Champion":
                return R.drawable.champion_avatar;
            case "Chieftain":
                return R.drawable.chieftain_avatar;
            case "Deadeye":
                return R.drawable.deadeye_avatar;
            case "Elementalist":
                return R.drawable.elementalist_avatar;
            case "Gladiator":
                return R.drawable.gladiator_avatar;
            case "Guardian":
                return R.drawable.guardian_avatar;
            case "Hierophant":
                return R.drawable.hierophant_avatar;
            case "Inquisitor":
                return R.drawable.inquisitor_avatar;
            case "Juggernaut":
                return R.drawable.juggernaut_avatar;
            case "Necromancer":
                return R.drawable.necromancer_avatar;
            case "Occultist":
                return R.drawable.occultist_avatar;
            case "Pathfinder":
                return R.drawable.pathfinder_avatar;
            case "Raider":
                return R.drawable.raider_avatar;
            case "Saboteur":
                return R.drawable.saboteur_avatar;
            case "Slayer":
                return R.drawable.slayer_avatar;
            case "Trickster":
                return R.drawable.trickster_avatar;
            default:
                return R.drawable.ic_launcher_foreground;
        }
    }
}
