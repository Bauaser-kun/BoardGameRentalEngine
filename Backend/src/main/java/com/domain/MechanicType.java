package com.domain;

public enum MechanicType {
    CARD ("Card game"),
    DICE ("Dice throwing"),
    TERITORY ("Teritory Control"),
    COOP ("Cooperation"),
    PUZZLE ("Puzzle Solving"),
    SINGLEPLAY ("Single player only");

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

    public static MechanicType valueOfOrDefault(String myType) {
        for(MechanicType type : MechanicType.class.getEnumConstants()) {
            if(type.toString().equals(myType)) {
                return type;
            }
        }
        throw new IllegalArgumentException("MechanicType not found");
    }
}
