## 学习内容

    java 基本知识
    java 包及命令行构建分离 [helloTutor](https://github.com/myhat123/helloTutor)
    okhttp 获取页面
    gson 解析 json 格式
    jdbc 数据库连接访问

    java 8 特性 (lambda, 日期, forEach等)

    weather09 多线程获取天气预报
    weather11 jdbc 写入数据库
    weather14 jdbc 读取数据库，并转为对象
    weather15 单元测试 entity, dao, service分离
    weather16 spring jdbc template
    weather17 spring ioc 容器手动装配(完全掌握Bean之间的依赖关系)
        ProvinceService 依赖 PlainProvDao, PlainCityDao, 及setDataSource
    weather18 spring ioc 容器自动装配(对依赖关系自动处理)
        @Service, @Repository, @Autowired(目前理解可自动处理Bean依赖)
    weather19 直接从App中自动装配, 并完成spring下的junit测试
        hamcrest中assertThat作为断言处理
    weather20 spring mvc + pebble template
        gradle :weather20:jettyRun
    weather21 jpa写入数据
    weather22 jpa读取数据
    weather23 spring data jpa 读取数据
        配置增加 @EnableJpaRepositories, 采用 CrudRepository
        fetch=FetchType.EAGER与LAZY的区别
        集成 spring jdbc template 查询
    weather24 沿袭weather23代码，加spring boot
        spring boot 自定义配置 @EnableJpaRepositories, @EntityScan, @Bean
        通过spring官网提供的gradle脚本改造, gradle :weather24:bootJar 打包
    weather25 spring boot初始化，改造之前的代码包结构
        WeatherApplication.java，作为包根目录最上层，@SpringBootApplication无需自定义配置
        gradle :weather25:bootRun 运行， gradle :weather25:bootJar 打包
        spring boot 会自动装配很多默认Bean，其中包括jdbcTemplate, dataSource
    weather26 spring boot web代码
        增加controller，并采用pebble template
    weather27 采用Hibernate 解决排序问题
        在findAll()使用了 JpaRepository
        在实体中的一对多利用 @OrderBy
    weather28 在weather27的基础上对页面进行处理 
        js中的JQuery库，使得页面成动态页面

    weather29 采用@Query方式 优化省市关联查询 
        在JpaRepository增加
        @Query("select distinct s from Province s left join fetch s.cities a ")

    weather30 采用@EntityGraph 解决查询SQL语句N+1的问题
        在entity增加了,并且增加在@Entity上面
        @NamedEntityGraph(name = "Province.cities", attributeNodes = {
            @NamedAttributeNode("cities")
        })

        在JpaRepository增加
        @Override
        @EntityGraph(value = "Province.cities")
        List<Province> findAll(Sort sort);


    annotation 注解 (lombok等)
    设计模式
    j2ee servlet
    控制反转、依赖注入 (spring ioc, google guice)
    页面模板引擎 (jtwig, pebble, freemarker)

    另: 了解gradle, groovy, jsp等
    注: zetcode.com 网站有相当多的代码示例

## 数据库建立

    sudo su
    su - postgres
    createuser -P jxyz (1234)
    createdb -E utf8 -O jxyz test 

    test province表, pcity表字段建立随意
    jpatest province表, city表字段约束规范

## 多项目构建

    采用 gradle 构建
    settings.gradle 包含子项目
    weather09, weather11, weather14 子项目中各包含 build.gradle 文件

    使用 shadowJar 打包
    spring boot采用官方提供的gradle插件， 使用 bootJar 打包

## 参数外部文件

    含在 jar 包内，如 weather14 的 db.properties, weather11 的 _city.json
    读取时需要采用特别的处理 classload.getResourceAsStream()

## 执行gradle子项目

    清理构建
    gradle :xxxx:clean

    只编译
    gradle :weather15:compileJava

    编译打包
    gradle :weather15:build

    运行
    gradle :weather15:run

    测试
    gradle :weather15:test

    执行jar打包
    在子项目中build.gradle中plugins增加插件
    
    plugins {
        id 'com.github.johnrengelman.shadow' version '5.0.0'
        id 'java'
        id 'application'
    }

    gradle :weatherxx:shadowJar

    在终端执行命令行
    java -jar weather15/build/libs/jar包名

## Reload Resources

    在build.gradle里面新加：
        bootRun {
        sourceResources sourceSets.main
        }

    在dependencies中写：
        runtimeOnly 'org.springframework.boot:spring-boot-devtools'

## 重启Spring Boot

    编译:
        gradle :weather28:compileJava

    重新编译bootrun自动重启，刷新页面即可

## 自动识别页面模板(application.properties)

    pebble.cache=false

## lombok 简化java代码

    参照lombok gradle官方文档

    dependencies中：
        compileOnly 'org.projectlombok:lombok:1.18.8'
	    annotationProcessor 'org.projectlombok:lombok:1.18.8'