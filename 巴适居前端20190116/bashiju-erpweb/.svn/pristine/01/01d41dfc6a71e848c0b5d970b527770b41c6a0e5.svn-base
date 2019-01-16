<!-- 住宅 -->

<template>
  <div v-loading="loadingView" style="height: 570px">
    <el-form v-if="selectOpts" :model="detailEditForm" ref="detailEditForm" :rules="rules" label-width="110px" size="small">

      <div class="form-wrapper">
        <el-row>
          <el-col :span="10">
            <el-form-item label="客户姓名">
              <el-form-item style="display: inline-block;"><el-input  v-model="detailEditForm.custName"></el-input></el-form-item>
              <el-form-item style="display: inline-block;width:50%;">
                <base-select
                  v-model="detailEditForm.custCallType"
                  :data="selectOpts.custCallType"
                  :props="selectProps"
                >
                </base-select>
              </el-form-item>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="需求区域" prop="communityIds">
              <base-cascader
                v-model="detailEditForm.communityIds"
                :url="getCommunitySelectUrl"
                :props="cascaderProps"
                :dataProps="cascaderDataProps" @change="onChange" style="width:150px">
              </base-cascader>
            </el-form-item>
          </el-col>

          <el-col :span ="6">
            <el-form-item label="用途" prop="houseUsesIds">

              <base-select
                v-model="detailEditForm.houseUsesIds"
                :data="selectOpts.houseUsesIds"
                :props="selectPropsOther"
                :multiple="true"
              >
              </base-select>

            </el-form-item>
          </el-col>
        </el-row>

        <el-row>

          <el-col :span="6">
            <el-form-item label="需求户型" prop="sellingPrice">
              <el-input style="width: 100%" v-model="detailEditForm.sellingPrice"><i slot="suffix" style="font-size: 12px;margin-right: 5px">万元</i></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="6">

            <el-form-item label="需求价格" required class="margin-b-none">

              <el-form-item style="display: inline-block;" prop="rentPrice">
                <el-input v-model="detailEditForm.rentPrice"></el-input>
              </el-form-item>


            </el-form-item>

          </el-col>
          <el-col :span="6">
            <el-form-item label="需求面积" prop="sellingPrice">
              <el-input style="width: 100%" v-model="detailEditForm.sellingPrice"><i slot="suffix" style="font-size: 12px;margin-right: 5px">万元</i></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item>
               <el-form-item style="display: inline-block;"><el-checkbox v-model="isFullAmount" label="全款" border></el-checkbox> </el-form-item>
               <el-form-item style="display: inline-block;"><el-checkbox v-model="isUrgent" label="急切" border></el-checkbox> </el-form-item>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>

          <el-col :span="6">
            <el-form-item label="需求楼层" label-width="80px"  class="margin-b-none">
              <el-form-item style="display: inline-block;"><el-input v-model="detailEditForm.floorCount" readonly="readonly"><i slot="suffix" style="font-size: 12px;margin-right: 5px">楼</i></el-input></el-form-item>
              <el-form-item style="display: inline-block;"><el-input v-model="detailEditForm.totalLayers" readonly="readonly"><i slot="suffix" style="font-size: 12px;margin-right: 5px">楼</i></el-input></el-form-item>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="朝向" prop="orientationId">

              <base-select
                v-model="detailEditForm.orientationId"
                :data="selectOpts.orientationId"
                :props="selectPropsOther"
                :multiple="true"
              >
              </base-select>

            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="需求房龄" label-width="80px"  class="margin-b-none">
              <el-form-item style="display: inline-block;"><el-input v-model="detailEditForm.floorCount" readonly="readonly"><i slot="suffix" style="font-size: 12px;margin-right: 5px">楼</i></el-input></el-form-item>
              <el-form-item style="display: inline-block;"><el-input v-model="detailEditForm.totalLayers" readonly="readonly"><i slot="suffix" style="font-size: 12px;margin-right: 5px">楼</i></el-input></el-form-item>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="装修" prop="decorationId">

              <base-select
                v-model="detailEditForm.decorationId"
                :data="selectOpts.decorationId"
                :props="selectPropsOther"
                :multiple="true"
              >
              </base-select>

            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="配套:" prop="matchingIds">

              <base-select
                v-model="detailEditForm.matchingIds"
                :data="selectOpts.matchingIds"
                :props="selectPropsOther"
                :multiple="true">
              </base-select>

            </el-form-item>
          </el-col>



          <el-col :span="12">

            <el-form-item label="需求原因" prop="reason">
              <el-input v-model="detailEditForm.reason" placeholder="原因"></el-input>
            </el-form-item>

          </el-col>

        </el-row>

        <el-row>

          <el-col :span="8">
            <el-form-item label="推荐标签：" prop="labeld">
              <base-select
                v-model="detailEditForm.labeld"
                :data="selectOpts.labeld"
                :props="selectPropsLabel"
                :multiple="true"
              >
              </base-select>
            </el-form-item>
          </el-col>

           <el-col :span="8">

            <el-form-item label="房屋类型" prop="houseTypeId">

              <base-select
                v-model="detailEditForm.houseTypeId"
                :data="selectOpts.houseTypeId"
                :props="selectPropsOther"
              >
              </base-select>

            </el-form-item>

          </el-col>

          <el-col :span="8">
            <el-form-item>
               <el-form-item style="display: inline-block;"><el-radio v-model="housePropType" label="01" border>一手</el-radio></el-form-item>
               <el-form-item style="display: inline-block;"><el-radio v-model="housePropType" label="02" border>二手</el-radio></el-form-item>
            </el-form-item>
          </el-col>

        </el-row>

         <div class="dashed-line mb15 mt5"></div>  <!-- 画线 -->

        <el-row>

          <el-col :span="6">
            <el-form-item label="沟通阶段" prop="communicateStageId">

              <base-select
                v-model="detailEditForm.communicateStageId"
                :data="selectOpts.communicateStageId"
                :props="selectPropsOther"
              >
              </base-select>

            </el-form-item>
          </el-col>

          <el-col :span="6">
             <el-form-item label="客户来源" prop="resourceTypeId">

              <base-select
                v-model="detailEditForm.resourceTypeId"
                :data="selectOpts.resourceTypeId"
                :props="selectPropsOther"
              >
              </base-select>

            </el-form-item>
          </el-col>

          <el-col :span="6">
            <el-form-item label="消费理念" prop="consumptIdeaId">

              <base-select
                v-model="detailEditForm.consumptIdeaId"
                :data="selectOpts.consumptIdeaId"
                :props="selectPropsOther"
                :multiple="true"
              >
              </base-select>

            </el-form-item>
          </el-col>

          <el-col :span="6">
            <el-form-item label="国籍" prop="censusRegisterId">

              <base-select
                v-model="detailEditForm.censusRegisterId"
                :data="selectOpts.censusRegisterId"
                :props="selectPropsOther"
              >
              </base-select>

            </el-form-item>
          </el-col>

        </el-row>

        <el-row>
          <el-col :span="6">
            <el-form-item label="民族" prop="nationsId">

              <base-select
                v-model="detailEditForm.nationsId"
                :data="selectOpts.nationsId"
                :props="selectPropsOther"
              >
              </base-select>

            </el-form-item>
          </el-col>

          <el-col :span="6">
            <el-form-item label="证件号码" prop="payTypeId">

              <base-select
                v-model="detailEditForm.payTypeId"
                :data="selectOpts.payTypeId"
                :props="selectProps"
              >
              </base-select>

            </el-form-item>
          </el-col>

          <el-col :span="6">
            <el-form-item label="邮箱" prop="percentage">

              <el-input v-model="detailEditForm.percentage" style="width: 100%"></el-input>

            </el-form-item>
          </el-col>

          <el-col :span="6">
            <el-form-item label="QQ" prop="minSellingPrice">

              <el-input v-model="detailEditForm.minSellingPrice" style="width: 100%"></el-input>

            </el-form-item>
          </el-col>


        </el-row>

        <el-row>

          <el-col :span="6">
            <el-form-item label="微信" prop="minRentPrice">

              <el-input v-model="detailEditForm.minRentPrice" style="width: 100%"></el-input>

            </el-form-item>
          </el-col>


          <el-col :span="6">
            <el-form-item label="交通工具" prop="vehicleId">

              <base-select
                v-model="detailEditForm.vehicleId"
                :data="selectOpts.vehicleId"
                :props="selectPropsOther"
              >
              </base-select>

            </el-form-item>
          </el-col>

          <!--<el-col :span="6">-->
          <!--<el-form-item label="钥匙店" prop="developers">-->

          <!--<el-select v-model="detailEditForm.propertyFees" style="width: 100%">-->
          <!--<el-option label="40年" value="40"></el-option>-->
          <!--<el-option label="50年" value="50"></el-option>-->
          <!--<el-option label="70年" value="70"></el-option>-->
          <!--<el-option label="其他" value="0"></el-option>-->
          <!--</el-select>-->

          <!--</el-form-item>-->
          <!--</el-col>-->

          <el-col :span="6">
            <el-form-item label="联系地址" prop="lastTradingTime">

              <el-date-picker
                v-model="detailEditForm.lastTradingTime"
                type="date"
                editable
                value-format="timestamp"
                placeholder="选择建筑年代"
                style="width: 100%"
              >
              </el-date-picker>

            </el-form-item>
          </el-col>

          <el-col :span="6">
            <el-form-item label="客源等级" prop="mortgageId">
              <el-input v-model="detailEditForm.levelType" style="width: 100%" disabled></el-input>
            </el-form-item>
          </el-col>

        </el-row>


        <div class="dashed-line mb15 mt5"></div>

        <!-- 自定义动态表单字段 -->
        <el-form v-if="customFields && customFields.length" :model="customForm" ref="customForm" label-width="110px" size="small">

          <el-row>
            <el-col :span="6" v-for="(item, index) in customFields" :key="index">

              <!-- 文本 -->
              <el-form-item
                :rules="item.fieldRequired === 'required' ? [
                { required: true, message: '该项为必填项', trigger: 'blur' }
              ] : []"
                v-if="item.fieldType === '1'" :label="item.title" :prop="item.field"
              >
                <el-input v-model="customForm[item.field]" style="width: 100%"></el-input>
              </el-form-item>

              <!-- 数字 -->
              <el-form-item
                :rules="item.fieldRequired === 'required' ? [
                { required: true, message: '该项为必填项', trigger: 'blur' }
              ] : []"
                v-if="item.fieldType === '2'" :label="item.title" :prop="item.field"
              >
                <el-input type="number" v-model="customForm[item.field]" style="width: 100%"><i slot="suffix" style="font-size: 12px;margin-right: 5px">{{item.fieldUnit}}</i></el-input>
              </el-form-item>

              <!-- 自定义下拉 -->
              <el-form-item
                :rules="item.fieldRequired === 'required' ? [
                { required: true, message: '该项为必填项', trigger: 'change' }
              ] : []"
                v-if="item.fieldType === '3'" :label="item.title" :prop="item.field"
              >

                <base-select
                  v-model="customForm[item.field]"
                  :data="item.value"
                  :props="selectProps"
                >
                </base-select>

              </el-form-item>

            </el-col>
          </el-row>

        </el-form>

        <el-form-item label="备注" prop="custRemark">
          <el-input type="textarea" v-model="detailEditForm.custRemark" style="width: 100%"></el-input>
        </el-form-item>
      </div>

      <div class="btn-group">
        <el-button type="primary" @click="handleSubmit" :loading="loadingSubmitBtn" size="medium">保存</el-button>
        <el-button @click="handleCancel" size="medium">取消</el-button>
        <el-button type="primary" @click="handleReset" size="medium">重置</el-button>
      </div>
    </el-form>
  </div>

</template>

<script>
  import customerBaseForm from './CustomerBaseFormMixin' // 混入对象
  export default {
    mixins: [customerBaseForm]
  }
</script>

<style scoped>
  .form-wrapper {
    max-height: 515px;
    height: 515px;
    overflow-y: auto;
    overflow-x: hidden;
  }

  .el-input {
    width:110px;
  }
</style>
