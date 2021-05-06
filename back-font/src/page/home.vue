<template>
    <div>
        <head-top></head-top>
		<section class="data_section">
			<!-- <header class="section_title">数据统计</header> -->
			<!-- <img src="../assets/img/avator.jpg" alt="" srcset=""> -->
			<upload></upload>
		</section>
    </div>
</template>

<script>
	import headTop from '../components/headTop'
	import foodList from './foodList' 
    export default {
    	data(){
    		return {
    			
    		}
    	},
    	components: {
    		headTop,
			upload
    	},
    	mounted(){
    		
    	},
        computed: {

        },
    	methods: {
    	}
    }
</script>

<style lang="less">
	@import '../style/mixin';
	.data_section{
		padding: 20px;
		margin-bottom: 40px;
	}	
    .wan{
        .sc(16px, #333)
    }
</style>
