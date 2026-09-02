package ru.yandex.practicum;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.FragileParcel;
import ru.yandex.practicum.delivery.ParcelBox;
import ru.yandex.practicum.delivery.PerishableParcel;
import ru.yandex.practicum.delivery.StandartParcel;

public class DeliveryCostTest {

    @Test
    void shouldCalculateStandardParcelCost() {
        StandartParcel parcel = new StandartParcel("Ноутбук", 10,
                "Москва", 1);
        int actual = parcel.calculateDeliveryCost();
        Assertions.assertEquals(20, actual);
    }

    @Test
    void shouldCalculateFragileParcelCost() {
        FragileParcel parcel = new FragileParcel("Компьютер", 10,
                "Кубань", 30);
        int actual = parcel.calculateDeliveryCost();
        Assertions.assertEquals(40, actual);
    }
    @Test
    void shouldCalculatePerishableParcelCost() {
        PerishableParcel parcel = new PerishableParcel("Стекло", 10,
                "Нью-Йорк", 4, 5);
        int actual = parcel.calculateDeliveryCost();
        Assertions.assertEquals(30, actual);
    }

    @Test
    void shouldDetectExpiredParcelPosutive() {
        PerishableParcel parcel = new PerishableParcel("Молоко", 10,
                "Москва", 4, 5);

        boolean actual = parcel.isExpired(10);
        Assertions.assertTrue(actual);
    }

    @Test
    void shouldDetectExpiredParcelNegative() {
        PerishableParcel parcel = new PerishableParcel("Молоко", 10,
                "Москва", 4, 5);

        boolean actual = parcel.isExpired(8);
        Assertions.assertFalse(actual);
    }
    @Test
    void shouldAddParcelToBox() {
        ParcelBox<StandartParcel> box = new ParcelBox<>(100);

        StandartParcel parcel = new StandartParcel("Ноутбук", 20,
                "Москва", 1);
        box.addParcel(parcel);
        Assertions.assertEquals(1, box.getAllParcels().size());
    }

    @Test
    void shouldAddParcelWithMaxWeightToBox() {
        ParcelBox<StandartParcel> box  = new ParcelBox<>(100);
           StandartParcel parcel = new StandartParcel("Холодильник", 100,
                   "Москва", 1);
           box.addParcel(parcel);

           Assertions.assertEquals(1, box.getAllParcels().size());

    }

    @Test
    void shouldAddParcelWithMinWeightToBox() {
        ParcelBox<StandartParcel> box  = new ParcelBox<>(100);
        StandartParcel parcel1 = new StandartParcel("Холодильник", 80,
                "Москва", 1);
        StandartParcel parcel2 = new StandartParcel("Телевизор", 30,
                "Москва", 1);
        box.addParcel(parcel1);
        box.addParcel(parcel2);

        Assertions.assertEquals(1, box.getAllParcels().size());

    }

}


