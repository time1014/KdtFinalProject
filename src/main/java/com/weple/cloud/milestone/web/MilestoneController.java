package com.weple.cloud.milestone.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weple.cloud.auth.service.LoginUserDetails;
import com.weple.cloud.auth.service.LoginUserVO;
import com.weple.cloud.milestone.service.MilestoneDetailVO;
import com.weple.cloud.milestone.service.MilestoneInfoVO;
import com.weple.cloud.milestone.service.MilestoneService;
import com.weple.cloud.milestone.service.MilestoneVO;
import com.weple.cloud.project.service.ProjectService;
import com.weple.cloud.project.service.ProjectVO;
import com.weple.cloud.repository.service.RepositoryService;
import com.weple.cloud.system.service.TaskTypeVO;
import com.weple.cloud.task.service.TaskVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/project/milestone") 
public class MilestoneController {

	private static final String PERMISSION_MILESTONE_CREATE_UPDATE_DELETE = "k1_version";
	private static final String PERMISSION_TASK_CREATE = "k3_add";
	private static final String PERMISSION_TASK_UPDATE = "k3_edit";
	private static final String PERMISSION_TASK_MYUPDATE = "k3_myedit";
	
	private final MilestoneService milestoneService;
	private final ProjectService projectService;
	private final RepositoryService repositoryService;

	// 로드맵 전체 조회
	@GetMapping
	public String milestoneList(@AuthenticationPrincipal LoginUserDetails loginUser, 
								@RequestParam Long projectId, 
								Model model) {
		// [추가] 프로젝트 참여 멤버 검증
		if (!hasProjectAccess(loginUser, projectId)) {
			return "weple/access-denide";
		}

		Set<String> permissionCodes = findMilestonePermissionCodes(loginUser, projectId);
		addMilestonePermissionAttributes(model, permissionCodes);

		List<MilestoneInfoVO> list = milestoneService.getRoadmapList(projectId); 
		
		model.addAttribute("currentMenu", "milestone");
		model.addAttribute("sidebarMenu", "project");
		model.addAttribute("projectId", projectId);
		model.addAttribute("project", projectService.findById(String.valueOf(projectId)));
		model.addAttribute("milestones", list);
		
		return "weple/milestone/list"; 
	}
	
	// 마일스톤 상세조회
	@GetMapping("/detail")
    public String getMilestoneDetail(@AuthenticationPrincipal LoginUserDetails loginUser, 
    								 @RequestParam Long projectId,
    								 @RequestParam Long milestoneId,
                                     @RequestParam(value = "page", defaultValue = "1") int page,
                                     Model model) {
		// [추가] 프로젝트 참여 멤버 검증
		if (!hasProjectAccess(loginUser, projectId)) {
			return "weple/access-denide";
		}

		Set<String> permissionCodes = findMilestonePermissionCodes(loginUser, projectId);
		addMilestonePermissionAttributes(model, permissionCodes);
		
		model.addAttribute("canAddTask", hasMilestonePermission(permissionCodes, PERMISSION_TASK_CREATE));
		model.addAttribute("canEditTask", hasMilestonePermission(permissionCodes, PERMISSION_TASK_UPDATE)
				|| hasMilestonePermission(permissionCodes, PERMISSION_TASK_MYUPDATE));

        MilestoneDetailVO detailInfo = milestoneService.getMilestoneDetailInfo(projectId, milestoneId);
        
        int pageSize = 20;
        List<TaskVO> paginatedTasks = milestoneService.getMilestoneTasksWithPaging(projectId, milestoneId, page, pageSize);
        
        int totalTaskCount = detailInfo.getTotalTaskCount();

        model.addAttribute("projectId", projectId); 
        model.addAttribute("detail", detailInfo);
        model.addAttribute("taskList", paginatedTasks);
        model.addAttribute("currentPage", page);
        model.addAttribute("currentMenu", "milestone");
        model.addAttribute("sidebarMenu", "project");
        model.addAttribute("project", projectService.findById(String.valueOf(projectId)));
        model.addAttribute("totalTasks", totalTaskCount);
        model.addAttribute("totalPages", (int) Math.ceil((double) totalTaskCount / pageSize));
        
        List<TaskTypeVO> taskTypeList = milestoneService.getTaskTypeList();
        model.addAttribute("taskTypeList", taskTypeList);

        return "weple/milestone/detail"; 
    }
	
	// 버전 등록 페이지 조회
	@GetMapping("/version/insert")
	public String versionInsertForm(@AuthenticationPrincipal LoginUserDetails loginUser, 
									@RequestParam Long projectId, 
									Model model) {
		// [추가] 프로젝트 참여 멤버 검증
		if (!hasProjectAccess(loginUser, projectId)) {
			return "weple/access-denide";
		}

		Set<String> permissionCodes = findMilestonePermissionCodes(loginUser, projectId);
		if (!hasMilestonePermission(permissionCodes, PERMISSION_MILESTONE_CREATE_UPDATE_DELETE)) {
			return "weple/access-denide";
		}
		addMilestonePermissionAttributes(model, permissionCodes);

	    model.addAttribute("currentMenu", "milestone");
	    model.addAttribute("sidebarMenu", "project");
	    model.addAttribute("projectId", projectId);
	    model.addAttribute("project", projectService.findById(String.valueOf(projectId)));
	    
	    ProjectVO project = milestoneService.findById(projectId); 
	    
	    model.addAttribute("projectId", projectId);
	    model.addAttribute("projectStart", project.getCreatedAt()); 
	    model.addAttribute("projectFinish", project.getFinishDate());
	    
	    return "weple/milestone/versionRegister";
	}

	// 마일스톤 등록 페이지 조회
	@GetMapping("/insert")
	public String milestoneInsertForm(@AuthenticationPrincipal LoginUserDetails loginUser, 
									  @RequestParam Long projectId, 
									  Model model) {
		// [추가] 프로젝트 참여 멤버 검증
		if (!hasProjectAccess(loginUser, projectId)) {
			return "weple/access-denide";
		}

		Set<String> permissionCodes = findMilestonePermissionCodes(loginUser, projectId);
		if (!hasMilestonePermission(permissionCodes, PERMISSION_MILESTONE_CREATE_UPDATE_DELETE)) {
			return "weple/access-denide";
		}
		addMilestonePermissionAttributes(model, permissionCodes);

	    model.addAttribute("currentMenu", "milestone");
	    model.addAttribute("sidebarMenu", "project");
	    model.addAttribute("projectId", projectId);
	    model.addAttribute("project", projectService.findById(String.valueOf(projectId)));
	    
	    List<TaskTypeVO> taskTypeList = milestoneService.getTaskTypeList();
	    model.addAttribute("taskTypeList", taskTypeList);
	    
	    List<MilestoneVO> parentMilestoneList = milestoneService.getMilestoneListByProjectId(projectId);
	    model.addAttribute("parentMilestoneList", parentMilestoneList);
	    
	    ProjectVO project = milestoneService.findById(projectId); 
	    
	    model.addAttribute("projectId", projectId);
	    model.addAttribute("projectStart", project.getCreatedAt()); 
	    model.addAttribute("projectFinish", project.getFinishDate());
	    
	    return "weple/milestone/register";
	}

	// 수정 페이지 조회 
	@GetMapping("/update")
	public String milestoneUpdateForm(@AuthenticationPrincipal LoginUserDetails loginUser, 
									  @RequestParam Long projectId, 
									  @RequestParam Long milestoneId, 
									  Model model) {
		// [추가] 프로젝트 참여 멤버 검증
		if (!hasProjectAccess(loginUser, projectId)) {
			return "weple/access-denide";
		}

		Set<String> permissionCodes = findMilestonePermissionCodes(loginUser, projectId);
		if (!hasMilestonePermission(permissionCodes, PERMISSION_MILESTONE_CREATE_UPDATE_DELETE)) {
			return "weple/access-denide";
		}
		addMilestonePermissionAttributes(model, permissionCodes);

	    model.addAttribute("currentMenu", "milestone");
	    model.addAttribute("sidebarMenu", "project");
	    model.addAttribute("projectId", projectId);
	    model.addAttribute("milestoneId", milestoneId);
	    model.addAttribute("project", projectService.findById(String.valueOf(projectId)));
	    
	    MilestoneVO milestone = milestoneService.getMilestoneInfoById(milestoneId);
	    model.addAttribute("milestone", milestone);
	    
	    List<TaskVO> connectedTaskList = milestoneService.getConnectedTaskList(milestoneId);
	    model.addAttribute("connectedTaskList", connectedTaskList);
	    
	    List<TaskTypeVO> taskTypeList = milestoneService.getTaskTypeList();
	    model.addAttribute("taskTypeList", taskTypeList);
	    
	    List<MilestoneVO> parentMilestoneList = milestoneService.getMilestoneListForUpdate(projectId, milestoneId);
	    model.addAttribute("parentMilestoneList", parentMilestoneList);
	    
	    ProjectVO project = milestoneService.findById(projectId); 
	    
	    model.addAttribute("projectId", projectId);
	    model.addAttribute("projectStart", project.getCreatedAt()); 
	    model.addAttribute("projectFinish", project.getFinishDate());
	    
	    return "weple/milestone/update"; 
	}

	// 미지정 일감 비동기 조회 (수정 폼 모달용)
	@GetMapping("/unassigned-tasks")
	@ResponseBody
	public Map<String, Object> getUnassignedTasks(
			@AuthenticationPrincipal LoginUserDetails loginUser, 
	        @RequestParam("projectId") Long projectId,
	        @RequestParam(value = "milestoneId", required = false) Long milestoneId, 
	        @RequestParam(value = "page", defaultValue = "1") int page,
	        @RequestParam(value = "taskStatus", required = false) String taskStatus,
	        @RequestParam(value = "priority", required = false) String priority,
	        @RequestParam(value = "taskManager", required = false) String taskManager,
	        @RequestParam(value = "typeId", required = false) Long typeId) {
		
		// [추가] 비동기 요청에 대한 프로젝트 참여 멤버 검증
		if (!hasProjectAccess(loginUser, projectId)) {
			Map<String, Object> errorResult = new HashMap<>();
			errorResult.put("error", "해당 프로젝트의 참여 멤버가 아닙니다.");
			return errorResult;
		}

		Set<String> permissionCodes = findMilestonePermissionCodes(loginUser, projectId);
				
		if (!hasMilestonePermission(permissionCodes, PERMISSION_TASK_UPDATE) && 
			!hasMilestonePermission(permissionCodes, PERMISSION_TASK_MYUPDATE)) {
					
			Map<String, Object> errorResult = new HashMap<>();
			errorResult.put("error", "해당 프로젝트의 일감 편집 권한이 없습니다.");
			return errorResult;
		}
	        
	    int pageSize = 10;
	    int startRow = (page - 1) * pageSize + 1;
	    int endRow = page * pageSize;
	    
	    List<TaskVO> taskList = milestoneService.getUnassignedTaskList(projectId, milestoneId, startRow, endRow, taskStatus, priority, taskManager, typeId);
	    int totalCount = milestoneService.getUnassignedTaskCount(projectId, milestoneId, taskStatus, priority, taskManager, typeId);
	    
	    Map<String, Object> result = new HashMap<>();
	    result.put("list", taskList);
	    result.put("totalCount", totalCount);
	    
	    return result;
	}

	// 버전 등록하기 
	@PostMapping("/version/insert")
	public String versionInsert(
	        @RequestParam Long projectId,
	        @AuthenticationPrincipal LoginUserDetails loginUser, 
	        MilestoneVO milestoneVO) {
		// [참고] POST 역시 진입 차단을 원하시면 상단 GetMapping과 동일하게 하단 유틸 메서드를 적용할 수 있습니다.
		Set<String> permissionCodes = findMilestonePermissionCodes(loginUser, projectId);
		if (!hasMilestonePermission(permissionCodes, PERMISSION_MILESTONE_CREATE_UPDATE_DELETE)) {
			return "weple/access-denide";
		}

	    String userCode = loginUser.getLoginUser().getUserCode();
	    milestoneVO.setUserCode(userCode);
	    milestoneVO.setProjectId(projectId);
	    
	    if (milestoneVO.getMilestoneStatus() == null) {
	        milestoneVO.setMilestoneStatus("g1"); 
	    }
	    
	    milestoneService.addVersion(milestoneVO);
	    		    
	    return "redirect:/project/milestone?projectId=" + projectId;
	}

	// 마일스톤 등록하기 
	@PostMapping("/insert")
	public String milestoneInsert(
	        @RequestParam Long projectId,
	        @RequestParam(value = "taskIds", required = false) List<String> taskIds, 
	        @AuthenticationPrincipal LoginUserDetails loginUser, 
	        MilestoneVO milestoneVO) {
	    
		Set<String> permissionCodes = findMilestonePermissionCodes(loginUser, projectId);
		if (!hasMilestonePermission(permissionCodes, PERMISSION_MILESTONE_CREATE_UPDATE_DELETE)) {
			return "weple/access-denide";
		}

	    String userCode = loginUser.getLoginUser().getUserCode();
	    milestoneVO.setUserCode(userCode);
	    milestoneVO.setProjectId(projectId);
	    
	    if (milestoneVO.getMilestoneStatus() == null) {
	        milestoneVO.setMilestoneStatus("g1"); 
	    }
	    
	    milestoneService.addMilestone(milestoneVO, taskIds);
	    
	    return "redirect:/project/milestone?projectId=" + projectId;
	}

	// 마일스톤 및 연결 일감 수정하기
	@PostMapping("/update")
	public String milestoneUpdate(
			@AuthenticationPrincipal LoginUserDetails loginUser, 
	        @RequestParam Long projectId,
	        @RequestParam Long milestoneId,
	        @RequestParam(value = "taskIds", required = false) List<String> taskIds, 
	        MilestoneVO milestoneVO) {
	    
		Set<String> permissionCodes = findMilestonePermissionCodes(loginUser, projectId);
		if (!hasMilestonePermission(permissionCodes, PERMISSION_MILESTONE_CREATE_UPDATE_DELETE)) {
			return "weple/access-denide";
		}

	    milestoneVO.setProjectId(projectId);
	    milestoneService.modifyMilestone(milestoneVO, taskIds);
	    
	    return "redirect:/project/milestone/detail?projectId=" + projectId + "&milestoneId=" + milestoneId;
	}

	// 상위 마일스톤 수정하기
	@PostMapping("/update-parent")
	public String parentMilestoneUpdate(@AuthenticationPrincipal LoginUserDetails loginUser, 
										@RequestParam Long projectId, 
										MilestoneVO milestoneVO) {
		Set<String> permissionCodes = findMilestonePermissionCodes(loginUser, projectId);
		if (!hasMilestonePermission(permissionCodes, PERMISSION_MILESTONE_CREATE_UPDATE_DELETE)) {
			return "weple/access-denide";
		}

		milestoneVO.setProjectId(projectId);
		milestoneService.updateParentMilestone(milestoneVO);
		
		return "redirect:/project/milestone?projectId=" + projectId;
	}

	// 삭제하기 
	@PostMapping("/delete")
	public String milestoneDelete(@AuthenticationPrincipal LoginUserDetails loginUser, 
								  @RequestParam Long projectId, 
								  @RequestParam Long milestoneId) {
		Set<String> permissionCodes = findMilestonePermissionCodes(loginUser, projectId);
		if (!hasMilestonePermission(permissionCodes, PERMISSION_MILESTONE_CREATE_UPDATE_DELETE)) {
			return "weple/access-denide";
		}

		milestoneService.deleteMilestone(milestoneId);
		
		return "redirect:/project/milestone?projectId=" + projectId;
	}

	/* ================= 팀원 양식 맞춤 권한 체크 유틸리티 메서드 ================= */

	/**
	 * [추가] 프로젝트 메뉴 접근 권한 체크 (최고관리자/시스템관리자는 pass, 일반 유저는 members 테이블 확인)
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
		return milestoneService.checkProjectMembership(projectId, user.getUserCode());
	}

	private Set<String> findMilestonePermissionCodes(LoginUserDetails loginUser, Long projectId) {
		if (loginUser == null || loginUser.getLoginUser() == null) {
			return Set.of();
		}
		LoginUserVO user = loginUser.getLoginUser();
		if (isCompanyManager(user)) {
	        return Set.of(
	            PERMISSION_MILESTONE_CREATE_UPDATE_DELETE, 
	            PERMISSION_TASK_CREATE,                    
	            PERMISSION_TASK_UPDATE,                    
	            PERMISSION_TASK_MYUPDATE                   
	        );
	    }
		return repositoryService.findProjectPermissionCodes(user.getUserCode(), projectId);
	}

	private boolean hasMilestonePermission(Set<String> permissionCodes, String permissionCode) {
		return permissionCodes != null && permissionCodes.contains(permissionCode);
	}

	private boolean isCompanyManager(LoginUserVO user) {
		return Integer.valueOf(1).equals(user.getOwnerYn()) || Integer.valueOf(1).equals(user.getAdminYn());
	}

	private void addMilestonePermissionAttributes(Model model, Set<String> permissionCodes) {
		model.addAttribute("canManageMilestone", hasMilestonePermission(permissionCodes, PERMISSION_MILESTONE_CREATE_UPDATE_DELETE));
	}
}