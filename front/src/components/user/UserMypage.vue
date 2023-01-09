<template>
	<b-container class="mt-3">
		<b-row text-center>
			<b-col>
				<b-alert variant="secondary" show><h3>마이페이지</h3></b-alert>
			</b-col>
		</b-row>

		<b-row>
			<b-container class="text-left">
				<div class="row justify-content-center">
					<div class="col-lg-10 col-md-10 col-sm-12">
						<b-form>
							<b-form-group label="이름: ">
								<b-form-input class="form-control" type="text" v-model="userInfo.username" />
							</b-form-group>
							<b-form-group label="아이디: " label-for="userid">
								<b-form-input class="form-control" id="userid" type="text" v-model="userInfo.userid" readonly />
							</b-form-group>
							<b-form-group label="이메일: " label-for="email">
								<b-form-input class="form-control" id="email" type="email" v-model="userInfo.email" />
							</b-form-group>

							<b-form-group label="비밀번호: " label-for="userpwd" description="비밀번호변경을 원하시면 입력하세요">
								<b-form-input
									class="form-control"
									id="userPwd"
									type="password"
									v-model="changePwd"
									placeholder="비밀번호..."
								/>
							</b-form-group>
							<b-form-group
								label="비밀번호 확인: "
								label-for="userPasswordConfirm"
								description="비밀번호를 다시 입력하세요"
							>
								<b-form-input
									class="form-control"
									type="password"
									v-model="changePwd2"
									placeholder="비밀번호 확인..."
								/>
							</b-form-group>

							<div class="col-auto text-center">
								<b-button @click="modify" variant="primary" class="m-1">회원정보 수정</b-button>
								<b-button @click="initPwd" variant="danger" class="m-1" disabled>비밀번호 초기화</b-button>
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
		created() {
			http.get("/user/" + this.$route.params.userId).then((result) => {
				console.log(this.$route.params.userId, result.data);
				this.userInfo = result.data.userInfo;
			});
		},
		data() {
			return {
				userInfo: {},
				changePwd: "",
				changePwd2: "",
			};
		},
		methods: {
			modify() {
				console.log("modify");
				if (this.changePwd && this.changePwd === this.changePwd2) {
					this.userInfo.userpwd = this.changePwd;
				}
				http.put("/admin/user", this.userInfo).then(this.goMain());
			},
			goMain() {
				this.$router.push({ name: "main" });
			},
			
		},
	};
</script>

<style></style>
