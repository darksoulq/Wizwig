package me.darksoul.Engine;

import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.lwjgl.openal.ALC;
import org.lwjgl.openal.ALCCapabilities;
import org.lwjgl.stb.STBVorbis;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.openal.ALC10.*;

public class Sound {

    private final Map<Integer, Integer> soundBuffers = new HashMap<>();
    private final boolean isSpatial;
    private final float[] position = new float[3];
    private long device;
    private long context;
    private int source;

    public Sound(String filename, boolean isSpatial, float x, float y, float z) {
        this.isSpatial = isSpatial;
        this.position[0] = x;
        this.position[1] = y;
        this.position[2] = z;

        initializeOpenAL();
        createSource();

        int buffer = loadSoundFile(filename);
        soundBuffers.put(buffer, buffer);

        AL10.alSourcei(source, AL10.AL_BUFFER, buffer);

        if (isSpatial) {
            AL10.alSource3f(source, AL10.AL_POSITION, position[0], position[1], position[2]);
        }
    }

    private void initializeOpenAL() {
        device = alcOpenDevice((ByteBuffer) null);
        if (device == MemoryUtil.NULL) {
            ErrorLogger.error("Sound", "Failed to open the audio device.");
            throw new RuntimeException("Failed to open the audio device.");
        }

        ALCCapabilities deviceCaps = ALC.createCapabilities(device);
        context = alcCreateContext(device, (IntBuffer) null);
        alcMakeContextCurrent(context);
        AL.createCapabilities(deviceCaps);
    }

    private void createSource() {
        source = AL10.alGenSources();
        if (source == AL10.AL_INVALID_VALUE) {
            ErrorLogger.error("Sound", "Failed to create an OpenAL source.");
            throw new RuntimeException("Failed to create an OpenAL source.");
        }

        AL10.alSourcei(source, AL10.AL_SOURCE_RELATIVE, isSpatial ? AL10.AL_FALSE : AL10.AL_TRUE);
    }

    private int loadSoundFile(String filename) {
        int buffer = AL10.alGenBuffers();

        try {
            if (filename.endsWith(".ogg")) {
                loadOggFile(filename, buffer);
            } else if (filename.endsWith(".wav")) {
                loadWavFile(filename, buffer);
            } else {
                ErrorLogger.error("Sound", "Unsupported file type: " + filename);
                throw new UnsupportedOperationException("Unsupported file type: " + filename);
            }
        } catch (IOException | UnsupportedAudioFileException e) {
            ErrorLogger.error("Sound", "Failed to load sound file: " + filename);
            throw new RuntimeException("Failed to load sound file: " + filename, e);
        }

        return buffer;
    }

    private void loadOggFile(String filename, int buffer) throws IOException {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            ByteBuffer byteBuffer = loadResource(filename);

            IntBuffer channels = stack.mallocInt(1);
            IntBuffer sampleRate = stack.mallocInt(1);
            ShortBuffer samples = STBVorbis.stb_vorbis_decode_memory(byteBuffer, channels, sampleRate);

            if (samples == null) {
                ErrorLogger.error("Sound", "Failed to load OGG file: " + filename);
                throw new RuntimeException("Failed to load OGG file.");
            }

            int format = channels.get(0) == 1 ? AL10.AL_FORMAT_MONO16 : AL10.AL_FORMAT_STEREO16;

            AL10.alBufferData(buffer, format, samples, sampleRate.get(0));
        }
    }

    private void loadWavFile(String filename, int buffer) throws IOException, UnsupportedAudioFileException {
        File file = new File(filename);
        try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file)) {
            AudioFormat format = audioInputStream.getFormat();

            byte[] audioBytes = audioInputStream.readAllBytes();

            int openALFormat = (format.getChannels() == 1) ? AL10.AL_FORMAT_MONO16 : AL10.AL_FORMAT_STEREO16;

            ByteBuffer audioBuffer = ByteBuffer.allocateDirect(audioBytes.length);
            audioBuffer.put(audioBytes);
            audioBuffer.flip();

            AL10.alBufferData(buffer, openALFormat, audioBuffer, (int) format.getSampleRate());
        }
    }

    private ByteBuffer loadResource(String filename) {
        Path path = Paths.get(filename);
        try {
            byte[] fileData = Files.readAllBytes(path);
            ByteBuffer byteBuffer = MemoryUtil.memAlloc(fileData.length);
            byteBuffer.put(fileData);
            byteBuffer.flip();
            return byteBuffer;
        } catch (IOException e) {
            ErrorLogger.error("Sound", "Failed to load sound resource: " + filename);
            throw new RuntimeException("Failed to load sound file: " + filename, e);
        }
    }

    public void play() {
        AL10.alSourcePlay(source);
    }

    public void pause() {
        AL10.alSourcePause(source);
    }

    public void resume() {
        AL10.alSourcePlay(source);
    }

    public void stop() {
        AL10.alSourceStop(source);
    }

    public void setVolume(float volume) {
        AL10.alSourcef(source, AL10.AL_GAIN, volume);
    }

    public void setLooping(boolean loop) {
        AL10.alSourcei(source, AL10.AL_LOOPING, loop ? AL10.AL_TRUE : AL10.AL_FALSE);
    }

    public void setPitch(float pitch) {
        AL10.alSourcef(source, AL10.AL_PITCH, pitch);
    }

    public void setPosition(float x, float y, float z) {
        if (isSpatial) {
            AL10.alSource3f(source, AL10.AL_POSITION, x, y, z);
        }
    }

    public void cleanup() {
        AL10.alDeleteSources(source);
        soundBuffers.forEach((key, value) -> AL10.alDeleteBuffers(value));
        alcDestroyContext(context);
        alcCloseDevice(device);
    }
}
