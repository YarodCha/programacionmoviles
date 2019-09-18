package com.gamecodeschool.webservice.model;

public class User {
    private int id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String createdAt;
    private String updatedAt;

    public User(int id, String username, String password, String firstName, String lastName, String createdAt, String updatedAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String aTexto(){
        return "Username: "+this.username+"\n"+
                "Password "+this.password+"\n"+
                "First Name: "+this.firstName+"\n"+
                "Last Name: "+this.lastName;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }



    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}
