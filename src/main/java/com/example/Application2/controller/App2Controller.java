
package com.example.Application2.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Application2.model.Task;
import com.example.Application2.service.CacheService;
import com.example.Application2.service.CalculationService;

@RestController
@RequestMapping("/tasks")
public class App2Controller {

    private static final Logger log = LoggerFactory.getLogger(App2Controller.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private CacheService cacheService;

    @Autowired
    private CalculationService calculationService;

    @PostMapping("/process")
    public ResponseEntity<String> processTask(@RequestBody Task task) {
        try {
           
            String taskId = task.getId() != null ? task.getId().toString() : "unknown";

            
            String cachedResult = cacheService.getCachedResult(taskId);

            if (cachedResult != null) {
                log.info("Result found in cache for task ID: {}", taskId);
                return ResponseEntity.ok(cachedResult);
            }

           
            String result = calculationService.performFakeCalculation(task.getDescription());

           
            cacheService.cacheResult(taskId, result);

          
            rabbitTemplate.convertAndSend("app1Exchange", "", result);

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error("Error during task processing.", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error during task processing.");
        }
    }

}
