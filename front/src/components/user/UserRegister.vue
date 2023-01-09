<template>
	<b-container class="mt-3">
		<b-row text-center>
			<b-col>
				<b-alert variant="secondary" show><h3>회원가입</h3></b-alert>
			</b-col>
		</b-row>
		<b-row>
			<b-container class="text-left">
				<div class="row justify-content-center">
					<div class="col-lg-10 col-md-10 col-sm-12">
						<b-form @submit="onSubmit" @reset="onReset">
							<b-form-group label="이름: " label-for="username" description="이름을 입력하세요">
								<b-form-input
									class="form-control"
									id="username"
									type="text"
									v-model="userInfo.username"
									placeholder="이름..."
								/>
							</b-form-group>
							<b-form-group label="아이디: " label-for="userid" description="아이디를 입력하세요">
								<b-form-input
									class="form-control"
									id="userid"
									type="text"
									v-model="userInfo.userid"
									@keyup="idcheck"
									placeholder="아이디..."
								/>
							</b-form-group>

							<div id="idcheck-result"></div>

							
							<b-form-group label="비밀번호: " label-for="userpwd" description="비밀번호를 입력하세요">
								<b-form-input
									class="form-control"
									id="userPwd"
									type="password"
									v-model="userInfo.userpwd"
									placeholder="비밀번호..."
								/>
							</b-form-group>
							<b-form-group
								label="비밀번호 확인: "
								label-for="userPasswordConfirm"
								description="비밀번호를 다시 입력하세요"
							>
								<b-form-input class="form-control" v-model="pwdcheck" type="password" placeholder="비밀번호 확인..." />
							</b-form-group>

							<b-form-group label="이메일: " label-for="email" description="이메일을 입력하세요">
								<b-form-input
									class="form-control"
									id="email"
									type="email"
									v-model="userInfo.email"
									placeholder="이메일..."
								/>
							</b-form-group>

							<div class="col-auto text-center">
								<b-button type="submit" variant="primary" class="m-1">회원가입</b-button>
								<b-button type="reset" variant="danger" class="m-1">초기화</b-button>
							</div>
						</b-form>
					</div>
				</div>
			</b-container>
		</b-row>
	</b-container>
</template>

<script>
	import http from "@/api/http";
	export default {
		name: "UserRegister",
		data() {
			return {
				isUseId: false,
				userInfo: {
					username: "",
					userid: "",
					userpwd: "",
					email: "",
				},
				pwdcheck: "",
			};
		},
		// mounted() {
		// 	// let isUseId = false;
		// 	// document.querySelector("#userid").addEventListener("keyup", ()=> );
		// },
		methods: {
			idcheck() {
				console.log("idcheck");
				let userid = this.userInfo.userid;
				let resultDiv = document.querySelector("#idcheck-result");
				console.log;
				if (userid.length < 4 || userid.length > 16) {
					resultDiv.setAttribute("class", "mb-3 text-dark");
					resultDiv.textContent = "아이디는 4자 이상 16자 이하 입니다.";
					this.isUseId = false;
				} else {
					http.get("/user/" + userid).then((res) => {
						console.log(res);
						if (res.data.userInfo) {
							resultDiv.setAttribute("class", "mb-3 text-danger");
							resultDiv.textContent = "이미 사용중인 아이디 입니다.";
							this.isUseId = false;
						} else {
							resultDiv.setAttribute("class", "mb-3 text-success");
							resultDiv.textContent = "사용 가능한 아이디 입니다.";
							this.isUseId = true;
						}
					});
				}
			},
			onSubmit(event) {
				event.preventDefault();
				console.log("submit");
				if (!this.userInfo.username) {
					alert("이름 입력!!");
					return;
				} else if (!this.userInfo.userid) {
					alert("아이디 입력!!");
					return;
				} else if (!this.userInfo.userpwd) {
					alert("비밀번호 입력!!");
					return;
				} else if (this.userInfo.userpwd != this.pwdcheck) {
					alert("비밀번호 확인!!");
					return;
				} else if (this.isUseId == false) {
					alert("아이디 중복체크!!");
					return;
				}
				this.registUser();
			},
			onReset(event) {
				event.preventDefault();
				this.userInfo.username = "";
				this.userInfo.userid = "";
				this.userInfo.userpwd = "";
				this.userInfo.email = "";
				this.pwdcheck = "";
			},
			registUser() {
				console.log("registUser");
				console.log(this.userInfo);
				http.post(`/user/join`, this.userInfo).then(({ data }) => {
					let msg = "등록 처리시 문제가 발생했습니다.";
					if (data === "success") {
						msg = "등록이 완료되었습니다.";
					}
					alert(msg);
					this.$router.push({ name: "login" });
				});
			},
		},
	};
</script>

<style></style>
