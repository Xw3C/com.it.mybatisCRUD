package com.it.test;

import com.it.dao.IUserDao;
import com.it.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MybatisTest {

    private InputStream in;
    private SqlSession sqlSession;
    private IUserDao userDao;

    @Before//用于测试方法执行之前执行
    public void init()throws Exception{
        //1、读取配置文件，生成字节输入流
         in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2、获得SqlSessionFactory对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3、获取SqlSession对象
        sqlSession = factory.openSession();
        //4、获得dao的代理对象
        userDao = sqlSession.getMapper(IUserDao.class);
    }

    @After //用于在测试方法之后执行
    public void destory() throws Exception{

        //提交事务
        sqlSession.commit();

        //6、释放资源
        sqlSession.close();
        in.close();


    }

    @Test
    public void testFindAll() throws IOException {

        //5、执行查询方法
        List<User> users = userDao.findAll();
        for (User user : users){
            System.out.println(user);
        }





    }
//测试保存操作
    @Test
    public void testSave(){
        User user=new User();
        user.setUsername("mybatis saveuser");
        user.setAddress("广州市黄埔区");
        user.setSex("男");
        user.setBirthday(new Date());

        //5、执行保存方法
        userDao.saveUser(user);

    }
//测试更新操作

    @Test
    public void testUpdate(){
        User user=new User();
        user.setId(49);
        user.setUsername("mybatis Updata user");
        user.setAddress("广州市黄埔区");
        user.setSex("女");
        user.setBirthday(new Date());

        //5、执行保存方法
        userDao.updateUser(user);

    }


    //测试删除操作
    @Test
    public void testDelete(){
        //5、执行保存方法
        userDao.deleteUser(48);

    }
}
