package HelloWorld.HelloWorld_Spring;

import HelloWorld.HelloWorld_Spring.repository.JdbcMemberRepository;
import HelloWorld.HelloWorld_Spring.repository.MemberRepository;
import HelloWorld.HelloWorld_Spring.repository.MemoryMemberRepository;
import HelloWorld.HelloWorld_Spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration

public class SpringConfig {
    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository(){
        //return new MemoryMemberRepository();
        return new JdbcMemberRepository(dataSource);
    }
}
