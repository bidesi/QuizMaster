package com.dbs.quiz.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.quiz.service.LeaderBoardServiceImpl;

@RestController
public class LeaderBoardController {
	
	@Autowired
	LeaderBoardServiceImpl leaderBoardSrvc;

	@SuppressWarnings("unchecked")
	@GetMapping("/getLeaderBoardScore/{qrCodeId}")
	public ResponseEntity<List<?>> getLeaderBoardScore(@PathVariable int qrCodeId) {
		return new ResponseEntity(leaderBoardSrvc.getLeaderBoard(qrCodeId), HttpStatus.ACCEPTED);
	}

	@PostMapping("/createScore")
	public ResponseEntity createScore(@RequestBody Map<String, Object> inputJsonObj) throws NumberFormatException, JSONException {
		
		int qrCodeId = (int) inputJsonObj.get("quizId");
		List<Object> answers = (List) inputJsonObj.get("answers");
		int timeTaken =  (int) inputJsonObj.get("time");
		int userID = (int) inputJsonObj.get("userid");
		
		Map<String, String> ansMap = new HashMap<String, String>();
		
		for (Object answer : answers) {
			Map keyVals = (Map) answer;
			ansMap.put(keyVals.get("questionid").toString(), keyVals.get("answer").toString());
		}
		
		leaderBoardSrvc.createScore(qrCodeId, ansMap, timeTaken, userID);
		
		//System.out.println(qrCodeId + " " + ansMap + userID);
		
		return new ResponseEntity(true, HttpStatus.ACCEPTED);
	}
}
