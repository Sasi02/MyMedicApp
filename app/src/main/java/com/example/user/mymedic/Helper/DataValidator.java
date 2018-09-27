package com.example.user.mymedic.Helper;

/**
 * Created by Sachith Rukshan on 9/27/2018.
 */

public class DataValidator {
    public static boolean isWord(String word){
        return word.matches("^[a-zA-Z]+$");
    }

    public static boolean isPhoneNumber(String phone){
        return phone.matches("^([\+]?94)?[0]?[1-9][0-9]{8}$");
    }

    public static boolean isNIC(String nic){
        return nic.matches("^([0-9]{4}[0-8][0-9]{7})$|^([4-9][0-9][0-8][0-9]{6}[vV])$");
    }

    public static boolean isBlood(String blood){
        return blood.matches("^[ABO][B]?[+-]$");
    }

    public static boolean isText(String text){
        return text.matches("^[A-Za-z ]$");
    }
}
