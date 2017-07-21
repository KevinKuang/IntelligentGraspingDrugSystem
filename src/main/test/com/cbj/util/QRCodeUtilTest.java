package com.cbj.util;

/**
 * Created by Administrator on 2017/7/7.
 */
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import cn.edu.cqupt.nmid.igds.util.QRCodeUtil;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.junit.Test;

/**
 * 自定义生成二维码
 * @author admin
 *
 */
public class QRCodeUtilTest {
    @Test
    public void createZxing()  {
        try {
            QRCodeUtil.createQR("dasdsa","png","D:/download");
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readZxing() throws IOException, NotFoundException {

    }

}
