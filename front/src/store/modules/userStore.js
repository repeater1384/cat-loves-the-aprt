import http from "@/api/http";
import jwt_decode from "jwt-decode";

const userStore = {
	namespaced: true,
	state: () => ({
		userInfo: {}, //로그인한 유저 정보
	}),
	getters: {},
	mutations: {
		SET_USER_INFO(state, payload) {
			state.userInfo = payload;
		},
		SET_TOKEN(state, payload) {
			state.token = payload;
		},
	},
	actions: {
		async login({ commit }, loginInfo) {
			console.log(commit);
			let { data } = await http.post("/user/login", loginInfo);
			console.log(data);
			let token = data["access-token"];
			console.log("발급받은 토큰", token);
			commit("SET_TOKEN", { token });
			//axios 요청시 header에 access-token 추가

			let decodedToken = jwt_decode(token);
			console.log("토큰 정보 : ", decodedToken);

			commit("SET_USER_INFO", { userid: decodedToken.userid });
		},
		logout({ commit }) {
			// delete http.defaults.headers.common["access-token"];

			commit("SET_TOKEN", { token: null });

			//vuex store의 유저정보 비우기
			commit("SET_USER_INFO", {});
		},
	},
};

export default userStore;
