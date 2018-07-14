package com.university.itis.utils;

import org.springframework.stereotype.Component;

@Component
public class AppUtils {

    public int getCourse(String group) {
        if (!assertItis(group))
            return 0;
        char first = group.charAt(group.indexOf('-') + 1);
        switch (first) {
            case '4':
                return 4;
            case '5':
                return 3;
            case '6':
                return 2;
            case '7':
                return 1;
            default:
                return 0;
        }
    }

    private boolean assertItis(String group) {
        return group.startsWith("11-");
    }

}
