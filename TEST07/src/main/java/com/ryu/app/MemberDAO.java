package com.ryu.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


public class MemberDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate; // 의존관계(멤버변수)
	
	// 응집도를 높여줌
	private final String SELECTALL="SELECT * FROM MEMBER";
	private final String SELECTONE="SELECT * FROM MEMBER WHERE MID=? AND MPW=?";
	private final String INSERT="INSERT INTO MEMBER VALUES(?,?)";
	private final String UPDATE="UPDATE MEMBER SET MPW=? WHERE MID=?";
	private final String DELETE="DELETE FROM MEMBER WHERE MID=?";
	
	public List<MemberDTO> selectAll(MemberDTO mDTO) {
		// 반복적인 로직을 대신 수행하는 "템플릿 패턴"을 활용
		return jdbcTemplate.query(SELECTALL, new BeanPropertyRowMapper<MemberDTO>(MemberDTO.class));
	}
	public MemberDTO selectOne(MemberDTO mDTO) { // 예외처리 미루기
		Object[] args= { mDTO.getMid(), mDTO.getMpw() };
		return jdbcTemplate.queryForObject(SELECTONE, args, new BeanPropertyRowMapper<MemberDTO>(MemberDTO.class));
	}
	public boolean insert(MemberDTO mDTO) {
		int result=jdbcTemplate.update(INSERT, mDTO.getMid(), mDTO.getMpw());
		return result>0 ? true : false;
	}
	public boolean update(MemberDTO mDTO) {
		int result=jdbcTemplate.update(UPDATE, mDTO.getMpw(), mDTO.getMid());
		return result>0 ? true : false;
	}
	public boolean delete(MemberDTO mDTO) {
		int result=jdbcTemplate.update(DELETE, mDTO.getMid());
		return result>0 ? true : false;
	}

}
