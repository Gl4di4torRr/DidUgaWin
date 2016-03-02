package com.github.gl4di4torrr.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.github.gl4di4torrr.entities.UgaWins;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by christopherbolton on 2/19/16.
 */
@Service
public class ESPNScraper {
    //Football
    //private static final String espnUGA = "http://espn.go.com/college-football/team/schedule/_/id/61";
    // Basketball
    private static final String espnUGA = "http://espn.go.com/mens-college-basketball/team/schedule/_/id/61";
    private static final String espnScorePrefix = "http://espn.go.com";
    private URLConnection conn;
    private String html = "";
    private String inputLine;
    private String gameOutcome = "";
    private String score = "";
    private String link = "";
    private BufferedReader br = null;

    public UgaWins lastSeasonGameOutcome() throws IOException {
        try {
            conn = openConnection(espnUGA);
            Document doc = getDocumentFromConnection(conn);
            // open the stream and put it into BufferedReader

            Elements gamesPlayed = doc.select("#showschedule")
                    .get(0)
                    .getElementsByTag("table")
                    .get(0)
                    .getElementsByTag("tr");
            for (int i = gamesPlayed.size() -1; i >= 0; i--) {
                Element elem = gamesPlayed.get(i);
                Elements loss = elem.getElementsByClass("loss");
                Elements wins = elem.getElementsByClass("win");

                if(loss.size() > 0){
                    gameOutcome = "L";
                    Element scoreLinkObj = elem.getElementsByClass("score").get(0).getElementsByTag("a").get(0).getElementsByAttribute("href").get(0)
                            .getAllElements().get(0);
                    score = scoreLinkObj.childNode(0).toString();
                    link = scoreLinkObj.attr("href");

                    UgaWins ugaWinsObject = new UgaWins(gameOutcome, score, link);
                    return ugaWinsObject;
                } else if(wins.size() > 0){
                    gameOutcome = "W";
                    Element scoreLinkObj = elem.getElementsByClass("score").get(0).getElementsByTag("a").get(0).getElementsByAttribute("href").get(0)
                            .getAllElements().get(0);
                    score = scoreLinkObj.childNode(0).toString();
                    link = espnScorePrefix + scoreLinkObj.attr("href");

                    UgaWins ugaWinsObject = new UgaWins(gameOutcome, score, link);
                    return ugaWinsObject;
                }
            }
            return null;
        }
        catch (MalformedURLException e) {
            throw e;
        }
        catch (IOException e) {
            throw e;
        }
        finally{
            if(br!=null)try {br.close();} catch (IOException e) {e.printStackTrace();}
        }

    }

    private URLConnection openConnection(String urlToConnect) throws MalformedURLException, IOException {
        try {
            URL url = new URL(urlToConnect);
            URLConnection conn = url.openConnection();
            return conn;
        }
        catch (MalformedURLException e) {
            throw e;
        }
        catch (IOException e) {
            throw e;
        }
    }

    private Document getDocumentFromConnection(URLConnection conn) throws MalformedURLException, IOException {
        try {
            br = new BufferedReader(
                new InputStreamReader(conn.getInputStream()));

            while ((inputLine = br.readLine()) != null) {
                html += inputLine;
            }

            return Jsoup.parse(html);
        }
        catch (MalformedURLException e) {
            throw e;
        }
        catch (IOException e) {
            throw e;
        }
    }
}
