package computerVision;

public class Reader {
	
	private final int X = 0;
	private final int Y = 1;
	
	private Direction readingDirection;
	private QR qr;
	private int currentBit[];
	
	Reader(){
		
	}
	
	public String read(QR _qr){
		String message = "";
		qr = _qr;
		currentBit = qr.getMessageStartCoords();
		for(int i = 0; i < qr.getMessageLength(); i++)
			message += (char)readNextByte();
		 
		return message;
	}
	/* OLD
	private char readVertical(int x, int y, boolean isUp){
		int result = 0;
		for(int i = 0; i < 8; i++){
			int xDiff = i % 2;
			int yDiff = 1 / 2;
			
			result += updateResult(isUp, x - xDiff, y, yDiff, i);
		}
		return (char)result;
	}
	
	private char readHorizontal(int x, int y, boolean isTop){
		int result = 0;
		for(int i = 0; i < 8; i++){
			int xDiff = (i / 4) * 2;
			int yDiff = 0;			
			
			if(i > 1 && i < 6)
				yDiff = 1;			
		
			result += updateResult(isTop, x - xDiff, y, yDiff, i);
		}
		return (char)result;
	}
	
	private int updateResult(boolean isUpOrTop, int x, int y, int yDiff, int i){
		if(isUpOrTop)
			return updateBy(y - yDiff, x, i);		
		return updateBy(y + yDiff, x, i);		
	}
	
	private int updateBy(int x, int y, int i){
		if(map[y][x])
			return 2 ^ i;
		return 0;
	}
	
	
	*/
	
	
	
	public int readNextByte(){
		int b = 0;
		
		for(int i = 0; i < 8; i++){
			if(qr.getMap()[currentBit[Y]][currentBit[X]])
				b |= 1;
			b = b << 1;
			getNextBit();
		}
	}
	
	/**
	 * 
	 * @param x
	 * @return
	 */
	private boolean isUp(int x){
		if(x % 4 == 0)
			return true;
		return false;
		
	}
	
	private void getNextBit(){
		int x = currentBit[X];
		int y = currentBit[Y];
				
		if(x % 2 == 0){
			x--;
		}else{
			x++;
			y = isUp(x) ? y++ : y--;
		}
		
		
	}
	
	private boolean isValid(int[] bit){
		if(bit[0])
		
		
		
		return false;
	}
	
	private BitType getBitType(int[] bit){
		if(bit[0] )
	}
	
	public void setQR(QR _qr){
		qr = _qr;
	}
}
