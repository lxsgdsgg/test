<!-- 变更相关经纪人信息 -->
<!-- type 经纪人类型 操作不同表单-->

<template>
  <div>
    <!-- 维护人 -->
    <el-form v-if="type === '5'"  v-loading="loadingView" :model="form1" :rules="rules1" ref="form" label-width="80px">
      <el-form-item label="原维护人">
        <base-cascader
          :disabled="true"
          :changeOnSelect="false"
          v-model="form1.agentId"
          :data="peopleSelectOpts" :props="selectProps" :dataProps="selectDataProps">
        </base-cascader>
      </el-form-item>

      <el-form-item label="现维护人" prop="peopleid">
        <base-cascader
          :changeOnSelect="false"
          @change="handleChangeSelect"
          v-model="form1.peopleid"
          :data="peopleSelectOpts" :props="selectProps" :dataProps="selectDataProps">
        </base-cascader>
      </el-form-item>

      <el-form-item class="margin-b-none">
        <el-button type="primary" :loading="loadingSubmitBtn" @click="handleFormSubmit(1)">确认</el-button>
        <el-button @click="handleFormCancel">取消</el-button>
      </el-form-item>

    </el-form>

  </div>
</template>

<script>
  import BaseCascader from '@/components/BaseCascader'
  import {updateAgent} from '@/request/customer/customerUsed'
  import {customerAddLog, customerUpdateLog, customerQueryLog,customerDelLog} from '@/request/log/customerLog'
  export default {
    props: {
      type: {
        type: [Number, String],
        required: true
      },
      transactionTypeId: {
        type: [Number, String],
        required: true
      },
      data: {
        type: Object,
        required: true
      },
      peopleSelectOpts: {
        type: Array,
        default () {
          return []
        }
      }
    },
    components: {BaseCascader},
    data () {
      return {
        selectDataProps: {id: 'code', parent: 'parentCode'}, // 级联数据源配置选项
        selectProps: { // 级联下拉组件配置选项
          value: 'code', // 指定选项的值为选项对象的某个属性值
          children: 'children', // 指定选项的子选项为选项对象的某个属性值
          label: 'name' // 指定选项标签为选项对象的某个属性值
        },
        loadingSubmitBtn: false,
        loadingView: false,
        form1: {
          recordid: '',
          agentId: '',
          peopleid: '',
          people: '',
          type: '',
        },
        rules1: {
          peopleid: [
            {required: true, message: '信息填写有误', trigger: 'change'}
          ]
        },
       
        currentSelData: null
      }
    },

    methods: {
      handleFormSubmit () {
        
        
        this.$refs['form'].validate((valid) => {
          if (valid) {

            this.$confirm('确定保存编辑的信息吗?', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              this.loadingSubmitBtn = true

              let cfg = {
                headers: {
                  'Content-Type': 'application/json;charset=UTF-8;'
                }
              }
              let params = {}

              switch (this.type) {
                case '5':
                  params = this.form1
                  delete params.agentId
                  break
                
              }
              updateAgent(params, cfg).then(res => {
                this.loadingSubmitBtn = false
                this.$message({
                  type: 'success',
                  message: res.msg
                })

                // 传递给父组件
                let emitData = {
                  agentType: this.type,
                  departmentName: this.currentSelData.parent.name,
                  agentName: this.currentSelData.name,
                  mobile: this.currentSelData.mobile,
                  updateTime: new Date().getTime(),
                  agentId: this.currentSelData.code,
                  id: params.recordid
                }

                this.$emit('handleBtnClick', 1, this.type, emitData)

                //记日志
                let logContent = "维护人由"+this.data.agentName+"变为"+this.form1.people 
                let message = {
                sourceId: this.data.demandId,
                sourceCode: this.data.demandId,
                businessTypeId: 2,//业务类型，
                sourceTypeId: this.transactionTypeId,
                operatTypeId: 208,
                logContent: logContent,
                remark: this.$route.query.formName
              }
              customerQueryLog({message: JSON.stringify(message)}).then(res => {
                console.log(res)
              })

              }).catch(() => {
                this.loadingSubmitBtn = false
              })
            })

          }
        })
      },

      handleFormCancel () {
        this.$emit('handleBtnClick', 2)
      },

      handleChangeSelect (val, name, data) {

        switch (this.type) {
          case '5':
            this.form1.people = name
            break
          
        }

        this.currentSelData = data

        if (data.dataType !== 'user') {

          this.$message({
            type: 'warning',
            message: '只能选择用户!',
            showClose: true
          })

          setTimeout(() => {
            for (let i = 1; i < 6; i++) {
              this['form' + i].people = ''
              this['form' + i].peopleid = ''
            }

            this.currentSelData = null
          }, 50)

        }
      },

      _setForm () {
        this.loadingView = true
        switch (this.type) {
          case '5':
            this.form1 = {...this.form1, ...this.data}
            break
          
        }
        this.loadingView = false
        console.log("11111111111111"+this.data)
        
      },

      _resetForm () {
        if (this.$refs['form']) {
          this.$refs['form'].resetFields && this.$refs['form'].resetFields()
          this.$refs['form'].clearValidate && this.$refs['form'].clearValidate()
        }
      }
    },

    mounted () {
      this._setForm()
    
    },

    watch: {
      data: {
        deep: true,
        handler () {
          this._setForm()
          
        }
      }
    },
  }
</script>

<style scoped lang="scss">

</style>
