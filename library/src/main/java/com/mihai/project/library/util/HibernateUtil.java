package com.mihai.project.library.util;

import java.util.List;

public class HibernateUtil {

    public static <T> T getUniqueResult(List<T> resultList) {
        if (resultList == null || resultList.isEmpty() || resultList.size() > 1) {
            return null;
        }
        return (T) resultList.get(0);
    }


}
