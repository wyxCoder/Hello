package org.demo;

import org.demo.dao.UserDAO;
import org.demo.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.Random;

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
