package com.dbs.quiz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbs.quiz.beans.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer>{
	List<Quiz> findAll();
	//public Questionnarie createQuizQuestions(Questionnarie qstns) 

	Quiz findByQrCode(long l);

}
