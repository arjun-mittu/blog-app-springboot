package com.example.demo2.counter;


import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/counter")
public class CounterController {
    private final Counter testCounter;
    public CounterController(MeterRegistry meterRegistry){
        testCounter=meterRegistry.counter("custom_counter");
    }
    @GetMapping
    public String getCounter(){
        testCounter.increment();
        return "Counter api";
    }
}
