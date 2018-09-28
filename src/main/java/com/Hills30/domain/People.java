/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hills30.domain;

import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author Nenad
 */
@Data
@AllArgsConstructor
@Entity
public class People {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String firstName;
    private String surname;
    private int age;
    private String gender;

    @ElementCollection
    private List<Integer> friends;

    public People() {
    }

    public People(String firstNAME, String surname, int age, String gender, List<Integer> frends) {
        this.firstName = firstNAME;
        this.surname = surname;
        this.age = age;
        this.gender = gender;
        this.friends = frends;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<Integer> getFriends() {
        return friends;
    }

    public void setFriends(List<Integer> friends) {
        this.friends = friends;
    }

    @Override
    public String toString() {
        return "People{" + "firstNAME=" + firstName + ", surname=" + surname + ", age=" + age + ", gender=" + gender + ", frends=" + friends + '}';
    }

}
