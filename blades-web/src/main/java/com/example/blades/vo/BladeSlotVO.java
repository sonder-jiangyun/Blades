package com.example.blades.vo;

import com.example.blades.service.blade.Blade;
import com.example.blades.service.blade.Slot;
import lombok.Data;

@Data
public class BladeSlotVO {

    /**
     * 扇叶槽编号
     */
    private Integer index;

    /**
     * 扇叶炉号名称
     */
    private String bladeName;

    /**
     * 弯曲频率
     */
    private Integer wValue;

    /**
     * 震动频率
     */
    private Integer zValue;

    /**
     * 弯曲频率差（相邻槽扇叶的差的绝对值）
     */
    private Integer wDistance;

    /**
     * 扭转频率差（相邻槽扇叶的差的绝对值）
     */
    private Integer zDistance;

    public static BladeSlotVO format(Slot slot) {
        Blade curBlade = slot.getBlade();
        String bladeName = curBlade.getName();
        int wValue = curBlade.getWValue();
        int zValue = curBlade.getZValue();


        Slot nextSlot = slot.getNext();
        Blade nextBlade = nextSlot.getBlade();
        Integer wDistance = Math.abs(wValue - nextBlade.getWValue());
        Integer zDistance = Math.abs(zValue - nextBlade.getZValue());

        BladeSlotVO vo = new BladeSlotVO();
        vo.setIndex(slot.getIndex() + 1);
        vo.setBladeName(bladeName);
        vo.setWValue(wValue);
        vo.setZValue(zValue);
        vo.setWDistance(wDistance);
        vo.setZDistance(zDistance);
        return vo;
    }

}
