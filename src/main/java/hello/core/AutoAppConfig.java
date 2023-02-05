package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.springframework.context.annotation.ComponentScan.Filter;

/**
 * @ComponentScan: 설정 정보가 없어도 자동으로 스프링 빈을 등록함!
 * @Autowired: 의존관계 자동 주입 (@Component를 붙인 클래스가 빈으로 등록되고 클래스 안에서 정의한 constructure에 의존관계 주입 할 방법이 없음! - 자동 등록)
 * excludeFilters: 자동으로 스프링 빈으로 등록되는데 그 중에서 뺄 것들 등록!
 *
 * 권장: 패키지 위치를 지정하지 않고 (basePackages X) 설정 정보 클래스의 위치를 프로젝트 최상단에 둘 것! (package hello.core)
 */
@Configuration
@ComponentScan(  //basePackages default: @ComponentScan가 있는 부분이 시작위치 (package hello.core)
        basePackages = "hello.core.member", //basePackages: 컴포넌트 스캔 탐색 시작위치 지정
        basePackageClasses = AutoAppConfig.class, //basePackageClasses: 지정한 클래스의 패키지를 탐색 시작 위치로 지정
        excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class  AutoAppConfig {

//    수동 빈 등록 - 충돌나는거 application.properties에서 false로 막음
/*    @Bean(name = "memoryMemberRepository")
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }*/
}
