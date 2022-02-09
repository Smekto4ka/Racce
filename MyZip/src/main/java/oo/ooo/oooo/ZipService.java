package oo.ooo.oooo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipService {
    public byte[] zip(byte[] data) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream);
        try {
            ZipEntry zipEntry = new ZipEntry("name");
            zipOutputStream.putNextEntry(zipEntry);
            zipOutputStream.write(data);
            zipOutputStream.closeEntry();
        } finally {
            zipOutputStream.close();
        }
        return byteArrayOutputStream.toByteArray();
    }

    public byte[] unZip(byte[] data, int size) throws IOException {
        ZipInputStream zipInputStream = new ZipInputStream(new ByteArrayInputStream(data));
        ZipEntry zipEntry = zipInputStream.getNextEntry();
        String entryName = zipEntry.getName();
        System.out.println(entryName);
        byte[] bytes = new byte[size];
        FileOutputStream out = new FileOutputStream(entryName);

        int bytesRead = 0;
        while ((bytesRead = zipInputStream.read(bytes)) != -1) {
            out.write(bytes, 0, bytesRead);
        }
        out.close();
        zipInputStream.closeEntry();
        zipInputStream.close();
        return bytes;
    }
}
