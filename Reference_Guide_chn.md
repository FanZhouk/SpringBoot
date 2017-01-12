[TOC]

> 本文是[Spring Boot 官方文档](http://docs.spring.io/autorepo/docs/spring-boot/current/reference/htmlsingle)的翻译。

# 第一部分 Spring Boot文档

文档结构介绍。全是废话。

# 第二部分 快速开始 

## 8. Spring Boot简介

Spring Boot可以快速创建独立的、生产级别的Spring应用。我们采用了Spring平台以及第三方库，可以让你使用的时候做最少的工作。多数Spring Boot应用几乎不用Spring配置。

部署方式：提供jar包部署和war包部署，同时也提供运行着"Spring scripts"的命令行工具。

我们的主要目标：

* 对所有Spring开发提供更快、高可用的使用体验。
* 遵从默认配置，也可以快速自定义配置。
* 提供一系列非功能性特性，以适应大型项目（如内嵌服务器、安全等）。
* 配置无代码，无xml。

## 9. 系统要求

Spring Boot 1.4.3默认要求Java1.7以及Spring Framework 4.3.5及以上版本。建议使用java 1.8。

Servlet容器：最低Tomcat 7，Servlet 3.0，Java 6。

## 10. 安装Spring Boot

可用经典的Java开发工具，也可以安装命令行工具（必须保证安装了Java SDK 1.6或以上版本）。

### 10.1 安装简介

只要在项目library中包含`spring-boot-*.jar`即可。当然可以直接下载jar包，但更推荐使用Maven或Gradle。以下介绍Maven安装方式，Gradle省略。

Spring Boot适配Apache Maven 3.2或更新版本。

* 使用的groupId应配置为： `org.springframework.boot` 。
* Maven的POM文件需要继承自 `spring-boot-starter-parent`项目。
* 需要声明对一个或多个Starters的依赖。
* Spring Boot也提供可选的Maven插件来创建可执行jar文件。

典型`pom.xml`文件示例：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.example</groupId>
    <artifactId>myproject</artifactId>
    <version>0.0.1-SNAPSHOT</version>

    <!-- Inherit defaults from Spring Boot -->
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>1.4.3.RELEASE</version>
    </parent>

    <!-- Add typical dependencies for a web application -->
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
    </dependencies>

    <!-- Package as an executable jar -->
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
```

### 10.2 安装Spring Boot命令行

// TODO 暂时省略

### 10.3 Spring Boot升级

[Spring Boot各版本](github.com/spring-projects/spring-boot/wiki)

## 11. 开发第一个Spring Boot应用

本节将开发一个"Hello World"网站应用，并强调一些Spring Boot的关键特性。本项目使用Maven构建。

构建项目前，先保证已经安装Java和maven！命令行代码如下：

Java：`java -version`

maven：`mvn -v`

注：示例项目要放在单独文件夹下。以下我们默认你已经创建了一个文件夹，作为“当前目录”。

### 11.1 创建POM文件

项目第一步，从创建Maven的`pom.xml`文件开始。`pom.xml`就像一个菜谱，用于指引你构建项目。打开任意编辑器，输入以下内容：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.example</groupId>
    <artifactId>myproject</artifactId>
    <version>0.0.1-SNAPSHOT</version>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>1.4.3.RELEASE</version>
    </parent>

    <!-- Additional lines to be added here... -->

</project>
```

`pom.xml`文件创建好以后，命令行进入文件目录，输入`mvn package`命令，可以测试pom文件是否正确，并自动下载对应的jar包（若有警告可以暂时忽略）。

这时完全可以利用IDE自动创建一个Maven项目了，但下面我们仍将使用手动的方式完成这个项目。

### 11.2 添加classpath依赖

Spring Boot提供了大量的“Starters”来简化我们添加jar包（相当于jar包的集合）。上面一步我们已经在`parent`块中使用了 `spring-boot-starter-parent` 这个starter，它提供了Maven一些默认的配置，也提供了`dependency-management`模块，我们可以省略`version`标签（见13.1节）。

其他的starter则简单地提供了在一些特定项目中用到的依赖。由于我们开发的是一个网站应用，我们还要加上这个依赖：`spring-boot-starter-web`。

添加特定依赖的代码：

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
</dependencies>
```

**查看依赖结构**：我们可以在命令行中利用 `mvn dependency:tree`命令查看项目依赖的树形结构。

### 11.3 写代码

现在开始写java代码。由于Maven会默认编译`src/main/java`目录下的源代码，所以要按照这个来创建目录，比如新建一个java文件：`src/main/java/Example.java`。java代码如下：

```java
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
public class Example {

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Example.class, args);
    }
}
```

代码虽然没几行，但作用却不小。下面我们来一句句分析。

* `@RestController`和`@RequestMapping`注解

  * `@RestController`注解：我们都熟知这个注解，它告诉人们这里是业务代码，并告诉Spring，这个类扮演着很重要的角色。Spring知道这是一个“控制器类（`@Controller`）”，因此当有请求发送过来，就会用有这个注解的类去处理。
  * `@RequestMapping`注解：提供了“路由（routing）”信息。它会告诉Spring，任何带有“/”路径的HTTP请求，都要映射到`home`方法上来。
  * 这两个注解都是Spring MVC的注解，对于Spring Boot并没有任何特殊之处。

* `@EnableAutoConfiguration`注解

  * 有了这个注解，Spring会根据你已经添加的jar包去“猜测”你想要如何配置。比如这个项目添加的`spring-boot-starter-web`，这个依赖包括了Tomcat和Spring MVC，那么Spring就会猜测你正在构建一个网站应用，并建立好Spring。
  * Starters和Auto-Configuration的关系：Auto-Configuration会根据Starters来做一些初始配置，但两者并不是完全绑定的。你仍然可以加入其它的jar包，Spring Boot仍然会尽力自动配置你的应用。

* `main`方法

  只是一个标准的、遵循Java规范的方法。其中的`run`方法，代理了Spring Boot的`SpringApplication`类，这个类会引导我们的应用开启Spring，并开启自动配置好的Tomcat web服务器。这个方法传递了`Example.class`，这样就告诉`SpringApplication`谁才是主要的Spring组成部分。


### 11.4 运行示例

到现在为止，我们的应用已经可以运行了。由于pom文件中已经添加了`spring-boot-starter-parent`这个依赖，我们已经有了可用的run方法。

命令行，项目根目录下，运行命令：`mvn spring-boot-run`，即可启动。

访问：[http://localhost:8080](http://localhost:8080)即可。

按`ctrl-c`可以优雅地停止项目。

### 11.5 创建可执行jar文件

我们现在要创建一个完整、独立的可执行jar文件来结束这次示例。在生产环境中我们可以直接运行这个jar文件。

可执行jar文件（有时也称fat jars），包括了所有已编译文件和所有依赖的包。

**添加插件**：要创建可执行jar文件，我们还需要向`pom.xml`文件中添加一个`spring-boot-maven-plugin`插件，代码如下：

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
    </plugins>
</build>
```

**创建jar文件**。运行命令：`mvn package`，运行结束后在target目录下会有一个 `myproject-0.0.1-SNAPSHOT.jar`文件，大小在10M左右。这就是创建好的jar文件。

查看jar文件的内容：运行命令：`jar tvf target/myproject-0.0.1-SNAPSHOT.jar`，会列出jar文件下所有的依赖包以及编译后的文件。

**执行jar文件**：运行命令：`java -jar target/myproject-0.0.1-SNAPSHOT.jar`，等待项目启动即可。

# 第三部分 使用Spring Boot

这一部分的内容要更细致一些，涵盖许多话题，比如构建系统、自动配置，以及如何运行应用，同时还包含了一些Spring Boot的最佳实践。

## 13. 构建系统

“构建系统”即依赖管理工具，主要推荐使用Maven或Gradle进行依赖管理，Ant也可以使用，但不完全支持。这里只翻译Maven部分。

### 13.2 Maven

Maven用户可以通过继承依赖`spring-boot-starter-parent`来获取合理的默认配置。该依赖提供如下的默认特性：

* java 1.6编译器；
* UTF-8编码集；
* 一个依赖管理模块，常用的依赖允许省略`<version>`标签；
* 合理的资源过滤；
* 合理的插件配置；
* //TODO

#### 13.2.1 继承自父POM

代码与11.2节中的`pom.xml`文件配置方式相同。为方便下面做对比，代码复制如下。

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>1.4.3.RELEASE</version>
</parent>
```

#### 13.2.2 不继承父POM 

不是所有人都想要继承父POM文件。若你的公司有规定的父POM文件，或者仅仅想明确地声明所有配置的时候，可以利用以下方法。

如果不想继承 `spring-boot-starter-parent`，你仍然可以享受依赖管理的好处（但插件管理就不适用了）。使用一个`scope=import`的依赖。实际上相当于把继承的父POM文件当做一个普通的jar文件引入。代码如下：

```xml
<dependencyManagement>
     <dependencies>
        <dependency>
            <!-- Import dependency management from Spring Boot -->
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-dependencies</artifactId>
            <version>1.4.3.RELEASE</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

但这种方式不允许覆盖独立的依赖（即替换父POM文件中某一个jar文件的版本）。为了达到这样的目的，可以在`<dependencies>`中添加多个`<dependency>`标签，来实现覆盖的功能。

注意，原始的`spring-boot-dependencies`必须在最下面！即添加的时候要在注释处添加。

#### 13.2.3 改变Java版本

 `spring-boot-starter-parent`在选择Java版本时极为保守。若想要改变Java版本，可以添加`java.version`配置。代码如下：

```xml
<properties>
    <java.version>1.8</java.version>
</properties>
```

#### 13.2.4 使用Spring Boot Maven插件

Spring Boot包含了一个Maven插件，它可以把整个项目打包为一个jar文件。添加这个插件的代码如下：

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
    </plugins>
</build>
```

回忆一下，打包的命令是：`mvn package`

### 13.5 Starters

一个Starter就是一组功能相近的依赖，你可以在项目中包含任意Starter。你可以得到Spring及相关技术的”一站式“的依赖，而不需要一个一个地复制jar文件。比如你想使用Spring和JPA做数据库，仅需要包含 `spring-boot-starter-data-jpa` 依赖即可。

命名规范：所有的starter都有一个固定格式：`spring-boot-starter-*`（当然，除了核心Starter，就叫做`spring-boot-starter`）

Starter有很多，Application类的如：thymeleaf, ws(Web Services), mail, web(RESTful, Spring MVC), test(JUnit), jdbc, validation, aop, security, data-jpa, mongodb等等，Production类，还有Technical类的如：jetty, logging, tomcat, log4j2...要查看全部starter，看[这里](http://docs.spring.io/autorepo/docs/spring-boot/current/reference/htmlsingle/#using-boot-starter)。

## 14. 结构化代码

Spring Boot不要求任何特殊的代码结构就可以正常工作，但有一些最佳实践仍然很有帮助。

### 14.1 使用默认包（default package）

若一个类里面没有“包”的声明语句，就说明它属于“默认包”。通常不推荐使用默认包，并且应该尽量避免这样使用。因为对Spring Boot应用来说，使用 `@ComponentScan` ，`@EntityScan` 或 `@SpringBootApplication`注解的时候会产生一些问题（此时jar包中的每一个类都会被读取到）。

推荐按照Java的传统命名习惯，使用“反转域名”（如`com.example.project`）。

### 14.2 定位main应用类

通常推荐你将main应用类（即带有项目启动的main方法的类）放置在比其他类更高一层的包中。注解 `@EnableAutoConfiguration`应放置在main方法所在的类上，这隐含地为这个项目定义了一个基础的“搜索包”。比如你在写一个JPA应用，那么注解了 `@EnableAutoConfiguration`的类所在的包，就会被用于搜索 `@Entity`注解。

把main方法放在根目录包中，当你在使用 `@ComponentScan` 注解时又省略了一个 `basePackage`属性。此时仍然可以在main方法所在类上使用 `@SpringBootApplication` 注解。

比如分包结构如下：

```
com
 +- example
     +- myproject
         +- Application.java
         |
         +- domain
         |   +- Customer.java
         |   +- CustomerRepository.java
         |
         +- service
         |   +- CustomerService.java
         |
         +- web
             +- CustomerController.java
```

其中 `Application.java`文件声明了`main`方法和基础的 `@Configuration`注解。

```java
package com.example.myproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

## 15. 配置类

Spring Boot支持基于java的配置。尽管你用XML文件配置，然后调用 `SpringApplication.run()`也是可以的，但我们更推荐的方式是：使用 `@Configuration`类。通常定义了`main`方法的类也可以作为主要的 `@Configuration`类。

### 15.1 引入外部配置类

不需要把所有的 `@Configuration`注解放在一个类中。你也可以用 `@Import`注解引入外部配置类。同样，你可以使用 `@ComponentScan`注解自动加载所有的Spring组件，包括 `@Configuration`类。

### 15.2 引入xml配置

如果你一定要使用xml注解，推荐你仍然从一个 `@Configuration`类开始。这样接下来就可以使用 `@ImportResource`注解来加载xml配置文件了。

## 16. 自动配置

Spring Boot的自动配置会自动根据你所加入的jar依赖包去配置。

使用方法：在`@Configuration`类上添加 `@EnableAutoConfiguration` 或 `@SpringBootApplication`注解，设置为自动配置。

不论任何时候，都要加上`@EnableAutoConfiguration`注解。通常建议把它加载`@Configuration`类中。

### 16.1 逐步取代自动配置

自动配置是非入侵性的，在任何位置你都可以定义自己的配置取代自动的配置。比如添加了一个 `DataSource` bean，那么默认内嵌的数据库支持就会为它让路。

如果你需要找出当前正在应用哪一个自动配置，为什么这样应用，就要用`--debug`模式启动项目。这样会启动debug日志。

### 16.2 禁用指定的自动配置

如果你发现有一些自动配置的类不是你想要的，可以使用 `@EnableAutoConfiguration`的` exclude`属性去禁用。

```java
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.autoconfigure.jdbc.*;
import org.springframework.context.annotation.*;

@Configuration
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class MyConfiguration {
}
```

如果这个类不在classpath上，可以给注解使用`excludeName`属性，指定类的完整路径。最后，你也可以通过 `spring.autoconfigure.exclude`属性，控制自动配置类的列表来禁用自动配置。

## 17. Spring Beans和依赖注入

你可以使用任意标准的Spring Framework技术来定义你的bean和他们注入的依赖。简单起见，我们经常使用`@ComponentScan`寻找bean，搭配 `@Autowired`构造器来注入。

如果你按照上面的方法安排代码（把main放在根目录包中），那么在添加`@ComponentScan`的时候无需任何其他代码。所有其他的部分 （`@Component`，`@Service`， `@Repository`， `@Controller`等）都会自动注册为Spring Bean。

下面是一个 `@Service` bean的例子，使用构造器注入（ constructor injection）来获取一个需要的bean。

```java
package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseAccountService implements AccountService {

    private final RiskAssessor riskAssessor;

    @Autowired
    public DatabaseAccountService(RiskAssessor riskAssessor) {
        this.riskAssessor = riskAssessor;
    }

    // ...

}
```

## 18. 使用`@SpringBootApplication`注解

很多Spring Boot开发者都会在main方法的类上使用三个注解： `@Configuration` ， `@EnableAutoConfiguration` 和 `@ComponentScan` 。由于这些注解经常会一起使用（尤其是你按照上面的最佳实践去开发），Spring Boot提供了一个方便的注解来替代：`@SpringBootApplication`。这个注解与使用上面三个注解的默认配置效果相同。

```java
package com.example.myproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
```

## 19. 运行你的应用

将项目打成jar包并使用HTTP服务最大的优势在于，你可以向运行其他应用一样运行这个项目。对项目进行debug也很容易，不需要任何特殊的IDE插件或扩展。

本节只适用于将项目打包为jar文件，若使用其他方式部署，可跳过本节。

//TODO

### 19.1 在IDE上运行 

### 19.2 打jar包运行

### 19.3 使用Maven插件

### 19.5 热部署

## 20. 开发者工具

//TODO

## 21. 打包项目，投入生产

可执行的jar包可直接用于生产部署。由于jar包是独立的，因此也适用于基于云的部署。

关于更多生产就绪（production ready）方面的特性，见第五部分。

# 第四部分 Spring Boot的特性 

这一部分会更深入Spring Boot的细节。

## 23. SpringApplication

SpringApplication类提供了一个方便的方式去引导Spring应用从`main`方法开启一个项目。多数情况下，你只需要把一切交给 `SpringApplication.run`方法。

```java
public static void main(String[] args) {
    SpringApplication.run(MySpringConfiguration.class, args);
}
```

当你看到一个大大的“Spring”，就说明项目启动成功了。默认显示出`INFO`日志信息，包括一些相关的启动细节。

//TODO **<u>下面暂时只列出目录和部分关键代码，需要的时候再翻译。</u>**

23.1 启动失败

23.2 自定义标题

23.3 自定义SpringApplication

```java
public static void main(String[] args) {
    SpringApplication app = new SpringApplication(MySpringConfiguration.class);
    app.setBannerMode(Banner.Mode.OFF);
    app.run(args);
}
```

23.4 流畅的构建API

23.5 应用的事件和监听

```java
new SpringApplicationBuilder()
        .sources(Parent.class)
        .child(Application.class)
        .bannerMode(Banner.Mode.OFF)
        .run(args);
```



23.6 网络环境

23.7 获取应用参数

23.8 使用ApplicationRunner或CommandLineRunner

23.9 退出应用

23.10 管理特性

## 24. 外化配置

Spring Boot允许外化你的配置，以便于在不同环境上使用。你可以使用配置文件、YAML文件、环境变量和命令行参数来外化配置。利用`@Value`注解，属性的值可以直接注入到bean中，这是通过Spring的`Environment`抽象，通过 `@ConfigurationProperties`实现的（见24.7节）。

//TODO

24.1 配置随机值

24.2 获取命令行属性

24.3 属性文件

24.4 指定profile配置

24.5 配置占位符

24.6 使用YAML代替配置

24.7 类型安全的配置

## 25. Profile

Spring Profile提供了一种将配置部分分离的方法，让一部分配置只适用于特定的环境。任何 `@Component`或`@Configuration`注解都可以被标记为`@Profile`来限制何时加载。

```java
@Configuration
@Profile("production")
public class ProductionConfiguration {
    // ...
}
```

在普通的Spring中，你可以使用 `spring.profiles.active` `Environment` 属性来指定哪个profile处于活动状态。在Spring Boot中，用Spring传统的方法指定配置是没有任何问题的，比如可以在`application.properties`文件中这样指定：

```
spring.profiles.active=dev,hsqldb
```

或者在命令行中指定：

 `--spring.profiles.active=dev,hsqldb`

// TODO

25.1 添加活动的profile

25.2 动态设置profile

25.3 指定profile配置文件

## 26. 日志

Spring Boot使用[Commons Logging](https://commons.apache.org/logging)实现内部日志，但把内部的日志实现是开放的。默认的配置是使用[Java Util Logging](https://docs.oracle.com/javase/7/docs/api/java/util/logging/package-summary.html)、[Log4J2](https://logging.apache.org/log4j/2.x/)和[Logback](http://logback.qos.ch/)。每一种都是预配置输出到控制台，以及可选择输出到文件的。

默认情况下，如果使用了Starters，那么LogBack会用于记录日志。同时包括了合适的LogBack路径（routing），以确保不论使用 Java Util Logging，Commons Logging，Log4J还是 SLF4J ，都可以正确工作。

// TODO

26.1 日志格式

26.2 控制台输出

26.3 文件输出

26.4 日志级别

26.5 自定义日志配置

26.6 Logback扩展

## 27. 开发网络应用

Spring Boot十分适合网络应用的开发。你可以轻松地用内嵌的Tomcat，Jetty或Undertow建立一个独立的HTTP服务器。多数的网络应用都会使用`spring-boot-starter-web`模块来快速运行。

### 27.1 Spring MVC

Spring Web MVC framework（通常简称为Spring MVC）是一个富“模型-视图-控制器”的网络框架。Spring MVC让你创建特殊的`@Controller`或`@RestController` bean来处理接收到的HTTP请求。Controller内部的方法会被映射到 `@RequestMapping` 注解对应的请求。

下面是典型的`@RestController`示例，用于处理JSON数据：

```java
@RestController
@RequestMapping(value="/users")
public class MyRestController {

    @RequestMapping(value="/{user}", method=RequestMethod.GET)
    public User getUser(@PathVariable Long user) {
        // ...
    }

    @RequestMapping(value="/{user}/customers", method=RequestMethod.GET)
    List<Customer> getUserCustomers(@PathVariable Long user) {
        // ...
    }

    @RequestMapping(value="/{user}", method=RequestMethod.DELETE)
    public User deleteUser(@PathVariable Long user) {
        // ...
    }

}
```

Spring MVC是Spring框架核心的一部分，更详细的信息在[Spring文档](http://docs.spring.io/spring/docs/4.3.5.RELEASE/spring-framework-reference/htmlsingle#mvc)。 [spring.io/guides](https://spring.io/guides)也有一些包含Spring MVC的项目。

#### 27.1.1 Spring MVC自动配置

Spring Boot提供对Spring MVC的自动配置，在大多项目中完成的很好。

自动配置在Spring默认的配置上，又添加了如下的特性：

* 包含了`ContentNegotiatingViewResolver`和`BeanNameViewResolver`的bean。
* 支持静态资源服务，包括对WebJars的支持（见下面）。
* 自动注册`Converter`，`GenericConverter`，`Formatter` 的bean。
* 支持`HttpMessageConverters`（见下面）。
* 自动注册`MessageCodesResolver`（见下面）。
* 支持静态的`index.html`。
* 支持自定义`Favicon`。
* 自动使用`ConfigurableWebBindingInitializer` bean（见下面）。


如果你想要完全控制Spring MVC，可以添加自己的`@Configuration`和`@EnableWebMvc`。

// TODO

27.1.2 HttpMessageConverters

Spring MVC使用`HttpMessageConverter`接口来转换HTTP请求和响应。

27.1.3 自定义JSON序列化器和反序列化器

27.1.4 MessageCodesResolver

### 27.1.5 静态内容

默认情况下，Spring Boot会从一个固定的目录中获取静态内容，这个目录是： classpath下的`/static` 或 `/public` 或 `/resources` 或 `/META-INF/` ，或 `ServletContext` 的根目录。由于使用了Spring MVC的 `ResourceHttpRequestHandler` ，你可以通过添加自己的 `WebMvcConfigurerAdapter` 并覆盖 `addResourceHandlers` 方法，来改变这个行为。

在独立的web应用中，容器内默认的servlet也是内嵌的，相当于一个备用物品，如果Spring不去处理，才会去 `ServletContext` 根目录下找。除非手动修改过MVC的默认配置，多数情况下这不会发生，因为Spring总是会通过 `DispatcherServlet` 处理请求。

可以使用 `spring.resources.staticLocations` 来自定义静态资源路径（默认值是一串路径列表，把它替换掉）。如果这样做的话，默认的欢迎页面路径会变为你的自定义位置，因此如果在你的启动路径下面有`index.xml` ，那它就是应用的首页。

除了上面的标准静态资源位置，对于 [Webjars content](http://www.webjars.org/) 有一个特例。如果项目被打包为Webjars格式，那么任何带有 `/webjars/**` 路径的资源都会被搜索到。

> 如果项目被打成jar包，不要使用 `src/main/webapp` 路径！因为尽管这个路径也是一个公共标准，但只有打成war包的时候才起作用。如果是jar包的话，这个路径会被大多数构建工具默默地忽视掉。

Spring Boot同样支持Spring MVC提供的更先进的资源处理特性，允许以下的用例，如清除缓存的静态资源，或Webjars的未知版本URL（version agnostic URLs for Webjars）等。











































# 备注

GitHub命令行push代码：[push](http://blog.csdn.net/steven6977/article/details/10567719) 
