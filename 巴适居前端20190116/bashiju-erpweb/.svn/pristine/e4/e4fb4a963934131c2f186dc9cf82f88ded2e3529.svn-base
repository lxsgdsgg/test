<template>
    <div class="page-content">
        <div class="page-content-hd">
            <el-form :inline="true"   ref="queryForm" :model="queryForm" size="small">
                <el-form-item label="公司名称" prop="companyId">
                  <base-cascader change-on-select
                     clearable v-model="queryForm.companyId"
                     :change-on-select="false"
                     @change="handleSelectCompany"
                     placeholder="选择公司"
                     :data="cityAndCompany"
                     :props="cascaderProps"
                     :dataProps="cascaderDataProps"
                     style="width: 140px" >
                  </base-cascader>
                </el-form-item>
                <el-form-item>
                    <el-button :isLoading="loadingBtn" type="primary" @click=" _loadData(true)">查询</el-button>
                    <el-button @click.native.prevent="handleReset">清空</el-button>
                </el-form-item>
            </el-form>
        </div>
        <div class="page-content-bd" v-loading="loadingView">
            <el-table
              :data="tableData"
              style="width: 100%"
              border
            >
              <el-table-column
                prop="companyName"
                label="公司"
                align="center"
              >
              </el-table-column>
              <el-table-column
                prop="houseId"
                label="房源编号"
                align="center"
              >
              </el-table-column>
              <el-table-column
                prop="deptName"
                label="部门名称"
                align="center"
              >
              </el-table-column>
              <el-table-column
                prop="follower"
                label="跟进人"
                align="center"
              >
              </el-table-column>
              <el-table-column
                prop="content"
                label="电话内容"
                align="center"
              >
                <template slot-scope="scope">
                  {{scope.row.content.split(':')[0]}}
                  &nbsp;
                  <icon
                    v-if="playShow(scope.row.content)"
                    @click.native="playSound(scope.row.content)"
                    class="play-icon" name="play" width="20" height="20" scale="20">
                  </icon>
                </template>
              </el-table-column>
              <el-table-column
                prop="addTime"
                label="添加时间"
                align="center"
                :formatter="dateFormat"
              >
              </el-table-column>
            </el-table>
            <b-pagination
              :listQuery="listQuery"
              @handleSizeChange="handleSizeChange"
              @handleCurrentChange="handleCurrentChange">
            </b-pagination>
          <audio ref="soundEl" style="display:none; height: 0" preload="auto"></audio>
        </div>
    </div>
</template>
<style></style>
<script>
  import {queryBackgroundCallRecord} from "@/request/manage/backgroundCallRecord";
  import PageList from '@/mixins/pageList'
  import * as selectUrl from '@/request/manage/common' // 获取下拉框数据 url地址
  import BaseCascader from '@/components/BaseCascader'
  export default {
        name:'backgroundCallRecord',
        components:{PageList,BaseCascader},
        mixins:[PageList],
        data() {
            return {
                queryForm: {
                  companyId:''
                },
                tableData:null,
                listQuery:{page:1, limit:10, total:0},
                loadingView:false,
                loadingBtn:false,
                cityAndCompany:[],
                cascaderDataProps: {id: 'code', parent: 'parentCode'}, // 级联数据源配置选项
                cascaderProps: { // 级联下拉组件配置选项
                    value: 'code', // 指定选项的值为选项对象的某个属性值
                    children: 'children', // 指定选项的子选项为选项对象的某个属性值
                    label: 'name' // 指定选项标签为选项对象的某个属性值
                },
            }
        },
        methods: {
          _queryBackgroundCallRecord(btn){
             if(btn){
               this.loadingBtn = btn
               this.listQuery.page = 1
               this.listQuery.currentPage = 1
             }
            this.loadingView = true
            this.loadingBtn = true
            let params = Object.assign({},this.queryForm,{
              limit:this.listQuery.limit,
              page:this.listQuery.page,
            })
            queryBackgroundCallRecord(params).then(res =>{
                this.loadingView = false
                this.loadingBtn = false
                this.tableData = res.data
                this.listQuery.total = res.count
            }).catch(error =>{
              console.log(error)
              this.loadingView = false
              this.loadingBtn = false
            })
          },
          _loadData(btn) {
            this._queryBackgroundCallRecord(btn);
          },
          //时间格式化
          dateFormat(row,clumn,cellValue){
            return this.$utils.timeFormat(cellValue,'{y}-{m}-{d} {h}:{i}:{s}')
          },
          playShow (data) {
            let index = data.indexOf(':')
            let url
            if (index !== -1) url = data.substring(index + 1)
            return !!url
          },
          playSound (data) {
            let index = data.indexOf(':')
            let url
            if (index !== -1) url = data.substring(index + 1)
            if (url) {
              let el = this.$refs['soundEl']
              if (el.paused) {
                el.src = url
                el.play()
              } else {
                el.pause()
              }
            }
          },
          //获取城市和公司数据
          _queryOpenCityCompany(){
            selectUrl.queryOpenCityCompany({}).then(res=>{
              this.cityAndCompany = res
            })
          },
          handleSelectCompany(value,name,data){
            if(data.dataType !== 'company'){
              setTimeout(() => {
                this.queryForm.companyId = ''
              }, 50)
            }
          },
          handleReset(){
            this.$refs['queryForm'].resetFields();
          }
        },
        mounted() {
            this._queryOpenCityCompany();
        }

    }

</script>
