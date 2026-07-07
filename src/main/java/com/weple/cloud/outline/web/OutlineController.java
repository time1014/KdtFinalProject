package com.weple.cloud.outline.web;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.weple.cloud.auth.service.LoginUserDetails;
import com.weple.cloud.auth.service.LoginUserVO;
import com.weple.cloud.outline.service.OutlineService;
import com.weple.cloud.outline.service.ProjectGroupMemberDTO;
import com.weple.cloud.outline.service.ProjectProgressDTO;
import com.weple.cloud.project.service.ProjectService;
import com.weple.cloud.project.service.ProjectVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class OutlineController {
	
	private final OutlineService outlineService;
	private final ProjectService projectService;
	
	// 프로젝트 개요 조회
	@GetMapping("/project/outline")
	public String getProjectOutline(@AuthenticationPrincipal LoginUserDetails loginUser,
									@RequestParam Long projectId, 
									Model model) {
		
		// [추가] 1. 프로젝트 참여 멤버 검증 (MilestoneController와 동일한 유틸 로직 적용)
		if (!hasProjectAccess(loginUser, projectId)) {
			return "weple/access-denide";
		}
		
		// [기존] 2. 모듈 맵핑 권한 체크 (b1 모듈 활성화 여부 확인)
		if (!outlineService.checkOutlineModuleActive(projectId)) {
			model.addAttribute("accessDenideTitle", "접근 권한이 없습니다.");
			model.addAttribute("accessDenideMessage", "해당 프로젝트의 개요(b1) 모듈이 비활성화 상태입니다. 관리자에게 문의하세요.");
			return "weple/access-denide";
		}
		
		model.addAttribute("currentMenu", "outline");
		model.addAttribute("sidebarMenu", "project");
		model.addAttribute("projectId", projectId); 
		model.addAttribute("project", projectService.findById(String.valueOf(projectId)));
		
		// 프로젝트 정보 조회 - 삭제되었거나 존재하지 않으면 안내 후 접근 제한
		ProjectVO project = outlineService.getProjectById(projectId);
		if (project == null) {
		    model.addAttribute("accessDenideTitle", "삭제되었거나 존재하지 않는 프로젝트입니다.");
		    model.addAttribute("accessDenideMessage", "요청하신 프로젝트를 찾을 수 없습니다. 삭제되었거나 잘못된 경로일 수 있습니다.");
		    return "weple/access-denide";
		}
	    
		// 그룹별 프로젝트 참여 멤버 조회
		List<ProjectGroupMemberDTO> groupMembers = outlineService.selectProjectMembersByGroup(projectId);
		model.addAttribute("groupMembers", groupMembers);
		
		// 프로젝트 총 추정/소요 시간 조회 
		ProjectProgressDTO progressData = outlineService.getProjectProgress(projectId);
		model.addAttribute("progressData", progressData);
		
		return "weple/outline/outline";
	}

	/* ================= 팀원 양식 맞춤 권한 체크 유틸리티 메서드 ================= */

	/**
	 * 프로젝트 메뉴 접근 권한 체크 (최고관리자/시스템관리자는 pass, 일반 유저는 members 테이블 확인)
	 */
	private boolean hasProjectAccess(LoginUserDetails loginUser, Long projectId) {
		if (loginUser == null || loginUser.getLoginUser() == null || projectId == null) {
			return false;
		}
		LoginUserVO user = loginUser.getLoginUser();
		
		// 최고관리자(Owner) 또는 시스템 관리자(Admin)는 무조건 허용
		if (isCompanyManager(user)) {
			return true;
		}
		
		// 일반 사용자는 DB의 members 테이블 참여 여부 판별
		return outlineService.checkProjectMembership(projectId, user.getUserCode());
	}

	/**
	 * 최고관리자 또는 시스템 관리자 여부 판별
	 */
	private boolean isCompanyManager(LoginUserVO user) {
		return Integer.valueOf(1).equals(user.getOwnerYn()) || Integer.valueOf(1).equals(user.getAdminYn());
	}
}
