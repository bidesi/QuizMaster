package com.dbs.quiz.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbs.quiz.repository.LeaderBoardDaoImpl;

@Service
public class LeaderBoardServiceImpl {

	@Autowired
	LeaderBoardDaoImpl leaderDao;

	public List<?> getLeaderBoard(int qrCodeId) {
		try {
			return leaderDao.getLeaderBoardData(qrCodeId);
		} catch (Exception e) {
			System.out.println("Exception occured in getLeaderBoard:" + e.getMessage());
		}
		return null;
	}

	public boolean createScore(int qrCode, Map<String, String> ansMap, int time, int userID) {
		try {
			return leaderDao.getCreateScore(qrCode, ansMap, time, userID);
		} catch (Exception e) {
			System.out.println("Exception occured in getLeaderBoard:" + e.getMessage());
		}

		return false;
	}
}
