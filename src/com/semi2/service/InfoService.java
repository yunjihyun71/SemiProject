package com.semi2.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.semi2.dao.InfoDAO;
import com.semi2.dto.DTO;

public class InfoService {
	HttpServletRequest request;
	HttpServletResponse response;

	// 생성자
	public InfoService(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		this.request = request;
		this.response = response;
	}

	// 학생 신상조회
	public void sProfile() throws ServletException, IOException {
		String loginId = request.getParameter("loginId");

		InfoDAO dao = new InfoDAO();
		DTO dto = dao.sProfile(loginId);

		// map에 dto 담기
		HashMap<String, Object> map = new HashMap<>();
		map.put("dto", dto);

		// json 전송
		Gson gson = new Gson();
		String json = gson.toJson(map);
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().println(json);
	}

	// 학생 시간표 조회
	public void sTimetable() throws IOException {
		String loginId = request.getParameter("loginId");

		InfoDAO dao = new InfoDAO();
		ArrayList<DTO> list = dao.sTimetable(loginId);

		// map에 list 담기
		HashMap<String, Object> map = new HashMap<>();
		map.put("list", list);

		// json 전송
		Gson gson = new Gson();
		String json = gson.toJson(map);
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().println(json);
	}

	// 등록금 학기 가져오기
	public void tuitionTerm() throws IOException {
		String loginId = request.getParameter("loginId");
		
		InfoDAO dao = new InfoDAO();
		// 등록금 학기 리스트
		ArrayList<String> termList = dao.tuitionTerm(loginId);
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("termList", termList);

		Gson gson = new Gson();
		String json = gson.toJson(map);
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().println(json);
	}
	
	// 등록금고지서 조회
	public void tuition() throws IOException {
		String loginId = request.getParameter("loginId");
		String term = request.getParameter("term");

		InfoDAO dao = new InfoDAO();
		DTO sendDTO = new DTO();
		sendDTO.setStd_id(loginId);
		sendDTO.setTerm_id(term);
		
		DTO dto = dao.tuition(sendDTO);

		// map에 dto 담기
		HashMap<String, Object> map = new HashMap<>();
		map.put("dto", dto);

		// json 전송
		Gson gson = new Gson();
		String json = gson.toJson(map);
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().println(json);
	}

	// 장학금 조회
	public void scholar() throws IOException {
		String loginId = request.getParameter("loginId");

		InfoDAO dao = new InfoDAO();
		ArrayList<DTO> scholarList = dao.scholar(loginId);

		// map에 dto 담기
		HashMap<String, Object> map = new HashMap<>();
		map.put("scholarList", scholarList);

		// json 전송
		Gson gson = new Gson();
		String json = gson.toJson(map);
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().println(json);
	}

	// 성적조회
	public void score() throws IOException {
		String loginId = request.getParameter("loginId");

		InfoDAO dao = new InfoDAO();
		ArrayList<DTO> scoreList = dao.score(loginId);

		// map에 dto 담기
		HashMap<String, Object> map = new HashMap<>();
		map.put("scoreList", scoreList);

		// json 전송
		Gson gson = new Gson();
		String json = gson.toJson(map);
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().println(json);
	}

	// 학점계산기 페이지
	public void calPage() throws IOException {
		String loginId = request.getParameter("loginId");

		InfoDAO dao = new InfoDAO();
		ArrayList<DTO> subjectList = dao.calPage(loginId);

		// map에 dto 담기
		HashMap<String, Object> map = new HashMap<>();
		map.put("subjectList", subjectList);

		// json 전송
		Gson gson = new Gson();
		String json = gson.toJson(map);
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().println(json);
	}

	// 교수 시간표
	public void pTimetable() throws IOException {
		String loginId = request.getParameter("loginId");

		InfoDAO dao = new InfoDAO();
		ArrayList<DTO> list = dao.pTimetable(loginId);

		// map에 list 담기
		HashMap<String, Object> map = new HashMap<>();
		map.put("list", list);

		// json 전송
		Gson gson = new Gson();
		String json = gson.toJson(map);
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().println(json);
	}

	// 교수 신상정보
	public void pProfile() throws ServletException, IOException {
		String loginId = (String) request.getSession().getAttribute("loginId");

		InfoDAO dao = new InfoDAO();
		DTO dto = dao.pProfile(loginId);
		
		request.setAttribute("dto", dto);
		RequestDispatcher rd = request.getRequestDispatcher("p01.jsp");
		rd.forward(request, response);
	}

}
