package nl.han.dare2date.matchservice;

import nl.han.dare2date.matchservice.model.MatchResponse;
import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.builder.xml.Namespaces;
import org.apache.camel.component.twitter.TwitterConstants;
import org.apache.camel.converter.jaxb.JaxbDataFormat;

public class SocialMediaMatchRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        JaxbDataFormat jaxbMatchResponse = new JaxbDataFormat(MatchResponse.class.getPackage().getName());
        Namespaces ns = new Namespaces("mes", "http://www.han.nl/schemas/messages");

        from("spring-ws:rootqname:{http://www.han.nl/schemas/messages}MatchRequest?endpointMapping=#matchEndpointMapping")
        .setExchangePattern(ExchangePattern.InOut)
        .split(ns.xpath("//mes:user/*"), new SocialMediaMatchAggregrate())
            .parallelProcessing()
            .convertBodyTo(String.class)
            .choice()
                .when(body().contains("twitterName"))
                    .log("Twitter: ${body}")
                    .setHeader(TwitterConstants.TWITTER_KEYWORDS, ns.xpath("/mes:twitterName/text()", String.class))
                .to("twitter://search")
                .otherwise()
                .setHeader("CamelFacebook.userId", ns.xpath("/mes:facebookid/text()", String.class))
                .to("facebook://user")
            .end()
        .end()
        .marshal(jaxbMatchResponse);
    }
}
