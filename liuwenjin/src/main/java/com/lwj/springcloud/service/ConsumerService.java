package com.lwj.springcloud.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 
 * @Description:   
 * @ClassName:     ConsumerService.java
 * @author         Mashuai
 * @Date           2018年8月22日 下午9:38:05
 * @Email          1119616605@qq.com
 */
@FeignClient("liuhaofei")
public interface ConsumerService {
	
	@RequestMapping("/courses/test")
	public Object getProviderFuture();
}