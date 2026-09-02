package ru.yandex.practicum.delivery;

public class StandartParcel extends Parcel {

    public StandartParcel(String description, int weight, String deliveryAddress, int sendDay) {
        super(description, weight, deliveryAddress, sendDay);
    }

    @Override
    protected int getBaseCost() {
        return 2;
    }

}
