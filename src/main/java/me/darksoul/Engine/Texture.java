package me.darksoul.Engine;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;

import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.file.Paths;

public class Texture {
    private final int textureID;
    private final int width;
    private final int height;

    public Texture(URI filepath) throws Exception {
        textureID = GL11.glGenTextures();
        String path = Paths.get(filepath).toString();
        bind();

        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer widthBuffer = stack.mallocInt(1);
            IntBuffer heightBuffer = stack.mallocInt(1);
            IntBuffer channelsBuffer = stack.mallocInt(1);

            ByteBuffer imageData = STBImage.stbi_load(path, widthBuffer, heightBuffer, channelsBuffer, 4);
            if (imageData == null) {
                ErrorLogger.error("Texture", "Failed to load texture file: " + filepath);
                throw new RuntimeException("Failed to load texture file: " + filepath);
            }

            width = widthBuffer.get();
            height = heightBuffer.get();

            GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, width, height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, imageData);
            STBImage.stbi_image_free(imageData);

            setTextureParameters();
        }
    }

    private void setTextureParameters() {
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
    }

    public static void unbind() {
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
    }

    public void bind() {
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
    }

    public void bind(int textureUnit) {
        GL13.glActiveTexture(GL13.GL_TEXTURE0 + textureUnit);
        bind();
    }

    public void cleanup() {
        GL11.glDeleteTextures(textureID);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getID() {
        return textureID;
    }
}
