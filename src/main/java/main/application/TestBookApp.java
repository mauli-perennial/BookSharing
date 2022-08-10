package main.application;

import user.app.UserDashboard;

public class TestBookApp {

    public static void main(String[] args) {
        try {
            UserDashboard.userDashboardRun();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
