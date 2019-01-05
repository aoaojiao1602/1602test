package com.gzz.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.gzz.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer>{
	//修改自己所发表的主题
	@Query(value="UPDATE projecttb SET project_title=?1,project_content=?2 WHERE project_id=?3",nativeQuery=true)
	@Modifying
	public int postProject(String projectTitle,String projectContent,Integer projectId);
	//发表主题(所属的讨论区模块是老师答疑区)
	@Query(value="INSERT INTO projecttb (clazz_id,project_content,project_createtime,project_title,u_id,project_moduleid) VALUE(?1,?2,?3,?4,?5,?6)",nativeQuery=true)
	@Modifying
	public int putProject(Integer clazzId,String projectContent,String projectCreatetime,String projectTitle,Integer projectUid,Integer moduleId);
	//发表主题(所属的讨论区模块是综合讨论区)
	@Query(value="INSERT INTO projecttb (project_content,project_createtime,project_title,u_id,project_moduleid) VALUE(?1,?2,?3,?4,?5)",nativeQuery=true)
	@Modifying
	public int putProjects(String projectContent,String projectCreatetime,String projectTitle,Integer projectUid,Integer moduleId);
	//查询用户自己所关注的主题
	@Query(value="SELECT * FROM projecttb WHERE project_state=?1 AND u_id=?2 LIMIT 5",nativeQuery=true)
	public List<Project> getProjectByProjectState(Integer projectState,Integer uId);
	//查询用户自己所关注的主题的id
	@Query(value="SELECT project_id FROM projecttb WHERE u_id=?1",nativeQuery=true)
	public List<Integer> getProjectId(Integer projectUid);
	//根据模块查询当前最先发布的主题
	@Query(value="SELECT * FROM projecttb WHERE project_moduleid=?1 ORDER BY project_createtime DESC  LIMIT 1",nativeQuery=true)
	public Project getProject(Integer projectModule);
	//查询某个帖子的浏览数
	@Query(value="SELECT COUNT(*) FROM throughtb WHERE  through_project_id=?1",nativeQuery=true)
	public int getThroug(Integer projectId);
	//查询某个帖子的回复数
	@Query(value="SELECT COUNT(*) FROM projectreplytb WHERE project_reply_project_id=1",nativeQuery=true)
	public int getReply(Integer projectId);
	//查询某个帖子的投票数
	@Query(value="SELECT COUNT(*) FROM projectcounttb WHERE project_count_project_id=?1",nativeQuery=true)
	public int getCount(Integer projectId);
}
