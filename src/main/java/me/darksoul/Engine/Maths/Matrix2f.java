package me.darksoul.Engine.Maths;

import java.nio.FloatBuffer;

public class Matrix2f {
    public float m00, m01, m10, m11;

    public Matrix2f() {
        this.m00 = 1.0f;
        this.m01 = 0.0f;
        this.m10 = 0.0f;
        this.m11 = 1.0f;
    }

    public Matrix2f(float m00, float m01, float m10, float m11) {
        this.m00 = m00;
        this.m01 = m01;
        this.m10 = m10;
        this.m11 = m11;
    }

    public Matrix2f set(float m00, float m01, float m10, float m11) {
        this.m00 = m00;
        this.m01 = m01;
        this.m10 = m10;
        this.m11 = m11;
        return this;
    }

    public Matrix2f add(Matrix2f other) {
        this.m00 += other.m00;
        this.m01 += other.m01;
        this.m10 += other.m10;
        this.m11 += other.m11;
        return this;
    }

    public Matrix2f subtract(Matrix2f other) {
        this.m00 -= other.m00;
        this.m01 -= other.m01;
        this.m10 -= other.m10;
        this.m11 -= other.m11;
        return this;
    }

    public Matrix2f multiply(float scalar) {
        this.m00 *= scalar;
        this.m01 *= scalar;
        this.m10 *= scalar;
        this.m11 *= scalar;
        return this;
    }

    public Matrix2f multiply(Matrix2f other) {
        float tempM00 = this.m00 * other.m00 + this.m01 * other.m10;
        float tempM01 = this.m00 * other.m01 + this.m01 * other.m11;
        float tempM10 = this.m10 * other.m00 + this.m11 * other.m10;
        float tempM11 = this.m10 * other.m01 + this.m11 * other.m11;

        this.m00 = tempM00;
        this.m01 = tempM01;
        this.m10 = tempM10;
        this.m11 = tempM11;

        return this;
    }

    public float determinant() {
        return this.m00 * this.m11 - this.m01 * this.m10;
    }

    public Matrix2f transpose() {
        float temp = this.m01;
        this.m01 = this.m10;
        this.m10 = temp;
        return this;
    }

    public Matrix2f invert() {
        float det = this.determinant();
        if (det == 0) {
            throw new ArithmeticException("Matrix is not invertible (determinant is 0).");
        }
        float invDet = 1.0f / det;

        float tempM00 = this.m11 * invDet;
        float tempM01 = -this.m01 * invDet;
        float tempM10 = -this.m10 * invDet;
        float tempM11 = this.m00 * invDet;

        this.m00 = tempM00;
        this.m01 = tempM01;
        this.m10 = tempM10;
        this.m11 = tempM11;

        return this;
    }

    @Override
    public String toString() {
        return "Matrix2f{" +
                "m00=" + m00 + ", m01=" + m01 +
                ", m10=" + m10 + ", m11=" + m11 + '}';
    }

    public void get(FloatBuffer buffer) {
        if (buffer.capacity() < 4) {
            throw new IllegalArgumentException("Buffer must have a capacity of at least 4.");
        }

        buffer.put(m00);
        buffer.put(m01);
        buffer.put(m10);
        buffer.put(m11);
    }

}
