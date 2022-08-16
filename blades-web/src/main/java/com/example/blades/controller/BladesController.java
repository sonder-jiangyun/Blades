package com.example.blades.controller;

import com.example.blades.common.BladeLevelType;
import com.example.blades.help.BladeExcelExportHelper;
import com.example.blades.param.BladeSlotParam;
import com.example.blades.response.ApiResponse;
import com.example.blades.service.BladeDataParser;
import com.example.blades.service.BladeService;
import com.example.blades.service.blade.Blade;
import com.example.blades.service.blade.Slot;
import com.example.blades.vo.BladeSlotData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/blades")
public class BladesController {

    @Autowired
    private BladeDataParser bladeDataParser;

    @Autowired
    private BladeService bladeService;

    @PostMapping("/arrange")
    public ApiResponse<List<BladeSlotData>> arrange(MultipartFile multipartFile, String bladeType) {
        try {
            BladeLevelType bladeLevelType = BladeLevelType.parse(bladeType);
            if (Objects.isNull(bladeLevelType)) {
                throw new IllegalArgumentException("请输入合法的扇叶类型值！");
            }

            List<Blade> blades = bladeDataParser.parse(multipartFile);
            List<Slot> slots = bladeService.arrange(blades, bladeLevelType);
            for (Slot slot : slots) {
                slot.println();
            }
            List<BladeSlotData> vos = slots.stream().map(BladeSlotData::format).collect(Collectors.toList());
            return ApiResponse.success(vos);
        } catch (Exception e) {
            String msg = e.getMessage();
            return ApiResponse.error(msg);
        }
    }

    @PostMapping("/export")
    public ApiResponse export(@RequestBody BladeSlotParam bladeSlotParam, HttpServletResponse response) throws IOException {
        List<BladeSlotData> bladeSlotDataList = bladeSlotParam.getBladeSlotDataList();

        OutputStream outputStream = response.getOutputStream();
        BladeExcelExportHelper bladeExcelExportHelper = new BladeExcelExportHelper();
        bladeExcelExportHelper.doExport(bladeSlotDataList, outputStream);
        return ApiResponse.success(null);
    }
}
