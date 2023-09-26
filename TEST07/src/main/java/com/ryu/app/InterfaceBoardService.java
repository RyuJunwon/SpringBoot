package com.ryu.app;

import java.util.List;

public interface InterfaceBoardService {

	List<BoardDTO> selectAll(BoardDTO bDTO);
	BoardDTO selectOne(BoardDTO bDTO);
	boolean insert(BoardDTO bDTO);
	boolean update(BoardDTO bDTO);
	boolean delete(BoardDTO bDTO);
}
