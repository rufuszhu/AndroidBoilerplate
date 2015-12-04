package ca.co.rufus.androidboilerplate.test.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import ca.co.rufus.androidboilerplate.data.model.Name;
import ca.co.rufus.androidboilerplate.data.model.Profile;
import ca.co.rufus.androidboilerplate.data.model.Ribot;

/**
 * Factory class that makes instances of data models with random field values.
 * The aim of this class is to help setting up test fixtures.
 */
public class TestDataFactory {

    public static String randomUuid() {
        return UUID.randomUUID().toString();
    }

    public static Ribot makeRibot() {
        return new Ribot(makeProfile());
    }

    public static List<Ribot> makeListRibots(int number) {
        List<Ribot> ribots = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            ribots.add(makeRibot());
        }
        return ribots;
    }

    public static Profile makeProfile() {
        Profile profile = new Profile();
        profile.email = randomUuid();
        profile.name = makeName();
        profile.dateOfBirth = new Date();
        profile.hexColor = "#0066FF";
        profile.avatar = "http://api.ribot.io/images/" + profile.email;
        profile.bio = randomUuid();
        return profile;
    }

    public static Name makeName() {
        Name name = new Name();
        name.first = randomUuid();
        name.last = randomUuid();
        return name;
    }

}