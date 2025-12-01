package com.pc.fruitshop.external.gorest.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.pc.fruitshop.external.gorest.dto.GorestResponseDto;
import com.pc.fruitshop.external.gorest.dto.UserDto;

@Service
public class GorestUserService {

    private final RestClient restClient = RestClient.create();

    public List<UserDto> fetchUsers() {

        GorestResponseDto response = restClient.get()
                .uri("https://gorest.co.in/public-api/users")
                .retrieve()
                .body(GorestResponseDto.class);

        return response.getData(); // 回傳使用者列表
    }
}
