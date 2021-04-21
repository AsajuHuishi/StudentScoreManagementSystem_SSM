@[TOC](JavaWeb+MySQL实现学生成绩管理系统)
# 简介
<font face="楷体" size="+1" color="#000033">本文基于**Java+MySQL+web**实现一个简单的学生成绩管理系统，实现B/S三层架构。它使用基础的`Servlet`和`JSP`实现Web层。它在Dao层使用`Druid`数据库连接池，使用`DBUtils`操作数据库。在功能上，相比前两个版本增加了**用户注册和登录功能**。

<font face="楷体" size="+1" color="#000033">完整代码见github

# 任务
<center><img src="https://img-blog.csdnimg.cn/20200923171232277.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2OTM3Njg0,size_16,color_FFFFFF,t_70#pic_center"/></center>
<br/>

# 项目结构
<center><font face="楷体" size="+1" color="#000033">整体结构</font></center>
<center><img src="https://img-blog.csdnimg.cn/20210315193254307.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2OTM3Njg0,size_16,color_FFFFFF,t_70"/></center>

<center><font face="楷体" size="+1" color="#000033">src目录</font></center>
<center><img src="https://img-blog.csdnimg.cn/20210315202004790.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2OTM3Njg0,size_16,color_FFFFFF,t_70"></center>

<center><font face="楷体" size="+1" color="#000033">web目录</font></center>
<center></font><img src="https://img-blog.csdnimg.cn/20210315202456583.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2OTM3Njg0,size_16,color_FFFFFF,t_70"></center>

<center><font face="楷体" size="+1" color="#000033">依赖jar包</font></center>
<center></font><img src="https://img-blog.csdnimg.cn/20210315202702881.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2OTM3Njg0,size_16,color_FFFFFF,t_70"></center>


# 相关工作
<font face="楷体" size="+1" color="#000033">[MySQL+java: 实现学生成绩管理系统（1.0版本）](https://blog.csdn.net/qq_36937684/article/details/108757156)
[MySQL+java: 实现学生成绩管理系统（2.0版本）](https://blog.csdn.net/qq_36937684/article/details/112502793)

<font face="楷体" size="+1" color="#000033">本项目基于以上[项目](https://github.com/AsajuHuishi/StudentScoreManagementSystem_JDBC)进行改进。主要改进内容有：
- 使用`Servlet+JSP`实现Web层，完整实现B/S三层架构；
- 使用`Druid`数据库连接池技术；
- 增加了用户注册和登录功能；


目录   | 功能
-------- | -----
indi.huishi.pojo  | 实体类，用户表和学生表
indi.huishi.utils  | 使用Druid数据库连接池，封装数据库连接和关闭
indi.huishi.dao  | Dao层，基于DBUtils封装CRUD
indi.huishi.service | Service层，实现具体业务
indi.huishi.controller| Web层，使用Servlet实现请求和响应
indi.huishi.test| 测试
/pages/menu| 学生成绩管理系统菜单，html页面
/pages/success| Servlet请求转发到jsp页面回传数据给用户
/pages/user| 用户注册与登录
<hr/>

# 数据库
<font face="楷体" size="+1" color="#000033">包括用户表和学生表。（本文使用数据库不同于2.0版本的db58）
用户表：
```sql
+----------+--------------+------+-----+---------+----------------+
| Field    | Type         | Null | Key | Default | Extra          |
+----------+--------------+------+-----+---------+----------------+
| id       | int(11)      | NO   | PRI | NULL    | auto_increment |
| username | varchar(20)  | NO   | UNI | NULL    |                |
| password | varchar(20)  | NO   |     | NULL    |                |
| email    | varchar(200) | YES  |     | NULL    |                |
+----------+--------------+------+-----+---------+----------------+
```
<font face="楷体" size="+1" color="#000033">学生表：注意和2.0版本有很大区别。使用id作为主键自增长列，学号为unique列。

```sql
+-----------+-------------+------+-----+---------+----------------+
| Field     | Type        | Null | Key | Default | Extra          |
+-----------+-------------+------+-----+---------+----------------+
| NAME      | varchar(20) | YES  |     | NULL    |                |
| score     | float       | YES  |     | NULL    |                |
| className | int(11)     | YES  |     | NULL    |                |
| no        | varchar(20) | YES  | UNI | NULL    |                |
| id        | int(11)     | NO   | PRI | NULL    | auto_increment |
+-----------+-------------+------+-----+---------+----------------+
```
# 结果页面
<font face="楷体" size="+1" color="#000033">主菜单
![在这里插入图片描述](https://img-blog.csdnimg.cn/2021031521022743.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2OTM3Njg0,size_16,color_FFFFFF,t_70#pic_center)
## 增加
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210315210256112.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2OTM3Njg0,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210315210256181.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2OTM3Njg0,size_16,color_FFFFFF,t_70)
## 查询
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210315210256199.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2OTM3Njg0,size_16,color_FFFFFF,t_70)![在这里插入图片描述](https://img-blog.csdnimg.cn/20210315210256164.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2OTM3Njg0,size_16,color_FFFFFF,t_70)
## 删除
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210315210256208.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2OTM3Njg0,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210315210256180.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2OTM3Njg0,size_16,color_FFFFFF,t_70)
## 修改
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210315210256156.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2OTM3Njg0,size_16,color_FFFFFF,t_70)

![在这里插入图片描述](https://img-blog.csdnimg.cn/20210315210256115.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2OTM3Njg0,size_16,color_FFFFFF,t_70)

## 排序
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210315210256230.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2OTM3Njg0,size_16,color_FFFFFF,t_70)
## 统计
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210315210256220.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2OTM3Njg0,size_16,color_FFFFFF,t_70)
## 注册
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210315210255540.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2OTM3Njg0,size_16,color_FFFFFF,t_70)
## 登录
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210315210255384.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2OTM3Njg0,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210315210256158.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2OTM3Njg0,size_16,color_FFFFFF,t_70)