/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hills30.service;

import com.Hills30.domain.People;
import com.Hills30.repository.UserRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 *
 * @author Nenad
 */
@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Iterable<People> list() {
        return userRepository.findAll();
    }

    public Iterable<People> save(List<People> users) {
        return userRepository.saveAll(users);
    }

    public People findUserbyID(long id) {
        return userRepository.findById(id);
    }

    public List<People> findUserFriendsById(long id) {
        List<People> people = new ArrayList<>();;
        List<Integer> l = userRepository.findFriendsById(id);
        int size = l.size();

        while (size != 0) {
            people.add(userRepository.findById(l.get(size - 1)));
            size--;
        }
        return people;

    }

    public List<People> findFriendsFriendsById(long id) {
        List<People> people = new ArrayList<>();
        List<Integer> p = new ArrayList<>();
        List<Integer> l = userRepository.findFriendsById(id);
        int size = l.size();

        while (size != 0) {
            p.addAll(userRepository.findFriendsById(l.get(size - 1)));
            size--;
        }
        List<Integer> clearList = p.stream().distinct().collect(Collectors.toList());
        Integer intVal = ((Number) id).intValue();
        clearList.remove(intVal);

        size = l.size();
        while (size != 0) {
            if (clearList.contains(l.get(size - 1))) {
                clearList.remove(l.get(size - 1));
            }
            size--;
        }
        size = clearList.size();
        while (size != 0) {
            people.add(userRepository.findById(clearList.get(size - 1)));
            size--;
        }
        return people;
    }

    public List<People> findSuggestedFriendsById(long id) {
        List<People> people = new ArrayList<>();
        List<Integer> p = new ArrayList<>();
        List<Integer> p1 = new ArrayList<>();
        List<Integer> l = userRepository.findFriendsById(id);
        int frequency, size = l.size();

        while (size != 0) {
            p.addAll(userRepository.findFriendsById(l.get(size - 1)));
            size--;
        }
        size = l.size();
        while (size != 0) {
            if (p.contains(l.get(size - 1))) {
                p.remove(l.get(size - 1));
            }
            size--;
        }

        size = p.size();

        while (size != 0) {
            frequency = Collections.frequency(p, p.get(size - 1));
            if (frequency >= 2) {
                p1.add(p.get(size - 1));
            }
            size--;
        }

        List<Integer> clearList = p1.stream().distinct().collect(Collectors.toList());
        Integer intVal = ((Number) id).intValue();
        clearList.remove(intVal);
        size = clearList.size();

        while (size != 0) {
            people.add(userRepository.findById(clearList.get(size - 1)));
            size--;
        }
        return people;
    }
}
