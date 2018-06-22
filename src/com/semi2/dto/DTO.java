package com.semi2.dto;

public class DTO {
	// 장학금
	private int scholar_id;
	private String scholar_name;
	private int scholar_money;
	// 등록금
	private int tuition_id;
	private int tuition_money;
	// 학생
	private String std_id;
	private String std_phone;
	private String std_address;
	private int std_year;
	private String std_state;
	private String std_birthday;
	private String std_email;
	private String std_name;
	private String std_pw;
	// 학과명
	private String major_id;
	private String major_name;
	// 수강신청
	private int enroll_id;
	// 강의평가
	private int grade_id;
	private String grade_grade;
	// 강의평가 질문
	private int question_id;
	private String question_question;
	// 학기
	private String term_id;
	// 성적
	private int score_id;
	private String score_score;
	// 교수
	private String pro_id;
	private String pro_phone;
	private String pro_name;
	private String pro_room;
	private String pro_email;
	private String pro_pw;
	// 과목
	private int subject_id;
	private String subject_name;
	private String subject_room;
	private String subject_time;
	private String subject_type;
	private double subject_grade;
	private int subject_credit;
	private int subject_limit;
	private int subject_count;

	// 강의계획서
	private String plan_cu;
	private String plan_book;
	private String plan_sub_book;
	private String plan_objective;


	// 관리자
	private String admin_id;
	private String admin_pw;
	// 학사일정
	private int schedule_id;
	private String schedule_date;
	private String schedule_content;
	// 게시판
	private int bbs_id;
	private int bbs_number;
	private String bbs_title;
	private String bbs_content;
	private String bbs_writer;
	private String bbs_date;
	// 게시판 종류
	private String bbssort_type;
	// 첨부파일
	private int upload_id;
	private String upload_name;
	// 등록금 실납입액
	private int totalMoney;
	public int getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(int totalMoney) {
		this.totalMoney = totalMoney;
	}
	// Getter, Setter
	public int getScholar_id() {
		return scholar_id;
	}

	public void setScholar_id(int scholar_id) {
		this.scholar_id = scholar_id;
	}

	public String getScholar_name() {
		return scholar_name;
	}

	public void setScholar_name(String scholar_name) {
		this.scholar_name = scholar_name;
	}

	public int getScholar_money() {
		return scholar_money;
	}

	public void setScholar_money(int scholar_money) {
		this.scholar_money = scholar_money;
	}

	public int getTuition_id() {
		return tuition_id;
	}

	public void setTuition_id(int tuition_id) {
		this.tuition_id = tuition_id;
	}

	public int getTuition_money() {
		return tuition_money;
	}

	public void setTuition_money(int tuition_money) {
		this.tuition_money = tuition_money;
	}

	public String getStd_id() {
		return std_id;
	}

	public void setStd_id(String std_id) {
		this.std_id = std_id;
	}

	public String getStd_phone() {
		return std_phone;
	}

	public void setStd_phone(String std_phone) {
		this.std_phone = std_phone;
	}

	public String getStd_address() {
		return std_address;
	}

	public void setStd_address(String std_address) {
		this.std_address = std_address;
	}

	public int getStd_year() {
		return std_year;
	}

	public void setStd_year(int std_year) {
		this.std_year = std_year;
	}

	public String getStd_state() {
		return std_state;
	}

	public void setStd_state(String std_state) {
		this.std_state = std_state;
	}

	public String getStd_birthday() {
		return std_birthday;
	}

	public void setStd_birthday(String std_birthday) {
		this.std_birthday = std_birthday;
	}

	public String getStd_email() {
		return std_email;
	}

	public void setStd_email(String std_email) {
		this.std_email = std_email;
	}

	public String getStd_name() {
		return std_name;
	}

	public void setStd_name(String std_name) {
		this.std_name = std_name;
	}

	public String getStd_pw() {
		return std_pw;
	}

	public void setStd_pw(String std_pw) {
		this.std_pw = std_pw;
	}

	public String getMajor_id() {
		return major_id;
	}

	public void setMajor_id(String major_id) {
		this.major_id = major_id;
	}

	public String getMajor_name() {
		return major_name;
	}

	public void setMajor_name(String major_name) {
		this.major_name = major_name;
	}

	public int getEnroll_id() {
		return enroll_id;
	}

	public void setEnroll_id(int enroll_id) {
		this.enroll_id = enroll_id;
	}

	public int getGrade_id() {
		return grade_id;
	}

	public void setGrade_id(int grade_id) {
		this.grade_id = grade_id;
	}

	public String getGrade_grade() {
		return grade_grade;
	}

	public void setGrade_grade(String grade_grade) {
		this.grade_grade = grade_grade;
	}

	public int getQuestion_id() {
		return question_id;
	}

	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}

	public String getQuestion_question() {
		return question_question;
	}

	public void setQuestion_question(String question_question) {
		this.question_question = question_question;
	}

	public String getTerm_id() {
		return term_id;
	}

	public void setTerm_id(String term_id) {
		this.term_id = term_id;
	}

	public int getScore_id() {
		return score_id;
	}

	public void setScore_id(int score_id) {
		this.score_id = score_id;
	}

	public String getScore_score() {
		return score_score;
	}

	public void setScore_score(String score_score) {
		this.score_score = score_score;
	}

	public String getPro_id() {
		return pro_id;
	}

	public void setPro_id(String pro_id) {
		this.pro_id = pro_id;
	}

	public String getPro_phone() {
		return pro_phone;
	}

	public void setPro_phone(String pro_phone) {
		this.pro_phone = pro_phone;
	}

	public String getPro_name() {
		return pro_name;
	}

	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}

	public String getPro_room() {
		return pro_room;
	}

	public void setPro_room(String pro_room) {
		this.pro_room = pro_room;
	}

	public String getPro_email() {
		return pro_email;
	}

	public void setPro_email(String pro_email) {
		this.pro_email = pro_email;
	}

	public String getPro_pw() {
		return pro_pw;
	}

	public void setPro_pw(String pro_pw) {
		this.pro_pw = pro_pw;
	}

	public int getSubject_id() {
		return subject_id;
	}

	public void setSubject_id(int subject_id) {
		this.subject_id = subject_id;
	}

	public String getSubject_name() {
		return subject_name;
	}

	public void setSubject_name(String subject_name) {
		this.subject_name = subject_name;
	}

	public String getSubject_room() {
		return subject_room;
	}

	public void setSubject_room(String subject_room) {
		this.subject_room = subject_room;
	}

	public String getSubject_time() {
		return subject_time;
	}

	public void setSubject_time(String subject_time) {
		this.subject_time = subject_time;
	}

	public String getSubject_type() {
		return subject_type;
	}

	public void setSubject_type(String subject_type) {
		this.subject_type = subject_type;
	}

	public double getSubject_grade() {
		return subject_grade;
	}

	public void setSubject_grade(double subject_grade) {
		this.subject_grade = subject_grade;
	}

	public int getSubject_credit() {
		return subject_credit;
	}

	public void setSubject_credit(int subject_credit) {
		this.subject_credit = subject_credit;
	}

	public int getSubject_limit() {
		return subject_limit;
	}

	public void setSubject_limit(int subject_limit) {
		this.subject_limit = subject_limit;
	}
	public int getSubject_count() {
		return subject_count;
	}
	public void setSubject_count(int subject_count) {
		this.subject_count = subject_count;
	}
	public String getPlan_cu() {
		return plan_cu;
	}

	public void setPlan_cu(String plan_cu) {
		this.plan_cu = plan_cu;
	}

	public String getPlan_book() {
		return plan_book;
	}

	public void setPlan_book(String plan_book) {
		this.plan_book = plan_book;
	}

	public String getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}

	public String getAdmin_pw() {
		return admin_pw;
	}

	public void setAdmin_pw(String admin_pw) {
		this.admin_pw = admin_pw;
	}

	public int getSchedule_id() {
		return schedule_id;
	}

	public void setSchedule_id(int schedule_id) {
		this.schedule_id = schedule_id;
	}

	public String getSchedule_date() {
		return schedule_date;
	}

	public void setSchedule_date(String schedule_date) {
		this.schedule_date = schedule_date;
	}

	public String getSchedule_content() {
		return schedule_content;
	}

	public void setSchedule_content(String schedule_content) {
		this.schedule_content = schedule_content;
	}

	public int getBbs_id() {
		return bbs_id;
	}

	public void setBbs_id(int bbs_id) {
		this.bbs_id = bbs_id;
	}

	public int getBbs_number() {
		return bbs_number;
	}

	public void setBbs_number(int bbs_number) {
		this.bbs_number = bbs_number;
	}

	public String getBbs_title() {
		return bbs_title;
	}

	public void setBbs_title(String bbs_title) {
		this.bbs_title = bbs_title;
	}

	public String getBbs_content() {
		return bbs_content;
	}

	public void setBbs_content(String bbs_content) {
		this.bbs_content = bbs_content;
	}

	public String getBbs_writer() {
		return bbs_writer;
	}

	public void setBbs_writer(String bbs_writer) {
		this.bbs_writer = bbs_writer;
	}

	public String getBbs_date() {
		return bbs_date;
	}

	public void setBbs_date(String bbs_date) {
		this.bbs_date = bbs_date;
	}

	public String getBbssort_type() {
		return bbssort_type;
	}

	public void setBbssort_type(String bbssort_type) {
		this.bbssort_type = bbssort_type;
	}

	public int getUpload_id() {
		return upload_id;
	}

	public void setUpload_id(int upload_id) {
		this.upload_id = upload_id;
	}

	public String getUpload_name() {
		return upload_name;
	}

	public void setUpload_name(String upload_name) {
		this.upload_name = upload_name;
	}

	public String getPlan_sub_book() {
		return plan_sub_book;
	}
	
	public void setPlan_sub_book(String plan_sub_book) {
		this.plan_sub_book = plan_sub_book;
	}
	
	public String getPlan_objective() {
		return plan_objective;
	}
	
	public void setPlan_objective(String plan_objective) {
		this.plan_objective = plan_objective;
	}
	
	
	
}
