<template>
  	<div class="login_page fillcontain">

		<div class="version">
			<span>当前版本：V1.0.0</span>
			<br>
			<span>©版权信息归国地信息中心所有 2020</span>
		</div>

	  	<transition name="form-fade" mode="in-out">
	  		<section class="form_contianer" v-show="showLogin">
		  		<div class="manage_tip">
		  			<!-- <p>细胞数据校验工具</p> -->
		  		</div>
		    	
				<div class="loginForm">
					<el-form :model="loginForm" :rules="rules" ref="loginForm">
					<el-form-item prop="username">
						<el-input v-model="loginForm.username" placeholder="请输入用户名"><span>dsfsf</span></el-input>
					</el-form-item>
					<el-form-item prop="password">
						<el-input type="password" placeholder="请输入密码" @keyup.enter.native="submitForm('loginForm')" v-model="loginForm.password"></el-input>
					</el-form-item>
					<el-form-item>
				    	<el-button type="primary" @click="submitForm('loginForm')" class="submit_btn">登录</el-button>
				  	</el-form-item>
				</el-form>
				</div>
				<!-- <br>
				<div class="tipWrap">
					<p class="tip">温馨提示：</p>
					<p class="tip">1.上传文件的模板必须正确</p>
					<p class="tip">2.建议把excel表中的数据整理成文本类型</p>
					<p class="tip">3.使用本工具过程中出现问题请联系管理员</p>
				</div> -->
	  		</section>
	  	</transition>
  	</div>
</template>

<script>
	// import {mapActions, mapState} from 'vuex'
	export default {
	    data(){
			return {
				loginForm: {
					username: '',
					password: '',
				},
				rules: {
					username: [
			            { required: true, message: '请输入用户名', trigger: 'blur' },
			        ],
					password: [
						{ required: true, message: '请输入密码', trigger: 'blur' }
					],
				},
				showLogin: false,
			}
		},
		mounted(){
			this.showLogin = true;
			// if (!this.adminInfo.id) {
    		// 	this.getAdminData()
    		// }
		},
		computed: {
			// ...mapState(['adminInfo']),
		},
		methods: {
			// ...mapActions(['getAdminData']),
			async submitForm(formName) {
				this.$refs[formName].validate(async (valid) => {
					if (valid) {
						this.$api.post("/login/in",this.loginForm,
						res =>{
								if (res.status == 200) {
									localStorage.setItem("user",1)
									this.$router.push('foodList')
									this.$message({
										type: 'success',
										message: '登录成功'
									});
								}else{
									this.$message({
										type: 'error',
										message: res.msg
									});
								}
						})

					} else {
						this.$notify.error({
							title: '错误',
							message: '请输入正确的用户名密码',
							offset: 100
						});
						return false;
					}
				});
			},
		},
		watch: {
			adminInfo: function (newValue){
				if (newValue.id) {
					this.$message({
                        type: 'success',
                        message: '检测到您之前登录过，将自动登录'
                    });
					this.$router.push('manage')
				}
			}
		}
	}
</script>

<style lang="less" scoped>
	@import '../style/mixin';
	.login_page{
		background-color: #324057;
	}
	.manage_tip{
		width: 100%;
		margin-top: 15px;
		p{
			font-size: 34px;
			font-weight: 600;
			color: #5f5f5f;
		}
	}
	.loginForm{
		margin-top: 50px;
	}
	.form_contianer{
		.wh(320px, 250px);
		.ctp(380px, 550px);
		padding: 30px;
		border-radius: 5px;
		border: 1px solid #gray;
		text-align: center;
		background-color: #fff;
		.submit_btn{
			width: 100%;
			font-size: 16px;
		}
	}
	.tip{
		font-size: 13px;
		color: #868686;
	}
	.tipWrap{
		text-align: left;
		line-height: 22px;
	}
	.form-fade-enter-active, .form-fade-leave-active {
	  	transition: all 1s;
	}
	.form-fade-enter, .form-fade-leave-active {
	  	transform: translate3d(0, -50px, 0);
	  	opacity: 0;
	}
	.version {
		position: absolute;
		bottom: 20px;
		color: #949494;
		text-align: center;
		width: 100%;
		font-size: 12px;
	}
</style>
