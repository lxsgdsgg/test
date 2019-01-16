<template>
  <div class="page-content">
    <div class="page-content-hd">
        <el-form size="small" :inline="true" :model="form" ref="form">

          <!--<el-form-item label="所属城市" >-->
            <!--<base-city-cascader-->
              <!--@change="handleCity" v-model="form.cityCode"-->
              <!--placeholder="所属城市"-->
              <!--:url="cityUrl"-->
              <!--:props="cascaderProps"-->
              <!--:dataProps="cascaderDataProps"-->
              <!--@initSelectData="initSelectData"-->
              <!--ref="close"-->
            <!--&gt;</base-city-cascader>-->
          <!--</el-form-item>-->

          <el-form-item label="类别名称" prop="name">
            <el-input v-model="form.name"></el-input>
          </el-form-item>

          <el-form-item label="类别等级" prop="level">
            <el-select v-model="form.level">
                <el-option
                  v-for="item in levelOpt"
                  :key="item.id"
                  :value="item.id"
                  :label="item.fieldValue">
                </el-option>
            </el-select>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click="_loadData(true)" :loading="loadingBtn">查询</el-button>
            <el-button @click.native.prevent="handleReset">清空</el-button>
            <el-button v-hasOnlyBtn="'addBtn'" @click="handleAdd">新增</el-button>
          </el-form-item>
        </el-form>
      </div>
    <div class="page-content-bd" v-loading="loadingView">
      <el-table :data="tableData" border align="left" style="width: 100%">

        <el-table-column
          prop="type"
          align="center"
          label="类型">
          <template slot-scope="scope">
              {{scope.row.type | typeValidate}}
          </template>
        </el-table-column>

        <el-table-column
          prop="name"
          align="center"
          label="类型名称">
        </el-table-column>

        <el-table-column
          prop="level"
          align="center"
          label="类别等级">
          <template slot-scope="scope">
              {{scope.row.level | levelValidate}}
          </template>
        </el-table-column>

        <el-table-column
          prop="paransName"
          align="center"
          label="上级类别">
        </el-table-column>

        <el-table-column
          prop="operator"
          align="center"
          label="操作人">
        </el-table-column>

        <el-table-column
          prop="addTime"
          align="center"
          :formatter="timeFormat"
          label="添加时间">
        </el-table-column>

        <!--<el-table-column-->
          <!--prop="updateTime"-->
          <!--align="center"-->
          <!--:formatter="timeFormat"-->
          <!--label="修改时间">-->
        <!--</el-table-column>-->

        <el-table-column
          align="center"
          label="操作">
          <template slot-scope="scope">
            <el-button v-hasMultipleBtn="['editBtn',scope.row]"  type="text" size="small" @click="handleEditor(scope.row)">编辑</el-button>
            <el-button v-hasMultipleBtn="['delBtn',scope.row]" type="text" size="small" @click="handleDelete(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <!--分页控件-->
      <b-pagination
        :listQuery="listQuery"
        @handleSizeChange="handleSizeChange"
        @handleCurrentChange="handleCurrentChange">
      </b-pagination>
    </div>
    <template>
      <el-dialog
        title="编辑"
        :visible.sync="houseArticleClassifyDialog"
        :close-on-click-modal="false"
        width="500px"
      >
        <house-article-classify-editor @handleClick="optionOrClose" v-if="hackReset" :data="currentRowData" :isAdd="isAdd"></house-article-classify-editor>
      </el-dialog>
    </template>
  </div>
</template>
<style></style>
<script>
  import PageList from '@/mixins/pageList'
  import BaseCityCascader from '@/components/BaseCascader'
  import {queryHouseArticleClassify,delHouseArticleClassify} from '@/request/manage/HouseArticleClassify'
  import HouseArticleClassifyEditor from './component/houseArticleClassifyEditor'
  import * as RequeryLogURL from '@/request/log/systemPlatformLog'//客源日志统一接口


  export default {
        name:'houseArticleClassify',
        mixins:[PageList],
        components:{BaseCityCascader,HouseArticleClassifyEditor,PageList},
        data() {
            return {
              form:{
                cityCode:'',
                name:'',
                level:''
              },
              tableData:[],
              levelOpt:[
                {id:'1',fieldValue:'一级'},
                {id:'2',fieldValue:'二级'},
                {id:'3',fieldValue:'三级'},
              ],
              cityUrl: 'manage/commonselect/queryOpenCity',//城市数据源
              cascaderDataProps: {id: 'code', parent: 'parentCode'}, // 级联数据源配置选项
              cascaderProps: { // 级联下拉组件配置选项
                value: 'code', // 指定选项的值为选项对象的某个属性值
                label: 'name' // 指定选项标签为选项对象的某个属性值
              },
              close:false,
              loadingView:false,
              isAdd:false,
              currentRowData:null,
              hackReset:false,
              loadingBtn:false,
              listQuery:{
                limit:10,
                page:1,
                total:0
              },
              houseArticleClassifyDialog:false,
            }
        },
        methods: {
          initSelectData(data){
            if (data!=null && data.length>0){
              this.form.cityCode = data[0].code
              this._loadData(false)
            }
          },
          queryHouseArticleClassify(loadingBtn){
            if(loadingBtn){
              this.listQuery.page = 1
              this.listQuery.currentPage = 1
            }
            this.loadingBtn = loadingBtn
            this.loadingView =true
            let params = Object.assign({},this.form,{
                  page:this.listQuery.page,
                  limit:this.listQuery.limit
            })
            queryHouseArticleClassify(params).then(res =>{
              this.tableData = res.data
              this.listQuery.total = res.count
              this.loadingView =false
              this.loadingBtn = false
            })
          },
          //城市
          handleCity(){

          },
          //查询
          handleSelect(){
            this.queryHouseArticleClassify(true)
          },
          //重置
          handleReset(){
            this.$refs['form'].resetFields()
          },
          //新增
          handleAdd(){
            this.hackReset = false
            this.$nextTick(()=>{
              this.hackReset = true
              this.isAdd = true
              this.currentRowData = null
              this.houseArticleClassifyDialog = true
            })
          },
          handleEditor(row){
            this.hackReset = false
            this.$nextTick(()=>{
              this.hackReset = true
              this.isAdd = false
              this.currentRowData = row
              this.houseArticleClassifyDialog = true
            })
          },
          //删除
          handleDelete(row){
              this.$confirm('确定要删除吗？',{
                confirmBtnText:'确定',
                cancelBtnText:'取消'
              }).then(()=>{
                let params = {houseAticleId:row.id}
                delHouseArticleClassify(params).then(res =>{
                  if(res.success){
                    this.$message({type:'success',message:res.msg})
                    let message = {
                      // sourceId:  row.demandId,//资源编号：客源编号
                      sourceCode:  row.name,//资源编号：客源编号
                      sourceTypeId:16,//类型：房产文章分类
                      operatTypeId: 3,//操作类型 删除,
                      logContent: '删除'+row.name//日志内容
                    }
                    RequeryLogURL.systemDelLog({message:JSON.stringify(message)}).then(res=>{
                      console.log(res)
                    }).catch(error =>{
                      console.log(error)
                    })
                    this.queryHouseArticleClassify(false)
                  }else{
                    this.$message({type:'warning',message:res.msg})
                  }
                }).catch(error =>{
                    console.log(error)
                })
              }).catch(() =>{
                this.$message({type:'info',message:'已取消'})
              })
          },
          _loadData(btn){
            this.queryHouseArticleClassify(btn)
          },
          //窗体回调方法
          optionOrClose(value){
            if(value && value == 1){
              this.houseArticleClassifyDialog = false
              this.queryHouseArticleClassify(false)
            }else{
              //取消
              this.houseArticleClassifyDialog = false
            }
          },
          //时间格式化
          timeFormat(row,clumn,cellValue){
              return this.$utils.timeFormat(cellValue)
          }
        },
        filters:{
          typeValidate(value){
            if(value == 0)return '房产百科'
            if(value == 1)return '房产资讯'

          },
          levelValidate(value){
            if(value ==1 )return '一级'
            if(value ==2 )return '二级'
            if(value ==3 )return '三级'
          }
        },
        mounted() {
              // this.queryHouseArticleClassify(false)
        }

    }

</script>
