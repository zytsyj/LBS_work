package com.yy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author young
 * @since 2024年04月08日
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("race")
public class Race implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "race_id", type = IdType.AUTO)
    private Integer raceId;

    @TableField("desx")
    private Integer desx;

    @TableField("desy")
    private Integer desy;
    @TableField("winner_id")
    public Integer winnerId;

}
