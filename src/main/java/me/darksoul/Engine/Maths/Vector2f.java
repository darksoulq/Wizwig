package me.darksoul.Engine.Maths;

public class Vector2f {
    public float x, y;

    public Vector2f() {
        this.x = 0;
        this.y = 0;
    }

    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2f(Vector2f vector) {
        this.x = vector.x;
        this.y = vector.y;
    }

    public static float dot(Vector2f v1, Vector2f v2) {
        return v1.x * v2.x + v1.y * v2.y;
    }

    public static Vector2f lerp(Vector2f start, Vector2f end, float alpha) {
        return new Vector2f(
                start.x + alpha * (end.x - start.x),
                start.y + alpha * (end.y - start.y)
        );
    }

    public Vector2f set(float x, float y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Vector2f add(Vector2f other) {
        this.x += other.x;
        this.y += other.y;
        return this;
    }

    public Vector2f subtract(Vector2f other) {
        this.x -= other.x;
        this.y -= other.y;
        return this;
    }

    public Vector2f multiply(float scalar) {
        this.x *= scalar;
        this.y *= scalar;
        return this;
    }

    public Vector2f divide(float scalar) {
        this.x /= scalar;
        this.y /= scalar;
        return this;
    }

    public float length() {
        return (float) Math.sqrt(x * x + y * y);
    }

    public float lengthSquared() {
        return x * x + y *y;
    }

    public Vector2f normalize() {
        float length = length();
        if (length != 0) {
            this.x /= length;
            this.y /= length;
        }
        return this;
    }

    public Vector2f negate() {
        this.x = -this.x;
        this.y = -this.y;
        return this;
    }

    @Override
    public String toString() {
        return "Vector2f{" + "x=" + x + ", y=" + y + '}';
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
