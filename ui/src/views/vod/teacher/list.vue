<template>
  <div class="app-container">
    讲师列表
    <!--查询表单-->
    <el-card class="operate-container" shadow="never">
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item label="名称">
          <el-input v-model="searchObj.name" placeholder="讲师名"/>
        </el-form-item>

        <el-form-item label="头衔">
          <el-select v-model="searchObj.level" clearable placeholder="头衔">
            <el-option value="1" label="高级讲师"/>
            <el-option value="0" label="首席讲师"/>
          </el-select>
        </el-form-item>

        <el-form-item label="入驻时间">
          <el-date-picker
            v-model="searchObj.joinDateBegin"
            placeholder="开始时间"
            value-format="yyyy-MM-dd"
          />
        </el-form-item>
        <el-form-item label="-">
          <el-date-picker
            v-model="searchObj.joinDateEnd"
            placeholder="结束时间"
            value-format="yyyy-MM-dd"
          />
        </el-form-item>

        <el-button type="primary" icon="el-icon-search" @click="fetchData()">查询</el-button>
        <el-button type="default" @click="resetData()">清空</el-button>
      </el-form>
    </el-card>

    <!-- 工具按钮 -->
    <el-card class="operate-container" shadow="never">
      <i class="el-icon-tickets" style="margin-top: 5px"></i>
      <span style="margin-top: 5px">数据列表</span>
      <el-button class="btn-add" @click="add()" style="margin-left: 10px;">添加</el-button>
      <el-button class="btn-add" @click="removeBatch()">批量删除</el-button>
    </el-card>
    <!-- 表格 -->
    <el-table :data="list" border stripe @selection-change="handleSelectionChange">
      <el-table-column type="selection"/>
      <el-table-column label="#" width="50">
        <template slot-scope="scope">
          {{ (page - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column prop="name" label="名称" width="80"/>
      <el-table-column label="头衔" width="90">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.level === 1" type="success" size="mini">高级讲师</el-tag>
          <el-tag v-if="scope.row.level === 2" size="mini">首席讲师</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="intro" label="简介"/>
      <el-table-column prop="sort" label="排序" width="60"/>
      <el-table-column prop="joinDate" label="入驻时间" width="160"/>
      <el-table-column label="操作" width="200" align="center">
        <template slot-scope="scope">
          <el-button type="text" size="mini" @click="removeById(scope.row.id)">删除</el-button>
          <router-link :to="'/vod/teacher/edit/' + scope.row.id">
            <el-button type="text" size="mini">修改</el-button>
          </router-link>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页组件 -->
    <el-pagination
      :current-page="page"
      :total="total"
      :page-size="limit"
      :page-sizes="[5, 10, 20, 30, 40, 50, 100]"
      style="padding: 30px 0; text-align: center;"
      layout="total, sizes, prev, pager, next, jumper"
      @size-change="changePageSize"
      @current-change="changeCurrentPage"
    />
  </div>
</template>

<script>
//  引入定义接口js文件
import teacherApi from '@/api/vod/teacher'
// js后缀可以省略

export default {
// 初始值，vue双向绑定模型的实时数据
  data() {
    return {
      list: [], // 讲师列表
      total: 0, // 总记录数
      page: 1, // 页码
      limit: 10, // 每页记录数
      searchObj: {}, // 查询条件
      multipleSelection: []// 批量删除选中的记录列表
    }
  },
  // 页面渲染之前
  created() {
    this.fetchData()
  },

  methods: {
    // 批量删除
    removeBatch() {
      // 判断非空
      if (this.multipleSelection.length === 0) {
        this.$message.warning('请选择要删除的记录！')
        return
      } else {
        this.$confirm('此操作将永久删除该记录，是否继续？', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          // 点击确定，远程调用ajax
          // 遍历selection，将id取出放入id列表
          var idList = []
          this.multipleSelection.forEach(item => {
            idList.push(item.id)
          })
          teacherApi.removeBatch(idList).then(res => {
            // 提示
            this.fetchData()
            this.$message({
              type: 'success',
              message: '删除成功！'
            })
            // 刷新页面
          })
        })
      }
    },
    // 多选id，复选框发生变化，选中复选框行内容传递
    handleSelectionChange(selection) {
      this.multipleSelection = selection
    },
    // 跳转到添加表单界面
    add() {
      this.$router.push({ path: '/vod/teacher/create' })
    },
    fetchData() {
      // 调用api
      teacherApi.pageList(this.page, this.limit, this.searchObj).then(response => {
        // debugger
        console.log(response.data.records)
        this.list = response.data.records
        this.total = response.data.total
      })
    },
    // 每页记录数改变，size：回调参数，表示当前选中的“每页条数”
    changePageSize(size) {
      this.limit = size
      this.fetchData()
    },
    // 改变页码，page：回调参数，表示当前选中的“页码”
    changeCurrentPage(page) {
      this.page = page
      this.fetchData()
    },
    // 重置表单
    resetData() {
      this.searchObj = {}
      this.fetchData()
    },
    // 根据id删除
    removeById(id) {
      this.$confirm('此操作将永久删除该记录，是否继续？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        teacherApi.removeById(id).then(res => {
          // 提示
          this.$message({
            type: 'success',
            message: '删除成功！'
          })
          // 刷新页面
          this.fetchData()
        })
      })
    }
  }
}
</script>
