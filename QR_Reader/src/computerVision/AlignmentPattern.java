package computerVision;

public class AlignmentPattern {
	
	//top left cell of pattern	
	private int startX, startY;
	
	//bottom right cell of pattern
	private int endX, endY;
	
	AlignmentPattern(int patternCenterX, int patternCenterY){
		startX = patternCenterX-2;
		startY = patternCenterY-2;
		endX = patternCenterX + 2;
		endY = patternCenterY + 2;
	}
	
	public int getStartX(){
		return startX;
	}

	public int getStartY(){
		return startY;
	}
	
	public int getEndX(){
		return endX;
	}
	
	public int getEndtX(){
		return endY;
	}
}
