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

        Car volkswagenPolo = new Car("volkswagen", "polo", "volkswagenPolo",
                "VERSIONE1", "diesel", "citycar", "5", "2345",
                "5", "abs", "5", "21.5", "145", "76",
                "8.9", "98");

        Car volkswagenPoloV2 = new Car("volkswagen", "polo", "volkswagenPolo",
                "VERSIONE2", "diesel", "citycar", "5", "2345",
                "5", "abs", "5", "21.5", "145", "76",
                "8.9", "98");

        this.garage.add(volkswagenPolo);
        this.garage.add(volkswagenPoloV2);
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

    public void setGarage(ArrayList<Car> garage) {
        this.garage = garage;
    }
}