package cn.edu.nuc.acmicpc.common.check;

import cn.edu.nuc.acmicpc.common.exception.AppException;
import cn.edu.nuc.acmicpc.common.util.FileUtil;
import com.google.common.base.Preconditions;

import java.io.File;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/3
 */
public class ZipDataChecker implements Checker<File> {

    @Override
    public void check(File file) {
        Preconditions.checkNotNull(file);
        File[] files = file.listFiles();
        if (files == null) {
            throw new AppException("Data file is invalid.");
        }

        Set<String> fileSet = new HashSet<>();
        List<String> outputFileList = new LinkedList<>();
        for (File current : files) {
            if (current.isDirectory()) {
                throw new AppException("Problem information contains sub-directory.");
            }
            if (current.getName().endsWith(".in")) {
                fileSet.add(FileUtil.getFileName(current));
            } else if (current.getName().endsWith(".out")) {
                outputFileList.add(FileUtil.getFileName(current));
            } else if (current.getName().equals("spj.cc")) {
                // spj checker, ignored
            } else if (current.getName().equals("problemInfo.xml")) {
                // problem info, ignored by default
            } else {
                throw new AppException("Problem information directory contains unknown file type.");
            }
        }

        if (outputFileList.size() != fileSet.size()) {
            throw new AppException("Some data files has not input file or output file.");
        }

        if (fileSet.size() == 0) {
            throw new AppException("No test data.");
        }

        for (String outputFile : outputFileList) {
            if (!fileSet.contains(outputFile)) {
                throw new AppException("Some data files has not input file or output file.");
            }
        }
    }
}
