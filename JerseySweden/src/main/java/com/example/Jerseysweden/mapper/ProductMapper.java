package com.example.Jerseysweden.mapper;


import com.example.Jerseysweden.dto.CreateProductDTO;
import com.example.Jerseysweden.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper{

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categoryImageUrl", ignore = true)
    Product toEntity(CreateProductDTO dto);

}
