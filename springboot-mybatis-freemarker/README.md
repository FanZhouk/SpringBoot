# springboot-mybatis-freemarker

## 1. 项目功能

使用Spring Boot结合MyBatis查询数据，把数据发送给前端freemarker来展示。

## 2. 注意事项

- **json处理**：使用阿里的 [fastjson](https://github.com/alibaba/fastjson) 。

- **后台与页面传值的两种方式**

  * `Model` ：与Spring MVC相同，利用 `org.springframework.ui.Model.addAttribute(String, Object)` 方法插入值。
  * `ModelMap` ：同样可以利用 `addAttribute()` 方法插入值，还可以用 `put()` 方法，因为 `ModelMap` 继承自 `LinkedHashMap` ，与操作哈希表的方法相同。
  * 接收方式：freemarker支持EL表达式，即 `${value}` 格式取数据。

  注意：不要传json！直接把对象传过来，freemarker可以通过点号（ `.` ）获取到属性。

- **改变默认端口**：见主类 `com.fzk.Application` ，利用 `org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer.setPort(int)` 方法改变默认端口。

- **myeclipse安装freemarker编辑器插件**： [链接](http://www.cnblogs.com/gossip/p/5038487.html) 

- **freemarker的循环**：语法格式如下：

  ```html
  <#list data as being>
  <tr><td>${being.name}<td>${being.price} Euros
  </#list>
  ```

  其中 `data` 是从后台传回来的数据的键值。

## 3. 运行方法

* 准备数据库：运行 `src/main/resources/tb_user.sql` 文件。
* 启动项目： `mvn spring-boot:run` 。
* 访问页面
  * 访问 `http://localhost:9090/getJson` ，可以看到Json数据。
  * 访问 `http://localhost:9090/getData` ，可以列表形式展现出来。