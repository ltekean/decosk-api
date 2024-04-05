package com.saysimple.decosk.test.security.handlers

import com.saysimple.decosk.security.utils.CookieUtils
import com.saysimple.decosk.test.security.HttpCookieOAuth2AuthorizationRequestRepository.Companion.REDIRECT_URI_PARAM_COOKIE_NAME
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import lombok.RequiredArgsConstructor
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler
import org.springframework.stereotype.Component
import org.springframework.web.util.UriComponentsBuilder
import java.io.IOException

@RequiredArgsConstructor
@Component
class OAuth2AuthenticationFailureHandler : SimpleUrlAuthenticationFailureHandler() {
    private val httpCookieOAuth2AuthorizationRequestRepository: com.saysimple.decosk.test.security.HttpCookieOAuth2AuthorizationRequestRepository? =
        null

    @Throws(IOException::class)
    override fun onAuthenticationFailure(
        request: HttpServletRequest, response: HttpServletResponse,
        exception: AuthenticationException
    ) {
        var targetUrl: String = CookieUtils.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME)?.value ?: "/"

        targetUrl = UriComponentsBuilder.fromUriString(targetUrl)
            .queryParam("error", exception.localizedMessage)
            .build().toUriString()

        httpCookieOAuth2AuthorizationRequestRepository?.removeAuthorizationRequestCookies(request, response)

        redirectStrategy.sendRedirect(request, response, targetUrl)
    }
}
