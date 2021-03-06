package com.lwj.springcloud.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.lwj.springcloud.entity.Entitysearch;
import com.lwj.springcloud.entity.Question;

public interface QuestionService {

	
	  /** 
	 * http://localhost:8080/indexQuestionPage
	  * 作者: 大娃   
	  * 邮件: 1558936588@qq.com  
	  * 时间: 2018年12月14日 下午11:49:03  
	  * 版本: V1.0   
	 */
	
	Page<Question> indexQuestionPage(Entitysearch search);

	
	  /** 
	 * http://localhost:8080/deleteQuestion
	  * 作者: 大娃   
	  * 邮件: 1558936588@qq.com  
	  * 时间: 2018年12月17日 下午7:23:18  
	  * 版本: V1.0   
	 */
	
	void deleteQuestion(int qid);


	
	  /** 
	 * http://localhost:8080/inserQuestion
	  * 作者: 大娃   
	  * 邮件: 1558936588@qq.com  
	  * 时间: 2018年12月18日 下午3:02:07  
	  * 版本: V1.0   
	 */
	
	Question inserQuestion(Question question);


	
	  /** 
	 * http://localhost:8080/updateQuestion
	  * 作者: 大娃   
	  * 邮件: 1558936588@qq.com  
	  * 时间: 2018年12月20日 下午9:21:14  
	  * 版本: V1.0   
	 */
	
	int updateQuestion(Question question);

}
