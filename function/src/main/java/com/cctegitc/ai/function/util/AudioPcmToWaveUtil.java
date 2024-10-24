package com.cctegitc.ai.function.util;

import javazoom.spi.mpeg.sampled.file.MpegAudioFileReader;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * @author ShiGuangWei
 * @ClassName AudioPcmToWaveUtil
 * @Description pcm格式音频文件转wav格式
 * @date 2022-09-27
 */
@Slf4j
public class AudioPcmToWaveUtil {
    private static final Logger logger = LoggerFactory.getLogger(AudioPcmToWaveUtil.class);

    // 采样频率
    private static final long SAMPLE_RATE = 8000L;

    public static AudioFormat targetFormat = null;

    // 声道数
    private static final int CHANNELS = 1;
    private static final int BYTE_SIZE = 1024;
    private static final int WAVE_HEADER_LENGTH = 44;

    public static Boolean audioPcmToWave(String pcmFilePath, String waveFilePath) {
        if (pcmFilePath == null || pcmFilePath.isEmpty() || waveFilePath == null) {
            log.debug("pcmFilePath or waveFilePath is null");
            return false;
        }
        log.error("pcm转wav格式开始！");
        long startTime = System.currentTimeMillis();
        log.error("AudioPcmToWaveUtil.audioPcmToWave pcmFilePath={}", pcmFilePath);
        long totalAudioLen;
        long totalDataLen;

        long byteRate = 16 * SAMPLE_RATE * CHANNELS / 8;
        byte[] data = new byte[BYTE_SIZE];

        // 使用try-with-resources自动管理资源
        try (FileInputStream inStream = new FileInputStream(pcmFilePath);
             FileOutputStream outStream = new FileOutputStream(waveFilePath)) {
            totalAudioLen = inStream.getChannel().size();
            totalDataLen = totalAudioLen + 36;
            byte[] waveFileHeader = getWaveFileHeader(totalAudioLen, totalDataLen, SAMPLE_RATE, CHANNELS, byteRate);
            outStream.write(waveFileHeader);
            while (inStream.read(data) != -1) {
                outStream.write(data);
            }
        } catch (FileNotFoundException e) {
            log.error("pcm格式转wav格式FileNotFoundException异常，异常信息：{}", e.getMessage(), e);
            return false;
        } catch (IOException e) {
            log.error("pcm格式转wav格式IOException异常，异常信息：{}", e.getMessage(), e);
            return false;
        }
        long endTime = System.currentTimeMillis();
        log.error("AudioPcmToWaveUtil.audioPcmToWave is end costime={}", endTime - startTime);
        return true;
    }

    /**
     * mp3转pcm(8k 16bit)
     *
     * @param mp3filepath
     * @param pcmfilepath
     * @return
     */
    public static boolean Mp3ToPcm(String mp3filepath, String pcmfilepath) {
        try {
            log.error("AudioPcmToWaveUtil.Mp3ToPcm mp3filepath=", mp3filepath);
            log.error("AudioPcmToWaveUtil.Mp3ToPcm pcmfilepath=", pcmfilepath);
            //获取文件的音频流，pcm的格式
            AudioInputStream audioInputStream = getPcmAudioInputStream(mp3filepath);
            //将音频转化为  pcm的格式保存下来
            AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, new File(pcmfilepath));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取MP3音频流
     *
     * @param mp3filepath
     * @return
     */
    private static AudioInputStream getPcmAudioInputStream(String mp3filepath) {
        File mp3 = new File(mp3filepath);
        AudioInputStream audioInputStream = null;

        try {
            AudioInputStream in = null;
            //读取音频文件的类
            MpegAudioFileReader mp = new MpegAudioFileReader();
            in = mp.getAudioInputStream(mp3);
            AudioFormat baseFormat = in.getFormat();
            //设定输出格式为pcm格式的音频文件
            targetFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(), 16,
                    baseFormat.getChannels(), baseFormat.getChannels() * 16 / 8, baseFormat.getFrameRate(), false);
            //输出到音频
            audioInputStream = AudioSystem.getAudioInputStream(targetFormat, in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return audioInputStream;
    }

    /**
     * pcm(8k 16bit)转wav(16k 16bit)
     *
     * @param pcmfilepath
     * @param wavfilepath
     * @throws IOException
     */
    public static void pcmToWav(String pcmfilepath, String wavfilepath) throws IOException {
        FileInputStream fis = new FileInputStream(pcmfilepath);
        byte channels = (byte) targetFormat.getChannels();
        int sampleRate = (int) targetFormat.getSampleRate();
        int byteRate = targetFormat.getFrameSize() * sampleRate * channels / 8;
        int datalen = (int) fis.getChannel().size();
        ByteBuffer bb = ByteBuffer.allocate(44);
        bb.order(ByteOrder.LITTLE_ENDIAN);
        bb.put(new byte[]{'R', 'I', 'F', 'F'});//RIFF标记
        bb.putInt(datalen + 44 - 8);//原始数据长度（不包含RIFF和本字段共8个字节）
        bb.put(new byte[]{'W', 'A', 'V', 'E'});//WAVE标记
        bb.put(new byte[]{'f', 'm', 't', ' '});//fmt标记
        bb.putInt(16);//“fmt”字段的长度，存储该子块的字节数（不含前面的Subchunk1ID和Subchunk1Size这8个字节）
        bb.putShort((short) 1);//存储音频文件的编码格式，PCM其存储值为1
        bb.putShort((short) targetFormat.getChannels());//通道数，单通道(Mono)值为1，双通道(Stereo)值为2
        //采样率
        bb.putInt(sampleRate);
        //音频数据传送速率,采样率*通道数*采样深度/8。(每秒存储的bit数，其值=SampleRate * NumChannels * BitsPerSample/8)
        bb.putInt(byteRate);
        //块对齐/帧大小，NumChannels * BitsPerSample/8
        bb.putShort((short) (targetFormat.getChannels() * 16 / 8));
        //pcm数据位数，一般为8,16,32等
        bb.putShort((short) 16);
        bb.put(new byte[]{'d', 'a', 't', 'a'});//data标记
        bb.putInt(datalen);//data数据长度
        byte[] header = bb.array();
        //wav头
//        for(int i=0;i<header.length;i++) {
//            System.out.printf("%02x ",header[i]);
//        }
        ByteBuffer wavbuff = ByteBuffer.allocate(44 + datalen);
        wavbuff.put(header);
        byte[] temp = new byte[datalen];
        fis.read(temp);
        wavbuff.put(temp);
        byte[] wavbytes = wavbuff.array();
        FileOutputStream fos = new FileOutputStream(wavfilepath);
        fos.write(wavbytes);
        fos.flush();
        fos.close();
        fis.close();
        System.out.println("end");
    }

    /**
     * 生成头部
     *
     * @param totalAudioLen 文件语音数据大小
     * @param totalDateLen  总文件大小
     * @param sampleRate    采样率
     * @param channels      声道数
     * @param byteRate      播放频率，数据缓存区大小
     * @return
     */
    private static byte[] getWaveFileHeader(long totalAudioLen, long totalDateLen, long sampleRate, int channels,
                                            long byteRate) {
        byte[] header = new byte[WAVE_HEADER_LENGTH];
        // RIFF/WAVE header
        header[0] = 'R';
        header[1] = 'I';
        header[2] = 'F';
        header[3] = 'F';

        header[4] = (byte) (totalDateLen & 0xff);
        header[5] = (byte) ((totalDateLen >> 8) & 0xff);
        header[6] = (byte) ((totalDateLen >> 16) & 0xff);
        header[7] = (byte) ((totalDateLen >> 24) & 0xff);

        header[8] = 'W';
        header[9] = 'A';
        header[10] = 'V';
        header[11] = 'E';
        // 'fmt' chunk
        header[12] = 'f';
        header[13] = 'm';
        header[14] = 't';
        header[15] = ' ';
        // 4bytes: size of 'fmt ' chunk
        header[16] = 16;
        header[17] = 0;
        header[18] = 0;
        header[19] = 0;
        // format = 1
        header[20] = 1;
        header[21] = 0;
        header[22] = (byte) channels;
        header[23] = 0;

        header[24] = (byte) (sampleRate & 0xff);
        header[25] = (byte) ((sampleRate >> 8) & 0xff);
        header[26] = (byte) ((sampleRate >> 16) & 0xff);
        header[27] = (byte) ((sampleRate >> 24) & 0xff);

        header[28] = (byte) (byteRate & 0xff);
        header[29] = (byte) ((byteRate >> 8) & 0xff);
        header[30] = (byte) ((byteRate >> 16) & 0xff);
        header[31] = (byte) ((byteRate >> 24) & 0xff);
        // block align
        header[32] = (byte) (2 * 16 / 8);
        header[33] = 0;
        header[34] = 16;
        header[35] = 0;
        // data
        header[36] = 'd';
        header[37] = 'a';
        header[38] = 't';
        header[39] = 'a';
        header[40] = (byte) (totalAudioLen & 0xff);
        header[41] = (byte) ((totalAudioLen >> 8) & 0xff);
        header[42] = (byte) ((totalAudioLen >> 16) & 0xff);
        header[43] = (byte) ((totalAudioLen >> 24) & 0xff);
        return header;
    }
}
