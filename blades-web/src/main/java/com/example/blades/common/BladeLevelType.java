package com.example.blades.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BladeLevelType {
    FIRST_LEVEL,
    SECOND_LEVEL;

    public static BladeLevelType parse(String bladeType) {
        for (BladeLevelType bladeLevelType : values()) {
            if (bladeLevelType.name().equals(bladeType)) {
                return bladeLevelType;
            }
        }

        return null;
    }
}
