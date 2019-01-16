<template>
  <div class="dialog-container">
    <el-dialog
      :width="width"
      :title="title"
      :visible.sync="visible"
      :close-on-click-modal="closeOnModal"
      @close="$emit('update:show', false)"
      :show="show">
      <slot></slot>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'BaseDialog',
  props: {
    show: {
      type: Boolean,
      default: false
    },
    width: {
      type: String,
      default: '600px'
    },
    closeOnModal: {
      type: Boolean,
      default: false
    },
    title: {
      type: String,
      default: 'Dialog'
    }
  },
  data () {
    return {
      visible: this.show
    }
  },
  watch: {
    show () {
      this.visible = this.show
    }
  }
}
</script>

<style scoped>

</style>
