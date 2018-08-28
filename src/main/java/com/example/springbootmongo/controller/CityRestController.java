package com.example.springbootmongo.controller;

import com.example.springbootmongo.bean.City;
import com.example.springbootmongo.repository.CityRepository;
import com.example.springbootmongo.service.CityService;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 说明:
 *
 * @author WangBin
 * @version V1.0
 * @since 2018.03.08
 */
@RestController
@RequestMapping(value = "/city")
public class CityRestController {
    @Autowired
    private CityRepository cityRepository;


    @RequestMapping("/list")
    public List<City> dataList() {
        return cityRepository.findAll();

    }
    @RequestMapping("/addCity")
    public void addCity(City city) {
        cityRepository.save(city);
    }

    @RequestMapping("/deleteById")
    public void deleteById(int id) {
        cityRepository.deleteById(id);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String findOneCity(@PathVariable("id") Long id) {
        System.err.println("findOneCity 1");
        // Mono mono = Mono.create(cityMonoSink -> cityMonoSink.success(cityService.findCityById(id)));
        System.err.println("findOneCity 3");
        return "aaaaa";
    }

    /**
     * Flux<Object> 支持REST风格的 JSON 和 XML 序列化和反序列化
     * 处理函数，它将数据库中发现的所有 City 对象返回为JSON。
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String findAllCity() {
        System.err.println("findAllCity 1");
        /*Flux flux = Flux.create(cityFluxSink -> {
            cityService.findAllCity().forEach(city -> cityFluxSink.next(city));
            cityFluxSink.complete();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.err.println("findAllCity 2");
        });*/
        System.err.println("findAllCity  end 3");
        return "bbbbbb";
    }

    /*@RequestMapping(method = RequestMethod.POST)
    public Mono<Long> createCity(@RequestBody City city) {
        return Mono.create(cityMonoSink -> cityMonoSink.success(cityService.saveCity(city)));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Mono<Long> modifyCity(@RequestBody City city) {
        return Mono.create(cityMonoSink -> cityMonoSink.success(cityService.updateCity(city)));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Mono<Long> modifyCity(@PathVariable("id") Long id) {
        return Mono.create(cityMonoSink -> cityMonoSink.success(cityService.deleteCity(id)));
    }*/
}