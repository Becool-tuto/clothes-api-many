package org.demo.clothes.api.helper;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class ClothesHelper {

    public Map<String,Object> createFilterMap(String filterQuery){
        Map<String,Object> filterMap = parse(filterQuery);
        return filterMap;
    }

    private Map<String, Object> parse(String filterQuery) throws IllegalArgumentException{
        Map<String, Object> filterMap = new HashMap<>();
        if (filterQuery != null) {
            String[] filters = filterQuery.split("\\|");
            for (String filter : filters) {
                String[] filtersAttr = filter.split("::");
                try {
                    filterMap.put(filtersAttr[0], filtersAttr[1]);
                } catch (ArrayIndexOutOfBoundsException ex) {
                    throw new IllegalArgumentException();
                }
            }
        }
        return filterMap;
    }
}
