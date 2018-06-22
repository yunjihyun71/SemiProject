package com.semi2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.semi2.dto.DTO;
import com.semi2.dto.PwDTO;

public class MainDAO {
	
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public MainDAO() {
		try {
			// DB 연결
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void resClose() {
		System.out.println("자원 반납");
		try {
			if(rs != null) {//rs 가 사용해서 있을 경우만 닫아 준다.
				rs.close();
			}			
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	// 로그인
	public Boolean login(String id, String pw) {
		boolean result = false;
		if(id.startsWith("s")) {//id가 s로 시작하면 (학생 로그인 체크)
			//ID, PW 가 맞는 지 확인하기위해 쿼리문 준비
			String sql = "SELECT std_id FROM std WHERE std_id =? AND std_pw=? ";
			try {
				//쿼리 실행하기 위해 PrepareStatement 준비
				ps =conn.prepareStatement(sql);
				//실행 하기 전에 ? 대응
				ps.setString(1, id);
				ps.setString(2, pw);
				System.out.println(id + " "+ pw);
				//쿼리 실행
				rs =ps.executeQuery();
				result =rs.next();
				System.out.println(rs.next());
				System.out.println(result);			
			} catch (SQLException e) {
				e.printStackTrace();
				return false;				
			}finally {
				resClose(); 
			}
			
		}else if(id.startsWith("p")){//id가 p로 시작 하면 (교수 로그인 체크)
			//ID, PW 가 맞는 지 확인하기위해 쿼리문 준비
			String sql = "SELECT pro_id FROM pro WHERE pro_id =? AND pro_pw=? ";
			try {
				//쿼리 실행하기 위해 PrepareStatement 준비
				ps =conn.prepareStatement(sql);
				//실행 하기 전에 ? 대응
				ps.setString(1, id);
				ps.setString(2, pw);
				System.out.println(id + " "+ pw);
				//쿼리 실행
				rs =ps.executeQuery();
				result =rs.next();
				System.out.println(rs.next());
				System.out.println(result);			
			} catch (SQLException e) {
				e.printStackTrace();
				return false;	
			}finally {
				resClose();
			}
			
		}else {//그 외는 (관리자 로그인 체크)
			//ID, PW 가 맞는 지 확인하기위해 쿼리문 준비
			String sql = "SELECT admin_id FROM admin WHERE admin_id =? AND admin_pw=? ";
			try {
				//쿼리 실행하기 위해 PrepareStatement 준비
				ps =conn.prepareStatement(sql);
				//실행 하기 전에 ? 대응
				ps.setString(1, id);
				ps.setString(2, pw);
				System.out.println(id + " "+ pw);
				//쿼리 실행
				rs =ps.executeQuery();
				result =rs.next();
				System.out.println(rs.next());
				System.out.println(result);
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}finally {
				resClose();
			}
		}
		return result;
	}

	//비밀번호 변경
	public int passChange(DTO dto,PwDTO pwdto) {
		
		int success = 0;
		try {
			if(pwdto.getLoginidDTO().startsWith("s")) {
				String sql = "UPDATE std SET std_pw=? WHERE std_id=?";
				ps = conn.prepareStatement(sql);			
				ps.setString(1, pwdto.getnewPw());
				ps.setString(2, pwdto.getLoginidDTO());
			}else if(pwdto.getLoginidDTO().startsWith("p")) {
				String sql = "UPDATE pro SET pro_pw=? WHERE pro_id=?";
				ps = conn.prepareStatement(sql);			
				ps.setString(1, pwdto.getnewPw());
				ps.setString(2, pwdto.getLoginidDTO());
			}else {
				String sql = "UPDATE admin SET admin_pw=? WHERE admin_id=?";
				ps = conn.prepareStatement(sql);			
				ps.setString(1, pwdto.getnewPw());
				ps.setString(2, pwdto.getLoginidDTO());
			}
			System.out.println(pwdto.getnewPw());
			System.out.println(pwdto.getLoginidDTO());
			success = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}finally {
			resClose();
		}
		return success;
	}

	//학사일정
	public ArrayList<DTO> dateEvent(String schedule) {
		ArrayList<DTO> dateList = new ArrayList<>();
		DTO dto = null;
		String sql = "SELECT  schedule_id, schedule_content FROM schedule WHERE schedule_date = ? ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, schedule);
			rs = ps.executeQuery();
			while (rs.next()) {
				dto = new DTO();
				dto.setSchedule_content(rs.getString("schedule_content"));
				dto.setSchedule_id(rs.getInt("schedule_id"));
				dateList.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			resClose();
		}
		
		return dateList;
	}
	
	//교수 과목 리스트
		public ArrayList<DTO> selectProSubject(String loginId) {
			ArrayList<DTO> subjectList = new ArrayList<>();
			String sql = "SELECT subject_name, subject_id FROM subject WHERE pro_id = ? AND term_id='2018-1' ";

			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, loginId);
				rs = ps.executeQuery();
				while (rs.next()) {
					DTO dto = new DTO();
					dto.setSubject_name(rs.getString("subject_name"));
					dto.setSubject_id(rs.getInt("subject_id"));
					subjectList.add(dto);
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				resClose();
			}

			return subjectList;
		}
		
		//학생 과목 리스트
			public ArrayList<DTO> selectStdSubject(String loginId) {
				ArrayList<DTO> subjectList = new ArrayList<>();
				String sql = "SELECT S.subject_id, S.subject_name FROM subject S JOIN enroll E ON S.subject_id = E.subject_id "
						+ "WHERE std_id = ? AND term_id='2018-1' ORDER BY subject_name ";

				try {
					ps = conn.prepareStatement(sql);
					ps.setString(1, loginId);
					rs = ps.executeQuery();

					while (rs.next()) {
						DTO dto = new DTO();
						dto.setSubject_id(rs.getInt("subject_id"));
						dto.setSubject_name(rs.getString("subject_name"));
						subjectList.add(dto);
					}

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					resClose();
				}

				return subjectList;
			}
		
		
		// 강의계획서 조회(교수 페이지) 
		public DTO plecturePlan(String loginId, int subject) {
			System.out.println("교수 lecturePlan 호출");
			DTO dto = new DTO();
			String sql = "SELECT T.term_id, S.subject_name, S.subject_type, S.subject_credit, P.pro_name, "
					+ "P.pro_email, S.subject_room, M.major_name, S.subject_time, std.std_year, p.plan_cu, p.plan_book, "
					+ "p.plan_objective, p.plan_sub_book,S.subject_id "
					+ "FROM pro P " + "JOIN subject S ON P.pro_id = S.pro_id "
					+ "JOIN term T ON S.term_id = T.term_id " + "JOIN major M ON S.major_id = M.major_id " 
					+ "JOIN std std ON std.major_id = M.major_id " 
					+ "JOIN plan p ON S.subject_id = p.subject_id "
					+ "WHERE P.pro_id=? AND S.subject_id=?";

			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, loginId);
				ps.setInt(2, subject);
				rs = ps.executeQuery();
				if (rs.next()) {
					dto.setMajor_name(rs.getString("major_name"));
					dto.setTerm_id(rs.getString("term_id"));
					dto.setSubject_name(rs.getString("subject_name"));
					dto.setStd_year(rs.getInt("std_year"));
					dto.setSubject_type(rs.getString("subject_type"));
					dto.setSubject_credit(rs.getInt("subject_credit"));
					dto.setPro_name(rs.getString("pro_name"));
					dto.setSubject_time(rs.getString("subject_time"));
					dto.setPro_email(rs.getString("pro_email"));
					dto.setSubject_room(rs.getString("subject_room"));
					dto.setPlan_cu(rs.getString("plan_cu"));
					dto.setPlan_book(rs.getString("plan_book"));
					dto.setPlan_sub_book(rs.getString("plan_sub_book"));
					dto.setPlan_objective(rs.getString("plan_objective"));
					dto.setSubject_id(rs.getInt("subject_id"));
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				resClose();
			}
			return dto;
		}

		//강의계획서 조회(학생 페이지) 
		public DTO slecturePlan(DTO planDTO) {
			System.out.println("학생 lecturePlan 호출");
			DTO dto = new DTO();
			String sql = "SELECT T.term_id, S.subject_name, S.subject_type, S.subject_credit, P.pro_name, "
					+ "P.pro_email, S.subject_room, M.major_name, S.subject_time, std.std_year, p.plan_cu, p.plan_book, "
					+ "p.plan_objective, p.plan_sub_book,S.subject_id "
					+ "FROM pro P " 
					+ "JOIN subject S ON P.pro_id = S.pro_id "
					+ "JOIN term T ON S.term_id = T.term_id " + "JOIN major M ON S.major_id = M.major_id " 
					+ "JOIN std std ON std.major_id = M.major_id " 
					+ "JOIN plan p ON S.subject_id = p.subject_id "
					+ "WHERE S.subject_id=?";

			try {
				ps = conn.prepareStatement(sql);
				ps.setInt(1, planDTO.getSubject_id());
				rs = ps.executeQuery();
				if (rs.next()) {
					dto.setMajor_name(rs.getString("major_name"));
					dto.setTerm_id(rs.getString("term_id"));
					dto.setSubject_name(rs.getString("subject_name"));
					dto.setStd_year(rs.getInt("std_year"));
					dto.setSubject_type(rs.getString("subject_type"));
					dto.setSubject_credit(rs.getInt("subject_credit"));
					dto.setPro_name(rs.getString("pro_name"));
					dto.setSubject_time(rs.getString("subject_time"));
					dto.setPro_email(rs.getString("pro_email"));
					dto.setSubject_room(rs.getString("subject_room"));
					dto.setPlan_cu(rs.getString("plan_cu"));
					dto.setPlan_book(rs.getString("plan_book"));
					dto.setPlan_sub_book(rs.getString("plan_sub_book"));
					dto.setPlan_objective(rs.getString("plan_objective"));
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				resClose();
			}
			return dto;
		}

		//강의 계획서 등록
		public int lectureWrite(DTO dto) {
			int result = 0;
			String sql = "INSERT INTO plan VALUES (?, ?, ?, ?, ?)";
			try {
				ps= conn.prepareStatement(sql);
				ps.setInt(1, dto.getSubject_id());
				ps.setString(2, dto.getPlan_cu());
				ps.setString(3, dto.getPlan_book());
				ps.setString(4, dto.getPlan_objective());
				ps.setString(5, dto.getPlan_sub_book());
				result = ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				resClose();
			}
			return result;
		}

		//강의 계획서 수정
		public int planUpdate(DTO dto) {
			int result = 0;
			String sql = "UPDATE plan SET plan_cu=?, plan_book=?, plan_objective=?, plan_sub_book=? "
					+ "WHERE subject_id=?";	
			try {
				ps= conn.prepareStatement(sql);
				ps.setString(1, dto.getPlan_cu());
				ps.setString(2, dto.getPlan_book());
				ps.setString(3, dto.getPlan_objective());
				ps.setString(4, dto.getPlan_sub_book());
				ps.setInt(5, dto.getSubject_id());
				result = ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				resClose();
			}
			return result;
		}

}
			


