package me.darksoul.Engine.ECS;

import java.util.HashMap;
import java.util.Map;

public class ECSManager {

    private final Map<Class<? extends Component>, Map<Integer, Component>> components = new HashMap<>();
    private final Map<Integer, Entity> entities = new HashMap<>();

    public Entity createEntity() {
        Entity entity = new Entity();
        entities.put(entity.getId(), entity);
        return entity;
    }

    public <T extends Component> void addComponent(Entity entity, T component) {
        components.computeIfAbsent(component.getClass(), k -> new HashMap<>())
                .put(entity.getId(), component);
    }

    public <T extends Component> T getComponent(Entity entity, Class<T> componentClass) {
        return componentClass.cast(components.getOrDefault(componentClass, new HashMap<>()).get(entity.getId()));
    }

    public <T extends Component> void removeComponent(Entity entity, Class<T> componentClass) {
        Map<Integer, Component> entityComponents = components.get(componentClass);
        if (entityComponents != null) {
            entityComponents.remove(entity.getId());
        }
    }

    public Map<Integer, Entity> getEntities() {
        return entities;
    }

    public Map<Class<? extends Component>, Map<Integer, Component>> getComponents() {
        return components;
    }
}
