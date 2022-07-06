package com.dbs.quiz.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.quiz.beans.Questions;
import com.dbs.quiz.beans.Quiz;
import com.dbs.quiz.service.QuizService;

@RestController
public class QuizController {

	@Autowired
	QuizService qstnrSrvc;

	@GetMapping("/getQuizQuestions/{qrCodeId}")
	public List<Questions> getQuizQuestions(@PathVariable Integer qrCodeId) {
		ResponseEntity resp = null;
		try {
			List<Questions> list = qstnrSrvc.getQuestions(qrCodeId);
			resp = new ResponseEntity(list, HttpStatus.OK);
			System.out.println(list);
			return list;
		} catch (Exception e) {
			System.out.println("Exception occured in getQuizQuestions:" + e.getMessage());
		}
		return null;
	}

	
	@PostMapping("/createQuestionnaire")
	public Long createQuestionnaire(@RequestBody Map<String, Object> inputJsonObj){
		try 
		{
			//Quiz quiz = (Quiz) CommonUtils.getObjectFromJSONText(Quiz.class, reqBody); 
			Quiz q = qstnrSrvc.createQuizQuestions(inputJsonObj);
			return q.getQrCode();
		} 
		catch (Exception e) 
		{
			System.out.println("Exception occured in getQuizQuestions:" + e.getMessage());
		}
		return 0l;
	}
}
