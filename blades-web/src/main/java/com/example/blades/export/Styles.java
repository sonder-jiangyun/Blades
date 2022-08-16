package com.example.blades.export;

import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

public class Styles {

    static WriteCellStyle head() {
        return basic(12);
    }

    static WriteCellStyle content() {
        return basic(11);
    }

    private static WriteCellStyle basic(int fontHeight) {
        WriteCellStyle cellStyle = new WriteCellStyle();
        cellStyle.setWrapped(false);
        cellStyle.setFillPatternType(FillPatternType.NO_FILL);
        cellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        cellStyle.setWriteFont(Styles.font(fontHeight));
        return cellStyle;
    }

    private static WriteFont font(int fontHeight) {
        WriteFont font = new WriteFont();
        font.setFontName("Calibri");
        font.setFontHeightInPoints((short) fontHeight);
        return font;
    }
}
