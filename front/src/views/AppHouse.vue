<template>
	<b-container class="bv-example-row mt-3 text-center">
		<h1 ><b-icon variant="primary" icon="house-fill"></b-icon> </h1>
		<!-- <b-row>
			<b-col>
				<b-img style="width: 70%; padding: 10px" alt="Happy House" :src="require('@/assets/house.jpg')" />
			</b-col>
		</b-row> -->
		<b-row>
			
		</b-row>
		<b-row>
			<b-col>
				<house-search-bar></house-search-bar>
			</b-col>
		</b-row>

		<b-row>
			<b-col cols="4" align="left">
				<house-list />
			</b-col>
			<b-col cols="4">
				<house-detail />
			</b-col>
			<b-col cols="4">
				<house-map />
			</b-col>
		</b-row>
		
	</b-container>
</template>
<script>
	import HouseSearchBar from "@/components/house/HouseSearchBar.vue";
	import HouseList from "@/components/house/HouseList.vue";
	import HouseDetail from "@/components/house/HouseDetail.vue";
	import HouseMap from "@/components/house/HouseMap.vue";

	export default {
		name: "AppHouse",
		components: {
			HouseSearchBar,
			HouseList,
			HouseDetail,
			HouseMap,
		},
	};
</script>
<style scoped>
	.underline-orange {
		display: inline-block;
		background: linear-gradient(180deg, rgba(255, 255, 255, 0) 70%, rgba(231, 149, 27, 0.3) 30%);
	}
</style>
