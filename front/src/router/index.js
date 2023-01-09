import Vue from "vue";
import VueRouter from "vue-router";
import AppMain from "@/views/AppMain";
// import AppInstagram from "@/views/AppInstagram";
import AppHouse from "@/views/AppHouse";
import store from "@/store";

Vue.use(VueRouter);

const routes = [
	{
		path: "/",
		name: "main",
		component: AppMain,
	},
	// {
	// 	path: "/instagram",
	// 	name: "insta",
	// 	component: AppInstagram,
	// },
	{
		path: "/house",
		name: "house",
		component: AppHouse,
	},
	{
		path: "/todo",
		name: "todo",
		component: () => import("@/views/AppTodo"),
	},
	{
		path: "/admin",
		name: "admin",
		component: () => import("@/views/AppAdmin"),
		children: [
			{
				path: "list",
				name: "userList",
				component: () => import("@/components/user/UserList"),
			},
		],
	},
	{
		path: "/user",
		name: "user",
		component: () => import("@/views/AppUser"),
		children: [
			{
				path: "join",
				name: "join",
				component: () => import("@/components/user/UserRegister"),
			},
			{
				path: "login",
				name: "login",
				component: () => import("@/components/user/UserLogin"),
			},
			{
				path: "mypage",
				name: "mypage",
				component: () => import("@/components/user/UserMypage"),
				props: true,
			},
			{
				path: "init-pass",
				name: "init-pass",
				component: () => import("@/components/user/UserInitPass"),
				props: true,
			},
		],
	},
	{
		path: "/board",
		name: "board",
		component: () => import("@/views/AppBoard"),
		redirect: "/board/list",
		children: [
			{
				path: "list",
				name: "boardlist",
				component: () => import("@/components/board/BoardList"),
			},
			{
				path: "write",
				name: "boardwrite",
				component: () => import("@/components/board/BoardWrite"),
			},
			{
				path: "view/:articleno",
				name: "boardview",
				component: () => import("@/components/board/BoardView"),
			},
			{
				path: "modify",
				name: "boardmodify",
				component: () => import("@/components/board/BoardModify"),
			},
			{
				path: "delete/:articleno",
				name: "boarddelete",
				component: () => import("@/components/board/BoardDelete"),
			},
		],
	},
];

const router = new VueRouter({
	mode: "history",
	base: process.env.BASE_URL,
	routes,
});
router.beforeEach((/*이동하려는 라우트 정보 */ to, /*가려는 라우트 정보*/ from, next) => {
	//라우팅 시 해당 라우트가 책관련 라우트이고
	//유저정보를 갖고 있지 않다면
	// console.log("beforeEach");
	// console.log(store.state.userStore);
	// console.log(store.state.userStore.userInfo);
	// console.log(store.state.userStore.userInfo.userid);
	if (to.path.includes("board") && !store.state.userStore.userInfo.userid) {
		alert("로그인이 필요한 서비스입니다.");
		return false;
	}

	//해당 라우트로 이동
	next();
});
export default router;
