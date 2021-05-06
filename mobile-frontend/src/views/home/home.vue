<template>
  <div class="app-container">
    <div class="box-div">
      <div class="main-content">
        <div class="logo-img">
          <img src="@/assets/image/guodi_logo.png" alt="个体化细胞治疗技术国家地方联合工程实验室" class="home-logo">
        </div>
        <h3>填写报名信息</h3>
        <p>报名信息</p>
        <ul class="mui-table-view" style="margin-bottom: 15px;">
          <li class="mui-table-view-cell">
            <p>参会人姓名：</p>
          </li>
          <li class="mui-table-view-cell">
            <mt-field v-model.trim="basicForm.name" :disableClear="true" :attr="{ maxlength: 30 }"></mt-field>
          </li>
          <li class="mui-table-view-cell">
            <p>参会人手机号：</p>
          </li>
          <li class="mui-table-view-cell">
            <mt-field v-model.trim="basicForm.phone" :disableClear="true" @blur.native.capture="checkPhone(basicForm.phone)"></mt-field>
          </li>
          <li class="mui-table-view-cell">
            <p>参会人所在单位名称：</p>
          </li>
          <li class="mui-table-view-cell">
            <mt-field v-model.trim="basicForm.company" :disableClear="true" :attr="{ maxlength: 50 }"></mt-field>
          </li>
          <li class="mui-table-view-cell">
            <p>联系邮箱：</p>
          </li>
          <li class="mui-table-view-cell">
            <mt-field v-model.trim="basicForm.email" :disableClear="true" @blur.native.capture="checkEmail(basicForm.email)"></mt-field>
          </li>
        </ul>
        <p>发票信息（仅可提供“咨询服务”发票）</p>
        <ul class="mui-table-view">
          <li class="mui-table-view-cell">
            <mt-radio
              v-model="basicForm.receiptType"
              :options="invoicoType"
              @change="checkType(basicForm.receiptType)"
              class="mtRadio">
            </mt-radio>
          </li>
          <div v-if="special">
            <li class="mui-table-view-cell">
              <p>单位全称：</p>
            </li>
            <li class="mui-table-view-cell">
              <mt-field v-model.trim="specialInvoice.receiptName" :disableClear="true" :attr="{ maxlength: 50 }"></mt-field>
            </li>
            <li class="mui-table-view-cell">
              <p>纳税人识别号：</p>
            </li>
            <li class="mui-table-view-cell">
              <mt-field v-model.trim="specialInvoice.receiptNum" :disableClear="true"></mt-field>
            </li>
            <li class="mui-table-view-cell">
              <p>单位地址：</p>
            </li>
            <li class="mui-table-view-cell">
              <mt-field v-model.trim="specialInvoice.receiptAddress" :disableClear="true" :attr="{ maxlength: 100 }"></mt-field>
            </li>
            <li class="mui-table-view-cell">
              <p>单位电话：</p>
            </li>
            <li class="mui-table-view-cell">
              <mt-field v-model.trim="specialInvoice.receiptPhone" :disableClear="true"  :attr="{ maxlength: 100 }"></mt-field>
            </li>
            <li class="mui-table-view-cell">
              <p>银行开户账号：</p>
            </li>
            <li class="mui-table-view-cell">
              <mt-field v-model.trim="specialInvoice.receiptAccount" :disableClear="true"></mt-field>
            </li>
            <li class="mui-table-view-cell">
              <p>开户行：</p>
            </li>
            <li class="mui-table-view-cell">
              <mt-field v-model.trim="specialInvoice.receiptBank" :disableClear="true" :attr="{ maxlength: 30 }"></mt-field>
            </li>
          </div>

          <div v-if="common">
            <li class="mui-table-view-cell">
              <p>名称：</p>
            </li>
            <li class="mui-table-view-cell">
              <mt-field v-model.trim="plainInvoice.receiptName" :disableClear="true" :attr="{ maxlength: 50 }"></mt-field>
            </li>
            <li class="mui-table-view-cell">
              <p>纳税人识别号：</p>
            </li>
            <li class="mui-table-view-cell">
              <mt-field v-model.trim="plainInvoice.receiptNum" :disableClear="true"></mt-field>
            </li>
          </div>
        </ul>

        <ul class="mui-table-view">

          <li class="mui-table-view-cell">
            <span class="warm-hint">请正确填写报名信息，若填写有误，可能导致您无法正常参会。您的信息我们将严格保密，仅作本次报名所用。</span>
          </li>
          <li class="mui-table-view-cell">
            <mt-button type="primary" @click.native="submit()" size="large">提交</mt-button>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import { save, check } from '@/api/index';
import { MessageBox } from 'mint-ui';

export default {
  data() {
    return {
      // 报名信息
      basicForm: {
        name: '',
        phone: '',
        company: '',
        email: '',
        receiptType: '0',
      },
      // 专用发票信息
      specialInvoice: {
        receiptName: '',
        receiptNum: '',
        receiptAddress: '',
        receiptAccount: '',
        receiptBank: '',
        receiptPhone: ''
      },
      // 普通发票信息
      plainInvoice: {
        receiptName: '',
        receiptNum: '',
      },
      // 发票类型
      invoicoType: [{
        label: '无',
        value: '0'
      },{
        label: '增值税专用发票',
        value: '1'
      },{
        label: '增值税普通发票',
        value: '2'
      }],
      special: false, // 是否显示专用发票模块
      common: false, // 是否显示普通发票模块
      basicPhone: false, // 手机号码校验结果
      basicEmail: false, // 邮箱校验结果
    };
  },
  methods: {
    // 校验手机号码
    checkPhone(phone) {
      this.basicPhone = false
      const phoneRegex = /^1[0-9]{10}$/
      if (!phoneRegex.test(phone)) {
        MessageBox({
          title: '提示',
          message: '输入有误，请输入正确的手机号码',
        });
        this.basicPhone = true
      } else {
        check(phone).then(response => {
          const res = response.data;
          if (res.status == 400) {
            MessageBox({
              title: '提示',
              message: '此手机号码已提交过报名信息，不可再次提交'
            });
            this.basicPhone = true
          }
        })
      }
    },
    // 校验邮箱
    checkEmail(email) {
      this.basicEmail = false
      const emailRegex = /^[a-zA-Z0-9]+([-_.][a-zA-Z0-9]+)*@[a-zA-Z0-9]+([-_.][a-zA-Z0-9]+)*\.[a-z]{2,4}$/
      if (!emailRegex.test(email)) {
        MessageBox({
          title: '提示',
          message: '输入有误，请输入正确的邮箱',
        });
        this.basicEmail = true
      }
    },
    // 根据发票类型显示输入框
    checkType(type) {
      if (type == '1') {
        this.special = true;
        this.common = false;
      } else if (type == '2') {
        this.special = false;
        this.common = true;
      } else {
        this.special = false;
        this.common = false;
      }
    },
    submit() {
      check(this.basicForm.phone).then(response => { // 再次校验手机号码是否注册
        const res = response.data;
        if (res.status == 400) {
          MessageBox({
            title: '提示',
            message: '此手机号码已提交过报名信息，不可再次提交',
          });
        } else {
          this.basicPhone = false

          let basicFlag = false; // 报名信息校验结果
          // 校验报名信息全部填写
          for (const key in this.basicForm)  {
            if (!this.basicForm[key]) {
              basicFlag = true
              break;
            }
          };

          if (!basicFlag && !this.basicPhone && !this.basicEmail) {
            let specialFlag = false; // 专用发票校验结果
            let plainFlag = false; // 普通发票校验结果

            let submitForm; // 提交的表单
            let submitFlag = false;

            const type = this.basicForm.receiptType
            // 根据发票类型进行所提交的表单拼接
            if (type === '1') {
              // 校验专用发票信息全部填写
              for (const key in this.specialInvoice)  {
                if (!this.specialInvoice[key]) {
                  specialFlag = true
                  break;
                }
              };
              // 拼接表单
              if (!specialFlag) {
                submitForm = Object.assign(this.basicForm, this.specialInvoice)
                submitFlag = true;
              }
            } else if (type === '2') {
              // 校验普通发票信息全部填写
              for (const key in this.plainInvoice)  {
                if (!this.plainInvoice[key]) {
                  plainFlag = true
                  break;
                }
              };
              if (!plainFlag) {
                submitForm = Object.assign(this.basicForm, this.plainInvoice)
                submitFlag = true;
              }
            } else {
              submitForm = this.basicForm
              submitFlag = true;
            }
            // 校验成功后提交表单
            if (submitFlag) {
              save(submitForm).then(response => {
                const res = response.data;
                if (res.status == 200) {
                  this.$router.push({name: 'Result', params: { home: true }})
                } else {
                  MessageBox({
                    title: '提示',
                    message: '提交错误，请联系管理员',
                  });
                }
              })
            } else {
              MessageBox({
                title: '提示',
                message: '请填写正确的发票信息后，再点击提交',
              });
            }
          } else {
            MessageBox({
              title: '提示',
              message: '请填写正确的报名信息后，再点击提交',
            });
          }
        }
      })
    },
  },
  created() {
    console.log("欢迎来到个体化细胞治疗技术国家地方联合工程实验室")
  },
};
</script>

<style scoped>
.box-div {
  width: 100%;
  padding: 20px;
}
.main-content {
  padding: 15px;
  border-radius: 10px;
  -webkit-box-shadow: 0 2px 4px 0 rgba(0,0,0,0.23);
  box-shadow: 0 2px 4px 0 rgba(0,0,0,0.23);
}
.logo-img {
  padding: 0 25px;
}
.logo-img > img {
  width: 100%;
  height: auto;
}
h3 {
  text-align: center;
  font-size: 18px;
}
.mui-table-view-cell /deep/ .mint-cell-wrapper {
  padding: 0px;
}
p,ul {
  margin: 0;
}
li>p::before {
  content: '*';
  color: red;
}
span {
  color: red;
  font-size: 14px;
}
.mint-field /deep/ input[type='text'] {
  margin-bottom: 0;
}
.mint-radiolist /deep/ .mint-radiolist-title {
  margin: 0;
}
.mint-radiolist /deep/ .mint-radio-label {
  font-size: 14px;
}
.mint-button--primary {
  background-color: rgb(22, 155, 213);
}
span.warm-hint {
  color: #909399;
  font-size: 12px;
}
.foot {
  width: 100%;
}
</style>
