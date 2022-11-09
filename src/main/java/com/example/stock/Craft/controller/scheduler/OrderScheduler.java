package com.example.stock.Craft.controller.scheduler;

import com.example.stock.Craft.repository.OrderServiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OrderScheduler {


    private static final Logger log = LoggerFactory.getLogger(OrderScheduler.class);

    private OrderServiceRepository orderServiceRepository;

    @Scheduled(cron = "0 0 15 * * *")
    public void schedulerForOrderCleaning() {

        // clean all data which order is not completed

    }
}
