package pl.robert.project.app.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@AllArgsConstructor
class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private int maxInactiveInterval;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) {
        request.getSession().setMaxInactiveInterval(60 * maxInactiveInterval);
        clearAuthenticationAttributes(request);
    }
}
