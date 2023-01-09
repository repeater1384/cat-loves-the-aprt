<template>
  <div class="user-wrap">
    <div class="user-image">
        <img :src="require('@/assets/empty_apart.png')"/>
    </div>
    <div class="user-text"  :class="aptName.length>5?'long-text':'short-text'" >
        <p>{{aptName|process}}</p>
    </div>
</div>
</template>

<script>
export default {
    name: "HouseImage",
    props: {
        aptName: null,
    },
    data() {
        return {
            img: "imgText", 
        }
    },
    filters: {
        process: (value) => value.split('(')[0]
    }
}
</script>

<style scoped>
.user-wrap {
    width: 100%;
    margin: 10px auto;
    position: relative;
}

.user-wrap img {
    width: 100%;
    vertical-align: middle;
}

.user-text {
    position: absolute;
    top: 40%;
    left: -40%;
    width: 100%;
    /* transform: translate(-50%, -50%); */
    font-family: 'ypseo';
    text-align: center;
    writing-mode: vertical-rl;
    text-orientation: upright;
}

.long-text{
    font-size:15px;
}
.short-text{
    font-size:25px;
}
</style>