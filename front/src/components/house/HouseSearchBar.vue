<template>
	<b-row class="mt-4 mb-4 text-center">
		<!-- <b-col class="sm-3">
      <b-form-input
        v-model.trim="dongCode"
        placeholder="동코드 입력...(예 : 11110)"
        @keypress.enter="sendKeyword"
      ></b-form-input>
    </b-col>
    <b-col class="sm-3" align="left">
      <b-button variant="outline-primary" @click="sendKeyword">검색</b-button>
    </b-col> -->

		<b-col class="sm-3">
			<b-form-select v-model="sidoCode" :options="sidos" @change="gugunList"></b-form-select>
		</b-col>
		<b-col class="sm-3">
			<b-form-select v-model="gugunCode" :options="guguns" @change="searchApt"></b-form-select>
		</b-col>
		<b-col class="sm-3">
			<b-form-input v-model.lazy="searchDate" placeholder="거래년월일( YYYYMM )" @change="searchApt"></b-form-input>
		</b-col>
	</b-row>
</template>

<script>
	import { mapState, mapActions, mapMutations } from "vuex";

	export default {
		name: "HouseSearchBar",
		data() {
			return {
				sidoCode: null,
				gugunCode: null,
				searchDate: 201901,
			};
		},
		computed: {
			...mapState(["sidos", "guguns", "houses"]),
			// sidos() {
			//   return this.$store.state.sidos;
			// },
		},
		created() {
			// this.$store.dispatch("getSido");
			// this.sidoList();
			this.CLEAR_SIDO_LIST();
			this.CLEAR_GUGUN_LIST();
			this.CLEAR_APT_LIST();
			this.getSido();
			console.log("created");
			console.log(process.env);
			console.log(process.env.BACK_END_URL);
			console.log(process.env.VUE_APP_KAKAOMAP_KEY);
		},
		methods: {
			...mapActions(["getSido", "getGugun", "getHouseList", "getRandomImgUrl"]),
			...mapMutations(["CLEAR_SIDO_LIST", "CLEAR_GUGUN_LIST", "CLEAR_APT_LIST"]),
			// sidoList() {
			//   this.getSido();
			// },
			gugunList() {
				// console.log(this.sidoCode);
				this.CLEAR_GUGUN_LIST();
				this.CLEAR_APT_LIST();
				this.gugunCode = null;
				if (this.sidoCode) this.getGugun(this.sidoCode);
			},
			searchApt() {
				this.CLEAR_APT_LIST();
				const param = { gugunCode: this.gugunCode, searchDate: this.searchDate };
				if (this.gugunCode && this.searchDate) {
					this.getHouseList(param);
					this.getRandomImgUrl();
				}
			},
		},
	};
</script>

<style></style>
