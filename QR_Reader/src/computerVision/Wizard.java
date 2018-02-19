package computerVision;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Wizard {
	public static void main(String[] args) throws IOException{		 
		 //https://internationalbarcodes.com/
		File input = new File("QR.jpg");
        BufferedImage image = ImageIO.read(input);	         
                
        QR qr = new QR(ImageToMap.covertImageToMap(image));
        
        printMap(qr);        
        
        System.out.println();
        System.out.println("Error correction level: " + qr.getErrorCorrection());
        System.out.println("Mask: " + qr.getMask());
        System.out.println("Message Length: " + qr.getMessageLength());
        
     
        
        System.out.println();
        
        qr.printMap();
        
        Reader r = new Reader();
        String message = "";
        try {
			message = r.read(qr);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
        System.out.println(message);
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
