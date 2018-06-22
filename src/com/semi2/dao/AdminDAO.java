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
public class AdminDAO {
	Connection conn=null;
	ResultSet rs=null;
	PreparedStatement ps= null;
	public AdminDAO(){
		try {
			Context cont= new InitialContext();
			DataSource ds=(DataSource) cont.lookup("java:comp/env/jdbc/Oracle");
			conn=ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//리스트
		public ArrayList<DTO> sManagePage() {
			ArrayList<DTO> list = new ArrayList<>();
			String sql="SELECT * FROM std";
			DTO dto = null;
			System.out.println("쿼리실행");
			try {
				ps=conn.prepareStatement(sql);
				rs=ps.executeQuery();
				while(rs.next()) {
					dto=new DTO();
					dto.setStd_id(rs.getString("std_id"));
					dto.setStd_year(rs.getInt("std_year"));
					dto.setStd_name(rs.getString("std_name"));
					dto.setStd_birthday(rs.getString("std_birthday"));
					dto.setStd_state(rs.getString("std_state"));
					dto.setStd_phone(rs.getString("std_phone"));
					dto.setStd_email(rs.getString("std_email"));
					dto.setStd_address(rs.getString("std_address"));		
					list.add(dto);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}finally {
				close();
			}
			return list;
		}
		/**자원반납***************************/
		public void close() {
			try {
				if(rs  !=null) {
					rs.close();
				}
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		/**수정***************************/
		public int sUpdate(DTO dto) {
			String sql="UPDATE std SET  std_year=?,std_name=?,std_birthday=?,std_state=?,std_phone=?,std_email=?,std_address=? WHERE std_id=?" ;
			int success=0;
			try {
				ps =conn.prepareStatement(sql);
				ps.setInt(1, dto.getStd_year());
				ps.setString(2, dto.getStd_name());
				ps.setString(3, dto.getStd_birthday());
				ps.setString(4, dto.getStd_state());
				ps.setString(5, dto.getStd_phone());
				ps.setString(6, dto.getStd_email());
				ps.setString(7, dto.getStd_address());
				ps.setString(8, dto.getStd_id());
				success=ps.executeUpdate();
				System.out.println(dto.getStd_id());
			} catch (SQLException e) {
				e.printStackTrace();
				return 0;
			}finally {
				close();
			}
			return success;
		}
		/**수정폼*************/
		public DTO sUpdatePage(String studentId) {
			DTO dto=null;
			String sql = "SELECT * FROM std WHERE std_id=?";
			try {
				ps=conn.prepareStatement(sql);
				ps.setString(1, studentId);
				rs=ps.executeQuery();
				if(rs.next()) {
					dto=new DTO();
					System.out.println(rs.getString("std_id"));
					dto.setStd_id(rs.getString("std_id"));
					
					dto.setStd_year(rs.getInt("std_year"));
					dto.setStd_name(rs.getString("std_name"));
					dto.setStd_birthday(rs.getString("std_birthday"));
					dto.setStd_state(rs.getString("std_state"));
					dto.setStd_phone(rs.getString("std_phone"));
					dto.setStd_email(rs.getString("std_email"));
					dto.setStd_address(rs.getString("std_address"));		
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return dto;
			}finally {
				close();
			}
			return dto;
		}
		/**학생 등록************************/
		public int sAdd(DTO dto) {
			int success=0;
			String sql = "INSERT INTO std(std_id, std_phone, std_address, std_year, std_state, std_birthday, std_email, std_name, std_pw, major_id) " + 
					"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			try {
				ps=conn.prepareStatement(sql);
				ps.setString(1, dto.getStd_id());
				ps.setString(2, dto.getStd_phone());
				ps.setString(3, dto.getStd_address());
				ps.setInt(4, dto.getStd_year());
				ps.setString(5, dto.getStd_state());
				ps.setString(6, dto.getStd_birthday());
				ps.setString(7, dto.getStd_email());
				ps.setString(8, dto.getStd_name());
				ps.setString(9, dto.getStd_pw());
				ps.setString(10, dto.getMajor_id());
				success=ps.executeUpdate();
				System.out.println(success);
			} catch (SQLException e) {
				e.printStackTrace();
				success=0;
			}finally {
				close();
			}
			return success;
		}
		/**삭제****************************/
		public int sDel(String std_id) {
			String sql="DELETE FROM std WHERE std_id = ?" ;
			int success =0;
			try {
				ps=conn.prepareStatement(sql);
				ps.setString(1, std_id);
				success = ps.executeUpdate();
				System.out.println(std_id);
			} catch (SQLException e) {
				e.printStackTrace();
				success=0;
			}finally {
				close();
			}
			return success;
		}
		/**검색 ****************/
		public ArrayList<DTO> sSearch(String selectbox, String val) {
			ArrayList<DTO> list = new ArrayList<>();
			try {
				if(selectbox.equals("std_id")) {
					ps=conn.prepareStatement("SELECT * FROM std WHERE std_id  LIKE '%' || ? || '%'");
					ps.setString(1, val);
				}else if(selectbox.equals("std_name")) {
					ps=conn.prepareStatement("SELECT * FROM std WHERE std_name LIKE '%' || ? || '%'");
					ps.setString(1, val);
				}else if(selectbox.equals("std_state")) {
					ps=conn.prepareStatement("SELECT * FROM std WHERE std_state LIKE '%' || ? || '%'");
					ps.setString(1, val);
				}
				rs=ps.executeQuery();
				System.out.println("/////////////");
				while(rs.next()) {
					DTO dto=new DTO();
					System.out.println(rs.getString("std_id"));
					dto.setStd_id(rs.getString("std_id"));
					dto.setStd_year(rs.getInt("std_year"));
					dto.setStd_name(rs.getString("std_name"));
					dto.setStd_birthday(rs.getString("std_birthday"));
					dto.setStd_state(rs.getString("std_state"));
					dto.setStd_phone(rs.getString("std_phone"));
					dto.setStd_email(rs.getString("std_email"));
					dto.setStd_address(rs.getString("std_address"));		
					list.add(dto);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}finally {
				close();
			}
			return list;
		}
		/**등록금*****************************/
		public ArrayList<DTO> tMangePage() {
			ArrayList<DTO> list = new ArrayList<DTO>();
			String sql="SELECT t.term_id,s.std_id,s.std_name,sc.scholar_name,sc.scholar_money,t.tuition_money,t.tuition_id "
					+ "FROM scholar sc JOIN tuition t ON sc.scholar_id = t.scholar_id JOIN std s ON t.std_id = s.std_id"; 
			DTO dto = null;
			try {
				ps=conn.prepareStatement(sql);
				rs=ps.executeQuery();
				while(rs.next()) {
					dto=new DTO();
					dto.setTerm_id(rs.getString("term_id"));
					dto.setStd_id(rs.getString("std_id"));
					dto.setStd_name(rs.getString("std_name"));
					dto.setScholar_name(rs.getString("scholar_name"));
					dto.setScholar_money(rs.getInt("scholar_money"));
					dto.setTuition_money(rs.getInt("tuition_money"));
					dto.setTotalMoney(rs.getInt("tuition_money")-rs.getInt("scholar_money"));
					dto.setTuition_id(rs.getInt("tuition_id"));
					list.add(dto);
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}finally {
				close();
			}
			return list;
		
		}
		/**등록금수정폼 **********/
		public DTO tUpdatePage(String studentId, String term_id) {
				DTO dto=null;
				String sql = "SELECT t.term_id,s.std_id,s.std_name,sc.scholar_name,sc.scholar_money,t.tuition_money " + 
					 "FROM scholar sc JOIN tuition t ON sc.scholar_id = t.scholar_id JOIN std s ON t.std_id = s.std_id WHERE t.std_id=? AND t.term_id=?"; 
				try {
					ps=conn.prepareStatement(sql);
					ps.setString(1, studentId);
					ps.setString(2, term_id);
					rs=ps.executeQuery();
					
					if(rs.next()) {
						dto=new DTO();
						dto.setTerm_id(rs.getString("term_id"));
						dto.setStd_id(rs.getString("std_id"));
						dto.setStd_name(rs.getString("std_name"));
						dto.setScholar_name(rs.getString("scholar_name"));
						dto.setScholar_money(rs.getInt("scholar_money"));
						dto.setTuition_money(rs.getInt("tuition_money"));
					}
				} catch (SQLException e) {
					e.printStackTrace();
					return dto;
				}finally {
					close();
				}
				return dto;
		}
		/**등록금수정**********/
		public int tUpdate(DTO dto) {
			String sql="UPDATE tuition SET tuition_money=? WHERE std_id=? AND term_id=?";
			int success = 0;
			try {
				ps=conn.prepareStatement(sql);
				ps.setInt(1, dto.getTuition_money());
				ps.setString(2, dto.getStd_id());
				ps.setString(3, dto.getTerm_id());
				success=ps.executeUpdate();
				System.out.println(success);
			} catch (SQLException e) {
				e.printStackTrace();
				success=0;
			}finally {
				close();
			}
			return success;
		}
		/**등록금 삭제************/
		public int tDell(int tuition_id) {
			String sql="DELETE FROM tuition WHERE tuition_id=?" ;
			int success =0;
			try {
				ps=conn.prepareStatement(sql);
				ps.setInt(1,tuition_id );
				success = ps.executeUpdate();
				System.out.println(tuition_id);
			} catch (SQLException e) {
				e.printStackTrace();
				success=0;
			}finally {
				close();
			}
			return success;
		}
		/**등록금 등록*****************/
		public int tAdd(DTO dto) {
			int success=0;
			String sql = "INSERT INTO tuition(tuition_id, tuition_money,std_id,scholar_id,term_id) VALUES (seq_tuition_id.NEXTVAL,?,?,?,?) ";
			try {
				ps=conn.prepareStatement(sql);
				ps.setInt(1, dto.getTuition_money());
				ps.setString(2, dto.getStd_id());
				ps.setInt(3, dto.getScholar_id());
				ps.setString(4, dto.getTerm_id());
				success=ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				success=0;
			}finally {
				close();
			}
			return success;
		}
		/**등록금 검색*************/
		public ArrayList<DTO> tSearch(String selectbox, String val) {
			ArrayList<DTO> list = new ArrayList<>();
			try {
				if(selectbox.equals("std_id")) {
					ps=conn.prepareStatement("SELECT t.term_id,s.std_id,s.std_name,sc.scholar_name,sc.scholar_money,t.tuition_money " + 
							"FROM scholar sc JOIN tuition t ON sc.scholar_id = t.scholar_id JOIN std s ON t.std_id = s.std_id WHERE t.std_id LIKE '%' || ? || '%'");
					ps.setString(1, val);
				}else if(selectbox.equals("std_name")) {
					ps=conn.prepareStatement("SELECT t.term_id,s.std_id,s.std_name,sc.scholar_name,sc.scholar_money,t.tuition_money " + 
							"FROM scholar sc JOIN tuition t ON sc.scholar_id = t.scholar_id JOIN std s ON t.std_id = s.std_id WHERE s.std_name LIKE '%' || ? || '%'");
					ps.setString(1, val);
				}else if(selectbox.equals("term_id")) {
					ps=conn.prepareStatement("SELECT t.term_id,s.std_id,s.std_name,sc.scholar_name,sc.scholar_money,t.tuition_money " + 
							"FROM scholar sc JOIN tuition t ON sc.scholar_id = t.scholar_id JOIN std s ON t.std_id = s.std_id WHERE t.term_id LIKE '%' || ? || '%'");
					ps.setString(1, val);
				}
				rs=ps.executeQuery();
				while(rs.next()) {
					DTO dto=new DTO();
					dto.setTerm_id(rs.getString("term_id"));
					dto.setStd_id(rs.getString("std_id"));
					dto.setStd_name(rs.getString("std_name"));
					dto.setScholar_name(rs.getString("scholar_name"));
					dto.setScholar_money(rs.getInt("scholar_money"));
					dto.setTuition_money(rs.getInt("tuition_money"));
					dto.setTotalMoney(rs.getInt("tuition_money")-rs.getInt("scholar_money"));
					list.add(dto);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}finally {
				close();
			}
			return list;
		}
		/**장학금 리스트****************/
		public ArrayList<DTO> scPage() {
			ArrayList<DTO> list = new ArrayList<>();
			String sql="SELECT * FROM scholar";
			DTO dto = null;
			try {
				ps=conn.prepareStatement(sql);
				rs=ps.executeQuery();
				while(rs.next()) {
					dto=new DTO();
					dto.setScholar_id(rs.getInt("scholar_id"));
					dto.setScholar_name(rs.getString("scholar_name"));
					dto.setScholar_money(rs.getInt("scholar_money"));
					list.add(dto);
				}
			} catch (SQLException e) {
				 e.printStackTrace();
				 return null;
			}finally {
				close();
			}
				return list;
		}
		/**장학금 수정폼****************/
		public DTO scUpdatePage(int scholar_id) {
			DTO dto=null;
			String sql = "SELECT * FROM scholar WHERE scholar_id=?"; 
			try {
				ps=conn.prepareStatement(sql);
				ps.setInt(1, scholar_id);
				rs=ps.executeQuery();
				if(rs.next()) {
					dto=new DTO();
					dto.setScholar_id(rs.getInt("scholar_id"));
					dto.setScholar_name(rs.getString("scholar_name"));
					dto.setScholar_money(rs.getInt("scholar_money"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return dto;
			}finally {
				close();
			}
			return dto;
		}
		/**장학금 수정***************/
		public int scUpdate(DTO dto) {
			String sql="UPDATE scholar SET scholar_name=?, scholar_money=? WHERE scholar_id=?";
			int success = 0;
			try {
				ps=conn.prepareStatement(sql);
				ps.setString(1, dto.getScholar_name());
				ps.setInt(2, dto.getScholar_money());
				ps.setInt(3, dto.getScholar_id());
				success=ps.executeUpdate();
				System.out.println(success);
			} catch (SQLException e) {
				e.printStackTrace();
				success=0;
			}finally {
				close();
			}
			return success;
		}
		/**장학금 삭제***************/
		public int scDel(int scholar_id) {
			String sql="DELETE FROM scholar WHERE scholar_id = ?" ;
			int success =0;
			try {
				ps=conn.prepareStatement(sql);
				ps.setInt(1, scholar_id);
				success = ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				success=0;
			}finally {
				close();
			}
			return success;
		}
		/**장학금 등록***************/
		public int scAdd(DTO dto) {
			int success=0;
			String sql = "INSERT INTO scholar (scholar_id,scholar_name,scholar_money) VALUES (seq_scholar_id.NEXTVAL,?,?) ";
			try {
				ps=conn.prepareStatement(sql);
				ps.setString(1, dto.getScholar_name());
				ps.setInt(2, dto.getScholar_money());
				success=ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				success=0;
			}finally {
				close();
			}
			return success;
		}
		/**장학금 관리 리스트*************************/
		public ArrayList<DTO> ePage() {
			ArrayList<DTO> list = new ArrayList<DTO>();
			String sql="SELECT t.term_id,s.std_id,s.std_name,sc.scholar_name,sc.scholar_money,t.tuition_id "
					+"FROM scholar sc JOIN tuition t ON sc.scholar_id = t.scholar_id JOIN std s ON t.std_id = s.std_id"; 
			DTO dto = null;
			try {
				ps=conn.prepareStatement(sql);
				rs=ps.executeQuery();
				while(rs.next()) {
					dto=new DTO();
					dto.setTerm_id(rs.getString("term_id"));
					dto.setStd_id(rs.getString("std_id"));
					dto.setStd_name(rs.getString("std_name"));
					dto.setScholar_name(rs.getString("scholar_name"));
					dto.setScholar_money(rs.getInt("scholar_money"));
					dto.setTuition_id(rs.getInt("tuition_id"));
					list.add(dto);
				}
			} catch (SQLException e) {
				 e.printStackTrace();
				 return null;
			}finally {
				close();
			}
				return list;
		}
		/**장학금 관리 등록************/
		public int eAdd(DTO dto) {
			int success=0;
			String sql = "INSERT INTO tuition(tuition_id, std_id, scholar_id, term_id) VALUES(seq_tuition_id.NEXTVAL,?,?,? )";
			try {
				ps=conn.prepareStatement(sql);
				ps.setString(1, dto.getStd_id());
				ps.setInt(2, dto.getScholar_id());
				ps.setString(3, dto.getTerm_id());
				success=ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				success=0;
			}finally {
				close();
			}
			return success;
		}
		/**장학금 관리 수정 폼***************/
		public DTO eUpdatePage(int tuition_id) {
			DTO dto=null;
			String sql = "SELECT t.term_id,s.std_id,s.std_name,sc.scholar_id,t.tuition_id " + 
				 "FROM scholar sc JOIN tuition t ON sc.scholar_id = t.scholar_id JOIN std s ON t.std_id = s.std_id WHERE t.tuition_id=?"; 
			try {
				ps=conn.prepareStatement(sql);
				ps.setInt(1, tuition_id);
				rs=ps.executeQuery();
				if(rs.next()) {
					dto=new DTO();
					dto.setTerm_id(rs.getString("term_id"));
					dto.setStd_id(rs.getString("std_id"));
					dto.setStd_name(rs.getString("std_name"));
					dto.setScholar_id(rs.getInt("scholar_id"));
					dto.setTuition_id(rs.getInt("tuition_id"));	
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return dto;
			}finally {
				close();
			}
			return dto;
		}
		/**장학금 관리 수정 ***************/
		public int eUpdate(DTO dto) {
			String sql="UPDATE tuition SET term_id=? , scholar_id=? WHERE tuition_id=?";
			int success = 0;
			try {
				ps=conn.prepareStatement(sql);
				ps.setString(1, dto.getTerm_id());
				ps.setInt(2, dto.getScholar_id());
				ps.setInt(3, dto.getTuition_id());
				success=ps.executeUpdate();
				System.out.println(dto.getTuition_id());
				System.out.println(dto.getScholar_id());
				System.out.println(success);
			} catch (SQLException e) {
				e.printStackTrace();
				success=0;
			}finally {
				close();
			}
			return success;
		}
		/**장학금 관리 검색 ******************/
		public ArrayList<DTO> eSearch(String selectbox, String val) {
			ArrayList<DTO> list = new ArrayList<>();
			try {
				if(selectbox.equals("std_id")) {
					ps=conn.prepareStatement("SELECT t.term_id,s.std_id,s.std_name,sc.scholar_name,sc.scholar_money " + 
							"FROM scholar sc JOIN tuition t ON sc.scholar_id = t.scholar_id JOIN std s ON t.std_id = s.std_id WHERE t.std_id LIKE '%' || ? || '%'");
					ps.setString(1, val);
				}else if(selectbox.equals("std_name")) {
					ps=conn.prepareStatement("SELECT t.term_id,s.std_id,s.std_name,sc.scholar_name,sc.scholar_money " + 
							"FROM scholar sc JOIN tuition t ON sc.scholar_id = t.scholar_id JOIN std s ON t.std_id = s.std_id WHERE s.std_name LIKE '%' || ? || '%'" );
					ps.setString(1, val);
				}else if(selectbox.equals("scholar_name")) {
					ps=conn.prepareStatement("SELECT t.term_id,s.std_id,s.std_name,sc.scholar_name,sc.scholar_money " + 
							"FROM scholar sc JOIN tuition t ON sc.scholar_id = t.scholar_id JOIN std s ON t.std_id = s.std_id WHERE t.scholar_name LIKE '%' || ? || '%'");
					ps.setString(1, val);
				}
				rs=ps.executeQuery();
				while(rs.next()) {
					DTO dto=new DTO();
					dto.setTerm_id(rs.getString("term_id"));
					dto.setStd_id(rs.getString("std_id"));
					dto.setStd_name(rs.getString("std_name"));
					dto.setScholar_name(rs.getString("scholar_name"));
					dto.setScholar_money(rs.getInt("scholar_money"));
					
					list.add(dto);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}finally {
				close();
			}
			return list;
		}
		/**교수리스트-***********************/
		public ArrayList<DTO> pManagePage() {
			ArrayList<DTO> list = new ArrayList<>();
			String sql="SELECT * FROM pro P JOIN major M ON P.major_id=M.major_id";
			DTO dto = null;
			try {
				ps=conn.prepareStatement(sql);
				rs=ps.executeQuery();
				while(rs.next()) {
					dto=new DTO();
					dto.setPro_id(rs.getString("pro_id"));
					dto.setPro_phone(rs.getString("pro_phone"));
					dto.setPro_name(rs.getString("pro_name"));
					dto.setPro_room(rs.getString("pro_room"));
					dto.setPro_email(rs.getString("pro_email"));
					dto.setMajor_name(rs.getString("major_name"));
					list.add(dto);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}finally {
				close();
			}
			return list;
		}
		/**교수 수정 페이지*******************/
		public DTO pUpdatePage(String pro_id) {
			DTO dto=null;
			String sql = "SELECT * FROM pro P JOIN major M ON P.major_id=M.major_id WHERE pro_id=?";
			try {
				ps=conn.prepareStatement(sql);
				ps.setString(1, pro_id);
				rs=ps.executeQuery();
				if(rs.next()) {
					dto=new DTO();
					dto.setPro_id(rs.getString("pro_id"));
					dto.setPro_phone(rs.getString("pro_phone"));
					dto.setPro_name(rs.getString("pro_name"));
					dto.setPro_room(rs.getString("pro_room"));
					dto.setPro_email(rs.getString("pro_email"));
					dto.setMajor_id(rs.getString("major_id"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return dto;
			}finally {
				close();
			}
			return dto;
		}
		/**교수 수정 *******************/
		public int pUpdate(DTO dto) {
			String sql="UPDATE pro SET  pro_id=?,pro_name=?,pro_phone=?,pro_email=?,pro_room=?, major_id=? WHERE pro_id=?" ;
			int success=0;
			try {
				ps =conn.prepareStatement(sql);
				ps.setString(1, dto.getPro_id());
				ps.setString(2, dto.getPro_name());
				ps.setString(3, dto.getPro_phone());
				ps.setString(4, dto.getPro_email());
				ps.setString(5, dto.getPro_room());
				ps.setString(6, dto.getMajor_id());
				ps.setString(7, dto.getPro_id());
				success=ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				return 0;
			}finally {
				close();
			}
			return success;
		}
		/**교수 삭제 *******************/
		public int pDel(String pro_id) {
			String sql="DELETE FROM pro WHERE pro_id = ?" ;
			int success =0;
			try {
				ps=conn.prepareStatement(sql);
				ps.setString(1, pro_id);
				success = ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				success=0;
			}finally {
				close();
			}
			return success;
		}
		/**교수 등록 *******************/
		public int pAdd(DTO dto) {
			int success=0;
			String sql = "INSERT INTO pro(pro_id, pro_phone, pro_name, pro_room, pro_email, pro_pw, major_id) " + 
					"VALUES (?, ?, ?, ?, ?, ?, ?)";
			try {
				ps=conn.prepareStatement(sql);
				ps.setString(1, dto.getPro_id());
				ps.setString(2, dto.getPro_phone());
				ps.setString(3, dto.getPro_name());
				ps.setString(4, dto.getPro_room());
				ps.setString(5, dto.getPro_email());
				ps.setString(6, dto.getPro_pw());
				ps.setString(7, dto.getMajor_id());
				success=ps.executeUpdate();
				System.out.println(success);
			} catch (SQLException e) {
				e.printStackTrace();
				success=0;
			}finally {
				close();
			}
			return success;
		}
		/**교수 검색 *******************/
		public ArrayList<DTO> pSearch(String selectbox, String val) {
			ArrayList<DTO> list = new ArrayList<>();
			try {
				if(selectbox.equals("pro_id")) {
					ps=conn.prepareStatement("SELECT * FROM pro P JOIN major M ON P.major_id=M.major_id WHERE pro_id LIKE '%' || ? || '%'");
					ps.setString(1, val);
				}else if(selectbox.equals("pro_name")) {
					ps=conn.prepareStatement("SELECT * FROM pro P JOIN major M ON P.major_id=M.major_id WHERE pro_name LIKE '%' || ? || '%'");
					ps.setString(1, val);
				}else if(selectbox.equals("major_name")) {
					ps=conn.prepareStatement("SELECT * FROM pro P JOIN major M ON P.major_id=M.major_id WHERE major_name LIKE '%' || ? || '%'");
					ps.setString(1, val);
				}
				rs=ps.executeQuery();
				while(rs.next()) {
					DTO dto=new DTO();
					dto.setPro_id(rs.getString("pro_id"));
					dto.setPro_phone(rs.getString("pro_phone"));
					dto.setPro_name(rs.getString("pro_name"));
					dto.setPro_room(rs.getString("pro_room"));
					dto.setPro_email(rs.getString("pro_email"));
					dto.setMajor_name(rs.getString("major_name"));
					list.add(dto);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}finally {
				close();
			}
			return list;
		}
		/**과목 리스트**************************/
		public ArrayList<DTO> suManagePage() {
			ArrayList<DTO> list = new ArrayList<>();
				String sql="SELECT subject_name, subject_room, subject_time, subject_type, subject_grade, subject_credit, subject_limit, term_id, M.major_name, P.pro_name, P.pro_id " + 
						"FROM subject S " + 
						"JOIN major M " + 
						"ON S.major_id = M.major_id " + 
						"JOIN pro P " + 
						"ON S.pro_id = P.pro_id";
			DTO dto = null;
			System.out.println("쿼리실행");
			try {
				ps=conn.prepareStatement(sql);
				rs=ps.executeQuery();
				while(rs.next()) {
					dto=new DTO();
					dto.setMajor_name(rs.getString("major_name"));
					dto.setSubject_name(rs.getString("subject_name"));
					dto.setPro_name(rs.getString("pro_name"));
					dto.setSubject_room(rs.getString("subject_room"));
					dto.setSubject_time(rs.getString("subject_time"));
					dto.setSubject_type(rs.getString("subject_type"));
					dto.setSubject_credit(rs.getInt("subject_credit"));
					dto.setSubject_limit(rs.getInt("subject_limit"));
					dto.setSubject_grade(rs.getDouble("subject_grade"));
					dto.setTerm_id(rs.getString("term_id"));
					dto.setPro_id(rs.getString("pro_id"));
					list.add(dto);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}finally {
				close();
			}
			return list;
		}
		/**과목 삭제**************************/
		public int suDel(String pro_id, String subject_time) {
			String sql="DELETE FROM subject WHERE pro_id = ? AND subject_time=?" ;
			int success =0;
			try {
				ps=conn.prepareStatement(sql);
				ps.setString(1, pro_id);
				ps.setString(2, subject_time);
				System.out.println(pro_id);
				success = ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				success=0;
			}finally {
				close();
			}
			return success;
		}
		/**과목 등록**************************/
		public int suAdd(DTO dto) {
			int success=0;
			String sql = "INSERT INTO subject(subject_id, term_id,major_id, pro_id, subject_name,"
					+ "subject_room,subject_time,subject_type,subject_grade,subject_credit,subject_limit ,subject_count) " + 
					"VALUES (?, ?, ?, ?, ?, ?, ?,?,?,?,?,?)";
			try {
				ps=conn.prepareStatement(sql);
				ps.setInt(1, dto.getSubject_id());
				ps.setString(2, dto.getTerm_id());
				ps.setString(3, dto.getMajor_id());
				ps.setString(4, dto.getPro_id());
				ps.setString(5, dto.getSubject_name());
				ps.setString(6, dto.getSubject_room());
				ps.setString(7, dto.getSubject_time());
				ps.setString(8, dto.getSubject_type());
				ps.setDouble(9, dto.getSubject_grade());
				ps.setInt(10, dto.getSubject_credit());
				ps.setInt(11, dto.getSubject_limit());
				ps.setInt(12, dto.getSubject_limit());
				success=ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				success=0;
			}finally {
				close();
			}
			return success;
		}
		/**과목 수정페이지*****************************/
		public DTO suUpdatePage(String pro_id, String subject_time) {
			DTO dto=null;
			String sql = "SELECT * FROM subject WHERE pro_id=? AND subject_time=?";
			try {
				ps=conn.prepareStatement(sql);
				ps.setString(1, pro_id);
				ps.setString(2, subject_time);
				rs=ps.executeQuery();
				if(rs.next()) {
					dto=new DTO();
					dto.setSubject_id(rs.getInt("subject_id"));
					dto.setTerm_id(rs.getString("term_id"));
					dto.setMajor_id(rs.getString("major_id"));
					dto.setPro_id(rs.getString("pro_id"));
					dto.setSubject_name(rs.getString("subject_name"));
					dto.setSubject_room(rs.getString("subject_room"));
					dto.setSubject_time(rs.getString("subject_time"));
					dto.setSubject_type(rs.getString("subject_type"));
					dto.setSubject_grade(rs.getDouble("subject_grade"));
					dto.setSubject_credit(rs.getInt("subject_credit"));
					dto.setSubject_limit(rs.getInt("subject_limit"));	
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return dto;
			}finally {
				close();
			}
			return dto;
		}
		/**과목 수정**********************/
		public int suUpdate(DTO dto) {
			String sql="UPDATE subject SET  major_id=?,subject_id=?,pro_id=?,subject_room=?,subject_name=?, subject_time=?, "
					+ "subject_type=?,subject_credit=?,subject_limit=?,subject_grade=?,term_id=? WHERE subject_id=?" ;
			int success=0;
			try {
				ps =conn.prepareStatement(sql);
				ps.setString(1, dto.getMajor_id());
				ps.setInt(2, dto.getSubject_id());
				ps.setString(3, dto.getPro_id());
				ps.setString(4, dto.getSubject_room());
				ps.setString(5, dto.getSubject_name());
				ps.setString(6, dto.getSubject_time());
				ps.setString(7, dto.getSubject_type());
				ps.setInt(8, dto.getSubject_credit());
				ps.setInt(9, dto.getSubject_limit());
				ps.setDouble(10, dto.getSubject_grade());
				ps.setString(11, dto.getTerm_id());
				ps.setInt(12, dto.getSubject_id());
				success=ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				return 0;
			}finally {
				close();
			}
			return success;
		}
		/** 과목검색*************/
		public ArrayList<DTO> suSearch(String selectbox, String val) {
			ArrayList<DTO> list = new ArrayList<>();
			try {
				if(selectbox.equals("major_name")) {
					ps=conn.prepareStatement("SELECT subject_name, subject_room, subject_time, subject_type, subject_grade, subject_credit, subject_limit, term_id, M.major_name, P.pro_name, P.pro_id " + 
							"FROM subject S " + 
							"JOIN major M " + 
							"ON S.major_id = M.major_id " + 
							"JOIN pro P " + 
							"ON S.pro_id = P.pro_id " + 
							"WHERE M.major_name LIKE '%' || ? || '%'");
					ps.setString(1, val);
				}else if(selectbox.equals("subject_name")) {
					ps=conn.prepareStatement("SELECT subject_name, subject_room, subject_time, subject_type, subject_grade, subject_credit, subject_limit, term_id, M.major_name, P.pro_name, P.pro_id " + 
							"FROM subject S " + 
							"JOIN major M " + 
							"ON S.major_id = M.major_id " + 
							"JOIN pro P " + 
							"ON S.pro_id = P.pro_id " + 
							"WHERE S	.subject_name LIKE '%' || ? || '%'");
					ps.setString(1, val);
				}else if(selectbox.equals("pro_name")) {
					ps=conn.prepareStatement("SELECT subject_name, subject_room, subject_time, subject_type, subject_grade, subject_credit, subject_limit, term_id, M.major_name, P.pro_name, P.pro_id " + 
							"FROM subject S " + 
							"JOIN major M " + 
							"ON S.major_id = M.major_id " + 
							"JOIN pro P " + 
							"ON S.pro_id = P.pro_id " + 
							"WHERE P.pro_name LIKE '%' || ? || '%'");
					ps.setString(1, val);
				}
				rs=ps.executeQuery();
				while(rs.next()) {
					DTO dto=new DTO();
					dto.setMajor_name(rs.getString("major_name"));
					dto.setSubject_name(rs.getString("subject_name"));
					dto.setPro_name(rs.getString("pro_name"));
					dto.setSubject_room(rs.getString("subject_room"));
					dto.setSubject_time(rs.getString("subject_time"));
					dto.setSubject_type(rs.getString("subject_type"));
					dto.setSubject_credit(rs.getInt("subject_credit"));
					dto.setSubject_limit(rs.getInt("subject_limit"));
					dto.setSubject_grade(rs.getDouble("subject_grade"));
					dto.setTerm_id(rs.getString("term_id"));
					dto.setPro_id(rs.getString("pro_id"));
					list.add(dto);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}finally {
				close();
			}
			return list;
		}
		/**강의평가 질문 리스트******************/
		public ArrayList<DTO> gPage() {
			ArrayList<DTO> list = new ArrayList<>();
			String sql="SELECT * FROM question";
		DTO dto = null;
		System.out.println("쿼리실행");
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()) {
				dto=new DTO();
				dto.setQuestion_id(rs.getInt("question_id"));
				dto.setQuestion_question(rs.getString("question_question"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			close();
		}
		return list;
		}
		/**강의평가 질문 등록***************/
		public int gAdd(DTO dto) {
			int success=0;
			String sql = "INSERT INTO question(question_id, question_question) VALUES(seq_question_id.NEXTVAL, ?)";
			try {
				ps=conn.prepareStatement(sql);
				ps.setString(1, dto.getQuestion_question());
				success=ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				success=0;
			}finally {
				close();
			}
			return success;
		}
		/**강의평가 질문 수정페이지***************/
		public DTO gUpdatePage(int question_id) {
			DTO dto=null;
			String sql = "SELECT * FROM question WHERE question_id=?";
			try {
				ps=conn.prepareStatement(sql);
				ps.setInt(1, question_id);
				rs=ps.executeQuery();
				if(rs.next()) {
					dto=new DTO();
					dto.setQuestion_id(rs.getInt("question_id"));
					dto.setQuestion_question(rs.getString("question_question"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return dto;
			}finally {
				close();
			}
			return dto;
		}
		/**강의평가 질문 수정***************/
		public int gUpdate(DTO dto) {
			String sql="UPDATE question SET  question_question=? WHERE question_id=?" ;
			int success=0;
			try {
				ps =conn.prepareStatement(sql);
				ps.setString(1, dto.getQuestion_question());
				ps.setInt(2, dto.getQuestion_id());
				success=ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				return 0;
			}finally {
				close();
			}
			return success;
		}
		/**강의평가 질문 삭제***************/
		public int gDel(int question_id) {
			String sql="DELETE FROM question WHERE question_id = ?" ;
			int success =0;
			try {
				ps=conn.prepareStatement(sql);
				ps.setInt(1, question_id);
				success = ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				success=0;
			}finally {
				close();
			}
			return success;
		}
		/**학사 일정 등록 *************/
		public int caAdd(DTO dto, String content, String getdate) {
			int success=0;
			String sql = "INSERT INTO schedule(schedule_id, schedule_date, schedule_content) " + 
					"VALUES(seq_schedule_id.NEXTVAL, ?, ?)";
			try {
				ps=conn.prepareStatement(sql);
				ps.setString(1, getdate);
				ps.setString(2, content);
				success=ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				success=0;
			}finally {
				close();
			}
			return success;
		}
		/**학사 일정 삭제  *************/
		public int caDell(int schedule_id) {
			String sql="DELETE FROM schedule WHERE schedule_id=?" ;
			int success =0;
			try {
				ps=conn.prepareStatement(sql);
				ps.setInt(1, schedule_id);
				System.out.println(schedule_id);
				success = ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				success=0;
			}finally {
				close();
			}
			return success;
		}
		/**학사 일정 수정  *************/
		public Integer caUpdate(int schedule_id, String schedule_content) {
			String sql="UPDATE schedule SET  schedule_content=? WHERE schedule_id=?" ;
			int success=0;
			try {
				ps =conn.prepareStatement(sql);
				ps.setString(1, schedule_content);
				System.out.println(schedule_content);
				ps.setInt(2, schedule_id);
				success=ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				return 0;
			}finally {
				close();
			}
			return success;
		}
}
