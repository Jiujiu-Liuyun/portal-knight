package com.zhangyun.portalknight.server.domain.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhangyun
 * @since 2022-08-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="File对象", description="")
public class File extends Model {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文件id")
    @TableId(value = "fileid", type = IdType.AUTO)
    private Integer fileid;

    @ApiModelProperty(value = "用户id")
    private String userid;

    @ApiModelProperty(value = "文件路径")
    private String filepath;

    @ApiModelProperty(value = "是否删除")
    private Integer isDelete;

    @ApiModelProperty(value = "是否为文件")
    private Integer isFile;

    @ApiModelProperty(value = "文件md5值")
    private String md5;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime modifiedTime;


}
