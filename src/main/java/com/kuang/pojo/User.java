package com.kuang.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    //@TableId(type = IdType.ID_WORKER) //默认雪花算法

    @TableId(type = IdType.AUTO) //自增  数据库一定设置自增
    //@TableId(type = IdType.NONE) //未设置主键
    //@TableId(type = IdType.INPUT) //手动输入
    //@TableId(type = IdType.UUID) // 全局唯一id uuid
    //@TableId(type = IdType.ID_WORKER_STR) ///ID_WORKER 字符串表示法

    private Long id;
    private String name;
    private Integer age;
    private String email;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @Version //乐观锁Version注解
    private Integer version;
    @TableLogic //逻辑删除
    private Integer deleted;
}
