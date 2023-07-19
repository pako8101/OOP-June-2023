package christmasPastryShop.core;

import christmasPastryShop.core.interfaces.Controller;
import christmasPastryShop.entities.booths.OpenBooth;
import christmasPastryShop.entities.booths.PrivateBooth;
import christmasPastryShop.entities.booths.interfaces.Booth;
import christmasPastryShop.entities.cocktails.Hibernation;
import christmasPastryShop.entities.cocktails.MulledWine;
import christmasPastryShop.entities.cocktails.interfaces.Cocktail;
import christmasPastryShop.entities.delicacies.Gingerbread;
import christmasPastryShop.entities.delicacies.interfaces.Delicacy;
import christmasPastryShop.repositories.interfaces.BoothRepository;
import christmasPastryShop.repositories.interfaces.CocktailRepository;
import christmasPastryShop.repositories.interfaces.DelicacyRepository;

import java.util.Collection;

import static christmasPastryShop.common.ExceptionMessages.BOOTH_EXIST;
import static christmasPastryShop.common.ExceptionMessages.FOOD_OR_DRINK_EXIST;
import static christmasPastryShop.common.OutputMessages.*;

public class ControllerImpl implements Controller {
    private final DelicacyRepository<Delicacy> delicacyRepository;
    private final CocktailRepository<Cocktail> cocktailRepository;
    private final BoothRepository<Booth> boothRepository;
    private static double sum;

    public ControllerImpl(DelicacyRepository<Delicacy> delicacyRepository,
                          CocktailRepository<Cocktail> cocktailRepository,
                          BoothRepository<Booth> boothRepository) {

        this.delicacyRepository = delicacyRepository;
        this.cocktailRepository = cocktailRepository;
        this.boothRepository = boothRepository;
    }


    @Override
    public String addDelicacy(String type, String name, double price) {
        Delicacy delicacy;
        if (type.equals("Gingerbread")) {
            delicacy = new Gingerbread(name, price);
        } else if (type.equals("Stolen")) {
            delicacy = new Gingerbread(name, price);
        } else {
            throw new IllegalArgumentException(String.
                    format(FOOD_OR_DRINK_EXIST, type, name));
        }
        this.delicacyRepository.add(delicacy);
        return String.format(DELICACY_ADDED, name, type);
    }

    @Override
    public String addCocktail(String type, String name, int size, String brand) {
        Cocktail cocktail = type.equals("Hibernation")
                ? new Hibernation(name, size, brand)
                : new MulledWine(name, size, brand);
        Cocktail prevAddedCocktail = this.cocktailRepository.getByName(name);
        if (prevAddedCocktail==null){
            this.cocktailRepository.add(cocktail);
            return String.format(COCKTAIL_ADDED, name, brand);
//        if (type.equals("Hibernation")) {
//            cocktail = new Hibernation(name, size, brand);
//        } else if (type.equals("MulledWine")) {
//            cocktail = new MulledWine(name, size, brand);
//        } else {
        }
            throw new IllegalArgumentException(String.format(FOOD_OR_DRINK_EXIST, type, name));

    }

    @Override
    public String addBooth(String type, int boothNumber, int capacity) {
        Booth booth = type.equals("OpenBooth")? new OpenBooth(boothNumber,capacity)
                : new PrivateBooth(boothNumber,capacity);
        Booth prevAddedBooth = boothRepository.getByNumber(boothNumber);
        if (prevAddedBooth==null) {
            this.boothRepository.add(booth);
            return String.format(BOOTH_ADDED, boothNumber);
//        if (type.equals("OpenBooth")) {
//            booth = new OpenBooth(boothNumber, capacity);
//        } else if (type.equals("PrivateBooth")) {
//            booth = new PrivateBooth(boothNumber, capacity);
//        } else {
        }
            throw new IllegalArgumentException(String.format(BOOTH_EXIST, boothNumber));
        }


    @Override
    public String reserveBooth(int numberOfPeople) {
        Collection<Booth> booths = this.boothRepository.getAll();
        Booth booth = booths.stream().filter(b -> !b.isReserved() && b.getCapacity() >= numberOfPeople)
                .findFirst()
                .orElse(null);
        if (booth != null) {
            booth.reserve(numberOfPeople);
            return String.format(BOOTH_RESERVED, booth.getBoothNumber(), numberOfPeople);
        }
        return String.format(RESERVATION_NOT_POSSIBLE, numberOfPeople);
    }

    @Override
    public String leaveBooth(int boothNumber) {
        Booth booth = this.boothRepository.getByNumber(boothNumber);
        double bill = booth.getBill();
        booth.clear();
        sum += bill;


        return String.format(BILL,boothNumber,bill);
    }

    @Override
    public String getIncome() {

        return String.format(TOTAL_INCOME,sum);
    }
}
