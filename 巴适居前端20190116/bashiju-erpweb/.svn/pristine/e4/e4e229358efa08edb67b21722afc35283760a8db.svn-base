<template>
  <div class="tags-view-container">
    <div @click="handleMoveTo(190)" class="btn-con prev">
      <i class="el-icon-d-arrow-left"></i>
    </div>

    <div @click="handleMoveTo(-190)" class="btn-con next">
      <i class="el-icon-d-arrow-right"></i>
    </div>

    <scroll-pane class='tags-view-wrapper' ref='scrollPane'>
      <router-link
        ref='tag' :class="isActive(tag)?'active':''"
        v-for="tag in Array.from(visitedViews)"
        :to="{ path: tag.fullPath, query: tag.query, fullPath: tag.fullPath }"
        class="tags-view-item"
        :key="tag.fullPath"
        tag="span"
        @click.middle.native="closeSelectedTag(tag)"
        @contextmenu.prevent.native="(tag.name !== 'dashboard') && openMenu(tag, $event)"
      >
        {{tag.title || tag.meta.title}}
        <span v-if="tag.name !== 'dashboard'" class='el-icon-close' @click.prevent.stop='closeSelectedTag(tag)'></span>
      </router-link>
    </scroll-pane>
    <ul class='contextmenu' v-show="visible" :style="{left:left+'px',top:top+'px'}">
      <li @click="refreshSelectedTag(selectedTag)">刷新</li>
      <li @click="closeSelectedTag(selectedTag)">关闭</li>
      <li @click="closeOthersTags">关闭其他</li>
      <li @click="closeAllTags">关闭所有</li>
    </ul>
  </div>
</template>

<script>
import ScrollPane from '@/components/BaseScrollPane'

export default {
  name: 'tagsView',
  components: {ScrollPane},
  data () {
    return {
      visible: false,
      top: 0,
      left: 0,
      selectedTag: {}
    }
  },
  computed: {
    visitedViews () {
      return this.$store.state.tagsView.visitedViews
    }
  },
  watch: {
    $route () {
      this.addViewTags()
      this.moveToCurrentTag()
    },
    visible (value) {
      if (value) {
        document.body.addEventListener('click', this.closeMenu)
      } else {
        document.body.removeEventListener('click', this.closeMenu)
      }
    }
  },
  mounted () {
    this.addViewTags()
  },
  methods: {
    isActive(route) {
      return route.path === this.$route.path
    },
    addViewTags () {
      const { name } = this.$route
      this.$store.dispatch('addIframeTabData', this.$route)
      if (name) {
        this.$store.dispatch('addView', this.$route)
      }
      return false
    },
    moveToCurrentTag() {
      const tags = this.$refs.tag
      this.$nextTick(() => {
        for (const tag of tags) {
          if (tag.to.path === this.$route.fullPath) {

            this.$refs.scrollPane.moveToTarget(tag)

            if (tag.to.fullPath !== this.$route.fullPath) {
              this.$store.dispatch('updateVisitedView', this.$route)
            }

            break
          }
        }
      })
    },
    handleMoveTo (num) {
      this.$refs.scrollPane.moveTo(num)
    },
    refreshSelectedTag(view) {
      const { showIframe }  = view.meta

      if (showIframe) {
        this.$store.dispatch('refreshSelectedIframe', view)
        return
      }

      this.$store.dispatch('delCachedView', view).then(() => {
        const { fullPath} = view
        this.$nextTick(() => {
          this.$router.replace({
            path: '/redirect' + fullPath
          })
        })
      })

    },
    closeSelectedTag (view) {
      this.$store.dispatch('delView', view).then(({ visitedViews }) => {
        if (this.isActive(view)) {
          const latestView = visitedViews.slice(-1)[0]
          if (latestView) {
            this.$router.push(latestView)
          } else {
            this.$router.push('/')
          }
        }
      })
    },
    closeOthersTags () {
      this.$router.push(this.selectedTag)
      this.$store.dispatch('delOthersViews', this.selectedTag).then(() => {
        this.moveToCurrentTag()
      })
    },
    closeAllTags () {
      this.$store.dispatch('delAllViews')
      this.$router.push('/')
    },
    openMenu (tag, e) {
      this.visible = true
      this.selectedTag = tag
      const offsetLeft = this.$el.getBoundingClientRect().left
      this.left = e.clientX - offsetLeft + 15
      this.top = e.offsetY + 10
    },
    closeMenu () {
      this.visible = false
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
  .tags-view-container {
    position: relative;
    box-shadow: 0 1px 3px 0 rgba(0, 0, 0, .12), 0 0 3px 0 rgba(0, 0, 0, .04);
    background: #303133;
    height: 45px;
    line-height: 45px;

    .tags-view-wrapper {
      position: absolute;
      top: 0;
      left: 35px;
      right: 35px;
      height: 100%;

      .tags-view-item {
        display: inline-block;
        position: relative;
        height: 26px;
        line-height: 26px;
        border: 1px solid #d8dce5;
        color: #495060;
        background: #fff;
        padding: 0 8px;
        font-size: 12px;
        margin-left: 10px;
        margin-top: 4px;
        cursor: pointer;
        &:first-of-type {
          margin-left: 5px;
        }
        &.active {
          background-color: #42b983;
          color: #fff;
          border-color: #42b983;
          &::before {
            content: '';
            background: #fff;
            display: inline-block;
            width: 8px;
            height: 8px;
            border-radius: 50%;
            position: relative;
            margin-right: 2px;
          }
        }
      }
    }
    .contextmenu {
      margin: 0;
      background: #fff;
      z-index: 999999999;
      position: absolute;
      list-style-type: none;
      padding: 5px 0;
      border-radius: 4px;
      font-size: 12px;
      font-weight: 400;
      color: #333;
      box-shadow: 2px 2px 3px 0 rgba(0, 0, 0, .3);
      li {
        margin: 0;
        padding: 0 16px;
        height: 28px;
        line-height: 28px;
        cursor: pointer;
        &:hover {
          background: #eee;
        }
      }
    }

    .btn-con {
      position: absolute;
      top: 0;
      z-index: 10;
      width: 35px;
      height: 100%;
      background: #303133;
      color: #fff;
      cursor: pointer;
      transition: all 0.3s;

      &.next {
        right: 0;
      }
      &.prev {
        left: 0;
      }

      &:hover {
        background-color: #606266;
      }
    }
  }
</style>

<style rel="stylesheet/scss" lang="scss">
  .tags-view-wrapper {
    .tags-view-item {
      .el-icon-close {
        width: 16px;
        height: 16px;
        vertical-align: 2px;
        border-radius: 50%;
        text-align: center;
        transition: all .3s cubic-bezier(.645, .045, .355, 1);
        transform-origin: 100% 50%;
        &:before {
          transform: scale(.6);
          display: inline-block;
          vertical-align: -3px;
        }
        &:hover {
          background-color: #b4bccc;
          color: #fff;
        }
      }
    }
  }
</style>
