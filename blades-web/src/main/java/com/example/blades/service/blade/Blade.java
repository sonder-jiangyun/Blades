package com.example.blades.service.blade;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: 11102942
 * @Date: 2022/6/27
 * @Description:
 */
@Data
@NoArgsConstructor
public class Blade {

    private String name;
    private int wValue;
    private int zValue;
    private boolean used;

    public Blade(int wValue, int zValue) {
        this.wValue = wValue;
        this.zValue = zValue;
    }
}
