package com.dbs.quiz.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dbs.quiz.beans.Quiz;
import com.dbs.quiz.beans.Questions;
import com.dbs.quiz.repository.QuestionsRepository;
import com.dbs.quiz.repository.QuizRepository;

@Component
public class QuizService {

	@Autowired
	QuizRepository qnRepos;
	@Autowired
	QuestionsRepository questionRepository;
	@Autowired
	QuizRepository quizRepo;
	
	public List<Questions> getQuestions(Integer qrCodeId) {
		try 
		{
			Quiz quiz = quizRepo.findByQrCode(qrCodeId);
			if(quiz != null) {
				List<Questions> questions = questionRepository.findByQuizId(quiz.getId());
				return questions;
			}
			
		} catch (Exception e) {
			System.out.println("Exception occured in getQuestions:" + e.getMessage());
		}
		return null;
	}
	
	public Quiz createQuizQuestions(Map<String, Object> inputJsonObj){
		try {
			
			 Quiz quizObj = new Quiz();
			 quizObj.setName((String) inputJsonObj.get("testName"));
			 quizObj.setTime((int) inputJsonObj.get("duration"));
			 quizObj.setQrCode(new Date().getTime());
			 List<Questions> qnList = new ArrayList<Questions>();
			 
			 List questionarries = (List) inputJsonObj.get("questionarie");
			 
			 for(Object obj : questionarries) {
				 Map qns = (Map) obj;
				 Questions qnObj = new Questions();
				 for (Object name : qns.keySet()) {
				 	qnObj.setQuestion((String) qns.get("question"));
				 	qnObj.setAnswer((String) qns.get("answer"));
				 	List options = (List) qns.get("options");
				 	qnObj.setOption1((String)options.get(0));
				 	qnObj.setOption2((String)options.get(1));
				 	qnObj.setOption3((String)options.get(2));
				 	qnObj.setOption4((String)options.get(3));
			 	}
				qnList.add(qnObj);
			 }
			 
			 quizObj.setQuestionList(qnList);
			 
			 Quiz q = qnRepos.save(quizObj);
			 return q;
		} 
		catch (Exception e) 
		{
			System.out.println("Exception occured in getQuestions:" + e.getMessage());
		}
		return null;
	}

}
