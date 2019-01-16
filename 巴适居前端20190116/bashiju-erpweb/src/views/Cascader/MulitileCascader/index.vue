<template>
  <span class="dropTreeLists">
    <span class="benchmark">基准&nbsp;:</span>
    <multiCascader
      :options="configOptions"
       @on-selected="getSelected"
       :inputValue="configTips"
      :disabledPair="disabledPair"
    >
    </multiCascader>
  </span>
</template>
<script>
  import multiCascader from "./MulCheckCascader.vue";
  //这个也是我们项目的接口，不必纠结，倒是换位自己的接口就好了
  // import { getlistBenchmark } from "@/api/basicManage";
  export default {
    components: {
      multiCascader
    },
    data() {
      return {
        configTips: "请选择基准",
        disabledPair: {
          thisPair: ["1"],
          thatPair: ["all"]
        },
        //模板勿删
        configOptions: [
          {
            value: "1",
            label: "一级菜单",
            checked: false,  //控制是否默认选中
            multiple: true,   //是否多选   false为该一级菜单不多选，true表示多选
            children: [
              {
                value: 11,
                checked: false,
                multiple: true,
                disabled:false,    //是否禁用
                label: "二级菜单",
                children: [
                  {
                    value: "21",
                    checked: false,
                    multiple: true,   //是否多选   false为该一级菜单不多选，true表示多选
                    disabled :true,    //是否禁用
                    label: "三级菜单1"
                  },
                  {
                    value: "22",
                    checked: false,
                    label: "三级菜单2"
                  }
                ]
              },
              {
                value: "12",
                checked: true,
                multiple: false,
                label: "二级菜单",
                children: [
                  {
                    value: "399300",
                    checked: true,
                    label: "三级菜单复制"
                  },
                  {
                    value: "399300",
                    checked: false,
                    label: "三级菜单"
                  }
                ]
              }
            ]
          }
        ],
        commonLength: ""
      };
    },
    mounted() {
      // this.MulitGetlistBenchmark(); //多选
    },
    methods: {
      // 点击每一个item的时候的操作   在这个方法内灵活判断多选的状态以及禁用状态
      getSelected(val) {
        let strnum = val.length;
        console.log(val);
        // 当选中的指数大于1并且小于10的时候让所有的指数都可以选择（没有禁用状态）
        if (val.length > 1 && val.length < 10) {
          this.LessThanThen(this.configOptions);
        }
        // 必须保留一个选中的
        if (val.length == 1) {
          let moreOne = val[0];
          this.LessThanMoreOne(this.configOptions, moreOne);
        }
        // 当选中的指数大于10的时候让除选中的之外的指数都变为禁用状态
        if (val.length >= 10) {
          let moreOne = val;
          this.LessThanMoreTen(this.configOptions, moreOne);
        }
        if (strnum !== this.commonLength) {
//将选中后的数组暴漏出去，在需要的页面使用
          this.$emit("CheckedsIndexCodes", val);
        }
        this.commonLength = val.length;
        // 勿删后期需求改变会用
        // this.selectGroups = val;
        // this.configTips = `已选择${val.length}个分组`;
      },
      // 此递归为当选中的指数大于10的时候让除选中的之外的指数都变为禁用状态
      LessThanMoreTen(datas, moreOne) {
        for (var i in datas) {
          if (datas[i].multiple !== false) {
            // console.log(datas[i]);
            datas[i].disabled = true;
            for (let d = 0; d < moreOne.length; d++) {
              if (datas[i].value == moreOne[d]) {
                datas[i].disabled = false;
              }
            }
          } else {
            this.LessThanMoreTen(datas[i].children, moreOne);
          }
        }
      },
      // 此递归为当选中的为选中的只剩下一个的时候禁止取消，也就是必须保留一个选中的
      LessThanMoreOne(datas, moreOne) {
        for (var i in datas) {
          if (datas[i].multiple !== false) {
            // console.log(datas[i]);
            if (datas[i].value == moreOne) {
              datas[i].disabled = true;
            }
          } else {
            this.LessThanMoreOne(datas[i].children, moreOne);
          }
        }
      },
      // 此递归为当选中的为  满足该条件时(val.length > 1 && val.length < 10)  所有的item的都可以选则
      LessThanThen(datas) {
        for (var i in datas) {
          if (datas[i].multiple !== false) {
            // console.log(datas[i]);
            datas[i].disabled = false;
          } else {
            this.LessThanThen(datas[i].children);
          }
        }
      },
      // 此递归为初始化时默认选中沪深300，由于只有一个所以禁用沪深300
      getArrayList(datas) {
        for (var i in datas) {
          if (datas[i].multiple !== false) {
            // console.log(datas[i]);
            datas[i].disabled = false;
            if (datas[i].value === "399300") {
              datas[i].disabled = true;
              datas[i].checked = true;
            }
          } else {
            // console.log(datas[i]);
            //每次在传入父节点的childreg去查找，自己调用自己的方法
            this.getArrayList(datas[i].children);
          }
        }
      },
      MulitGetlistBenchmark() {
//此接口为我们项目中的接口，上边有数据模板，可根据数据模板来写数据。
        getlistBenchmark({}).then(response => {
          this.configOptions = response.data.data;
          this.getArrayList(this.configOptions);
        });
      }
    }
  };
</script>

<style lang="scss" scoped>
  .benchmark {
    font-size: 14px;
  }
</style>
