import Vue from 'vue'
import Router from 'vue-router'

import AppMain from '../views/layout/components/AppMain'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      component: () => import('../views/layout'),
      redirect: '/dashboard',
      children: [
        {
          path: '/dashboard',
          name: 'dashboard',
          component: () => import('../views/dashboard'),
          meta: {title: '首页'}
        },
        {
          path: '/manage',
          name: 'manage',
          component: AppMain,
          meta: {title: '管理平台'},
          redirect: '/manage/system/dept',
          children: [
            {
              path: '/manage/system',
              name: 'system',
              component: AppMain,
              redirect: '/manage/system/dept',
              meta: {title: '系统管理'},
              children: [
                {
                  path: '/manage/system/dept',
                  name: 'dept',
                  component: () => import('../views/manage/system/dept'),
                  meta: {title: '部门管理'}
                },
                {
                  path: '/manage/system/menu',
                  name: 'menu',
                  component: () => import('../views/manage/system/menu'),
                  meta: {title: '菜单管理'}
                },
                {
                  path: '/manage/system/role',
                  name: 'role',
                  component: () => import('../views/manage/system/role'),
                  meta: {title: '角色管理'}
                },
                {
                  path: '/manage/system/user',
                  name: 'user',
                  component: () => import('../views/manage/system/user'),
                  meta: {title: '用户管理'}
                },
                {
                  path: '/manage/system/deptType',
                  name: 'deptType',
                  component: () => import('../views/manage/system/deptType'),
                  meta: {title: '部门类型'}
                },
                {
                  path: '/manage/system/post',
                  name: 'post',
                  component: () => import('../views/manage/system/post'),
                  meta: {title: '职位管理'}
                },
                {
                  path: '/manage/system/divideIntoConfig',
                  name: 'divideIntoConfig',
                  component: () => import('../views/manage/system/divideIntoConfig'),
                  meta: {title: '分成配置管理'}
                },
                {
                  path: '/manage/region/region-list',
                  name: 'region',
                  component: () => import('../views/manage/system/region'),
                  meta: {title: '片区管理'}
                },
                {
                  path: '/manage/company/company',
                  name: 'company',
                  component: () => import('../views/manage/system/company'),
                  meta: {title: '公司管理'}
                },
                {
                  path: '/manage/community/list',
                  name: 'community',
                  component: () => import('../views/manage/system/community'),
                  meta: {title: '小区管理'}
                }
              ]
            }
          ]
        }
      ]
    }
  ]
})
