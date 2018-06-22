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

public class InfoDAO {

	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	// 생성자 : DB 연결
	public InfoDAO() {
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 자원 반납
	public void resClose() {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 학생 신상조회
	public DTO sProfile(String loginId) {
		DTO dto = new DTO();
		String sql = "SELECT s.std_id, s.std_name, m.major_name, s.std_year, s.std_state, s.std_birthday, s.std_phone, s.std_email, s.std_address "
				+ "FROM major M JOIN std S ON M.major_id = S.major_id " + "WHERE s.std_id = ?";

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, loginId);
			rs = ps.executeQuery();

			if (rs.next()) {
				dto.setStd_id(rs.getString("std_id"));
				dto.setStd_year(rs.getInt("std_year"));
				dto.setStd_name(rs.getString("std_name"));
				dto.setStd_birthday(rs.getString("std_birthday"));
				dto.setMajor_name(rs.getString("major_name"));
				dto.setStd_state(rs.getString("std_state"));
				dto.setStd_phone(rs.getString("std_phone"));
				dto.setStd_email(rs.getString("std_email"));
				dto.setStd_address(rs.getString("std_address"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			resClose();
		}

		return dto;
	}

	// 학생 시간표 조회
	public ArrayList<DTO> sTimetable(String loginId) {
		ArrayList<DTO> list = new ArrayList<>();
		String sql = "SELECT sub.subject_name, sub.subject_time, sub.subject_room " + "FROM enroll E "
				+ "JOIN subject sub ON e.subject_id = sub.subject_id " + "JOIN pro P ON P.pro_id = sub.pro_id "
				+ "WHERE std_id = ? AND term_id='2018-1'";

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, loginId);
			rs = ps.executeQuery();

			while (rs.next()) {
				DTO dto = new DTO();
				dto.setSubject_name(rs.getString("subject_name"));
				dto.setSubject_time(rs.getString("subject_time"));
				dto.setSubject_room(rs.getString("subject_room"));
				list.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			resClose();
		}
		return list;
	}

	// 등록금 학기 리스트
	public ArrayList<String> tuitionTerm(String loginId) {
		ArrayList<String> termList = new ArrayList<>();
		String sql = "SELECT term_id FROM tuition WHERE std_id = ? ORDER BY term_id DESC";

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, loginId);
			rs = ps.executeQuery();

			while (rs.next()) {
				termList.add(rs.getString("term_id"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			resClose();
		}

		return termList;
	}

	// 등록금고지서 조회
	public DTO tuition(DTO sendDTO) {
		DTO dto = new DTO();
		String sql = "SELECT T.tuition_money,  S.scholar_name, S.scholar_money, m.major_name, t.term_id, std.std_name, std.std_id "
				+ "FROM tuition T " + "JOIN scholar S ON T.scholar_id = S.scholar_id "
				+ "JOIN std ON std.std_id = T.std_id " + "JOIN major M ON std.major_id = M.major_id "
				+ "WHERE std.std_id=? AND T.term_id=?";

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, sendDTO.getStd_id());
			ps.setString(2, sendDTO.getTerm_id());
			rs = ps.executeQuery();

			if (rs.next()) {
				dto.setStd_id(rs.getString("std_id"));
				dto.setStd_name(rs.getString("std_name"));
				dto.setTuition_money(rs.getInt("tuition_money"));
				dto.setScholar_money(rs.getInt("scholar_money"));
				dto.setScholar_name(rs.getString("scholar_name"));
				dto.setMajor_name(rs.getString("major_name"));
				dto.setTerm_id(rs.getString("term_id"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			resClose();
		}
		return dto;
	}

	// 장학금 조회
	public ArrayList<DTO> scholar(String loginId) {
		ArrayList<DTO> scholarList = new ArrayList<>();
		String sql = "SELECT S.scholar_id, S.scholar_name, S.scholar_money, T.term_id " + "FROM scholar S "
				+ "JOIN tuition T ON S.scholar_id= T.scholar_id " + "JOIN std st ON st.std_id=T.std_id "
				+ "WHERE st.std_id=? " + "order by t.term_id DESC";

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, loginId);
			rs = ps.executeQuery();

			while (rs.next()) {
				DTO dto = new DTO();
				dto.setScholar_id(rs.getInt("scholar_id"));
				dto.setScholar_name(rs.getString("scholar_name"));
				dto.setScholar_money(rs.getInt("scholar_money"));
				dto.setTerm_id(rs.getString("term_id"));
				scholarList.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			resClose();
		}

		return scholarList;
	}

	// 성적조회
	public ArrayList<DTO> score(String loginId) {
		ArrayList<DTO> scoreList = new ArrayList<>();
		String sql = "SELECT sc.std_id, sb.term_id, sb.subject_name, score_score, sb.subject_credit, sb.subject_type " + 
				"FROM score sc " + 
				"JOIN subject sb " + 
				"ON sc.subject_id = sb.subject_id " + 
				"WHERE sc.std_id =? " + 
				"order by term_id desc";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, loginId);
			rs = ps.executeQuery();

			while (rs.next()) {
				DTO dto = new DTO();
				dto.setTerm_id(rs.getString("term_id"));
				dto.setSubject_name(rs.getString("subject_name"));
				dto.setScore_score(rs.getString("score_score"));
				dto.setSubject_credit(rs.getInt("subject_credit"));
				dto.setSubject_type(rs.getString("subject_type"));
				scoreList.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			resClose();
		}
		
		return scoreList;
	}

	// 학점계산기 페이지(현재 학기 수강과목 리스트 반환)
	public ArrayList<DTO> calPage(String loginId) {
		ArrayList<DTO> subjectList = new ArrayList<>();
		String sql = "SELECT subject_name, subject_type, subject_credit " + 
				"FROM enroll E " + 
				"JOIN subject sub ON E.subject_id = sub.subject_id " + 
				"WHERE std_id=? AND term_id='2018-1' " +
				"ORDER BY subject_name";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, loginId);
			rs = ps.executeQuery();

			while (rs.next()) {
				DTO dto = new DTO();
				dto.setSubject_name(rs.getString("subject_name"));
				dto.setSubject_type(rs.getString("subject_type"));
				dto.setSubject_credit(rs.getInt("subject_credit"));
				subjectList.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			resClose();
		}
		
		return subjectList;
	}

	// 교수 신상조회
	public DTO pProfile(String loginId) {
		DTO dto = new DTO();
		String sql = "SELECT p.pro_id, p.pro_name, m.major_name, p.pro_room, p.pro_phone, p.pro_email " + 
				"FROM pro P " + 
				"JOIN major M ON P.major_id = M.major_id " + 
				"WHERE P.pro_id = ?";

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, loginId);
			rs = ps.executeQuery();

			if (rs.next()) {
				dto.setPro_id(rs.getString("pro_id"));
				dto.setPro_name(rs.getString("pro_name"));
				dto.setMajor_name(rs.getString("major_name"));
				dto.setPro_room(rs.getString("pro_room"));
				dto.setPro_phone(rs.getString("pro_phone"));
				dto.setPro_email(rs.getString("pro_email"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			resClose();
		}
		return dto;
	}

	// 교수 시간표
	public ArrayList<DTO> pTimetable(String loginId) {
		ArrayList<DTO> list = new ArrayList<>();
		String sql = "SELECT S.subject_name, S.subject_time, S.subject_room " + 
				"FROM subject S " + 
				"JOIN pro P ON S.pro_id = P.pro_id " + 
				"WHERE P.pro_id = ? AND S.term_id = '2018-1' ";

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, loginId);
			rs = ps.executeQuery();

			while (rs.next()) {
				DTO dto = new DTO();
				dto.setSubject_name(rs.getString("subject_name"));
				dto.setSubject_time(rs.getString("subject_time"));
				dto.setSubject_room(rs.getString("subject_room"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			resClose();
		}
		return list;
	}

}
