package com.uga.wins.Utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.uga.wins.Exceptions.ESPNParserException;

public class ESPNScraper {
	
	public static String lastSeasonGameOutcome() throws ESPNParserException{
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
            throw new ESPNParserException(e.getMessage(), e);
        } 
        catch (IOException e) {
        	throw new ESPNParserException(e.getMessage(), e);
        }
        finally{
        	if(br!=null)try {br.close();} catch (IOException e) {e.printStackTrace();}
        }

	}
}
