package com.semi2.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi2.service.AdminService;
import com.semi2.service.BbsService;
import com.semi2.service.EnrollService;
import com.semi2.service.InfoService;
import com.semi2.service.LectureService;
import com.semi2.service.MainService;

@WebServlet("/")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		dual(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		dual(request, response);
	}

	private void dual(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// 인코딩 설정
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String uri = request.getRequestURI();
		String cp = request.getContextPath();
		String sub = uri.substring(cp.length());
		System.out.println("sub : " + sub);
		/**AdminService  민철******************************/
		AdminService adservice = new AdminService(request, response);
		/**EnrollService  지현******************************/
		EnrollService enService= new EnrollService(request,response);
		/**MainService  수빈******************************/
		MainService mservice = new MainService(request, response);
		
		InfoService infoService = null;
		LectureService lectureService = null;
		//은경
		BbsService bbs = null;
		
		switch (sub) {
		/******************* 준도 *******************/
		case "/sProfile" :
			System.out.println("학생 신상조회 요청");
			infoService = new InfoService(request, response);
			infoService.sProfile();
			break;
		case "/sTimetable" :
			System.out.println("학생 시간표 요청");
			infoService = new InfoService(request, response);
			infoService.sTimetable();
			break;
		case "/tuitionTerm" :
			System.out.println("등록금 학기 가져오기");
			infoService = new InfoService(request, response);
			infoService.tuitionTerm();
			break;
		case "/tuition" :
			System.out.println("등록금고지서 요청");
			infoService = new InfoService(request, response);
			infoService.tuition();
			break;
		case "/scholar" :
			System.out.println("장학금조회 요청");
			infoService = new InfoService(request, response);
			infoService.scholar();
			break;
		case "/score" :
			System.out.println("성적조회 요청");
			infoService = new InfoService(request, response);
			infoService.score();
			break;
		case "/calPage" :
			System.out.println("학점계산기 페이지 요청");
			infoService = new InfoService(request, response);
			infoService.calPage();
			break;
		case "/pTimetable" :
			System.out.println("교수 시간표 요청");
			infoService = new InfoService(request, response);
			infoService.pTimetable();
			break;
		case "/pProfile" :
			System.out.println("교수 신상정보 요청");
			infoService = new InfoService(request, response);
			infoService.pProfile();
			break;
		case "/studentSearchPage" :
			System.out.println("수강생조회 페이지 요청");
			lectureService = new LectureService(request, response);
			lectureService.studentSearchPage();
			break;
		case "/studentSearch" :
			System.out.println("수강생조회 요청");
			lectureService = new LectureService(request, response);
			lectureService.studentSearch();
			break;
		case "/scoreRegistPage" :
			System.out.println("성적등록 페이지 요청");
			lectureService = new LectureService(request, response);
			lectureService.scoreRegistPage();
			break;
		case "/scoreRegist" :
			System.out.println("성적등록 요청");
			lectureService = new LectureService(request, response);
			lectureService.scoreRegist();
			break;
			
		/******************* 준도 *******************/
			
		/******************* 지현 *******************/
		case "/subjectSearch" :
			System.out.print("이전 학기과목 평점조회 or ");
			System.out.println("신 학기 수강신청 과목 조회 필터링으로 분류");
			enService.subjectSearch();
			break;
		case "/stdEnroll" :
			System.out.println("로그인 학생의 수강 신청 과목 조회");
			enService.stdEnroll();
			break;
		case "/stdCredit" :
			System.out.println("로그인 학생의 수강 과목 학점 조회");
			enService.stdCredit();
			break;
		case "/enroll" :
			System.out.println("로그인 학생의 수강 신청 ");
			enService.enroll();
			break; 
		case "/enrollChange" :
			System.out.println("로그인 학생의 수강 정정 ");
			enService.enrollChange();
			break; 
		/******************* 지현 *******************/
			
/**민철******************************************************/
		case "/student":
			System.out.println("학생 리스트 요청");
			adservice.sManagePage();
			break;
		
		case "/update":
			System.out.println("수정 페이지 요청");
			adservice.sUpdate();
			break;
		case "/updateForm":
			System.out.println("수정폼 요청");
			adservice.sUpdatePage();
			break;
		case "/register":
			System.out.println("학생등록 요청");
			adservice.sAdd();
			break;
		case "/del":
			System.out.println("삭제 요청");
			adservice.sDel();
			break;
		case "/search":
			System.out.println("검색 요청");
			adservice.sSearch();
			break;
		case "/aTuition":
			System.out.println("등록금 페이지");
			adservice.tMangePage();
			break;
		case "/tUpdateForm":
			System.out.println("등록금 수정폼");
			adservice.tUpdatePage();
			break;
		case "/tUpdate":
			System.out.println("등록금 수정");
			adservice.tUpdate();
			break;
		case "/tDell":
			System.out.println("등록금 삭제");
			adservice.tDell();
			break;
		case "/tAdd":
			System.out.println("등록금 등록");
			adservice.tAdd();
			break;
		case "/tSearch":
			System.out.println("등록금 검색");
			adservice.tSearch();
			break;
		case "/scScholar":
			System.out.println("장학금 리스트");
			adservice.scPage();
			break;
		case "/scUpdatePage":
			System.out.println("장학금 수정페이지");
			adservice.scUpdatePage();
			break;
		case "/scUpdate":
			System.out.println("장학금 수정");
			adservice.scUpdate();
			break;
		case "/scDel":
			System.out.println("장학금 삭제");
			adservice.scDel();
			break;
		case "/scAdd":
			System.out.println("장학금 등록");
			adservice.scAdd();
			break;
		case "/ePage":
			System.out.println("장학금 관리 리스트 페이지");
			adservice.ePage();
			break;
		case "/eUpdatePage":
			System.out.println("장학금 관리 수정폼");
			adservice.eUpdatePage();
			break;
		case "/eUpdate":
			System.out.println("장학금 관리 수정");
			adservice.eUpdate();
			break;
		case "/eDel":
			System.out.println("장학금 관리 삭제");
			adservice.eDel();
			break;
		case "/eAdd":
			System.out.println("장학금 관리 등록");
			adservice.eAdd();
			break;
		case "/eSearch":
			System.out.println("장학금 관리 검색");
			adservice.eSearch();
			break;
		case "/pManagePage":
			System.out.println("교수 리스트 페이지");
			adservice.pManagePage();
			break;
		case "/pUpdatePage":
			System.out.println("교수 수정폼");
			adservice.pUpdatePage();
			break;
		case "/pUpdate":
			System.out.println("교수 수정");
			adservice.pUpdate();
			break;
		case "/pDel":
			System.out.println("교수 삭제");
			adservice.pDel();
			break;
		case "/pAdd":
			System.out.println("교수 등록");
			adservice.pAdd();
			break;
		case "/pSearch":
			System.out.println("교수 검색");
			adservice.pSearch();
			break;
		case "/suManagePage":
			System.out.println("과목 리스트");
			adservice.suManagePage();
			break;
		case "/suDel":
			System.out.println("과목 리스트");
			adservice.suDel();
			break;
		case "/suAdd":
			System.out.println("과목추가");
			adservice.suAdd();
			break;
		case "/suUpdatePage":
			System.out.println("과목수정페이지");
			adservice.suUpdatePage();
			break;
		case "/suUpdate":
			System.out.println("과목수정");
			adservice.suUpdate();
			break;
		case "/suSearch":
			System.out.println("과목검색");
			adservice.suSearch();
			break;
		case "/gPage":
			System.out.println("강의평가 질문 리스트");
			adservice.gPage();
			break;
		case "/gAdd":
			System.out.println("강의평가 질문 작성");
			adservice.gAdd();
			break;
		case "/gUpdatePage":
			System.out.println("강의평가 질문 수정페이지");
			adservice.gUpdatePage();
			break;
		case "/gUpdate":
			System.out.println("강의평가 질문 수정");
			adservice.gUpdate();
			break;
		case "/gDel":
			System.out.println("강의평가 질문 삭제");
			adservice.gDel();
			break;
		case "/caAdd":
			System.out.println("학사일정 등록");
			adservice.caAdd();
			break;
		case "/caDell":
			System.out.println("학사일정 삭제");
			adservice.caDell();
			break;
		case "/caUpdate":
			System.out.println("학사일정 수정");
			adservice.caUpdate();
			break;
			
/**********************************************************************/			
		//수빈			
		case "/login" :
			System.out.println("login 요청");	
			mservice.login();
			break;
		case "/logout" :
			System.out.println("logout 요청");
			mservice.logout();
			break;
		case "/smain" :
			System.out.println("학생 main 페이지 이동");
			mservice.smain();
			break;
		case "/pmain" :
			System.out.println("교수 main 페이지 이동");
			mservice.pmain();
			break;
		case "/amain" :
			System.out.println("관리자 main 페이지 이동");
			mservice.amain();
			break;
		case "/passChange" :
			System.out.println("비밀번호 변경 요청");
			mservice.passChange();
			break;
		case "/dateEvent" :
			System.out.println("학사일정표 요청");
			mservice.dateEvent();
			break;
		case "/selectProSubject" :
			System.out.println("교수 과목 리스트 정보 요청");
			mservice.selectProSubject();
			break;
		case "/selectStdSubject" :
			System.out.println("교수 과목 리스트 정보 요청");
			mservice.selectStdSubject();
			break;
		case "/plecturePlan" :
			System.out.println("교수 강의계획서 페이지 요청");
			mservice.plecturePlan();
			break;
		case "/slecturePlan" :
			System.out.println("학생 강의계획서 페이지 요청");
			mservice.slecturePlan();
			break;

		case "/lectureWrite" :
			System.out.println("교수 강의계획서 페이지 작성 요청");
			mservice.lectureWrite();
			break;
		case "/planWritePage" :
			System.out.println("교수 강의계획서 페이지 글쓰기 폼 요청");
			mservice.planWritePage();
			break;
		case "/planUpdatePage" :
			System.out.println("교수 강의계획서 페이지 수정 폼 요청");
			mservice.planUpdatePage();
			break;
			
		case "/planUpdate" :
			System.out.println("교수 강의계획서 수정 요청");
			mservice.planUpdate();
			break;
			
/**********************************************************************/			
			//은경
			
		case "/subjectTab":
			System.out.println("select에 신청과목 들어가도록 요청");
			bbs = new BbsService(request,response);
			bbs.subjectTab();
			break;
			
		case "/list":
			System.out.println("강의자료 요청");
			bbs = new BbsService(request, response);
			bbs.list();
			break;	
			
		case "/write":
			System.out.println("글쓰기 요청");
			bbs = new BbsService(request,response);
			bbs.write();
			break;	
			
		case "/detail":
			System.out.println("상세보기 요청");
			bbs = new BbsService(request,response);
			bbs.detail();
			break;	
			
		case "/uploadlist":
			System.out.println("과제 게시판 요청");
			bbs = new BbsService(request,response);
			bbs.uploadlist();
			break;	
			
		case "/uploaddetail":
			System.out.println("과제 상세보기 요청");
			bbs = new BbsService(request,response);
			bbs.uploaddetail();
			break;		
			
		case "/updatePage":
			System.out.println("학생 - 과제 게시판 수정 폼 요청");
			bbs = new BbsService(request,response);
			bbs.updatePage();
			break;		
			
		case "/sUpdate":
			System.out.println("학생 - 과제 게시판 수정 요청");
			bbs = new BbsService(request,response);
			bbs.update();
			break;		
			
		case "/sDel":
			System.out.println("학생 - 과제 게시판 삭제 요청");
			bbs = new BbsService(request,response);
			bbs.del();
			break;			
			
		case "/gradePage":
			System.out.println("학생-강의평가 폼 요청");
			bbs = new BbsService(request,response);
			bbs.gradePage();
			break;		
			
		case "/grade":
			System.out.println("학생-강의평가 저장 요청");
			bbs = new BbsService(request,response);
			bbs.grade();
			break;		
			
		case "/prosubjectTab":
			System.out.println("교수-과목 게시판 클릭시 select 박스 채움 요청");
			bbs = new BbsService(request,response);
			bbs.prosubjectTab();
			break;		
			
		case "/prolist":
			System.out.println("교수-강의자료 게시판 요청");
			bbs = new BbsService(request,response);
			bbs.prolist();
			break;		
			
		case "/prouploadlist":
			System.out.println("교수-과목 게시판 요청");
			bbs = new BbsService(request,response);
			bbs.prouploadlist();
			break;			
			
		case "/prodetail":
			System.out.println("교수-강의자료 상세보기 요청");
			bbs = new BbsService(request,response);
			bbs.prodetail();
			break;		
			
		case "/prouploaddetail":
			System.out.println("교수-과제게시판 상세보기 요청");
			bbs = new BbsService(request,response);
			bbs.prouploaddetail();
			break;		

		case "/prowrite":
			System.out.println("교수-강의자료 글쓰기 요청");
			bbs = new BbsService(request,response);
			bbs.prowrite();
			break;	
			
		case "/proupdatePage":
			System.out.println("교수-강의자료 글쓰기 수정 폼 요청");
			bbs = new BbsService(request,response);
			bbs.proupdatePage();
			break;	
			
		case "/proupdate":
			System.out.println("교수-강의자료 글쓰기 수정 요청");
			bbs = new BbsService(request,response);
			bbs.proupdate();
			break;	
			
		case "/prodel":
			System.out.println("교수-강의자료 삭제 요청");
			bbs = new BbsService(request,response);
			bbs.prodel();
			break;
			
		case "/listback":
			System.out.println("뒤로가기 요청");
			bbs = new BbsService(request,response);
			bbs.listback();
			break;
			
		case "/overlay":
			System.out.println("체크 요청");
			bbs = new BbsService(request,response);
			bbs.overlay();
			break;	
		}
	}

}
