<template>
  <div>
    <el-form :inline="true" :model="editForm" ref="editForm" style="width: 100%;margin-top:0px;" size="small">
      <el-form-item label="分成缘由" style="width: 15%;font-weight: 900;" align="left"></el-form-item>
      <el-form-item label="分成比例" style="width: 30%;font-weight: 900;" align="left"></el-form-item>
      <el-form-item label="分成转移（分成人没有时转移）" style="width: 38%;margin-left: 15px;font-weight: 900;"></el-form-item>

      <el-form-item label="房源录入人">
        <el-input v-model="editForm.actionRoleIptRate" prop="actionRoleIptRate"
          style="width: 200px;" type="number" min="0"
          >
        </el-input>
        <span class="el-input__suffix">
          <span class="el-input__suffix-inner">
            <i class="el-input__icon">%</i>
          </span>
        </span>
      </el-form-item>
      <el-form-item label="房源开盘人">
        <el-input v-model="editForm.actRoleOpMgrRate" prop="actRoleOpMgrRate"
                  style="width: 200px;" type="number" min="0"
                  suffix-icon="@/common/images/img-percent.ico"></el-input>
        <span class="el-input__suffix">
          <span class="el-input__suffix-inner">
            <i class="el-input__icon">%</i>
          </span>
        </span>
      </el-form-item>

      <el-form-item label="房源维护人">
        <el-input
          v-model="editForm.actRoleHouseMsgRate" prop="actRoleHouseMsgRate"
          style="width: 200px;"
          type="number" min="0"
        ></el-input>
        <span class="el-input__suffix">
          <span class="el-input__suffix-inner">
            <i class="el-input__icon">%</i>
          </span>
        </span>
      </el-form-item>

      <el-form-item label="客源录入人">
        <el-input
          v-model="editForm.actRoleCusIptRate" prop="actRoleCusIptRate"
          style="width: 200px;"
          type="number" min="0">
        </el-input>
        <span class="el-input__suffix">
          <span class="el-input__suffix-inner">
            <i class="el-input__icon">%</i>
          </span>
        </span>
      </el-form-item>

      <el-form-item label="客源维护人">
        <el-input
          v-model="editForm.actRoleCusMgrRate" prop="actRoleCusMgrRate"
          type="number" min="0"
          style="width: 200px;"></el-input>
        <span class="el-input__suffix">
          <span class="el-input__suffix-inner">
            <i class="el-input__icon">%</i>
          </span>
        </span>
      </el-form-item>

      <el-form-item label="合同成交人">
        <el-input
          v-model="editForm.actRoleContractMgrRate" prop="actRoleContractMgrRate"
          style="width: 200px;" type="number" min="0"></el-input>
        <span class="el-input__suffix">
          <span class="el-input__suffix-inner">
            <i class="el-input__icon">%</i>
          </span>
        </span>
      </el-form-item>

      <el-form-item label="房源委托人">
        <el-input v-model="editForm.actHouseEntrustMgrRate" type="number" min="0"
           prop="actHouseEntrustMgrRate" style="width: 200px;"></el-input>
        <span class="el-input__suffix">
          <span class="el-input__suffix-inner">
            <i class="el-input__icon">%</i>
          </span>
        </span>
      </el-form-item>
      <el-form-item prop="houseEntrustTrans">
        <el-select style="width: 100%" v-model="editForm.houseEntrustTrans" placehelder="--请选择--">
          <el-option
            v-for="item in divideActRoleOpts"
            :key="item.name"
            :label="item.name"
            :value=item.id
            :disabled="item.id===7"
          >
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="拿钥匙人"  style="margin-left: 15px;" prop="actRoleKeyMgrRate">
        <el-input v-model="editForm.actRoleKeyMgrRate" style="width: 200px;" type="number" min="0"></el-input>
        <span class="el-input__suffix">
          <span class="el-input__suffix-inner">
            <i class="el-input__icon">%</i>
          </span>
        </span>
      </el-form-item>
      <el-form-item prop="keyMgrTrans">
        <el-select v-model="editForm.keyMgrTrans" style="width: 220px;">
          <el-option
            v-for="item in divideActRoleOpts"
            :key="item.name"
            :label="item.name"
            :value="item.id"
            :disabled="item.id===8"
          >
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="签独家人" style="margin-left: 15px;" prop="actRoleSignUkRate">
        <el-input v-model="editForm.actRoleSignUkRate" style="width: 200px;"
                  type="number" min="0"></el-input>
        <span class="el-input__suffix">
          <span class="el-input__suffix-inner">
            <i class="el-input__icon">%</i>
          </span>
        </span>
      </el-form-item>
      <el-form-item style="width: 220px;" prop="signUkTrans">
        <el-select v-model="editForm.signUkTrans" style="width: 220px;">
          <el-option
            v-for="item in divideActRoleOpts"
            :key="item.name"
            :label="item.name"
            :value="item.id"
            :disabled="item.id===9"
            >
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="传照片人"  style="margin-left: 15px;" prop="actRolePivUploadRate">
        <el-input type="number" min="0"
          v-model="editForm.actRolePivUploadRate" style="width: 200px;"></el-input>
        <span class="el-input__suffix">
          <span class="el-input__suffix-inner">
            <i class="el-input__icon">%</i>
          </span>
        </span>
      </el-form-item>
      <el-form-item prop="picUploadTrans">
        <el-select v-model="editForm.picUploadTrans" style="width: 220px;">
          <el-option
            v-for="item in divideActRoleOpts"
            :key="item.name"
            :label="item.name"
            :value="item.id"
            :disabled="item.id===10"
          >
          </el-option>
        </el-select>
      </el-form-item>
    </el-form>

    <div class="btn-group">
      <el-button type="primary" @click="handleConfigSubmit">确认</el-button>
      <el-button @click="handleConfigCancel">取消</el-button>
      <el-button type="primary" @click="resetForm">重置</el-button>
    </div>
  </div>
</template>

<script>
  import { queryDivideintoRole } from '@/request/manage/common'
  import { saveOrUpdateDivideintoDetailConfig,  queryDivideDetailConfigObj } from '@/request/manage/divideinto'
  export default {
    name: "detailConf",
    props: {
      detailData: {
        type: Object
      },
      configId:{
        type: Number
      }
    },
    data(){
      return {
        divideActRoleOpts: [],
        editForm: {
          actionRoleIptRate: '',// 房源录入人
          actRoleOpMgrRate: '', // 房源开盘人
          actRoleHouseMsgRate: '', // 房源维护人
          actRoleCusIptRate: '', // 客源录入人
          actRoleCusMgrRate: '', // 客源维护人
          actRoleContractMgrRate: '', // 合同成交人
          actHouseEntrustMgrRate: '', // 房屋委托人
          houseEntrustTrans: '',
          actRoleSignUkRate: '', // 签独家人
          keyMgrTrans: '',
          actRoleKeyMgrRate: '', // 拿钥匙人
          signUkTrans: '',
          actRolePivUploadRate: '', // 传照片人
          picUploadTrans: ''
        }
      }
    },
    methods:{
      isActRoleShow(key1, key2){
        if(key1 == key2){
          return true
        }
        this.disabled = false
      },
      // 查询分成转移角色信息
      queryDivideintoRole(){
        queryDivideintoRole(null).then(res=>{
          this.divideActRoleOpts = res
        }).catch(err=>{
          console.log(err)
        })
      },
      // 重置数据
      resetForm(){
        for (let i in this.editForm) {
          this.editForm[i] = ''
        }
      },
      setForm(){
        this.resetForm()
        for (let i in this.editForm) {
          if(this.detailData && this.detailData[i]){
              this.editForm[i] = this.detailData[i]
          }
        }
      },
      // 取消按钮
      handleConfigCancel() {
        this.$emit('handleClick', 3)
      },
      // 提交数据
      handleConfigSubmit:function () {
        this.$refs['editForm'].validate((valid) => {
          if (valid) {
            let params = {...this.editForm}
            if((params.actHouseEntrustMgrRate + params.actRoleContractMgrRate + params.actRoleCusIptRate
              + params.actRoleCusMgrRate + params.actRoleHouseMsgRate + params.houseEntrustTrans
              + params.actRoleKeyMgrRate + params.actRoleOpMgrRate + params.houseEntrustTrans
              + params.actRolePivUploadRate + params.actRoleSignUkRate + params.keyMgrTrans + params.picUploadTrans
              + params.actionRoleIptRate + params.signUkTrans) == "" ){
              this.$message({
                type: 'warning',
                message: '没有输入任何数据！'
              })
              return false;
            }
            params.configId = this.configId
            this.$confirm('确定保存数据?', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              this.loadingBtn = true
              saveOrUpdateDivideintoDetailConfig({jsonData: JSON.stringify(params)}).then(res => {
                if (res.success) {
                  this.$message({
                    type: 'success',
                    message: res.msg
                  })
                  this.loadingBtn = false
                  this.$emit('handleClick', 4)
                } else {
                  this.$message({
                    type: 'warning',
                    message: res.msg
                  })
                  this.loadingBtn = false
                }
              }).catch(() => {
                this.loadingBtn = false
              })
            }).catch(() => {
              this.$message({
                type: 'info',
                message: '取消!'
              })
            })
          }
        })
      }
    },
    // 页面启动加载函数（生命周期函数）
    mounted (){
      this.queryDivideintoRole()
      this.setForm(this.detailData)
    },
    watch: {
      detailData: {
        deep: true,
        handler () {
          this.setForm(this.detailData)
        }
      }
    }
  }
</script>

<style scoped>
  .percent{
    position: absolute;
    height: 100%;
    right: 5px;
    top: 0;
    text-align: center;
    color: #c0c4cc;
    -webkit-transition: all .3s;
    transition: all .3s;
    pointer-events: none;
    url: '/common/imagesimg-percent.ico'
  }
</style>
