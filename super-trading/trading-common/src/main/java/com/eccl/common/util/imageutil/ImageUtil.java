package com.eccl.common.util.imageutil;

import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.FileOutputStream;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;

/**
 * 图片压缩类（整理）
 * 
 * @author weifeng
 */
public class ImageUtil {

	/**
	 * 等比压缩图像
	 * 
	 * @param src
	 *            源图像文件
	 * @param target
	 *            压缩后要存放的目标文件
	 * @param maxWidth
	 *            压缩后允许的最大宽度
	 * @param maxHeight
	 *            压缩后允许的最大高度
	 * @throws java.io.IOException
	 */
	public static void transform(String src, String target, int maxWidth, int maxHeight) throws Exception {
		String dirStr = target.substring(0, target.lastIndexOf("/") + 1);
		File dir = new File(dirStr);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		File srcFile = new File(src);
		File targetFile = new File(target);
		AffineTransform transform = new AffineTransform();
		BufferedImage biSrc = ImageIO.read(srcFile);
		int width = biSrc.getWidth();
		int height = biSrc.getHeight();
		int newWidth = maxWidth;
		int newHeight = (int) (((double) newWidth / width) * height);
		if (newHeight > maxHeight) {
			newHeight = maxHeight;
			newWidth = (int) (((double) newHeight / height) * width);
		}
		double scaleX = (double) newWidth / width;
		double scaleY = (double) newHeight / height;
		transform.setToScale(scaleX, scaleY);
		// 此句设置图片品质或某一部分放大，如何应用目前不详
		transform.setToQuadrantRotation(50);
		AffineTransformOp ato = new AffineTransformOp(transform, null);
		BufferedImage biTarget = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_3BYTE_BGR);
		biTarget.getGraphics().drawImage(biSrc.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);
		ato.filter(biSrc, biTarget);
		ImageIO.write(biTarget, "jpeg", targetFile);
	}

	/**
	 * 不改变图片大小，压缩图片
	 * 
	 * @param srcFilePath
	 *            源图像文件
	 * @param descFilePath
	 *            压缩后要存放的目标文件
	 * @param qality
	 *            压缩的程度(取值0~1)，图片质量
	 * @return true/false
	 */
	public static boolean compressPic(String srcFilePath, String descFilePath, float qality) {
		File file = null;
		BufferedImage src = null;
		FileOutputStream out = null;
		ImageWriter imgWrier;
		ImageWriteParam imgWriteParams;
		// 指定写图片的方式为 jpg
		imgWrier = ImageIO.getImageWritersByFormatName("jpg").next();
		imgWriteParams = new javax.imageio.plugins.jpeg.JPEGImageWriteParam(null);
		// 要使用压缩，必须指定压缩方式为MODE_EXPLICIT
		imgWriteParams.setCompressionMode(imgWriteParams.MODE_EXPLICIT);
		// 这里指定压缩的程度，参数qality是取值0~1范围内，
		imgWriteParams.setCompressionQuality((float) qality);
		imgWriteParams.setProgressiveMode(imgWriteParams.MODE_DISABLED);
		ColorModel colorModel = ColorModel.getRGBdefault();
		// 指定压缩时使用的色彩模式
		imgWriteParams.setDestinationType(
				new javax.imageio.ImageTypeSpecifier(colorModel, colorModel.createCompatibleSampleModel(16, 16)));
		try {
			file = new File(srcFilePath);
			src = ImageIO.read(file);
			out = new FileOutputStream(descFilePath);

			imgWrier.reset();
			// 必须先指定 out值，才能调用write方法, ImageOutputStream可以通过任何 OutputStream构造
			imgWrier.setOutput(ImageIO.createImageOutputStream(out));
			// 调用write方法，就可以向输入流写图片
			imgWrier.write(null, new IIOImage(src, null, null), imgWriteParams);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// 测试方法
	public static void main(String[] args) throws Exception {
		ImageUtil.transform("e:/1111111.jpg", "e:/3333.jpg", 800, 600);
		System.out.println("success");

		if (compressPic("e:/testmy.jpg", "e:/qqqqqqq.jpg", 0.7f)) {
			System.out.println("压缩成功！");
		} else {
			System.out.println("压缩失败！");
		}

	}

}