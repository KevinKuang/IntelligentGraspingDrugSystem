package cn.edu.cqupt.nmid.igds.util;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;

/**
 * Created by Administrator on 2017/7/8.
 */
public class QRCodeUtil {
    public static void createQR(String content,String format,String path) throws WriterException, IOException {
        int width=300;
        int hight=300;
        HashMap hints=new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);//纠错等级L,M,Q,H
        hints.put(EncodeHintType.MARGIN, 2); //边距
        BitMatrix bitMatrix=new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, hight, hints);
        Path file=new File(path+"/"+content+"."+format).toPath();
        MatrixToImageWriter.writeToPath(bitMatrix, format, file);
    }

    public static String readQR(String pngPath) throws IOException, NotFoundException {
        MultiFormatReader read = new MultiFormatReader();
        File file=new File(pngPath);
        BufferedImage image= ImageIO.read(file);
        Binarizer binarizer=new HybridBinarizer(new BufferedImageLuminanceSource(image));
        BinaryBitmap binaryBitmap=new BinaryBitmap(binarizer);
        Result res=read.decode(binaryBitmap);
        return res.getText();
    }
}
