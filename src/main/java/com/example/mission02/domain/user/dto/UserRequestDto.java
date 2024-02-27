package com.example.mission02.domain.user.dto;

import com.example.mission02.domain.user.entity.GenderType;
import com.example.mission02.domain.user.entity.User;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserRequestDto {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CreateUserRequestDto {
        @NotBlank(message = "이름을 입력해주세요.")
        private String name;
        private GenderType gender;
        @NotBlank(message = "전화번호를 입력해주세요.")
        private String phone;
        @NotBlank(message = "주민번호를 입력해주세요.")
        private String identification;
        private String address;

        public User toEntity() {
            return User.builder()
                    .name(this.name)
                    .gender(this.gender)
                    .phone(this.phone)
                    .identification(this.identification)
                    .address(this.address)
                    .build();
        }
    }
}
