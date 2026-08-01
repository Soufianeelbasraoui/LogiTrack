package org.example.logitrack.mapper;

import org.example.logitrack.dto.ClientRequestDTO;
import org.example.logitrack.dto.ClientResponseDTO;
import org.example.logitrack.model.Client;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    @Mapping(target = "id", source = "id")
    ClientResponseDTO toDto(Client client);

    Client toEntity(ClientRequestDTO dto);

    ClientRequestDTO toDtoRequest(Client client);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(ClientRequestDTO dto, @MappingTarget Client client);
}
