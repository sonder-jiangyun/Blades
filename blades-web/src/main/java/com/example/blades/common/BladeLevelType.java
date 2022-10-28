package com.example.blades.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BladeLevelType {
    FIRST_LEVEL(24),
    SECOND_LEVEL(34);

    private Integer code;

    public static BladeLevelType parse(Integer code) {
        for (BladeLevelType bladeLevelType : values()) {
            if (bladeLevelType.getCode().equals(code)) {
                return bladeLevelType;
            }
        }

        return null;
    }
}
