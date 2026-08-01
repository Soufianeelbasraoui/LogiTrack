package org.example.logitrack.mapper;

import org.example.logitrack.dto.CommandeRequestDTO;
import org.example.logitrack.dto.CommandeResponseDTO;
import org.example.logitrack.model.Commande;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface CommandeMapper {

    @Mapping(target = "clientId", source = "client.id")
    @Mapping(target = "nomClient", source = "client.nom")
    CommandeResponseDTO toDto(Commande commande);

    Commande toEntity(CommandeRequestDTO dto);

    CommandeRequestDTO toDtoRequest(Commande commande);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(CommandeRequestDTO dto, @MappingTarget Commande commande);
}
