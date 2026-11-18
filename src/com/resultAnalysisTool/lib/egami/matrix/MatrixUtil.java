package com.resultAnalysisTool.lib.egami.matrix;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MatrixUtil {
	/**
	 * Compares two <code>Matrix</code>s and represents their similarity in a double type number.
	 * This method uses simple algorithm and static pixel comparison. Both matrix must be in same size.
	 * @param mat1 <code>Matrix</code> for comparing
	 * @param mat2 <code>Matrix</code> that is compared to mat1
	 * @return similarity of the two <code>Matrix</code>s
	 */
	public static double compareMatrix(Matrix mat1, Matrix mat2){
		int rows = mat1.getRow(),
			cols = mat1.getCol();
		
		
		double sumOfDissimilarityByRow = 0;
		double sumOfDissimilarity = 0;
		
		for(int row=0, col; row<rows; row++){
			sumOfDissimilarityByRow = 0;
			
			for(col=0; col<cols; col++){
				sumOfDissimilarityByRow += findDifference(mat1.getPixel(row, col), mat2.getPixel(row, col), 8);
			}
			sumOfDissimilarity += sumOfDissimilarityByRow/2.55/cols;
		}
		
		double dissimilarity = sumOfDissimilarity/rows;
		
		return (100-dissimilarity);
	}
	

	/**
	 * Find difference in two doubles
	 * @param a
	 * @param b
	 * @param tolerance
	 * @return
	 */
	private static int findDifference(double a, double b, int tolerance){
		int dif = (int) (a-b);
		
		if(dif<0){
			dif = -dif;
		}
		
		if(dif<tolerance){
			return 0;
		}else{
			return dif;
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////

	public static BufferedImage matrixToBufferedImage(Matrix mat){
		int row = mat.getRow();
		int col = mat.getCol();
		
		BufferedImage bufferedImage = new BufferedImage(col, row, BufferedImage.TYPE_BYTE_GRAY);
		
		for(int i=0, j; i<row; i++){
			for(j=0; j<col; j++){
				bufferedImage.setRGB( j, i, rGBToInteger(mat.getPixel(i, j)) );
			}
		}
		
		return bufferedImage;
	}
	
	public static int rGBToInteger(int rGBInt){
		Color color = new Color(rGBInt, rGBInt, rGBInt, 255);
		
		return color.getRGB();
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////

	public static Matrix bufferedImageToMatrix(BufferedImage bufferedImage){
		int row = bufferedImage.getHeight();
		int col = bufferedImage.getWidth();
		
		Matrix matrix = new Matrix(row, col);
		
		for(int i=0, j; i<row; i++){
			for(j=0; j<col; j++){
				matrix.setPixel(i, j, integerToRGB(bufferedImage.getRGB(j, i)));
			}
		}
		
		return matrix;
	}
	
	public static int integerToRGB(int colorInt){
		Color color = new Color(colorInt, true);
		
		int colorRGB = color.getRed();//(color.getRed()+color.getGreen()+color.getBlue()) / 3;
		
		return colorRGB;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////

	public static Matrix resize(Matrix matrix, int scaledWidth, int scaledHeight){
		BufferedImage originalBufferedImage = matrixToBufferedImage(matrix);
		BufferedImage scaledBufferedImage = new BufferedImage(scaledWidth, scaledHeight, originalBufferedImage.getType());

		Graphics2D g = scaledBufferedImage.createGraphics();
    	g.drawImage(originalBufferedImage, 0, 0, scaledWidth, scaledHeight, null); 
    	g.dispose();
    	
    	return bufferedImageToMatrix(scaledBufferedImage);
    }
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static Matrix rotate(Matrix matrix, float degree){
		BufferedImage originalBufferedImage = matrixToBufferedImage(matrix);
		BufferedImage rotatedBufferedImage = new BufferedImage
				(originalBufferedImage.getWidth(), originalBufferedImage.getHeight(), originalBufferedImage.getType());
		
		AffineTransform affineTransform = new AffineTransform();
		affineTransform.rotate(Math.toRadians(degree), 0, rotatedBufferedImage.getHeight());
	    AffineTransformOp affineTransformOp = new AffineTransformOp(affineTransform, AffineTransformOp.TYPE_BILINEAR);
	    rotatedBufferedImage = affineTransformOp.filter(originalBufferedImage, null);
	    
	    //int cropTop = originalBufferedImage.getHeight() * (1-Math.sin(degree));
		
		return bufferedImageToMatrix(rotatedBufferedImage);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void write(Matrix matrix, String filePath) throws IOException{
		BufferedImage bufferedImage = matrixToBufferedImage(matrix);
		
		ImageIO.write(bufferedImage, filePath.substring(filePath.lastIndexOf('.')+1), new File(filePath));
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////

	public static void main(String[] args) throws IOException {
		Matrix matrix = new Matrix("C:\\Users\\admin\\Desktop\\a.jpg");
		
		matrix = rotate(matrix, 90);
		
		MatrixUtil.write(matrix, "C:\\Users\\admin\\Desktop\\b.png");
	}
}
