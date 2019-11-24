package com.wk.pojo;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wk
 * @since 2019-09-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
//@TableName(value = "数据库对应表名")  如果数据库表名和实体类名不同可以使用此注解使之对应
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     * @TableId(value = "userid")   代表主键，如果数据库字段和属性不同也可以用此注解使之对应
     */
    private Integer id;

    /**
     * 姓名
     * @TableField(value = "")  数据库字段和属性相同可以不加该注解
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 乐观锁版本号，必须加上@Version注解
     */
    @Version
    private Integer version;

    /**
     * 逻辑删除，必须加上@TableLogic注解
     */
    @TableLogic
    private Integer deleted;

}
