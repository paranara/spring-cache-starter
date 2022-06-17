package org.paranora.cache.redis;

import lombok.Builder;
import lombok.Data;

/**
 * @author :  paranora
 * @description :  TODO
 * @date :  2021/6/23 17:34
 */
@Data
@Builder
public class Key {
    private CustomCacheKeyPrefix prifix;
    private String content;
}
