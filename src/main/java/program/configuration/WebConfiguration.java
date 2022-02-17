package program.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//  Оголошення системної конфігурації
@Configuration
//  Реєстрування веб-захисту
@EnableWebSecurity
//  Реєстрація системи захисту від запитів
@EnableGlobalMethodSecurity( securedEnabled = true, proxyTargetClass = true)
//  WebSecurityConfigurerAdapter надає можливість задання налаштувань для веб-захисту
public class WebConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //  Вимикає захист від атак csrf
        http.csrf().disable();
        //  Правила доступу до ресурсів серверу.
        http.authorizeRequests()
                .antMatchers("/swagger", "/swagger-ui/**", "/rest-api-docs/**").permitAll()
                .antMatchers("/api/author/**").permitAll()
                .antMatchers("/api/book/**").permitAll()
                .antMatchers("/files/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin();
    }
}
