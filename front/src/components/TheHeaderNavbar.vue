<template>
	<div>
		<b-navbar toggleable="lg">
			<b-navbar-brand href="#">
				<router-link :to="{ name: 'main' }">
					<b-img :src="require('@/assets/logo.png')" id="logo" class="d-inline-block align-top" alt="logo"></b-img>
				</router-link>
			</b-navbar-brand>

			<b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

			<b-collapse id="nav-collapse " is-nav>
				<b-navbar-nav class="iconRow">
					<b-nav-item href="#" class="justify-content-center">
						<router-link :to="{ name: 'main' }" class="m-1 p-2 link navBarIcon">
							<b-icon icon="house-door" variant="primary" animation="spin-reverse" font-scale="2"></b-icon>
							메인
						</router-link>
						<span @click="goInsta" class="m-1 p-2 link navBarIcon">
							<b-img
								thumbnail
								fluid
								rounded
								:src="$store.state.randomImgUrl"
								style="width: 50px; height: 50px; border-radius: 20px"
							/>
							<!-- <b-icon :src="$store.state.randomImgUrl" variant="primary" animation="spin" font-scale="2"></b-icon> -->
							인스타그램
						</span>
						<router-link :to="{ name: 'board' }" class="m-1 p-2 link navBarIcon">
							<b-icon icon="journal" variant="primary" animation="spin-reverse" font-scale="2"></b-icon>
							게시판
						</router-link>
						<router-link :to="{ name: 'house' }" class="m-1 p-2 link navBarIcon">
							<b-icon icon="house-fill" variant="primary" animation="fade" font-scale="2"></b-icon>
							아파트정보
						</router-link>
						<!-- <router-link :to="{ name: 'todo' }" class="link m-1 p-2 navBarIcon">
							<b-icon icon="calendar-check" animation="fade" font-scale="2"></b-icon>
							TodoList
						</router-link> -->
					</b-nav-item>
				</b-navbar-nav>

				<!-- Right aligned nav items -->
				<b-navbar-nav class="ml-auto">
					<b-nav-item v-if="userInfo.userid" style="margin-top: 10px; margin-right: 10px">
						{{ userInfo.userid }}님, 반갑습니다!
					</b-nav-item>
					<b-nav-item-dropdown right class="navBarIcon" variant="primary">
						<template #button-content>
							<b-icon variant="primary" icon="people" font-scale="2"></b-icon>
						</template>
						<b-dropdown-item href="#">
							<router-link :to="{ name: 'join' }" class="link">
								<b-icon icon="person-circle"></b-icon> 회원가입
							</router-link>
						</b-dropdown-item>

						<b-dropdown-item href="#" v-if="!userInfo.userid">
							<router-link :to="{ name: 'login' }" class="link"> <b-icon icon="key"></b-icon> 로그인 </router-link>
						</b-dropdown-item>

						<b-dropdown-item href="#" class="link" @click="logout" v-else>
							<b-icon icon="key"></b-icon> 로그아웃
						</b-dropdown-item>

						<b-dropdown-item href="#">
							<router-link :to="{ name: 'mypage', params: { userId: userInfo.userid } }" class="link"
								><b-icon variant="primary" icon="people-fill"></b-icon> 마이페이지</router-link
							>
						</b-dropdown-item>

						<b-dropdown-item href="#" class="link" @click="userManager" v-if="userInfo.userid == 'admin'">
							<b-icon icon="key"></b-icon> 유저 관리
						</b-dropdown-item>
					</b-nav-item-dropdown>
				</b-navbar-nav>
			</b-collapse>
		</b-navbar>
	</div>
</template>

<script>
	import { mapActions } from "vuex";
	import { mapState } from "vuex";
	export default {
		created() {
			this.getRandomImgUrl();
		},
		name: "TheHeaderNavbar",
		computed: {
			...mapState("userStore", ["userInfo"]),
		},
		data() {
			return {
				home_bg: require("@/assets/bg2.jpg"),
				variant: "light",
				opacity: 0.3,
				blur: "2px",
				variants: [
					"transparent",
					"white",
					"light",
					"dark",
					"primary",
					"secondary",
					"success",
					"danger",
					"warning",
					"info",
				],
				blurs: [{ text: "None", value: "" }, "1px", "2px", "5px", "0.5em", "1rem"],
			};
		},
		methods: {
			...mapActions(["getRandomImgUrl"]),
			logout() {
				// console.log("logout");
				this.$store.dispatch("userStore/logout");
				if (this.$route.path !== "/") this.$router.push({ name: "main" });
			},
			userManager() {
				this.$router.push({ name: "userList" });
			},
			goInsta() {
				window.open("https://www.instagram.com/");
			},
		},
	};
</script>

<style scoped>
	#logo {
		width: 120px;
	}

	.link {
		text-decoration: none;
		/* color:#FFFFFF; */
	}
	.iconRow {
		position: absolute;
		left: 50%;
		transform: translateX(-50%);
	}
</style>
