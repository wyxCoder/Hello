# Spring Boot中使用Mybatis
相信很多人都喜欢注解这种优雅的方式，但是使用动态sql时还是xml更加方便一些。本项目采用了注解加xml的方式，以便可适应多种场景。

### 1. 添加依赖
详情请见pom.xml
```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>1.3.6.RELEASE</version>
    <relativePath/> <!-- lookup parent from repository -->
</parent>


<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>org.mybatis.spring.boot</groupId>
        <artifactId>mybatis-spring-boot-starter</artifactId>
        <version>1.1.1</version>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

### 2. 修改配置文件
在src/main/resources目录下新建配置文件，并添加如下配置：
```
spring.datasource.url=jdbc:mysql://localhost:3306/demo?useUnicode=true&characterEncoding=utf8&useSSL=false
spring.datasource.username=root
spring.datasource.password=123456
mybatis.config-location=classpath:mybatis-config.xml
```

### 3. 修改MyBatis配置文件
在src/main/resources目录下新建配置文件mybatis-config.xml，并添加如下配置：
```xml
<configuration>
    <settings>
        <!-- Globally enables or disables any caches configured in any mapper under this configuration -->
        <setting name="cacheEnabled" value="true"/>
        <!-- Sets the number of seconds the driver will wait for a response from the database -->
        <setting name="defaultStatementTimeout" value="3000"/>
        <!-- Enables automatic mapping from classic database column names A_COLUMN to camel case classic Java property names aColumn -->
        <setting name="mapUnderscoreToCamelCase" value="true"/>
        <!-- Allows JDBC support for generated keys. A compatible driver is required.
        This setting forces generated keys to be used if set to true,
         as some drivers deny compatibility but still work -->
        <setting name="useGeneratedKeys" value="true"/>
    </settings>
</configuration>
```

### 4. 编写代码
注解的方式操作数据库，详情请见UserDAO.java
```java
@Mapper
public interface UserDAO {
    // 注意空格
    String TABLE_NAME = " user ";
    String INSERT_FIELDS = " name, password";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
            ") values (#{name},#{password})"})
    int addUser(User user);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where name=#{name}"})
    User selectByName(String name);

    @Delete({"delete from ", TABLE_NAME, " where id=#{id}"})
    void deleteById(int id);

    void updatePasswd(User user);
}
```

编写动态SQL，详情请见src/main/resources/org/demo/dao/UserDAO.xml
```xml
<mapper namespace="org.demo.dao.UserDAO">
    <sql id="table">user</sql>
    <sql id="selectFields">id, name, password</sql>
    <select id="updatePasswd" parameterType="org.demo.model.User">
        UPDATE
        <include refid="table"/>
        <if test="password != null and password != ''">
            SET password = #{password}
        </if>
        WHERE id = #{id}
    </select>
</mapper>
```

### 5. 编写测试代码
详情请见src/test/java/org/demo/InitDatabaseTest.java
```java
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@Sql("/init-schema.sql")
public class InitDatabaseTests {

    @Autowired
    UserDAO userDAO;

    @Test
    public void contextLoads() {

        User user = new User();
        user.setName("name");
        user.setPassword("123");
        userDAO.addUser(user);

        User user1 = userDAO.selectByName("name");
        Assert.assertEquals("name", user1.getName());
        Assert.assertEquals("123", user1.getPassword());

        user1.setPassword("456");
        userDAO.updatePasswd(user1);
        Assert.assertEquals("456", user1.getPassword());


        userDAO.deleteById(user1.getId());
        Assert.assertNull(userDAO.selectByName("name"));
    }
}
```

初始化SQL，详情请见src/test/resources/init-schema.sql
```sql
DROP TABLE IF EXISTS `user`;
  CREATE TABLE `user` (
    `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
    `name` varchar(64) NOT NULL DEFAULT '',
    `password` varchar(128) NOT NULL DEFAULT '',
    PRIMARY KEY (`id`),
    UNIQUE KEY `name` (`name`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```


