package com.semi2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sound.midi.Synthesizer;
import javax.sql.DataSource;

import com.semi2.dto.DTO;

public class EnrollDAO {
	Connection conn = null;
	PreparedStatement ps =null;
	ResultSet rs = null;
	/*생성자 : 객체화 시 가장 먼저 실행*/
	public EnrollDAO()  {

		try {
			//1.DB 정보가 담겨있는 context.xml과 객체화
			Context ctx = new InitialContext();
			//2.데이터를 추출 하고 데이터를 통해 커넥션을 추출한다.
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/Oracle");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*optValue 에 따른 총 게시글이 몇개 인지 연산 메서드*/
	//optValue 로 쿼리를 분류 하는 이유는 ..
	//조건식에 따라서 총 게시글 수가 다르고 ,  조건이 다르기 때문!
	public int listCount(String optValue,String inpValue, String term_id) {
		int cnt =0;
		String sql ="";
		if(optValue.equals("entry")) {
			sql =" SELECT count(*) AS cnt "
					+" FROM subject S "
					+" JOIN major M "
					+" ON S.major_id = M.major_id "
					+" JOIN pro P "
					+" ON S.pro_id = P.pro_id  "
					+" WHERE S.term_id "+ term_id 
					+ "ORDER BY term_id DESC ";
		}else if(optValue.equals("term")) {
			sql =" SELECT count(*) AS cnt "
					+" FROM subject S "
					+" JOIN major M "
					+" ON S.major_id = M.major_id "
					+" JOIN pro P "
					+" ON S.pro_id = P.pro_id  "
					+" WHERE S.term_id "+ term_id 
					+ "AND S.term_id Like '%"+inpValue+"%'"
					+ "ORDER BY term_id DESC ";
		}else if(optValue.equals("pro")) {
			sql =" SELECT count(*) AS cnt "
					+" FROM subject S "
					+" JOIN major M "
					+" ON S.major_id = M.major_id "
					+" JOIN pro P "
					+" ON S.pro_id = P.pro_id  "
					+" WHERE S.term_id "+ term_id 
					+ "AND P.pro_name Like '%"+inpValue+"%'"
					+ "ORDER BY term_id DESC ";
		}else if(optValue.equals("maj")) {
			sql =" SELECT count(*) AS cnt "
					+" FROM subject S "
					+" JOIN major M "
					+" ON S.major_id = M.major_id "
					+" JOIN pro P "
					+" ON S.pro_id = P.pro_id  "
					+" WHERE S.term_id "+ term_id 
					+ "AND M.major_name Like '%"+inpValue+"%'"
					+ "ORDER BY term_id DESC ";
		}else {
			sql =" SELECT count(*) AS cnt "
					+" FROM subject S "
					+" JOIN major M "
					+" ON S.major_id = M.major_id "
					+" JOIN pro P "
					+" ON S.pro_id = P.pro_id  "
					+" WHERE S.term_id "+ term_id 
					+ "AND S.subject_name Like '%"+inpValue+"%'"
					+ "ORDER BY term_id DESC ";
		}
				
		try {
			ps =conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				cnt =rs.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return cnt;
	}
	/*이 전 학기의 과목 평점 조회 필터링 검색*/
	public ArrayList<DTO> subjectSearch(String optValue,String inpValue,String term_id, int startNum, int endNum ) {
		System.out.println("subjectSearch DAO");
		ArrayList<DTO> list = new ArrayList<DTO>();
		String sql ="";
		if(optValue.equals("entry")) {
			System.out.println("전체");
			sql =" SELECT A.rnum, A.subject_id,A.term_id ,A.major_name, A.subject_name," 
					+" A.pro_name, A.subject_room, A.subject_time,"
					+" A.subject_type, A.subject_credit, A.subject_limit, subject_grade,subject_count "  
					+" FROM "
					+" (SELECT ROWNUM AS rnum,B.subject_id,B.term_id ,B.major_name, B.subject_name, "
					+" B.pro_name, B.subject_room, "
					+" B.subject_time,B.subject_type, B.subject_credit, B.subject_limit, B.subject_grade,"
					+" B.subject_count " 
					+" FROM "
					+" (SELECT S.subject_id,S.term_id ,M.major_name, S.subject_name, P.pro_name, "
					+" S.subject_room, S.subject_time,"
					+" S.subject_type, S.subject_credit, S.subject_limit, S.subject_grade,"
					+" S.subject_count"   
					+" FROM subject S "
					+" JOIN major M "
					+" ON S.major_id = M.major_id " 
					+" JOIN pro P "
					+" ON S.pro_id = P.pro_id   "
					+" WHERE S.term_id "+ term_id +"ORDER BY term_id DESC) B "
					+" WHERE rownum <= ?) A " //endNum
					+" WHERE A.rnum >=? " ; //startNum
		}else if(optValue.equals("term")) {
			System.out.println("학기");
			sql =" SELECT A.rnum, A.subject_id,A.term_id ,A.major_name, A.subject_name," 
					+" A.pro_name, A.subject_room, A.subject_time,"
					+" A.subject_type, A.subject_credit, A.subject_limit, subject_grade,subject_count "  
					+" FROM "
					+" (SELECT ROWNUM AS rnum,B.subject_id,B.term_id ,B.major_name, B.subject_name, "
					+" B.pro_name, B.subject_room, "
					+" B.subject_time,B.subject_type, B.subject_credit, B.subject_limit, B.subject_grade,"
					+" B.subject_count " 
					+" FROM "
					+" (SELECT S.subject_id,S.term_id ,M.major_name, S.subject_name, P.pro_name, "
					+" S.subject_room, S.subject_time,"
					+" S.subject_type, S.subject_credit, S.subject_limit, S.subject_grade,"
					+" S.subject_count"   
					+" FROM subject S "
					+" JOIN major M "
					+" ON S.major_id = M.major_id " 
					+" JOIN pro P "
					+" ON S.pro_id = P.pro_id   "
					+" WHERE S.term_id "+ term_id + "AND S.term_id Like '%"+inpValue+"%'"
					+" ORDER BY term_id DESC) B "
					+" WHERE rownum <= ?) A " //endNum
					+" WHERE A.rnum >=? " ; //startNum
		}else if(optValue.equals("pro")) {
			System.out.println("교수");
			sql =" SELECT A.rnum, A.subject_id,A.term_id ,A.major_name, A.subject_name," 
					+" A.pro_name, A.subject_room, A.subject_time,"
					+" A.subject_type, A.subject_credit, A.subject_limit, subject_grade,subject_count "  
					+" FROM "
					+" (SELECT ROWNUM AS rnum,B.subject_id,B.term_id ,B.major_name, B.subject_name, "
					+" B.pro_name, B.subject_room, "
					+" B.subject_time,B.subject_type, B.subject_credit, B.subject_limit, B.subject_grade,"
					+" B.subject_count " 
					+" FROM "
					+" (SELECT S.subject_id,S.term_id ,M.major_name, S.subject_name, P.pro_name, "
					+" S.subject_room, S.subject_time,"
					+" S.subject_type, S.subject_credit, S.subject_limit, S.subject_grade,"
					+" S.subject_count"   
					+" FROM subject S "
					+" JOIN major M "
					+" ON S.major_id = M.major_id " 
					+" JOIN pro P "
					+" ON S.pro_id = P.pro_id   "
					+" WHERE S.term_id "+ term_id + "AND P.pro_name Like '%"+inpValue+"%'"
					+" ORDER BY term_id DESC) B "
					+" WHERE rownum <= ?) A " //endNum
					+" WHERE A.rnum >=? " ; //startNum

		}else if(optValue.equals("maj")) {
			System.out.println("학과");
			sql =" SELECT A.rnum, A.subject_id,A.term_id ,A.major_name, A.subject_name," 
					+" A.pro_name, A.subject_room, A.subject_time,"
					+" A.subject_type, A.subject_credit, A.subject_limit, subject_grade,subject_count "  
					+" FROM "
					+" (SELECT ROWNUM AS rnum,B.subject_id,B.term_id ,B.major_name, B.subject_name, "
					+" B.pro_name, B.subject_room, "
					+" B.subject_time,B.subject_type, B.subject_credit, B.subject_limit, B.subject_grade,"
					+" B.subject_count " 
					+" FROM "
					+" (SELECT S.subject_id,S.term_id ,M.major_name, S.subject_name, P.pro_name, "
					+" S.subject_room, S.subject_time,"
					+" S.subject_type, S.subject_credit, S.subject_limit, S.subject_grade,"
					+" S.subject_count"   
					+" FROM subject S "
					+" JOIN major M "
					+" ON S.major_id = M.major_id " 
					+" JOIN pro P "
					+" ON S.pro_id = P.pro_id   "
					+" WHERE S.term_id "+ term_id + "AND M.major_name Like '%"+inpValue+"%'"
					+" ORDER BY term_id DESC) B "
					+" WHERE rownum <= ?) A " //endNum
					+" WHERE A.rnum >=? " ; //startNum

		}else {
			System.out.println("과목");
			sql =" SELECT A.rnum, A.subject_id,A.term_id ,A.major_name, A.subject_name," 
					+" A.pro_name, A.subject_room, A.subject_time,"
					+" A.subject_type, A.subject_credit, A.subject_limit, subject_grade,subject_count "  
					+" FROM "
					+" (SELECT ROWNUM AS rnum,B.subject_id,B.term_id ,B.major_name, B.subject_name, "
					+" B.pro_name, B.subject_room, "
					+" B.subject_time,B.subject_type, B.subject_credit, B.subject_limit, B.subject_grade,"
					+" B.subject_count " 
					+" FROM "
					+" (SELECT S.subject_id,S.term_id ,M.major_name, S.subject_name, P.pro_name, "
					+" S.subject_room, S.subject_time,"
					+" S.subject_type, S.subject_credit, S.subject_limit, S.subject_grade,"
					+" S.subject_count"   
					+" FROM subject S "
					+" JOIN major M "
					+" ON S.major_id = M.major_id " 
					+" JOIN pro P "
					+" ON S.pro_id = P.pro_id   "
					+" WHERE S.term_id "+ term_id + "AND S.subject_name Like '%"+inpValue+"%'"
					+" ORDER BY term_id DESC) B "
					+" WHERE rownum <= ?) A " //endNum
					+" WHERE A.rnum >=? " ; //startNum

		} 
		try {
			ps =conn.prepareStatement(sql);
			ps.setInt(1, endNum);
			ps.setInt(2, startNum);
			rs = ps.executeQuery();
			while(rs.next()) {
				System.out.println("반복문 실행!");
				DTO dto =new DTO();
				dto.setSubject_id(rs.getInt("subject_id"));
				dto.setTerm_id(rs.getString("term_id"));
				dto.setMajor_name(rs.getString("major_name"));
				dto.setSubject_name(rs.getString("subject_name"));
				dto.setPro_name(rs.getString("pro_name"));
				dto.setSubject_room(rs.getString("subject_room"));
				dto.setSubject_time(rs.getString("subject_time"));
				dto.setSubject_type(rs.getString("subject_type"));
				dto.setSubject_credit(rs.getInt("subject_credit"));
				dto.setSubject_limit(rs.getInt("subject_limit"));
				dto.setSubject_grade(rs.getDouble("subject_grade"));
				dto.setSubject_count(rs.getInt("subject_count"));
				list.add(dto);
			}
		} catch (SQLException e) {
			System.out.println("전체과목 검색 에러");
			e.printStackTrace();
			return null;
		}finally {
			resClose();
		}
		return list;
	}
	/*로그인 학생의 수강 신청 과목 조회*/
	public ArrayList<DTO> stdEnroll(String loginId, String term_id) {
		System.out.println("stdEnroll DAO");
		ArrayList<DTO> list = new ArrayList<DTO>();
		String sql = " SELECT S.subject_id,term_id,M.major_name, subject_name, P.pro_name, "
				+" subject_room,subject_time,subject_type, subject_credit, subject_limit,subject_grade,subject_count "
				+" FROM enroll E  JOIN subject S ON E.subject_id = S.subject_id "
				+" JOIN major M ON S.major_id = M.major_id "
				+" JOIN pro P ON S.pro_id = P.pro_id "
				+" WHERE E.std_id = ? AND S.term_id= ? ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, loginId);
			ps.setString(2, term_id);
			System.out.println("실행 전");
			rs = ps.executeQuery();
			System.out.println("실행 후");
			while(rs.next()) {
				System.out.println("과목 조회 반복!!");
				DTO dto = new DTO();
				dto.setSubject_id(rs.getInt("subject_id"));
				dto.setTerm_id(rs.getString("term_id"));
				dto.setMajor_name(rs.getString("major_name"));
				dto.setSubject_name(rs.getString("subject_name"));
				dto.setPro_name(rs.getString("pro_name"));
				dto.setSubject_room(rs.getString("subject_room"));
				dto.setSubject_time(rs.getString("subject_time"));
				dto.setSubject_type(rs.getString("subject_type"));
				dto.setSubject_credit(rs.getInt("subject_credit"));
				dto.setSubject_limit(rs.getInt("subject_limit"));
				dto.setSubject_grade(rs.getDouble("subject_grade"));
				dto.setSubject_count(rs.getInt("subject_count"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("과목 조회 에러");
			return null;
		}finally {
			System.out.println("과목 조회 자원반납");
			resClose();
		}
		return list;
	}


	/*로그인 학생의 수강 과목 학점 조회*/
	public int stdCredit(String loginId, String term_id) {
		System.out.println("stdCredit DAO");
		int resultCredit =0;
		String sql = " SELECT C.subject_credit FROM"
				+ " std S,enroll E,subject C "  
				+" WHERE S.std_id = E.std_id AND E.subject_id = C.subject_id" 
				+" AND S.std_id =? AND C.term_id =? "; 
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, loginId);
			System.out.println(loginId);
			ps.setString(2, term_id);
			System.out.println(term_id);
			rs =ps.executeQuery();
			while(rs.next()) {
				//학점 더하기
				resultCredit +=rs.getInt("subject_credit");
				System.out.println("resultCredit : "+resultCredit);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(" 학점 조회 에러");
		}finally {
			//자원 반납
			resClose();
		}
		return resultCredit;
	}

	/*로그인 학생의 수강 신청  --최대학점 | 과목 , 강의시간 중복 여부에 따른 쿼리 실행 */
	public int[] enroll(String loginId, String[] tdValue, int stdCredit, ArrayList<Object> currentSub, ArrayList<Object> currentTime, int count) {
		System.out.println("enroll DAO");
		System.out.println(tdValue.length);

		/*result.add(index 순서)*/
		int[] result = new int[5]; //각각의 성공 여부 변수를 담는 배열  --실패를 의미하는 0 을 초기화
		result[0]  =0;  //쿼리 성공 여부 반환 변수 ---index[0]
		result[1]  =0;  //최대학점 기준 성공 여부 반환 변수---index[1]
		result[2]  =0;  //중복된 과목이 있는지 구분하여 반환하는  변수 -index[2]
		result[3]  =0; //중복된 강의시간이  있는지 구분하여 반환하는  변수 ---index[3]
		result[4]  =0; //수강 신청 가능인원에 속한지 구분하여 반환하는 변수 ----index[4]
		String InsertSql = " INSERT INTO enroll(enroll_id,std_id,subject_id) "
				+ " VALUES(seq_enroll_id.NEXTVAL,?,?)";
		System.out.println(loginId+"님의 현재 학점은 ? :"+stdCredit);
		System.out.println(currentSub);
		System.out.println(currentTime);
		System.out.println(" 과목 id "+tdValue[0]);
		System.out.println("강의 시간  "+tdValue[6]);
		try {
			//tdValue[12] : 최대 20학점   --20학점 이상 수강 신청 불가!
			if(stdCredit< Integer.parseInt(tdValue[12])) {
				result[1] =1;
				//tdValue[0] : 수강신청 버튼 클릭시 수강신청 될  과목 id
				if((!currentSub.contains(Integer.parseInt(tdValue[0])))) {
					System.out.println("과목이 중복되지 않아 수강신청 가능합니다.");
					result[2] =1;
					//tdValue[6] : 수강신청 버튼 클릭시 수강신청 될  강의시간
					if(!(currentTime.contains(tdValue[6]))) {
						System.out.println("강의시간이 중복되지 않아 수강신청 가능합니다.");
						result[3] =1;
						//tdValue[10] : 수강 가능 인원 
						System.out.println("dao쪽 :::::::"+count);
						if(count > 0) {
							//수강 신청 완료(1) 시 [수강신청 가능인원] 컬럼에   -1 
							System.out.println("수강 신청 가능인원 -1");
							String UpdateSql =" UPDATE subject SET subject_count = subject_count-1 "
									+" WHERE subject_id =? " ;
							//tdValue[0] :  과목 id
							ps =conn.prepareStatement(UpdateSql);
							ps.setString(1, tdValue[0]);
							result[4]  =ps.executeUpdate();					     
							//4가지 모든 조건 완료 ! ---> 1 반환
							/////////////수강신청/////////////////////////
							ps = conn.prepareStatement(InsertSql);
							ps.setString(1, loginId);
							ps.setString(2, tdValue[0]);
							result[0]=	ps.executeUpdate(); //성공 여부 1 반환
						}else {
							System.out.println("수강 신청 인원 마감");
						}
					}else {
						System.out.print("중복된 강의시간이 있습니다");
					}
				}else {
					System.out.print("중복된 과목 id");
				}
			}else{
				System.out.print("20학점 초과");
			}

			System.out.println(result);
		} catch (SQLException e) {
			System.out.println(" 수강신청 에러");
			e.printStackTrace();
		}finally {
			//자원 반납
			System.out.println("수강 신청 자원 반납");
			resClose();
		}
		System.out.println("리턴 : "+result);
		return result;
	}
	/*로그인 학생의 수강 정정 */
	public int enrollChange(String loginId, String subject_id) {
		System.out.println("enrollChange DAO");
		int success =0;
		//수강 신청 완료(1) 시 [수강신청 가능인원] 컬럼에   -1 
		System.out.println("수강 신청 가능인원 -1");


		try {
			String Deletesql = " DELETE FROM enroll WHERE  std_id=? AND subject_id =?  " ;
			ps = conn.prepareStatement(Deletesql);
			ps.setString(1, loginId);
			ps.setString(2,subject_id);
			success =ps.executeUpdate();
			if(success>0) {
				String UpdateSql =" UPDATE subject SET subject_count = subject_count+1 "
						+" WHERE subject_id =? " ;
				ps =conn.prepareStatement(UpdateSql);
				ps.setString(1, subject_id);
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println("수강 정정 에러");
			e.printStackTrace();
		}finally {
			resClose();
		}
		return success;
	}
	/*중복여부 판별하기위한 현재과목 || 강의시간 조회  */
	public ArrayList<Object> overLapDAO(String loginId, String string, int flag) {
		ArrayList<Object> current = new ArrayList<Object>();
		String sql ="";
		if(flag ==1) {
			sql =  " SELECT S.subject_id "
					+" FROM enroll E  JOIN subject S ON E.subject_id = S.subject_id "
					+" JOIN major M ON S.major_id = M.major_id "
					+" JOIN pro P ON S.pro_id = P.pro_id "
					+" WHERE E.std_id = ? AND S.term_id= ? ";
		}else{
			sql =  " SELECT S.subject_time "
					+" FROM enroll E  JOIN subject S ON E.subject_id = S.subject_id "
					+" JOIN major M ON S.major_id = M.major_id "
					+" JOIN pro P ON S.pro_id = P.pro_id "
					+" WHERE E.std_id = ? AND S.term_id= ? ";
		}
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, loginId); //학생 id
			ps.setString(2, string); // 학기 
			rs = ps.executeQuery();
			while(rs.next()) {
				System.out.println("반복");
				if(flag ==1) {
					current.add(rs.getInt("subject_id"));
				}else if(flag==2) {
					current.add(rs.getString("subject_time"));
				}else {
					current.add(rs.getInt("subject_count"));
				}
			}
		} catch (SQLException e) {
			System.out.println("중복 여부 에러");
			e.printStackTrace();
		}finally {
			resClose();
		}
		return current ;
	}



	/*자원 반납*/
	private void resClose() {
		try {
			if(rs !=null) {
				//executeQuery 만 쓰이기 때문에 항상 rs.close() 해줄 필요 없음. 사용시에만 닫아줌
				rs.close();
			}
			if(ps !=null) {
				//수강 신청 하기 전에 중복 여부에 따라 sql 쿼리를 실행 안하는 경우가 있으므로 
				//사용 안할 경우에는 close 해줄 필요가 없음  --conn도 같다
				ps.close();
			}
			if(conn !=null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/*수강 신청 시 제한 인원 조회*/
	public int count(String string) {
		int resultCount =0;
		String sql = " SELECT subject_count FROM subject"
				+ " WHERE subject_id =?" ;
		try {
			ps =conn.prepareCall(sql);
			ps.setString(1, string);
			rs =ps.executeQuery();
			if(rs.next()) {
				resultCount = rs.getInt("subject_count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return resultCount;
	}

}
