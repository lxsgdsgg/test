<!-- 更多条件 -->
<template>
  <div :id="dropWrapId">
    <el-input
      placeholder="更多"
      :suffix-icon="isFocus ? 'el-icon-caret-top' : 'el-icon-caret-bottom'"
      readonly="readonly"
      :value="inputVal"
    >
    </el-input>

    <transition name="el-zoom-in-top">

      <div v-show="isFocus" class="dropdown el-select-dropdown el-popper" x-placement="bottom-start" style="width: 255%">
        <div class="el-scrollbar">

          <div class="input-wrap">
            <el-form v-if="selectOpts" :model="form" ref="form" size="mini">

              <el-row>
                <el-col :span="8">

                  <el-form-item prop="houseUsesId">

                    <base-select
                      placeholder="用途"
                      v-model="form.houseUsesId"
                      :data="selectOpts.houseUsesId"
                      :props="selectProps"
                    >
                    </base-select>

                  </el-form-item>
                </el-col>

                <el-col :span="8">
                  <el-form-item prop="payTypeId">

                    <base-select
                      placeholder="付款方式"
                      v-model="form.payTypeId"
                      :data="selectOpts['sellPayType']"
                      :props="selectProps"
                    >
                    </base-select>

                  </el-form-item>
                </el-col>

                <el-col :span="8">
                  <el-form-item prop="floor">

                    <base-section-select v-model="form.floor" :data="regionValue.floor" placeholder="楼层" description="楼"></base-section-select>

                  </el-form-item>
                </el-col>

              </el-row>

              <el-row>
                <el-col :span="8">

                  <el-form-item prop="room">

                    <base-section-select v-model="form.room" :data="regionValue.space" placeholder="几室" description="室"></base-section-select>

                  </el-form-item>
                </el-col>

                <el-col :span="8">
                  <el-form-item prop="hall">

                    <base-section-select v-model="form.hall" :data="regionValue.space" placeholder="几厅" description="厅"></base-section-select>

                  </el-form-item>
                </el-col>

                <el-col :span="8">
                  <el-form-item prop="toilet">

                    <base-section-select v-model="form.toilet" :data="regionValue.space" placeholder="几卫" description="卫"></base-section-select>

                  </el-form-item>
                </el-col>

              </el-row>

              <el-row>
                <el-col :span="8">
                  <el-form-item prop="dateType">

                    <base-select
                      placeholder="日期类型"
                      v-model="form.dateType"
                      :data="selectOpts.dateTypes"
                      :props="selectProps"
                    >
                    </base-select>

                  </el-form-item>
                </el-col>

                <el-col :span="8">
                  <el-form-item prop="beginDate">
                    <el-date-picker
                      v-model="form.beginDate"
                      type="date"
                      editable
                      value-format="yyyy-MM-dd"
                      placeholder="开始日期"
                      style="width: 100%"
                    >
                    </el-date-picker>
                  </el-form-item>
                </el-col>

                <el-col :span="8">
                  <el-form-item prop="endDate">
                    <el-date-picker
                      v-model="form.endDate"
                      type="date"
                      editable
                      value-format="yyyy-MM-dd"
                      placeholder="结束日期"
                      style="width: 100%"
                    >
                    </el-date-picker>
                  </el-form-item>
                </el-col>
              </el-row>

              <el-row>
                <el-col :span="8">
                  <el-form-item prop="orientationId">

                    <base-select
                      placeholder="朝向"
                      v-model="form.orientationId"
                      :data="selectOpts.orientationId"
                      :props="selectProps"
                      :multiple="true"
                      :collapseTags="true"
                    >
                    </base-select>

                  </el-form-item>
                </el-col>

                <el-col :span="8">
                  <el-form-item prop="decorationId">

                    <base-select
                      placeholder="装修"
                      v-model="form.decorationId"
                      :data="selectOpts.decorationId"
                      :props="selectProps"
                      :multiple="true"
                      :collapseTags="true"
                    >
                    </base-select>

                  </el-form-item>
                </el-col>

                <el-col :span="8">
                  <el-form-item prop="matchingIds">

                    <base-select
                      placeholder="配套"
                      v-model="form.matchingIds"
                      :data="selectOpts.matchingIds"
                      :props="selectProps"
                      :multiple="true"
                      :collapseTags="true"
                    >
                    </base-select>

                  </el-form-item>
                </el-col>
              </el-row>

              <el-row>
                <el-col :span="8">
                  <el-form-item prop="houseTypeId">

                    <base-select
                      placeholder="房屋类型"
                      v-model="form.houseTypeId"
                      :data="selectOpts.houseTypeId"
                      :props="selectProps"
                      :multiple="true"
                      :collapseTags="true"
                    >
                    </base-select>

                  </el-form-item>
                </el-col>

                <el-col :span="8">
                  <el-form-item prop="synchronType">

                    <el-select v-model="form.synchronType" clearable placeholder="外网同步">
                      <el-option label="是" value="1"></el-option>
                      <el-option label="否" value="0"></el-option>
                    </el-select>

                  </el-form-item>
                </el-col>

                <el-col :span="8">
                  <el-form-item prop="isDeposit">

                    <el-select v-model="form.isDeposit" clearable placeholder="下定">
                      <el-option label="是" value="1"></el-option>
                      <el-option label="否" value="0"></el-option>
                    </el-select>

                  </el-form-item>
                </el-col>

              </el-row>

              <el-row>
                <el-col :span="8">
                  <el-form-item prop="labeld">

                    <base-select
                      placeholder="房源标签"
                      v-model="form.labeld"
                      :data="selectOpts.labeld"
                      :props="selectProps"
                      :multiple="true"
                      :collapseTags="true"
                    >
                    </base-select>

                  </el-form-item>
                </el-col>

                <el-col :span="8">
                  <el-form-item prop="pictureType">

                    <el-select v-model="form.pictureType" clearable placeholder="图片">
                      <el-option label="是" value="1"></el-option>
                      <el-option label="否" value="0"></el-option>
                    </el-select>

                  </el-form-item>
                </el-col>

                <el-col :span="8">
                  <el-form-item prop="description">

                    <el-select v-model="form.description" clearable placeholder="房源描述">
                      <el-option label="是" value="1"></el-option>
                      <el-option label="否" value="0"></el-option>
                    </el-select>

                  </el-form-item>
                </el-col>

              </el-row>

              <el-row>
                <el-col :span="8">
                  <el-form-item prop="propertyTypeId">

                    <base-select
                      placeholder="产权性质"
                      v-model="form.propertyTypeId"
                      :data="selectOpts.propertyTypeId"
                      :props="selectProps"
                      :multiple="true"
                      :collapseTags="true"
                    >
                    </base-select>

                  </el-form-item>
                </el-col>

                <el-col :span="8">
                  <el-form-item prop="propRightsLenId">

                    <base-select
                      placeholder="产权年限"
                      v-model="form.propRightsLenId"
                      :data="selectOpts.propRightsLenId"
                      :props="selectProps"
                    >
                    </base-select>

                  </el-form-item>
                </el-col>

                <el-col :span="8">
                  <el-form-item prop="structureId">

                    <base-select
                      placeholder="建筑结构"
                      v-model="form.structureId"
                      :data="selectOpts.structureId"
                      :props="selectProps"
                      :multiple="true"
                      :collapseTags="true"
                    >
                    </base-select>

                  </el-form-item>
                </el-col>

              </el-row>

              <el-row>
                <el-col :span="8">
                  <el-form-item prop="resourceTypeId">

                    <base-select
                      placeholder="来源"
                      v-model="form.resourceTypeId"
                      :data="selectOpts.resourceTypeId"
                      :props="selectProps"
                      :multiple="true"
                      :collapseTags="true"
                    >
                    </base-select>

                  </el-form-item>
                </el-col>

                <el-col :span="8">
                  <el-form-item prop="actualityId">

                    <base-select
                      placeholder="现状"
                      v-model="form.actualityId"
                      :data="selectOpts.actualityId"
                      :props="selectProps"
                    >
                    </base-select>

                  </el-form-item>
                </el-col>

                <el-col :span="8">
                  <el-form-item prop="housingYearsId">

                    <base-select
                      placeholder="交易年限"
                      v-model="form.housingYearsId"
                      :data="selectOpts.housingYearsId"
                      :props="selectProps"
                    >
                    </base-select>

                  </el-form-item>
                </el-col>
              </el-row>

              <el-row>
                <el-col :span="8">
                  <el-form-item prop="panorama">

                    <el-select v-model="form.panorama" clearable placeholder="全景看房">
                      <el-option label="是" value="1"></el-option>
                      <el-option label="否" value="0"></el-option>
                    </el-select>

                  </el-form-item>
                </el-col>

                <el-col :span="8">
                  <el-form-item prop="levelType">

                    <base-select
                      placeholder="房源等级"
                      v-model="form.levelType"
                      :data="selectOpts.levelType"
                      :props="selectProps"
                    >
                    </base-select>

                  </el-form-item>
                </el-col>

                <el-col :span="8">
                  <el-form-item prop="buildDates">

                    <base-section-select v-model="form.buildDates" :data="regionValue.buildDte" placeholder="房龄" description="年"></base-section-select>

                  </el-form-item>
                </el-col>
              </el-row>

              <el-row class="text-center">
                <el-form-item class="margin-b-none">
                  <el-button type="primary" @click="handleConfirm">确定</el-button>
                  <el-button @click="handleReset">重置</el-button>
                </el-form-item>
              </el-row>

            </el-form>
          </div>

        </div>
        <div class="popper__arrow" style="left: 35px;"></div>
      </div>

    </transition>
  </div>
</template>

<script>
  import BaseSelect from '@/components/BaseSelect'
  import BaseSectionSelect from '@/components/BaseSectionSelect'
  import * as consts from './consts'
  import {getBaseInfoSelectOpts} from '@/request/house/houseUsed'

  // 室 厅 卫 区间数据
  const spaceRegionValue = {
    min: [
      '0',
      '1',
      '2',
      '3',
      '4'
    ],
    max: [
      '3',
      '4',
      '5',
      '6',
      '不限'
    ]
  }

  // 楼层 区间数据
  const floorRegionValue = {
    min: [
      '0',
      '1',
      '2',
      '3',
      '4',
      '5',
      '6',
      '7'
    ],
    max: [
      '3',
      '4',
      '5',
      '6',
      '7',
      '8',
      '9',
      '不限'
    ]
  }

  // 房龄 区间数据
  const buildDateRegionValue = {
    min: [
      '0',
      '2005',
      '2006',
      '2007',
      '2008',
      '2009',
      '2010',
      '2011'
    ],
    max: [
      '2007',
      '2008',
      '2009',
      '2010',
      '2011',
      '2012',
      '2013',
      '不限'
    ]
  }
    const room = {
    min: [
      '0',
      '1',
      '2',
      '3',
      '4',
      '5',
      '6',
      '7'
    ],
    max: [
      '3',
      '4',
      '5',
      '6',
      '7',
      '8',
      '9',
      '不限'
    ]
  }
    const price = {
    min: [
      '0',
      '1',
      '2',
      '3',
      '4',
      '5',
      '6',
      '7'
    ],
    max: [
      '3',
      '4',
      '5',
      '6',
      '7',
      '8',
      '9',
      '不限'
    ]
  }
    const floorCount = {
    min: [
      '0',
      '1',
      '2',
      '3',
      '4',
      '5',
      '6',
      '7'
    ],
    max: [
      '3',
      '4',
      '5',
      '6',
      '7',
      '8',
      '9',
      '不限'
    ]
  }

  export default {
    components: {BaseSelect, BaseSectionSelect},

    props: {
      placeholder: {
        type: String,
        default: ''
      }
    },

    data () {
      return {
        form: {
          houseUsesId: '',
          payTypeId: '',
          dateType: '',
          beginDate: '',
          endDate: '',

          orientationId: [],
          decorationId: [],
          resourceTypeId: [],
          propertyTypeId: [],
          structureId: [],
          houseTypeId: [],
          matchingIds: [],
          labeld: [],

          levelType: '',
          actualityId: '',
          housingYearsId: '',
          synchronType: '',
          isDeposit: '',
          pictureType: '',
          description: '',
          propRightsLenId: '',
          panorama: '',

          floor: '',
          room: '',
          hall: '',
          toilet: '',
          buildDates: ''
        },
        inputVal: '',
        selectOpts: null,
        selectProps: {
          label: 'fieldValue', // 指定选项的值绑定为下拉框的label属性
          value: 'fieldCode' // 指定选项的值绑定为下拉框的Value属性
        },
        isFocus: false,
        handleConfirmed: false,
        dropWrapId: 'sectionWrap' + new Date().getTime(),
        regionValue: { // 区间下拉数据
          space: spaceRegionValue,
          floor: floorRegionValue,
          buildDte: buildDateRegionValue
        }
      }
    },

    methods: {
      handleConfirm () {
        let formInfo = {...this.form}
        let fieldSize = 0

        // 多选参数专为字符串，英文逗号分隔
        formInfo.orientationId = formInfo.orientationId.join()
        formInfo.decorationId = formInfo.decorationId.join()
        formInfo.resourceTypeId = formInfo.resourceTypeId.join()
        formInfo.propertyTypeId = formInfo.propertyTypeId.join()
        formInfo.structureId = formInfo.structureId.join()
        formInfo.houseTypeId = formInfo.houseTypeId.join()
        formInfo.matchingIds = formInfo.matchingIds.join()
        formInfo.labeld = formInfo.labeld.join()

        Object.keys(formInfo).forEach(key => {
          if (!formInfo[key]) {
            delete formInfo[key]
          } else {
            fieldSize += 1
          }
        })

        this.inputVal = `更多(${fieldSize})`

        this.$emit('handleConfirmOrReset', formInfo)
        this.handleConfirmed = true
      },

      handleReset () {
        this.$refs['form'] && this.$refs['form'].resetFields()
        this.inputVal = ''
        this.$emit('handleConfirmOrReset', null)
      },

      _fetchData () {
        getBaseInfoSelectOpts({param: 'house'}).then(res => {
          this.selectOpts = res.data || []
          // // 前端固定写死的下拉数据
          this.selectOpts.houseUsesId = consts.useTypes // 用途
          this.selectOpts.dateTypes = consts.dateTypes // 用途
        })
      }
    },

    mounted () {
      this._fetchData()

      this.$nextTick(() => {
        document.addEventListener('click', (event) => {
          let dropWrap =  document.getElementById(this.dropWrapId)
          if (dropWrap && dropWrap.contains !== null) {

            if (this.handleConfirmed) {
              this.isFocus = false
              this.handleConfirmed = false
              return false
            }

            this.isFocus =  dropWrap.contains(event.target)
          }
        })
      })
    },
  }
</script>

<style scoped lang="scss">
  .input-wrap {
    padding: 10px;
  }
</style>
