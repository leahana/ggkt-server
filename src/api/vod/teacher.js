import request from '@/utils/request'
import { resetRouter } from '@/router'

const api_name = '/admin/vod/teacher'

export default {
  /**
   * 分页查询
   * @param {*} current  页码
   * @param {*} limit 每页显示数量
   * @param {*} searchObj 条件对象
   */
  pageList(current, limit, searchObj) {
    return request({
      url: `${api_name}/findQueryPage/${current}/${limit}`,
      method: 'post',
      // 使用JSON格式传递 写法 data:searchObj
      // 使用普通格式传递  写法 params:searchObj
      data: searchObj
    })
  },
  /**
   * 根据id删除
   * @param id 教师id
   */
  removeById(id) {
    return request({
      url: `${api_name}/remove/${id}`,
      method: 'delete'
    })
  },
  /**
   * 添加讲师
   * @param teacher
   */
  saveTeacher(teacher) {
    return request({
      url: `${api_name}/save`,
      method: 'post',
      data: teacher
    })
  },
  /**
   * 根据id查询讲师
   * @param id
   */
  getTeacherById(id) {
    return request({
      url: `${api_name}/getTeacher/${id}`,
      method: 'get'
    })
  },
  /**
   * 修改讲师信息
   * @param teacher
   */
  updateTeacher(teacher) {
    return request({
      url: `${api_name}/update`,
      method: 'put',
      data: teacher
    })
  },
  /**
   * 批量删除
   * @param idList 讲师id集合
   */
  removeBatch(idList) {
    return request({
      url: `${api_name}/removeBatch`,
      method: 'delete',
      data: idList
    })
  }
}
