<template>

  <div style="height: calc(100vh - 165px); background-color: #fff; overflow: hidden;">

    <template v-for="(item, index) in data">

      <iframe
        v-if="item.meta.src"
        :src="item.meta.src"
        :key="index"
        v-show="currentSrc === item.meta.src"
        style="width: 100%; height: 100%;"
      >
      </iframe>

    </template>

  </div>

</template>

<script>
  export default {
    props: {
      data: {
        type: Array,
        default () {
          return []
        }
      },
    },

    computed: {
      currentSrc () {
        return this.$route.meta.src || ''
      }
    }
  }
</script>

<style scoped lang="scss">

</style>
