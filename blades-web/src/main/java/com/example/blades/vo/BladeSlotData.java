package com.example.blades.vo;

import com.example.blades.service.blade.Blade;
import com.example.blades.service.blade.Slot;
import lombok.Data;

@Data
public class BladeSlotData {

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
    private Integer bending;

    /**
     * 震动频率
     */
    private Integer vibration;

    /**
     * 弯曲频率差（相邻槽扇叶的差的绝对值）
     */
    private Integer bendingDistance;

    /**
     * 扭转频率差（相邻槽扇叶的差的绝对值）
     */
    private Integer vibrationDistance;

    public static BladeSlotData format(Slot slot) {
        Blade curBlade = slot.getBlade();
        String bladeName = curBlade.getName();
        int wValue = curBlade.getWValue();
        int zValue = curBlade.getZValue();


        Slot nextSlot = slot.getNext();
        Blade nextBlade = nextSlot.getBlade();
        Integer wDistance = Math.abs(wValue - nextBlade.getWValue());
        Integer zDistance = Math.abs(zValue - nextBlade.getZValue());

        BladeSlotData vo = new BladeSlotData();
        vo.setIndex(slot.getIndex() + 1);
        vo.setBladeName(bladeName);
        vo.setBending(wValue);
        vo.setVibration(zValue);
        vo.setBendingDistance(wDistance);
        vo.setVibrationDistance(zDistance);
        return vo;
    }

}
