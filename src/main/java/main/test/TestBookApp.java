package main.test;

import book.service.UserDashboard;
import java.util.logging.Logger;

public class TestBookApp {
    public static final Logger log = Logger.getLogger(String.valueOf(TestBookApp.class));
    public static void main(String[] args){
        UserDashboard.userDashboardRun();
    }
}
