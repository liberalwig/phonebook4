package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PersonVo;

@Repository
public class PhoneDao {

	@Autowired
	private SqlSession sqlSession;

	// 전체 리스트 가져오기
	public List<PersonVo> getPersonList() {
		System.out.println("PhoneDao.getPersonList()");

		List<PersonVo> personList = sqlSession.selectList("phonebook.selectList");

		return personList;
	}

	// 전화번호 추가
	public int personInsert2(String name, String hp, String company) {
		System.out.println("PhoneDao.personInsert() 파라미터 여러가지로 받을 때 ");

		Map<String, Object> personMap = new HashMap<String, Object>();

		personMap.put("name", name);
		personMap.put("hp", hp);
		personMap.put("company", company);
		int count = sqlSession.insert("phonebook.insert2", personMap);
		System.out.println(count + "건 저장");
		return 0;
	}

	// 전화번호 추가
	public int personInsert(PersonVo personVo) {
		System.out.println("PhoneDao.personInsert()");

		int count = sqlSession.insert("phonebook.insert", personVo);
		System.out.println(count + "건 저장");

		return count;
	}

	// 전화번호 삭제
	public int personDelete(int personId) {
		System.out.println("PersonDao.personDelete()");

		return sqlSession.delete("phonebook.delete", personId);
	}

	// 전화번호 수정하기 위해 하나 가져오기

	public PersonVo getPerson(int personId) {
		System.out.println("PersonDao.getPerson()");

		PersonVo personVo = sqlSession.selectOne("phonebook.selectOne", personId);
		System.out.println("personVo");

		return personVo;
	}

	// Map전화번호 수정하기 위해 하나 가져오기
//	public PersonVo getPerson(int personId) {
//		System.out.println("PersonDao.getPerson2()");
//
//		Map<String, String>personMap = sqlSession.selectOne("phonebook.selectPerson2", personId);
//		personMap.get("PERSON_ID");
//		personMap.get("NAME");
//		personMap.get("HP");
//		personMap.get("COMPANY");
//		System.out.println("personVo");
//	
//		return ;
//	}

	// 수정한 전화번호 업데이트
	public int personUpdate(PersonVo personVo) {
		System.out.println("PersonDao.personUpdate");

		return sqlSession.update("phonebook.update", personVo);
	}

}
/*
 * // 사람 추가 public int personInsert(PersonVo personVo) { int count = 0;
 * getConnection();
 * 
 * try {
 * 
 * // 3. SQL문 준비 / 바인딩 / 실행 String query = ""; // 쿼리문 문자열만들기, ? 주의 query +=
 * " INSERT INTO person "; query += " VALUES (seq_person_id.nextval, ?, ?, ?) ";
 * // System.out.println(query);
 * 
 * pstmt = conn.prepareStatement(query); // 쿼리로 만들기
 * 
 * pstmt.setString(1, personVo.getName()); // ?(물음표) 중 1번째, 순서중요
 * pstmt.setString(2, personVo.getHp()); // ?(물음표) 중 2번째, 순서중요
 * pstmt.setString(3, personVo.getCompany()); // ?(물음표) 중 3번째, 순서중요
 * 
 * count = pstmt.executeUpdate(); // 쿼리문 실행
 * 
 * // 4.결과처리 // System.out.println("[" + count + "건 추가되었습니다.]");
 * 
 * } catch (SQLException e) { System.out.println("error:" + e); } close();
 * return count; }
 * 
 * // 사람 리스트(검색안할때) public List<PersonVo> getPersonList() { return
 * getPersonList(""); }
 * 
 * // 사람 리스트(검색할때) public List<PersonVo> getPersonList(String keyword) {
 * List<PersonVo> personList = new ArrayList<PersonVo>();
 * 
 * getConnection();
 * 
 * try {
 * 
 * // 3. SQL문 준비 / 바인딩 / 실행 --> 완성된 sql문을 가져와서 작성할것 String query = ""; query +=
 * " select  person_id, "; query += "         name, "; query += "         hp, ";
 * query += "         company "; query += " from person";
 * 
 * if (keyword != "" || keyword == null) { query += " where name like ? "; query
 * += " or hp like  ? "; query += " or company like ? "; pstmt =
 * conn.prepareStatement(query); // 쿼리로 만들기
 * 
 * pstmt.setString(1, '%' + keyword + '%'); // ?(물음표) 중 1번째, 순서중요
 * pstmt.setString(2, '%' + keyword + '%'); // ?(물음표) 중 2번째, 순서중요
 * pstmt.setString(3, '%' + keyword + '%'); // ?(물음표) 중 3번째, 순서중요 } else { pstmt
 * = conn.prepareStatement(query); // 쿼리로 만들기 }
 * 
 * rs = pstmt.executeQuery();
 * 
 * // 4.결과처리 while (rs.next()) { int personId = rs.getInt("person_id"); String
 * name = rs.getString("name"); String hp = rs.getString("hp"); String company =
 * rs.getString("company");
 * 
 * PersonVo personVo = new PersonVo(personId, name, hp, company);
 * personList.add(personVo); }
 * 
 * } catch (SQLException e) { System.out.println("error:" + e); }
 * 
 * close();
 * 
 * return personList;
 * 
 * }
 * 
 * // 사람 1명정보만 가져올때 public PersonVo getPerson(int personId) { PersonVo personVo
 * = null;
 * 
 * getConnection();
 * 
 * try {
 * 
 * // 3. SQL문 준비 / 바인딩 / 실행 --> 완성된 sql문을 가져와서 작성할것 String query = ""; query +=
 * " select  person_id, "; query += "         name, "; query += "         hp, ";
 * query += "         company "; query += " from person "; query +=
 * " where person_id = ? ";
 * 
 * pstmt = conn.prepareStatement(query); // 쿼리로 만들기
 * 
 * pstmt.setInt(1, personId); // ?(물음표) 중 1번째, 순서중요
 * 
 * rs = pstmt.executeQuery();
 * 
 * // 4.결과처리 rs.next(); int id = rs.getInt("person_id"); String name =
 * rs.getString("name"); String hp = rs.getString("hp"); String company =
 * rs.getString("company");
 * 
 * personVo = new PersonVo(id, name, hp, company);
 * 
 * } catch (SQLException e) { System.out.println("error:" + e); }
 * 
 * close();
 * 
 * return personVo;
 * 
 * }
 * 
 * // 사람 수정 public int personUpdate(PersonVo personVo) { int count = 0;
 * getConnection();
 * 
 * try {
 * 
 * // 3. SQL문 준비 / 바인딩 / 실행 String query = ""; // 쿼리문 문자열만들기, ? 주의 query +=
 * " update person "; query += " set name = ? , "; query += "     hp = ? , ";
 * query += "     company = ? "; query += " where person_id = ? ";
 * 
 * pstmt = conn.prepareStatement(query); // 쿼리로 만들기
 * 
 * pstmt.setString(1, personVo.getName()); // ?(물음표) 중 1번째, 순서중요
 * pstmt.setString(2, personVo.getHp()); // ?(물음표) 중 2번째, 순서중요
 * pstmt.setString(3, personVo.getCompany()); // ?(물음표) 중 3번째, 순서중요
 * pstmt.setInt(4, personVo.getPersonId()); // ?(물음표) 중 4번째, 순서중요
 * 
 * count = pstmt.executeUpdate(); // 쿼리문 실행
 * 
 * // 4.결과처리 // System.out.println(count + "건 수정되었습니다.");
 * 
 * } catch (SQLException e) { System.out.println("error:" + e); }
 * 
 * close(); return count; }
 * 
 * // 사람 삭제 public int personDelete(int personId) { int count = 0;
 * getConnection();
 * 
 * try { // 3. SQL문 준비 / 바인딩 / 실행 String query = ""; // 쿼리문 문자열만들기, ? 주의 query
 * += " delete from person "; query += " where person_id = ? "; pstmt =
 * conn.prepareStatement(query); // 쿼리로 만들기
 * 
 * pstmt.setInt(1, personId);// ?(물음표) 중 1번째, 순서중요
 * 
 * count = pstmt.executeUpdate(); // 쿼리문 실행
 * 
 * // 4.결과처리 // System.out.println(count + "건 삭제되었습니다.");
 * 
 * } catch (SQLException e) { System.out.println("error:" + e); }
 * 
 * close(); return count; }
 */
