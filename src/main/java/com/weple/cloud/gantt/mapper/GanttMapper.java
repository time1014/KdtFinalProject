package com.weple.cloud.gantt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.weple.cloud.milestone.service.MilestoneInfoVO;
import com.weple.cloud.task.service.TaskVO;

public interface GanttMapper {
	List<TaskVO> selectTaskAll(@Param("projectId") Long projectId);
	
	// 집계 없는 마일스톤 조회
    List<MilestoneInfoVO> selectMilestoneForGantt(@Param("projectId") Long projectId);
    
 // [추가] 프로젝트별 특정 모듈 활성화 카운트 조회
    int isModuleActive(@Param("projectId") Long projectId, @Param("moduleName") String moduleName);
    
 // 프로젝트 접근 권한여부
 	int checkProjectMembership(@Param("projectId") Long projectId, @Param("userCode") String userCode);
}
