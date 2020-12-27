package com.example.nk.serviceImpl;

import com.example.nk.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CacheServiceImpl implements CacheService {
  @Autowired
  CacheManager cacheManager;

  @Override
  public void evictAllCaches() {
    cacheManager.getCacheNames().stream()
        .forEach(cacheName -> cacheManager.getCache(cacheName).clear());
  }

  @Override
  @Scheduled(fixedRate = 10000)
  public void evictAllCachesAtIntervals() {
    evictAllCaches();
  }
}
