package me.darksoul.Engine.Maths;

public class PlaneBox {
    private Vector3f normal;
    private float distance;

    public PlaneBox(Vector3f normal, float distance) {
        this.normal = new Vector3f(normal).normalize();
        this.distance = distance;
    }

    public boolean isPointInFront(Vector3f point) {
        return VectorUtils.dot(normal, point) > distance;
    }

    public boolean intersectsSphere(BoundingSphere sphere) {
        float distFromPlane = VectorUtils.dot(normal, sphere.getCenter()) - distance;
        return Math.abs(distFromPlane) <= sphere.getRadius();
    }

    public Vector3f getNormal() {
        return normal;
    }

    public float getDistance() {
        return distance;
    }
}

