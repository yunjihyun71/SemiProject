package com.semi2.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.semi2.dao.EnrollDAO;
import com.semi2.dto.DTO;
import com.semi2.dto.PageInfo;
import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;


public class EnrollService {
	HttpServletRequest request =null;
	HttpServletResponse response =null;
	public EnrollService(HttpServletRequest request, HttpServletResponse response) 
			throws UnsupportedEncodingException  {
		this.request =request;
		this.response=response;
		//한글 깨짐 방지
		request.setCharacterEncoding("UTF-8");
	}

	/*이전 학기과목 평점조회 or 신 학기 수강신청 과목 조회 필터링으로 분류  */
	public void subjectSearch() throws IOException {
		String optValue  = request.getParameter("optValue");
		String inpValue = request.getParameter("inpValue");
		String term_id = request.getParameter("term_id");
		System.out.println(optValue+" / "+inpValue+" / "+term_id);
		//페이징을 하기 위해 현재 총 게시글이 몇개인지 구해야 함. 
		//cf)필터링으로 검색시 ,  조건에 따라 게시글 수가 다르기때문에 쿼리 구분해줄수 있는 조건을 매개변수로!
		EnrollDAO listCount = new EnrollDAO();
		int totalCount = listCount.listCount(optValue,inpValue,term_id);
		System.out.println("현재 총 게시글 수 : "+totalCount);
		//현재 페이지 , 한 페이지에 보여줄 게시글 수 , 한 페이지에 보여줄 페이지 수,총게시글  
		String paramPage =request.getParameter("page");
		int page ; //현재 페이지
		if(paramPage == null) {
			page = 1;
		}else {
			page = Integer.parseInt(paramPage);
		}
		PageInfo paging = new PageInfo(page, 5, 10, totalCount);
		int startNum =paging.getStartNum();
		int endNum = paging.getEndNum();
		System.out.println(startNum+" ~ "+endNum);

		//DB접속을 통해서 optSel에 맞는 쿼리문 구분.->데이터가 많으므로 DTO에 담음.
		EnrollDAO dao = new EnrollDAO();
		ArrayList<DTO> searchList = new ArrayList<DTO>();
		//startNum,endNum 을 추가해서 쿼리의 원하는 부분만 조회 예정
		searchList = dao.subjectSearch(optValue,inpValue,term_id,startNum,endNum);
		System.out.println("필터링 반환 받았나?");
		System.out.println(searchList);
		System.out.println("반환 개수: "+searchList.size());
		/*라이브러리에 추가한 Gson 형태로 반환  why ? ajax로 요청을 받을떄 json 타입으로 받는다고 했음
		 * 자바 언어로 그냥 response로 보내면 안될까? - JSON 은 자바스크립트 언어의 하나이기 때문에 java와 다르므로 변환이 필요*/
		//1.Gson 객체 생성
		Gson json = new Gson();
		//2. json 과 최대한 유사한 형태로 java를 변환 K:V
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("searchList", searchList);
		map.put("paging", paging);
		//3.변환
		String transJson= json.toJson(map);
		//4.response 로 반환
		//옵션  1 : 만약 한글이 들어가 있다면 한글깨짐 방지
		response.setContentType("text/html; charset=UTF-8");
		/*옵션  2: Cross domain 허용 : 서로 다른 IP 가 접속을 해도 접속을 허용 시킨다.
		 * (localhost내에서 사용할 경우에는 옵션 안써도되지만,
		여러 IP와 통신할 경우에 크로스 도메인을 허용시켜야 외부 Ip가 접속이 가능하다*/
		response.setHeader("Access-Control-Allow-Origin", "*");
		//필수  -- 요청 한 페이지로  getWriter()  transJson을 success | error 로 반환해줌
		response.getWriter().println(transJson);
	}

	/*로그인 학생의 수강 신청 과목 조회*/
	public void stdEnroll() throws IOException {
		String loginId = request.getParameter("loginId");
		String term_id = request.getParameter("term_id");
		//요청한 내용을 이용해 DB접속->수강 신청 과목 조회하기
		EnrollDAO dao =new EnrollDAO();
		//수강신청한 과목의 list를 받기 때문에 배열 변수를 선언하고 반환값을 담아주자
		ArrayList<DTO> list =new ArrayList<DTO>();
		list = dao.stdEnroll(loginId,term_id);
		HashMap<String, Object> map = new HashMap<String,Object>();
		map.put("searchList", list);
		System.out.println(list);
		Gson json = new Gson();
		String transJson = json.toJson(map);
		//response 로 반환
		response.setContentType("text/html; charset=UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.getWriter().println(transJson);
	}
	/*로그인 학생의 수강 과목 학점 조회*/
	public void stdCredit() throws IOException {
		String loginId  =request.getParameter("loginId");
		String term_id   =request.getParameter("term_id");
		//요청한 내용을 이용해 DB접속->수강 과목의 학점 조회하기
		EnrollDAO dao  =new EnrollDAO();
		//수강 과목의 학점을 반환 받아야 하기 때문에 받아줄 int 변수선언
		int stdCredit = dao.stdCredit(loginId,term_id);
		System.out.println(loginId+" 학생의 신청학점은? "+stdCredit);
		//ajax 에서 요청한 dataType으로 변환
		HashMap<String, Integer> map =new HashMap<String, Integer>();
		map.put("stdCredit", stdCredit);
		Gson json = new Gson();
		String transJson = json.toJson(map);
		//response 로 반환
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.getWriter().println(transJson);
	}
	/*로그인 학생의 수강 신청 */
	public void enroll() throws IOException {
		//ajax 로 호출 한 내용을 파라메터로 받기
		String loginId = request.getParameter("loginId");
		String[] tdValue  = request.getParameterValues("tdValue[]"); 
		System.out.println("request 개수 : "+tdValue.length);
		
		//Insert 하기 전에  로그인 학생의 학점이 20 미만일 경우에는 신청 불가 
		// ->로그인 학생의 학점 조회 (id와 현재학기를 기준)
		EnrollDAO CreditDao= new EnrollDAO();
		int stdCredit =CreditDao.stdCredit(loginId, tdValue[1]);
		
		
		//Insert 하기전에 신청한 (1)과목id or (2)강의시간 중복 여부 판단 
		// ->로그인 학생의 신청한 과목 조회(id와 현재학기를 기준) + flag 변수
		//flag = 0 일경우 과목 조회 쿼리 결과를 얻어오고
		// flag = 1 일 경우 강의시간 겹치는지 유무를 얻어옴    (2개의 메서드를 하나로 합침)
		//(1)
		EnrollDAO overLap1  = new EnrollDAO(); 
		ArrayList<Object> currentSub= new ArrayList<Object>(); //반환결과를 배열로담음
		int flag =1 ;
		currentSub = overLap1.overLapDAO(loginId,tdValue[1],flag);
		//(2) 
		//overLap1에서 자원반납이 이루어졌기 때문에 다시 객체화해서 커넥션 연결
		EnrollDAO overLap2  = new EnrollDAO();  
		flag =2 ;
		//enroll메서드에 매개변수를 각각 추가해야 하기 때문에 배열 2개 쓰는게 불가피.
		ArrayList<Object> currentTime= new ArrayList<Object>(); 
		currentTime = overLap2.overLapDAO(loginId,tdValue[1],flag);
		
		EnrollDAO currentCount = new EnrollDAO();  
		int count = currentCount.count(tdValue[0]);
		
		//해당 학생 id,과목 id를  기준으로 수강 신청 과목을 Insert ->DB 접속 필요
		EnrollDAO enrollDao =new EnrollDAO();
		int[] result = new int[5];
		result=  enrollDao.enroll(loginId,tdValue,stdCredit,currentSub,currentTime,count);
		
		//결과값을 JSON 형태로 반환해야함 
		Gson json = new Gson();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		String transJson = json.toJson(map);
		//response 로 반환
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.getWriter().println(transJson);

	}
	/*로그인 학생의 수강 정정 */
	public void enrollChange() throws IOException {
		String loginId = request.getParameter("loginId");
		String subject_id = request.getParameter("subject_id");
		//로그인 학생의 id 와 과목id를 기준으로 sql 구문을 실행시켜야 함 -> DB접속 필요
		EnrollDAO dao =new EnrollDAO();
		int success = dao.enrollChange(loginId,subject_id);
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		map.put("success", success);
		Gson json = new Gson();
		String transJson = json.toJson(map);
		//response 로 반환
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.getWriter().println(transJson);
	}



}
