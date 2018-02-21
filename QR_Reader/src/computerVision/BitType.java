package computerVision;

public enum BitType {
	Valid, Alignment, NotValid, Timing;
	
	final static int FINDERLENGTH = 8; // with white surrounding space
	
	public static BitType getBitType(QR qr, int[] bit){		

		if(isOutOfBounds(qr, bit) || isFinderOrFormatting(bit, qr.getSize()-1)){
			return NotValid;
			
		}else if(isAllignment(bit, qr.getAlignmentPatterns())){
			return Alignment;
		}else if(isTiming(bit))
			return Timing;
		return Valid;
	}
	
	public static boolean isTiming(int[] bit){
		if(bit[0] == 6 || bit[1] == 6)
			return true;
		return false;
	}
	
	private static boolean isOutOfBounds(QR qr, int[] bit){	
		int[] qrStart = {0,0};
		int[] qrEnd = {qr.getSize()-1, qr.getSize()-1};
		return !isWithIn(qrStart, qrEnd , bit);
	}
	
	/**
	 * 
	 * @param bit
	 * @param qrLength size of qr -1 so map[qrLength][qrLength] = last bit in qr
	 * @return
	 */
	public static boolean isFinderOrFormatting(int bit[], int qrLength){
		int formatting = 1;
		
		//Right top
		int[] finderStart = {qrLength - (FINDERLENGTH - formatting), 0}; 
		int[] finderEnd = {qrLength, FINDERLENGTH}; 
		
		if(isWithIn(finderStart, finderEnd, bit))
			return true;	
		
		//Left top
		finderStart[0] = 0; 
		finderStart[1] = 0;
		finderEnd[0] = FINDERLENGTH; 
		finderEnd[1] = FINDERLENGTH; 
		
		if(isWithIn(finderStart, finderEnd, bit))
			return true;	
		
		//Bottom	
		finderStart[0] = 0; 
		finderStart[1] = qrLength - FINDERLENGTH;
		finderEnd[0] = FINDERLENGTH; 
		finderEnd[1] = qrLength; 
		
		if(isWithIn(finderStart, finderEnd, bit))
			return true;	
		
		return false;
	}
	
	private static boolean isAllignment(int[] bit, AlignmentPattern[] patterns){
		for(AlignmentPattern p : patterns){
			if(isWithIn(p.getStartCoords(), p.getEndCoords(), bit))
				return true;
		}
		return false;
	}
	
	/**
	 * Checks if a point (x,y) is inclusively in a space start(x,y) and end(x,y)
	 * Where start is the top left corner of a square or rectangular space and 
	 * end is the bottom right corner.
	 * @param spaceStart
	 * @param spaceEnd
	 * @param point
	 * @return
	 */
	private static boolean isWithIn(int[] spaceStart, int[] spaceEnd, int[] point){
		if(point[0] >= spaceStart[0] && point[0] <= spaceEnd[0]){
			if(point[1] >= spaceStart[1] && point[1] <= spaceEnd[1])
				return true;
		}
		return false;
	}
}
