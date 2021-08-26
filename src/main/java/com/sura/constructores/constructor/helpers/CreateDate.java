package com.sura.constructores.constructor.helpers;

import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class CreateDate {


    private Date getActualDate() {
        var date = Calendar.getInstance();
        return date.getTime();
    }

    public Date setFinishDate(Date date, Integer days) {
        Calendar calendar = addDays(date, days);
        return calendar.getTime();
    }

    public Date setStartDate(Date date) {
        if(date == null){
            return getActualDate();
        }
        Calendar calendar = addDays(date, 1);
        return calendar.getTime();
    }

    private Calendar addDays(Date date, Integer days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, days);
        return calendar;
    }

}
