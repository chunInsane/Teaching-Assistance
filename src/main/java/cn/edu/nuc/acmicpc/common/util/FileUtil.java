package cn.edu.nuc.acmicpc.common.util;

import cn.edu.nuc.acmicpc.common.exception.AppException;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/8
 * File util
 */
public class FileUtil {

    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    /** buffer size*/
    private static final Integer BUFFER_SIZE = 1024;

    /**
     * Save string content into the specific file
     * @param content
     * @param filePath
     */
    public static void saveToFile(String content, String filePath) {
        try {
            OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filePath));
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
            bufferedOutputStream.write(content.getBytes());
            outputStream.close();
        } catch (IOException e) {
            throw new AppException(e);
        }
    }

    /**
     * Save inputStream's content into outputStream
     * @param inputStream
     * @param outputStream
     */
    public static void saveToFile(InputStream inputStream, OutputStream outputStream) {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, BUFFER_SIZE);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream, BUFFER_SIZE);
        byte[] buffer = new byte[BUFFER_SIZE];
        int len;
        try {
            while ((len = bufferedInputStream.read(buffer)) != -1) {
                bufferedOutputStream.write(buffer, 0, len);
            }
            bufferedOutputStream.flush();
            bufferedInputStream.close();
            bufferedOutputStream.close();
        } catch (IOException e) {
            throw new AppException(e);
        }
    }

    /**
     * Move source directory into destination directory
     * @param srcDir
     * @param destDir
     */
    public static void moveDirectory(File srcDir, File destDir) {
        try {
            org.aspectj.util.FileUtil.copyDir(srcDir, destDir);
        } catch (IOException e) {
            throw new AppException(e);
        }
        clearDirectory(srcDir);
    }

    /**
     * Count number of files in the folder
     * @param file
     * @return
     */
    public static int countFiles(File file) {
        if (!file.exists()) {
            return 0;
        }
        File[] files = file.listFiles();
        return files == null ? 0 : files.length;
    }

    /**
     * Delete specific directory
     * @param file
     */
    public static void clearDirectory(File file) {
        if (file.exists()) {
            deleteContents(file);
            file.delete();
        }
    }

    /**
     * Delete specific directory by file name.
     * @param filename
     */
    public static void clearDirectory(String filename) {
        File file = new File(Preconditions.checkNotNull(filename));
        clearDirectory(file);
    }

    /**
     * Delete the contents, but not contain itself
     * @param targetFile
     * @return
     */
    public static int deleteContents(File targetFile) {
        if ((targetFile.exists()) && targetFile.isDirectory()) {
            return org.aspectj.util.FileUtil.deleteContents(targetFile);
        } else {
            return 0;
        }
    }

    /**
     * Create directory if not exists
     * @param path
     */
    public static void createDirectoryIfNotExists(String path) {
       File dir = new File(path);
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                throw new AppException("can not create directory");
            }
        }
    }


    /**
     * get file's name without file's extension
     * @param file
     * @return
     */
    public static String getFileName(File file) {
        String filename = file.getName();
        //remove extension
        int index = filename.indexOf(".");
        if (index == -1) {
            return filename;
        } else {
            return filename.substring(0, index);
        }
    }
}
