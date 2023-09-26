package com.ryu.app;

import java.util.List;

public interface InterfaceMemberDAO {
	
	List<MemberDTO> selectAll(MemberDTO mDTO);
	MemberDTO selectOne(MemberDTO mDTO);
	boolean insert(MemberDTO mDTO);
	boolean update(MemberDTO mDTO);
	boolean delete(MemberDTO mDTO);
}
