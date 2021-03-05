package xyz.ipush.web.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import xyz.ipush.web.config.filter.TokenFilter;
import xyz.ipush.web.service.UserService;

import javax.annotation.Resource;

/**
 * Spring Security配置
 *
 * @author jwei
 * @date 2020/12/16
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private UserService userService;
    @Resource
    private TokenFilter tokenFilter;
    @Resource
    private CustomAccessDeniedHandler customAccessDeniedHandler;
    @Resource
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class)
                .csrf().disable()
                .cors().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().exceptionHandling()
                .accessDeniedHandler(customAccessDeniedHandler)
                .authenticationEntryPoint(customAuthenticationEntryPoint)
                .and().authorizeRequests((authorize) -> authorize
                .antMatchers("/user/login", "/user/register", "/user/active", "/user/validateUsername").permitAll()
                .anyRequest().authenticated());
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(
                "/v3/api-docs",
                "/swagger-resources/**",
                "/swagger-ui/**",
                "/webjars/**");
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return (UserDetailsService) userService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
