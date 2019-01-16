<template>
  <div class="wrapper">
    <el-tabs v-model="tabActive">
      <el-tab-pane label="管理后台" name="1">
        <ul class="permission-edit">
          <li class="clearfix">
            <span class="label">
              <el-tag type="info">部门管理</el-tag>
            </span>
            <div class="content">
              <el-checkbox-group v-model="checkList">
                <el-checkbox  class="checkbox" label="查看"></el-checkbox>
                <el-checkbox class="checkbox" label="新增"></el-checkbox>
                <el-checkbox class="checkbox" label="修改"></el-checkbox>
                <el-checkbox class="checkbox" label="锁定"></el-checkbox>
                <el-checkbox class="checkbox" label="清空"></el-checkbox>
                <el-checkbox class="checkbox" label="查询"></el-checkbox>
              </el-checkbox-group>
            </div>
          </li>

          <li class="clearfix">
            <span class="label">
              <el-tag type="info">用户管理</el-tag>
            </span>
            <div class="content">
              <el-checkbox-group v-model="checkList">
                <el-checkbox class="checkbox" label="查看"></el-checkbox>
                <el-checkbox class="checkbox" label="新增"></el-checkbox>
                <el-checkbox class="checkbox" label="修改"></el-checkbox>
                <el-checkbox class="checkbox" label="锁定"></el-checkbox>
                <el-checkbox class="checkbox" label="清空"></el-checkbox>
                <el-checkbox class="checkbox" label="查询"></el-checkbox>
                <el-checkbox class="checkbox" label="权限设置"></el-checkbox>
                <el-checkbox class="checkbox" label="重置密码"></el-checkbox>
                <el-checkbox class="checkbox" label="信息转移"></el-checkbox>
              </el-checkbox-group>
            </div>
          </li>

          <li class="clearfix">
            <span class="label">
              <el-tag type="info">角色管理</el-tag>
            </span>
            <div class="content">
              <el-checkbox-group v-model="checkList">
                <el-checkbox class="checkbox" label="查看"></el-checkbox>
                <el-checkbox class="checkbox" label="新增"></el-checkbox>
                <el-checkbox class="checkbox" label="修改"></el-checkbox>
                <el-checkbox class="checkbox" label="锁定"></el-checkbox>
                <el-checkbox class="checkbox" label="清空"></el-checkbox>
                <el-checkbox class="checkbox" label="查询"></el-checkbox>
              </el-checkbox-group>
            </div>
          </li>

          <li class="clearfix">
            <span class="label">
              <el-tag type="info">职位管理</el-tag>
            </span>
            <div class="content">
              <el-checkbox-group v-model="checkList">
                <el-checkbox class="checkbox" label="查看"></el-checkbox>
                <el-checkbox class="checkbox" label="新增"></el-checkbox>
                <el-checkbox class="checkbox" label="修改"></el-checkbox>
                <el-checkbox class="checkbox" label="锁定"></el-checkbox>
                <el-checkbox class="checkbox" label="清空"></el-checkbox>
                <el-checkbox class="checkbox" label="查询"></el-checkbox>
              </el-checkbox-group>
            </div>
          </li>

          <li class="clearfix">
            <span class="label">
              <el-tag type="info">片区管理</el-tag>
            </span>
            <div class="content">
              <el-checkbox-group v-model="checkList">
                <el-checkbox class="checkbox" label="查看"></el-checkbox>
                <el-checkbox class="checkbox" label="新增"></el-checkbox>
                <el-checkbox class="checkbox" label="修改"></el-checkbox>
                <el-checkbox class="checkbox" label="锁定"></el-checkbox>
                <el-checkbox class="checkbox" label="清空"></el-checkbox>
                <el-checkbox class="checkbox" label="查询"></el-checkbox>
              </el-checkbox-group>
            </div>
          </li>

          <li class="clearfix">
            <span class="label">
              <el-tag type="info">公司管理</el-tag>
            </span>
            <div class="content">
              <el-checkbox-group v-model="checkList">
                <el-checkbox class="checkbox" label="查看"></el-checkbox>
                <el-checkbox class="checkbox" label="新增"></el-checkbox>
                <el-checkbox class="checkbox" label="修改"></el-checkbox>
                <el-checkbox class="checkbox" label="锁定"></el-checkbox>
                <el-checkbox class="checkbox" label="清空"></el-checkbox>
                <el-checkbox class="checkbox" label="查询"></el-checkbox>
              </el-checkbox-group>
            </div>
          </li>

          <li class="clearfix">
            <span class="label">
              <el-tag type="info">部门管理</el-tag>
            </span>
            <div class="content">
              <el-checkbox-group v-model="checkList">
                <el-checkbox class="checkbox" label="查看"></el-checkbox>
                <el-checkbox class="checkbox" label="新增"></el-checkbox>
                <el-checkbox class="checkbox" label="修改"></el-checkbox>
                <el-checkbox class="checkbox" label="锁定"></el-checkbox>
                <el-checkbox class="checkbox" label="清空"></el-checkbox>
                <el-checkbox class="checkbox" label="查询"></el-checkbox>
              </el-checkbox-group>
            </div>
          </li>

          <li class="clearfix">
            <span class="label">
              <el-tag type="info">用户管理</el-tag>
            </span>
            <div class="content">
              <el-checkbox-group v-model="checkList">
                <el-checkbox class="checkbox" label="查看"></el-checkbox>
                <el-checkbox class="checkbox" label="新增"></el-checkbox>
                <el-checkbox class="checkbox" label="修改"></el-checkbox>
                <el-checkbox class="checkbox" label="锁定"></el-checkbox>
                <el-checkbox class="checkbox" label="清空"></el-checkbox>
                <el-checkbox class="checkbox" label="查询"></el-checkbox>
                <el-checkbox class="checkbox" label="权限设置"></el-checkbox>
                <el-checkbox class="checkbox" label="重置密码"></el-checkbox>
                <el-checkbox class="checkbox" label="信息转移"></el-checkbox>
              </el-checkbox-group>
            </div>
          </li>

          <li class="clearfix">
            <span class="label">
              <el-tag type="info">角色管理</el-tag>
            </span>
            <div class="content">
              <el-checkbox-group v-model="checkList">
                <el-checkbox class="checkbox" label="查看"></el-checkbox>
                <el-checkbox class="checkbox" label="新增"></el-checkbox>
                <el-checkbox class="checkbox" label="修改"></el-checkbox>
                <el-checkbox class="checkbox" label="锁定"></el-checkbox>
                <el-checkbox class="checkbox" label="清空"></el-checkbox>
                <el-checkbox class="checkbox" label="查询"></el-checkbox>
              </el-checkbox-group>
            </div>
          </li>

          <li class="clearfix">
            <span class="label">
              <el-tag type="info">职位管理</el-tag>
            </span>
            <div class="content">
              <el-checkbox-group v-model="checkList">
                <el-checkbox class="checkbox" label="查看"></el-checkbox>
                <el-checkbox class="checkbox" label="新增"></el-checkbox>
                <el-checkbox class="checkbox" label="修改"></el-checkbox>
                <el-checkbox class="checkbox" label="锁定"></el-checkbox>
                <el-checkbox class="checkbox" label="清空"></el-checkbox>
                <el-checkbox class="checkbox" label="查询"></el-checkbox>
              </el-checkbox-group>
            </div>
          </li>

          <li class="clearfix">
            <span class="label">
              <el-tag type="info">片区管理</el-tag>
            </span>
            <div class="content">
              <el-checkbox-group v-model="checkList">
                <el-checkbox class="checkbox" label="查看"></el-checkbox>
                <el-checkbox class="checkbox" label="新增"></el-checkbox>
                <el-checkbox class="checkbox" label="修改"></el-checkbox>
                <el-checkbox class="checkbox" label="锁定"></el-checkbox>
                <el-checkbox class="checkbox" label="清空"></el-checkbox>
                <el-checkbox class="checkbox" label="查询"></el-checkbox>
              </el-checkbox-group>
            </div>
          </li>

          <li class="clearfix">
            <span class="label">
              <el-tag type="info">公司管理</el-tag>
            </span>
            <div class="content">
              <el-checkbox-group v-model="checkList">
                <el-checkbox class="checkbox" label="查看"></el-checkbox>
                <el-checkbox class="checkbox" label="新增"></el-checkbox>
                <el-checkbox class="checkbox" label="修改"></el-checkbox>
                <el-checkbox class="checkbox" label="锁定"></el-checkbox>
                <el-checkbox class="checkbox" label="清空"></el-checkbox>
                <el-checkbox class="checkbox" label="查询"></el-checkbox>
              </el-checkbox-group>
            </div>
          </li>
        </ul>
      </el-tab-pane>
      <el-tab-pane label="房源平台" name="2">配置管理</el-tab-pane>
      <el-tab-pane label="财务平台" name="3">角色管理</el-tab-pane>
      <el-tab-pane label="办公平台" name="4">定时任务补偿</el-tab-pane>
      <el-tab-pane label="客源平台" name="5">定时任务补偿</el-tab-pane>
      <el-tab-pane label="决策分析" name="6">定时任务补偿</el-tab-pane>
      <el-tab-pane label="成交平台" name="7">定时任务补偿</el-tab-pane>
    </el-tabs>

    <div class="btn-group">
      <el-button @click="handleSubmit" type="primary">确定</el-button>
      <el-button @click="handleCancel">取消</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ThePermissionManage',
  data () {
    return {
      tabActive: '1',
      checkList: []
    }
  },
  methods: {
    handleSubmit () {
      this.$emit('handleClick', 1)
    },
    handleCancel () {
      this.$emit('handleClick', 2)
    }
  }
}
</script>

<style lang="scss" scoped>
  .permission-edit {
    min-height: 300px;
    max-height: 500px;
    overflow-y: scroll;
    li {
      padding: 10px 20px;
      line-height: 20px;
      border-bottom: 1px dashed #ccc;
      .label {
        float: left;
        width: 15%;
      }

      .content {
        float: left;
        width: 85%;
        margin-top: 5px;

        .checkbox {
          margin-left: 0;
          margin-right: 20px;
        }
      }
    }
  }

  .btn-group {
    margin-top: 20px;
  }
</style>
