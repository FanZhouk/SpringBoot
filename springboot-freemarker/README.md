# springboot-freemarker

## 1. 项目功能

利用Spring Boot访问freemarker文件（ftl格式），并实现从后台传递一个数据给前台。

## 2. 注意事项

- **ftl存放路径**

  ftl的存放路径与html路径有所不同。ftl文件的路径在 `application.properties` 文件中进行配置。本项目中存放位置是： `/src/main/webapp/hello.ftl` ，对应的配置文件写作：

  ```properties
  spring.freemarker.template-loader-path: /
  spring.freemarker.suffix: .ftl
  ```

- **项目整体路径**

  ```
  │  pom.xml
  │
  └─src
      └─main
          ├─java
          │  └─com
          │      └─fzk
          │          │  WebApplication.java
          │          │
          │          └─controller
          │                  HelloController.java
          │
          ├─resources
          │      application.properties
          │
          └─webapp
                  hello.ftl
  ```

  ## 3. 运行方法

  启动项目： `mvn spring-boot:run` 

  浏览器访问： `http://localhost:8080/hello` 