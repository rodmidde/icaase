package nl.han.dare2date.matchservice;

import facebook4j.User;
import nl.han.dare2date.matchservice.model.MatchResponse;
import nl.han.dare2date.matchservice.model.MatchResult;
import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import twitter4j.Status;

import java.math.BigInteger;
import java.util.ArrayList;

public class SocialMediaMatchAggregrate implements AggregationStrategy {
    private int match = 0;

    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        if (oldExchange == null) {
            return newExchange;
        }

        increateMatchValueForFacebookUser(oldExchange, newExchange);
        increaseMatchForTwitterStatus(oldExchange, newExchange);

        return oldExchange;
    }

    private void increaseMatchForTwitterStatus(Exchange oldExchange, Exchange newExchange) {
        ArrayList<Status> statuses = newExchange.getIn().getBody(ArrayList.class);
        if (statuses != null) {
            for (Status status : statuses) {
                match += status.getFavoriteCount();
            }
            MatchResult matchResult = new MatchResult();
            matchResult.setNumber(BigInteger.valueOf(match));
            MatchResponse matchResponse = new MatchResponse();
            matchResponse.setMatchResult(matchResult);
            oldExchange.getIn().setBody(matchResponse);
        }
    }

    private void increateMatchValueForFacebookUser(Exchange oldExchange, Exchange newExchange) {
        User user = newExchange.getIn().getBody(User.class);
        if (user != null) {
            match += user.getName().length();
            MatchResult matchResult = new MatchResult();
            matchResult.setNumber(BigInteger.valueOf(match));
            MatchResponse matchResponse = new MatchResponse();
            matchResponse.setMatchResult(matchResult);
            oldExchange.getIn().setBody(matchResponse);
        }
    }
}
