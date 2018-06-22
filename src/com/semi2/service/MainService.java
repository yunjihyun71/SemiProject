package com.semi2.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.semi2.dao.MainDAO;
import com.semi2.dto.DTO;
import com.semi2.dto.PwDTO;

public class MainService extends PwDTO{
	HttpServletRequest request;
	HttpServletResponse response;

	public MainService(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		this.request = request;
		this.response = response;
	}

	//로그인
	public void login() throws IOException, ServletException {
		String id = request.getParameter("userId");
		String pw = request.getParameter("userPw");
		System.out.println(id+pw);
		//DB 작업이 필요 하기 때문에 BbsDAO 객체화.
		MainDAO dao = new MainDAO();
		Boolean result = dao.login(id,pw);
		DTO dto = new DTO();
		PwDTO pwdto = new PwDTO();
		pwdto.setLoginidDTO(id);
		
		System.out.println("pwdto.getLoginidDTO() : "+pwdto.getLoginidDTO());
		
		String msg = "아이디 또는 비밀번호를 확인해주세요";
		
		if(result==true) {
			System.out.println("로그인 성공");
			//JAVA에서 내장객체가 아니기 때문에 session 불러오자
			HttpSession session = request.getSession();
			session.setAttribute("loginId", id);
			session.setAttribute("loginPw", pw);
			if(id.startsWith("s")) {
				response.sendRedirect("smain");
			}else if(id.startsWith("p")) {
				response.sendRedirect("pmain");
			}else {
				response.sendRedirect("student");
			}
		}else {
			System.out.println("로그인 실패");
			request.setAttribute("msg", msg);
			RequestDispatcher dis =request.getRequestDispatcher("index.jsp");
			dis.forward(request, response);
		}
	}

	//로그아웃
	public void logout() throws IOException, ServletException {
		HttpSession session = request.getSession();
		session.removeAttribute("loginId");
		response.sendRedirect("index.jsp");
	}

	//학생 메인
	public void smain() throws IOException, ServletException {
		HttpSession session = request.getSession();
		response.sendRedirect("s01.jsp");
	}

	//교수 메인
	public void pmain() throws IOException, ServletException {
		HttpSession session = request.getSession();
		response.sendRedirect("p01_main.jsp");
	}

	//관리자 메인
	public void amain() throws IOException, ServletException {
		HttpSession session = request.getSession();
		response.sendRedirect("a01.jsp");
	}

	//새 비밀번호로 변경 
	public void passChange() throws IOException, ServletException{
		HttpSession session = request.getSession();
		
		System.out.println("이곳으로 오는가");
		
		request.setCharacterEncoding("UTF-8");//한글깨짐방지
		MainDAO dao = new MainDAO();
		String npw = request.getParameter("newPw");
		PwDTO pwdto = new PwDTO();
		pwdto.setnewPw(npw);
		pwdto.setLoginidDTO(request.getParameter("loginId"));
		
		System.out.println("받아온 newPw :"+npw);
		System.out.println("dto에서 받아온 newPw :"+pwdto.getnewPw());
		System.out.println("pwdto.getLoginidDTO() 2 : "+ pwdto.getLoginidDTO());
		
		DTO dto = new DTO();
		String npwcheck = request.getParameter("newPwCheck");
		System.out.println("npw : "+npw+"/"+"npwcheck : "+npwcheck);
		System.out.println("같은가 : " + npw.equals(npwcheck));
		if(npw.equals(npwcheck)) {
			if(dao.passChange(dto, pwdto)>0) {
				System.out.println("비밀번호 변경 완료");
				//location.href="index.jsp";
				response.sendRedirect("index.jsp");
			}else {
				System.out.println("실패");
				response.sendRedirect("m02.jsp");
			}
		}else {
			System.out.println("새 비밀번호가 일치하지 않습니다.");
			response.sendRedirect("m02.jsp");
		}		
	}

	//학사일정 조회
	public void dateEvent() throws IOException {

		String schedule = request.getParameter("schedule");
		//System.out.println(schedule+"sad");
		MainDAO dao = new MainDAO();
		ArrayList<DTO> dateList = dao.dateEvent(schedule);
		
		// map에 dto 담기
		HashMap<String, Object> map = new HashMap<>();
		map.put("dateList", dateList);
		
		// json 전송
		Gson gson = new Gson();
		String obj = gson.toJson(map);
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().println(obj);
	}
	
	//교수 과목 목록 가져오기
	public void selectProSubject() throws IOException {			
		String loginId = request.getParameter("loginId");
		
		MainDAO dao = new MainDAO();
		ArrayList<DTO> subjectList = dao.selectProSubject(loginId);
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("subjectList", subjectList);

		Gson gson = new Gson();
		String json = gson.toJson(map);
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().println(json);
	}
	
	//학생 과목 목록 가져오기
	public void selectStdSubject() throws IOException {			
		String loginId = request.getParameter("loginId");
		
		MainDAO dao = new MainDAO();
		ArrayList<DTO> subjectList = dao.selectStdSubject(loginId);
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("subjectList", subjectList);

		Gson gson = new Gson();
		String json = gson.toJson(map);
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().println(json);
	}
	
	// 강의계획서 조회(교수 페이지)
	public void plecturePlan() throws IOException {
		String loginId = request.getParameter("loginId");
		int subject = Integer.parseInt(request.getParameter("subject"));

		MainDAO dao = new MainDAO();
		DTO planDTO = new DTO();
		
		DTO dto = dao.plecturePlan(loginId,subject);
		
		// map에 dto 담기
		HashMap<String, Object> map = new HashMap<>();
		map.put("dto", dto);

		// json 전송
		Gson gson = new Gson();
		String json = gson.toJson(map);
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().println(json);
	}

	// 강의계획서 조회(학생 페이지)
	public void slecturePlan() throws IOException {
		String loginId = request.getParameter("loginId");
		int subject = Integer.parseInt(request.getParameter("subject"));

		MainDAO dao = new MainDAO();
		DTO planDTO = new DTO();
		planDTO.setStd_id(loginId);
		planDTO.setSubject_id(subject);
		DTO dto = dao.slecturePlan(planDTO);

		// map에 dto 담기
		HashMap<String, Object> map = new HashMap<>();
		map.put("dto", dto);

		// json 전송
		Gson gson = new Gson();
		String json = gson.toJson(map);
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().println(json);
	}

	//강의계획서 작성 요청(교수 페이지)
	public void lectureWrite() throws ServletException, IOException {
		DTO dto = new DTO();
		dto.setSubject_id(Integer.parseInt(request.getParameter("selected")));
		
		/*dto.setTerm_id(request.getParameter("term"));
		dto.setSubject_name(request.getParameter("subject_name"));
		dto.setStd_year(Integer.parseInt(request.getParameter("class")));
		dto.setSubject_type(request.getParameter("major_type"));
		dto.setSubject_credit(Integer.parseInt(request.getParameter("score")));
		dto.setPro_name(request.getParameter("pro"));
		dto.setSubject_time(request.getParameter("time"));
		dto.setPro_email(request.getParameter("email"));
		dto.setSubject_room(request.getParameter("classroom"));*/
		dto.setPlan_cu(request.getParameter("cu"));
		dto.setPlan_objective(request.getParameter("objective"));
		dto.setPlan_book(request.getParameter("planbook"));
		dto.setPlan_sub_book(request.getParameter("sub_book"));
		
		MainDAO dao = new MainDAO();
		if (dao.lectureWrite(dto) > 0) {
			
		}
		request.setAttribute("plan", dto);
		RequestDispatcher dis = request.getRequestDispatcher("p03.jsp");
		dis.forward(request, response);
		
	}

	//강의계획서 글 쓰기 폼 요청
	public void planWritePage() throws ServletException, IOException{
		String loginId = request.getParameter("loginId");
		int subject_id = Integer.parseInt(request.getParameter("subject_id"));
		System.out.println("수정 폼 아이디 : "+loginId);
		System.err.println("수정 폼 서브젝트 아이디 : "+subject_id);
		
		MainDAO dao = new MainDAO();
		DTO planDTO = new DTO();
		
		DTO dto = dao.plecturePlan(loginId,subject_id);
		request.setAttribute("dto", dto);
		RequestDispatcher dis = request.getRequestDispatcher("p03w.jsp");
		dis.forward(request, response);
	}
	
	
	//강의계획서를 수정하는 폼 요청 메서드
	public void planUpdatePage() throws ServletException, IOException {
		String loginId = request.getParameter("loginId");
		int subject_id = Integer.parseInt(request.getParameter("subject_id"));
		System.out.println("수정 폼 아이디 : "+loginId);
		System.err.println("수정 폼 서브젝트 아이디 : "+subject_id);
		
		MainDAO dao = new MainDAO();
		DTO planDTO = new DTO();
		
		DTO dto = dao.plecturePlan(loginId,subject_id);
		request.setAttribute("dto", dto);
		RequestDispatcher dis = request.getRequestDispatcher("p03u.jsp");
		dis.forward(request, response);
		
	}
	
	//강의계획서 수정 메서드
	public void planUpdate() throws ServletException, IOException {
		DTO dto = new DTO();
		dto.setSubject_id(Integer.parseInt(request.getParameter("selected")));
		
		/*dto.setTerm_id(request.getParameter("term"));
		dto.setSubject_name(request.getParameter("subject_name"));
		dto.setStd_year(Integer.parseInt(request.getParameter("class")));
		dto.setSubject_type(request.getParameter("major_type"));
		dto.setSubject_credit(Integer.parseInt(request.getParameter("score")));
		dto.setPro_name(request.getParameter("pro"));
		dto.setSubject_time(request.getParameter("time"));
		dto.setPro_email(request.getParameter("email"));
		dto.setSubject_room(request.getParameter("classroom"));*/
		dto.setPlan_cu(request.getParameter("cu"));
		dto.setPlan_objective(request.getParameter("objective"));
		dto.setPlan_book(request.getParameter("planbook"));
		dto.setPlan_sub_book(request.getParameter("sub_book"));
		
		MainDAO dao = new MainDAO();
		if (dao.planUpdate(dto) > 0) {
			
		}
		request.setAttribute("plan", dto);
		RequestDispatcher dis = request.getRequestDispatcher("p03.jsp");
		dis.forward(request, response);
		
	}


	
}

