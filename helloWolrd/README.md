# Hello World 

这是Spring Boot的Hello World程序。

## 1. 项目功能

​完成了浏览器发送请求，Controller接收请求，返回一句话并显示在浏览器上的功能。

## 2. 运行方法

1. 将项目下载到本地。
2. **生成jar文件**。命令行进入项目目录，运行`mvn package`命令，给项目生成jar文件。这时在项目根目录下会生成一个target目录，里面的`myproject-0.0.1-SNAPSHOT.jar`文件就是该项目的jar文件。
3. **运行jar文件**。运行命令`java -jar target/myproject-0.0.1-SNAPSHOT.jar`，等待项目启动完成。
4. **访问页面**。运行成功后，浏览器中访问`http://localhost:8080`即可看到输出内容。