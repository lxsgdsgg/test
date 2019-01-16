<template>
  <div style="min-height: calc(100vh - 165px); overflow: hidden;">
    <transition name="fade-transform" mode="out-in">
      <keep-alive v-show="!showIframe" :include="cachedViews">
        <router-view :key="key"></router-view>
      </keep-alive>

    </transition>

    <transition name="fade-transform" mode="out-in">

      <iframe-comp v-show="showIframe" :data="iframeTabData"></iframe-comp>

    </transition>
  </div>
</template>

<script>
  import IframeComp from './IframeComp'
  import { mapGetters } from 'vuex'

  export default {
    name: 'AppMain',
    components: {IframeComp},
    computed: {
      ...mapGetters([
        'cachedViews',
        'showIframe',
        'iframeTabData'
      ]),

      key () {
        return this.$route.fullPath
      },

      currentSrc () {
        return this.$route.meta.src || ''
      }
    }
  }
</script>

<style lang="scss" scoped>
</style>
