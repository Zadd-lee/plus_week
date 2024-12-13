package com.example.demo.util;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PasswordEncoderTest {

    @Test
    void matchesTrue() {
        //given
        String password = "a123";
        //when
        String encodedPassword = PasswordEncoder.encode(password);
        //then
        assertThat(PasswordEncoder.matches(password, encodedPassword)).isTrue();

    }

    //실패 케이스
    @Test
    void matchesFalse() {
        //given
        String password1 = "a123";
        String password2 = "b123";
        //when
        String encodedPassword1 = PasswordEncoder.encode(password1);
        String encodedPassword2 = PasswordEncoder.encode(password2);
        //then
        assertThat(PasswordEncoder.matches(password1, encodedPassword2)).isFalse();
    }

}