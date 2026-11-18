/****************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																	*
* Date: 10-Mar-2015																								*
****************************************************************************************************************/


package com.resultAnalysisTool.lib.egami.matrix;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Matrix {
	private int[][] pixels;
	
	public Matrix(int row, int col){	//row=height, col=width
		this.pixels = new int[row][col];
	}
	
	public Matrix(String imageFilePath) throws IOException{
		this(ImageIO.read(new File(imageFilePath)));
	}
	
	public Matrix(BufferedImage bufferedImage){
		this.pixels = MatrixUtil.bufferedImageToMatrix(bufferedImage).pixels;
	}
	
	
	public Matrix subMatrix(int rowStart, int rowEnd, int colStart, int colEnd){
		if(rowStart>=0 && rowEnd>rowStart && colStart>=0 && colEnd>colStart){
			int row = rowEnd-rowStart;
			int col = colEnd-colStart;
			
			Matrix matrix = new Matrix(row, col);
			
			for(int i=0; i<row; i++){
				for(int j=0; j<col; j++){
					matrix.pixels[i][j] = this.pixels[rowStart+i][colStart+j];
				}
			}
			
			return matrix;
			
		}else{
			return new Matrix(1, 1);
		}
	}
	
	public int getRow(){
		return pixels.length;
	}
	
	public int getCol(){
		return pixels[0].length;
	}
	
	public int getPixel(int row, int col){
		return pixels[row][col];
	}
	
	public void setPixel(int row, int col, int value){
		pixels[row][col] = value;
	}
	
	public String dump(){
		int row = pixels.length;
		int col = pixels[0].length;
		
		String string = "(" + row + "," + col + ")\n";
		string += "[";
		for(int i=0, j; i<row; i++){
			for(j=0; j<col; j++){
				string += pixels[i][j]+",";
			}
		}
		string += "]";
		
		return string;
	}
	
	public static Matrix load(String string){
		int startIndex=0, stopIndex=0;
		int row=0, col=0;
		
		startIndex = 1;
		stopIndex = string.indexOf(',', startIndex);
		
		row = Integer.valueOf(string.substring(startIndex, stopIndex));
		
		startIndex = stopIndex+1;
		stopIndex = string.indexOf(')', startIndex);
		
		col = Integer.valueOf(string.substring(startIndex, stopIndex));
		System.out.println(row+ " "+ col);
		
		Matrix matrix = new Matrix(row, col);
		
		stopIndex = string.indexOf('[', stopIndex);
		for(int i=0; i<row; i++){
			for(int j=0; j<col; j++){
				startIndex = stopIndex+1;
				stopIndex = string.indexOf(',', startIndex);
				matrix.pixels[i][j] = Integer.valueOf(string.substring(startIndex, stopIndex));
				System.out.println(i+ " "+ j + " " + matrix.pixels[i][j]);
			}
		}
		
		return matrix;
	}
	
	/**TEST_ONLY**/
	public static void main(String[] args) {
		try {
			Matrix matrix = new Matrix("C:\\Users\\admin\\Desktop\\a.jpg");
			
			MatrixUtil.write(matrix, "C:\\Users\\admin\\Desktop\\c.jpg");
			//System.out.println(matrix.dump());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
