package com.dbs.quiz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dbs.quiz.beans.Questions;
import com.dbs.quiz.repository.QuestionDaoImpl;
import com.dbs.quiz.repository.QuestionsRepository;

@Component
public class QuestionServiceImpl 
{
	@Autowired
	QuestionDaoImpl qstnDao;
	
	@Autowired
	QuestionsRepository qnRep;
	
	public List<Questions> getQuestions(String qrCodeId)
	{
		try
		{
			//return qstnDao.getQuestions(qrCodeId);
			List<Questions> qnOptions = qnRep.findAll();
			//return qnRep.findById(qrCodeId);
			return qnOptions;
		}
		catch(Exception e)
		{
			System.out.println("Exception occured in getQuestions:"+e.getMessage());
		}
		return null;
	}
	
	public List<Questions> createQuizQuestions(Questions qstns)
	{
		try
		{
			//return qstnDao.createQuestions(qrCodeId);
		}
		catch(Exception e)
		{
			System.out.println("Exception occured in getQuestions:"+e.getMessage());
		}
		return null;
	}
}
