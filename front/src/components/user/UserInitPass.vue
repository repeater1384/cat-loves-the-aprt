<template>
	<b-container class="mt-3">
		<b-row text-center>
			<b-col>
				<b-alert variant="secondary" show><h3>비밀번호 찾기</h3></b-alert>
			</b-col>
		</b-row>
		<b-row>
			<b-container class="text-left">
				<div class="row justify-content-center">
					<div class="col-lg-10 col-md-10 col-sm-12">
						<b-form>
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
									placeholder="아이디..."
								/>
							</b-form-group>

							<div id="idcheck-result"></div>

							<b-form-group label="이메일: " label-for="email" :description="emailCheckResult">
								<b-form-input
									class="form-control"
									id="email"
									type="email"
									v-model.lazy="userInfo.email"
									placeholder="이메일..."
								/>
								
							</b-form-group>

							<div class="col-auto text-center">
								<b-button variant="danger" class="m-1" @click="valueCheck">비밀번호 초기화</b-button>
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
				userInfo: {
					username: "",
					userid: "",
					email: "",
				},
				emailDomais:['naver.com', 'google.com', 'daum.net'],
			};
	},
	computed: {
		emailCheckResult() {
			if (!this.userInfo.email)
				return '';
			if (this.userInfo.email.includes('@')) {
				const arr = this.userInfo.email.split('@');
				for (const domain of this.emailDomais) {
					if (arr[1].includes(domain))
						return '올바른 입력입니다';
				}
				return '도메인이 올바르지 않습니다';
			} else {
				return "@가 없습니다";
			}
		},
		},
		methods: {
			valueCheck() {
				console.log("valueCheck!", this.userInfo);
				http.post("/user/find-user", this.userInfo).then(
					({ data }) => {
						if (data == "FAIL") {
							alert("회원정보가 일치하지 않습니다 확인해주세요");
						} else {
							// 회원정보 일치. 
							// 1 비밀번호 초기화(메일발송)
							const initPwd = this.initPwd();
							// 2 백엔드 수정
							this.userInfo.userpwd = initPwd;
							http.put("/admin/user", this.userInfo).then(()=>console.log("수정성공"));
							//3 로그인화면으로
							this.$router.push({ name: 'login' });
						}
					}
				);
			},
			initPwd() {
				console.log("initPassword");

				let specialChars = ["!", "@", "#", "$", "%", "^", "&", "*"];
				let idx1 = Math.floor(Math.random() * specialChars.length);
				let idx2 = Math.floor(Math.random() * specialChars.length);
				// 랜덤 비밀번호 생성
				const initalizedPwd = Math.random().toString(36).substring(2, 10) + specialChars[idx1] + specialChars[idx2];
				console.log(initalizedPwd);
				console.log(this.userInfo.email);

				// 입력되어있는 메일로 보내기
				const param = {
					email: this.userInfo.email,
					initializePwd: initalizedPwd,
				};
				console.log(param);
				http.post("/user/init-pwd", param).then((result) => console.log(result));

				return initalizedPwd;
				
				// // 로그아웃 후 메인으로 보내기
				// this.goMain();
			},
		},
	};
</script>

<style></style>
