package com.example.mycarmanager;

import java.util.ArrayList;

public class Car {

    private String brand, name, image, plate, fuelType, carType;
    private String doors, weight, seats, abs, gears, consumption, emissions, horsepower;
    private String acceleration, fuelOrEnergyLevel;
    protected ArrayList<Car> cars = new ArrayList<>();

    public Car(String brand, String name, String image, String plate,
               String fuelType, String carType, String doors, String weight,
               String seats, String abs, String gears, String consumption, String emissions,
               String horsepower, String acceleration, String fuelOrEnergyLevel) {

        this.brand = brand;
        this.name = name;
        this.image = image;
        this.plate = plate;
        this.fuelType = fuelType;
        this.carType = carType;
        this.doors = doors;
        this.weight = weight;
        this.seats = seats;
        this.abs = abs;
        this.gears = gears;
        this.consumption = consumption;
        this.emissions = emissions;
        this.horsepower = horsepower;
        this.acceleration = acceleration;
        this.fuelOrEnergyLevel = fuelOrEnergyLevel;

        boolean check = false;

        for(int i = 0; i < cars.size(); i++){

            if(cars.get(i).getPlate().equals(plate)) {
                check = true;
                break;
            }
        }

        if(!check)
            cars.add(this);
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getDoors() {
        return doors;
    }

    public void setDoors(String doors) {
        this.doors = doors;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public String getAbs() {
        return abs;
    }

    public void setAbs(String abs) {
        this.abs = abs;
    }

    public String getGears() {
        return gears;
    }

    public void setGears(String gears) {
        this.gears = gears;
    }

    public String getConsumption() {
        return consumption;
    }

    public void setConsumption(String consumption) {
        this.consumption = consumption;
    }

    public String getEmissions() {
        return emissions;
    }

    public void setEmissions(String emissions) {
        this.emissions = emissions;
    }

    public String getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(String horsepower) {
        this.horsepower = horsepower;
    }

    public String getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(String acceleration) {
        this.acceleration = acceleration;
    }

    public String getFuelOrEnergyLevel() {
        return fuelOrEnergyLevel;
    }

    public void setFuelOrEnergyLevel(String fuelOrEnergyLevel) {
        this.fuelOrEnergyLevel = fuelOrEnergyLevel;
    }
}