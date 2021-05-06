<template>
<div class="fillcontain">
    <head-top></head-top>
    <div style="padding:20px 0px 20px 20px"><el-button type="primary" @click="batchAll">全部导出</el-button></div>
    <div class="table_container" >
        <el-table :data="data" align="left">
            <el-table-column label="参会人姓名" prop="tName">
                <template slot-scope="scope">
                    <span>{{scope.row.name}}</span>
                </template>
            </el-table-column>
            <el-table-column label="参会人手机号码" prop="tPhone">
                <template slot-scope="scope">
                    <span>{{scope.row.phone}}</span>
                </template>
            </el-table-column>
            <el-table-column label="参会人所在单位名称" prop="tCompany">
                <template slot-scope="scope">
                    <span>{{scope.row.company}}</span>
                </template>
            </el-table-column>
            <el-table-column label="联系邮箱" prop="tEmail">
                <template slot-scope="scope">
                    <span>{{scope.row.email}}</span>
                </template>
            </el-table-column>
            <el-table-column label="发票类型" prop="tReceiptType">
                <template slot-scope="scope">
                    <span v-if="scope.row.receiptType==0">不开发票</span>
                    <span v-else-if="scope.row.receiptType==1">增值税专用发票</span>
                    <span v-else-if="scope.row.receiptType==2">增值税普通发票</span>
                    <span v-else >未知发票类型</span>
                </template>
            </el-table-column>            
            <el-table-column label="单位全称/名称" prop="tReceiptName">
                <template slot-scope="scope">
                    <span>{{scope.row.receiptName}}</span>
                </template>
            </el-table-column>
            <el-table-column label="纳税人识别号" prop="tReceiptNum">
                <template slot-scope="scope">
                    <span>{{scope.row.receiptNum}}</span>
                </template>
            </el-table-column>
            <el-table-column label="单位地址" prop="tReceiptAddress">
                <template slot-scope="scope">
                    <span>{{scope.row.receiptAddress}}</span>
                </template>
            </el-table-column>
            <el-table-column label="单位电话" prop="tReceiptPhone">
                <template slot-scope="scope">
                    <span>{{scope.row.receiptPhone}}</span>
                </template>
            </el-table-column>
            <el-table-column label="银行开户账号" prop="tReceiptAccount">
                <template slot-scope="scope">
                    <span>{{scope.row.receiptAccount}}</span>
                </template>
            </el-table-column>
            <el-table-column label="开户行" prop="tReceiptBank">
                <template slot-scope="scope">
                    <span>{{scope.row.receiptBank}}</span>
                </template>
            </el-table-column>
            <el-table-column label="报名提交时间" prop="tCreateTime">
                <template slot-scope="scope">
                    <span>{{transferTime(scope.row.createTime)}}</span>
                </template>
            </el-table-column>
        </el-table>
        <!-- <div class="Pagination" style="float:right" :visible.sync="abc">
            <el-pagination :current-page="page.current" :page-size="page.size" :total="page.total" :hide-on-single-page="true" layout="total, prev, pager, next" @size-change="handleSizeChange" @current-change="handleCurrentChange">
            </el-pagination>

        </div> -->
        <el-dialog title="查看比对结果" :visible.sync="dialogVisible">
            <el-row style="overflow: auto; text-align: left;">
                <!-- <span :v-for="text in txtText" :key="1"> -->
                <p style="white-space: pre-line;" v-html="txtText">
                </p>
            </el-row>
        </el-dialog>
    </div>
</div>
</template>

<script>
import headTop from '../components/headTop';
import moment from "moment";
import {
    baseUrl,
    baseImgPath
} from '@/config/env'

export default {
    data() {
        return {
            abc:false,
            timeInt: '',
            baseUrl,
            baseImgPath,
            dialogVisible: false,
            txtText: "",
            data:[],
            page: {
                size: 15,
                total: 6,
                current: 1,
                records: [
                    //     {
                    //     id: "20191218135932342234",
                    //     fileName: "脐带+脐带健康版+胎盘+胎盘健康版存储证模板.xlsx",
                    //     userName: "杨运军",
                    //     uploadTime: "2019-6-18 14:33",
                    //     contrastStatus: 0,
                    //     checkResultOssKey: "01de471d1afb4d1ab99ba3292f4e8516.txt"
                    // }
                ],
            }
        }

    },
    created() {
        //加载数据
        this.getList();
    },
    computed: {

    },
    components: {
        headTop,
    },
    methods: {
        transferTime(time) {
            var date = new Date(time);
            return this.formatDate(date);
        },
        formatDate(now) {
            var year = now.getFullYear();
            var month = now.getMonth() + 1;
            var date = now.getDate();
            var hour = now.getHours();
            var minute = now.getMinutes();
            var second = now.getSeconds();
            return year + "-" + month + "-" + date + " " + hour + ":" + minute + ":" + second;
        },
        handleEdit(row) {
            this.dialogVisible = true;
            this.getTxtFile(row.checkResultOssKey);
        },
        handleSizeChange(val) {
            this.getFiles()
        },
        handleCurrentChange(val) {
            this.page.current = val;
            this.getFiles()
        },
        async handleDelete(index, row) {
            alert(index);
        },
         getList() {
            this.$api.post("/api/signResult/list", {
                    current: this.page.current,
                    size: this.page.size,
                },
                res => {
                    if (res.status == 200) {
                        this.data = res.data;

                    } else {
                        this.$message({
                            type: 'error',
                            message: res.msg
                        });
                    }
                })
        },
        batchAll() {
             this.$axios.post('/signResult/outPut', this.page,{responseType: 'blob'}).then((res) => {
                console.log( res.data)
                const link = document.createElement('a')
                let blob = new Blob([res.data],{type: 'application/vnd.ms-excel'});
                link.style.display = 'none'
                link.href = URL.createObjectURL(blob);
                link.setAttribute('download', "报名信息"+this.getCurrentDateTime()+".xls")
                document.body.appendChild(link)
                link.click()
                document.body.removeChild(link)
             })
        },
        getCurrentDateTime(){
          return moment(new Date()).format('YYYY-MM-DD')
        },

    },
}
</script>

<style lang="less">
@import '../style/mixin';

.demo-table-expand {
    font-size: 0;
}

.demo-table-expand label {
    width: 90px;
    color: #99a9bf;
}

.demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 50%;
}

.table_container {
    padding: 20px;
}

.Pagination {
    display: flex;
    justify-content: flex-start;
    margin-top: 8px;
}

.avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
}

.avatar-uploader .el-upload:hover {
    border-color: #20a0ff;
}

.avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 120px;
    height: 120px;
    line-height: 120px;
    text-align: center;
}

.avatar {
    width: 120px;
    height: 120px;
    display: block;
}
</style>
