package com.app.vple.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class NicknameUpdateDto {

    @ApiModelProperty(example = "바꿀 닉네임")
    @Pattern(regexp = "^[a-zA-Z0-9가-힣]{3,10}$", message = "닉네임은 3글자 이상 10글자 이하, 특수 문자를 허용하지 않습니다.")
    private String nickname;
}
