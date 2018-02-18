package computerVision;

public enum BitType {
	Valid, Alignment, Finder, Formatting, OutOfBounds;
	
	public BitType getBitType(QR qr, int[] bit){
		int x = bit[0];
		int y = bit[1];
		
		//check for out of bounds
		if(isOutOfBounds(qr, x, y)){
			return OutOfBounds;
			
		}else if(){
			
		}
	}
	
	private boolean isOutOfBounds(QR qr, int[] bit){	
		int[] qrStart = {0,0};
		int[] qrEnd = {qr.getSize()-1, qr.getSize()-1};
		return isWithIn(qrStart, qrEnd , bit);
	}
		
	private boolean isFinder(int x, int y, int qrLength){
		int[] finderRight = {qrLength, 0}; 
		int[] finderRightTop = {0,0}; 
		int[] finderBottom = {0, qrLength}; 
		
		
		for(int i = 0; i < 3; i++){
			int[] corner = new int[2];
			corner[0] = i % 2 == 0 ? 0 : qrLength;
			corner[1] = i > 2 ? 0 : qrLength;
			
			switch(i){
				
			}
		}
	}
	
	private int[] getStartOfFinder(int[] corner){
		int finderLength = 7;
		
		if(corner[0] == 0){
			if(corner[1] == 0){
				return corner;
			}else{
				return new int[] {0, corner[1] -};
			}
		}
	}
	
	/**
	 * Checks if a point (x,y) is inclusively in a space start(x,y) and end(x,y)
	 * @param spaceStart
	 * @param spaceEnd
	 * @param point
	 * @return
	 */
	private boolean isWithIn(int[] spaceStart, int[] spaceEnd, int[] point){		
		for(int i = 0; i < 2; i++){
			//check if point is NOT within 1D bounds
			if(point[i] < spaceStart[i] && point[i] > spaceEnd[i])
				return false;
		}
		return true;
	}
}
