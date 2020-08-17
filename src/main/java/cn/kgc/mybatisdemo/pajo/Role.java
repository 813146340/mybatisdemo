package cn.kgc.mybatisdemo.pajo;

import lombok.Data;

/**
 * @Data 可以自动完成get set 方法无需程序员自己实现
 */
@Data
public class Role {
    private Long id;
    private String roleCode;
    private String roleName;
}
