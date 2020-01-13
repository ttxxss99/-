package com.bishe.constant;

public enum WorkAge {
    lessOneYear(0, 500),

    OneToThreeYear(1, 2000),

    ThreeToFiveYear(2, 5000),

    ThanFiveYear(3, 10000);


    private int code;
    private int money;

    WorkAge(int code, int money) {
        this.code = code;
        this.money = money;
    }
}
