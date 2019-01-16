<!-- 住宅 -->

<template>
  <div v-loading="loadingView" style="height: 570px">
    <el-form v-if="selectOpts" :model="detailEditForm" ref="detailEditForm" :rules="rules" label-width="110px" size="small">

      <div class="form-wrapper">
        <el-row>
          <el-col :span="12">
            <el-form-item label="客户姓名" required class="margin-b-none">
              <el-form-item style="display: inline-block;" prop="custName">
                <el-input v-model="detailEditForm.custName"></el-input>
              </el-form-item>
              <el-form-item style="display: inline-block;" prop="relateTypeId">
                <base-select
                  style="width: 150px"
                  v-model="detailEditForm.relateTypeId"
                  :data="selectOpts.custCallType"
                  :props="selectProps"
                >
                </base-select>
              </el-form-item>
            </el-form-item>
          </el-col>

          <el-col :span="6">

            <el-form-item prop="areaCodes" label="需求区划">
              <community-selector
                style="width: 180px"
                :changeOnSelect="false"
                v-model="detailEditForm.areaCodes"
                :url="areasUrl"
                :props="cascaderProps"
                :dataProps="cascaderDataProps"
                placeholder="选择小区"
                @change="handleChange"
              >
              </community-selector>
            </el-form-item>

          </el-col>

          <el-col :span="6">
            <el-form-item label="房屋用途:" prop="houseUsesIds">

              <base-select
                v-model="detailEditForm.houseUsesIds"
                :data="selectOpts.houseUsesIds"
                :props="selectPropsOther"
                :multiple="true"
                collapse-tags
              >
              </base-select>

            </el-form-item>
          </el-col>


        </el-row>



        </el-row>

        <el-row>



          <el-col :span="6">

            <el-form-item label="需求户型:" prop="room">
              <base-section-select v-model="detailEditForm.room" placeholder="需求户型"  :data="regionValue.room" description="室"></base-section-select>
            </el-form-item>
          </el-col>

          <el-col :span="6">
            <el-form-item label="需求价格:" prop="price">
              <base-section-select v-model="detailEditForm.price" :data="regionValue.price"   placeholder="需求价格" description="万元" ></base-section-select>
            </el-form-item>
          </el-col>


          <el-col :span="6">

            <el-form-item label="需求面积:" prop="space">
              <base-section-select v-model="detailEditForm.space" placeholder="需求面积" :data="regionValue.space"   description="平米"></base-section-select>
            </el-form-item>

            </el-form-item>

          </el-col>

          <el-col :span="6">
            <el-form-item label-width="40px"  class="margin-b-none">
              <el-checkbox v-model="detailEditForm.isUrgent">急切</el-checkbox>
              <el-checkbox v-model="detailEditForm.isFullAmount">全款</el-checkbox>
            </el-form-item>
          </el-col>


        </el-row>

        <el-row>


          <el-col :span="6">
            <el-form-item label="朝向：" prop="orientationId">

              <base-select
                v-model="detailEditForm.orientationId"
                :data="selectOpts.orientationId"
                :props="selectPropsOther"
                :multiple="true"
                collapse-tags
              >
              </base-select>

            </el-form-item>
          </el-col>




          <el-col :span ="6">
            <el-form-item label="需求楼层:" prop="floorCount">
              <base-section-select v-model="detailEditForm.floorCount" placeholder="需求楼层":data="regionValue.floor"   description="层"></base-section-select>
            </el-form-item>
          </el-col>

          <el-col :span ="6">
            <el-form-item label="需求房龄:" prop="buildDates">
              <base-section-select v-model="detailEditForm.buildDates" :data="regionValue.buildingYears" placeholder="需求房龄" description="年"></base-section-select>
            </el-form-item>
          </el-col>

          <el-col :span="6">
            <el-form-item label="装修:" prop="decorationIds">

              <base-select
                v-model="detailEditForm.decorationIds"
                :data="selectOpts.decorationIds"
                :props="selectPropsOther"
                :multiple="true"
                collapse-tags
              >
              </base-select>

            </el-form-item>
          </el-col>



        </el-row>

        <el-row>


          <el-col :span="10">
            <el-form-item label="配套:" prop="matchingIds">

              <base-select
                v-model="detailEditForm.matchingIds"
                :data="selectOpts.matchingIds"
                :props="selectPropsOther"
                :multiple="true"
                collapse-tags
              >
              </base-select>

            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="需求原因：" prop="reason">
              <el-input type="text" v-model="detailEditForm.reason" style="width: 100%"></el-input>
            </el-form-item>
          </el-col>


          <el-col :span="6">

            <el-form-item label="推荐标签：" prop="labeld">
              <base-select
                v-model="detailEditForm.labeld"
                :data="selectOpts.labeld"
                :props="selectPropsLabel"
                :multiple="true"
                collapse-tags
              >
              </base-select>
            </el-form-item>
          </el-col>

          <el-col :span="6">

            <el-form-item label="房屋类型" prop="houseTypeIds">

              <base-select
                v-model="detailEditForm.houseTypeIds"
                :data="selectOpts.houseTypeIds"
                :props="selectPropsOther"
                :multiple="true"
                collapse-tags
              >
              </base-select>

            </el-form-item>

          </el-col>


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
            <el-form-item label="客户来源" prop="sourceTypeId">

              <base-select
                v-model="detailEditForm.sourceTypeId"
                :data="selectOpts.resourceTypeId"
                :props="selectPropsOther"
              >
              </base-select>

            </el-form-item>
          </el-col>

        </el-row>


        <el-row>


          <el-col :span="6">
            <el-form-item label="消费理念" prop="consumptIdeaId">

              <base-select
                v-model="detailEditForm.consumptIdeaId"
                :data="selectOpts.consumptIdeaId"
                :props="selectPropsOther"
                :multiple="true"
                collapse-tags
              >
              </base-select>

            </el-form-item>
          </el-col>

          <el-col :span="6">
            <el-form-item label=" 国籍" prop="censusRegisterId">

              <base-select
                v-model="detailEditForm.censusRegisterId"
                :data="selectOpts.censusRegisterId"
                :props="selectPropsOther"
              >
              </base-select>

            </el-form-item>
          </el-col>

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
            <el-form-item label="证件号码" prop="IDCard">

              <el-input v-model="detailEditForm.IDCard" style="width: 100%"></el-input>

            </el-form-item>
          </el-col>

        </el-row>

        <el-row>

          <el-col :span="6">
            <el-form-item label="邮箱" prop="email">

              <el-input type="text" v-model="detailEditForm.email" style="width: 100%"></el-input>


            </el-form-item>
          </el-col>

          <el-col :span="6">
            <el-form-item label="QQ" prop="qqNum">

              <el-input v-model="detailEditForm.qqNum" style="width: 100%"></el-input>

            </el-form-item>
          </el-col>

          <el-col :span="6">
            <el-form-item label="微信" prop="wxNum">

              <el-input v-model="detailEditForm.wxNum" style="width: 100%"></el-input>

            </el-form-item>
          </el-col>


          <el-col :span="6">
            <el-form-item label="联系地址" prop="contactAddress">

              <el-input type="text" v-model="detailEditForm.contactAddress" style="width: 100%" disabled></el-input>


            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
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

          <el-col :span="6">
            <el-form-item label="客源等级" prop="levelType">
              <el-input v-model="detailEditForm.levelType" style="width: 100%" disabled></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="6">
            <el-form-item>
              <el-checkbox-group v-model="detailEditForm.housePropType">
                <el-checkbox label="one">一手</el-checkbox>
                <el-checkbox label="two">二手</el-checkbox>
              </el-checkbox-group>
            </el-form-item>
          </el-col>


        </el-row>


        <el-row>


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
        <el-button type="primary" @click="handleLastStep" size="medium">上一步</el-button>
        <el-button type="primary" @click="handleSubmit" :loading="loadingSubmitBtn" size="medium">保存</el-button>
        <el-button @click="handleCancel" size="medium">取消</el-button>
        <!--<el-button type="primary" @click="handleReset" size="medium">重置</el-button>-->
      </div>
    </el-form>
  </div>

</template>

<script>


  import houseBaseForm from './HouseBaseFormMixin' // 混入对象
  import {getBaseInfoSelectOpts, getHouseLabel, updateHousing} from '@/request/house/houseUsed'
  import {getRegionValue} from '@/request/app'

  export default {
    mixins: [houseBaseForm],
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
