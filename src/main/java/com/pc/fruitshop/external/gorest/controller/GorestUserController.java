package com.pc.fruitshop.external.gorest.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pc.fruitshop.external.gorest.dto.UserDto;
import com.pc.fruitshop.external.gorest.service.GorestUserService;

@Controller
@RequestMapping("/gorest")
public class GorestUserController {

    private final GorestUserService gorestUserService;

    public GorestUserController(GorestUserService gorestUserService) {
        this.gorestUserService = gorestUserService;
    }

    @GetMapping("/users")
    public String showUsers(Model model) {

        List<UserDto> users = gorestUserService.fetchUsers();
        model.addAttribute("users", users);

        return "gorest-users";
    }
}
