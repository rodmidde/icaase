package com.simple.spring.ws;

import java.util.Date;

/**
 * Description for the class HumanResourceService:
 * <p/>
 * Example usage:
 * <p/>
 * <pre>
 *
 * </pre>
 *
 * @author mdkr
 * @version Copyright (c) 2012 HAN University, All rights reserved.
 */
public interface HumanResourceService {
    void bookHoliday(Date startDate, Date endDate, String name);
}
