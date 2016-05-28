package com.mindpool.easymed.cache

import grails.transaction.Transactional

@Transactional
class UserCacheService {

    def grailsCacheManager

    private getCache() {
        return grailsCacheManager.getCache("users")
    }

    Long getElement(String key){
        return (Long)getCache().get(key)?.get()
    }

    String save(Long userElement) {
        String key = UUID.randomUUID().toString()
        getCache().put(key, userElement)
        return key
    }

    void delete(String key) {
        getCache().evict(key)
    }
}
