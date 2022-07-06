package com.dbs.quiz.repository;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dbs.quiz.beans.Questions;


@Repository
@Transactional
public class QuestionDaoImpl 
{
	@Autowired
	JdbcTemplate jdbc;
	//@Persistence
	public List<Questions> getQuestions(String qrCodeId)
	throws SQLException
	{
//		private int qrCodeId;
//		private String question;
//		//private int questionId;
//		private String option1;
//		private String option2;
//		private String option3;
//		private String option4;
//		private String answer;
//		//private String time;
		
		return jdbc.queryForList("select id, qr_code, question, option1, option2, option3, option4, answer from questionnarie where qr_code="+qrCodeId, Questions.class);
	}
	/*
	 * public List<Questions> createQuestions(Questions qstns) throws SQLException {
	 * String sql=
	 * 
	 * return jdbc.e }
	 */
}

