package Google;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private Company company;
    private Car car;
    private List<Parents> parents;
    private List<Kid>kids;
    private  List<Pokemon>pokemons;
public Person(){
    this.parents = new ArrayList<>();
    this.kids = new ArrayList<>();
    this.pokemons = new ArrayList<>();
}
    public Person(Company company, Car car, List<Parents> parents, List<Kid> kids, List<Pokemon> pokemons) {
        this.company = company;
        this.car = car;
        this.parents = parents;
        this.kids = kids;
        this.pokemons = pokemons;
    }

    public void setCompany(Company company) {
    this.company = company;
    }

    public List<Pokemon> getPokemons() {
    return this.pokemons;
    }

    public List<Parents> getParents() {
    return this.parents;
    }

    public List<Kid> getKids() {
    return this.kids;
    }

    public void setCars(Car car) {
    this.car = car;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Company:").append(System.lineSeparator());
        if (company!= null){
            builder.append(company).append(System.lineSeparator());
        }
        builder.append("Car:").append(System.lineSeparator());
        if (car != null){
            builder.append(car).append(System.lineSeparator());
        }
        builder.append("Pokemon:").append(System.lineSeparator());
        for (Pokemon pokemon : pokemons) {
            builder.append(pokemon).append(System.lineSeparator());
        }
        builder.append("Parents:").append(System.lineSeparator());
        for (Parents parent : parents) {
            builder.append(parent).append(System.lineSeparator());
        }
        builder.append("Children:").append(System.lineSeparator());
        for (Kid kid : kids) {
            builder.append(kid).append(System.lineSeparator());

        }return builder.toString();
    }
}
