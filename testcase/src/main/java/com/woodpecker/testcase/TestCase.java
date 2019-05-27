package com.woodpecker.testcase;

import com.sword.autotest.framework.testcase.BaseTestCase;
import com.woodpecker.WoodpeckerApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;


/**
 * 类描述:〈TestCase基类〉
 *
 * @version 1.0
 * @author: xujinjian
 */
@SpringBootTest(classes = {WoodpeckerApplication.class})
public class TestCase extends BaseTestCase {

  protected Logger logger = LoggerFactory.getLogger(this.getClass());

}
