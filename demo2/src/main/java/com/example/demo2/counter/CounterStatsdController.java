package com.example.demo2.counter;


import com.timgroup.statsd.StatsDClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/scounter")
public class CounterStatsdController {
//    private static final MeterRegistry registry=new StatsdMeterRegisty

//    private final Counter testCounter;
//    CounterStatsdController(StatsdMeterRegistry statsdMeterRegistry){
//        testCounter=statsdMeterRegistry.counter("statsd_counter");
//    }
//    StatsDClient statsDClient;
//    @Autowired
//    CounterStatsdController(StatsDClient statsDClient){
//        this.statsDClient=statsDClient;
//    }
    MetricsConfig metricsConfig;
    @Autowired
    CounterStatsdController(MetricsConfig metricsConfig){
        this.metricsConfig=metricsConfig;
    }
    @GetMapping
    public String statsdcounter(){
        //testCounter.increment();
        //statsDClient.increment("service.random");
        metricsConfig.statsDClient("localhost",8125).increment("service.random");
        return "Stats d counter";
    }

}
