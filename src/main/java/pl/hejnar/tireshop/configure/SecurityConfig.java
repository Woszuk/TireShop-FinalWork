package pl.hejnar.tireshop.configure;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;
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
        http.sessionManagement()
                .maximumSessions(100)
                .maxSessionsPreventsLogin(false)
                .expiredUrl("/login?sessionExpire=true")
                .sessionRegistry(sessionRegistry());

        http.csrf().disable();
        http.headers().disable();
        http.authorizeRequests()
                .antMatchers("/shop/edit", "/shop/remove", "/account/users", "/account/users/**").hasRole("ADMIN")
                .antMatchers("/account/change/**", "/account/orders", "/account/details", "/transaction").hasAnyRole("USER", "ADMIN")
                .and().formLogin().loginPage("/login").defaultSuccessUrl("/account/details").failureUrl("/loginError")
                .and().logout().logoutSuccessUrl("/")
                .and().exceptionHandling().accessDeniedPage("/");
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }
}
