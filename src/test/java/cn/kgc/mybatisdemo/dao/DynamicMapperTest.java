package cn.kgc.mybatisdemo.dao;

import cn.kgc.mybatisdemo.pajo.User;
import cn.kgc.mybatisdemo.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class DynamicMapperTest {
    private SqlSession sqlSession;
    private DynamicMapper dynamicMapper;

    @Before
    public void setUp() throws Exception{
        sqlSession= SqlSessionFactoryUtil.getSqlSession(true);
        dynamicMapper=sqlSession.getMapper(DynamicMapper.class);
    }
    @After
    public void tearDown() throws Exception{
        if(sqlSession!=null){
            sqlSession.close();
        }
    }
    @Test
    public void dynamicTest1if() {
        List<User> users =dynamicMapper.dynamicTest1if("孙",3);
        for (User user:users
             ) {
            System.out.println(user);
        }
    }

    @Test
    public void updateUserByDynamic(){
        User user=new User();
        user.setId(1L);
        user.setUserName("武常健");
        Integer integer=dynamicMapper.updateUserByDynamic(user);
        if(integer>0){
            System.out.println("更新成功");
        }
    }

    @Test
    public void queryByForeach_array(){
        Integer[] roleId={1,2};
        List<User> users=dynamicMapper.queryByForeach_array(roleId);
        for (User user:users
             ) {
            System.out.println(user);
        }
    }

    @Test
    public void queryUserByForeachList(){
        ArrayList<Integer> roleId=new ArrayList<Integer>();
        roleId.add(1);
        roleId.add(2);
        List<User> users=dynamicMapper.queryUserByForeachList(roleId);
        for (User user:users
             ) {
            System.out.println(user);
        }
    }

    @Test
    public void queryUserByForeachMap(){
        Map<String,Object> map=new HashMap<String, Object>();
        List<Integer> list=new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        map.put("roleId",list);
        map.put("gender",1);
        List<User> users=dynamicMapper.queryUserByForeachMap(map);
        for (User user:users
             ) {
            System.out.println(user);
        }
    }

    @Test
    public void queryByChoose(){
        User user=new User();
        user.setUserName("孙");
        user.setUserRole(1);
        List<User> users=dynamicMapper.queryByChoose(user);
        for (User user1:users
             ) {
            System.out.println(user1);
        }
    }

    @Test
    public void queryUserPage(){
        List<User> users=dynamicMapper.queryUserPage(0,5);
        for (User user:users
             ) {
            System.out.println(user);
        }
    }
}