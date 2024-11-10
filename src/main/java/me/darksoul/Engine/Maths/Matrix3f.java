package me.darksoul.Engine.Maths;

import java.nio.FloatBuffer;

public class Matrix3f {
    public float m00, m01, m02;
    public float m10, m11, m12;
    public float m20, m21, m22;

    public Matrix3f() {
        this.m00 = 1.0f;
        this.m01 = 0.0f;
        this.m02 = 0.0f;
        this.m10 = 0.0f;
        this.m11 = 1.0f;
        this.m12 = 0.0f;
        this.m20 = 0.0f;
        this.m21 = 0.0f;
        this.m22 = 1.0f;
    }

    public Matrix3f(float m00, float m01, float m02,
                    float m10, float m11, float m12,
                    float m20, float m21, float m22) {
        this.m00 = m00;
        this.m01 = m01;
        this.m02 = m02;
        this.m10 = m10;
        this.m11 = m11;
        this.m12 = m12;
        this.m20 = m20;
        this.m21 = m21;
        this.m22 = m22;
    }

    public Matrix3f set(float m00, float m01, float m02,
                        float m10, float m11, float m12,
                        float m20, float m21, float m22) {
        this.m00 = m00;
        this.m01 = m01;
        this.m02 = m02;
        this.m10 = m10;
        this.m11 = m11;
        this.m12 = m12;
        this.m20 = m20;
        this.m21 = m21;
        this.m22 = m22;
        return this;
    }

    public Matrix3f add(Matrix3f other) {
        this.m00 += other.m00;
        this.m01 += other.m01;
        this.m02 += other.m02;
        this.m10 += other.m10;
        this.m11 += other.m11;
        this.m12 += other.m12;
        this.m20 += other.m20;
        this.m21 += other.m21;
        this.m22 += other.m22;
        return this;
    }

    public Matrix3f subtract(Matrix3f other) {
        this.m00 -= other.m00;
        this.m01 -= other.m01;
        this.m02 -= other.m02;
        this.m10 -= other.m10;
        this.m11 -= other.m11;
        this.m12 -= other.m12;
        this.m20 -= other.m20;
        this.m21 -= other.m21;
        this.m22 -= other.m22;
        return this;
    }

    public Matrix3f multiply(float scalar) {
        this.m00 *= scalar;
        this.m01 *= scalar;
        this.m02 *= scalar;
        this.m10 *= scalar;
        this.m11 *= scalar;
        this.m12 *= scalar;
        this.m20 *= scalar;
        this.m21 *= scalar;
        this.m22 *= scalar;
        return this;
    }

    public Matrix3f multiply(Matrix3f other) {
        float tempM00 = this.m00 * other.m00 + this.m01 * other.m10 + this.m02 * other.m20;
        float tempM01 = this.m00 * other.m01 + this.m01 * other.m11 + this.m02 * other.m21;
        float tempM02 = this.m00 * other.m02 + this.m01 * other.m12 + this.m02 * other.m22;

        float tempM10 = this.m10 * other.m00 + this.m11 * other.m10 + this.m12 * other.m20;
        float tempM11 = this.m10 * other.m01 + this.m11 * other.m11 + this.m12 * other.m21;
        float tempM12 = this.m10 * other.m02 + this.m11 * other.m12 + this.m12 * other.m22;

        float tempM20 = this.m20 * other.m00 + this.m21 * other.m10 + this.m22 * other.m20;
        float tempM21 = this.m20 * other.m01 + this.m21 * other.m11 + this.m22 * other.m21;
        float tempM22 = this.m20 * other.m02 + this.m21 * other.m12 + this.m22 * other.m22;

        this.m00 = tempM00;
        this.m01 = tempM01;
        this.m02 = tempM02;
        this.m10 = tempM10;
        this.m11 = tempM11;
        this.m12 = tempM12;
        this.m20 = tempM20;
        this.m21 = tempM21;
        this.m22 = tempM22;

        return this;
    }

    public float determinant() {
        return m00 * (m11 * m22 - m12 * m21)
                - m01 * (m10 * m22 - m12 * m20)
                + m02 * (m10 * m21 - m11 * m20);
    }

    public Matrix3f transpose() {
        float tempM01 = this.m10;
        float tempM02 = this.m20;
        this.m10 = this.m01;
        this.m20 = this.m02;
        this.m01 = tempM01;
        this.m02 = tempM02;

        float tempM12 = this.m21;
        this.m21 = this.m12;
        this.m12 = tempM12;

        return this;
    }

    public Matrix3f invert() {
        float det = this.determinant();
        if (det == 0) {
            throw new ArithmeticException("Matrix is not invertible (determinant is 0).");
        }
        float invDet = 1.0f / det;

        Matrix3f result = new Matrix3f(
                (m11 * m22 - m12 * m21) * invDet, (m02 * m21 - m01 * m22) * invDet, (m01 * m12 - m02 * m11) * invDet,
                (m12 * m20 - m10 * m22) * invDet, (m00 * m22 - m02 * m20) * invDet, (m02 * m10 - m00 * m12) * invDet,
                (m10 * m21 - m11 * m20) * invDet, (m01 * m20 - m00 * m21) * invDet, (m00 * m11 - m01 * m10) * invDet
        );

        this.m00 = result.m00;
        this.m01 = result.m01;
        this.m02 = result.m02;
        this.m10 = result.m10;
        this.m11 = result.m11;
        this.m12 = result.m12;
        this.m20 = result.m20;
        this.m21 = result.m21;
        this.m22 = result.m22;

        return this;
    }

    @Override
    public String toString() {
        return "Matrix3f{" +
                "m00=" + m00 + ", m01=" + m01 + ", m02=" + m02 +
                ", m10=" + m10 + ", m11=" + m11 + ", m12=" + m12 +
                ", m20=" + m20 + ", m21=" + m21 + ", m22=" + m22 + '}';
    }

    public void get(FloatBuffer buffer) {
        if (buffer.capacity() < 9) {
            throw new IllegalArgumentException("Buffer must have a capacity of at least 9.");
        }

        buffer.put(m00);
        buffer.put(m01);
        buffer.put(m02);

        buffer.put(m10);
        buffer.put(m11);
        buffer.put(m12);

        buffer.put(m20);
        buffer.put(m21);
        buffer.put(m22);
    }

}
