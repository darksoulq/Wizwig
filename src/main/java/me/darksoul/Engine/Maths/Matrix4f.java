package me.darksoul.Engine.Maths;

import java.nio.FloatBuffer;

public class Matrix4f {
    public float m00, m01, m02, m03;
    public float m10, m11, m12, m13;
    public float m20, m21, m22, m23;
    public float m30, m31, m32, m33;

    public Matrix4f() {
        this.m00 = 1.0f;
        this.m01 = 0.0f;
        this.m02 = 0.0f;
        this.m03 = 0.0f;
        this.m10 = 0.0f;
        this.m11 = 1.0f;
        this.m12 = 0.0f;
        this.m13 = 0.0f;
        this.m20 = 0.0f;
        this.m21 = 0.0f;
        this.m22 = 1.0f;
        this.m23 = 0.0f;
        this.m30 = 0.0f;
        this.m31 = 0.0f;
        this.m32 = 0.0f;
        this.m33 = 1.0f;
    }

    public Matrix4f(float m00, float m01, float m02, float m03,
                    float m10, float m11, float m12, float m13,
                    float m20, float m21, float m22, float m23,
                    float m30, float m31, float m32, float m33) {
        this.m00 = m00;
        this.m01 = m01;
        this.m02 = m02;
        this.m03 = m03;
        this.m10 = m10;
        this.m11 = m11;
        this.m12 = m12;
        this.m13 = m13;
        this.m20 = m20;
        this.m21 = m21;
        this.m22 = m22;
        this.m23 = m23;
        this.m30 = m30;
        this.m31 = m31;
        this.m32 = m32;
        this.m33 = m33;
    }

    public Matrix4f set(float m00, float m01, float m02, float m03,
                        float m10, float m11, float m12, float m13,
                        float m20, float m21, float m22, float m23,
                        float m30, float m31, float m32, float m33) {
        this.m00 = m00;
        this.m01 = m01;
        this.m02 = m02;
        this.m03 = m03;
        this.m10 = m10;
        this.m11 = m11;
        this.m12 = m12;
        this.m13 = m13;
        this.m20 = m20;
        this.m21 = m21;
        this.m22 = m22;
        this.m23 = m23;
        this.m30 = m30;
        this.m31 = m31;
        this.m32 = m32;
        this.m33 = m33;
        return this;
    }

    public Matrix4f add(Matrix4f other) {
        this.m00 += other.m00;
        this.m01 += other.m01;
        this.m02 += other.m02;
        this.m03 += other.m03;
        this.m10 += other.m10;
        this.m11 += other.m11;
        this.m12 += other.m12;
        this.m13 += other.m13;
        this.m20 += other.m20;
        this.m21 += other.m21;
        this.m22 += other.m22;
        this.m23 += other.m23;
        this.m30 += other.m30;
        this.m31 += other.m31;
        this.m32 += other.m32;
        this.m33 += other.m33;
        return this;
    }

    public Matrix4f subtract(Matrix4f other) {
        this.m00 -= other.m00;
        this.m01 -= other.m01;
        this.m02 -= other.m02;
        this.m03 -= other.m03;
        this.m10 -= other.m10;
        this.m11 -= other.m11;
        this.m12 -= other.m12;
        this.m13 -= other.m13;
        this.m20 -= other.m20;
        this.m21 -= other.m21;
        this.m22 -= other.m22;
        this.m23 -= other.m23;
        this.m30 -= other.m30;
        this.m31 -= other.m31;
        this.m32 -= other.m32;
        this.m33 -= other.m33;
        return this;
    }

    public Matrix4f multiply(float scalar) {
        this.m00 *= scalar;
        this.m01 *= scalar;
        this.m02 *= scalar;
        this.m03 *= scalar;
        this.m10 *= scalar;
        this.m11 *= scalar;
        this.m12 *= scalar;
        this.m13 *= scalar;
        this.m20 *= scalar;
        this.m21 *= scalar;
        this.m22 *= scalar;
        this.m23 *= scalar;
        this.m30 *= scalar;
        this.m31 *= scalar;
        this.m32 *= scalar;
        this.m33 *= scalar;
        return this;
    }

    public Matrix4f multiply(Matrix4f other) {
        float tempM00 = this.m00 * other.m00 + this.m01 * other.m10 + this.m02 * other.m20 + this.m03 * other.m30;
        float tempM01 = this.m00 * other.m01 + this.m01 * other.m11 + this.m02 * other.m21 + this.m03 * other.m31;
        float tempM02 = this.m00 * other.m02 + this.m01 * other.m12 + this.m02 * other.m22 + this.m03 * other.m32;
        float tempM03 = this.m00 * other.m03 + this.m01 * other.m13 + this.m02 * other.m23 + this.m03 * other.m33;

        float tempM10 = this.m10 * other.m00 + this.m11 * other.m10 + this.m12 * other.m20 + this.m13 * other.m30;
        float tempM11 = this.m10 * other.m01 + this.m11 * other.m11 + this.m12 * other.m21 + this.m13 * other.m31;
        float tempM12 = this.m10 * other.m02 + this.m11 * other.m12 + this.m12 * other.m22 + this.m13 * other.m32;
        float tempM13 = this.m10 * other.m03 + this.m11 * other.m13 + this.m12 * other.m23 + this.m13 * other.m33;

        float tempM20 = this.m20 * other.m00 + this.m21 * other.m10 + this.m22 * other.m20 + this.m23 * other.m30;
        float tempM21 = this.m20 * other.m01 + this.m21 * other.m11 + this.m22 * other.m21 + this.m23 * other.m31;
        float tempM22 = this.m20 * other.m02 + this.m21 * other.m12 + this.m22 * other.m22 + this.m23 * other.m32;
        float tempM23 = this.m20 * other.m03 + this.m21 * other.m13 + this.m22 * other.m23 + this.m23 * other.m33;

        float tempM30 = this.m30 * other.m00 + this.m31 * other.m10 + this.m32 * other.m20 + this.m33 * other.m30;
        float tempM31 = this.m30 * other.m01 + this.m31 * other.m11 + this.m32 * other.m21 + this.m33 * other.m31;
        float tempM32 = this.m30 * other.m02 + this.m31 * other.m12 + this.m32 * other.m22 + this.m33 * other.m32;
        float tempM33 = this.m30 * other.m03 + this.m31 * other.m13 + this.m32 * other.m23 + this.m33 * other.m33;

        this.m00 = tempM00;
        this.m01 = tempM01;
        this.m02 = tempM02;
        this.m03 = tempM03;
        this.m10 = tempM10;
        this.m11 = tempM11;
        this.m12 = tempM12;
        this.m13 = tempM13;
        this.m20 = tempM20;
        this.m21 = tempM21;
        this.m22 = tempM22;
        this.m23 = tempM23;
        this.m30 = tempM30;
        this.m31 = tempM31;
        this.m32 = tempM32;
        this.m33 = tempM33;

        return this;
    }

    public float determinant() {
        return m00 * (m11 * m22 * m33 + m12 * m23 * m31 + m13 * m21 * m32
                - m13 * m22 * m31 - m12 * m21 * m33 - m11 * m23 * m32)
                - m01 * (m10 * m22 * m33 + m12 * m23 * m30 + m13 * m20 * m32
                - m13 * m22 * m30 - m12 * m20 * m33 - m10 * m23 * m32)
                + m02 * (m10 * m21 * m33 + m11 * m23 * m30 + m13 * m20 * m31
                - m13 * m21 * m30 - m11 * m20 * m33 - m10 * m23 * m31)
                - m03 * (m10 * m21 * m32 + m11 * m22 * m30 + m12 * m20 * m31
                - m12 * m21 * m30 - m11 * m20 * m32 - m10 * m22 * m31);
    }

    public Vector4f transform(Vector4f v) {
        float x = v.x * m00 + v.y * m01 + v.z * m02 + v.w * m03;
        float y = v.x * m10 + v.y * m11 + v.z * m12 + v.w * m13;
        float z = v.x * m20 + v.y * m21 + v.z * m22 + v.w * m23;
        float w = v.x * m30 + v.y * m31 + v.z * m32 + v.w * m33;

        return new Vector4f(x, y, z, w);
    }

    public Vector3f transform(Vector3f v) {
        float x = v.x * m00 + v.y * m01 + v.z * m02 + m03;
        float y = v.x * m10 + v.y * m11 + v.z * m12 + m13;
        float z = v.x * m20 + v.y * m21 + v.z * m22 + m23;

        return new Vector3f(x, y, z);
    }

    public Matrix4f transpose() {
        float temp;

        temp = this.m01;
        this.m01 = this.m10;
        this.m10 = temp;

        temp = this.m02;
        this.m02 = this.m20;
        this.m20 = temp;

        temp = this.m03;
        this.m03 = this.m30;
        this.m30 = temp;

        temp = this.m12;
        this.m12 = this.m21;
        this.m21 = temp;

        temp = this.m13;
        this.m13 = this.m31;
        this.m31 = temp;

        temp = this.m23;
        this.m23 = this.m32;
        this.m32 = temp;

        return this;
    }

    public Matrix4f invert() {
        float det = determinant();

        if (det == 0) {
            throw new IllegalArgumentException("Matrix is singular and cannot be inverted");
        }

        Matrix4f adjugate = new Matrix4f(
                cofactor(0, 0), cofactor(1, 0), cofactor(2, 0), cofactor(3, 0),
                cofactor(0, 1), cofactor(1, 1), cofactor(2, 1), cofactor(3, 1),
                cofactor(0, 2), cofactor(1, 2), cofactor(2, 2), cofactor(3, 2),
                cofactor(0, 3), cofactor(1, 3), cofactor(2, 3), cofactor(3, 3)
        );

        adjugate.transpose();

        return adjugate.multiply(1.0f / det);
    }

    private float cofactor(int row, int col) {
        float[] submatrix = new float[9];
        int index = 0;
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                if (r != row && c != col) {
                    submatrix[index++] = get(r, c);
                }
            }
        }

        float minor = determinant3x3(submatrix);

        return (row + col) % 2 == 0 ? minor : -minor;
    }

    private float get(int row, int col) {
        switch (row) {
            case 0:
                return col == 0 ? m00 : col == 1 ? m01 : col == 2 ? m02 : m03;
            case 1:
                return col == 0 ? m10 : col == 1 ? m11 : col == 2 ? m12 : m13;
            case 2:
                return col == 0 ? m20 : col == 1 ? m21 : col == 2 ? m22 : m23;
            case 3:
                return col == 0 ? m30 : col == 1 ? m31 : col == 2 ? m32 : m33;
            default:
                return 0;
        }
    }

    private float determinant3x3(float[] m) {
        return m[0] * (m[4] * m[8] - m[5] * m[7])
                - m[1] * (m[3] * m[8] - m[5] * m[6])
                + m[2] * (m[3] * m[7] - m[4] * m[6]);
    }

    public float[] toArray() {
        return new float[]{
                m00, m01, m02, m03,
                m10, m11, m12, m13,
                m20, m21, m22, m23,
                m30, m31, m32, m33
        };
    }

    public void get(FloatBuffer buffer) {
        if (buffer.capacity() < 16) {
            throw new IllegalArgumentException("Buffer must have a capacity of at least 16.");
        }

        buffer.put(m00);
        buffer.put(m01);
        buffer.put(m02);
        buffer.put(m03);

        buffer.put(m10);
        buffer.put(m11);
        buffer.put(m12);
        buffer.put(m13);

        buffer.put(m20);
        buffer.put(m21);
        buffer.put(m22);
        buffer.put(m23);

        buffer.put(m30);
        buffer.put(m31);
        buffer.put(m32);
        buffer.put(m33);
    }

    public Matrix4f translate(Vector3f translation) {
        Matrix4f translationMatrix = new Matrix4f();
        translationMatrix.m03 = translation.x;
        translationMatrix.m13 = translation.y;
        translationMatrix.m23 = translation.z;

        return this.multiply(translationMatrix);
    }

    public void setIdentity() {
        this.m00 = 1.0f;
        this.m01 = 0.0f;
        this.m02 = 0.0f;
        this.m03 = 0.0f;
        this.m10 = 0.0f;
        this.m11 = 1.0f;
        this.m12 = 0.0f;
        this.m13 = 0.0f;
        this.m20 = 0.0f;
        this.m21 = 0.0f;
        this.m22 = 1.0f;
        this.m23 = 0.0f;
        this.m30 = 0.0f;
        this.m31 = 0.0f;
        this.m32 = 0.0f;
        this.m33 = 1.0f;

    }

}
