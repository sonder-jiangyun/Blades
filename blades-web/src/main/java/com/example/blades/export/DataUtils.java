package com.example.blades.export;

import com.alibaba.excel.annotation.ExcelProperty;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DataUtils {

    static List<List<String>> generateHead(Class dataClass, String mainTitle) {
        return Arrays.stream(dataClass.getDeclaredFields())
                .map(field -> field.getAnnotation(ExcelProperty.class))
                .filter(Objects::nonNull)
                .map(ExcelProperty::value)
                .map(arr -> Arrays.asList(mainTitle, arr[0]))
                .collect(Collectors.toList());
    }
}
