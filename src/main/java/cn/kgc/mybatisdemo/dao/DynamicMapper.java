package cn.kgc.mybatisdemo.dao;

import cn.kgc.mybatisdemo.pajo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DynamicMapper {
    /**
     * 动态sql第一版测试 sql语句根据传入参数的情况来调整查询语句
     * @param userName
     * @param roleid
     * @return
     */
    List<User> dynamicTest1if(@Param("userName") String userName, @Param("roleid") Integer roleid);

    /**
     * 动态更新用户  一个sql语句解决所有和用户相关的修改操作
     * @param user
     * @return
     */
    Integer updateUserByDynamic(User user);

    /**
     * 使用trim标签代替set标签
     * @param user  trim 是一个功能强大的标签
     * @return
     */
    Integer updateUserByDynamicTrim(User user);

    /**
     * 通过foreach 标签来遍历数组 !!!
     * @param roleId 通过角色ID 来查询用户信息
     * @return
     */
    List<User> queryByForeach_array(Integer[] roleId);

    /**
     * 通过foreach 遍历list集合
     * @param roleId
     * @return
     */
    List<User> queryUserByForeachList(List<Integer> roleId);

    /**
     * 通过foreach 遍历map集合  map的键通常设置为string 这样就可以通过键来获取对应的值！！！
     * @param map
     * @return
     */
    List<User> queryUserByForeachMap(Map<String,Object> map);

    /**
     * mybatis 动态sql choose,类似于java的switch!!!
     * @param user
     * @return
     */
    List<User> queryByChoose(User user);

    /**
     * mybatis 分页
     * @param currentPage 起始页
     * @param pageSize 页面大小
     * @return
     */
    List<User> queryUserPage(@Param("currentPage") Integer currentPage, @Param("pageSize") Integer pageSize);
}
