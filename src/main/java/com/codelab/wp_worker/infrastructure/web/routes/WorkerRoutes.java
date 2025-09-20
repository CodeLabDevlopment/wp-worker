package com.codelab.wp_worker.infrastructure.web.routes;

import com.codelab.wp_worker.domain.model.Worker;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/api/v1/worker")
public interface WorkerRoutes {

    @GetMapping("/all")
    ResponseEntity<List<Worker>> findAll();

    @GetMapping("{workerId}")
    ResponseEntity<Worker> findById(@PathVariable Long workerId);

}
