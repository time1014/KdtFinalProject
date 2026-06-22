package com.weple.cloud.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.weple.cloud.system.service.SignupApprovalUserVO;

@Mapper
public interface SignupApprovalMapper {

    // 같은 회사에 속한 승인 대기 회원만 조회합니다.
    List<SignupApprovalUserVO> selectPendingUsersByCompanyId(@Param("companyId") Long companyId);

    // 같은 회사의 승인 대기 회원만 활성 상태로 변경합니다.
    int approvePendingUser(@Param("companyId") Long companyId, @Param("userCode") String userCode);

    // 같은 회사의 승인 대기 회원만 삭제합니다.
    int cancelPendingUser(@Param("companyId") Long companyId, @Param("userCode") String userCode);
}
