#依赖基础镜像
FROM java:8
#将当前目录下的jar包复制到docker容器的 / 目录下
ADD shop-0.0.1-SNAPSHOT.jar /shop_back.jar
#运行过程中创建一个shop_back.jar文件
RUN bash -c 'touch /shop_back.jar'
#指定端口号
EXPOSE 8888
#指定docker容器启动时运行的jar包
ENTRYPOINT ["java", "-jar", "/shop_back.jar"]
#指定维护者 用户
MAINTAINER yangtzboy