<template>
	<b-container v-if="users && users.length != 0" class="bv-example-row">
		<div id="searchbar">
			<b-button class="float-right ml-2" variant="primary" @click="searchUser">검색</b-button>
			<input type="text" v-model="search" id="searchbarInput" class="float-right m-1" />
			<select v-model="selected" class="float-right m-2">
				<option>사용자명</option>
				<option>아이디</option>
				<option>이메일</option>
			</select>
		</div>
		<b-table-simple hover responsive>
			<tbody>
				<b-tr>
					<b-th class="text-center p-2 bg-light" style="width: 15%">사용자명</b-th>
					<b-th class="text-center p-2 bg-light" style="width: 15%">아이디</b-th>
					<b-th class="text-center p-2 bg-light" style="width: 15%">비밀번호</b-th>
					<b-th class="text-center p-2 bg-light" style="width: 15%">이메일</b-th>
					<b-th class="text-center p-2 bg-light" style="width: 6%"> </b-th>
					<b-th class="text-center p-2 bg-light" style="width: 6%"> </b-th>
				</b-tr>
				<user-list-item
					v-for="user in users"
					:key="user.userid"
					v-bind="user"
					@userModify="userListModify"
					@userDelete="userListDelete"
				/>
			</tbody>
		</b-table-simple>

		<!-- <tbody></tbody>
		<b-row class="mt-4 mb-4 text-center">
			<b-col> </b-col>
		</b-row> -->
	</b-container>
	<b-container v-else class="bv-example mt-3">
		<b-row>
			<b-col><b-alert show>사용자 목록이 없습니다.</b-alert></b-col>
		</b-row>
	</b-container>
</template>

<script>
	import UserListItem from "@/components/user/UserListItem";
	// import { mapState } from "vuex";
	import http from "@/api/http";
	export default {
		name: "UserList",
		components: {
			UserListItem,
		},
		data() {
			return {
				users: [],
				search: [],
				selected: "",
			};
		},
		created() {
			http.get("/admin/user").then(({ data }) => {
				this.users = data;
			});
		},
		methods: {
			userListModify(userid, username, userpwd, email) {
				this.users.forEach((user) => {
					if (user.userid == userid) {
						user.username = username;
						user.userpwd = userpwd;
						user.email = email;
					}
				});
			},
			userListDelete(userid) {
				this.users = this.users.filter((user) => {
					return user.userid != userid;
				});
			},
			searchUser() {
				console.log(this.selected);
				if (this.selected == "") {
					alert("검색 조건을 선택해주세요.");
					return;
				}
				http
					.get(`/admin/searchUser`, {
						params: {
							selected: this.selected,
							search: this.search,
						},
					})
					.then(({ data }) => {
						console.log(data);
						if (data.length > 0) this.users = data;
						else alert("해당하는 결과가 없음");
					});
			},
		},
	};
</script>

<style>
	#searchbar {
		display: inline-block;
		width: 100%;
	}
	#searchbarInput {
		border-radius: 3px;
	}
</style>
