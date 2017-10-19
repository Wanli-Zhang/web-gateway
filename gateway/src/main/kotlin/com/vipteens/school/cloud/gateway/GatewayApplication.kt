package com.vipteens.school.cloud.gateway

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.netflix.zuul.EnableZuulProxy
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource


/**
 * Created by morgan on 19/10/2017.
 * Project: cloud
 */
@SpringBootApplication
@EnableZuulProxy
class GatewayApplication : WebSecurityConfigurerAdapter() {

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        // @formatter:on
        http.authorizeRequests()
                .antMatchers("/webapi/v1/**").permitAll()
                .anyRequest().authenticated()
            .and().csrf().disable()
//                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
            .cors().configurationSource(configurationSource())
            .and().logout().logoutSuccessHandler(HttpStatusReturningLogoutSuccessHandler())
        // @formatter:off
    }

    private fun configurationSource(): CorsConfigurationSource {
        val source = UrlBasedCorsConfigurationSource()
        val config = CorsConfiguration()
        config.addAllowedOrigin("*")
        config.allowCredentials = true
        config.addAllowedHeader("*")
        config.addAllowedMethod(HttpMethod.GET)
        config.addAllowedMethod(HttpMethod.POST)
        config.addAllowedMethod(HttpMethod.PUT)
        config.addAllowedMethod(HttpMethod.DELETE)
        source.registerCorsConfiguration("/**", config)
//        source.registerCorsConfiguration("/logout", config)
//        source.registerCorsConfiguration("/Api/**", config)
        return source
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(GatewayApplication::class.java, *args)
}


