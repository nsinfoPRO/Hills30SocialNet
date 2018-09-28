/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hills30.controller;

import com.Hills30.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Nenad
 */
@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("users", userService.list());
        return "users/listOfUsers";
    }

    @RequestMapping("/list/{id}")
    public String byAuthor(@PathVariable(value = "id") Long id, Model model, Model model2, Model model3, Model model4) {
        model.addAttribute("user", userService.findUserbyID(id));
        model2.addAttribute("friends", userService.findUserFriendsById(id));
        model3.addAttribute("friendss", userService.findFriendsFriendsById(id));
        model4.addAttribute("sugfriends", userService.findSuggestedFriendsById(id));
        return "users/user";

    }

}
