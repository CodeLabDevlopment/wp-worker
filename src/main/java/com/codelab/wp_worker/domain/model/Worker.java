package com.codelab.wp_worker.domain.model;

public record Worker(
        Long id,
        String name,
        Double dailyIncome
) {

}
