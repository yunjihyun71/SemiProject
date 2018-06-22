package com.semi2.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.semi2.dao.AdminDAO;
import com.semi2.dto.DTO;


public class AdminService {
	HttpServletRequest request=null;
	HttpServletResponse response=null;
	
	public AdminService(HttpServletRequest request,HttpServletResponse response){
		this.request =request;
		this.response=response;
	}
	/**학생 리스트 출력***************/
	public void sManagePage() throws ServletException, IOException {
		AdminDAO dao = new AdminDAO();
		ArrayList<DTO> sManagePage=dao.sManagePage();
		request.setAttribute("list", sManagePage);
		RequestDispatcher dis = request.getRequestDispatcher("a01.jsp");
		dis.forward(request, response);
	}
	/**수정************************************/
	public void sUpdate() throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		String std_id =request.getParameter("std_id");
		int std_year = Integer.parseInt(request.getParameter("std_year"));
		String std_name = request.getParameter("std_name");
		String std_birthday = request.getParameter("std_birthday");
		String std_state = request.getParameter("std_state");
		String std_phone = request.getParameter("std_phone");
		String std_email = request.getParameter("std_email");
		String std_address = request.getParameter("std_address");
		AdminDAO dao = new AdminDAO();
		DTO dto = new DTO();
		dto.setStd_id(std_id);
		dto.setStd_year(std_year);
		dto.setStd_name(std_name);
		dto.setStd_birthday(std_birthday);
		dto.setStd_state(std_state);
		dto.setStd_phone(std_phone);
		dto.setStd_email(std_email);
		dto.setStd_address(std_address);
		// 데이터 수정
		int success = dao.sUpdate(dto);
		// 결과 확인
		if (success > 0) {
			System.out.println("성공");	
			request.setAttribute("update", "수정 완료!!");
			RequestDispatcher rd = request.getRequestDispatcher("student");
			rd.forward(request, response);
		} else {
			request.setAttribute("update", "수정 실패!!");
			RequestDispatcher rd = request.getRequestDispatcher("a01_UpdatePage.jsp");
			rd.forward(request, response);
		}
				
	}
	/**수정폼********************/
	public void sUpdatePage() throws ServletException, IOException {
		String studentId =request.getParameter("std_id");
		AdminDAO dao = new AdminDAO();
		DTO dto = dao.sUpdatePage(studentId);
		if (dto != null) {
			request.setAttribute("form", dto);
		} else {
			System.out.println("오류");
		}
		RequestDispatcher rd = request.getRequestDispatcher("a01_UpdatePage.jsp");
		rd.forward(request, response);
	}
	/**등록***************************/
	public void sAdd() throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		AdminDAO dao =new AdminDAO();
		DTO dto= new DTO();
		dto.setStd_id(request.getParameter("std_id"));
		dto.setStd_year(Integer.parseInt(request.getParameter("std_year")));
		dto.setStd_name(request.getParameter("std_name"));
		dto.setStd_birthday(request.getParameter("std_birthday"));
		dto.setStd_state(request.getParameter("std_state"));
		dto.setStd_phone(request.getParameter("std_phone"));
		dto.setStd_email(request.getParameter("std_email"));
		dto.setStd_address(request.getParameter("std_address"));
		dto.setStd_pw(request.getParameter("std_pw"));
		dto.setMajor_id(request.getParameter("major_id"));
		if(dao.sAdd(dto)>0) {
			System.out.println("저장완료");
		}
		RequestDispatcher dis = request.getRequestDispatcher("student");
		dis.forward(request, response);
		
	}
	/**삭제********************************/
	public void sDel() throws ServletException, IOException{
		String std_id= request.getParameter("std_id");
		AdminDAO dao = new AdminDAO();
		if(dao.sDel(std_id) >0) {
			System.out.println("삭제성공");
		}
		RequestDispatcher dis = request.getRequestDispatcher("student");
		dis.forward(request, response);
	}
	/**검색*********************************/
	public void sSearch() throws ServletException, IOException{
		String selectbox = request.getParameter("selectbox");
		String val=request.getParameter("val");
		AdminDAO dao = new AdminDAO();
		ArrayList<DTO> list=dao.sSearch(selectbox,val);
		request.setAttribute("search", list);
		RequestDispatcher dis = request.getRequestDispatcher("a01.jsp");
		dis.forward(request, response);
	}
	/**등록금 리스트 ***************************/
	public void tMangePage() throws ServletException, IOException {
		AdminDAO dao = new AdminDAO();
		ArrayList<DTO> tMangePage=dao.tMangePage();
		request.setAttribute("list", tMangePage);
		RequestDispatcher dis = request.getRequestDispatcher("a02_index.jsp");
		dis.forward(request, response);
	}
	/**등록금 수정폼********************/
	public void tUpdatePage() throws ServletException, IOException {
		String studentId =request.getParameter("std_id");
		String term_id = request.getParameter("term_id");
		AdminDAO dao = new AdminDAO();
		DTO dto = dao.tUpdatePage(studentId,term_id);
		if (dto != null) {
			request.setAttribute("form", dto);
		} else {
			System.out.println("오류");
		}
		RequestDispatcher rd = request.getRequestDispatcher("a02_update.jsp");
		rd.forward(request, response);
	}
	/**등록금 수정 ********************/
	public void tUpdate() throws ServletException, IOException {
		String std_id =request.getParameter("std_id");
		int tuition_money=Integer.parseInt(request.getParameter("tuition_money"));
		String term_id = request.getParameter("term_id");
		AdminDAO dao = new AdminDAO();
		DTO dto = new DTO();
		dto.setStd_id(std_id);
		dto.setTuition_money(tuition_money);
		dto.setTerm_id(term_id);
		// 데이터 수정
		int success = dao.tUpdate(dto);
		// 결과 확인
		if (success > 0) {
			System.out.println("성공");
			RequestDispatcher rd = request.getRequestDispatcher("aTuition");
			rd.forward(request, response);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("a02_update.jsp");
			rd.forward(request, response);
		}
	}
	/**등록금 삭제***********/
	public void tDell() throws ServletException, IOException {
		int tuition_id=Integer.parseInt(request.getParameter("tuition_id"));
		AdminDAO dao = new AdminDAO();
		if(dao.tDell(tuition_id) >0) {
			System.out.println("삭제성공");
		}
		RequestDispatcher dis = request.getRequestDispatcher("aTuition");
		dis.forward(request, response);
	}
	/**등록금 등록****************/
	public void tAdd() throws ServletException, IOException {
		AdminDAO dao =new AdminDAO();
		DTO dto= new DTO();
		dto.setTerm_id(request.getParameter("term_id"));
		dto.setScholar_id(Integer.parseInt(request.getParameter("scholar_id")));
		dto.setStd_id(request.getParameter("std_id"));
		dto.setTuition_money(Integer.parseInt(request.getParameter("tuition_money")));
		if(dao.tAdd(dto)>0) {
			System.out.println("저장완료");
		}
		RequestDispatcher dis = request.getRequestDispatcher("aTuition");
		dis.forward(request, response);
		
	}
	/** 등록금 검색 ********************/
	public void tSearch() throws ServletException, IOException {
		String selectbox = request.getParameter("selectbox");
		String val=request.getParameter("val");
		AdminDAO dao = new AdminDAO();
		ArrayList<DTO> list=dao.tSearch(selectbox,val);
		request.setAttribute("search", list);
		//request.setAttribute("result", result);
		RequestDispatcher dis = request.getRequestDispatcher("a02_index.jsp");
		dis.forward(request, response);
	}
	/**장학금 리스트**********************/
	public void scPage() throws ServletException, IOException{
		AdminDAO dao = new AdminDAO();
		ArrayList<DTO> list=dao.scPage(); 
		request.setAttribute("list", list);
		RequestDispatcher dis = request.getRequestDispatcher("a03_index.jsp");
		dis.forward(request, response);
	}
	/**장학금 수정페이지**********************/
	public void scUpdatePage() throws ServletException, IOException{
		int scholar_id =Integer.parseInt(request.getParameter("scholar_id"));
		AdminDAO dao = new AdminDAO();
		DTO dto = dao.scUpdatePage(scholar_id);
		if (dto != null) {
			request.setAttribute("form", dto);
		} else {
			System.out.println("오류");
		}
		RequestDispatcher rd = request.getRequestDispatcher("a03_update.jsp");
		rd.forward(request, response);
	}
	/**장학금 수정**********************/
	public void scUpdate() throws ServletException, IOException{
		int scholar_id =Integer.parseInt(request.getParameter("scholar_id"));
		String scholar_name=request.getParameter("scholar_name");
		int scholar_money =Integer.parseInt(request.getParameter("scholar_money"));
		AdminDAO dao = new AdminDAO();
		DTO dto = new DTO();
		dto.setScholar_id(scholar_id);
		dto.setScholar_name(scholar_name);
		dto.setScholar_money(scholar_money);
		// 데이터 수정
		int success = dao.scUpdate(dto);
		// 결과 확인
		if (success > 0) {
			System.out.println("성공");
			RequestDispatcher rd = request.getRequestDispatcher("scScholar");
			rd.forward(request, response);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("a03_update.jsp");
			rd.forward(request, response);
		}
	}
	/**장학금 삭제**********************/
	public void scDel()  throws ServletException, IOException{
		int scholar_id =Integer.parseInt(request.getParameter("scholar_id"));
		AdminDAO dao = new AdminDAO();
		if(dao.scDel(scholar_id) >0) {
			System.out.println("삭제성공");
		}
		RequestDispatcher dis = request.getRequestDispatcher("scScholar");
		dis.forward(request, response);
		
	}
	/**장학금 등록**********************/
	public void scAdd() throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		AdminDAO dao =new AdminDAO();
		DTO dto= new DTO();
		dto.setScholar_name(request.getParameter("scholar_name"));
		dto.setScholar_money(Integer.parseInt(request.getParameter("scholar_money")));
		if(dao.scAdd(dto)>0) {
			System.out.println("저장완료");
		}
		RequestDispatcher dis = request.getRequestDispatcher("scScholar");
		dis.forward(request, response);
	}
	
	/**장학금 관리 리스트********************/
	public void ePage()  throws ServletException, IOException {
		AdminDAO dao = new AdminDAO();
		ArrayList<DTO> ePage=dao.ePage();
		request.setAttribute("list", ePage);
		RequestDispatcher dis = request.getRequestDispatcher("a04_index.jsp");
		dis.forward(request, response);
	}
	/**장학금 관리 등록************/
	public void eAdd() throws ServletException, IOException {
		AdminDAO dao =new AdminDAO();
		DTO dto= new DTO();
		dto.setTerm_id(request.getParameter("term_id"));
		dto.setScholar_id(Integer.parseInt(request.getParameter("scholar_id")));
		dto.setStd_id(request.getParameter("std_id"));
		if(dao.eAdd(dto)>0) {
			System.out.println("저장완료");
		}
		RequestDispatcher dis = request.getRequestDispatcher("ePage");
		dis.forward(request, response);
	}
	/**장학금 관리 삭제************/
	public void eDel() throws ServletException, IOException {
		int tuition_id=Integer.parseInt(request.getParameter("tuition_id"));
		AdminDAO dao = new AdminDAO();
		if(dao.tDell(tuition_id) >0) {
			System.out.println("삭제성공");
		}
		RequestDispatcher dis = request.getRequestDispatcher("ePage");
		dis.forward(request, response);
		
	}
	/**장학금 관리 수정 폼***************/
	public void eUpdatePage() throws ServletException, IOException {
		int tuition_id =Integer.parseInt(request.getParameter("tuition_id"));
		AdminDAO dao = new AdminDAO();
		DTO dto = dao.eUpdatePage(tuition_id);
		if (dto != null) {
			request.setAttribute("form", dto);
		} else {
			System.out.println("오류");
		}
		RequestDispatcher rd = request.getRequestDispatcher("a04_update.jsp");
		rd.forward(request, response);
	}	
	/**장학금 관리 수정 ***************/
	public void eUpdate() throws ServletException, IOException {
		int scholar_id=Integer.parseInt(request.getParameter("scholar_id"));
		String term_id = request.getParameter("term_id");
		int tuition_id=Integer.parseInt(request.getParameter("tuition_id"));
		AdminDAO dao = new AdminDAO();
		DTO dto = new DTO();
		dto.setScholar_id(scholar_id);
		dto.setTerm_id(term_id);
		dto.setTuition_id(tuition_id);
		// 데이터 수정
		int success = dao.eUpdate(dto);
		// 결과 확인
		if (success > 0) {
			System.out.println("성공");
			RequestDispatcher rd = request.getRequestDispatcher("ePage");
			rd.forward(request, response);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("a04_update.jsp");
			rd.forward(request, response);
		}
	}
	/**장학금 관리 검색 ******************/
	public void eSearch() throws ServletException, IOException {
		String selectbox = request.getParameter("selectbox");
		String val=request.getParameter("val");
		AdminDAO dao = new AdminDAO();
		ArrayList<DTO> list=dao.eSearch(selectbox,val);
		request.setAttribute("search", list);
		RequestDispatcher dis = request.getRequestDispatcher("a04_index.jsp");
		dis.forward(request, response);
	}
	/**교수 리스트******************/
	public void pManagePage() throws ServletException, IOException {
		AdminDAO dao = new AdminDAO();
		ArrayList<DTO> pManagePage=dao.pManagePage();
		request.setAttribute("list", pManagePage);
		RequestDispatcher dis = request.getRequestDispatcher("a05_index.jsp");
		dis.forward(request, response);
	}
	/**교수 수정페이지******************/
	public void pUpdatePage() throws ServletException, IOException {
		String pro_id =request.getParameter("pro_id");
		AdminDAO dao = new AdminDAO();
		DTO dto = dao.pUpdatePage(pro_id);
		if (dto != null) {
			request.setAttribute("form", dto);
		} else {
			System.out.println("오류");
		}
		RequestDispatcher rd = request.getRequestDispatcher("a05_UpdatePage.jsp");
		rd.forward(request, response);
	}
	/**교수 수정******************/
	public void pUpdate() throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String pro_id =request.getParameter("pro_id");
		String pro_name = request.getParameter("pro_name");
		String pro_phone = request.getParameter("pro_phone");
		String pro_email = request.getParameter("pro_email");
		String pro_room = request.getParameter("pro_room");
		String major_id=request.getParameter("major_id");
		AdminDAO dao = new AdminDAO();
		DTO dto = new DTO();
		dto.setPro_id(pro_id);
		dto.setPro_name(pro_name);
		dto.setPro_email(pro_email);
		dto.setPro_phone(pro_phone);
		dto.setPro_room(pro_room);
		dto.setMajor_id(major_id);
		// 데이터 수정
		int success = dao.pUpdate(dto);
		// 결과 확인
		if (success > 0) {
			System.out.println("성공");	
			request.setAttribute("update", "수정 완료!!");
			RequestDispatcher rd = request.getRequestDispatcher("pManagePage");
			rd.forward(request, response);
		} else {
			request.setAttribute("update", "수정 실패!!");
			RequestDispatcher rd = request.getRequestDispatcher("a05_UpdatePage.jsp");
			rd.forward(request, response);
		}
	}
	/** 교수 삭제********/
	public void pDel() throws ServletException, IOException {
		String pro_id= request.getParameter("pro_id");
		AdminDAO dao = new AdminDAO();
		if(dao.pDel(pro_id) >0) {
			System.out.println("삭제성공");
		}
		RequestDispatcher dis = request.getRequestDispatcher("pManagePage");
		dis.forward(request, response);
	}
	/** 교수 등록********/
	public void pAdd() throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		AdminDAO dao =new AdminDAO();
		DTO dto= new DTO();
		dto.setPro_id(request.getParameter("pro_id"));
		dto.setPro_pw(request.getParameter("pro_pw"));
		dto.setPro_name(request.getParameter("pro_name"));
		dto.setPro_email(request.getParameter("pro_email"));
		dto.setPro_phone(request.getParameter("pro_phone"));
		dto.setPro_room(request.getParameter("pro_room"));
		dto.setMajor_id(request.getParameter("major_id"));
		if(dao.pAdd(dto)>0) {
			System.out.println("저장완료");
		}
		RequestDispatcher dis = request.getRequestDispatcher("pManagePage");
		dis.forward(request, response);
	}
	/** 교수 검색********/
	public void pSearch() throws ServletException, IOException {
		String selectbox = request.getParameter("selectbox");
		String val=request.getParameter("val");
		AdminDAO dao = new AdminDAO();
		ArrayList<DTO> list=dao.pSearch(selectbox,val);
		request.setAttribute("search", list);
		RequestDispatcher dis = request.getRequestDispatcher("a05_index.jsp");
		dis.forward(request, response);
	}
	/** 과목 리스트********/
	public void suManagePage() throws ServletException, IOException {
		AdminDAO dao = new AdminDAO();
		ArrayList<DTO> suManagePage=dao.suManagePage();
		request.setAttribute("list", suManagePage);
		RequestDispatcher dis = request.getRequestDispatcher("a06_index.jsp");
		dis.forward(request, response);
	}
	/** 과목 삭제********/
	public void suDel() throws ServletException, IOException {
		String pro_id= request.getParameter("pro_id");
		String subject_time=request.getParameter("subject_time");
		AdminDAO dao = new AdminDAO();
		if(dao.suDel(pro_id,subject_time) >0) {
			System.out.println("삭제성공");
		}
		RequestDispatcher dis = request.getRequestDispatcher("suManagePage");
		dis.forward(request, response);
	}
	/** 과목 추가********/
	public void suAdd() throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		AdminDAO dao =new AdminDAO();
		DTO dto= new DTO();
		dto.setMajor_id(request.getParameter("major_id"));
		dto.setSubject_id(Integer.parseInt(request.getParameter("subject_id")));
		dto.setPro_id(request.getParameter("pro_id"));
		dto.setSubject_room(request.getParameter("subject_room"));
		dto.setSubject_name(request.getParameter("subject_name"));
		dto.setSubject_time(request.getParameter("subject_time"));
		dto.setSubject_type(request.getParameter("subject_type"));
		dto.setSubject_credit(Integer.parseInt(request.getParameter("subject_credit")));
		dto.setSubject_limit(Integer.parseInt(request.getParameter("subject_limit")));
		dto.setSubject_grade(Double.parseDouble(request.getParameter("subject_grade")));
		dto.setTerm_id(request.getParameter("term_id"));
		dto.setSubject_count(Integer.parseInt(request.getParameter("subject_limit")));
		if(dao.suAdd(dto)>0) {
			System.out.println("저장완료");
		}
		RequestDispatcher dis = request.getRequestDispatcher("suManagePage");
		dis.forward(request, response);
	}
	/**과목 수정 페이지*****************/
	public void suUpdatePage() throws ServletException, IOException {
		String pro_id =request.getParameter("pro_id");
		String subject_time=request.getParameter("subject_time");
		AdminDAO dao = new AdminDAO();
		DTO dto = dao.suUpdatePage(pro_id,subject_time);
		if (dto != null) {
			request.setAttribute("form", dto);
		} else {
			System.out.println("오류");
		}
		RequestDispatcher rd = request.getRequestDispatcher("a06_update.jsp");
		rd.forward(request, response);
	}
	/**과목 수정 *****************/
	public void suUpdate() throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String major_id =request.getParameter("major_id");
		int subject_id=Integer.parseInt(request.getParameter("subject_id"));
		String pro_id =request.getParameter("pro_id");
		String subject_room =request.getParameter("subject_room");
		String subject_name =request.getParameter("subject_name");
		String subject_time =request.getParameter("subject_time");
		String subject_type =request.getParameter("subject_type");
		int subject_credit=Integer.parseInt(request.getParameter("subject_credit"));
		int subject_limit=Integer.parseInt(request.getParameter("subject_limit"));
		Double subject_grade=Double.parseDouble(request.getParameter("subject_grade"));
		String term_id =request.getParameter("term_id");
		AdminDAO dao = new AdminDAO();
		DTO dto = new DTO();
		dto.setMajor_id(major_id);
		dto.setSubject_id(subject_id);
		dto.setPro_id(pro_id);
		dto.setSubject_room(subject_room);
		dto.setSubject_name(subject_name);
		dto.setSubject_time(subject_time);
		dto.setSubject_type(subject_type);
		dto.setSubject_credit(subject_credit);
		dto.setSubject_limit(subject_limit);
		dto.setSubject_grade(subject_grade);
		dto.setTerm_id(term_id);
		int success = dao.suUpdate(dto);
		if (success > 0) {
			System.out.println("성공");	
			request.setAttribute("update", "수정 완료!!");
			RequestDispatcher rd = request.getRequestDispatcher("suManagePage");
			rd.forward(request, response);
		} else {
			request.setAttribute("update", "수정 실패!!");
			RequestDispatcher rd = request.getRequestDispatcher("a06_update.jsp");
			rd.forward(request, response);
		}
	}
	/**과목 검색*************/
	public void suSearch() throws ServletException, IOException {
		String selectbox = request.getParameter("selectbox");
		String val=request.getParameter("val");
		AdminDAO dao = new AdminDAO();
		ArrayList<DTO> list=dao.suSearch(selectbox,val);
		request.setAttribute("search", list);
		RequestDispatcher dis = request.getRequestDispatcher("a06_index.jsp");
		dis.forward(request, response);
	}
	/**강의평가 질문 리스트*************/
	public void gPage() throws ServletException, IOException {
		AdminDAO dao = new AdminDAO();
		ArrayList<DTO> gPage=dao.gPage();
		request.setAttribute("list", gPage);
		RequestDispatcher dis = request.getRequestDispatcher("a07_index.jsp");
		dis.forward(request, response);
	}
	/**강의평가 질문 등록*************/
	public void gAdd() throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		AdminDAO dao =new AdminDAO();
		DTO dto= new DTO();
		dto.setQuestion_question(request.getParameter("question_question"));
		if(dao.gAdd(dto)>0) {
			System.out.println("저장완료");
		}
		RequestDispatcher dis = request.getRequestDispatcher("gPage");
		dis.forward(request, response);
	}
	/**강의평가 질문 등록 수정페이지 *************/
	public void gUpdatePage() throws ServletException, IOException {
		int question_id =Integer.parseInt(request.getParameter("question_id"));
		AdminDAO dao = new AdminDAO();
		DTO dto = dao.gUpdatePage(question_id);
		if (dto != null) {
			request.setAttribute("form", dto);
		} else {
			System.out.println("오류");
		}
		RequestDispatcher rd = request.getRequestDispatcher("a07_update.jsp");
		rd.forward(request, response);
	}
	/**강의평가 질문 등록 수정 *************/
	public void gUpdate() throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int question_id = Integer.parseInt(request.getParameter("question_id"));
		String question_question=request.getParameter("question_question");
		AdminDAO dao = new AdminDAO();
		DTO dto = new DTO();
		dto.setQuestion_id(question_id);
		dto.setQuestion_question(question_question);
		int success = dao.gUpdate(dto);
		if (success > 0) {
			System.out.println("성공");	
			request.setAttribute("update", "수정 완료!!");
			RequestDispatcher rd = request.getRequestDispatcher("gPage");
			rd.forward(request, response);
		} else {
			request.setAttribute("update", "수정 실패!!");
			RequestDispatcher rd = request.getRequestDispatcher("a07_update.jsp");
			rd.forward(request, response);
		}
	}
	/**강의평가 질문 삭제 *************/
	public void gDel() throws ServletException, IOException {
		int question_id= Integer.parseInt(request.getParameter("question_id"));
		AdminDAO dao = new AdminDAO();
		if(dao.gDel(question_id) >0) {
			System.out.println("삭제성공");
		}
		RequestDispatcher dis = request.getRequestDispatcher("gPage");
		dis.forward(request, response);
	}
	/**학사 일정 등록*************/
	public void caAdd() throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String getdate =request.getParameter("getdate");
		String content=request.getParameter("content");
		System.out.println(getdate);
		AdminDAO dao =new AdminDAO();
		DTO dto= new DTO();
		dto.setSchedule_date(request.getParameter("schedule_date"));
		if(dao.caAdd(dto,content,getdate)>0) {
			System.out.println("저장완료");
		}
		response.sendRedirect("a08_index.jsp");
	}
	/**학사 일정 삭제 **********************************/
	public void caDell() throws ServletException, IOException {
		int schedule_id=Integer.parseInt(request.getParameter("schedule_id"));
		System.out.println(schedule_id);
		AdminDAO dao = new AdminDAO();
		boolean success= false;
		
		if(dao.caDell(schedule_id) >0) {
			System.out.println("삭제성공");
		}
		Gson json = new Gson();
		HashMap<String, Boolean> map = new HashMap<>();
		map.put("success", success);
		String obj = json.toJson(map);
		response.getWriter().println(obj);
		response.sendRedirect("a08_index.jsp");
	}
	/**학사 일정 수정 **********************************/
	public void caUpdate() throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int schedule_id=Integer.parseInt(request.getParameter("schedule_id"));
		String schedule_content=request.getParameter("content");
		AdminDAO dao = new AdminDAO();
		
		System.out.println(schedule_content+"asdweqewq");
		Gson json = new Gson();
		HashMap<String, Integer> map = new HashMap<>();
		map.put("success", dao.caUpdate(schedule_id,schedule_content));
		String obj = json.toJson(map);
		response.getWriter().println(obj);
		response.sendRedirect("a08_index.jsp");
	}
}
