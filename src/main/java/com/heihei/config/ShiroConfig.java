package com.heihei.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.heihei.shiro.MyShiroRealm;
import org.apache.shiro.cache.ehcache.EhCache;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
/**
 * ShiroConfig
 * @Description shiro配置类
 * @author CHENZEJIA
 * @date 2019/12/17
 */
@Configuration
public class ShiroConfig {
    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        securityManager.setCacheManager(getCache());
        return securityManager;
    }
    @Bean
    public MyShiroRealm myShiroRealm(){
        MyShiroRealm shiroRealm = new MyShiroRealm();
        return shiroRealm;
    }
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }
    /*
     * @Description  配置缓存，使用shiro的内置缓存Ehcache
     * @Author CHENZEJIA
     * @Date 2019/12/17
     * @Param []
     * @return org.apache.shiro.cache.ehcache.EhCacheManager
     **/
    @Bean
    public EhCacheManager getCache(){
        return new EhCacheManager();
    }
    /*
     * @Description shiro的过滤器，配置拦截的规则：对登录页面和注册页面开放，放开静态资源以及对于显示页面需要求有Select权限
     * @Author CHENZEJIA
     * @Date 2019/12/17
     * @Param [securityManager]
     * @return org.apache.shiro.spring.web.ShiroFilterFactoryBean
     **/
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String,String> filterChainDefinitionMap = new HashMap<>();
        shiroFilterFactoryBean.setLoginUrl("/login/to_login");
        filterChainDefinitionMap.put("/bootstrap/**","anon");
        filterChainDefinitionMap.put("/jquery-validation/**","anon");
        filterChainDefinitionMap.put("/js/**","anon");
        filterChainDefinitionMap.put("/layer/**","anon");
        filterChainDefinitionMap.put("/login/to_login","anon");
        filterChainDefinitionMap.put("/login/toregister","anon");
        filterChainDefinitionMap.put("/login/register","anon");
        filterChainDefinitionMap.put("/login/do_login","anon");
        filterChainDefinitionMap.put("/login/show","anon");
        filterChainDefinitionMap.put("/login/show","perms[Select]");
        filterChainDefinitionMap.put("/user/**","perms[Select]");
        filterChainDefinitionMap.put("/**","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }
}
