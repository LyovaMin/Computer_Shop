package by.lyofchik.AppSpring.Service.EntityInterfaces;

import by.lyofchik.AppSpring.Filter.ProductFilter;
import by.lyofchik.AppSpring.Model.DTO.ProductDTO;

import java.util.List;

public interface FindAllEntitiesByFilter<T> {
    List<T> findAllByFilter(ProductFilter filter);
}
