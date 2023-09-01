package com.example.mycarmanager;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {

    private String username, password, email, imgPath;
    private ArrayList<Car> garage = new ArrayList<>();
    protected static ArrayList<User> users = new ArrayList<>();

    public User(){

        this.setUsername("");
        this.setPassword("");
        this.setImgPath("");
        this.setEmail("");
    }

    public User(String username, String password, String email, String imgPath) {

        this.setUsername(username);
        this.setPassword(password);
        this.setEmail(email);
        this.setImgPath(imgPath);

        boolean check = false;

        for(int i = 0; i < users.size(); i++){

            if(users.get(i).getUsername().equals(username)) {
                check = true;
                break;
            }
        }

        if(!check)
            users.add(this);

        if(username.equals("user")){

            Car volkswagenPolo = new Car("Volkswagen", "Polo", "volkswagenPolo",
                    "GH236FF", "petrol", "sedan", "5", "1478",
                    "5", "abs", "5", "22", "103", "76",
                    "10.9", "98");

            Car bmwI3 = new Car("BMW", "I3", "bmwI3",
                    "GK211TR", "electric", "citycar", "5", "1345",
                    "5", "abs", "5", "12.9", "9.3", "170",
                    "7.1", "67");

            Car jeepCherokee = new Car("Jeep", "Cherokee", "jeepCherokee",
                    "GS011FA", "diesel", "suv", "5", "2097",
                    "5", "abs", "6", "15.7", "385", "380",
                    "6.3", "87");

            this.garage.add(volkswagenPolo);
            this.garage.add(bmwI3);
            this.garage.add(jeepCherokee);
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public ArrayList<Car> getGarage() {
        return garage;
    }
}