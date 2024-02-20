/*
 * The CategoriesDTO class represents a Data Transfer Object (DTO) for category information.
 * It is used to transfer data related to categories between different layers of the application.
 */
package com.cowards.onlyarts.repositories.categories;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Data Transfer Object (DTO) for category information.
 * This class encapsulates the data related to categories and is used for transferring information between layers of the application.
 * 
 * <p>
 * The class includes fields for category ID and category name.
 * </p>
 * 
 * @author Admin
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CategoriesDTO {

    /**
     * The unique identifier for the category.
     */
    private String cateId;

    /**
     * The name of the category.
     */
    private String cateName;
}
