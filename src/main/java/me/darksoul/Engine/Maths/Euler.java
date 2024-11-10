package me.darksoul.Engine.Maths;

public class Euler {
    private float pitch, yaw, roll;

    public Euler(float pitch, float yaw, float roll) {
        this.pitch = pitch;
        this.yaw = yaw;
        this.roll = roll;
    }

    public Euler() {
        this(0.0f, 0.0f, 0.0f);
    }

    public float getPitch() {
        return pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public float getRoll() {
        return roll;
    }

    public void setRoll(float roll) {
        this.roll = roll;
    }

    public Quaternion toQuaternion() {
        float cy = (float) Math.cos(yaw * 0.5f);
        float sy = (float) Math.sin(yaw * 0.5f);
        float cp = (float) Math.cos(pitch * 0.5f);
        float sp = (float) Math.sin(pitch * 0.5f);
        float cr = (float) Math.cos(roll * 0.5f);
        float sr = (float) Math.sin(roll * 0.5f);

        float w = cr * cp * cy + sr * sp * sy;
        float x = sr * cp * cy - cr * sp * sy;
        float y = cr * sp * cy + sr * cp * sy;
        float z = cr * cp * sy - sr * sp * cy;

        return new Quaternion(w, x, y, z);
    }

    public Matrix4f toRotationMatrix() {
        float c1 = (float) Math.cos(yaw);
        float s1 = (float) Math.sin(yaw);
        float c2 = (float) Math.cos(pitch);
        float s2 = (float) Math.sin(pitch);
        float c3 = (float) Math.cos(roll);
        float s3 = (float) Math.sin(roll);

        Matrix4f matrix = new Matrix4f();
        matrix.set(
                c1 * c2, c1 * s2 * s3 - s1 * c3, c1 * s2 * c3 + s1 * s3, 0.0f,
                s1 * c2, s1 * s2 * s3 + c1 * c3, s1 * s2 * c3 - c1 * s3, 0.0f,
                -s2, c2 * s3, c2 * c3, 0.0f,
                0.0f, 0.0f, 0.0f, 1.0f
        );
        return matrix;
    }

    public Vector3f rotate(Vector3f vector) {
        Quaternion quaternion = this.toQuaternion();
        return quaternion.rotate(vector);
    }

    public Vector3f rotateWithMatrix(Vector3f vector) {
        Matrix4f rotationMatrix = this.toRotationMatrix();
        return rotationMatrix.transform(vector);
    }

    @Override
    public String toString() {
        return "Euler[pitch=" + pitch + ", yaw=" + yaw + ", roll=" + roll + "]";
    }
}

