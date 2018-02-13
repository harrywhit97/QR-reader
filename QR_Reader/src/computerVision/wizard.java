package computerVision;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class wizard {
	public static void main(String[] args) throws IOException{		 
		 
		File input = new File("QR.jpg");
        BufferedImage image = ImageIO.read(input);	         
                
        QR qr = new QR(imageToMap.covertImageToMap(image));
        
        printMap(qr);        
        
        System.out.println();
        System.out.println("Error correction level: " + qr.getErrorCorrection());
        System.out.println("Mask: " + qr.getMask());
        
        qr.unMask();
        
        System.out.println();
        
        printMap(qr);
	 }
	
	private static void printMap(QR qr){
		for(boolean[] a : qr.getMap()){
        	for(boolean b : a){
        		int i = 0;
        		if(b)
        			i = 1;
        		System.out.print(i);        			
        	}        		
        	System.out.println();
        }
	}
}