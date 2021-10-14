package com.jt.pojo;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain=true)
public class BasePojo implements Serializable{
	//新增操作时 自动填充
	@TableField(fill = FieldFill.INSERT)
	private Date created;
	//新增和修改操作时自动填充
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updated;
}
