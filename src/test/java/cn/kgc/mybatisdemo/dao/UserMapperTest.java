package cn.kgc.mybatisdemo.dao;

import cn.kgc.mybatisdemo.pajo.User;
import cn.kgc.mybatisdemo.util.SqlSessionFactoryUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.*;

public class UserMapperTest {

    @Test
    public void queryUser() {
        String resources="mybatis-config.xml";
        try {
            InputStream is =Resources.getResourceAsStream(resources);
            //建造者通过配置文件创建会话工厂（会话工厂类似于basedao的connection对象，这里会话工厂是全局的）
            SqlSessionFactory sqlSessionFactory =new SqlSessionFactoryBuilder().build(is);
            //会话工厂负责生产 sal会话
            SqlSession sqlSession =sqlSessionFactory.openSession();
            //sql会话是最小的操作数据库单位，里面存放着操作数据库需要用到的所有方法
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            List<User> users =userMapper.queryUser();
            for (User user:users
                 ) {
                System.out.println(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void utilTest(){
        SqlSession sqlSession= SqlSessionFactoryUtil.getSqlSession(false);
        List<User> objects=sqlSession.selectList("queryUser");
        System.out.println(objects);
        UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
        List<User> users=userMapper.queryUser();
        for (User user:users
             ) {
            System.out.println(user);
        }
        sqlSession.close();
    }

    private SqlSession sqlSession;
    private UserMapper userMapper;
    @After
    public void after(){
        //在测试方法执行完成后执行
        if(sqlSession!=null){
            sqlSession.close();
        }
    }
    @Before
    public void before(){
        //在测试方法执行完成前执行
        sqlSession=SqlSessionFactoryUtil.getSqlSession(true);
        userMapper=sqlSession.getMapper(UserMapper.class);
    }
    @Test
    public void util2(){
        List<User> users=userMapper.queryUser();
        for (User user:users
             ) {
            System.out.println(user);
        }
    }

    @Test
    public void queryUserByUserName(){
        List<User> users=userMapper.queryUserByUserName("孙");
        for (User user:users
             ) {
            System.out.println(user);
        }
    }

    @Test
    public void queryUserByUserNameAndRoleId(){
        List<User> users=userMapper.queryUserByUserNameAndRoleId("孙",3);
        for (User user:users
             ) {
            System.out.println(user);
        }
    }

    @Test
    public void quryUserByObject(){
        User test=new User();
        test.setUserName("孙");
        test.setUserRole(3);
        List<User> users=userMapper.quryUserByObject(test);
        for (User user:users
             ) {
            System.out.println(user);
        }
    }

    @Test
    public void addUser(){
        User user=new User();
        user.setUserCode("test_user");
        user.setUserName("添加用户测试");
        user.setUserPassword("000");
        Integer integer=userMapper.addUser(user);
        if(integer>0){
            System.out.println("添加数据成功");
        }
    }

    @Test
    public void updateUser(){
        User user =new User();
        user.setId(17L);
        user.setUserName("修改测试");
        Integer rowCount =userMapper.updateUser(user);
        if(rowCount>0){
            System.out.println("修改成功！！！");
        }
    }

    @Test
    public void deleteUser(){
        if(userMapper.deleteUser(17)>0){
            System.out.println("删除成功");
        }
    }

    /*一对一测试*/
    @Test
    public void queryUserByRoleId(){
    List<User> users=userMapper.queryUserByRoleId(2);
        for (User user:users
             ) {
            System.out.println(user);
        }
    }

    /*一对多测试*/
    @Test
    public void queryUserAddressListByUserId(){
        User user=userMapper.queryUserAddressListByUserId(1);
        System.out.println(user);
    }

    @Test//缓存
    public void cacheTest(){
        List<User> users=userMapper.queryUser();
        System.out.println("第一次查询完成");
        List<User> users1=userMapper.queryUser();
        System.out.println("第二次查询完成");
    }


    @Test//一级缓存
    public void cache1(){
        //一级缓存测试，在mybatis的sqlsession中，默认开启一级缓存
        //所有执行查询的操作都会在缓存中存放
        //第一次查询
        System.out.println("第一次查询");
        userMapper.queryUser();
        System.out.println("第二次查询");
        userMapper.queryUser();//缓存
    }

    @Test//二级缓存
    public void cache2(){
        SqlSession sqlSession1=SqlSessionFactoryUtil.getSqlSession(true);
        SqlSession sqlSession2=SqlSessionFactoryUtil.getSqlSession(true);
        UserMapper userMapper1=sqlSession1.getMapper(UserMapper.class);
        UserMapper userMapper2=sqlSession2.getMapper(UserMapper.class);
        userMapper1.queryUser();
        sqlSession1.commit();
        userMapper2.queryUser();
    }
}