package com.weple.cloud.system.service;

import java.util.List;

public interface SystemService {
	// 일감유형 전체조회
	public List<TaskTypeVO> findAll();
	
	// 일감유형 등록
	public int addTaskType(TaskTypeVO taskTypeVO);
	
	// 일감유형 편집
	
	
	// 일감유형 삭제
	
	
}
