package com.jbj616.springboot.web.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class HelloResponseTest {

    @Test
    public void 롬복_기능_test(){
        String name = "test";
        int amount = 1000;

        HelloResponseDto dto = new HelloResponseDto(name, amount);

        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
    }
}
