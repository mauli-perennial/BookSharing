package dummydata;

import model.User;
import model.UserBuilder;

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
            list.add(new User(new UserBuilder(1,"mauli","satav",21313,"mauli.satav@gmail.com","Mahi@123").bioData("SASAS")));
        } catch (Exception e) {
          e.printStackTrace();
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

