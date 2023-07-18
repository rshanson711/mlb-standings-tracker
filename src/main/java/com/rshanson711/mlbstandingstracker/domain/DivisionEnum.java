package com.rshanson711.mlbstandingstracker.domain;

public enum DivisionEnum {
    AL_EAST("AL East"),
    AL_CENTRAL("AL Central"),
    AL_WEST("AL East"),
    NL_EAST("NL East"),
    NL_CENTRAL("NL Central"),
    NL_WEST("NL West");
    private final String name;

    DivisionEnum(String s){
        this.name = s;
    }

    public String getName() {
        return this.name;
    }
}
