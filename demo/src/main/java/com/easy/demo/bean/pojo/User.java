package com.easy.demo.bean.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 用户实体类
 * @author muchi
 */
@Data
@Schema(description = "用户实体", example = "{\"id\":1, \"name\":\"张三\", \"email\":\"zhangsan@example.com\"}")
public class User {

    @Schema(description = "用户ID", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "用户姓名", example = "张三")
    private String name;

    @Schema(description = "用户邮箱", example = "zhangsan@example.com")
    private String email;
}
