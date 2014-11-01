package im.dadoo.auth.biz;

import javax.annotation.Resource;

import im.dadoo.auth.biz.configuration.BizContext;
import im.dadoo.auth.biz.dao.UserDAO;
import im.dadoo.auth.data.po.UserPO;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BizContext.class)
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
public class UserDAOTest extends AbstractTransactionalJUnit4SpringContextTests {
  
  @Resource
  private UserDAO userDAO;
  
  @Test
  public void testInsert() {
    UserPO userPO = new UserPO();
    userPO.setNickname("codekitten");
    userPO.setEmail("codekitten@qq.com");
    userPO.setPassword("123456");
    userPO.setSigninUTC(System.currentTimeMillis());
    userPO.setSignupUTC(System.currentTimeMillis());
    userPO.setDeleted(0);
    this.userDAO.insert(userPO);
    Assert.assertEquals(1, this.countRowsInTable("t_user"));;
  }
  
}
