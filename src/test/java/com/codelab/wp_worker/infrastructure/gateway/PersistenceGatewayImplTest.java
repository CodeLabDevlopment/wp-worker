package com.codelab.wp_worker.infrastructure.gateway;

import com.codelab.wp_worker.domain.model.Worker;
import com.codelab.wp_worker.factory.WorkerFactory;
import com.codelab.wp_worker.infrastructure.database.entity.WorkerEntity;
import com.codelab.wp_worker.infrastructure.database.repositories.WorkerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersistenceGatewayImplTest {

    @Mock
    WorkerRepository workerRepository;
    @InjectMocks
    PersistenceGatewayImpl persistenceGateway;

    @Test
    @DisplayName("Should find all workers and map to domain")
    void case01() {
        List<WorkerEntity> entity = WorkerFactory.createWorkerEntities();

        when(workerRepository.findAll()).thenReturn(entity);

        List<Worker> workers = persistenceGateway.findAllWorkers();

        assertEquals(entity.size(), workers.size());
        verify(workerRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should find worker by id and map to domain")
    void case02() {
        WorkerEntity entity = WorkerFactory.createWorkerEntity();

        when(workerRepository.findById(1L)).thenReturn(Optional.of(entity));

        Worker worker = persistenceGateway.findWorkerById(1L);

        assertEquals(entity.getId(), worker.id());
        assertEquals(entity.getName(), worker.name());
        assertEquals(entity.getDailyIncome(), worker.dailyIncome());
        verify(workerRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Should throw exception when worker not found by id")
    void case03() {
        assertThrows(EntityNotFoundException.class,
                () -> persistenceGateway.findWorkerById(1L)
        );
        verify(workerRepository, times(1)).findById(1L);
    }

}