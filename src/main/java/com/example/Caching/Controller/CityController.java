package com.example.Caching.Controller;

import com.example.Caching.Entity.City;
import com.example.Caching.Service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.web.bind.annotation.*;

@RestController
public class CityController {

    @Autowired
    CacheService cacheService;

    @GetMapping("/city/{name}")
    public City getCityByName(@PathVariable String name) {
        return cacheService.getZipCode(name);
    }

    @PostMapping("/city")
    public City addCache(@RequestBody City city) {
        return cacheService.addCity(city);
    }

    @DeleteMapping("/cache")
    public String removeCache() {
        return cacheService.removeCache();
    }


    @GetMapping("/cache/{name}")
    public Cache getCacheDetails(@PathVariable String name) {
        return cacheService.getCacheByName(name);
    }


}
