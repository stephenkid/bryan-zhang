package org.poseidon.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtil {
	static final int BUFFER = 2048;

	public static void zipFile(File inFile, File outFile) throws IOException{
		ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(outFile)));
		ZipEntry ze = new ZipEntry(inFile.getName());
		zos.putNextEntry(ze);

		byte[] buf = new byte[BUFFER];
		int readLen = 0;
		InputStream is = new BufferedInputStream(new FileInputStream(inFile));
		while ((readLen = is.read(buf, 0, BUFFER)) != -1) {
			zos.write(buf, 0, readLen);
		}
		is.close();

		zos.close();
	}
}
