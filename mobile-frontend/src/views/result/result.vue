<template>
  <div class="app-container">
    <div class="box-div">
      <div class="main-contant">
        <div class="imgs">
          <img src="@/assets/image/success.png" alt="提交成功" class="imgs">
        </div>
        <div class="tips">
          <span>提交成功！</span>
        </div>
        <div class="text">
          <p>提交报名信息后，请及时完成转账，以便工作人员进行核对。发票请于参会当日凭付款凭证或工作证联系现场工作人员领取。</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
    }
  },
  computed: {
  },
  methods: {
  },
  created() {
    // 判断是否从首页跳转 否则返回首页
    const check = this.$route.params.home;
    if (!check) {
      this.$router.push({ name: 'Home' })
    }
  },
}
</script>

<style scoped>
.box-div {
  width: 100%;
  padding: 20px;
}
.main-contant {
  padding: 15px;
}
.imgs {
  text-align: center;
  margin: 80px 0 0;
}
.imgs > img {
  width: 95px;
  height: 95px;
  margin: 0;
}
.tips {
  text-align: center;
  font-size: 18px;
  font-weight: 700;
  color: #339966;
  margin: 30px 0;
}
</style>
