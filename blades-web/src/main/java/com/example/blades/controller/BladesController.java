package com.example.blades.controller;

import com.example.blades.common.BladeLevelType;
import com.example.blades.service.BladeDataParser;
import com.example.blades.service.BladeService;
import com.example.blades.service.blade.Blade;
import com.example.blades.service.blade.Slot;
import com.example.blades.vo.BladeSlotVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
    public List<BladeSlotVO> arrange(MultipartFile multipartFile, String bladeType) {
        List<Blade> blades = bladeDataParser.parse(multipartFile);
        BladeLevelType bladeLevelType = BladeLevelType.parse(bladeType);
        if (Objects.isNull(bladeLevelType)) {
            throw new IllegalArgumentException("请输入合法的扇叶类型值！");
        }

        List<Slot> slots = bladeService.arrange(blades, bladeLevelType);
        return slots.stream().map(BladeSlotVO::format).collect(Collectors.toList());
    }
}
