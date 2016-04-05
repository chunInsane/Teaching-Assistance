package cn.edu.nuc.acmicpc.common.util;

import cn.edu.nuc.acmicpc.common.check.Checker;
import cn.edu.nuc.acmicpc.common.exception.AppException;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/3
 */
public class ZipUtil {

    /**
     * Unzip the zip file and put all the files in the zip file to the path.
     */
    public static void unzipFile(ZipFile zipFile, String path, Checker<File> checker) {
        try {
            Enumeration<?> enumeration = zipFile.entries();
            while (enumeration.hasMoreElements()) {
                ZipEntry zipEntry = (ZipEntry) enumeration.nextElement();
                if (zipEntry.isDirectory()) {
                    if (!new File(path + "/" + zipEntry.getName()).mkdirs()) {
                        throw new Exception();
                    }
                    continue;
                }
                File file = new File(path + "/" + zipEntry.getName());
                File parent = file.getParentFile();
                if (parent != null && !parent.exists()) {
                    if (!parent.mkdirs()) {
                        throw new AppException();
                    }
                }
                FileUtil.saveToFile(zipFile.getInputStream(zipEntry), new FileOutputStream(file));
            }
            zipFile.close();
            File targetFile = new File(path);
            try {
                checker.check(targetFile);
            } catch (AppException e) {
                FileUtil.clearDirectory(targetFile.getAbsolutePath());
                throw e;
            }
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException("Unzip zip file error.");
        }
    }
}
