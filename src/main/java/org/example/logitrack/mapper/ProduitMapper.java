package org.example.logitrack.mapper;

import org.example.logitrack.dto.ProduitRequestDTO;
import org.example.logitrack.dto.ProduitResponseDTO;
import org.example.logitrack.model.Produit;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ProduitMapper {

    @Mapping(target = "id", source = "id")
    ProduitResponseDTO toDto(Produit produit);

    Produit toEntity(ProduitRequestDTO dto);

    ProduitRequestDTO toDtoRequest(Produit produit);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(ProduitRequestDTO dto, @MappingTarget Produit produit);
}
