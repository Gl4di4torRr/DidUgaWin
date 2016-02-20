package com.github.gl4di4torrr.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by christopherbolton on 2/19/16.
 */
public class UgaWins {

    private final String didUgaWin;

    @JsonCreator
    public UgaWins(@JsonProperty("didUgaWin") String didUgaWin){
        this.didUgaWin = didUgaWin;
    }

    public String getDidUgaWin() {
        return didUgaWin;
    }
}
