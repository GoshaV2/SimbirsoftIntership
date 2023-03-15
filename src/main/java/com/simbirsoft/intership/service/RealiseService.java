package com.simbirsoft.intership.service;

import com.simbirsoft.intership.dto.request.CreatingRealiseDto;
import com.simbirsoft.intership.model.Realise;
import com.simbirsoft.intership.model.User;

public interface RealiseService {
    Realise createRealise(CreatingRealiseDto realise, User user);
    Realise getRealise(long id, long projectId);
}
