package com.ryu.app;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface InterfaceMemberDAO {
	List<MemberDTO> selectAll(MemberDTO mDTO);
	MemberDTO selectOne(Map<String, String> map);
	boolean insert(Map<String, String> map);
	boolean update(Map<String, String> map);
	boolean delete(@Param("MID")String mid);
}
