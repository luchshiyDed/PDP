package com.PDP.service;

import com.PDP.model.DeleteCondition;
import com.PDP.repository.BaseRepository;
import com.PDP.repository.DeleteConditionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DeleteConditionService extends BaseService<DeleteCondition>{

    public DeleteConditionService(DeleteConditionRepository repository) {
        super(repository);
    }
}
