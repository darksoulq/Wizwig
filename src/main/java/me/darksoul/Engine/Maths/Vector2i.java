package me.darksoul.Engine.Maths;

public class Vector2i {
    public int x, y;

    public Vector2i() {
        this.x = 0;
        this.y = 0;
    }

    public Vector2i(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector2i(Vector2i vector) {
        this.x = vector.x;
        this.y = vector.y;
    }

    public static int dot(Vector2i v1, Vector2i v2) {
        return v1.x * v2.x + v1.y * v2.y;
    }

    public Vector2i set(int x, int y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Vector2i add(Vector2i other) {
        this.x += other.x;
        this.y += other.y;
        return this;
    }

    public Vector2i subtract(Vector2i other) {
        this.x -= other.x;
        this.y -= other.y;
        return this;
    }

    public Vector2i multiply(int scalar) {
        this.x *= scalar;
        this.y *= scalar;
        return this;
    }

    public Vector2i divide(int scalar) {
        this.x /= scalar;
        this.y /= scalar;
        return this;
    }

    public float length() {
        return (float) Math.sqrt(x * x + y * y);
    }

    public int lengthSquared() {
        return x * x + y * y;
    }

    public Vector2i normalize() {
        float length = length();
        if (length != 0) {
            this.x = (int) (this.x / length);
            this.y = (int) (this.y / length);
        }
        return this;
    }

    public Vector2i negate() {
        this.x = -this.x;
        this.y = -this.y;
        return this;
    }

    @Override
    public String toString() {
        return "Vector2i{" + "x=" + x + ", y=" + y + '}';
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

