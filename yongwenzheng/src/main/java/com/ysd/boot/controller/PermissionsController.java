package com.ysd.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ysd.boot.service.PermissionsService;
import com.ysd.boot.tool.Result;

@RestController
@RequestMapping(value="/permissions",name="权限管理")
public class PermissionsController {
	
	@Autowired
	private PermissionsService permissionsService;
	
	@GetMapping(value="/updateAllPermissions",name="更新所有权限")
	public Object updateAllPermissions() {
		Integer k=permissionsService.updatePermissionsCount();
		Result result=new Result();		
			result.setState(1);
			result.setMsg("成功更新 "+k+" 条权限");		
		return result;		
	}

}