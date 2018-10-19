package com.client.client.service;

import com.client.client.entity.Gender;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TypeParser {


    public static Long parseLong(String value) {
        try {
            return Long.parseLong(value);
        }catch (NumberFormatException e){
            return null;
        }
    }

    public static Double parseDouble(String value) {
        try {
            return Double.parseDouble(value);
        }catch (NumberFormatException e){
            return null;
        }
    }

    public static Integer parseInt(String value) {
        try {
            return Integer.parseInt(value);
        }catch (NumberFormatException e){
            return null;
        }
    }

    public static Date parseDate(String value) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return simpleDateFormat.parse(value);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Boolean parseBoolean(String value){
        try {
            return Boolean.parseBoolean(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Gender parseGender(String value){
        try {
            switch (value){
                case "1" :  return Gender.MALE;
                case "0" : return  Gender.FEMALE;
                case "male" : return Gender.MALE;
                case "female" : return Gender.FEMALE;
                default: return null;
            }

        } catch (NumberFormatException e) {
            return null;
        }
    }
}
