package com.caching.caching;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import java.util.Objects;
/**
 * Configuration class for enabling and setting up caching in the application using EhCache.
 */
@Configuration
@EnableCaching
public class CacheConfig {
    /**
     * Creates a bean for the {@link CacheManager}, which is responsible for managing cache operations.
     * This implementation uses EhCache as the caching provider.
     *
     * @return A configured {@link CacheManager} instance.
     */
    @Bean
    public CacheManager cacheManager() {
        return new EhCacheCacheManager(Objects.requireNonNull(ehCacheManagerFactory().getObject()));
    }
    /**
     * Creates a factory bean for managing the EhCache configuration.
     * The configuration is loaded from the `ehcache.xml` file located in the classpath.
     *
     * @return A configured {@link EhCacheManagerFactoryBean} instance.
     */
    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactory() {
        EhCacheManagerFactoryBean factoryBean = new EhCacheManagerFactoryBean();
        factoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
        factoryBean.setShared(true);
        return factoryBean;
    }
}
