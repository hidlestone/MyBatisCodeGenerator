## MyBatisCodeGenerator

> 此项目提供相关插件，可在 MyBtis　代码生成器基础上，修改定制自己想要的生成代码命名格式风格。支持 IBatis、MyBatis 代码生成。 

##### 附注：　　
使用 mybatis-generator 自动生成文件，默认名都是XXXExample.java，XXXMapper.java，XXXMapper.xml，
如果要求是XXXDao.java，XXXCondition.java，XXXDao.xml 无法实现。

1.修改 db.properties 数据库配置，代码生成位置
```bash
#=================MySQL=================
jdbc.driverClassName=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/mytest?characterEncoding=UTF-8&amp;zeroDateTimeBehavior=convertToNull
jdbc.username=root
jdbc.password=root

#=================Oracle=================
#jdbc.driverClassName=oracle.jdbc.driver.OracleDriver
#jdbc.url=jdbc:oracle:thin:@localhost:1521:mytest
#jdbc.username=cemalluser
#jdbc.password=cemallpass

#=================DB2=================
#jdbc.driverClassName=com.ibm.db2.jcc.DB2Driver
#jdbc.url=jdbc:db2://10.0.12.10:50000/mytest
#jdbc.username=Administrator
#jdbc.password=i2finance720

#================ POJO 产生的目录和包名==================
java.model.target.package=com.payn.pojo
java.model.target.project=.\\src\\genresult

#================ Mapper 接口产生的目录和包名==================
javaClient.target.package=com.payn.mapper
javaClient.target.project=.\\src\\genresult

#================ Mapper 映射配置文件产生的目录和包名==================
sqlmap.target.package=com.payn.mapper
sqlmap.target.project=.\\src\\genresult\\resource
```

2.IBatis 代码生成器配置

    修改 IBatisGeneratorConfig.xml 配置文件

3.MyBatis 代码生成器配置

    修改 MyBatisGeneratorConfig.xml 配置文件

4.根据使用的是 IBatis 或者 MyBatis 运行对应的主方法

    IBatisGenerator、MyBatisGenerator


