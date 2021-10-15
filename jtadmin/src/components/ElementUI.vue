<template>
  <!-- vue编辑页面必须有根标签-->
  <div>
    <!-- 1.面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>用户管理</el-breadcrumb-item>
      <el-breadcrumb-item>用户列表</el-breadcrumb-item>
    </el-breadcrumb>

    <!-- 准备卡片 -->
    <el-card class="box-card">

      <!-- 定义栅格 可以根据页面宽度自动适配  栅格一共24格 -->
      <el-row  :gutter="20">
        <el-col :span="8">
          <!-- 输入框 -->
          <el-input placeholder="请输入内容" v-model="data" class="input-with-select">
             <el-button slot="append" icon="el-icon-search"></el-button>
           </el-input>
        </el-col>
        <el-col :span="8">
           <el-button type="success">成功按钮</el-button>
        </el-col>
      </el-row>

      <!-- 定义表格
          :data 表示数据的来源
          prop:对象的属性
          label="显示的名称"
      -->
     <el-table :data="tableData" style="width: 100%">
          <el-table-column prop="name" label="姓名"></el-table-column>
          <el-table-column prop="address" label="地址"></el-table-column>
          <el-table-column prop="date" label="日期"></el-table-column>
          <el-table-column label="操作">
              <el-button type="primary" @click="updateBtn">修改</el-button>
              <el-button type="danger">删除</el-button>
          </el-table-column>
     </el-table>

    <h1>图片上传操作</h1>
    <!--
      action: 图片上传的网址
      :on-preview 点击文件列表中已上传的文件时的钩子
      :on-remove	文件列表移除文件时的钩子
      :file-list  用户看到的图片的列表信息
      name	上传的文件字段名	string	—	file
      数据上传的形式:  关键字默认:file=文件信息
    -->
    <el-upload
      class="upload-demo"
      action="https://jsonplaceholder.typicode.com/posts/"
      :on-preview="handlePreview"
      :on-remove="handleRemove"
      :file-list="fileList"
      list-type="picture">
      <el-button size="small" type="primary">点击上传</el-button>
      <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
    </el-upload>




    </el-card>

    <!-- 当用户点击修改按钮时,弹出对话框
        dialogVisible: true 展现  false 关闭
    -->
    <el-dialog title="修改数据" :visible.sync="dialogVisible"
      width="80%" :before-close="handleClose">
      <span>这是一段信息</span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="updateItem">确 定</el-button>
      </span>
    </el-dialog>

  </div>
</template>

<script>
export default {
    data(){
      return {
        data: '刘昱江',
        tableData: [
                      {
                        date: '2016-05-02',
                        name: '王小虎',
                        address: '上海市普陀区金沙江路 1518 弄'
                      },
                      {
                        date: '2016-05-04',
                        name: '王小虎',
                        address: '上海市普陀区金沙江路 1517 弄'
                      }
                    ],
        dialogVisible: false,
        fileList: [{name: 'food.jpeg', url: 'https://img14.360buyimg.com/n0/jfs/t1/94581/19/18436/123859/6141ca9bEdc318374/892cc4297882fe0d.jpg'}, {name: 'food2.jpeg', url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'}]
      }
    },
    methods: {
      updateBtn(){
        this.dialogVisible = true
      },
      updateItem(){
        alert("点击提交业务数据ajax调用")
        this.dialogVisible = false
      }
    }
}

</script>

<style lang="less" scoped>
</style>
