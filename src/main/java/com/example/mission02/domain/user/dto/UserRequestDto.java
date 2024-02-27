package com.example.mission02.domain.user.dto;

import com.example.mission02.domain.user.entity.GenderType;
import com.example.mission02.domain.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserRequestDto {

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Getter
    public static class CreateUserRequestDto {

        @Schema(description = "이름", example = "홍길동")
        @NotBlank(message = "이름을 입력해주세요.")
        private String name;

        @Schema(description = "성별", example = "MALE")
        private GenderType gender;

        @Schema(description = "전화번호", example = "010-1111-2222")
        @NotBlank(message = "전화번호를 입력해주세요.")
        @Pattern(regexp = "\\d{3}-\\d{4}-\\d{4}", message = "올바르지 않은 전화번호입니다.")
        private String phone;

        @Schema(description = "주민번호", example = "000000-0000000")
        @NotBlank(message = "주민번호를 입력해주세요.")
        @Pattern(regexp = "\\d{6}-\\d{7}", message = "올바르지 않은 주민번호입니다.")
        private String identification;

        @Schema(description = "주소", example = "서울특별시")
        @NotBlank(message = "주소를 입력해주세요.")
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
