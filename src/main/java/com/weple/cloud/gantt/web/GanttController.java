package com.weple.cloud.gantt.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.weple.cloud.auth.service.LoginUserDetails;
import com.weple.cloud.auth.service.LoginUserVO;
import com.weple.cloud.gantt.service.GanttService;
import com.weple.cloud.project.service.ProjectService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/project/gantt")
public class GanttController {

    private final ProjectService projectService;
    private final GanttService ganttService;

    // 간트차트 화면 조회
    @GetMapping
    public String ganttChart(@AuthenticationPrincipal LoginUserDetails loginUser, 
                             @RequestParam Long projectId, 
                             Model model) {
        
        // [추가] 1. 프로젝트 참여 멤버 검증 (MilestoneController와 동일한 유틸 로직 적용)
        if (!hasProjectAccess(loginUser, projectId)) {
            return "weple/access-denide";
        }
        
        // [기존] 2. 모듈 맵핑 권한 체크 (b6 모듈 활성화 여부 확인)
        if (!ganttService.checkGanttModuleActive(projectId)) {
            model.addAttribute("accessDenideTitle", "접근 권한이 없습니다.");
            model.addAttribute("accessDenideMessage", "해당 프로젝트의 간트차트(b6) 모듈이 비활성화 상태입니다. 관리자에게 문의하세요.");
            return "weple/access-denide";
        }
        
        // 기존 툴바/사이드바 UI 유지를 위한 속성 세팅
        model.addAttribute("currentMenu", "gantt");
        model.addAttribute("sidebarMenu", "project");
        model.addAttribute("projectId", projectId);
        model.addAttribute("project", projectService.findById(String.valueOf(projectId)));
        
        // 알맞은 뷰 경로 리턴
        return "weple/gantt/chart"; 
    }

    /* ================= 팀원 양식 맞춤 권한 체크 유틸리티 메서드 ================= */

     // 프로젝트 메뉴 접근 권한 체크 (최고관리자/시스템관리자는 pass, 일반 유저는 members 테이블 확인)
    private boolean hasProjectAccess(LoginUserDetails loginUser, Long projectId) {
        if (loginUser == null || loginUser.getLoginUser() == null || projectId == null) {
            return false;
        }
        LoginUserVO user = loginUser.getLoginUser();
        
        // 최고관리자(Owner) 또는 시스템 관리자(Admin)는 무조건 허용
        if (isCompanyManager(user)) {
            return true;
        }
        
        // 일반 사용자는 DB의 members 테이블 참여 여부 판별 (GanttService에 추가하신 메서드 호출)
        // ※ 만약 GanttService가 아닌 MilestoneService의 메서드를 써야 한다면 서비스 주입을 변경해야 합니다.
        return ganttService.checkProjectMembership(projectId, user.getUserCode());
    }

    /**
     * 최고관리자 또는 시스템 관리자 여부 판별
     */
    private boolean isCompanyManager(LoginUserVO user) {
        return Integer.valueOf(1).equals(user.getOwnerYn()) || Integer.valueOf(1).equals(user.getAdminYn());
    }
}
