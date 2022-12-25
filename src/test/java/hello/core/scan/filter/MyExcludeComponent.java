package hello.core.scan.filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE) //.TYPE - class 레벨에 붙음
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyExcludeComponent {
}

/**
 * 이 annotation이 붙으면 ComponentScan에 제외한다!
 */
