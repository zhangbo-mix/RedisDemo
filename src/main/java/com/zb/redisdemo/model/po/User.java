package com.zb.redisdemo.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor//自动生成无参数构造函数。
@AllArgsConstructor//自动生成全参数构造函数。
@Data //自动生成get set方法
public class User implements Serializable {

    private int id;
    private String userName;
    private String userNameSex;

}
