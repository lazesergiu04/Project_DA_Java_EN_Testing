package com.openclassrooms.lambazon.store.domain.model;

/**
 * FIXME: This should be removed and replaced with the Product domain entity.
 * @deprecated Replace with {@link Product} domain entity and remove
 */
@Deprecated
public class ProductModel {

    private Long id;
    private String name;            // Required
    private String description;
    private String details;         // TODO: Remove from model as we only need the description
    private String  quantity;       // Required, Integer, Greater than zero
    private String  price;          // Required, Numeric, Greater than zero

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
