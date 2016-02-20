package com.github.gl4di4torrr.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by christopherbolton on 2/19/16.
 */
@Service
public class ESPNScraper {

    public String lastSeasonGameOutcome() throws IOException {
        URL url = null;
        BufferedReader br = null;

        try {
            // get URL content

            String espnUGA = "http://espn.go.com/college-football/team/_/id/61/georgia-bulldogs";
            url = new URL(espnUGA);
            URLConnection conn = url.openConnection();

            // open the stream and put it into BufferedReader
            br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));

            String html = "";
            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                html += inputLine;
            }

            Document doc = Jsoup.parse(html);

            Elements listOfGamesPlayed =  doc.getElementsByClass("club-schedule")
                    .get(0).getElementsByTag("ul").select("li");

            for(int i = 0; i < listOfGamesPlayed.size(); i++){

                String gameOutcome = listOfGamesPlayed.get(i)
                        .getElementsByClass("game-result")
                        .text();

                if((i + 1) == listOfGamesPlayed.size()){
                    return gameOutcome;
                }
                else if((i + 1) <= listOfGamesPlayed.size() - 1){
                    String gameOutcomePlusOne = listOfGamesPlayed.get(i+1)
                            .getElementsByClass("game-result")
                            .text();

                    if(gameOutcomePlusOne.equals("W") || gameOutcomePlusOne.equals("L")){
                        continue;
                    }

                    return gameOutcome;
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
}
