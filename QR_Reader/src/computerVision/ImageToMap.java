package computerVision;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * 
 * @author Harry Whittaker
 * @since 13/02/2018
 *  
 *
 */
public class ImageToMap {
	 	 
	 /**
	  * Converts a buffered image of a QR code where the code takes up the entire image
	  * and is correctly positioned
	  * @param image
	  * @return 2D boolean array where true represents a black box on the QR code
	  */
	 public static boolean[][] covertImageToMap(BufferedImage image){
		 
		 int boxSize = getSizeOfBox(image);
		 boolean[][] map = new boolean[image.getHeight() / boxSize][image.getWidth() / boxSize];		 
		 
		 for(int x = 0; x < map[0].length; x++){
			 for(int y = 0; y < map.length; y++){
				 
				 int imX = (x * boxSize) + (boxSize/2);
				 int imY = (y * boxSize) + (boxSize/2);
				 map[y][x] = (isBlack(new Color(image.getRGB(imX, imY)))) ? true : false;
			 }
		 }
		 		 
		 return map;
	 }
	 
	 /**
	  * Get the pixel length of a side of a single box in the QR code
	  * @param image
	  * @return pixel length of the side of a box, return -1 if no white boxes exist
	  */
	 private static int getSizeOfBox(BufferedImage image){
		 
		 for(int i = 0; i < image.getWidth(); i++){
			 
			 if(!isBlack(new Color(image.getRGB(i, 0))))
				 //7 black boxes in first row before first white box
				 return i/7;
		 }
		 return -1;
	 }
	 
	 /**
	  * Test if input value is black
	  * @param colour TYPE_INT_ARGB
	  * @return true is input colour is black
	  */
	 private static boolean isBlack(Color colour){
		 int R = colour.getRed();
		 int G = colour.getGreen();
		 int B = colour.getBlue();
		 
		 if(R < 128 && G < 128 && B < 128)
			 return true;
		 return false;
	 }
}
