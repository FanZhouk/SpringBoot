# springboot-mybatis

一个将Spring Boot与MyBatis结合访问MySQL数据库的应用。[参考链接](http://www.cnblogs.com/java-zhao/p/5350021.html) 

## 1. 项目功能

将Spring Boot与MyBatis结合，通过get方式传参，向数据库中插入一条用户记录。

## 2. 运行方法

1. **准备数据库**：数据库的配置在 `/springboot-mybatis/src/main/resources/application.properties` 文件中，可根据需要修改。建表语句如下：

   ```sql
   DROP TABLE IF EXISTS `tb_user`;
   CREATE TABLE `tb_user` (
     `id` int(11) NOT NULL AUTO_INCREMENT,
     `username` varchar(20) DEFAULT NULL,
     `password` varchar(20) DEFAULT NULL,
     `account` decimal(10,2) DEFAULT NULL,
     PRIMARY KEY (`id`)
   ) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
   ```

2. **启动项目**：下载项目到本地，运行 `mvn spring-boot:run` 命令。

3. **插入数据**：在浏览器中输入： `http://localhost:8080/user/addUser?username=1234aaa&password=4321` ，访问后会发现数据库中多了一条用户数据，用户名为1234aaa，密码为4321。