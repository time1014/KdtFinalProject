package com.weple.cloud.system.mapper;

import java.util.List;

import com.weple.cloud.system.service.TaskTypeVO;

public interface SystemMapper {

	
	// ---------------------------- 일감유형 --------------------------
	// 전체 조회
	public List<TaskTypeVO> selectTaskTypeAll();
	
	// 등록
	public int insertTaskType(TaskTypeVO taskType);
	
	// 편집
	
	
	// 삭제
}
