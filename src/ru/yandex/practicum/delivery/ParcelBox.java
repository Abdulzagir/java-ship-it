package ru.yandex.practicum.delivery;

import java.util.ArrayList;
import java.util.List;

public class ParcelBox <T extends Parcel> {

    private List<T> parcels = new ArrayList<>();
    private int maxWeight;
    private int currentWeight = 0;

    public ParcelBox(int maxWeight) {
        this.maxWeight = maxWeight;

    }

    public void addParcel(T parcel) {


        if (currentWeight + parcel.getWeight() <= maxWeight) {
            parcels.add(parcel);
            currentWeight +=parcel.getWeight();
        } else {
            System.out.println("Посылка не помещается в коробку");
        }
    }

    public List<T> getAllParcels() {
        return parcels;
    }
}
