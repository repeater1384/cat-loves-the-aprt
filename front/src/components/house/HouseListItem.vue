<template>
	<b-row
		class="m-2"
		@click="selectHouse"
		@mouseover="colorChange(true)"
		@mouseout="colorChange(false)"
		:class="{ 'mouse-over-bgcolor': isColor }"
	>
		<b-col cols="4" class="text-center align-self-center">
			<b-img thumbnail :src="require(`@/assets/apt${idx%3+1}.png`)" ></b-img>
		</b-col>
		<b-col cols="8" class="align-self-center">[{{ idx + 1 }}] {{ house.apartmentName }} </b-col>
	</b-row>
</template>

<script>
import { mapActions } from "vuex";
import { mapState } from "vuex";

	export default {
		name: "HouseListItem",
		data() {
			return {
				isColor: false,
			};
		},
		props: {
		
			house:Object, idx: Number,
		
		},
		methods: {
			...mapActions(["detailHouse"]),
			selectHouse() {
				console.log("listRow : ", this.house);
				// this.$store.dispatch("getHouse", this.house);
				this.detailHouse(this.house);
			},
			colorChange(flag) {
				this.isColor = flag;
			},
	},
	computed: {
		...mapState(["randomImgUrl"]),
		}
	};
</script>

<style scoped>
	.apt {
		width: 50px;
	}
	.mouse-over-bgcolor {
		background-color: lightblue;
	}
</style>
