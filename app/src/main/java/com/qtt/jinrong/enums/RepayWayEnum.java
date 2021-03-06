package com.qtt.jinrong.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 还款方式
 * Created by yanxin on 16/3/4.
 */
public enum RepayWayEnum {

    等额本息(1),
    先息后本(2);

    private int code;
    RepayWayEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static List<String> getValues() {
        RepayWayEnum[] enums = RepayWayEnum.values();
        List<String> vals = new ArrayList<>();
        for(int i=0;i<enums.length;i++) {
            vals.add(enums[i].name());
        }
        return vals;
    }

    public static RepayWayEnum find(Integer code) {
        if(code == null) return null;
        RepayWayEnum[] enums = RepayWayEnum.values();
        for(int i=0;i<enums.length;i++) {
            if(enums[i].getCode() == code.intValue()) return enums[i];
        }
        return null;
    }

}
