package com.caching.caching;

import lombok.extern.slf4j.Slf4j;
import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.springframework.stereotype.Component;
/**
 * A component for logging cache events.
 * This listener monitors and logs events related to the cache, such as creation, updates, removal, eviction, and expiration.
 */
@Slf4j
@Component
public class CacheEventLogger implements CacheEventListener<Object, Object> {
    @Override
    public void onEvent(CacheEvent<?, ?> event) {
        // Log the cache event based on the event type
        switch (event.getType()) {
            case CREATED:
                log.info("Cache event - CREATED: Key = {}, Value = {}", event.getKey(), event.getNewValue());
                break;
            case UPDATED:
                log.info("Cache event - UPDATED: Key = {}, Old Value = {}, New Value = {}", event.getKey(),
                        event.getOldValue(), event.getNewValue());
                break;
            case REMOVED:
                log.info("Cache event - REMOVED: Key = {}, Value = {}", event.getKey(), event.getOldValue());
                break;
            case EVICTED:
                log.info("Cache event - EVICTED: Key = {}, Value = {}", event.getKey(), event.getOldValue());
                break;
            case EXPIRED:
                log.info("Cache event - EXPIRED: Key = {}", event.getKey());
                break;
            default:
                log.info("Cache event - UNKNOWN: Key = {}", event.getKey());
                break;
        }
    }
}
