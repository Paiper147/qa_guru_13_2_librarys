package quru.qa.tests;

import java.util.HashMap;
import java.util.Map;

public class TestData {
    public final static String NAME = "myName";
    public final static String LAST_NAME = "myLastName";

    public final static String EMAIL_DOMAIN = "@qa.guru";

    public final static String[] INPUT_ARRAY_GENDERS = {"Male", "Female", "Other"};

    public final static String[] INPUT_ARRAY_SUBJECTS = {
            "Maths",
            "Chemistry",
            "Computer Science",
            "Commerce",
            "Economics",
            "English",
            "Social Studies",
            "Arts", "History",
            "Accounting",
            "Physics",
            "Biology",
            "Hindi",
            "Civics"
    };

    public final static String[] INPUT_ARRAY_OF_HOBBIES = {"Sports", "Reading", "Music"};
    public final static String[] INPUT_ARRAY_OF_PICTURES = {"1.png", "2.png", "3.png"};

    public final static String[] INPUT_STATES = new String[]{"NCR", "Uttar Pradesh", "Haryana", "Rajasthan"};
    public final static Map<String, String[]> STATE_AND_CITY_MAP = new HashMap<>();
    static {
        STATE_AND_CITY_MAP.put("NCR", new String[]{"Delhi", "Gurgaon", "Noida"});
        STATE_AND_CITY_MAP.put("Uttar Pradesh", new String[]{"Agra", "Lucknow", "Merrut"});
        STATE_AND_CITY_MAP.put("Haryana", new String[]{"Karnal", "Panipat"});
        STATE_AND_CITY_MAP.put("Rajasthan", new String[]{"Jaipur", "Jaiselmer"});
    }
}
