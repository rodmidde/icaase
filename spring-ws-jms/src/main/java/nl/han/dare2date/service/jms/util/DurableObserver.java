package nl.han.dare2date.service.jms.util;

import java.util.Observer;

/**
 * Description for the class DurableObserver:
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
public abstract class DurableObserver implements Observer {
    public abstract String getSubscriberName();
}
