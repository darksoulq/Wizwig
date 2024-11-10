package me.darksoul.Engine.Maths;

public class Quaternion {
    private float w, x, y, z;

    public Quaternion(float w, float x, float y, float z) {
        this.w = w;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Quaternion() {
        this(1.0f, 0.0f, 0.0f, 0.0f);
    }

    public static Quaternion fromAxisAngle(Vector3f axis, float angle) {
        float halfAngle = angle / 2.0f;
        float s = (float) Math.sin(halfAngle);
        float c = (float) Math.cos(halfAngle);
        return new Quaternion(c, axis.getX() * s, axis.getY() * s, axis.getZ() * s);
    }

    public static Matrix4f toRotationMatrix(Quaternion q) {
        float xx = q.getX() * q.getX();
        float yy = q.getY() * q.getY();
        float zz = q.getZ() * q.getZ();
        float xy = q.getX() * q.getY();
        float xz = q.getX() * q.getZ();
        float yz = q.getY() * q.getZ();
        float wx = q.getW() * q.getX();
        float wy = q.getW() * q.getY();
        float wz = q.getW() * q.getZ();

        Matrix4f matrix = new Matrix4f();
        matrix.set(
                1.0f - 2.0f * (yy + zz), 2.0f * (xy - wz), 2.0f * (xz + wy), 0.0f,
                2.0f * (xy + wz), 1.0f - 2.0f * (xx + zz), 2.0f * (yz - wx), 0.0f,
                2.0f * (xz - wy), 2.0f * (yz + wx), 1.0f - 2.0f * (xx + yy), 0.0f,
                0.0f, 0.0f, 0.0f, 1.0f
        );
        return matrix;
    }

    public float getW() {
        return w;
    }

    public void setW(float w) {
        this.w = w;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public Quaternion conjugate() {
        return new Quaternion(w, -x, -y, -z);
    }

    public Quaternion normalize() {
        float length = length();
        if (length != 0) {
            return new Quaternion(w / length, x / length, y / length, z / length);
        }
        return this;
    }

    public float length() {
        return (float) Math.sqrt(w * w + x * x + y * y + z * z);
    }

    public Quaternion multiply(Quaternion q) {
        float w_ = w * q.getW() - x * q.getX() - y * q.getY() - z * q.getZ();
        float x_ = w * q.getX() + x * q.getW() + y * q.getZ() - z * q.getY();
        float y_ = w * q.getY() - x * q.getZ() + y * q.getW() + z * q.getX();
        float z_ = w * q.getZ() + x * q.getY() - y * q.getX() + z * q.getW();
        return new Quaternion(w_, x_, y_, z_);
    }

    public Quaternion multiply(float scalar) {
        return new Quaternion(w * scalar, x * scalar, y * scalar, z * scalar);
    }

    public Quaternion inverse() {
        float lengthSquared = length() * length();
        if (lengthSquared > 0) {
            return conjugate().multiply(1.0f / lengthSquared);
        }
        return this;
    }

    public Vector3f rotate(Vector3f vector) {
        Quaternion qv = new Quaternion(0, vector.getX(), vector.getY(), vector.getZ());
        Quaternion qConjugate = conjugate();
        Quaternion result = this.multiply(qv).multiply(qConjugate);
        return new Vector3f(result.getX(), result.getY(), result.getZ());
    }

    @Override
    public String toString() {
        return "Quaternion[w=" + w + ", x=" + x + ", y=" + y + ", z=" + z + "]";
    }

    public Euler toEuler() {
        float sinr_cosp = 2.0f * (w * x + y * z);
        float cosr_cosp = 1.0f - 2.0f * (x * x + y * y);
        float roll = (float) Math.atan2(sinr_cosp, cosr_cosp);

        float sinp = 2.0f * (w * y - z * x);
        float pitch = 0.0f;
        if (Math.abs(sinp) >= 1) {
            pitch = Math.copySign((float) Math.PI / 2, sinp);
        } else {
            pitch = (float) Math.asin(sinp);
        }

        float siny_cosp = 2.0f * (w * z + x * y);
        float cosy_cosp = 1.0f - 2.0f * (y * y + z * z);
        float yaw = (float) Math.atan2(siny_cosp, cosy_cosp);

        return new Euler(pitch, yaw, roll);
    }

}

