package com.home.project.pet.clinic.utils;

import org.apache.log4j.Logger;

public class Util {

    public static void log(String data, Class cls) {
        Logger.getLogger(cls).error(data);
    }

    public static boolean isEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    public static boolean isTel(String tel) {
        String regex = "^\\d{10}$";
        return tel.matches(regex);
    }

}
