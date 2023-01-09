import Vue from "vue";
import Vuex from "vuex";
import http from "@/api/http";
import axios from "axios";
import createPersistedState from "vuex-persistedstate";
import boardStore from "@/store/modules/boardStore.js";
import userStore from "@/store/modules/userStore.js";
Vue.use(Vuex);

export default new Vuex.Store({
	state: {
		sidos: [{ value: null, text: "선택하세요" }],
		guguns: [{ value: null, text: "선택하세요" }],
		houses: [],
		testtest: [1, 2, 3, 4, 5],
		house: null,
		randomImgUrl:null,
		todos: [
			// { title: '할 일1', completed: false },
			// { title: '할 일1', completed: false },
			// { title: '할 일2', completed: false },
			// { title: '할 일2', completed: false },
		],
	},
	getters: {
		allTodosCount(state) {
			return state.todos.length;
		},
		completedTodosCount(state) {
			return state.todos.filter((todo) => {
				return todo.completed === true;
			}).length;
		},
		unCompletedTodosCount(state) {
			return state.todos.filter((todo) => {
				return todo.completed === false;
			}).length;
		},
	},
	mutations: {
		/////////////////////////////// House start /////////////////////////////////////
		SET_SIDO_LIST(state, sidos) {
			sidos.forEach((sido) => {
				state.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
			});
		},
		SET_GUGUN_LIST(state, guguns) {
			guguns.forEach((gugun) => {
				state.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
			});
		},
		CLEAR_SIDO_LIST(state) {
			state.sidos = [{ value: null, text: "선택하세요" }];
		},
		CLEAR_APT_LIST(state) {
			state.houses = [];
			state.house = null;
		},
		CLEAR_GUGUN_LIST(state) {
			state.guguns = [{ value: null, text: "선택하세요" }];
		},
		SET_HOUSE_LIST(state, houses) {
			state.houses = houses;
		},
		SET_DETAIL_HOUSE(state, house) {
			// console.log("Mutations", house);
			state.house = house;
		},
		SET_RANDOM_IMG_URL(state, url) {
			state.randomImgUrl = url;
		},
		/////////////////////////////// House end /////////////////////////////////////

		//////////////////////////// Todo List start //////////////////////////////////
		CREATE_TODO(state, todoItem) {
			// console.log('mutation CREATE_TODO Call');
			// console.log(state);
			// console.log(todoItem);
			state.todos.push(todoItem);
		},
		DELETE_TODO(state, todoItem) {
			const index = state.todos.indexOf(todoItem);
			state.todos.splice(index, 1);
		},
		UPDATE_TODO_STATUS(state, todoItem) {
			// console.log(state);
			// console.log(todoItem);
			state.todos = state.todos.map((todo) => {
				if (todo === todoItem) {
					return {
						// title: todoItem.title,
						...todo,
						completed: !todoItem.completed,
					};
				}
				return todo;
			});
		},
		//////////////////////////// Todo List end //////////////////////////////////
	},
	actions: {
		/////////////////////////////// House start /////////////////////////////////////
		getSido({ commit }) {
			http
				.get(`/map/sido`)
				.then(({ data }) => {
					console.log(data);
					commit("SET_SIDO_LIST", data);
				})
				.catch((error) => {
					console.log(error);
				});
		},
		getGugun({ commit }, sidoCode) {
			const params = { sido: sidoCode };
			http
				.get(`/map/gugun`, { params })
				.then(({ data }) => {
					commit("SET_GUGUN_LIST", data);
				})
				.catch((error) => {
					console.log(error);
				});
		},
		getHouseList({ commit }, { gugunCode, searchDate }) {
			http.get(`/housedeal/${ gugunCode }/${ searchDate }`).then(({ data }) => {
				console.log("gugunCode22", gugunCode);
				commit("SET_HOUSE_LIST", data);
			});
		},
		getRandomImgUrl({ commit }) {
			console.log("!!!!!!!!!!!!!GETRANDOMIMGURL")
			axios.get("https://api.thecatapi.com/v1/images/search").then(({ data }) => {
				commit("SET_RANDOM_IMG_URL", data[0].url);
			}
			);
		},
		detailHouse({ commit }, house) {
			console.log("아파트이름", house.apartmentName);
			// axios.get("https://api.thecatapi.com/v1/images/search").then(({ data }) => {
			// 	console.log(commit);
			// 	house.imageUrl = data[0].url;
			// 	commit("SET_DETAIL_HOUSE", house);
			// }
			// );
			http
				.get(`/map/image?aptName=${house.apartmentName}`)
				.then(({ data }) => {
					console.log(data);
					house.imageUrl = data.imageUrl;
					commit("SET_DETAIL_HOUSE", house);
				})
				.catch((error) => {
					console.log(error);
				});
			
		},
		/////////////////////////////// House end /////////////////////////////////////

		//////////////////////////// Todo List start //////////////////////////////////
		// createTodo(context, todoItem) {
		//   console.log(context);
		//   console.log('actions createTodo Call');
		//   context.commit('CREATE_TODO', todoItem);
		// },
		// destructuring 활용
		createTodo({ commit }, todoItem) {
			commit("CREATE_TODO", todoItem);
		},
		deleteTodo({ commit }, todoItem) {
			// console.log(context);
			// console.log(todoItem);
			commit("DELETE_TODO", todoItem);
		},
		updateTodoStatus({ commit }, todoItem) {
			commit("UPDATE_TODO_STATUS", todoItem);
		},
		//////////////////////////// Todo List end //////////////////////////////////
	},
	plugins: [
		createPersistedState({
			// 브라우저 종료시 제거하기 위해 localStorage가 아닌 sessionStorage로 변경. (default: localStorage)
			storage: sessionStorage,
		}),
	],
	modules: { boardStore, userStore },
});
