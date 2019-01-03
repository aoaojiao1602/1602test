package com.hwg.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hwg.entity.Coursecomment;
import com.hwg.service.YwzService;
import com.hwg.serviceImpl.CoursecommentServiceImpl;
import com.hwg.utils.ExcelCreate;
import com.hwg.utils.POIUtils;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @Description 课程评论控制器
 * @author HJiong
 * @time 2018年12月31日 下午12:15:18
 */
@RequestMapping("comment")
@RestController
public class CourseCommentController {
	//课程评论
	@Autowired
	private CoursecommentServiceImpl cService;
	
	//分类接口
	@Autowired
	private YwzService caService;


	@ApiOperation("条件查询课程评论")
	@GetMapping()
	public List<Coursecomment> getCommentByWhere(String ksTime,String jsTime){
		return cService.getCommentByWhere(ksTime,jsTime);
	}
	
	/**
	 * 导出excel
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@GetMapping("/export")
	@ApiOperation("导出export（根据不同分类 创建不同sheet表）")
	@ApiImplicitParams({@ApiImplicitParam(name="response",value="数据响应对象"),@ApiImplicitParam(name="ksTime",value="开始日期"),
	@ApiImplicitParam(name="jsTime",value="结束日期")
	})
	public void export(HttpServletResponse response,String ksTime,String jsTime) throws Exception {
		List<Coursecomment> list=cService.getCommentByWhere(ksTime,jsTime);//符合条件的所有课程评论
		List<Map<String, Object>> cates=caService.getCategoryAll("token");//所有的课程分类
		Workbook resultWorkBook = new XSSFWorkbook();//创建workbook对象
		Map<String, String> map=new HashMap<>();//设置要导出的字段
		map.put("commentId", "评论id");
		map.put("commentContent", "内容");
		map.put("commentCourseId", "课程编号");
		map.put("commentStuId", "学生编号");
		for (Map<String, Object> ct : cates) {//循环所有分类
			List<Object> exportRows=new ArrayList<>();//每次循环分类都重新创建该集合
			for (Coursecomment cc : list) {//循环到该分类的课程评论 加入新集合
				if(ct.get("navId").equals(cc.getCommentCourseCategoryId())) {
					exportRows.add(cc);
				}
			}
			//内循环完毕后 创建当前分类sheet表  并传入当前分类的所有评价（exportRows） 
			POIUtils.copySheet(resultWorkBook,ExcelCreate.createWorkbook( ct.get("navName").toString(), map, exportRows).getSheetAt(0), resultWorkBook.createSheet(ct.get("navName").toString()), true);
		}
		//最后所有sheet表创建完毕 调用响应文件 响应workbook
		ExcelCreate.downloadExcel(response,resultWorkBook,"课程评价数据");
	}
	
}
