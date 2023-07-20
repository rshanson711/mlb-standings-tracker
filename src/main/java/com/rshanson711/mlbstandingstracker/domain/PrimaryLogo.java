package com.rshanson711.mlbstandingstracker.domain;

public enum PrimaryLogo {
    ARIZONA_DIAMONDBACKS("/static/images/arizona_diamondbacks_logo_primary_20123733.png");

    private final String filepath;

    PrimaryLogo(String path) {
        this.filepath = path;
    }
}
