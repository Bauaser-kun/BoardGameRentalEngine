package com.domain;

public enum MechanicType {
    CARD ("Card game"), DICE ("Dice throwing"), TERITORY ("Teritory Control"), SINGLEPLAY ("Single player only");

    private String value;

    MechanicType(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }
}
