# 简介
<center>
<img src ="https://img-blog.csdnimg.cn/20210421022916813.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2OTM3Njg0,size_16,color_FFFFFF,t_70">
</center>

<font face="楷体" size="+1" color="#000033">本文基于**Spring+SpringMVC+Mybatis**实现一个简单的学生成绩管理系统，实现三层架构。它在上一JavaWeb版本基础上使用了SSM框架，优化了相关功能，使用**分页模型**对所有学生信息进行显示。

# 任务
<center><img src="https://img-blog.csdnimg.cn/20200923171232277.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2OTM3Njg0,size_16,color_FFFFFF,t_70#pic_center"/></center>
<br/>

# 相关工作

- [MySQL+java:   实现学生成绩管理系统（1.0版本）](https://blog.csdn.net/qq_36937684/article/details/108757156)
- [Mybatis 基于注解方式实现学生成绩管理系统（完整代码）](https://blog.csdn.net/qq_36937684/article/details/113201038)
- [整合Spring+Mybatis    学生成绩管理系统（完整代码）](https://blog.csdn.net/qq_36937684/article/details/113622364)
- [JavaWeb+MySQL实现学生成绩管理系统（1.0版本完整代码）](https://blog.csdn.net/qq_36937684/article/details/114846331?spm=1001.2014.3001.5501)
- [Spring MVC 拦截器判断用户是否登录](https://blog.csdn.net/qq_36937684/article/details/115743919?spm=1001.2014.3001.5501)

<font face="楷体" size="+1" color="#000033">本项目基于以上[项目](https://github.com/AsajuHuishi/StudentScoreManagementSystem_JavaWeb)进行改进。主要改进内容有：
- 使用Spring+SpringMVC+Mybatis框架实现，代替原生Servlet处理请求转发；
- 使用SpringMVC拦截器判断用户是否登录，在注册页面使用验证码；
- 使用分页模型显示所有学生信息，在主页基础上实现增加、删除、修改、统计功能，在前端页面上增加更多交互功能和提示。


# 项目结构
<font face="楷体" size="+1" color="#000033">这是一个maven工程。



```bash
└─main
    ├─java
    │  └─indi
    │      └─huishi
    │          ├─controller 控制器
    │          ├─dao		持久层，和数据库交互
    │          │  └─impl
    │          ├─handler	处理异常
    │          ├─interceptor拦截器判断登录状态
    │          ├─pojo		实体类 Student User
    │          ├─service	业务层
    │          │  └─impl	实现类
    │          ├─test		测试
    │          │  └─basic
    │          └─utils
    ├─resources				配置文件
    │  └─indi
    │      └─huishi
    │          └─dao
    └─webapp
        ├─META-INF
        ├─pages
        │  ├─common			页面通用部分
        │  ├─error			错误页面
        │  ├─menu			主菜单：学生信息处理相关所有页面
        │  ├─test
        │  ├─useless
        │  └─user			登录和注册
        ├─static
        │  ├─css			样式
        │  ├─img			图片
        │  └─script			jquery
        └─WEB-INF
            ├─classes
            │  └─indi
            │      └─huishi
            │          ├─controller
            │          ├─dao
            │          ├─handler
            │          ├─interceptor
            │          ├─pojo
            │          ├─service
            │          │  └─impl
            │          ├─test
            │          │  └─basic
            │          └─utils
            └─lib

```
<hr/>

# 数据库

<font face="楷体" size="+1" color="#000033">使用MySQL实现，和上一版本的区别：学生表的主键和学号字段分离。主键将不再面向用户出现。

```sql
USE student_score_ssm;

CREATE TABLE student_score(
	id INT PRIMARY KEY AUTO_INCREMENT,
	NO VARCHAR(10) UNIQUE NOT NULL,
	NAME VARCHAR(20) NOT NULL,
	score FLOAT(20),
	class_name INT
);


CREATE TABLE USER(
	id INT PRIMARY KEY AUTO_INCREMENT,
	username VARCHAR(20) UNIQUE,
	PASSWORD VARCHAR(20) NOT NULL,
	email VARCHAR(20)
);

```
# 结果页面
## 主页
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210421021004637.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2OTM3Njg0,size_16,color_FFFFFF,t_70#pic_center)
![在这里插入图片描述](https://img-blog.csdnimg.cn/2021042102104968.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2OTM3Njg0,size_16,color_FFFFFF,t_70#pic_center)

## 查询
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210421021214493.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2OTM3Njg0,size_16,color_FFFFFF,t_70#pic_center)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210421021257903.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2OTM3Njg0,size_16,color_FFFFFF,t_70#pic_center)
<font face="楷体" size="+1" color="#000033">查询异常
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210421021228421.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2OTM3Njg0,size_16,color_FFFFFF,t_70#pic_center)
## 增加
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210421021731287.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2OTM3Njg0,size_16,color_FFFFFF,t_70#pic_center)

## 修改
![在这里插入图片描述](https://img-blog.csdnimg.cn/2021042102170056.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2OTM3Njg0,size_16,color_FFFFFF,t_70#pic_center)
## 删除
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210421021712884.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2OTM3Njg0,size_16,color_FFFFFF,t_70#pic_center)


## 统计
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210421021346321.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2OTM3Njg0,size_16,color_FFFFFF,t_70#pic_center)
## 登录
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210421170512593.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2OTM3Njg0,size_16,color_FFFFFF,t_70#pic_center)

##  注册

![在这里插入图片描述](https://img-blog.csdnimg.cn/20210421170527699.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2OTM3Njg0,size_16,color_FFFFFF,t_70#pic_center)
