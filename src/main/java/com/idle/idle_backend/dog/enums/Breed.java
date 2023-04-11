package com.idle.idle_backend.dog.enums;

public enum Breed implements EnumModel {

    Maltese("말티즈"),
    Pomeranian("포메라니안"),
    Poodle("푸들"),
    Welshcorgi("웰시코기"),
    Beagle("비글"),
    Retriever("리트리버"),
    Samoyed("사모예드"),
    Shihtzu("시츄"),
    Chihuahua("치와와"),
    Mixdog("믹스견");

    private String value;

    Breed(String value) {
        this.value = value;
    }

    @Override
    public String getKey() {
        return name();
    }

    @Override
    public String getValue() {
        return value;
    }
}
