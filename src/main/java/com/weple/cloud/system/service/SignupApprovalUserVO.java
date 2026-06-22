package com.weple.cloud.system.service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupApprovalUserVO {

    // 승인 처리에 사용할 사용자 고유 코드
    private String userCode;

    // 가입 요청을 보낸 사용자 정보
    private String loginId;
    private String userName;
    private String email;
    private String phoneNumber;

    // 사용자 코드에 포함된 회원가입 요청 일자
    private String requestedAt;
}
