package com.vipteens.school.cloud.gateway.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import javax.servlet.http.HttpServletResponse

/**
 * Created by morgan on 20/10/2017.
 * Project: cloud
 */
@Controller
class AuthController {
    @RequestMapping("/auth")
    fun auth(@RequestParam("redirect_uri") redirectUri: String, httpServletResponse: HttpServletResponse) {
        httpServletResponse.setHeader("Location", redirectUri)
        httpServletResponse.status = 302
    }
}