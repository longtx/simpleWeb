<template>
    <div class="header_container">

		<el-breadcrumb separator="/">
			<el-breadcrumb-item :to="{ path: '/fooList' }"></el-breadcrumb-item>
			<el-breadcrumb-item v-for="(item, index) in $route.meta" :key="index">{{item}}</el-breadcrumb-item>
		</el-breadcrumb>


		
		<div>
			<span style="font-weight:650;font-color:gray;font-size:14px;cursor: pointer;">Hi,{{username}}</span>
			&nbsp;
			<span style="font-weight:650;font-color:gray;font-size:14px;margin-right:25px;" class="logout" @click="logout">退出</span>
		</div>
    </div>
</template>

<script>
	import {baseImgPath} from '@/config/env'
	// import {mapActions, mapState} from 'vuex'

    export default {
    	data(){
    		return {
				baseImgPath,
				username:"超级管理员"
    		}
    	},
    	created(){
			var user = localStorage.getItem("user")
			if(user != null && user != undefined){
				this.username  = JSON.parse(user).username;
			}

			setInterval(()=>{
				var user = localStorage.getItem("user")
				if(user == null && user == undefined){
					this.$router.push('/');
				}
			},500);

    	},
    	computed: {
    		// ...mapState(['adminInfo']),
    	},
		methods: {
			logout(){
				localStorage.removeItem("user")
				this.$router.push('/');
			},
			// ...mapActions(['getAdminData']),
			
			async handleCommand(command) {
				if (command == 'home') {
					this.$router.push('/manage');
				}else if(command == 'signout'){
					const res = await signout()
					if (res.status == 1) {
						this.$message({
	                        type: 'success',
	                        message: '退出成功'
	                    });
	                    this.$router.push('/');
					}else{
						this.$message({
	                        type: 'error',
	                        message: res.message
	                    });
					}
				}
			},
		}
    }
</script>

<style lang="less">
	@import '../style/mixin';
	.header_container{
		background-color: #EFF2F7;
		height: 60px;
		display: flex;
		justify-content: space-between;
		align-items: center;
		padding-left: 20px;
	}
	.avator{
		.wh(36px, 36px);
		border-radius: 50%;
	}
	.el-dropdown-menu__item{
        text-align: center;
	}
	.logout{
		cursor:pointer;
	}
	.logout:hover{
		text-decoration:underline;
		color: red;
	}


</style>
