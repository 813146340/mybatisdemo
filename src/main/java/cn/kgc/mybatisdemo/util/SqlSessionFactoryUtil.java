package cn.kgc.mybatisdemo.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * 单例模式 懒汉模式
 */
public class SqlSessionFactoryUtil {
    private static SqlSessionFactory sqlSessionFactory = null;
    /**
     * 因为涉及到可能会有多个线程同时加入一个方法，所以使用这个来当线程锁
     */
    private static final Class CLASS_BLOCK = SqlSessionFactoryUtil.class;

    /**
     * 因为是单例模式所以不能允许外部的类随意的new，因此构造方法设置为私有
     */
    private SqlSessionFactoryUtil() {
    }

    /**
     * 创建会话工厂，因为当前类不能实例化所以这里使用静态方法，外面可以直接调用
     * 为防止同时调用，创建多个工厂，使用线程锁来进行把控
     *
     * @return
     */
    public static SqlSessionFactory createSqlSessionFactory() {
        //先判断工厂的状态，如果工厂以及被创建那么工厂中就会有数据
        if (sqlSessionFactory == null) {
            //第一次访问 创建工厂
            String resource = "mybatis-config.xml";
            try {
                InputStream is = Resources.getResourceAsStream(resource);
                //线程锁
                synchronized (CLASS_BLOCK) {
                    //创建工厂的核心
                    sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
                }
            } catch (IOException e) {
                System.out.println("文件读取失败...");
                e.printStackTrace();
            }
            //返回数据
            return sqlSessionFactory;
        } else {
            //不是第一次访问，直接返回工厂
            return sqlSessionFactory;
        }
    }

    /**
     * 获取sql会话
     * @param openAutoCommit 是否开启自动提交 为以后做扩展 TODO 暂时不用
     * @return
     */
    public static SqlSession getSqlSession(boolean openAutoCommit){
        sqlSessionFactory=createSqlSessionFactory();
        //开启会话
        return  sqlSessionFactory.openSession(openAutoCommit);
    }
}
