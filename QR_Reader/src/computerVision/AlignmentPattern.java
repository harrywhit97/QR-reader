package computerVision;

public class AlignmentPattern {
	
	//top left cell of pattern	
	private int[] startXY;
	
	//bottom right cell of pattern
	private int[] endXY;
	
	AlignmentPattern(int patternCenterX, int patternCenterY){
		startXY = new int [2];
		endXY = new int [2];
		startXY[0] = patternCenterX-2;
		startXY[1] = patternCenterY-2;
		endXY[0] = patternCenterX + 2;
		endXY[1] = patternCenterY + 2;
	}
	
	public int[] getStartCoords(){
		return startXY;
	}
	
	public int[] getEndCoords(){
		return endXY;
	}
}
