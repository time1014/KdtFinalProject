package com.weple.cloud.project.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class ProjectVO {
	private Long project_id;
	private String project_title;
	private String project_describe;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date created_at;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date finish_date;
	private String project_status;
	
}
