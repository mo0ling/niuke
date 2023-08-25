package com.example.niuke.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginTicket {
    private int id;
    private int userId;
    private String ticket;//凭证编号
    private int status;//凭证状态 0表示有效凭证 1表示无效凭证
    private Date expired;//用于检测这个凭证是否已经过期
}
