package aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @DESCIRPTION
 * @AUTHOR SCORPIO.HU
 * @DATE 2022/3/16 10:49 AM
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan("aop")
public class MainConfig {


}
