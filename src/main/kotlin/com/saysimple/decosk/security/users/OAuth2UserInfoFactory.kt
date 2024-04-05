package com.saysimple.decosk.test.security.users

import com.example.oauth2.oauth2.exception.OAuth2AuthenticationProcessingException

object OAuth2UserInfoFactory {
    fun getOAuth2UserInfo(
        registrationId: String,
        accessToken: String?,
        attributes: Map<String, Any>
    ): OAuth2UserInfo {
        return if (OAuth2Provider.GOOGLE.registrationId.equals(registrationId)) {
            GoogleOAuth2UserInfo(accessToken!!, attributes)
        } else if (OAuth2Provider.NAVER.registrationId.equals(registrationId)) {
            NaverOAuth2UserInfo(accessToken!!, attributes)
        } else if (OAuth2Provider.KAKAO.registrationId.equals(registrationId)) {
            KakaoOAuth2UserInfo(accessToken!!, attributes)
        } else {
            throw OAuth2AuthenticationProcessingException("Login with $registrationId is not supported")
        }
    }
}
