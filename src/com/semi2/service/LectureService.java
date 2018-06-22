package com.semi2.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.semi2.dao.LectureDAO;
import com.semi2.dto.DTO;

public class LectureService {
	HttpServletRequest request;
	HttpServletResponse response;

	// 생성자
	public LectureService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}
	
	// 수강생조회 페이지(강의 과목 리스트 반환)
	public void studentSearchPage() throws IOException, ServletException {
		String loginId = (String) request.getSession().getAttribute("loginId");

		LectureDAO dao = new LectureDAO();
		ArrayList<DTO> subjectList = dao.studentSearchPage(loginId);

		request.setAttribute("subjectList", subjectList);
		RequestDispatcher rd = request.getRequestDispatcher("p09.jsp");
		rd.forward(request, response);
	}

	// 수강생, 성적(없을 수도있음) 조회
	public void studentSearch() throws IOException {
		String subjectId = request.getParameter("subjectId");
		
		LectureDAO dao = new LectureDAO();
		ArrayList<DTO> studentList = dao.studentSearch(subjectId);
		dao = new LectureDAO();
		ArrayList<DTO> scoreList = dao.scoreSearch(subjectId);
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("studentList", studentList); // 수강생 리스트
		map.put("scoreList", scoreList); // 성적 리스트(성적 등록을 하지 않은 경우 없음)
		
		Gson gson = new Gson();
		String json = gson.toJson(map);
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().println(json);
	}

	// 성적등록 페이지(수강생조회 페이지와 코드 같음. 이동하는 페이지만 다름)
	public void scoreRegistPage() throws ServletException, IOException {
		String loginId = (String) request.getSession().getAttribute("loginId");

		LectureDAO dao = new LectureDAO();
		ArrayList<DTO> subjectList = dao.studentSearchPage(loginId);

		request.setAttribute("subjectList", subjectList);
		RequestDispatcher rd = request.getRequestDispatcher("p10.jsp");
		rd.forward(request, response);
	}

	// 성적등록
	public void scoreRegist() throws ServletException, IOException {
		// 파라미터 모두 받기
		Enumeration<String> enu = request.getParameterNames();
		// 모든 파라미터 이름을 담을 리스트
		ArrayList<String> paramNames = new ArrayList<>();
		while (enu.hasMoreElements()) {
			paramNames.add(enu.nextElement());
		}
		// 모든 학점과 성적을 담을 리스트
		ArrayList<DTO> scoreList = new ArrayList<>();
		// 과목 ID
		int subject_id = 0;
		
		for (String name : paramNames) {
			DTO dto = new DTO();
			// 파라미터가 과목 ID이면
			if (name.equals("subject_id")) {
				subject_id = Integer.parseInt(request.getParameter("subject_id"));
			} 
			// 파라미터가 학생 : 성적 이면
			else {
				dto.setStd_id(name); // 학번
				dto.setScore_score(request.getParameter(name)); // 성적
				scoreList.add(dto);
			}
		}
		
		// 성적 DB에 반영
		LectureDAO dao = new LectureDAO();
		int success = dao.scoreRegist(subject_id, scoreList);
		
		// 결과 전송
		request.setAttribute("success", success);
		RequestDispatcher rd = request.getRequestDispatcher("scoreRegistPage?subject_id=" + subject_id);
		rd.forward(request, response);
	}

}
