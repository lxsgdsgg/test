<template>
  <div>
    <section class="operate-wrap">
      <div class="operate" :key="item.id" v-for="item in opts">
        <el-checkbox size="small" :label="item.name" :checked="item.checked" @change="item.checked=!item.checked" border></el-checkbox>
        <!--<input type="checkbox" v-model="form[item.id]">-->
      </div>
    </section>

    <div class="btn-group">
      <el-button type="primary" @click="innerHandleSubmit">确认</el-button>
      <el-button @click="innerHandleCancel">取消</el-button>
    </div>
  </div>
</template>

<script>
  export default {
    props: {
      opts: {
        type: Array,
        default: () => {
          return []
        }
      },
      type: {
        type: String,
        default: ''
      }
    },

    data () {
      return {
        form: {}
      }
    },

    methods: {
      innerHandleSubmit () {
        let optChecks = []
        this.opts.forEach(item => {
          if (item.checked) {
            optChecks.push(item.id)
          }
        })
        this.$emit('submit', optChecks, this.type)
      },

      innerHandleCancel () {
        this.$emit('cancel')
      }
    }
  }
</script>

<style scoped lang="scss">
  .btn-group {
    margin-top: 20px;
  }

  .operate-wrap {
    .operate {
      display: inline-block;
      margin-right: 15px;
      margin-bottom: 15px;
    }

  }
</style>
