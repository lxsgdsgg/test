<template>
  <div>
    <div class="wrapper">
      <el-tabs v-model="activeName" @tab-click="handlerTabClick" type="card" style="max-width: 1280px">
        <el-tab-pane label="授权导航" name="authNavigationSet">
          <author-navigation
            ref="authNavigationSet"
            @switchCurrentTab="switchCurrentTab"
            :data="data"
            @cancelClick="cancelClick"
          ></author-navigation>
        </el-tab-pane>

        <el-tab-pane label="搜索条件" name="queryFormSet">
          <client-search-query-set ref="queryFormSet" @cancelClick="cancelClick" :data="data"></client-search-query-set>
        </el-tab-pane>

        <el-tab-pane label="搜索价格配置" name="queryPriceFormSet">
          <client-price-search-query-set ref="queryPriceFormSet" @cancelClick="cancelClick" :data="data"></client-price-search-query-set>
        </el-tab-pane>

        <el-tab-pane label="税费管理" name="taxAllocationSet">
          <tax-allocation-set ref="taxAllocationSet" @cancelClick="cancelClick" :data="data"></tax-allocation-set>
        </el-tab-pane>

        <el-tab-pane label="贷款比例" name="loanRatioSet">
          <loan-ratio-set ref="loanRatioSet" @cancelClick="cancelClick" :data="data"></loan-ratio-set>
        </el-tab-pane>

        <el-tab-pane label="文章分类" name="articleTypeSet">
          <article-type-set ref="articleTypeSet" :data="data" @cancelClick="cancelClick"></article-type-set>
        </el-tab-pane>

        <el-tab-pane label="图片管理" name="imgMgrSet">
          <image-mgr-set ref="imgMgrSet" @cancelClick="cancelClick" :data="data"></image-mgr-set>
        </el-tab-pane>

        <el-tab-pane label="区间配置" name="sectionSet">
          <section-set ref="sectionSet" @cancelClick="cancelClick" :data="data"></section-set>
        </el-tab-pane>
        <el-tab-pane label="采集地址" name="collectionAddressSet">
          <collection-address-set ref="collectionAddressSet" @cancelClick="cancelClick" :data="data"></collection-address-set>
        </el-tab-pane>

      </el-tabs>

    </div>
  </div>
</template>

<script>
  import AuthorNavigation from './AuthorNavigation'
  import ClientSearchQuerySet from './ClientSearchQuerySet'
  import TaxAllocationSet from './TaxAllocationSet'
  import LoanRatioSet from './LoanRatioSet'
  import ArticleTypeSet from './ArticleTypeSet'
  import ImageMgrSet from './ImageMgrSet'
  import SectionSet from './SectionSet'
  import CollectionAddressSet from './CollectionAddressSet'
  import ClientPriceSearchQuerySet from './ClientPriceSearchQuerySet'

  export default {
    name: "OpenCityTabPages",
    components: {AuthorNavigation
      , ClientSearchQuerySet
      , TaxAllocationSet
      , LoanRatioSet
      , ArticleTypeSet
      , ImageMgrSet
      , SectionSet
      , CollectionAddressSet
      , ClientPriceSearchQuerySet},

    props:{
      data:{
        type: Object,
        required: true,
        default(){
          return null
        }
      }
    },

    data(){
      return {
        activeName: 'authNavigationSet'
      }
    },

    methods:{
      cancelClick(){
        this.$emit('cancelClick')
      },
      handlerTabClick (tab) {
        if (tab.name === 'authNavigationSet'){
          this.$refs['authNavigationSet'].getBars()
        }
        // C端查询条件配置
        if (tab.name === 'queryFormSet') {
          this.$refs['queryFormSet']._loadData()
        }
        // C端查询价格条件配置
        if (tab.name === 'queryPriceFormSet') {
          this.$refs['queryPriceFormSet']._loadData()
        }
        // 税费配置
        if (tab.name === 'taxAllocationSet') {
          this.$refs['taxAllocationSet']._loadData()
        }
        // 贷款比例配置
        if (tab.name === 'loanRatioSet') {
          this.$refs['loanRatioSet']
        }
        // 文章分类
        if (tab.name === 'articleTypeSet') {
          this.$refs['articleTypeSet']._loadData()
        }
        // 图片管理
        if (tab.name === 'imgMgrSet') {
          this.$refs['imgMgrSet']._loadData()
        }
        // 区间配置管理
        if (tab.name === 'sectionSet') {
          this.$refs['sectionSet']._loadData()
        }
        // 采集地址管理
        if (tab.name === 'collectionAddressSet') {
          this.$refs['collectionAddressSet']._loadData()
        }
      },

      switchCurrentTab (name) {
        this.activeName = name
      }
    },

    mounted(){
      this.activeName = 'authNavigationSet'
    }
  }
</script>

<style scoped>

</style>
