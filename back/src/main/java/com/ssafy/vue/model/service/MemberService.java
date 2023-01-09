package com.ssafy.vue.model.service;

import java.util.List;

import com.ssafy.vue.model.MemberDto;

public interface MemberService {

	public MemberDto login(MemberDto memberDto) throws Exception;
	public MemberDto userInfo(String userid) throws Exception;

	void joinMember(MemberDto memberDto) throws Exception;
	/* Admin */
	List<MemberDto> listMember() throws Exception;
	MemberDto getMember(String userId) throws Exception;
	void updateMember(MemberDto memberDto) throws Exception;
	void deleteMember(String userid) throws Exception;
	List<MemberDto> getMemberByUserid(String userid) throws Exception;
	List<MemberDto> getMemberByUsername(String username) throws Exception;
	List<MemberDto> getMemberByEmail(String email) throws Exception;
	MemberDto checkMember(MemberDto memberDto) throws Exception;
}
