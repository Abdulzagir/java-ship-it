package ru.yandex.practicum.delivery;

import java.util.ArrayList;
import java.util.List;

public class ParcelBox <T extends Parcel>{

    private List<T> parcels = new ArrayList<>();
    private int maxWeight;

    public ParcelBox(int maxWeight) {
        this.maxWeight = maxWeight;

    }

    public void addParcel(T parcel) {
        int currentWeight = 0;
        for(T p : parcels) {
            currentWeight += p.getWeight();
        }

        if (currentWeight + parcel.getWeight() <= maxWeight) {
            parcels.add(parcel);
        } else {
            System.out.println("Посылка не помещается в коробку");
        }
    }

    public List<T> getAllParcels() {
        return parcels;
    }
}
