package com.lhf.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lhf.entity.Courses;
import com.lhf.entity.CoursesSearch;
import com.lhf.entity.Hours;
import com.lhf.service.CoursesService;
@CrossOrigin
@RestController
@RequestMapping("/courses")
public class CoursesController {
	@Autowired
	private CoursesService cService;
	/**查询方法
	 * localhost:8050/courses/query?page=1&limit=2`	
	 * @param page 页码
	 * @param limit 条数
	 * @return
	 */
	@RequestMapping("/query")
	public Object queryByDynamicSQLPage(CoursesSearch c,int page,int limit) {
		Page<Courses> sqlPage = cService.queryByDynamicSQLPage(c, page-1, limit);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("count", sqlPage.getTotalElements());
		map.put("data", sqlPage.getContent());
		return map;
	}
	@RequestMapping("/queryCourses")
	public Object getCoursesById(Integer courseCreator) {
		List<Courses> list=cService.getCoursesById(courseCreator);
		return list;
	}
	@RequestMapping("/queryhours")
	public Object getHoursBycourseCreatorAndcourseId(Integer courseId) {
		List<Hours> list=cService.getHoursBycourseCreatorAndcourseId(courseId);
		return list;
	}
	/**
	 * 添加方法
	 * http://localhost:8050/courses/putCourses?courseName=诗经&courseCategory=国学经典&courseCreator=李四
	 * @param c 实体类课程
	 * @return
	 */
	@RequestMapping("/putCourses")
	public Object putCourses(@RequestBody Courses c) {
		int C=cService.putCourses(c);
		Map<String, Object> map = new HashMap<>();
		if(C>0) {
			map.put("success",true);
			map.put("message","添加成功");
		}else {
			map.put("success",false);
			map.put("message","添加失败");
		}
		return C;
	}
	//删除
	/**
	 * 删除方法
	 * http://localhost:8050/courses/deleteCourses?courseId=5
	 * @param courseId 课程Id
	 * @return
	 */
	@RequestMapping("/deleteCourses")
	public Object deleteCoursesById(Integer courseId) {
		int C=cService.deleteCoursesById(courseId);
		Map<String, Object> map = new HashMap<>();
		if(C>0) {
			map.put("success",true);
			map.put("message","删除成功");
		}else {
			map.put("success",false);
			map.put("message","删除失败");
		}
		return C;
	}
	/**
	 * 修改方法
	 * http://localhost:8050/courses/postCourses?courseCategory=aaa&courseName=aaaaa&courseId=6
	 * @param c 实体类课程666
	 * @return
	 */
	@RequestMapping("/postCourses")
	public Object postCoursesById(@RequestBody Courses c) {
		int C=cService.postCoursesById(c);
		Map<String, Object> map = new HashMap<>();
		if(C>0) {
			map.put("success",true);
			map.put("message","修改成功");
		}else {
			map.put("success",false);
			map.put("message","修改失败");
		}
		return C;
	}
	
	/**
	 * 发布课程
	 * @param courseId
	 * @return
	 */
	@RequestMapping("/faBuCourse")
	public int faBuCourse(Integer courseId) {
		return cService.faBuCourse("已发布", courseId);
	}
	
	/**
	 * 根据课程ID查询课程
	 * @param courseId
	 * @return
	 */
	@RequestMapping("/findByCourseId")
	public Courses findByCourseId(int courseId) {
		return cService.findByCourseId(courseId);
	}
	/**
	 * 根据课程类别查询课程
	 * @param courseCategory
	 * @return
	 */
	@RequestMapping("/findByCourseCategory")
	public List<Courses> findByCourseCategory(Integer courseCategory){
		return cService.findByCourseCategory(courseCategory);
	}
	/**
	 * 根据课程多个ID查询课程
	 * @param courseId
	 * @return
	 */
	@RequestMapping("/queryByCourseId")
	public List<Courses> queryByCourseId(List<Integer> courseId){
		List<Courses> list=new ArrayList<Courses>();
		for (Integer integer : courseId) {
			list.add(cService.findByCourseId(integer));
		}
	   return list;
	}
	@RequestMapping("/a")
	public List<Courses> queryByCourseId(){
		List<Integer> ls=new ArrayList<Integer>();
		ls.add(5);
		ls.add(6);
	   return this.queryByCourseId(ls);
	}

}
