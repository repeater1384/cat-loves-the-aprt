<template>
	<b-container v-if="house" class="bv-example-row">
		<b-row>
			<b-col
				><h3>{{ house.apartmentName }}</h3></b-col
			>
		</b-row>
		<b-row class="mb-2 mt-1">
			<house-image :aptName="house.apartmentName"></house-image>
			<!-- <b-col><b-img :src="house.imageUrl" fluid-grow></b-img></b-col> -->
			<!-- <b-col><b-img :src="require('@/assets/apt.png')" fluid-grow></b-img></b-col> -->
		</b-row>
		<b-row>
			<b-col>
				<b-alert show variant="secondary">일련번호 : {{ house.apartmentCode }}</b-alert>
			</b-col>
		</b-row>
		<b-row>
			<b-col>
				<b-alert show variant="primary">아파트 이름 : {{ house.apartmentName }} </b-alert>
			</b-col>
		</b-row>
		<b-row>
			<b-col>
				<b-alert show variant="info">법정동 : {{ house.dong }} </b-alert>
			</b-col>
		</b-row>
		<b-row>
			<b-col>
				<b-alert show variant="warning">층수 : {{ house.floor }}층</b-alert>
			</b-col>
		</b-row>
		<b-row>
			<b-col>
				<b-alert show variant="danger"
					>거래금액 : {{ (parseInt(house.amount.replace(",", "")) * 10000) | price }}원</b-alert
				>
			</b-col>
		</b-row>
	</b-container>
</template>

<script>
	import { mapState } from "vuex";
	import HouseImage from "@/components/house/HouseDetailImage.vue"
	
export default {
	components:
	{
		HouseImage
	}
	
	,
		name: "HouseDetail",
		computed: {
			...mapState(["house","randomImgUrl"]),
			// house() {
			//   return this.$store.state.house;
			// },
		},
		filters: {
			price(value) {
				if (!value) return value;
				return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
			},
		},
	};
</script>

<style></style>
