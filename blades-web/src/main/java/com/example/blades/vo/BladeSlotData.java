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

    /**
     * 扇叶质量
     */
    private Integer wight;

    /**
     * 区域扇叶质量和
     */
    private Integer wightSum;

    public static BladeSlotData format(Slot slot) {
        Blade curBlade = slot.getBlade();
        String bladeName = curBlade.getName();
        int wValue = curBlade.getWValue();
        int zValue = curBlade.getZValue();
        int wight = curBlade.getWight();


        Slot nextSlot = slot.getNext();
        Blade nextBlade = nextSlot.getBlade();
        int wDistance = Math.abs(wValue - nextBlade.getWValue());
        int zDistance = Math.abs(zValue - nextBlade.getZValue());
        int wightSum = 0;
        if ((slot.getIndex() + 1) % 6 == 0) {
            int count = 6;
            Slot temp = slot;
            while (count > 0) {
                wightSum += temp.getBlade().getWight();
                temp = temp.getPrev();
                count--;
            }
        }

        BladeSlotData vo = new BladeSlotData();
        vo.setIndex(slot.getIndex() + 1);
        vo.setBladeName(bladeName);
        vo.setBending(wValue);
        vo.setVibration(zValue);
        vo.setBendingDistance(wDistance);
        vo.setVibrationDistance(zDistance);
        vo.setWight(wight);
        vo.setWightSum(wightSum);
        return vo;
    }

}
