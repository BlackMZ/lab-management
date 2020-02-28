package com.cloud.lab.management.util;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Decoder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;

/**
 * @Author: John.ma
 * @Description: 文件处理
 * @Date: 2019/11/29 16:16
 */
@Slf4j
public class FileUtil {


    /**
     * base64转图片
     *
     * @param base64Code base64码
     */
//    public static String convertBase64ToImage(String base64Code, String imgpath, String frontOrBack, String randomCode) {
//        String name = "";
//        String fileName;
//        try {
//            if (Objects.equals("0", frontOrBack)) {
//                fileName = randomCode + "-b-0.tif";
//            } else {
//                fileName = randomCode + "-a-0.tif";
//            }
//            byte[] imageByte = DatatypeConverter.parseBase64Binary(base64Code);
//            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
//            BufferedImage image = ImageIO.read(bis);
//            bis.close();
//            File file = new File(imgpath + "\\" + fileName);
////            if (!file.exists()) {
////                file.mkdirs();
////            }
//            boolean tif = ImageIO.write(image, "tif", file);
//            System.out.println(tif);
//            name = file.getName();
//        } catch (IOException e) {
//            log.error("base64转图片错误：" + e.getMessage());
//        }
//        return name;
//    }

    public static String convertBase64ToImage(String base64Code, String imgpath, String frontOrBack, String randomCode) {
        //对字节数组字符串进行Base64解码并生成图片
        if (base64Code == null) {
            return "";
        }
        String fileName;
        if (Objects.equals("0", frontOrBack)) {
            fileName = randomCode + "-b-0.png";
        } else {
            fileName = randomCode + "-a-0.png";
        }
        imgpath = imgpath + "\\" + fileName;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            //Base64解码
            byte[] b = decoder.decodeBuffer(base64Code);
            for (int i = 0; i < b.length; ++i) {
                //调整异常数据
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            //生成图片
            OutputStream out = new FileOutputStream(imgpath);
            out.write(b);
            out.flush();
            out.close();
        } catch (Exception e) {
            log.error("base64转图片错误：" + e.getMessage());
        }
        return fileName;
    }

    /**
     * 读取文件内容
     *
     * @param filePath 文件路径
     * @return 文件内容
     */
    public static String txt2String(String filePath) {
        File file = new File(filePath);
        StringBuilder result = new StringBuilder();
        try (InputStreamReader isr = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
             BufferedReader br = new BufferedReader(isr)) {
            String content;
            while ((content = br.readLine()) != null) {
                result.append(content).append(System.lineSeparator());
            }
        } catch (Exception e) {
            log.error("读取文件内容错误：" + e.getMessage());
        }
        return result.toString();
    }

    /**
     * 删除文件夹内所有文件
     *
     * @param path
     * @return
     */
    public static boolean deleteDir(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return false;
        }
        String[] content = file.list();
        for (String name : content) {
            File temp = new File(path, name);
            if (temp.isDirectory()) {
                deleteDir(temp.getAbsolutePath());
                temp.delete();
            } else {
                if (!temp.delete()) {
                    log.error("删除文件错误： " + name);
                }
            }
        }
        return true;
    }

    /**
     * 复制文件
     *
     * @param filename 文件名列表
     * @param oldpath  原路径
     * @param newpath  新路径
     * @return list 新路径列表
     * @throws IOException
     */
    public static List<String> copy(List<String> filename, String oldpath, String newpath, int serialNumber) throws IOException {
        List<String> path = Lists.newArrayList();
        for (String name : filename) {
            File oldpaths = new File(oldpath + "/" + name);
            File newpaths = new File(newpath + "/" + serialNumber + "-" + name);
            Files.copy(oldpaths.toPath(), newpaths.toPath());
            path.add(newpaths.getPath());
        }
        return path;
    }

    /**
     * 判断内容是不是都是 数字
     *
     * @param str
     * @return
     */
    public static boolean isAllNumber(String str) {
        return str.matches("[0-9]+");
    }



//    public static void tiffTurnJpg(String filePath){
//        RenderedOp file = JAI.create("fileload", filePath);//读取tiff图片文件
//        OutputStream ops = null;
//        try {
//            ops = new FileOutputStream("E:/A测试流/fk2.jpg");
//            //文件存储输出流
//            JPEGEncodeParam param = new JPEGEncodeParam();
//            ImageEncoder image = ImageCodec.createImageEncoder("JPEG", ops, param); //指定输出格式
//            //解析输出流进行输出
//            image.encode(file);
//            //关闭流
//            ops.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println("tiff转换jpg成功");
//    }

    private static String[] getCode(String str) {
        str = str.replaceAll("[\\[\\]]", "");
        str = str.replaceAll(" ", "");
        return str.split(",");
    }


    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        File file = new File("D:\\zz");
        FilenameFilter filenameFilter = (dir, name) -> name.endsWith(".DESC");
        File[] files = file.listFiles(filenameFilter);
        String content = "";
        int length = files.length;
        System.out.println(length);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

}
