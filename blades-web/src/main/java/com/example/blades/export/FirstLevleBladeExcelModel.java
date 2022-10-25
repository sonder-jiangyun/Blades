package com.example.blades.export;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;

@Data
public class FirstLevleBladeExcelModel {

    @ColumnWidth(15)
    @ExcelProperty("序号")
    private String index;

    @ColumnWidth(15)
    @ExcelProperty("扇叶炉号编码")
    private String bladeName;

    @ColumnWidth(15)
    @ExcelProperty("弯曲频率")
    private Integer bending;

    @ColumnWidth(15)
    @ExcelProperty("弯曲频率差值")
    private Integer bendingDistance;

    @ColumnWidth(15)
    @ExcelProperty("扭转频率")
    private Integer vibration;

    @ColumnWidth(15)
    @ExcelProperty("扭转频率差值")
    private Integer vibrationDistance;

    @ColumnWidth(15)
    @ExcelProperty("扇叶质量")
    private Integer wight;

    @ColumnWidth(15)
    @ExcelProperty("区域扇叶质量和")
    private Integer wightSum;

}
