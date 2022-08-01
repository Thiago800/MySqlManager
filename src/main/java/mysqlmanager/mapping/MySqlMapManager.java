package mysqlmanager.mapping;

import mysqlmanager.mapping.annotations.Entity;

import java.util.ArrayList;
import java.util.List;

public abstract class MySqlMapManager {

    private static List<Class<?>> maps = new ArrayList<>();

    public static void add(Class<?> classMap) throws NullPointerException, IllegalArgumentException {

        if (classMap.getAnnotation(Entity.class) == null) throw new IllegalArgumentException("is not an entity, use @Entity");
        maps.add(classMap);
    }

    public static List<Class<?>> getAll() {
        return maps;
    }

    public static <T> Class<T> getBy(Class<T> mapClass) throws IllegalArgumentException {

        for (Class<?> source : maps) {

            if (source.equals(mapClass)) {
                return mapClass;
            }
        }
        throw new IllegalArgumentException("unmapped class");
    }

    public static Class<?> getByEntityName(String name) throws NullPointerException {

        for (Class<?> source : maps) {

            Entity entity = source.getAnnotation(Entity.class);
            if (entity.name().equals(name)) return source;
        }

        throw new IllegalArgumentException("entity not found");
    }
}
