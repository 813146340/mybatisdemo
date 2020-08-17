package cn.kgc.mybatisdemo.dao;

import java.util.List;
import cn.kgc.mybatisdemo.pajo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    List<User> queryUser();

    /**
     * 根据用户名模糊查询用户列表
     * @param userName
     * @return
     */
    List<User> queryUserByUserName(String userName);

    /**
     * 根据用户名和用户角色来查询用户信息
     * TODO
     *   在mybatis 中传递参数时，如果需要传递多个参数时，需要进行判断
     *   如果参数的个数在五个以下就一个一个的传（如果mybatis中的接口传递参数的个数大于等于两个请使用@param来进行标识）
     *   如果参数的个数大于了五个就使用对象来进行传递
     *   原则上不建议使用map来进行传参，因为这样会降低团队效率（具体怎么降低，请听后面的课程）
     * @param userName 用户名
     * @param roleId  角色id
     * @return
     */
    List<User> queryUserByUserNameAndRoleId(@Param("userName") String userName, @Param("roleId") Integer roleId);

    /**
     * 通过对象来传递参数
     * @param user  user对象
     * @return
     */
    List<User> quryUserByObject(User user);

    /**
     * 添加用户
     * @param user
     * @return
     */
    Integer addUser(User user);

    /**
     * 修改用户,注意必须传递ID 这里假设只修改用户名
     * @param user
     * @return
     */
    Integer updateUser(User user);

    /**
     * 根据用户ID删除用户
     * @param userId
     * @return
     */
    Integer deleteUser(Integer userId);

    /**
     * 根据角色ID查询对应的用户信息表
     * @param roleId
     * @return
     */
    List<User> queryUserByRoleId(Integer roleId);

    /**
     * 一对多关系映射！！！
     * @param userId
     * @return
     */
    User queryUserAddressListByUserId(Integer userId);
}
