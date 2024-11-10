package me.darksoul.Engine.Maths;

public class VectorUtils {

    // Vector2f
    public static float distance(Vector2f v1, Vector2f v2) {
        float dx = v1.x - v2.x;
        float dy = v1.y - v2.y;
        return (float) Math.sqrt(dx * dx + dy * dy);
    }

    public static float distanceSquared(Vector2f v1, Vector2f v2) {
        float dx = v1.x - v2.x;
        float dy = v1.y - v2.y;
        return dx * dx + dy * dy;
    }

    public static float angle(Vector2f v1, Vector2f v2) {
        float dotProduct = Vector2f.dot(v1, v2);
        float length1 = v1.length();
        float length2 = v2.length();
        return (float) Math.acos(dotProduct / (length1 * length2));
    }

    public static float cross(Vector2f v1, Vector2f v2) {
        return v1.x * v2.y - v1.y * v2.x;
    }

    public static float dot(Vector2f v1, Vector2f v2) {
        return Vector2f.dot(v1, v2);
    }

    public static float angleWithXAxis(Vector2f v) {
        return (float) Math.atan2(v.y, v.x);
    }

    public static Vector2f clampLength(Vector2f v, float minLength, float maxLength) {
        float length = v.length();
        if (length < minLength) {
            return v.normalize().multiply(minLength);
        } else if (length > maxLength) {
            return v.normalize().multiply(maxLength);
        }
        return v;
    }

    // Vector2i
    public static int distance(Vector2i v1, Vector2i v2) {
        int dx = v1.x - v2.x;
        int dy = v1.y - v2.y;
        return (int) Math.sqrt(dx * dx + dy * dy);
    }

    public static int distanceSquared(Vector2i v1, Vector2i v2) {
        int dx = v1.x - v2.x;
        int dy = v1.y - v2.y;
        return dx * dx + dy * dy;
    }

    public static float angle(Vector2i v1, Vector2i v2) {
        int dotProduct = Vector2i.dot(v1, v2);
        float length1 = v1.length();
        float length2 = v2.length();
        return (float) Math.acos(dotProduct / (length1 * length2));
    }

    public static int cross(Vector2i v1, Vector2i v2) {
        return v1.x * v2.y - v1.y * v2.x;
    }

    public static int dot(Vector2i v1, Vector2i v2) {
        return Vector2i.dot(v1, v2);
    }

    public static float angleWithXAxis(Vector2i v) {
        return (float) Math.atan2(v.y, v.x);
    }

    public static Vector2i clampLength(Vector2i v, int minLength, int maxLength) {
        float length = v.length();
        if (length < minLength) {
            return v.normalize().multiply(minLength);
        } else if (length > maxLength) {
            return v.normalize().multiply(maxLength);
        }
        return v;
    }

    // Vector3f
    public static float distance(Vector3f v1, Vector3f v2) {
        float dx = v1.x - v2.x;
        float dy = v1.y - v2.y;
        float dz = v1.z - v2.z;
        return (float) Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    public static float distanceSquared(Vector3f v1, Vector3f v2) {
        float dx = v1.x - v2.x;
        float dy = v1.y - v2.y;
        float dz = v1.z - v2.z;
        return dx * dx + dy * dy + dz * dz;
    }

    public static float angle(Vector3f v1, Vector3f v2) {
        float dotProduct = Vector3f.dot(v1, v2);
        float length1 = v1.length();
        float length2 = v2.length();
        return (float) Math.acos(dotProduct / (length1 * length2));
    }

    public static Vector3f cross(Vector3f v1, Vector3f v2) {
        return Vector3f.cross(v1, v2);
    }

    public static float dot(Vector3f v1, Vector3f v2) {
        return Vector3f.dot(v1, v2);
    }

    public static float angleWithXAxis(Vector3f v) {
        return (float) Math.atan2(v.y, v.x);
    }

    public static Vector3f clampLength(Vector3f v, float minLength, float maxLength) {
        float length = v.length();
        if (length < minLength) {
            return v.normalize().multiply(minLength);
        } else if (length > maxLength) {
            return v.normalize().multiply(maxLength);
        }
        return v;
    }

    public static Vector3f project(Vector3f v1, Vector3f v2) {
        float dotProduct = Vector3f.dot(v1, v2);
        float lengthSquared = v2.x * v2.x + v2.y * v2.y + v2.z * v2.z;
        float scalar = dotProduct / lengthSquared;
        return new Vector3f(v2.x * scalar, v2.y * scalar, v2.z * scalar);
    }

    public static Vector3f reflect(Vector3f v, Vector3f normal) {
        return v.subtract(normal.multiply(2 * Vector3f.dot(v, normal)));
    }

    // Vector3i
    public static int distance(Vector3i v1, Vector3i v2) {
        int dx = v1.x - v2.x;
        int dy = v1.y - v2.y;
        int dz = v1.z - v2.z;
        return (int) Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    public static int distanceSquared(Vector3i v1, Vector3i v2) {
        int dx = v1.x - v2.x;
        int dy = v1.y - v2.y;
        int dz = v1.z - v2.z;
        return dx * dx + dy * dy + dz * dz;
    }

    public static float angle(Vector3i v1, Vector3i v2) {
        int dotProduct = Vector3i.dot(v1, v2);
        float length1 = v1.length();
        float length2 = v2.length();
        return (float) Math.acos(dotProduct / (length1 * length2));
    }

    public static Vector3i cross(Vector3i v1, Vector3i v2) {
        return Vector3i.cross(v1, v2);
    }

    public static int dot(Vector3i v1, Vector3i v2) {
        return Vector3i.dot(v1, v2);
    }

    public static Vector3i normalize(Vector3i v) {
        int length = v.length();
        if (length != 0) {
            return v.divide(length);
        }
        return v;
    }

    public static float angleWithXAxis(Vector3i v) {
        return (float) Math.atan2(v.y, v.x);
    }

    public static Vector3i clampLength(Vector3i v, int minLength, int maxLength) {
        int length = v.length();
        if (length < minLength) {
            return v.normalize().multiply(minLength);
        } else if (length > maxLength) {
            return v.normalize().multiply(maxLength);
        }
        return v;
    }

    public static Vector3i project(Vector3i v1, Vector3i v2) {
        int dotProduct = Vector3i.dot(v1, v2);
        int lengthSquared = v2.x * v2.x + v2.y * v2.y + v2.z * v2.z;
        int scalar = dotProduct / lengthSquared;
        return new Vector3i(v2.x * scalar, v2.y * scalar, v2.z * scalar);
    }

    public static Vector3i reflect(Vector3i v, Vector3i normal) {
        int dotProduct = Vector3i.dot(v, normal);
        return v.subtract(normal.multiply(2 * dotProduct));
    }

    // Vector4f
    public static float distance(Vector4f v1, Vector4f v2) {
        float dx = v1.x - v2.x;
        float dy = v1.y - v2.y;
        float dz = v1.z - v2.z;
        float dw = v1.w - v2.w;
        return (float) Math.sqrt(dx * dx + dy * dy + dz * dz + dw * dw);
    }

    public static float distanceSquared(Vector4f v1, Vector4f v2) {
        float dx = v1.x - v2.x;
        float dy = v1.y - v2.y;
        float dz = v1.z - v2.z;
        float dw = v1.w - v2.w;
        return dx * dx + dy * dy + dz * dz + dw * dw;
    }

    public static float angle(Vector4f v1, Vector4f v2) {
        float dotProduct = Vector4f.dot(v1, v2);
        float length1 = v1.length();
        float length2 = v2.length();
        return (float) Math.acos(dotProduct / (length1 * length2));
    }

    public static float dot(Vector4f v1, Vector4f v2) {
        return Vector4f.dot(v1, v2);
    }

    public static Vector4f normalize(Vector4f v) {
        float length = v.length();
        if (length != 0) {
            return v.divide(length);
        }
        return v;
    }

    public static Vector4f clampLength(Vector4f v, float minLength, float maxLength) {
        float length = v.length();
        if (length < minLength) {
            return v.normalize().multiply(minLength);
        } else if (length > maxLength) {
            return v.normalize().multiply(maxLength);
        }
        return v;
    }

    public static Vector4f project(Vector4f v1, Vector4f v2) {
        float dotProduct = Vector4f.dot(v1, v2);
        float lengthSquared = v2.x * v2.x + v2.y * v2.y + v2.z * v2.z + v2.w * v2.w;
        float scalar = dotProduct / lengthSquared;
        return new Vector4f(v2.x * scalar, v2.y * scalar, v2.z * scalar, v2.w * scalar);
    }

    public static Vector4f reflect(Vector4f v, Vector4f normal) {
        float dotProduct = Vector4f.dot(v, normal);
        return v.subtract(normal.multiply(2 * dotProduct));
    }

    // Vector4i
    public static int distance(Vector4i v1, Vector4i v2) {
        int dx = v1.x - v2.x;
        int dy = v1.y - v2.y;
        int dz = v1.z - v2.z;
        int dw = v1.w - v2.w;
        return (int) Math.sqrt(dx * dx + dy * dy + dz * dz + dw * dw);
    }

    public static int distanceSquared(Vector4i v1, Vector4i v2) {
        int dx = v1.x - v2.x;
        int dy = v1.y - v2.y;
        int dz = v1.z - v2.z;
        int dw = v1.w - v2.w;
        return dx * dx + dy * dy + dz * dz + dw * dw;
    }

    public static float angle(Vector4i v1, Vector4i v2) {
        int dotProduct = Vector4i.dot(v1, v2);
        float length1 = v1.length();
        float length2 = v2.length();
        return (float) Math.acos(dotProduct / (length1 * length2));
    }

    public static int dot(Vector4i v1, Vector4i v2) {
        return Vector4i.dot(v1, v2);
    }

    public static Vector4i normalize(Vector4i v) {
        int length = v.length();
        if (length != 0) {
            return v.divide(length);
        }
        return v;
    }

    public static Vector4i clampLength(Vector4i v, int minLength, int maxLength) {
        int length = v.length();
        if (length < minLength) {
            return v.normalize().multiply(minLength);
        } else if (length > maxLength) {
            return v.normalize().multiply(maxLength);
        }
        return v;
    }

    public static Vector4i project(Vector4i v1, Vector4i v2) {
        int dotProduct = Vector4i.dot(v1, v2);
        int lengthSquared = v2.x * v2.x + v2.y * v2.y + v2.z * v2.z + v2.w * v2.w;
        int scalar = dotProduct / lengthSquared;
        return new Vector4i(v2.x * scalar, v2.y * scalar, v2.z * scalar, v2.w * scalar);
    }

    public static Vector4i reflect(Vector4i v, Vector4i normal) {
        int dotProduct = Vector4i.dot(v, normal);
        return v.subtract(normal.multiply(2 * dotProduct));
    }
}
