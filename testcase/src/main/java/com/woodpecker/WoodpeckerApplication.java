package com.woodpecker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableTransactionManagement
@ComponentScan({"com.woodpecker", "com.sword.autotest.framework"})
public class WoodpeckerApplication {


  public static void main(String[] args) {
    SpringApplication.run(WoodpeckerApplication.class, args);
  }


}
