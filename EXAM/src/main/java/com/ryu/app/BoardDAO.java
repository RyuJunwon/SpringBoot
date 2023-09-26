package com.ryu.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final String SELECTALL="SELECT * FROM BOARD ORDER BY BID DESC";
	private final String SELECTONE="SELECT * FROM BOARD WHERE BID=?";
	private final String INSERT="INSERT INTO BOARD VALUES((SELECT NVL(MAX(BID),100)+1 FROM BOARD),?,?)";
	private final String UPDATE="UPDATE BOARD SET CONTENT=? WHERE BID=?";
	private final String DELETE="DELETE FROM BOARD WHERE BID=?";
	
	public List<BoardDTO> selectAll(BoardDTO bDTO) {
		return jdbcTemplate.query(SELECTALL, new BeanPropertyRowMapper<BoardDTO>(BoardDTO.class));
	}
	public BoardDTO selectOne(BoardDTO bDTO) throws Exception {
		Object[] args= { bDTO.getBid() };
		return jdbcTemplate.queryForObject(SELECTONE, args, new BeanPropertyRowMapper<BoardDTO>(BoardDTO.class));
	}
	public boolean insert(BoardDTO bDTO) {
		int result=jdbcTemplate.update(INSERT, bDTO.getMid(), bDTO.getContent());
		return result>0 ? true : false;
	}
	public boolean update(BoardDTO bDTO) {
		int result=jdbcTemplate.update(UPDATE, bDTO.getContent(), bDTO.getBid());
		return result>0 ? true : false;
	}
	public boolean delete(BoardDTO bDTO) {
		int result=jdbcTemplate.update(DELETE, bDTO.getBid());
		return result>0 ? true : false;
	}

}
