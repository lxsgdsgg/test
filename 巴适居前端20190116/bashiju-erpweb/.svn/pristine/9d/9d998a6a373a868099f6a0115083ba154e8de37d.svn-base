<!--
  模糊查询输入建议
-->

<template>
  <div>
    <el-autocomplete
      style="width: 100%"
      popper-class="my-autocomplete"
      v-model="inputVal"
      :fetch-suggestions="querySearch"
      :placeholder="placeholder"
      :trigger-on-focus="false"
      @select="handleSelect"
      @input="handleInput"
      clearable
    >

      <i
        class="el-icon-edit el-input__icon"
        slot="suffix">
      </i>

      <template v-if="prependText" slot="prepend">{{prependText}}</template>

      <template slot-scope="{ item }">
        <i class="el-icon-search search-icon"></i>
        <span class="label mr10">{{ item.label }}</span>
        <span class="queryStr">{{ item.value }}</span>
      </template>

    </el-autocomplete>
  </div>
</template>

<script>
  export default {
    name: 'BaseVagueAutocomplete',

    props: {
      dataProps: {
        type: [Array, String],
        default () {
          return []
        },
        required: true
      },
      placeholder: {
        type: String,
        default: '请输入查询内容'
      },
      value: {
        default: ''
      },
    },

    model: {
      prop: 'value',
      event: 'input'
    },

    data () {
      return {
        inputVal: this.value,
        prependText: ''
      }
    },

    methods: {
      querySearch(queryString, cb) {
        let results = this.dataProps.map(item => {
          return {
            label: item.label,
            value: queryString,
            type: item.type,
          }
        })

        // 调用 callback 返回建议列表的数据
        cb(results)
      },

      // 点击输入建议项
      handleSelect(item) {
        this.prependText = item.label
        // this.inputVal = `${item.label}: ${this.inputVal}`
        this.$emit('select', item)
      },

      handleInput (val) {
        this.$emit('input', val)
      }
    },

    watch: {
      value (newValue) {
        this.inputVal = newValue
        if (!newValue) {
          this.prependText = ''
        }
      }
    },
  }
</script>

<style scoped lang="scss">
  .my-autocomplete {
    li {
      line-height: normal;
      padding: 7px;
      display: flex;
      font-size: 12px;

      .search-icon {
        font-size: 12px;
      }

      .label {
        font-size: 12px;
        text-overflow: ellipsis;
        overflow: hidden;
      }
      .queryStr {
        font-size: 12px;
        color: #b4b4b4;
      }

      .highlighted .queryStr {
        color: #ddd;
      }
    }
  }
</style>
