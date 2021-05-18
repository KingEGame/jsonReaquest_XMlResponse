package com.cbk.kg.excersice.enums;

public enum PaymentStatusesEnum {
    CHECKED(1, "Проверено"),
    CONFIRMED(0, "Оплачено"),
    ERROR(-1, "ERROR");

    private final Integer Id;
    private final String name;

    PaymentStatusesEnum(Integer id, String name) {
        Id = id;
        this.name = name;
    }

    public Integer getId() {
        return Id;
    }

    public String getName() {
        return name;
    }
}
