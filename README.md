# Gxucpc-system 1.2

[![Python](https://img.shields.io/badge/java-1.8-blue.svg?style=flat-square)](https://www.oracle.com/java/technologies/downloads/)
[![Spring Boot](https://img.shields.io/badge/SpringBoot-2.7-blue.svg?style=flat-square)](https://spring.com/)

> 基于 Java 和 Vue 的竞赛管理系统，一站式解决报名-排名-奖状下载一条龙服务。

## 概览

+ 基于 Docker，一键部署无压力
+ 前后端分离
+ 更细致的权限划分，超级管理员和普通管理员各司其职
+ 自定义报名表单信息
+ 奖状自助下载
+ 赛后榜单查看

## 项目

+ 后端(Spring Boot): [https://github.com/cityTS/Gxucpc-system](https://github.com/cityTS/Gxucpc-system)

+ 前端(Vue): [https://github.com/cityTS/Gxucpc-systemFE](https://github.com/cityTS/Gxucpc-systemFE)

## 安装

>

## 文档
目前xcpc-system 实现的基础功能

### 比赛

#### 基础功能

* 添加比赛
* 分页查询所有比赛
* 修改比赛信息
* 删除比赛

#### 下载奖状功能

* 设定是否开启奖状下载
* 奖状批量上传
* 奖状查询式下载，对应学号和姓名和比赛号

#### 表单导出功能

* 对于单场比赛，导出所有提交的表单

### 管理员管理

#### 基础功能

* 管理员的新建
* 管理员的添加
* 管理员的查询
* 管理员的信息修改

#### 管理员权限

* 大管理可以增加管理员
* 小管理不可以进行任何管理员管理操作

### 通知信息管理

#### 基础功能

* 添加赛事通知
* 修改赛事通知信息
* 删除赛事通知信息
* 添加榜单

#### 支持语法

* markdown
* html 

#### 邮箱群发通知

* 群发邮件，预通知，赛前邮件通知

### 表单管理功能

#### 基础功能

* 提交表单与收集
* 修改表单信息
* 删除表单
* 校验表单内的数据合法性，特别是对于邮箱的数据校验

### 登录功能

#### 基础功能

* 登录拦截器
* 前后端均有权限拦截的token校验
* 密文md5存储密码

### 分发账号密码

* 根据所填信息获取比赛账号密码

## TODO

* 动态表单
* 错误与异常处理
* excel动态导出
* 实时榜单传递

## 许可

The [MIT](http://opensource.org/licenses/MIT) License

