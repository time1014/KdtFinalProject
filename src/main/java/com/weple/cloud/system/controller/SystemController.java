package com.weple.cloud.system.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weple.cloud.system.service.SystemService;
import com.weple.cloud.system.service.TaskTypeVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SystemController {

	private final SystemService systemService;
	
	//-------------------------------일감유형------------------------------
	// 전체조회
	@GetMapping("/system/taskType")
	public String systemTaskTypeList(Model model) {
		List<TaskTypeVO> list = systemService.findTaskTypeAll();
		model.addAttribute("taskTypes", list);
		return "weple/system/taskType/list";
	}
	
	// 등록페이지 조회
	@GetMapping("/system/taskTypeInsert")
	public String taskTypeInsert() {
		return "weple/system/taskType/register";
	}
	
	// 등록하기
	@PostMapping("/system/taskTypeInsert")
	public String systemTaskTypeInsert(TaskTypeVO taskTypeVO) {
		systemService.addTaskType(taskTypeVO);
		return "redirect:/system/taskType";
	}
	
	// 순서 수정하기 (드래그&드랍으로 변경된 순서)
	@PutMapping("/system/taskTypeReorder")
	@ResponseBody
	public ResponseEntity<String> systemTaskTypeReorder(@RequestBody List<Integer> sortedIds) {
		systemService.reorderTaskTypes(sortedIds);
		return ResponseEntity.ok("SUCCESS");
	}
	
	// 수정페이지 조회
	@GetMapping("/system/taskTypeUpdate")
	public String taskTypeUpdate() {
		return "weple/system/taskType/register";
	}
	
	// 수정하기
	@PutMapping("/system/taskTypeUpdate")
	public String
	
	
	
	
}
