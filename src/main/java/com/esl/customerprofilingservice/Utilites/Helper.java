package com.esl.myservicenameservice.Utilites;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Helper {
    public static boolean isNullOrEmpty(String statement){

        return (statement == null || statement.trim().isEmpty());

    }

}
