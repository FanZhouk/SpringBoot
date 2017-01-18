# todolist

## 1. 项目功能

这是一个todo list应用，可用于记录待办事项、查看已完成事项、将事项置顶。

使用Spring Boot+mybatis+freemarker实现。

未使用任何前端框架，仅用freemarker和JavaScript实现。

## 2. 注意事项

* **Controller中重定向**：在控制器中要进行重定向，返回的字符串如下： `return "redirect:/...";` ，斜杠后为要发送请求的路径。

* **js中发送请求**：使用 `location.href="/..."; ` 斜杠后为要发送请求的路径。

* **目录结构**：

  ```
  │  pom.xml
  │
  └─src
      └─main
          ├─java
          │  └─com
          │      └─fzk
          │          │  Application.java
          │          │  Global.java
          │          │
          │          ├─config
          │          │      MyBatisConfig.java
          │          │
          │          ├─controller
          │          │      ItemController.java
          │          │
          │          ├─dao
          │          │      ItemDao.java
          │          │
          │          ├─entity
          │          │      Item.java
          │          │
          │          ├─mapper
          │          │      ItemMapper.java
          │          │
          │          └─service
          │                  ItemService.java
          │
          ├─resources
          │  │  application.properties
          │  │  item.sql
          │  │
          │  └─mapper
          │          ItemMapper.xml
          │
          └─webapp
                  itemForm.ftl
                  itemList.ftl
  ```

  ​

## 3. 运行方法

* **准备数据库**：使用MySQL数据库，执行 `todolist/src/main/resources` 文件。
* **启动项目**： 
  * `mvn spring-boot:run` 运行即可。
  * 也可以导入myeclipse中启动。
* **访问页面**：访问 `http://localhost:9090/index` 即可。

