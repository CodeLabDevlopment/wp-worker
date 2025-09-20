package com.codelab.wp_worker.infrastructure.web.controller;

import com.codelab.wp_worker.application.usecases.worker.FindWorkerByIdCase;
import com.codelab.wp_worker.application.usecases.worker.ListWorkersCase;
import com.codelab.wp_worker.domain.model.Worker;
import com.codelab.wp_worker.infrastructure.web.routes.WorkerRoutes;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
public class WorkerController implements WorkerRoutes {

    private final ListWorkersCase listWorkersCase;
    private final FindWorkerByIdCase findWorkerByIdCase;

    @Override
    public ResponseEntity<List<Worker>> findAll() {
        log.debug("Received request to find all workers");
        return ResponseEntity.ok(this.listWorkersCase.execute());
    }

    @Override
    public ResponseEntity<Worker> findById(Long workerId) {
        log.debug("Received request to find worker by id: {}", workerId);
        return ResponseEntity.ok(this.findWorkerByIdCase.execute(workerId));
    }

}
