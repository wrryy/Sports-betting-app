package pl.wrryy.amelco;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

@Component
public class AuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

        @Override
        protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Collection<? extends GrantedAuthority> roles = auth.getAuthorities();

            String targetUrl = "";
            for  (GrantedAuthority role : roles){
            if(role.toString().contains("ADMIN")) {
                targetUrl = "/admin/users";
            } else if(role.toString().contains("USER")) {
                targetUrl = "/";
            }
            }
            return targetUrl;
        }

//        implements AuthenticationSuccessHandler {
//    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
//            throws IOException {
//
//        handle(request, response, authentication);
//        clearAuthenticationAttributes(request);
//    }
//
//    protected void handle(HttpServletRequest request,
//                          HttpServletResponse response, Authentication authentication)
//            throws IOException {
//
//        String targetUrl = determineTargetUrl(authentication);
//        redirectStrategy.sendRedirect(request, response, targetUrl);
//    }
//
//    protected String determineTargetUrl(Authentication authentication) {
//        boolean isUser = false;
//        boolean isAdmin = false;
//
//        Collection<? extends GrantedAuthority> authorities
//                = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
//        for (GrantedAuthority grantedAuthority : authorities) {
//            if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
//                isAdmin = true;
//                break;
//            } else if (grantedAuthority.getAuthority().equals("ROLE_USER")) {
//                isUser = true;
//                break;
//            }
//        }
//        if (isAdmin){
//            return "redirect:/admin/users";
//        } else if (isUser) {
//            return "redirect:/";
//        } else {
//            throw new IllegalStateException();
//        }
//    }
//
//    protected void clearAuthenticationAttributes(HttpServletRequest request) {
//        HttpSession session = request.getSession(false);
//        if (session == null) {
//            return;
//        }
//        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
//    }
//
//    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
//        this.redirectStrategy = redirectStrategy;
//    }
//
//    protected RedirectStrategy getRedirectStrategy() {
//        return redirectStrategy;
//    }
}
