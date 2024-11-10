package me.darksoul.Engine.Maths;

public class Vector3i {
    public int x, y, z;

    public Vector3i() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public Vector3i(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3i(Vector3i vector) {
        this.x = vector.x;
        this.y = vector.y;
        this.z = vector.z;
    }

    public static int dot(Vector3i v1, Vector3i v2) {
        return v1.x * v2.x + v1.y * v2.y + v1.z * v2.z;
    }

    public static Vector3i cross(Vector3i v1, Vector3i v2) {
        int x = v1.y * v2.z - v1.z * v2.y;
        int y = v1.z * v2.x - v1.x * v2.z;
        int z = v1.x * v2.y - v1.y * v2.x;
        return new Vector3i(x, y, z);
    }

    public Vector3i set(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }

    public Vector3i add(Vector3i other) {
        this.x += other.x;
        this.y += other.y;
        this.z += other.z;
        return this;
    }

    public Vector3i subtract(Vector3i other) {
        this.x -= other.x;
        this.y -= other.y;
        this.z -= other.z;
        return this;
    }

    public Vector3i multiply(int scalar) {
        this.x *= scalar;
        this.y *= scalar;
        this.z *= scalar;
        return this;
    }

    public Vector3i divide(int scalar) {
        this.x /= scalar;
        this.y /= scalar;
        this.z /= scalar;
        return this;
    }

    public int length() {
        return (int) Math.sqrt(x * x + y * y + z * z);
    }

    public int lengthSquared() {
        return x * x + y * y + z * z;
    }

    public Vector3i normalize() {
        int length = length();
        if (length != 0) {
            this.x /= length;
            this.y /= length;
            this.z /= length;
        }
        return this;
    }

    public Vector3i negate() {
        this.x = -this.x;
        this.y = -this.y;
        this.z = -this.z;
        return this;
    }

    @Override
    public String toString() {
        return "Vector3i{" + "x=" + x + ", y=" + y + ", z=" + z + '}';
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }
}

