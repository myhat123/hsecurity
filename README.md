## 学习内容

    hsecurity01 shiro官网示例 安全机制包含user(password), role
        http://shiro.apache.org/tutorial.html
        http://shiro.apache.org/architecture.html
    
    hsecurity02 Introduction to Apache Shiro
        不用ini配置文件，直接构建realm
        https://github.com/eugenp/tutorials/tree/master/apache-shiro

    hsecurity03 继承JdbcRealm，自定义Realm
    hsecurity04 采用SimpleAccountRealm 只有user(password), role两部分，不包含permission

    hsecurity05 JdbcRealm
        直接看shiro源码，有3个表users, user_roles, roles_permissions
        根据hsecurity01的shiro.ini来写入表
        增加spring-boot-starter-jdbc自动拿到DataSource的Bean
        https://github.com/apache/shiro/blob/master/core/src/main/java/org/apache/shiro/realm/jdbc/JdbcRealm.java

## 数据库

    sudo su
    su - postgres
    createdb -E utf8 -O jxyz hsctest
    psql -h localhost -U jxyz -d hsctest < schema.sql