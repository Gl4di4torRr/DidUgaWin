package com.github.gl4di4torrr.controllers;

import com.github.gl4di4torrr.entities.UgaWins;
import com.github.gl4di4torrr.utils.ESPNScraper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


/**
 * Created by christopherbolton on 2/19/16.
 */
@RestController
@RequestMapping("/uga")
public class UGAController {

    @Autowired
    ESPNScraper espnScraper;

    @RequestMapping(path = "/wins", method = RequestMethod.GET)
    public UgaWins getWins() throws IOException {
        String gameOutCome = espnScraper.lastSeasonGameOutcome();
        String score = "L 55-7";
        String link = "http://espn.go.com/ncb/recap?gameId=400839775&version=mobile&teamId=150";
        return new UgaWins(gameOutCome, score, link);
    }
}
