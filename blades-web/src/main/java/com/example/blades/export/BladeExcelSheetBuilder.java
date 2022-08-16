package com.example.blades.export;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class BladeExcelSheetBuilder {

    private String name;
    private List<SheetPart> parts = new ArrayList<>();

    public BladeExcelSheetBuilder(String name) {
        this.name = name;
    }

    public <T> BladeExcelSheetBuilder addList(String title, Class<T> dataClass, List<T> dataList) {
        parts.add(new SheetPart(title, dataClass, dataList));
        return this;
    }

    @Data
    @AllArgsConstructor
    static class SheetPart {
        private String title;
        private Class<?> dataClass;
        private List<?> dataList;
    }

}
