# statusResource

一个利用Spring Boot访问静态资源的程序。

## 1. 项目功能

利用Spring Boot访问指定目录的静态资源。

## 2. 注意事项 

* **默认路径**

  Spring Boot中代码默认存放在`PROJECT/src/main/java`目录下。


* **静态资源路径问题**

  默认情况下，静态资源必须存放在指定的目录下才会被加载。这里放在了classpath下面的`resouces/static`中，又因为所有代码的默认路径是`src/main` ，因此完整的资源路径应该是：`PROJECT/src/main/resources/static/xx.html` 。

  但实践发现，如果路径配成了`.../main/static/xx.html` ，仍然无法显示出来... don't know why.


* **POM文件中的配置问题**
  * groupId：这个属性表示包结构，通常写成“反转域名”，如`com.spring.xxx` 。但是实践发现，不管写什么，项目都能正常工作...
  * artifactId：项目的唯一标识符，即项目名称。


* **注解**

  本项目在`main`方法的类上用了 `@SpringBootApplication` 注解。使用这个注解与同时使用 `@Configuration` ， `@EnableAutoConfiguration` 和 `@ComponentScan` 三个注解的效果相同。

## 3. 运行方法

与上个项目相同，命令行进入项目目录， `mvn package` 打包，然后用 `java -jar target/xxx.jar` 即可运行项目。