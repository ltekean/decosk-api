import lombok.RequiredArgsConstructor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
class WebSecurityConfig {

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return NoOpPasswordEncoder.getInstance()
    }

    @Bean
    fun userDetailsService(): UserDetailsService {
        val manager: InMemoryUserDetailsManager = InMemoryUserDetailsManager()
        manager.createUser(User.withUsername("user1").password("1234").build())
        return manager
    }


    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.authorizeHttpRequests { authorizeRequests ->
            authorizeRequests.requestMatchers("/api1").hasRole("user")
                .requestMatchers("/api2").hasRole("admin").anyRequest().authenticated()
        }.formLogin { formLogin ->
            formLogin.usernameParameter("username").passwordParameter("password").defaultSuccessUrl("/", true)
        }
        return http.build()
    }

}
