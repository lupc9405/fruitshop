package com.pc.fruitshop.external.gorest.dto;

import java.util.List;

public class GorestResponseDto {

    private Integer code;
    private List<UserDto> data;

    // Getter & Setter
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<UserDto> getData() {
        return data;
    }

    public void setData(List<UserDto> data) {
        this.data = data;
    }
}
