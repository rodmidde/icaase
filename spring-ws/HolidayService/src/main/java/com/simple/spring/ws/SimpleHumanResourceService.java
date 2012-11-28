package com.simple.spring.ws;

import java.util.Date;

public class SimpleHumanResourceService implements HumanResourceService {
    public void bookHoliday(Date startDate, Date endDate, String name) {
        System.out.println("Holiday booked for " + name);
    }
}
