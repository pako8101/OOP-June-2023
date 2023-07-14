package restaurant.entities.tables;

import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.entities.healthyFoods.interfaces.HealthyFood;
import restaurant.entities.tables.interfaces.Table;

import java.util.ArrayList;
import java.util.Collection;

import static restaurant.common.ExceptionMessages.INVALID_NUMBER_OF_PEOPLE;
import static restaurant.common.ExceptionMessages.INVALID_TABLE_SIZE;

public abstract class BaseTable implements Table {
    private Collection<HealthyFood> healthyFoods;
    private Collection<Beverages> beverages;
    private int number;
    private int size;
    private int numberOfPeople;
    private double pricePerPerson;
    private boolean isReservedTable;
    private double allPeople;

    public BaseTable(int number, int size, double pricePerPerson) {
        this.number = number;
        this.size = size;
        this.pricePerPerson = pricePerPerson;
        this.healthyFoods = new ArrayList<>();
        this.beverages = new ArrayList<>();
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setSize(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException(INVALID_TABLE_SIZE);
        }
        this.size = size;
    }

    public void setPricePerPerson(double pricePerPerson) {
        this.pricePerPerson = pricePerPerson;
    }

    @Override
    public int getTableNumber() {
        return this.number;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public int numberOfPeople() {
        return this.numberOfPeople;
    }

    @Override
    public double pricePerPerson() {
        return this.pricePerPerson;
    }

    @Override
    public boolean isReservedTable() {

        return isReservedTable;
    }

    @Override
    public double allPeople() {
        return this.allPeople;
    }

    @Override
    public void reserve(int numberOfPeople) {
        if (numberOfPeople <= 0) {
            throw new IllegalArgumentException(INVALID_NUMBER_OF_PEOPLE);
        }
        this.numberOfPeople = numberOfPeople;
        this.isReservedTable = true;
    }

    @Override
    public void orderHealthy(HealthyFood food) {
        this.healthyFoods.add(food);
    }

    @Override
    public void orderBeverages(Beverages beverages) {
        this.beverages.add(beverages);
    }

    @Override
    public double bill() {
        double foodBill = this.healthyFoods.stream()
                .mapToDouble(HealthyFood::getPrice)
                .sum();
        double beveragesBill = this.beverages.stream()
                .mapToDouble(Beverages::getPrice)
                .sum();
        return foodBill + beveragesBill + (pricePerPerson * numberOfPeople);
    }

    @Override
    public void clear() {
        this.numberOfPeople = 0;
        this.isReservedTable = false;
        this.beverages.clear();
        this.healthyFoods.clear();

    }

    @Override
    public String tableInformation() {
       return String.format("Table - %d%n" +
               "Size - %d%n" +
               "Type - %s%n" +
               "All price - %.2f", this.number,this.size,this.getClass().getSimpleName(),pricePerPerson);

    }
}
