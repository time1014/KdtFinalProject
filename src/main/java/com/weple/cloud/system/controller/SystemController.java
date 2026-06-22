package com.weple.cloud.system.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weple.cloud.auth.service.LoginUserDetails;
import com.weple.cloud.system.service.TaskTypeService;
import com.weple.cloud.system.service.TaskTypeVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SystemController {

	private final TaskTypeService taskTypeService;


	// -------------------------------일감유형------------------------------
	// 전체조회
	@GetMapping("/system/taskType")
	public String systemTaskTypeList(@AuthenticationPrincipal LoginUserDetails loginUser, Model model) {
		Long companyId = loginUser.getLoginUser().getCompanyId();
		List<TaskTypeVO> list = taskTypeService.findTaskTypeAll(companyId);
		model.addAttribute("taskTypes", list);
		return "weple/system/taskType/list";
	}

	// 등록페이지 조회
	@GetMapping("/system/taskType/Insert")
	public String taskTypeInsert() {
		return "weple/system/taskType/register";
	}

	// 등록하기
	@PostMapping("/system/taskType/Insert")
	public String systemTaskTypeInsert(@AuthenticationPrincipal LoginUserDetails loginUser, TaskTypeVO taskTypeVO) {
		Long companyId = loginUser.getLoginUser().getCompanyId();
		taskTypeVO.setCompanyId(companyId);
		taskTypeService.addTaskType(taskTypeVO);
		return "redirect:/system/taskType";
	}

	// 순서 수정하기 (드래그&드랍으로 변경된 순서)
	@PostMapping("/system/taskType/Reorder")
	@ResponseBody
	public ResponseEntity<String> systemTaskTypeReorder(@RequestBody List<Integer> sortedIds) {
		taskTypeService.reorderTaskTypes(sortedIds);
		return ResponseEntity.ok("SUCCESS");
	}

	// 수정페이지 조회
	@GetMapping("/system/taskType/Update")
	public String taskTypeUpdate(@RequestParam("typeId") int typeId, Model model) {
		TaskTypeVO taskType = taskTypeService.findTaskTypeById(typeId);
		model.addAttribute("taskType", taskType);
		return "weple/system/taskType/register";
	}

	// 수정하기
	@PostMapping("/system/taskType/Update")
	public String systemTaskTypeUpdate(TaskTypeVO taskTypeVO) {
		taskTypeService.updateTaskType(taskTypeVO);
		return "redirect:/system/taskType";
	}
	
	// 삭제하기
	@PostMapping("/system/taskType/Delete/{typeId}")
	@ResponseBody
	public ResponseEntity<String> systemTaskTypeDelete(@PathVariable("typeId") int typeId) {
		taskTypeService.deleteTaskType(typeId);
		return ResponseEntity.ok("SUCCESS");
	}

	
	// ---------------------------- 가입승인 --------------------------
	private final com.weple.cloud.system.service.SignupApprovalService signupApprovalService;

	// 현재 로그인한 관리자의 회사에 접수된 승인 대기 회원을 조회합니다.
	@GetMapping("/approvalList")
	public String approvalList(
			@org.springframework.security.core.annotation.AuthenticationPrincipal com.weple.cloud.auth.service.LoginUserDetails loginUser,
			Model model) {
		List<com.weple.cloud.system.service.SignupApprovalUserVO> pendingUsers =
				signupApprovalService.findPendingUsers(loginUser.getLoginUser().getCompanyId());
		model.addAttribute("pendingUsers", pendingUsers);
		model.addAttribute("menu", "approval");
		return "weple/admin/config/join-request";
	}

	// 요청 URL의 사용자 코드만 받고 회사 정보는 로그인 세션에서 가져와 승인합니다.
	@PostMapping("/approvalList/{userCode}/approve")
	public String approveSignupRequest(
			@org.springframework.security.core.annotation.AuthenticationPrincipal com.weple.cloud.auth.service.LoginUserDetails loginUser,
			@org.springframework.web.bind.annotation.PathVariable String userCode,
			org.springframework.web.servlet.mvc.support.RedirectAttributes redirectAttributes) {
		try {
			signupApprovalService.approvePendingUser(loginUser.getLoginUser().getCompanyId(), userCode);
			redirectAttributes.addFlashAttribute("approvalSuccess", "가입 요청을 승인했습니다.");
		} catch (IllegalArgumentException ex) {
			redirectAttributes.addFlashAttribute("approvalError", ex.getMessage());
		}
		return "redirect:/approvalList";
	}

	// 취소한 승인 대기 가입 요청은 USERS 테이블에서 삭제합니다.
	@PostMapping("/approvalList/{userCode}/cancel")
	public String cancelSignupRequest(
			@org.springframework.security.core.annotation.AuthenticationPrincipal com.weple.cloud.auth.service.LoginUserDetails loginUser,
			@org.springframework.web.bind.annotation.PathVariable String userCode,
			org.springframework.web.servlet.mvc.support.RedirectAttributes redirectAttributes) {
		try {
			signupApprovalService.cancelPendingUser(loginUser.getLoginUser().getCompanyId(), userCode);
			redirectAttributes.addFlashAttribute("approvalSuccess", "가입 요청을 취소했습니다.");
		} catch (IllegalArgumentException ex) {
			redirectAttributes.addFlashAttribute("approvalError", ex.getMessage());
		}
		return "redirect:/approvalList";
	}
	//-------------------------------프로젝트------------------------------
	// 프로젝트 생성
	@Autowired
	private SystemProjectService systemProjectService;
	
	@GetMapping("/system/project")
	public String projectCreateForm(Model model) {
		
		model.addAttribute("sidebarMenu", "system");
		model.addAttribute("currentMenu", "systemproject");
		
		return "weple/system/projectCreate";
	}
	
	@PostMapping("/system/project")
	public String projectCreateProcess(SystemProjectVO projectVO, Model model) {
		int result = systemProjectService.createProject(projectVO);
		
		if(result > 0) {
			return "redirect:/project";
		}else {
			model.addAttribute("errorMessage", "프로젝트 생성에 실패했습니다.");
			model.addAttribute("sidebarMenu", "system");
			model.addAttribute("currentMenu", "systemproject");
			
			
			return "weple/system/projectCreate";
		}
	}
	
	
}
