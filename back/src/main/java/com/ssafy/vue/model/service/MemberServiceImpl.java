package com.ssafy.vue.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.model.MemberDto;
import com.ssafy.vue.model.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public MemberDto login(MemberDto memberDto) throws Exception {
		if(memberDto.getUserid() == null || memberDto.getUserpwd() == null)
			return null;
		return sqlSession.getMapper(MemberMapper.class).login(memberDto);
	}

	@Override
	public MemberDto userInfo(String userid) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).userInfo(userid);
	}
	@Override
	public void joinMember(MemberDto memberDto) throws Exception {
		sqlSession.getMapper(MemberMapper.class).joinMember(memberDto);
		return;
	}
	@Override
	public List<MemberDto> listMember() throws Exception {
		return sqlSession.getMapper(MemberMapper.class).listMember();
	}

	@Override
	public MemberDto getMember(String userId) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).getMember(userId);
	}

	@Override
	public void updateMember(MemberDto memberDto) throws Exception {
		sqlSession.getMapper(MemberMapper.class).updateMember(memberDto);
	}

	@Override
	public void deleteMember(String userId) throws Exception {
		sqlSession.getMapper(MemberMapper.class).deleteMember(userId);
	}
	
	@Override
	public List<MemberDto> getMemberByUserid(String userid) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).getMemberByUserid(userid);
	}

	@Override
	public List<MemberDto> getMemberByUsername(String username) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).getMemberByUsername(username);
	}

	@Override
	public List<MemberDto> getMemberByEmail(String email) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).getMemberByEmail(email);
	}

	@Override
	public MemberDto checkMember(MemberDto memberDto) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).checkMember(memberDto);
	}
}
