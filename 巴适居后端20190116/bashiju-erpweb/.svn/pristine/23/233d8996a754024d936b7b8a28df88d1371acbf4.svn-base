<template>
  <transition name="fade-transform" mode="out-in">
    <div class="sidebar-panel">
    <ul class="sidebar-panel-ul" v-if="data && data.length" @mouseover="handleMouseOver">
      <li
        v-for="(item, index) in data"
        :class="{isActive: currentIndex === index}"
        :key="item.id"
        @mouseenter="showChild(item, index)"
      >
        <p :class="{textCenter: !item.children}">
          <template v-if="item.children">{{item.title}}</template>
          <template v-else>
            <router-link v-if="item.url" :to="{path: item.url}">{{item.title}}</router-link>
            <template v-else>{{item.title}}</template>
          </template>
        </p>
        <icon v-if="item.children && item.children.length" class="icon" name="arrow-right" width="15" height="15" scale="20"></icon>
      </li>
    </ul>

    <sidebar-panel ref="childMenu" v-if="childMenu && childMenu.length" :data="childMenu"></sidebar-panel>
    <!--<template v-for="(item, index) in data">-->
      <!--<sidebar-panel v-show="currentIndex === index" ref="childMenu" :data="item.children" :key="index"></sidebar-panel>-->
    <!--</template>-->
  </div>
  </transition>
</template>

<script>
export default {
  name: 'sidebarPanel',
  props: {
    data: Array,
    asideShow: Boolean
  },
  data () {
    return {
      childMenu: [],
      currentIndex: -1
    }
  },
  watch: {
    data () {
      this.childMenu = []
    },
    asideShow () {
      if (!this.asideShow) {
        this.childMenu = []
        this.currentIndex = -1
      }
    }
  },
  methods: {
    showChild (item, index) {
      this.currentIndex = index
      this.childMenu = item.children
    },
    handleMouseOver (e) {
      if (e.srcElement.nodeName === 'UL') {
        this.currentIndex = -1
        this.childMenu = []
      } else {
        return false
      }
    }
  }
}
</script>

<style scoped lang="scss">
  $red: #e41e2b;
  .sidebar-panel {
    display: flex;
    height: 100%;
    transition: all 0.3s;
    background-color: #303133;
    color: #fff;

    ul {
      overflow-y: auto;
      -ms-overflow-style:none;
      overflow:-moz-scrollbars-none;

      &::-webkit-scrollbar {
        display: none;
      }

      li {
        display: flex;
        width: 160px;
        align-items: center;
        height: 50px;
        line-height: 50px;
        font-size: 14px;
        padding: 6px 20px;
        cursor: pointer;

        p { flex: 1 }
      }
    }
  }
  .isActive {
    color: $red;
  }

  .textCenter {
    text-align: center;
  }
</style>
