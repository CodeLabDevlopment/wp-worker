package com.codelab.wp_worker.infrastructure.gateway;

import com.codelab.wp_worker.application.gateway.PersistenceGateway;
import com.codelab.wp_worker.domain.model.Worker;
import com.codelab.wp_worker.infrastructure.database.repositories.WorkerRepository;
import com.codelab.wp_worker.infrastructure.mapper.WorkerMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class PersistenceGatewayImpl implements PersistenceGateway {

    private final WorkerRepository workerRepository;

    public PersistenceGatewayImpl(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    @Override
    public List<Worker> findAllWorkers() {
        log.info("Finding all workers");
        return this.workerRepository.findAll()
                .stream()
                .map(WorkerMapper::toDomain)
                .toList();
    }

    @Override
    public Worker findWorkerById(Long workerId) {
        log.debug("Finding worker by id: {}", workerId);
        return this.workerRepository.findById(workerId)
                .map(WorkerMapper::toDomain)
                .orElseThrow(() -> {
                    log.error("Worker not found with id: {}", workerId);
                    return new EntityNotFoundException("Worker not found with id: " + workerId);
                });
    }

}
