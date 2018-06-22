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

public class LectureDAO {
	
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	// 생성자 : DB 연결
	public LectureDAO() {
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

	// 수강생조회 페이지(강의 과목 리스트 반환)
	public ArrayList<DTO> studentSearchPage(String loginId) {
		ArrayList<DTO> subjectList = new ArrayList<>();
		String sql = "SELECT S.subject_id, S.subject_name " + 
				"FROM subject S " + 
				"JOIN pro P ON S.pro_id = P.pro_id " + 
				"WHERE P.pro_id = ? AND S.term_id = '2018-1'" +
				"ORDER BY S.subject_name";

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

	// 수강생조회
	public ArrayList<DTO> studentSearch(String subjectId) {
		ArrayList<DTO> studentList = new ArrayList<>();
		String sql = "SELECT s.std_id,s.std_year,s.std_name,m.major_name,s.std_phone,s.std_email " + 
				"FROM std s " + 
				"JOIN major M ON S.major_id = M.major_id " + 
				"JOIN enroll e ON S.std_id =E.std_id " + 
				"WHERE E.subject_id=? " + 
				"order by s.std_id";

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, subjectId);
			rs = ps.executeQuery();

			while (rs.next()) {
				DTO dto = new DTO();
				dto.setStd_id(rs.getString("std_id"));
				dto.setStd_year(rs.getInt("std_year"));
				dto.setStd_name(rs.getString("std_name"));
				dto.setMajor_name(rs.getString("major_name"));
				dto.setStd_phone(rs.getString("std_phone"));
				dto.setStd_email(rs.getString("std_email"));
				studentList.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			resClose();
		}
		return studentList;
	}

	// 성적 조회(성적 등록을 아직 하지 않은 경우 빈 리스트 반환)
	public ArrayList<DTO> scoreSearch(String subjectId) {
		ArrayList<DTO> scoreList = new ArrayList<>();
		String sql = "SELECT std.std_id, std.std_name, score.score_score " + 
				"FROM score " + 
				"JOIN std ON score.std_id = std.std_id " + 
				"WHERE score.subject_id = ? " + 
				"order by std.std_id";

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, subjectId);
			rs = ps.executeQuery();

			while (rs.next()) {
				DTO dto = new DTO();
				dto.setStd_id(rs.getString("std_id"));
				dto.setStd_name(rs.getString("std_name"));
				dto.setScore_score(rs.getString("score_score"));
				scoreList.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			resClose();
		}
		return scoreList;
	}

	// 과목ID, 학생:성적 리스트를 받아 성적 등록
	public int scoreRegist(int subject_id, ArrayList<DTO> scoreList) {
		int success = 0;
		String sql = "MERGE INTO score sc " + 
				"USING dual " + 
				"ON (sc.subject_id = ? and sc.std_id = ?) " + 
				"WHEN MATCHED THEN " + 
				"UPDATE SET sc.score_score = ? " + 
				"WHEN NOT MATCHED THEN " + 
				"INSERT (score_id, subject_id, std_id, score_score) " +
				"VALUES (seq_score_id.NEXTVAL, ?, ?, ?)";
		
		try {
			ps = conn.prepareStatement(sql);
			for (DTO dto : scoreList) {
				ps.setInt(1, subject_id);
				ps.setString(2, dto.getStd_id());
				ps.setString(3, dto.getScore_score());
				ps.setInt(4, subject_id);
				ps.setString(5, dto.getStd_id());
				ps.setString(6, dto.getScore_score());
				success = ps.executeUpdate();
				// 실패한 경우
				if (success == 0) {
					return -1;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			resClose();
		}
		
		return success;
	}

}
