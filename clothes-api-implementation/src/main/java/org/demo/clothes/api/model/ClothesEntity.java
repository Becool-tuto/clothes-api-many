package org.demo.clothes.api.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CLOTHES", schema = "clothesdb")
public class ClothesEntity{
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "CLOTHES_ID")
    private String id;

    @Column(name = "CLOTHES_SIZE")
    private String size;

    @Column(name = "CLOTHES_COLOR")
    private String color;

    @ManyToMany
    @JoinTable(
            name= "CLOTHES_BRDS",
            joinColumns = @JoinColumn(name = "CLOTHES_ID"),
            inverseJoinColumns = @JoinColumn(name = "BRD_ID"))
    private Set<BrandEntity> clothesBrands = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name="CLOTHES_TYPE", referencedColumnName = "TYPE_ID")
    })
    private TypeEntity type;

    public String getId() {
        return id;
    }

    public String getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    public Set<BrandEntity> getClothesBrands() {
        return clothesBrands;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setClothesBrands(Set<BrandEntity> clothesBrands) {
        this.clothesBrands = clothesBrands;
    }

    public TypeEntity getType() {
        return type;
    }

    public void setType(TypeEntity type) {
        this.type = type;
    }
}
