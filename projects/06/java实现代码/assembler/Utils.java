package com.learn.assembler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author jiaolong
 * @date 2023/12/13 11:33
 */
public class Utils {


    /**
     * @author yingfeng
     * @date 2023-12-13 12:00
     * 判断字符串是否是数字
     */
    public static boolean isNumber(String str) {
        return str != null && str.chars().allMatch(Character::isDigit);
    }

    /**
     *
     * @author yingfeng
     * @date 2023-12-14 16:17
      将字符串数字转换位16位的二进制
     */
    public static String toBinary(String address) {
        String binaryString = Integer.toBinaryString(Integer.valueOf(address));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < (16 - binaryString.length()); i++) {
            sb.append("0");
        }
        sb.append(binaryString);
        return sb.toString();
    }


    public static List<String> readAsmFile(Path p) {
        List<String> asmInfo = null;
        try {
            asmInfo = Files.readAllLines(p);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return asmInfo;
    }

    public static void outputFile(Path p, ArrayList<String> binaryProgram) {
        String fileName = p.getFileName().toString();
        String outFileName = fileName.substring(0, fileName.length()-4)+".hack";
        Path outPath = Paths.get(p.getParent().toString(), outFileName );

        try {
            Files.write(outPath, binaryProgram);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String getCommand(String info){
        //获取代码，去除注解
        String command = info.trim();
        if(info.contains("//")){
            command = info.substring(0,info.indexOf("//")).trim();
        }
        return command;
    }

    public static List<String> getFiles(Path sourcePath)  {
        List<String> files = new ArrayList<>();

        try {
            Files.list(sourcePath).forEach(x-> {
                Path pc = Paths.get(x.toString());
                try {
                    Files.list(pc).forEach(y -> {files.add(y.toString());});
                } catch (IOException e) {
                    e.printStackTrace();
                }

            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return files;
    }

}
