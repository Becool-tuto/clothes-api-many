package org.demo.clothes.api.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "BRDS",schema = "clothesdb")
public class BrandEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid", strategy = "uuid2")
    @Column(name ="BRD_ID")
    private String id;

    @Column(name= "BRD_NAME")
    private String name;

    @ManyToMany(mappedBy = "clothesBrands",fetch = FetchType.EAGER)
    private Set<ClothesEntity> brandsClothes = new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ClothesEntity> getBrandsClothes() {
        return brandsClothes;
    }

    public void setBrandsClothes(Set<ClothesEntity> brandsClothes) {
        this.brandsClothes = brandsClothes;
    }
}
