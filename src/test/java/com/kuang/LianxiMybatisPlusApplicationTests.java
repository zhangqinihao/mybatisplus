package com.kuang;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kuang.mapper.Usermapper;
import com.kuang.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LianxiMybatisPlusApplicationTests {
    // 继承了BaseMapper，所有的方法都来自己父类
    // 我们也可以编写自己的扩展方法！
    @Autowired
    private Usermapper usermapper;
    @Test
    public void contextLoads() {
        // 参数是一个 Wrapper ，条件构造器，这里我们先不用 null
        // 查询全部用户
        List<User> users = usermapper.selectList(null);
        users.forEach(System.out::println);

    }


    @Test
    public void testInsert() {
        User user = new User();
        user.setName("狂神说sssssssss1Java");
        user.setAge(3);
        user.setEmail("24736743@qq.com");
        int result = usermapper.insert(user); // 帮我们自动生成id
        System.out.println(result); // 受影响的行数
        System.out.println(user); // 发现，id会自动回填 }
    }

    @Test
    public void testUpdate() {
        User user = new User();
        // 通过条件自动拼接动态sql
        user.setId(1L);
        user.setName("啊啊啊");
        user.setEmail("24736743@qq.com");
        int i = usermapper.updateById(user);
        System.out.println(i);

    }

//
//    乐观锁实现方式：
//            ·取出记录时，获取当前version
//·更新时，带上这个version
//·执行更新时，set version=newVersion where version=oldVersion
//·如果version不对，就更新失败


    // 测试乐观锁成功！
    @Test
    public void testOptimisticLocker() {
        // 1、查询用户信息
        User user = usermapper.selectById(1272104512017989638L);
        // 2、修改用户信息
        user.setName("大大");
        usermapper.updateById(user);
    }


    // 测试乐观锁失败！多线程下
    @Test
    public void testOptimisticLocker2(){
        // 线程 1
        User user = usermapper.selectById(1L);
        user.setName("kuangshen111");
        user.setEmail("24736743@qq.com");

        // 模拟另外一个线程执行了插队操作
        User user2 = usermapper.selectById(1L);
        user2.setName("kuangshen2222");
        user2.setEmail("24736743@qq.com");

        usermapper.updateById(user2);
       // 自旋锁来多次尝试提交！
        usermapper.updateById(user); // 如果没有乐观锁就会覆盖插队线程的值！
    }

    // 测试逻辑删除
    @Test
    public void testdelete(){

        usermapper.deleteById(1L);
        // UPDATE user SET deleted=1 WHERE id=? AND deleted=0
        // sql 变为 update
        // SELECT id,name,age,email,create_time,update_time,version,deleted FROM user WHERE deleted=0
         //查询附带条件
    }

    // 测试性能分析
    @Test
    public void testPerformanceInterceptor() {

        List<User> users = usermapper.selectList(null);
        users.forEach(System.out::println);
    }

}
