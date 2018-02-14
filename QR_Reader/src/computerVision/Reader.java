package computerVision;

public class Reader {
	
	Reader(){
		
	}
	
	public String read(QR qr){
		String message = "";
		
		//Assume 
		return message;
	}
	
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
	
	public int readByte(boolean[][] map){
		
	}
	
	private int[] getNextBit(){
		
	}
}
