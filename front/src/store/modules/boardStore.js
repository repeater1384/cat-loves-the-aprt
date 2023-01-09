import axios from "@/api/http";
import router from "@/router";
const bookStore = {
	namespaced: true,
	state: () => ({
		books: [], //책 목록데이터
		book: {}, //책 상세정보 데이터
	}),
	getters: {},
	mutations: {
		SET_BOOKS(state, payload) {
			state.books = payload;
		},
		SET_BOOK(state, payload) {
			state.book = payload;
		},
	},
	actions: {
		//서버로부터 책목록 데이터 요청
		//context - actions에서 써야하는 store의 몇몇 속성들과 그 외 다른 속성들을 모아놓은 객체
		async setBooks(context) {
			const { data } = await axios.get("/books");
			console.log(data);
			if (data == "not Login") {
				alert("로그인 해주세요.");
				router.push("/");
				return;
			}
			//mutations를 통해 books 세팅
			//context.commit(메소드명, 전달할데이터)
			context.commit("SET_BOOKS", data);
		},
		//서버에 책 상세정보 요청
		async setBook(context, isbn) {
			const { data } = await axios.get(`/books/${isbn}`);
			console.log(data);
			if (data == "not Login") {
				alert("로그인 해주세요.");
				router.push("/");
				return;
			}
			//mutation를 통해 book 세팅
			context.commit("SET_BOOK", data);
		},
	},
};

export default bookStore;
