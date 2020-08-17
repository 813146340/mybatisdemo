package cn.kgc.mybatisdemo.pajo;

import lombok.Data;

import java.util.List;

@Data
public class User {
    private  Long id;
    private String userCode;
    private String userPassword;
    private String userName;
    private Integer userRole;
    private String userRoleName;

    /*一对一关系映射*/
    private Role role;

    /*一对多关系映射，一个用户拥有多个收货地址*/
    private List<Address> addressList;
}
