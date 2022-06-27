package org.javaboy.vhr.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @Author szh
 * @Date 2022/6/21 0:16
 * @PackageName:org.javaboy.vhr.config
 * @ClassName: CustomUrlDecisionManager
 * @Description: 判断当前用户是否具备MyFilter中返回的角色
 * @Version 1.0
 */
@Component
public class CustomUrlDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        for (ConfigAttribute configAttribute : configAttributes) {
            String needRole = configAttribute.getAttribute();
            /*登录之后就能访问*/
            if(needRole.equals("ROLE_LOGIN")){
                /*判断是否登录*/
                if (authentication instanceof AnonymousAuthenticationToken) {
                    /*匿名登录*/
                    throw new AccessDeniedException("尚未登录，请登录");
                }else{
                    return;
                }
            }
            /*获取登录用户的角色*/
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                if(authority.getAuthority().equals(needRole)){
                    return;
                }
            }
        }
        throw new AccessDeniedException("权限不足，请联系管理员");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return false;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }
}
