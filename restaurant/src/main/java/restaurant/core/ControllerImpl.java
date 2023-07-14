package restaurant.core;

import restaurant.core.interfaces.Controller;
import restaurant.entities.drinks.Fresh;
import restaurant.entities.drinks.Smoothie;
import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.entities.healthyFoods.Salad;
import restaurant.entities.healthyFoods.VeganBiscuits;
import restaurant.entities.healthyFoods.interfaces.HealthyFood;
import restaurant.entities.tables.InGarden;
import restaurant.entities.tables.Indoors;
import restaurant.entities.tables.interfaces.Table;
import restaurant.repositories.interfaces.BeverageRepository;
import restaurant.repositories.interfaces.HealthFoodRepository;
import restaurant.repositories.interfaces.TableRepository;

import java.util.Collection;

import static restaurant.common.ExceptionMessages.*;
import static restaurant.common.OutputMessages.*;

public class ControllerImpl implements Controller {


    private final HealthFoodRepository<HealthyFood> healthFoodRepository;
    private final BeverageRepository<Beverages> beverageRepository;
    private final TableRepository<Table> tableRepository;
    private double sum;

    public ControllerImpl(HealthFoodRepository<HealthyFood> healthFoodRepository, BeverageRepository<Beverages> beverageRepository, TableRepository<Table> tableRepository) {
        this.healthFoodRepository = healthFoodRepository;
        this.beverageRepository = beverageRepository;
        this.tableRepository = tableRepository;
    }

    @Override
    public String addHealthyFood(String type, double price, String name) {
        HealthyFood food = type.equals("Salad")
                ? new Salad(name, price)
                : new VeganBiscuits(name, price);
        HealthyFood prevAddedFood = this.healthFoodRepository.foodByName(name);
        if (prevAddedFood == null) {
            this.healthFoodRepository.add(food);
            return String.format(FOOD_ADDED, name);
        }
        throw new IllegalArgumentException(String.format(FOOD_EXIST, name));
    }

    @Override
    public String addBeverage(String type, int counter, String brand, String name) {
        Beverages beverage = type.equals("Fresh") ? new Fresh(name, counter, brand)
                : new Smoothie(name, counter, brand);
        Beverages prevAddedBeverages = this.beverageRepository.beverageByName(type, brand);
        if (prevAddedBeverages == null) {
            this.beverageRepository.add(beverage);
            return String.format(BEVERAGE_ADDED, type, brand);
        }
        throw new IllegalArgumentException(String.format(BEVERAGE_EXIST, name));
    }

    @Override
    public String addTable(String type, int tableNumber, int capacity) {
        Table table = type.equals("Indoors") ? new Indoors(tableNumber, capacity)
                : new InGarden(tableNumber, capacity);
        Table prevAddedTable = this.tableRepository.byNumber(tableNumber);
        if (prevAddedTable == null) {
            this.tableRepository.add(table);
            return String.format(TABLE_ADDED, tableNumber);
        }
        throw new IllegalArgumentException(String.format(TABLE_IS_ALREADY_ADDED, tableNumber));
    }

    @Override
    public String reserve(int numberOfPeople) {
        Collection<Table> tables = this.tableRepository.getAllEntities();
        Table table = tables.stream().filter(t -> !t.isReservedTable() && t.getSize() >= numberOfPeople)
                .findFirst()
                .orElse(null);
        String message = String.format(RESERVATION_NOT_POSSIBLE, numberOfPeople);
        if (table != null) {
            table.reserve(numberOfPeople);
            message = String.format(TABLE_RESERVED, table.getTableNumber(), numberOfPeople);
        }
        return message;
    }

    @Override
    public String orderHealthyFood(int tableNumber, String healthyFoodName) {
        Table table = this.tableRepository.byNumber(tableNumber);
        String message;
        if (table == null) {
            return message = String.format(WRONG_TABLE_NUMBER, tableNumber);
        }
        HealthyFood food = this.healthFoodRepository.foodByName(healthyFoodName);
        if (food == null) {
            return message = String.format(NONE_EXISTENT_FOOD, healthyFoodName);
        }
        table.orderHealthy(food);
        return String.format(FOOD_ORDER_SUCCESSFUL, healthyFoodName, tableNumber);

    }

    @Override
    public String orderBeverage(int tableNumber, String name, String brand) {
        Table table = this.tableRepository.byNumber(tableNumber);
        String message;
        if (table == null) {
            return message = String.format(WRONG_TABLE_NUMBER, tableNumber);
        }
        Beverages beverage = this.beverageRepository.beverageByName(name, brand);
        if (beverage == null) {
            return message = String.format(NON_EXISTENT_DRINK, name, brand);
        }

        table.orderBeverages(beverage);
        return String.format(BEVERAGE_ORDER_SUCCESSFUL, name, tableNumber);
    }

    @Override
    public String closedBill(int tableNumber) {
        Table table = this.tableRepository.byNumber(tableNumber);
        double bill = table.bill();
        table.clear();
        sum += bill;
        return String.format(BILL, tableNumber, bill);
    }


    @Override
    public String totalMoney() {
        return String.format(TOTAL_MONEY, sum);
    }
}
