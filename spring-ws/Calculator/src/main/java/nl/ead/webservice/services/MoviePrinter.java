package nl.ead.webservice.services;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class MoviePrinter implements IMoviePrinter {
    public void printMovieDetails(String searchTerm) {
        try {
            HttpResponse<JsonNode> jsonResponse = Unirest.post("http://www.moviemeter.nl/api/film/")
                    .header("accept", "application/json")
                    .queryString("api_key", "6e3fjzydznzxv2q2qgntyu0g10crthmm")
                    .queryString("q", searchTerm)
                    .asJson();
            System.out.println(jsonResponse.getBody().toString());
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }
}