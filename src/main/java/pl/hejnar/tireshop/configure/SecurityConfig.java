package pl.hejnar.tireshop.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.hejnar.tireshop.auth.ApplicationUserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ApplicationUserService customUserDetailsService() {
        return new ApplicationUserService();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().disable();
        http.authorizeRequests()
                .antMatchers("/account/**", "/transaction").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .and().formLogin().loginPage("/login").defaultSuccessUrl("/account/details").failureUrl("/loginError")
                .and().logout().logoutSuccessUrl("/");
    }
}
