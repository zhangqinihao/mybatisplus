package com.kuang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kuang.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository // 代表持久层
//@Mapper  用这个注解 启动类不用加扫描
public interface Usermapper extends BaseMapper<User> {

// 所有的CRUD操作都已经编写完成了

// 你不需要像以前的配置一大堆文件了！

}
