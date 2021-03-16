package org.demo.clothes.api.repository.filter;

import org.demo.clothes.api.model.ClothesEntity;
import org.demo.clothes.api.model.ClothesEntity_;
import org.demo.clothes.api.model.TypeEntity_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import javax.persistence.metamodel.SingularAttribute;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.demo.clothes.api.util.ClothesConstants.*;

public class ClothesSpecification implements Specification<ClothesEntity> {

    private Map<String, Object> filterMap;

    public ClothesSpecification(Map filterMap) {
        this.filterMap = filterMap;
    }

    @Override
    public Predicate toPredicate(Root<ClothesEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if(filterMap.containsKey(FIELD_NAME_TYPE)){
           predicates.add(root.join(ClothesEntity_.type, JoinType.INNER).get(TypeEntity_.id).in(filterMap.get(FIELD_NAME_TYPE)));
        }

        filterMap.remove(FIELD_NAME_TYPE);
        filterMap.forEach((key,value)-> predicates.add(criteriaBuilder.equal(root.get(matchIdAttribut(key)),value)));


        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private SingularAttribute<ClothesEntity,?> matchIdAttribut(String attribute){
        switch (attribute){
            case FIELD_NAME_COLOR:
                return ClothesEntity_.color;
            case FIELD_NAME_SIZE:
                return ClothesEntity_.size;
            default:
                throw new IllegalArgumentException(attribute);
        }
    }

    public Map<String, Object> getFilterMap() {
        return filterMap;
    }
}
