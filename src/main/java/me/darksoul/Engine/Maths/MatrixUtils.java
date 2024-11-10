package me.darksoul.Engine.Maths;

public class MatrixUtils {

    // Matrix2f
    public static Vector2f multiply(Matrix2f matrix, Vector2f vector) {
        float x = matrix.m00 * vector.x + matrix.m01 * vector.y;
        float y = matrix.m10 * vector.x + matrix.m11 * vector.y;
        return new Vector2f(x, y);
    }

    public static Matrix2f identity2f() {
        return new Matrix2f(1.0f, 0.0f, 0.0f, 1.0f);
    }

    public static Matrix2f rotation2f(float angle) {
        float cos = (float) Math.cos(angle);
        float sin = (float) Math.sin(angle);
        return new Matrix2f(cos, -sin, sin, cos);
    }

    public static Matrix2f scale2f(float sx, float sy) {
        return new Matrix2f(sx, 0.0f, 0.0f, sy);
    }

    // Matrix3f
    public static Vector3f multiply(Matrix3f matrix, Vector3f vector) {
        float x = matrix.m00 * vector.x + matrix.m01 * vector.y + matrix.m02 * vector.z;
        float y = matrix.m10 * vector.x + matrix.m11 * vector.y + matrix.m12 * vector.z;
        float z = matrix.m20 * vector.x + matrix.m21 * vector.y + matrix.m22 * vector.z;
        return new Vector3f(x, y, z);
    }

    public static Matrix3f identity3f() {
        return new Matrix3f(1.0f, 0.0f, 0.0f,
                0.0f, 1.0f, 0.0f,
                0.0f, 0.0f, 1.0f);
    }

    public static Matrix3f rotation3f(float angle) {
        float cos = (float) Math.cos(angle);
        float sin = (float) Math.sin(angle);
        return new Matrix3f(cos, -sin, 0.0f,
                sin, cos, 0.0f,
                0.0f, 0.0f, 1.0f);
    }

    public static Matrix3f scale3f(float sx, float sy) {
        return new Matrix3f(sx, 0.0f, 0.0f,
                0.0f, sy, 0.0f,
                0.0f, 0.0f, 1.0f);
    }

    public static Matrix3f translate3f(float tx, float ty) {
        return new Matrix3f(1.0f, 0.0f, tx,
                0.0f, 1.0f, ty,
                0.0f, 0.0f, 1.0f);
    }

    // Matrix4f
    public static Matrix4f identity4f() {
        return new Matrix4f();
    }

    public static Matrix4f rotation4f(float angle, float x, float y, float z) {
        float cos = (float) Math.cos(angle);
        float sin = (float) Math.sin(angle);
        float oneMinusCos = 1.0f - cos;

        return new Matrix4f(
                cos + x * x * oneMinusCos, x * y * oneMinusCos - z * sin, x * z * oneMinusCos + y * sin, 0.0f,
                y * x * oneMinusCos + z * sin, cos + y * y * oneMinusCos, y * z * oneMinusCos - x * sin, 0.0f,
                z * x * oneMinusCos - y * sin, z * y * oneMinusCos + x * sin, cos + z * z * oneMinusCos, 0.0f,
                0.0f, 0.0f, 0.0f, 1.0f
        );
    }

    public static Matrix4f scale4f(float sx, float sy, float sz) {
        return new Matrix4f(
                sx, 0.0f, 0.0f, 0.0f,
                0.0f, sy, 0.0f, 0.0f,
                0.0f, 0.0f, sz, 0.0f,
                0.0f, 0.0f, 0.0f, 1.0f
        );
    }

    public static Matrix4f translate4f(float tx, float ty, float tz) {
        return new Matrix4f(
                1.0f, 0.0f, 0.0f, tx,
                0.0f, 1.0f, 0.0f, ty,
                0.0f, 0.0f, 1.0f, tz,
                0.0f, 0.0f, 0.0f, 1.0f
        );
    }

    public static Matrix4f perspective(float fov, float aspect, float near, float far) {
        float tanHalfFov = (float) Math.tan(fov / 2.0f);
        return new Matrix4f(
                1.0f / (aspect * tanHalfFov), 0.0f, 0.0f, 0.0f,
                0.0f, 1.0f / tanHalfFov, 0.0f, 0.0f,
                0.0f, 0.0f, -(far + near) / (far - near), -1.0f,
                0.0f, 0.0f, -(2 * far * near) / (far - near), 0.0f
        );
    }

    public static Matrix4f orthoPerspective(float left, float right, float bottom, float top, float near, float far) {
        return new Matrix4f(
                2.0f / (right - left), 0.0f, 0.0f, -(right + left) / (right - left),
                0.0f, 2.0f / (top - bottom), 0.0f, -(top + bottom) / (top - bottom),
                0.0f, 0.0f, -2.0f / (far - near), -(far + near) / (far - near),
                0.0f, 0.0f, 0.0f, 1.0f
        );
    }

    public static Matrix4f lookAt(Vector3f cameraPosition, Vector3f target, Vector3f up) {
        // Compute the forward vector (camera direction)
        Vector3f forward = target.subtract(cameraPosition).normalize();

        // Compute the right vector (camera right direction)
        Vector3f right = up.cross(up, forward).normalize();

        // Compute the up vector (camera up direction)
        Vector3f newUp = forward.cross(forward, right).normalize();

        // Create the final look-at matrix
        Matrix4f matrix = new Matrix4f();
        matrix.m00 = right.x;
        matrix.m01 = right.y;
        matrix.m02 = right.z;
        matrix.m03 = 0.0f;

        matrix.m10 = newUp.x;
        matrix.m11 = newUp.y;
        matrix.m12 = newUp.z;
        matrix.m13 = 0.0f;

        matrix.m20 = -forward.x;
        matrix.m21 = -forward.y;
        matrix.m22 = -forward.z;
        matrix.m23 = 0.0f;

        matrix.m30 = -right.dot(right, cameraPosition);
        matrix.m31 = -newUp.dot(newUp, cameraPosition);
        matrix.m32 = forward.dot(forward, cameraPosition);
        matrix.m33 = 1.0f;

        return matrix;
    }

}


