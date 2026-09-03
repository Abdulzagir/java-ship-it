package ru.yandex.practicum.delivery;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeliveryApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static List<Parcel> allParcels = new ArrayList<>();
    private static List<Trackable> trackableParcels = new ArrayList<>();
    private static ParcelBox<StandartParcel> standartBox = new ParcelBox<>(100);
    private static ParcelBox<FragileParcel> fragileBox = new ParcelBox<>(100);
    private static ParcelBox<PerishableParcel> perishableBox = new ParcelBox<>(100);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addParcel();
                    break;
                case 2:
                    sendParcels();
                    break;
                case 3:
                    calculateCosts();
                    break;
                case 4:
                    System.out.println("Введите новое местоположение: ");
                    String newLocation = scanner.nextLine();

                    for (Trackable trackable : trackableParcels) {
                        trackable.reportStatus(newLocation);
                    }
                    break;
                case 5:
                   showBoxContents();
                   break;


                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 — Добавить посылку");
        System.out.println("2 — Отправить все посылки");
        System.out.println("3 — Посчитать стоимость доставки");
        System.out.println("4 — Отследить хрупкие посылки");
        System.out.println("5 — Показать посылки в коробке");
        System.out.println("0 — Завершить");
    }

    // реализуйте методы ниже

    private static void addParcel() {
        System.out.println("Выберите тип посылки:");
        System.out.println("1.Стандартная");
        System.out.println("2.Хрупкая");
        System.out.println("3.Скоропортящаяся");


        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1: {
                System.out.println("Выбрана стандартная посылка");
                System.out.println("Введите описание посылки: ");
                String description = scanner.nextLine();
                System.out.println("Введите вес: ");
                int weight = Integer.parseInt(scanner.nextLine());
                System.out.println("Введите адрес доставки: ");
                String deliveryAddress = scanner.nextLine();
                System.out.println("Введите день отправки: ");
                int sendDay = Integer.parseInt(scanner.nextLine());
                StandartParcel parcel = new StandartParcel(description, weight,
                        deliveryAddress, sendDay);
                allParcels.add(parcel);
                standartBox.addParcel(parcel);

                break;
            }

            case 2: {
                System.out.println("Выбрана хрупкая посылка");
                System.out.println("Введите описание посылки: ");
                String description = scanner.nextLine();
                System.out.println("Введите вес: ");
                int weight = Integer.parseInt(scanner.nextLine());
                System.out.println("Введите адрес доставки: ");
                String deliveryAddress = scanner.nextLine();
                System.out.println("Введите день отправки: ");
                int sendDay = Integer.parseInt(scanner.nextLine());
                FragileParcel fragileParcel = new FragileParcel(description, weight,
                        deliveryAddress, sendDay);
                allParcels.add(fragileParcel);
                trackableParcels.add(fragileParcel);
                fragileBox.addParcel(fragileParcel);

                break;
            }
            case 3: {
                System.out.println("Выбрана скоропортящаяся посылка");
                System.out.println("Введите описание посылки: ");
                String description = scanner.nextLine();
                System.out.println("Введите вес: ");
                int weight = Integer.parseInt(scanner.nextLine());
                System.out.println("Введите адрес доставки: ");
                String deliveryAddress = scanner.nextLine();
                System.out.println("Введите день отправки: ");
                int sendDay = Integer.parseInt(scanner.nextLine());
                System.out.println("Введите срок хранения в днях: ");
                int timeToLive = Integer.parseInt(scanner.nextLine());
                PerishableParcel perishableParcel = new PerishableParcel(description, weight,
                        deliveryAddress, sendDay, timeToLive);
                allParcels.add(perishableParcel);
                perishableBox.addParcel(perishableParcel);
                break;
            }


            default:
                System.out.println("Неверный тип посылки");

                // Подсказка: спросите тип посылки и необходимые поля, создайте объект и добавьте в allParcels
        }
    }

    public static void showBoxContents() {
        System.out.println("Введите тип коробки: ");
        System.out.println("1.Стандартная");
        System.out.println("2.Хрупкая");
        System.out.println("3.Скоропортящаяся");

        int choiceBox = Integer.parseInt(scanner.nextLine());


        switch (choiceBox) {
            case 1:
                for (StandartParcel parcel : standartBox.getAllParcels()) {
                    System.out.println(parcel.getDescription());
                }
                break;
            case 2:
                for (FragileParcel parcel : fragileBox.getAllParcels()) {
                    System.out.println(parcel.getDescription());
                }
                break;
            case 3:
                for (PerishableParcel parcel : perishableBox.getAllParcels()) {
                    System.out.println(parcel.getDescription());

                }
                break;
            default:
                System.out.println("Неверный тип коробки");

        }

    }

    private static void sendParcels() {
        for (Parcel parcel : allParcels) {
            parcel.packageItem();
            parcel.deliver();// Пройти по allParcels, вызвать packageItem() и deliver()
        }
    }

    private static void calculateCosts() {
        int totalCost = 0;
        for (Parcel parcel : allParcels) {
            totalCost += parcel.calculateDeliveryCost();// Посчитать общую стоимость всех доставок и вывести на экран
        }
        System.out.println("Общая стоимость доставки: " + totalCost);
    }

}

