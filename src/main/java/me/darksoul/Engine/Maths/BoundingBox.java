package me.darksoul.Engine.Maths;

import me.darksoul.Engine.ECS.Component;

public class BoundingBox implements Component {
    private Vector3f min;
    private Vector3f max;
    private Vector3f position;

    public BoundingBox(Vector3f min, Vector3f max, Vector3f position) {
        this.min = new Vector3f(min);
        this.max = new Vector3f(max);
        this.position = new Vector3f(position);
    }

    public BoundingBox(Vector3f min, Vector3f max) {
        this(min, max, new Vector3f(0, 0, 0));
    }

    public BoundingBox(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        this(new Vector3f(minX, minY, minZ), new Vector3f(maxX, maxY, maxZ), new Vector3f(0, 0, 0));
    }

    public void move(Vector3f delta) {
        position.add(delta);
    }

    public void setPosition(Vector3f newPosition) {
        this.position.set(newPosition);
    }

    public Vector3f getTransformedMin() {
        return new Vector3f(min).add(position);
    }

    public Vector3f getTransformedMax() {
        return new Vector3f(max).add(position);
    }

    public boolean intersects(BoundingBox other) {
        Vector3f otherMin = other.getTransformedMin();
        Vector3f otherMax = other.getTransformedMax();

        Vector3f thisMin = this.getTransformedMin();
        Vector3f thisMax = this.getTransformedMax();

        return (thisMin.x <= otherMax.x && thisMax.x >= otherMin.x) &&
                (thisMin.y <= otherMax.y && thisMax.y >= otherMin.y) &&
                (thisMin.z <= otherMax.z && thisMax.z >= otherMin.z);
    }

    public boolean contains(Vector3f point) {
        Vector3f transformedMin = getTransformedMin();
        Vector3f transformedMax = getTransformedMax();

        return (point.x >= transformedMin.x && point.x <= transformedMax.x) &&
                (point.y >= transformedMin.y && point.y <= transformedMax.y) &&
                (point.z >= transformedMin.z && point.z <= transformedMax.z);
    }

    public void expand(Vector3f point) {
        min.x = Math.min(min.x, point.x - position.x);
        min.y = Math.min(min.y, point.y - position.y);
        min.z = Math.min(min.z, point.z - position.z);

        max.x = Math.max(max.x, point.x - position.x);
        max.y = Math.max(max.y, point.y - position.y);
        max.z = Math.max(max.z, point.z - position.z);
    }

    public void expand(BoundingBox other) {
        expand(other.getTransformedMin());
        expand(other.getTransformedMax());
    }

    public Vector3f getCenter() {
        return new Vector3f((min.x + max.x) / 2, (min.y + max.y) / 2, (min.z + max.z) / 2).add(position);
    }

    public Vector3f getMin() {
        return min;
    }

    public Vector3f getMax() {
        return max;
    }

    public Vector3f getPosition() {
        return position;
    }

    public Vector3f getDimensions() {
        return new Vector3f(max.x - min.x, max.y - min.y, max.z - min.z);
    }

    @Override
    public String toString() {
        return "BoundingBox[min=" + getTransformedMin() + ", max=" + getTransformedMax() + ", position=" + position + "]";
    }
}
