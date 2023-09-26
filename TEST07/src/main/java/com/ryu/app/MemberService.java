package com.ryu.app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class MemberService implements InterfaceMemberService {
	
	@Autowired
	private InterfaceMemberDAO mDAO;

	@Override
	public List<MemberDTO> selectAll(MemberDTO mDTO) {
		return mDAO.selectAll(mDTO);
	}

	@Override
	public MemberDTO selectOne(MemberDTO mDTO) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("MID", mDTO.getMid());
		map.put("MPW", mDTO.getMpw());
		return mDAO.selectOne(map);
	}

	@Override
	public boolean insert(MemberDTO mDTO) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("MID", mDTO.getMid());
		map.put("MPW", mDTO.getMpw());
		return mDAO.insert(map);
	}

	@Override
	public boolean update(MemberDTO mDTO) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("MPW", mDTO.getMpw());
		map.put("MID", mDTO.getMid());
		return mDAO.update(map);
	}

	@Override
	public boolean delete(MemberDTO mDTO) {
		return mDAO.delete(mDTO.getMid());
	}

}
