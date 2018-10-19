package com.client.client.service;

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



//    STRING {
//        @Override
//        public Object parse(String value) {
//            return value;
//        }
//    },
//
//    INTEGER {
//        @Override
//        public Object parse(String value) {
//            try {
//                return Integer.parseInt(value);
//            }catch (NumberFormatException e){
//                return null;
//            }
//        }
//    },
//
//    DOUBLE {
//        @Override
//        public Object parse(String value) {
//            try {
//                return Double.parseDouble(value);
//            }catch (NumberFormatException e){
//                return null;
//            }
//        }
//    },
//
//    LONG {
//        @Override
//        public Long parse(String value) {
//            try {
//                return Long.parseLong(value);
//            }catch (NumberFormatException e){
//                return null;
//            }
//        }
//    },
//
//    DATE {
//        @Override
//        public Date parse(String value) {
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
//            try {
//                return simpleDateFormat.parse(value);
//            } catch (ParseException e) {
//               return null;
//            }
//        }
//    },
//
//    BOOLEAN {
//        @Override
//        public Boolean parse(String value) {
//            try {
//                return Boolean.parseBoolean(value);
//            } catch (NumberFormatException e) {
//                return null;
//            }
//        }
//    };
//
//    public abstract Object parse(String value) ;
}
