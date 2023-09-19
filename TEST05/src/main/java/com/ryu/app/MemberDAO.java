package com.ryu.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;// 의존 관계 (멤버 변수)

	// 상단에 SQL문을 작성함으로써 응집도를 높임
	// 또한 final 키워드를 사용하여 상수화된 것들은 대문자로도 사용함
	private final String SQL_SELECTALL = "SELECT * FROM MEMBER";
	private final String SQL_SELECTONE = "SELECT * FROM MEMBER WHERE MID = ? AND MPW = ?"; 
	private final String SQL_SELECTONE_MEMBER = "SELECT * FROM MEMBER WHERE MID = ?";
	private final String SQL_INSERT = "INSERT INTO MEMBER (MID, MPW) VALUES (?, ?)";
	private final String SQL_UPDATE = "UPDATE MEMBER SET MPW = ? WHERE MID = ?";
	private final String SQL_DELETE = "DELETE FROM MEMBER WHERE MID = ?";

	public List<MemberDTO> selectAll(MemberDTO mDTO) {
		// 반복적인 로직을 대신 수행하는 "템플릿 패턴"을 활용 (jdbcTemplate)
		return jdbcTemplate.query(SQL_SELECTALL, new BeanPropertyRowMapper<MemberDTO>(MemberDTO.class)); // 롬복이 제공 해주는 RowMapper
	}

	public MemberDTO selectOne(MemberDTO mDTO) {
		try {
			if(mDTO.getSearchCondition().equals("중복확인")) {
				Object[] args = { mDTO.getMid() };
				return jdbcTemplate.queryForObject(SQL_SELECTONE_MEMBER, new BeanPropertyRowMapper<MemberDTO>(MemberDTO.class), args);	
			}
			else {
				Object[] args = { mDTO.getMid(), mDTO.getMpw() };
				return jdbcTemplate.queryForObject(SQL_SELECTONE, new BeanPropertyRowMapper<MemberDTO>(MemberDTO.class), args);
			}
		} catch(EmptyResultDataAccessException e) {
			return null;
		}
	}

	public boolean insert(MemberDTO mDTO) {

		int result = jdbcTemplate.update(SQL_INSERT, mDTO.getMid(), mDTO.getMpw());

		if(result <= 0) {
			return false;
		}
		return true; 
	}

	public boolean update(MemberDTO mDTO) {

		int result = jdbcTemplate.update(SQL_UPDATE, mDTO.getMpw(), mDTO.getMid());

		if(result <= 0) {
			return false;
		}
		return true;
	}

	public boolean delete(MemberDTO mDTO) {

		int result = jdbcTemplate.update(SQL_DELETE, mDTO.getMid());

		if(result <= 0) {
			return false;
		}
		return true;
	}
}
