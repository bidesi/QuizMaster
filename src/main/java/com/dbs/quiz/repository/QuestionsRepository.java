package com.dbs.quiz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbs.quiz.beans.Questions;

@Repository
public interface QuestionsRepository extends JpaRepository<Questions, Integer> {
	
	List<Questions> findAll();

	List<Questions> findByQuizId(Integer id);

	List<Questions> findAllByQuizId(Integer id);

	//List<Questions> findByClientId(Long id);
}
