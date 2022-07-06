package com.dbs.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbs.quiz.beans.LeaderBoard;

@Repository
public interface LeaderBoardRepository extends JpaRepository<LeaderBoard, Integer> {

}
