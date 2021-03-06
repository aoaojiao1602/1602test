package com.ysd.boot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ysd.boot.entity.Clazz;
import com.ysd.boot.entity.Professional;
import com.ysd.boot.service.ProfessionalService;
import com.ysd.boot.tool.Result;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/professional")
public class ProfessionalController {

	@Autowired
	private ProfessionalService professionalService;
	
	 /**
             * 通过departmentId得到Professional
      * @param departmentId
     * @return
      */
	@ApiOperation(value="获取系信息", notes="通过departmentId得到Professional")
	@GetMapping("/getAllProfessionalBydepartmentId")
    public List<Professional> getAllProfessionalBydepartmentId(@RequestParam Integer departmentId,@RequestParam String access_token){
    	
    	return professionalService.getAllProfessionalBydepartmentId(departmentId);
    }
	
	
	/**
	 * 添加系信息
	 * @param professionalName
	 * @param department_id
	 * @return
	 */
	@ApiOperation(value="获取系信息", notes="添加系信息")
	@ApiImplicitParams({
	@ApiImplicitParam(name = "professionalName", value = "系名称", required = true, dataType = "String", paramType = "query"),
	@ApiImplicitParam(name = "department_id", value = "所属部门id", required = true, dataType = "Integer", paramType = "query"),
	@ApiImplicitParam(name = "access_token", value = "token", required = true, dataType = "String", paramType = "query")
	})
	@PutMapping("/addProfessional")
	public Object addProfessional(@RequestParam String professionalName,@RequestParam Integer department_id,@RequestParam String access_token) {
		Result result=new Result();
		if (professionalService.addProfessional(professionalName, department_id)>0) {
			result.setState(1);
			result.setMsg("添加成功");
			
		} else {
			result.setState(0);
			result.setMsg("添加失败");
		}
		return result;
		
	}
	
	
	/**
	  * 通过id删除系信息
	 * @param professionalId
	 * @return
	 */
	@ApiOperation(value="获取系信息", notes="通过id删除系信息")
	@ApiImplicitParams({
	@ApiImplicitParam(name = "professionalId", value = "系id", required = true, dataType = "Integer", paramType = "query"),
	@ApiImplicitParam(name = "access_token", value = "token", required = true, dataType = "String", paramType = "query")
	})
	@DeleteMapping("/deleteProfessional")
	public Object deleteProfessional(@RequestParam Integer professionalId,@RequestParam String access_token) {
		Result result=new Result();
		if (professionalService.deleteProfessional(professionalId)>0) {
			result.setState(1);
			result.setMsg("删除成功");
			
		} else {
			result.setState(0);
			result.setMsg("删除失败");
		}
		return result;
		
	}
	
	/**
	  * 通过id修改系信息
	 * @param professionalId
	 * @param professionalName
	 * @param department_id
	 * @return
	 */
	@ApiOperation(value="获取系信息", notes=" 通过id修改系信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "professionalId", value = "系id", required = true, dataType = "Integer", paramType = "query"),
     	@ApiImplicitParam(name = "professionalName", value = "系名称", required = true, dataType = "String", paramType = "query"),
	    @ApiImplicitParam(name = "department_id", value = "所属部门id", required = true, dataType = "Integer", paramType = "query"),
	    @ApiImplicitParam(name = "access_token", value = "token", required = true, dataType = "String", paramType = "query")
	})
	@PostMapping("/updateProfessional")
	public Object updateProfessional(@RequestParam Integer professionalId,@RequestParam String professionalName,@RequestParam Integer department_id,@RequestParam String access_token) {
		Result result=new Result();
		if (professionalService.updateProfessional(professionalId, professionalName, department_id)>0) {
			result.setState(1);
			result.setMsg("修改成功");
			
		} else {
			result.setState(0);
			result.setMsg("修改失败");
		}
		return result;
		
	}

	/**
	 * 查询所有系信息
	 * @return
	 */
	@ApiOperation(value="获取系信息", notes=" 查询所有系信息")
	@ApiImplicitParam(name = "access_token", value = "token", required = true, dataType = "String", paramType = "query")
	@GetMapping("/getAllProfessionalList")
    public Object getAllProfessionalList(@RequestParam String access_token) {
    	
    	return professionalService.getAllProfessionalList();
    }
	 /**
             * 分页查询系信息
      * @param name
      * @param pageable
      * @return
      */
	@ApiOperation(value="获取系信息", notes=" 分页查询系信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", value = "页码", required = true, dataType = "Integer", paramType = "query"),
			@ApiImplicitParam(name = "rows", value = "行数", required = true, dataType = "Integer", paramType = "query"),
			@ApiImplicitParam(name = "name", value = "系名称", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "access_token", value = "token", required = true, dataType = "String", paramType = "query")
     })
	@GetMapping("/queryProfessionalByPage") 
	public Object queryProfessionalByPage(@RequestParam Integer page,@RequestParam Integer rows,@RequestParam String name,@RequestParam String access_token){
        Page<Professional> pages=professionalService.queryProfessionalByPage(page, rows, name);
        Map<String, Object> map = new HashMap<>();
	       map.put("total", pages.getTotalElements());
	       map.put("rows",pages.getContent());
	    return map;

     }
	
	
	
}
