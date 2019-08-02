package com.woodpecker.testcase;

import com.sword.autotest.framework.notice.EmailNotice;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 类描述:〈Config〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Configuration
public class EmailConfig {

  @Bean
  public EmailNotice createEmailNotice() {
    return new EmailNotice();
  }

}
