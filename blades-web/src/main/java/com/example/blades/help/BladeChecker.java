package com.example.blades.help;

import com.example.blades.common.BladeLevelType;
import com.example.blades.service.blade.Blade;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class BladeChecker {

    public static boolean isValid(List<Blade> blades, BladeLevelType bladeType) {
        if (CollectionUtils.isEmpty(blades)) {
            return false;
        }

        blades.sort(Comparator.comparing(Blade::getWValue));
        Blade minBlade = Collections.min(blades, Comparator.comparing(Blade::getWValue));
        Blade maxBlade = Collections.max(blades, Comparator.comparing(Blade::getWValue));

        switch (bladeType) {
            case FIRST_LEVEL:
                return maxBlade.getWValue() - minBlade.getWValue() >= 24;
            case SECOND_LEVEL:
                return maxBlade.getWValue() - minBlade.getWValue() >= 20;
            default:
                return false;
        }
    }
}
