
package com.example.Caching.Service;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.Caching.Entity.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CacheService {

    @Autowired
    CacheManager cacheManager;

    Map<String, City> cityZipCodeMap;

    public CacheService() {
        cityZipCodeMap = new HashMap<>();
        cityZipCodeMap.put("HitechCity", City.builder().cityName("HitechCity").zipCode("500081").build());
        cityZipCodeMap.put("SrNagar", City.builder().cityName("SrNagar").zipCode("500038").build());
        cityZipCodeMap.put("Miyapur", City.builder().cityName("Miyapur").zipCode("500049").build());
        cityZipCodeMap.put("WaveRock", City.builder().cityName("WaveRock").zipCode("500095").build());
        cityZipCodeMap.put("Gachivali", City.builder().cityName("Gachvali").zipCode("500032").build());
    }

    @Cacheable(value = "city-zip-code", key = "#cityName")
    public City getZipCode(String cityName) {
        System.out.println("getZipCode() method called");
        return cityZipCodeMap.get(cityName);
    }

    @CachePut(value = "city-zip-code", key = "#city.cityName")
    public City addCity(City city) {
        System.out.println("addCity() method called");
        return cityZipCodeMap.put(city.getCityName(), city);
    }

    @CacheEvict(value = "city-zip-code", allEntries = true)
    public String removeCache() {
        return "Cache removed successfully";
    }

    public Cache getCacheByName(String cacheName) {
        return cacheManager.getCache(cacheName);
    }


}
