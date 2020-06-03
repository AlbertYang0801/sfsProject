package com.sfs.model;

import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * @author  by yjw
 * on 2019/9/18
 */
@Data
@ToString
public class Student {

    private String name;
    private int age;
    private Date birthday;
    private Float money;
    private List<Student> friends;
    private Student bestFriend;

}
