package com.semi2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.semi2.dto.DTO;

public class BbsDAO {
	
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	//DB 연결
	public BbsDAO(){
		
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/Oracle");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//자원반납 메서드
	public void resClose() {
			try {
				if (rs != null) {
				rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	//학생 - 과목 게시판 
	public ArrayList<DTO> list(int selected, String bbssort_type) {
		ArrayList<DTO> list = new ArrayList<DTO>();
		DTO dto= null;
		String sql = "SELECT B.bbs_id, B.bbs_title, B.bbs_writer, B.bbs_date FROM bbs B JOIN bbssort bs ON bs.bbssort_type = B.bbssort_type " 
						+"JOIN bbssort bs ON bs.bbssort_type = B.bbssort_type " 
						+"WHERE B.subject_id=? AND bs.bbssort_type=? ORDER BY B.bbs_id DESC";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, selected);
			ps.setString(2, bbssort_type);
			rs = ps.executeQuery();
			while (rs.next()) {
				dto = new DTO();
				dto.setBbs_id(rs.getInt("bbs_id"));
				dto.setBbs_title(rs.getString("bbs_title"));
				dto.setBbs_writer(rs.getString("bbs_writer"));
				dto.setBbs_date(rs.getString("bbs_date"));
				dto.setSubject_id(selected);
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return list;
	}
	
	//학생 - 과목 게시판 (페이징)
	public ArrayList<DTO> pagingList(int selected, String bbssort_type, int startNum, int endNum) {
		ArrayList<DTO> list = new ArrayList<DTO>();
		DTO dto= null;
		String sql = "SELECT X.rnum, X.bbs_id, X.bbs_title, X.bbs_writer, X.bbs_date " + 
				"FROM ( " + 
				"    SELECT ROWNUM AS rnum, A.bbs_id, A.bbs_title, A.bbs_writer, A.bbs_date " + 
				"    FROM ( " + 
				"        SELECT B.bbs_id, B.bbs_title, B.bbs_writer, B.bbs_date FROM bbs B JOIN bbssort bs ON bs.bbssort_type = B.bbssort_type " + 
				"        JOIN bbssort bs ON bs.bbssort_type = B.bbssort_type " + 
				"        WHERE b.subject_id=? AND bs.bbssort_type=? ORDER BY b.bbs_id DESC) A " + 
				"    WHERE ROWNUM <= ?) x " + 
				"WHERE X.rnum >= ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, selected);
			ps.setString(2, bbssort_type);
			ps.setInt(3, endNum);
			ps.setInt(4, startNum);
			rs = ps.executeQuery();
			while (rs.next()) {
				dto = new DTO();
				dto.setBbs_id(rs.getInt("bbs_id"));
				dto.setBbs_title(rs.getString("bbs_title"));
				dto.setBbs_writer(rs.getString("bbs_writer"));
				dto.setBbs_date(rs.getString("bbs_date"));
				dto.setSubject_id(selected);
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return list;
	}

	//학생 - 신청과목 리스트
	public ArrayList<DTO> sublist(String id, String term) {
		ArrayList<DTO> sublist = new ArrayList<>();
		String sql = "SELECT sub.subject_id, sub.subject_name FROM subject sub JOIN enroll e ON e.subject_id = sub.subject_id WHERE e.std_id=? AND sub.term_id=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, term);
			rs = ps.executeQuery();
			while(rs.next()) {
				DTO dto = new DTO();
				dto.setSubject_id(rs.getInt("subject_id"));
				dto.setSubject_name(rs.getString("subject_name"));
				sublist.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return sublist;
	}

	//글쓰기 메서드
	public int write(DTO dto) {
		long fk = 0;
		String sql = "INSERT INTO bbs(bbs_id, bbs_title, bbs_content, bbs_writer, bbs_date, subject_id, bbssort_type) VALUES(seq_bbs_id.NEXTVAL, ?,?,?,SYSDATE,?,?)";
		try {
			ps = conn.prepareStatement(sql, new String[] {"bbs_id"});
			ps.setString(1, dto.getBbs_title());
			ps.setString(2, dto.getBbs_content());
			ps.setString(3, dto.getBbs_writer());
			ps.setInt(4, dto.getSubject_id());
			ps.setString(5, dto.getBbssort_type());
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			String fileName = dto.getUpload_name();
			System.out.println("업로드된 파일 : "+fileName);
			if(rs.next()) {
				fk = rs.getLong(1);
				if (fileName != null) {
					sql = "INSERT INTO upload(upload_id, upload_name, bbs_id) VALUES (seq_upload_id.NEXTVAL,?,?)";
					ps = conn.prepareStatement(sql);
					ps.setString(1, dto.getUpload_name());
					ps.setLong(2, fk);
					ps.executeUpdate();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return (int) fk;
	}

	public DTO detail(int bbs_id, int selected, String bbssort_type) {
		DTO dto = null;
		String sql = "SELECT B.bbs_id, B.bbs_title, B.bbs_writer, B.bbs_date, B.bbs_content, B.subject_id,bs.bbssort_type FROM bbs B "
				+"JOIN bbssort bs ON B.bbssort_type = bs.bbssort_type "
				+"WHERE B.bbs_id=? AND B.subject_id=? AND bs.bbssort_type=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bbs_id);
			ps.setInt(2, selected);
			ps.setString(3, bbssort_type);
			rs = ps.executeQuery();
			if(rs.next()) {
				dto = new DTO();
				dto.setBbs_id(rs.getInt("bbs_id"));
				dto.setBbs_title(rs.getString("bbs_title"));
				dto.setBbs_writer(rs.getString("bbs_writer"));
				dto.setBbs_date(rs.getString("bbs_date"));
				dto.setBbs_content(rs.getString("bbs_content"));
				dto.setSubject_id(selected);
				dto.setBbssort_type(bbssort_type);
			}
			String newFileName = fileNameCall(bbs_id);
			if(newFileName != null) {
				dto.setUpload_name(newFileName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return dto;
	}

	//강의 질문 조회
	public DTO gradePage(int subject_id) {
		DTO dto = null;
		String sql = "SELECT s.subject_name, p.pro_name FROM subject s JOIN pro p ON p.pro_id = s.pro_id " 
						+ "WHERE s.subject_id=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, subject_id);
			rs = ps.executeQuery();
			while(rs.next()) {
				dto = new DTO();
				dto.setSubject_name(rs.getString("subject_name"));
				dto.setPro_name(rs.getString("pro_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return dto;
	}

	//학생 - 강의 평가 저장 메서드
	public DTO grade(int selected, String std_id, double aver) {
		DTO dto = null;
		String sql = "INSERT INTO grade(grade_id, grade_grade, subject_id, std_id) VALUES (seq_grade_id.NEXTVAL,?,?,?)";
		String sql2 = "update subject set subject_grade=(select avg(grade_grade) from grade where subject_id=?) where subject_id=?";
		try {
			dto = new DTO();
			ps = conn.prepareStatement(sql);
			ps.setDouble(1, aver);
			ps.setInt(2, selected);
			ps.setString(3, std_id);
			ps.executeUpdate();
			
			ps = conn.prepareStatement(sql2);
			ps.setInt(1, selected);
			ps.setInt(2, selected);
			ps.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return dto;
	}

	//교수 - 강의 과목 리스트
	public ArrayList<DTO> prosublist(String id, String term) {
		ArrayList<DTO> prosublist = new ArrayList<>();
		String sql = "SELECT subject_id, subject_name FROM subject WHERE pro_id=? AND term_id=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, term);
			rs = ps.executeQuery();
			while(rs.next()) {
				DTO dto = new DTO();
				dto.setSubject_id(rs.getInt("subject_id"));
				dto.setSubject_name(rs.getString("subject_name"));
				prosublist.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return prosublist;
	}

	//게시판 수정 메서드
	public int update(DTO dto) {
		String sql = "UPDATE bbs SET bbs_title=?, bbs_content=? WHERE bbs_id=?";
		int success = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getBbs_title());
			ps.setString(2, dto.getBbs_content());
			ps.setInt(3, dto.getBbs_id());
			success = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}finally {
			resClose();
		}
		return success;
	}

	//파일 이름 호출 메서드
	public String fileNameCall(int bbs_id) {
		System.out.println(bbs_id+" 번호의 파일 이름 호출");
		String sql = "SELECT upload_name FROM upload WHERE bbs_id=?";
		String fileName = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bbs_id);
			rs = ps.executeQuery();
			fileName = rs.next() ? rs.getString("upload_name") : null;
			System.out.println("파일 이름 호출 : "+fileName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fileName;
	}

	//파일 변경 메서드
	public void fileNameUpdate(int bbs_id, String upload_name, String oldFileName) {
		System.out.println("파일 변경"+bbs_id);
		System.out.println("파일 변경"+upload_name);
		System.out.println("파일 변경"+oldFileName);
		String sql = "";
			try {
				if(oldFileName != null) {
					sql = "UPDATE upload SET upload_name =? WHERE bbs_id =?";
					ps = conn.prepareStatement(sql);
					ps.setString(1, upload_name);
					ps.setInt(2, bbs_id);
					
				}else {
					sql = "INSERT INTO upload VALUES(seq_upload_id.NEXTVAL,?,?)";
					ps = conn.prepareStatement(sql);
					ps.setString(1, "no file");
					ps.setInt(2, bbs_id);
				}
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				resClose();
			}
	}

	//게시글 삭제 메서드
	public int del(int bbs_id) {
		System.out.println("삭제할 게시글 아이디"+bbs_id);
		int success = 0;
		String sql = "DELETE FROM bbs WHERE bbs_id=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bbs_id);
			success = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}finally {
			resClose();
		}
		return success;
	}

	// 총 게시글 수 반환
	public int totalCount(int selected, String bbssort_type) {
		String sql = "SELECT count(*) AS cnt FROM bbs WHERE subject_id=? AND bbssort_type=?";
		int cnt = 0;
	    try {
	        ps = conn.prepareStatement(sql);
	        ps.setInt(1, selected);
	        ps.setString(2, bbssort_type);
	        rs = ps.executeQuery();

	        if (rs.next()) {
	            cnt = rs.getInt("cnt");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        resClose();  // 자원 반납
	    }

	    return cnt;
	}
	
	public boolean overlay(String std_id, int selected) {
		boolean success = true;
		String sql = "SELECT grade_grade FROM grade WHERE std_id=? AND subject_id=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, std_id);
			ps.setInt(2, selected);
			rs = ps.executeQuery();
			if(rs.next()) {
				success=false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return success;
	}
}
