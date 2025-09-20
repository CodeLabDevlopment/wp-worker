package com.codelab.wp_worker.infrastructure.web.controller;

import com.codelab.wp_worker.application.usecases.worker.FindWorkerByIdCase;
import com.codelab.wp_worker.application.usecases.worker.ListWorkersCase;
import com.codelab.wp_worker.domain.model.Worker;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WorkerController.class)
class WorkerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    ListWorkersCase listWorkersCase;
    @MockitoBean
    FindWorkerByIdCase findWorkerByIdCase;

    @Test
    @DisplayName("Should return all workers when GET /workers is called")
    void case01() throws Exception {
        List<Worker> workers = workersList();

        when(listWorkersCase.execute()).thenReturn(workers);

        mockMvc.perform(get("/api/v1/worker/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(workers.size())))
                .andExpect(jsonPath("$[0].name").value("Bob"))
                .andExpect(jsonPath("$[1].name").value("Maria"))
                .andExpect(jsonPath("$[2].name").value("Alex"));

        verify(listWorkersCase, times(1)).execute();
    }

    @Test
    @DisplayName("Should return worker by id when GET /workers/{id} is called")
    void case02() {
        Worker worker = new Worker(1L, "Bob", 200.0);

        when(findWorkerByIdCase.execute(1L)).thenReturn(worker);

        try {
            mockMvc.perform(get("/api/v1/worker/{workerId}", 1L))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(worker.id()))
                    .andExpect(jsonPath("$.name").value(worker.name()))
                    .andExpect(jsonPath("$.dailyIncome").value(worker.dailyIncome()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        verify(findWorkerByIdCase, times(1)).execute(1L);
    }

    @Test
    @DisplayName("Should return 404 when GET /workers/{id} is called with non-existing id")
    void case03() {
        when(findWorkerByIdCase.execute(99L)).thenThrow(new EntityNotFoundException("Worker not found with id: 99"));

        try {
            mockMvc.perform(get("/api/v1/worker/{workerId}", 99L))
                    .andExpect(status().isNotFound());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        verify(findWorkerByIdCase, times(1)).execute(99L);
    }

    private List<Worker> workersList() {
        return List.of(
                new Worker(1L, "Bob", 200.0),
                new Worker(2L, "Maria", 300.0),
                new Worker(3L, "Alex", 250.0)
        );
    }

}