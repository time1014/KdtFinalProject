package com.weple.cloud.system.mapper;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weple.cloud.system.service.SystemService;
import com.weple.cloud.system.service.TaskTypeVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SystemServiceImpl implements SystemService {

	private final SystemMapper systemMapper;
	
	//-------------------------------일감유형------------------------------
	
	// 일감유형 전체조회
	@Override
	public List<TaskTypeVO> findTaskTypeAll() {
		return systemMapper.selectTaskTypeAll();
	}

	// 일감유형 등록
	@Override
	public void addTaskType(TaskTypeVO taskTypeVO) {
		// 기업아이디 값 = 1 더미데이터 추가
		taskTypeVO.setCompanyId(1);
		systemMapper.insertTaskType(taskTypeVO);
	}
	
	// 일감유형 순서 수정
	@Override
	@Transactional
	public void reorderTaskTypes(List<Integer> sortedIds) {
		for (int i = 0; i < sortedIds.size(); i++) {
			int position = i + 1;
			systemMapper.updatePosition(sortedIds.get(i), position);
		}
	}
	
	@Override
	public void updateTaskType(TaskTypeVO taskTypeVO) {
		systemMapper.updateTaskType(taskTypeVO);
	}

	@Override
	public int deleteTaskType(int typeId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
