package com.dbs.quiz.repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dbs.quiz.beans.DataObject;
import com.dbs.quiz.beans.LeaderBoard;
import com.dbs.quiz.beans.Questions;
import com.dbs.quiz.beans.Quiz;
import com.dbs.quiz.beans.User;


@Repository
@Transactional
public class LeaderBoardDaoImpl {
	@Autowired
	JdbcTemplate jdbc;
	
	@Autowired
	QuizRepository quizRepository;
	
	@Autowired
	QuestionsRepository questionsRepository;
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	LeaderBoardRepository leaderBoardRepository;

	// @Persistence
	public List<LeaderBoard> getLeaderBoardData(int qrCodeId) throws SQLException {
		List<LeaderBoard>  test = new ArrayList<LeaderBoard>();
		
		List<Map<String, Object>> list = jdbc.queryForList("select user_id, score, time from leader_board where qr_code = " + qrCodeId
				+ "  ORDER BY score desc, time asc ");
        for (Map<String, Object> row : list) {
        	LeaderBoard lbObj = new LeaderBoard();
        	lbObj.setUser((User) row.get("user_id"));
        	lbObj.setScore((Integer) row.get("score"));
        	lbObj.setTime((Long) row.get("time"));
        	test.add(lbObj);
        }   
		return test;
	}

	public boolean getCreateScore(int qrCode, Map<String, String> ansMap, int time, int userID) {
		try {
			Quiz quiz = quizRepository.findByQrCode(Long.parseLong(qrCode+""));
			List<Questions> qns = questionsRepository.findAllByQuizId(quiz.getId());
			
			//List<DataObject> rAnsMap = jdbc.queryForList("SELECT qn.id, qn.answer FROM questions qn INNER JOIN quiz q ON qn.quiz_id = q.id WHERE q.qr_code = " + qrCode, DataObject.class);
			int count = 0;
			for (Questions qn : qns) {
				if (qn.getAnswer().equals(ansMap.get(qn.getId()))) {
					count++;
				}
			}
			
			Optional<User> user = userRepository.findById(userID);
			LeaderBoard lbObj = new LeaderBoard();
			lbObj.setScore(count);
			lbObj.setTime((long) time);
			lbObj.setQrCode((long) qrCode);
			lbObj.setUser(user.get());
			leaderBoardRepository.save(lbObj);
			//jdbc.update("insert into leader_board (user_id, score, time, qr_code) values(" + userID+"," + count + "," + time + "," + qrCode + ")");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
