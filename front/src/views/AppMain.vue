<template>
	<b-container class="bv-example-row mt-3 text-center">
		<h3 class="underline-steelblue"><b-icon icon="house"></b-icon> SSAFY</h3>
		<img id="header-bg" style="width: 100%" :src="require('@/assets/home-bg.jpg')" />
	</b-container>
</template>

<script>
	export default {
		name: "AppMain",
	};
</script>

<style scoped>
	.underline-steelblue {
		/* display: inline-block; */
		/* background: linear-gradient(180deg, rgba(255, 255, 255, 0) 70%, rgba(255, 0, 0, 0.3) 30%); */
		color: blue;
	}

	#header-bg {
		position: absolute;
		top: 0%;
		left: 0;
		width: 100%;
		height: 100%;
		z-index: -1;
		/* filter: brightness(50%); */
		/* clip: rect(0px, 5000px, 900px, 0px); */
	}
</style>
