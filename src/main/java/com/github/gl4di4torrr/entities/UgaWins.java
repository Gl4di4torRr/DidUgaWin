package com.github.gl4di4torrr.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by christopherbolton on 2/19/16.
 */
public class UgaWins {

    private final String didUgaWin;
    private final String score;
    private final String link;

    @JsonCreator
    public UgaWins(@JsonProperty("didUgaWin") String didUgaWin,
                   @JsonProperty("score") String score,
                   @JsonProperty("didUgaWin") String link){

        this.didUgaWin = didUgaWin;
        this.link = link;
        this.score = score;
    }

    public String getDidUgaWin() {
        return didUgaWin;
    }

    public String getScore() {
        return score;
    }

    public String getLink() {
        return link;
    }
}
