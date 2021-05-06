<template>
	<div class="manage_page fillcontain">
		<el-row style="height: 100%;">
	  		<el-col :span="3"  style="min-height: 100%; background-color: #324057;">
				<el-menu :default-active="defaultActive" style="min-height: 100%;" theme="dark" router>

					<el-menu-item index="manage"><i class="el-icon-menu"></i>报名管理</el-menu-item>
					<el-menu-item index="foodList"><i></i>报名信息</el-menu-item>
					<!-- <el-menu-item index="adminList"><i class="el-icon-plus"></i>上传文件</el-menu-item> -->

				</el-menu>
			</el-col>
			<el-col :span="21" style="height: 100%;overflow: auto;">
				<keep-alive>
				    <router-view></router-view>
				</keep-alive>
			</el-col>
		</el-row>
  	</div>
</template>

<script>
    export default {
		data(){
            return {
                // defaultActive:"manage",
            }
        },
		computed: {
			defaultActive: function(){
				return this.$route.path.replace('/', '');
			}
		},
    }
</script>


<style lang="less" scoped>
	@import '../style/mixin';
	.manage_page{
		
	}
</style>
