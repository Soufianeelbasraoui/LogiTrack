package org.example.logitrack.mapper;

import org.example.logitrack.dto.LigneCommandeRequestDTO;
import org.example.logitrack.dto.LigneCommandeResponseDTO;
import org.example.logitrack.model.LigneCommande;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface LigneCommandeMapper {

    @Mapping(target = "produitId", source = "produit.id")
    LigneCommandeResponseDTO toDto(LigneCommande ligne);

    LigneCommande toEntity(LigneCommandeRequestDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(LigneCommandeRequestDTO dto, @MappingTarget LigneCommande ligne);
}
