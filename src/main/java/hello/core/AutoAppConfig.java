package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.springframework.context.annotation.ComponentScan.Filter;

@Configuration
@ComponentScan(  //basePackages 없을 때 default: @ComponentScan이 붙은 설정 정보 클래스의 패키지가 시작 위치
        basePackages = "hello.core.member", //basePackages: 컴포넌트 스캔 탐색 시작위치 지정
        basePackageClasses = AutoAppConfig.class, //basePackageClasses: 지정한 클래스의 패키지를 탐색 시작 위치로 지정
        excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class  AutoAppConfig {

    /*@Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }*/
}
