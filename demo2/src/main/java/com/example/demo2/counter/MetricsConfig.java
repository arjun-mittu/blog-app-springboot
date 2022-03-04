package com.example.demo2.counter;


import com.timgroup.statsd.NonBlockingStatsDClient;
import com.timgroup.statsd.NonBlockingStatsDClientBuilder;
import com.timgroup.statsd.StatsDClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MetricsConfig {
    @Bean
    public StatsDClient statsDClient(
//        @Value("${metrics.statsd.host:localhost}") String host,
//        @Value("${metrics.statsd.port:8125}") int port,
//        @Value("${metrics.prefix:example.app}") String prefix
            @Value("${management.metrics.export.statsd.host}") String host,
            @Value("${management.metrics.export.statsd.port}") int port
    ){

        String prefix="example.app";
        NonBlockingStatsDClientBuilder nonBlockingStatsDClientBuilder=new NonBlockingStatsDClientBuilder();
        nonBlockingStatsDClientBuilder.port(port);
        nonBlockingStatsDClientBuilder.prefix(prefix);
        nonBlockingStatsDClientBuilder.hostname(host);
        nonBlockingStatsDClientBuilder.queueSize(1);

//        return new NonBlockingStatsDClient(prefix,host,port,10);
        return new NonBlockingStatsDClient(nonBlockingStatsDClientBuilder);
        //return new NonBlockingStatsDClient(prefix, host, port, 1);
    }
}
