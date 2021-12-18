package com.alkemy.ong.utils;




import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.Base64;

public class imageUtils {


    private static String encodeImage(String imagePath, String savePath) throws Exception{
        FileInputStream imageStream = new FileInputStream(imagePath);
        byte[] data = imageStream.readAllBytes();
        String imageString = Base64.getEncoder().encodeToString(data);

        FileWriter fileWriter = new FileWriter(savePath);
        fileWriter.write(imageString);
        fileWriter.close();
        imageStream.close();
        return imageString;
    }

    private static void decodeImage(String txtPath, String savePath) throws Exception {

        FileInputStream inputStream = new FileInputStream(txtPath);
        inputStream.readAllBytes();
        byte [] data = Base64.getDecoder().decode(new String(inputStream.readAllBytes()));

        FileOutputStream fileOutputStream = new FileOutputStream(savePath);
        fileOutputStream.write(data);
        fileOutputStream.close();
        inputStream.close();
    }








}
