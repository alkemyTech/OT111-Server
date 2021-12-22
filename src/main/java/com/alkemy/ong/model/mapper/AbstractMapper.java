package com.alkemy.ong.model.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractMapper<E, D> { //From sonarsource.com: "This change may not be appropriate in libraries or other applications where the class is intended to be used as an API."

    public abstract D entity2DTO(E entity);

    public abstract E dto2Entity(D dto);


    public List<D> entity2DTO(Collection<E> entities) {
        return entities.stream()
                .map(this::entity2DTO)
                .collect(Collectors.toList());
    }

    public List<E> dto2Entity(Collection<D> dtos) {
        return dtos.stream()
                .map(this::dto2Entity)
                .collect(Collectors.toList());
    }


}
