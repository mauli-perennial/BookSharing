package dummydata;

import model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserSample {
    private UserSample() {
    }

    public static List<User> sampleReadyUser() {
        List<User> list = new ArrayList<>();
        try {
            list.add(new User(1, "mauli", "satav", 1234, "I am mauli satav from solapur maharashtra", "mauli.satav@perennialsys.com", "Mahi@123"));
        } catch (Exception e) {
            System.out.println("error in user sample date");
        }
        return list;
    }

    public static Map<String, User> userData(List<User> user) {
        HashMap<String, User> myUser = new HashMap<>();
        for (User users : user) {
            myUser.put(users.getEmail(), users);
        }
        return myUser;
    }
}

