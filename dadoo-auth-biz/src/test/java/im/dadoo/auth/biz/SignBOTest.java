package im.dadoo.auth.biz;

import javax.annotation.Resource;

import im.dadoo.auth.biz.bo.SignBO;
import im.dadoo.auth.biz.configuration.BizContext;
import im.dadoo.auth.data.constant.ErrorConstants;
import im.dadoo.auth.data.dto.SignDTO;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.google.common.base.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BizContext.class)
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
public class SignBOTest extends AbstractTransactionalJUnit4SpringContextTests {
  
  @Resource
  private SignBO signBO;
  
  @Test
  public void testSignup() {
    Optional<SignDTO> signDTO = this.signBO.signup("codekitten", "codekitten@qq.com", "123456");
    Assert.assertEquals(1, this.countRowsInTable("t_user"));
  }
  
  @Test
  public void testSignin() {
    Optional<SignDTO> signDTO = this.signBO.signup("codekitten", "codekitten@qq.com", "123456");
    System.out.println(signDTO.get());
    signDTO = this.signBO.signin("codekitten@qq.com", "123456");
    System.out.println(signDTO.get());
    Assert.assertEquals(signDTO.get().getErrorDTO().getCode(), ErrorConstants.CODE_SUCCESS);
    signDTO = this.signBO.signin("codekitten@qq.com", "12345");
    Assert.assertEquals(signDTO.get().getErrorDTO().getCode(), ErrorConstants.CODE_PASSWORD_INCORRECT);
    signDTO = this.signBO.signin("code@qq.com", "123456");
    Assert.assertEquals(signDTO.get().getErrorDTO().getCode(), ErrorConstants.CODE_QUERY_NULL);
    signDTO = this.signBO.signin("code@qq.com", "12345");
    Assert.assertEquals(signDTO.get().getErrorDTO().getCode(), ErrorConstants.CODE_QUERY_NULL);
  }
}
