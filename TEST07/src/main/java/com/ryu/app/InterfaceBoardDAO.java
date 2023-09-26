package com.ryu.app;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface InterfaceBoardDAO {
	List<BoardDTO> selectAll(BoardDTO bDTO);
	BoardDTO selectOne(int bid); // #{param1} 방식
	boolean insert(Map<String, String> map);
	boolean update(Map<String, String> map);
	boolean delete(@Param("BID")int bid); // #{BID} 방식
}
