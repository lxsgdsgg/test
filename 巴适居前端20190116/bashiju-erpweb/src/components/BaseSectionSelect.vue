<!-- 区间下拉 -->
<template>
  <div :id="sectionWrapId">
    <el-input
      :placeholder="placeholder"
      readonly="readonly"
      :value="inputVal"
      @mouseenter.native="handleMouseenter"
      @mouseleave.native="clearable = false"
    >
      <i v-if="clearable" @click="clearVal" slot="suffix" class="el-input__icon el-icon-circle-close clearable"></i>
      <i v-else style="font-size: 14px;" slot="suffix" :class="isFocus ? 'el-icon-caret-top' : 'el-icon-caret-bottom'" class="el-input__icon"></i>
    </el-input>

    <transition name="el-zoom-in-top">
      <div v-show="isFocus" :style="{width: dropdownWidth}" class="dropdown el-select-dropdown el-popper" x-placement="bottom-start">
        <div class="el-scrollbar">

          <div class="input-wrap">
            <el-row>
              <el-col :span="10">
                <el-input @focus="isRight = false" placeholder="0" v-model="leftVal" type="number" min="0"></el-input>
              </el-col>

              <el-col :span="4" class="text-center">
                --
              </el-col>

              <el-col :span="10">
                <el-input @focus="isRight = true" placeholder="0" v-model="rightVal" type="number" min="0"></el-input>
              </el-col>

            </el-row>
          </div>

          <div class="el-select-dropdown__wrap el-scrollbar__wrap" style="margin-bottom: -15px; margin-right: -15px;">

            <ul v-show="!isRight" class="el-scrollbar__view el-select-dropdown__list">
              <li
                v-for="item in min"
                @click="leftClick(item)"
                class="el-select-dropdown__item"
              >
                <span>{{item}}</span>
              </li>
            </ul>

            <ul v-show="isRight" class="el-scrollbar__view el-select-dropdown__list">
              <li
                v-for="item in max"
                @click="rightClick(item)"
                class="el-select-dropdown__item"
                :style="{textAlign: isRight ? 'right' : ''}"
              >
                <span>{{item}}</span>
              </li>
            </ul>

          </div>

        </div><!---->
        <div class="popper__arrow" style="left: 35px;"></div>
      </div>
    </transition>
  </div>
</template>

<script>

  export default {
    name: 'BaseSectionSelect',

    model: {
      prop: 'value',
      event: 'change'
    },

    props: {
      value: {
        default: ''
      },

      placeholder: {
        type: String,
        default: '区间'
      },

      description: { // 描述后缀
        type: String,
        default: ''
      },

      data: { // 下拉数据 格式为 { min: [], max: [] }
        type: Object,
        required: true,
        default () {
          return {}
        }
      },

      dropdownWidth: {
        type: String,
        default: '120%'
      }
    },

    data () {
      return {
        isFocus: false,
        sectionWrapId: 'sectionWrap' + new Date().getTime(),
        leftVal: '',
        rightVal: '',
        isRight: false,
        rightClicked: false,
        min: this.data.min || [],
        max: this.data.max || [],
        clearable: false
      }
    },

    methods: {
      leftClick (val) {
        this.isRight = true
        this.leftVal = val
      },

      rightClick (val) {
        this.isRight = false
        this.rightVal = val

        this.rightClicked = true
      },

      handleMouseenter () {
        if (this.inputVal) {
          this.clearable = true
        }
      },

      // 清空
      clearVal () {
        this.leftVal = ''
        this.rightVal = ''

        // 阻止弹框弹出
        this.rightClicked = true
        this.clearable = false
      },

      setVal (val) {
        if (typeof val === 'object') {
          let minVal = val.min || ''
          let maxVal = val.max || ''
          this.leftVal = minVal
          this.rightVal = maxVal
        }
      },

      setInputVal () {

      }
    },

    mounted () {
      this.$nextTick(() => {
        document.addEventListener('click', (event) => {
          let sectionWrap =  document.getElementById(this.sectionWrapId)
          if (sectionWrap && sectionWrap.contains !== null) {

            if (this.rightClicked) {
              this.isFocus = false
              this.rightClicked = false
              return false
            }

            this.isFocus = sectionWrap.contains(event.target)

          }
        })
      })

      if (this.value) {
        this.setVal(this.value)
      }
    },

    computed: {
      inputVal () {
        let str = ''
        if (this.leftVal) {
          if (this.leftVal !== '0') str = `${this.leftVal}${this.description}以上`

          // 过滤区间最大值
          let temp = []
          this.max.forEach(item => {
            if (Number(item) > Number(this.leftVal)) {
              temp.push(item)
            }
          })
          temp.push('不限')
          this.max = temp

        }

        if (this.rightVal && this.rightVal !== '0') {
          if (Number(this.leftVal) > Number(this.rightVal)) {
            this.rightVal = this.max[0]
          }

          str = `${this.leftVal} - ${this.rightVal}${this.description}`

          if (this.leftVal === '0' || this.leftVal === '') {
            str = `${this.rightVal}${this.description}以下`
          }

          if (this.rightVal === '不限') {
            this.rightVal = '0'
            str = `${this.leftVal}${this.description}以上`
          }

          if (this.rightVal === ('不限') && this.leftVal === '0') {
            this.leftVal = ''
            this.rightVal = ''
            str = ''
          }

          // 最大值和最小值都为0的情况
          if (this.rightVal === '0' && this.leftVal === '0') {
            this.leftVal = ''
            this.rightVal = ''
            str = ''
          }

        }

        if (this.leftVal || this.rightVal) {
          let val = {
            min: this.leftVal || '0', // 下限为0
            max: this.rightVal || '0' // 上限为0
          }
          this.$emit('change', val)
        } else {
          this.$emit('change', '')
        }

        return str
      }
    },

    watch: {
      value (newValue) {
        if (newValue) {
          this.setVal(newValue)
        } else {
          this.leftVal = ''
          this.rightVal = ''
          this.isRight = false
          this.rightClicked = false
        }
      },

      data (newVal) {
        this.min = newVal.min || []
        this.max = newVal.max || []
      }
    }
  }
</script>

<style lang="scss" scoped>
  .dropdown {
    min-width: 150px;
    transform-origin: center top 0;

    &.el-select-dropdown {
      z-index: 999;
    }
  }

  .input-wrap {
    padding: 5px 10px;
  }

  .clearable {
    font-size: 14px;
    transition: all 0.3s;
    cursor: pointer;
  }
  .clearable:hover {
    color: #82848a;
  }

</style>
