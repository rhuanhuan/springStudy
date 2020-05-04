package com.hruan.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;

public class CRule {

    @Bean
    public IRule myRule() {
        return new RandomRule();
    }
}
