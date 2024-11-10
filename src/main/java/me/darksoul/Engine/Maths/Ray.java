package me.darksoul.Engine.Maths;

public class Ray {
    private Vector3f origin;
    private Vector3f direction;

    public Ray(Vector3f origin, Vector3f direction) {
        this.origin = new Vector3f(origin);
        this.direction = new Vector3f(direction).normalize();
    }

    public boolean intersectsSphere(BoundingSphere sphere) {
        Vector3f oc = new Vector3f(origin).subtract(sphere.getCenter());
        float b = 2.0f * VectorUtils.dot(oc, direction);
        float c = oc.lengthSquared() - (sphere.getRadius() * sphere.getRadius());
        float discriminant = b * b - 4 * c;
        return discriminant > 0;
    }

    public boolean intersectsBoundingBox(BoundingBox box) {
        Vector3f min = box.getTransformedMin();
        Vector3f max = box.getTransformedMax();

        float tmin = (min.x - origin.x) / direction.x;
        float tmax = (max.x - origin.x) / direction.x;
        if (tmin > tmax) { float temp = tmin; tmin = tmax; tmax = temp; }

        float tymin = (min.y - origin.y) / direction.y;
        float tymax = (max.y - origin.y) / direction.y;
        if (tymin > tymax) { float temp = tymin; tymin = tymax; tymax = temp; }

        if ((tmin > tymax) || (tymin > tmax)) return false;

        if (tymin > tmin) tmin = tymin;
        if (tymax < tmax) tmax = tymax;

        float tzmin = (min.z - origin.z) / direction.z;
        float tzmax = (max.z - origin.z) / direction.z;
        if (tzmin > tzmax) { float temp = tzmin; tzmin = tzmax; tzmax = temp; }

        return (tmin <= tzmax) && (tzmin <= tmax);
    }

    public Vector3f getOrigin() {
        return origin;
    }

    public Vector3f getDirection() {
        return direction;
    }

    public void setOrigin(Vector3f origin) {
        this.origin = origin;
    }

    public void setDirection(Vector3f direction) {
        this.direction = direction;
    }
}

