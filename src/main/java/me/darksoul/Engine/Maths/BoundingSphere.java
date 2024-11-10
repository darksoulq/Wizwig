package me.darksoul.Engine.Maths;

public class BoundingSphere {
    private Vector3f center;
    private float radius;

    public BoundingSphere(Vector3f center, float radius) {
        this.center = new Vector3f(center);
        this.radius = radius;
    }

    public boolean intersects(BoundingSphere other) {
        float distanceSquared = VectorUtils.distanceSquared(center, other.center);
        float radiusSum = radius + other.radius;
        return distanceSquared <= (radiusSum * radiusSum);
    }

    public boolean contains(Vector3f point) {
        return VectorUtils.distanceSquared(center, point) <= (radius * radius);
    }

    public void move(Vector3f delta) {
        center.add(delta);
    }

    public Vector3f getCenter() {
        return center;
    }

    public float getRadius() {
        return radius;
    }
}

