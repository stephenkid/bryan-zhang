package org.poseidon.util.back;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.apache.log4j.Logger;
import org.poseidon.util.LoggerUtil;

/**
 * ZipUtil
 * 
 * @author 何晓滨
 * @version 创建时间：Sep 1, 2008 3:00:29 PM
 */
public abstract class ZipUtil {

    private static final int BUFFER_SIZE = 4096;
    private static final Logger LOGGER = Logger.getLogger(ZipUtil.class);
	static{
		LoggerUtil.initLogging(LOGGER);
	}
	
    /**
     * 将数据进行zip压缩后再写进输出流
     * @param os
     *            输出流
     * @param entryName
     *            条目名（数据的名字）
     * @param entryData
     *            原始数据
     * @throws IOException
     */
    public static void writeSimpleZipStream(OutputStream os, String entryName, byte[] entryData) throws IOException {
        ZipOutputStream zos = null;
        try {
            zos = new ZipOutputStream(os);
            zos.putNextEntry(new ZipEntry(entryName));
            zos.write(entryData);
            zos.closeEntry();
        } finally {
            if (zos != null) {
                try {
                    zos.close();
                } catch (IOException e) {
                	LOGGER.error(e.getMessage(), e);
                }
            }
        }
    }

    /**
     * 从输入流中读出数据并进行unzip操作得到原始数据
     * @param is
     *            输入流
     * @param entryName
     *            条目名（数据的名字）
     * @return 原始数据
     * @throws IOException
     */
    public static byte[] readSimpleZipStream(InputStream is, String entryName) throws IOException {
        byte[] entryData = null;
        ZipInputStream zis = null;
        try {
            zis = new ZipInputStream(is);
            ZipEntry ze;
            while ((ze = zis.getNextEntry()) != null) {
                if (ze.getName().equals(entryName)) {
                    ByteArrayOutputStream bao = new ByteArrayOutputStream(BUFFER_SIZE);
                    byte[] buffer = new byte[BUFFER_SIZE];
                    int len = 0;
                    while ((len = zis.read(buffer, 0, buffer.length)) > 0) {
                        bao.write(buffer, 0, len);
                    }
                    entryData = bao.toByteArray();
                    break;
                }
                zis.closeEntry();
            }
        } finally {
            if (zis != null) {
                try {
                    zis.close();
                } catch (IOException e) {
                	LOGGER.error(e.getMessage(), e);
                }
            }
        }
        return entryData;
    }

    /**
     * 将数据进行zip压缩后再写进文件
     * @param file
     * @param entryName
     * @param entryData
     * @throws IOException
     */
    public static void writeSimpleZipStream(File file, String entryName, byte[] entryData) throws IOException {
        FileOutputStream os = null;
        try {
            os = new FileOutputStream(file);
            writeSimpleZipStream(os, entryName, entryData);
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                	LOGGER.error(e.getMessage(), e);
                }
            }
        }
    }

}
