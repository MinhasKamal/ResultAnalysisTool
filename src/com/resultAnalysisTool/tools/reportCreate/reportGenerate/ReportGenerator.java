package com.resultAnalysisTool.tools.reportCreate.reportGenerate;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class ReportGenerator {
	public void createPdf(String filePath, BufferedImage bufferedImage)
			throws IOException, DocumentException {

		Document document = new Document();

		PdfWriter.getInstance(document, new FileOutputStream(filePath));

		document.open();

		Image image = Image.getInstance(bufferedImage, null);
		image.scaleToFit(700, 700);
		
		document.add(image);

		document.close();
	}
	
	public static void main(String[] args) throws Exception {
		String report = "C:\\Users\\admin\\Desktop\\a.pdf";
		String imageFilePath = "C:\\Users\\admin\\Desktop\\omr.PNG";
		Scanner s = new Scanner(System.in);
		new ReportGenerator().createPdf(report, ImageIO.read(new File(imageFilePath)));
		s.close();
	}

}