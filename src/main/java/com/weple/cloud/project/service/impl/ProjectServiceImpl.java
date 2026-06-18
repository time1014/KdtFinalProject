package com.weple.cloud.project.service.impl;

import org.hibernate.mapping.List;
import org.springframework.stereotype.Service;

import com.weple.cloud.project.mapper.ProjectMapper;
import com.weple.cloud.project.service.ProjectVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl {
	private final ProjectMapper projectMapper;
	
	@Override
	public List<ProjectVO> findAll(){
		return projectMapper.selectAll();
	}
}
