package caas.incrementid.service;

import caas.incrementid.entity.IncrementId;
import caas.incrementid.repository.IncrementIdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncrementIdService {
    @Autowired
    private IncrementIdRepository incrementIdRepository;

    public Integer get(String name) {
        if (!incrementIdRepository.exists(name)) {
            IncrementId incrementId = new IncrementId();
            incrementId.name = name;
            incrementIdRepository.save(incrementId);
            return incrementId.id;
        }
        IncrementId incrementId = incrementIdRepository.findOne(name);
        incrementId.id += 1;
        incrementIdRepository.save(incrementId);
        return incrementId.id;
    }
}
