package com.sura.constructores.constructor.helpers;

import java.util.Calendar;
import java.util.Date;

public class CreateDate {


    public Date getActualDate() {
        var date = Calendar.getInstance();
        return date.getTime();
    }

    public Date setFinishDate(Date date, Integer days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, days);
        return calendar.getTime();
    }

    public Date setStartDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        return calendar.getTime();
    }

}
