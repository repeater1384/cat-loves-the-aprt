package com.ssafy.vue.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.model.MemberDto;

@Mapper
public interface MemberMapper {

	public MemberDto login(MemberDto memberDto) throws SQLException;
	void joinMember(MemberDto memberDto) throws SQLException;
	public MemberDto userInfo(String userid) throws SQLException;

	/* Admin */
	List<MemberDto> listMember() throws SQLException;
	MemberDto getMember(String userid) throws SQLException;
	void updateMember(MemberDto memberDto) throws SQLException;
	void deleteMember(String userid) throws SQLException;
	List<MemberDto> getMemberByUserid(String userid) throws SQLException;
	List<MemberDto> getMemberByUsername(String username) throws SQLException;
	List<MemberDto> getMemberByEmail(String email) throws SQLException;
	MemberDto checkMember(MemberDto memberDto) throws SQLException;
}
