package com.example.mission02.domain.user.dto;

import com.example.mission02.domain.user.entity.GenderType;
import com.example.mission02.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserResponseDto {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateUserResponseDto {
        private String name;
        private GenderType gender;
        private String phone;
        private String address;

        public CreateUserResponseDto(User user) {
            this.name = user.getName();
            this.gender = user.getGender();
            this.phone = user.getPhone();
            this.address = user.getAddress();
        }

    }
}
