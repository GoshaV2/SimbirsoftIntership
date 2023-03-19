package com.simbirsoft.intership.service.constants;

import com.simbirsoft.intership.model.Realise;

import java.sql.Date;

public enum Realises {
    RealiseWithTask(Realise.builder().id(1L).title("Релиз первого проекта")
            .project(Projects.ProjectOpen.getProject())
            .dateStart(Date.valueOf("2023-03-12")).dateEnd(Date.valueOf("2023-03-19")).build()),
    RealiseWithoutTask(Realise.builder().id(2L).title("Релиз первого проекта")
            .project(Projects.ProjectOpen.getProject())
            .dateStart(Date.valueOf("2023-03-12")).dateEnd(Date.valueOf("2023-03-19")).build());
    private Realise realise;

    Realises(Realise realise) {
        this.realise = realise;
    }

    public Realise getRealise() {
        return realise;
    }
}
