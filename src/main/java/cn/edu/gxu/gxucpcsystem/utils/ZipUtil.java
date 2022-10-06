package cn.edu.gxu.gxucpcsystem.utils;

import cn.edu.gxu.gxucpcsystem.domain.Medal;
import org.apache.tomcat.jni.Time;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;


/**
 * @author MaoMao
 * @Description
 * @create 2022-09-05 6:27 AM
 */

@Service
public class ZipUtil {
    private static final String ZIP = ".zip";
    private static final int BUFFER = 1024;

    /***
     *
     * @param file  文件流
     * @return
     * @throws IOException  文件流转换异常
     */
    public static byte[] fileToByte(File file) throws IOException{
        byte[] bytes = null;
        FileInputStream fis = null;
        try{
            fis = new FileInputStream(file);
            bytes = new byte[(int) file.length()];
            fis.read(bytes);
        }catch(IOException e){
            e.printStackTrace();
            throw e;
        }finally{
            fis.close();
        }
        return bytes;
    }

    /**
     * 解压文件到指定目录,注明文件内若包含.为开头的文件为隐藏文件，读取过程中会直接失败
     * @param file 压缩文件(全路径,包含文件名)
     */
    public static List<Medal> unzipFile(FileInputStream file) throws IOException {
        List<Medal> ls = new ArrayList<>();


        //  输入流过滤器.用于读取ZIP文件格式的文件
        try (ZipInputStream zipInputStream = new ZipInputStream(file)) {
            FileInputStream fileInputStream = file;
            // ZIP文件项
            ZipEntry zipEntry;

            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                Medal medal = new Medal();
                byte[] bytes = new byte[2048];
                fileInputStream.read(bytes);
                medal.setFiles(bytes);
                medal.setSize(bytes.length);
                medal.setFileName(zipEntry.getName());
                medal.setDate(System.currentTimeMillis());
                ls.add(medal);
            }


        } catch (IOException e) {

            throw new IOException("解压失败,原因:" + e.getMessage());
        }
        return ls;
    }
    public static boolean compressFileToZip(String compresspath) {
        boolean bool = false;
        try {
            ZipOutputStream zipOutput = null;
            File file = new File(compresspath);
            if(file.isDirectory()){
                zipOutput = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(compresspath + ".zip")));
                compressZip(zipOutput, file, "");
            }else{
                zipOutput = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(compresspath.substring(0, compresspath.lastIndexOf(".")) + ".zip")));
                zipOFile(zipOutput, file);
            }
            zipOutput.closeEntry();
            zipOutput.close();
            bool = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bool;
    }

    /**
     * @Title: compressZip
     * @Description: 子文件夹中可能还有文件夹，进行递归
     * @param @param zipOutput
     * @param @param file
     * @param @param suffixpath
     * @param @throws IOException
     * @return void    返回类型
     * @throws
     */
    private static void compressZip(ZipOutputStream zipOutput, File file, String suffixpath) {
        File[] listFiles = file.listFiles();// 列出所有的文件
        for(File fi : listFiles){
            if(fi.isDirectory()){
                if(suffixpath.equals("")){
                    compressZip(zipOutput, fi, fi.getName());
                }else{
                    compressZip(zipOutput, fi, suffixpath + File.separator + fi.getName());
                }
            }else{
                zip(zipOutput, fi, suffixpath);
            }
        }
    }

    /**
     * @Title: zip
     * @Description: 压缩的具体操作
     * @param @param zipOutput
     * @param @param file  文件
     * @param @param suffixpath  文件夹拼接路径
     * @return void    返回类型
     * @throws
     */
    public static void zip(ZipOutputStream zipOutput, File file, String suffixpath) {
        try {
            ZipEntry zEntry = null;
            if(suffixpath.equals("")){
                zEntry = new ZipEntry(file.getName());
            }else{
                zEntry = new ZipEntry(suffixpath + File.separator + file.getName());
            }
            zipOutput.putNextEntry(zEntry);
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            byte[] buffer = new byte[1024];
            int read = 0;
            while((read = bis.read(buffer)) != -1){
                zipOutput.write(buffer, 0, read);
            }
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Title: zip
     * @Description: 压缩单个文件
     * @param @param zipOutput
     * @param @param file  文件
     * @return void    返回类型
     * @throws
     */
    public static void zipOFile(ZipOutputStream zipOutput, File file) {
        try {
            ZipEntry zEntry = new ZipEntry(file.getName());
            zipOutput.putNextEntry(zEntry);
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            byte[] buffer = new byte[1024];
            int read = 0;
            while((read = bis.read(buffer)) != -1){
                zipOutput.write(buffer, 0, read);
            }
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
