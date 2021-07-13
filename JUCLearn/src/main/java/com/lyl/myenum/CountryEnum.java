package com.lyl.myenum;

import lombok.Getter;

public enum CountryEnum {
    ONE(1,"齐"),TWO(2,"楚"),
    Three(3,"燕"),Four(4,"赵"),
    Five(5,"魏"),Six(6,"韩");

    @Getter private Integer retCode;
    @Getter private String retString;

     CountryEnum(Integer retCode, String retString) {
        this.retCode = retCode;
        this.retString = retString;
    }

    public static CountryEnum forEach_CountryEnum(int index){
        CountryEnum[] values = CountryEnum.values();
        for (CountryEnum value : values) {
            if (value.getRetCode()==index)
                return value;
        }
        return null;
    }
}
