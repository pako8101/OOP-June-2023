package catHouse.core;

import catHouse.common.ConstantMessages;
import catHouse.common.ExceptionMessages;
import catHouse.entities.cat.Cat;
import catHouse.entities.cat.LonghairCat;
import catHouse.entities.cat.ShorthairCat;
import catHouse.entities.houses.House;
import catHouse.entities.houses.LongHouse;
import catHouse.entities.houses.ShortHouse;
import catHouse.entities.toys.Ball;
import catHouse.entities.toys.Mouse;
import catHouse.entities.toys.Toy;
import catHouse.repositories.ToyRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    private ToyRepository toys;
    private Collection<House> houses;

    public ControllerImpl() {
        this.toys = new ToyRepository();
        this.houses = new ArrayList<>();
    }

    @Override
    public String addHouse(String type, String name) {
        House house;
        if (type.equals(ShortHouse.class.getSimpleName()))
            house = new ShortHouse(name);
        else if (type.equals(LongHouse.class.getSimpleName())) {
            house = new LongHouse(name);
        } else {
            throw new NullPointerException(ExceptionMessages.INVALID_HOUSE_TYPE);
        }
//        switch (type) {
//            case "ShortHouse":
//                house = new ShortHouse(name);
//                break;
//            case "LongHouse":
//                house = new LongHouse(name);
//                break;
//            default:
//                throw new NullPointerException(ExceptionMessages.INVALID_HOUSE_TYPE);
//        }
        this.houses.add(house);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_HOUSE_TYPE, type);
    }

    @Override
    public String buyToy(String type) {
        Toy toy;
//        if (type.equals("Ball")) {
//            toy = new Ball();
//        } else if (type.equals("Mouse")) {
//            toy = new Mouse();
        if (type.equals(Ball.class.getSimpleName())) {
            toy = new Ball();
        } else if (type.equals(Mouse.class.getSimpleName())) {
            toy = new Mouse();
        } else {
            throw new NullPointerException(ExceptionMessages.INVALID_TOY_TYPE);
        }
        this.toys.buyToy(toy);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_TOY_TYPE, type);
    }

    @Override
    public String toyForHouse(String houseName, String toyType) {
        Toy toy = this.toys.findFirst(toyType);
        if (toy == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NO_TOY_FOUND, toyType));
        }
        House house = getHouseName(houseName);
        house.buyToy(toy);
        this.toys.removeToy(toy);


        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_TOY_IN_HOUSE, toyType, houseName);
    }


    private House getHouseName(String houseName) {
        return this.houses.stream()
                .filter(house -> house.getName().equals(houseName))
                .findFirst()
                .orElse(null);
    }


    @Override
    public String addCat(String houseName, String catType, String catName, String catBreed, double price) {
        Cat cat;
        switch (catType) {
            case "ShorthairCat"://catType.equals(ShorthairCat.class.getSimpleName())
                cat = new ShorthairCat(catName, catBreed, price);
                break;
            case "LonghairCat":
                cat = new LonghairCat(catName, catBreed, price);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_CAT_TYPE);
        }
        House house = getHouseName(houseName);

//        boolean checkShort = catType.startsWith("Short") &&
//                house.getClass().getSimpleName().startsWith("Short");
//        boolean checkLong = catType.startsWith("Long") &&
//                house.getClass().getSimpleName().startsWith("Long");
        boolean checkShort = catType.equals(ShorthairCat.class.getSimpleName()) &&
                house.getClass().getSimpleName().equals(ShortHouse.class.getSimpleName());
        boolean checkLong = catType.equals(LonghairCat.class.getSimpleName()) &&
                house.getClass().getSimpleName().equals(LongHouse.class.getSimpleName());
        if (checkShort || checkLong) {
            house.addCat(cat);

        } else {
            return ConstantMessages.UNSUITABLE_HOUSE;
        }
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_CAT_IN_HOUSE, catType, houseName);
    }

    @Override
    public String feedingCat(String houseName) {
        House house = getHouseName(houseName);
        house.feeding();

        return String.format(ConstantMessages.FEEDING_CAT, house.getCats().size());
    }

    @Override
    public String sumOfAll(String houseName) {
        House house = getHouseName(houseName);
        double priceCats = house.getCats().stream().mapToDouble(Cat::getPrice).sum();
        double priceToys = house.getToys().stream().mapToDouble(Toy::getPrice).sum();
        double priceAll = priceCats + priceToys;

        return String.format(ConstantMessages.VALUE_HOUSE, houseName, priceAll);
    }

    @Override
    public String getStatistics() {
        return this.houses.stream().map(House::getStatistics)
                .collect(Collectors.joining(System.lineSeparator())).trim();
//        StringBuilder sb = new StringBuilder();
//        for (House house : this.houses) {
//           sb.append(house.getStatistics()).append(System.lineSeparator());
//        }
//        return sb.toString().trim();
    }
}
