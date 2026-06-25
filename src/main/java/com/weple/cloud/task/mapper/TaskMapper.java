package com.weple.cloud.task.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.weple.cloud.history.task.service.TaskHistoryVO;
import com.weple.cloud.task.service.VO.TaskCommentVO;
import com.weple.cloud.task.service.VO.TaskMemberVO;
import com.weple.cloud.task.service.VO.TaskMilestoneVO;
import com.weple.cloud.task.service.VO.TaskParentVO;
import com.weple.cloud.task.service.VO.TaskPriorityVO;
import com.weple.cloud.task.service.VO.TaskProjectSelectVO;
import com.weple.cloud.task.service.VO.TaskSpentTimeVO;
import com.weple.cloud.task.service.VO.TaskStatusVO;
import com.weple.cloud.task.service.VO.TaskTypeListVO;
import com.weple.cloud.task.service.VO.TaskUpdateHistoryVO;
import com.weple.cloud.task.service.VO.TaskVO;

public interface TaskMapper {
	public List<TaskVO> selectAll(@Param("pId") long pId);
	
	public List<TaskTypeListVO> taskTypes(@Param("cId") long cId);
	
	public List<TaskStatusVO> taskStatuses();
	
	public List<TaskMemberVO> taskMembers(@Param("pId") long pId);
	
	public List<TaskPriorityVO> taskPriorities(@Param("cId") long cId);
	
	public List<TaskParentVO> taskParents(@Param("pId") long pId);
	
	public List<TaskMilestoneVO> taskMilestones(@Param("pId") long pId);
	
    public int insertTask(TaskVO taskVO);
    
    public List<TaskVO> selectAllList(@Param("tManager") String tManager);
    
    public TaskVO taskDetail(@Param("tId") String tId);
    
    public List<TaskVO>childTask(@Param("tId") String tId);
    
    public List<TaskProjectSelectVO> myAllTasks(@Param("uCode") String uCode);
    
    public int updateTask(TaskVO taskVO);
    
    public void deleteTask(@Param("tId") String tId);
    
    public List<TaskCommentVO>taskCommentList(@Param("tId") String tId);
    
    public int insertTaskComment(TaskCommentVO taskCommentVO);
    
    public int updateTaskComment(TaskCommentVO taskCommentVo);
    
    public int deleteTaskComment(@Param("commentId") Long commentId , @Param("userCode") String userCode);
    
    public List<TaskUpdateHistoryVO> taskUpdateHistory(@Param("tId") String tId);
    
    public List<TaskSpentTimeVO> taskSpentTime(@Param("tId") String tId);

    public Long getMilestoneIdByTaskId(@Param("tId") String tId);

}
