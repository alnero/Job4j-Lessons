package alnero;

import java.util.Objects;

public class City {
    private int id;

    private String name;

    private int population;

    public City(int id, String name, int population) {
        this.id = id;
        this.name = name;
        this.population = population;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;
        City city = (City) o;
        return getId() == city.getId() && getPopulation() == city.getPopulation() && Objects.equals(getName(), city.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPopulation());
    }
}
