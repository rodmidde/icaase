package nl.han.dare2date.service.web.applyregistration;

import org.apache.camel.Exchange;

/**
 * Description for the class TwitterAggregator:
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
public class TwitterAggregator implements org.apache.camel.processor.aggregate.AggregationStrategy {
    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        String newBody = newExchange.getIn().getBody(String.class);
        String oldBody = "";
        if (oldExchange != null) {
            oldBody = oldExchange.getIn().getBody(String.class);
        }
        newExchange.getIn().setBody(oldBody + newBody);
        return newExchange;
    }
}
