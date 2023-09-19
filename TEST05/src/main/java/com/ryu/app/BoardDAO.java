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

	private final String SQL_SELECTALL = "SELECT * FROM BOARD";
	private final String SQL_SELECTONE = "SELECT * FROM BOARD WHERE BID = ?";
	private final String SQL_INSERT = "INSERT INTO BOARD (MID, CONTENT) VALUES (?, ?)";
	private final String SQL_UPDATE = "UPDATE BOARD SET CONTENT = ? WHERE BID = ?";
	private final String SQL_DELETE = "DELETE FROM BOARD WHERE BID = ?";


	public List<BoardDTO> selectAll(BoardDTO bDTO) {
		return jdbcTemplate.query(SQL_SELECTALL, new BeanPropertyRowMapper<BoardDTO>(BoardDTO.class));
	}

	public BoardDTO selectOne(BoardDTO bDTO) {
		Object[] args = { bDTO.getBid() };
		return jdbcTemplate.queryForObject(SQL_SELECTONE, new BeanPropertyRowMapper<BoardDTO>(BoardDTO.class), args);
	}

	public boolean insert(BoardDTO bDTO) {

		int result = jdbcTemplate.update(SQL_INSERT, bDTO.getMid(), bDTO.getContent());

		if(result <= 0) {
			return false;
		}

		return true;
	}

	public boolean update(BoardDTO bDTO) {

		int result = jdbcTemplate.update(SQL_UPDATE, bDTO.getContent(), bDTO.getBid());

		if(result <= 0) {
			return false;
		}
		return true;
	}

	public boolean delete(BoardDTO bDTO) {

		int result = jdbcTemplate.update(SQL_DELETE, bDTO.getBid());

		if(result <= 0) {
			return false;
		}
		return true;
	}

}
