package nl.ead.webservice.services;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import java.net.URL;
import java.util.HashMap;

public class MoviePrinter implements IMoviePrinter {
    public void printMovieDetails(String searchTerm) {
        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        try {

            config.setServerURL(new URL("http://www.moviemeter.nl/ws"));
            XmlRpcClient client = new XmlRpcClient();
            client.setConfig(config);
            Object[] params = new Object[]{new String("6e3fjzydznzxv2q2qgntyu0g10crthmm")};
            HashMap results = (HashMap) client.execute("api.startSession", params);
            String sessionKey = (String) results.get("session_key");
            params = new Object[]{sessionKey, searchTerm};
            Object[] searchResponse = (Object[]) client.execute("film.search", params);
            for (int i = 0; i < searchResponse.length; i++) {
                HashMap film = (HashMap) searchResponse[i];
                System.out.println(film.get("title"));
                System.out.println(film.get("actors_text"));
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}