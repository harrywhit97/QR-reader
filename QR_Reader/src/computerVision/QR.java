package computerVision;

public class QR {
	
	private boolean[][] map;
	private Mask mask;
	private ErrorCorrection ec;
	private int messageLength;
	private EncodingLevel encodingLevel;
	
	QR(boolean[][] _map){
		map = _map;
		mask = findMask();
		ec = findErrorCorrection();
		encodingLevel = findEncodingLevel();
		//length = findLength();
	}
	
	public Mask getMask(){
		return mask;
	}
	
	public void setMap(boolean[][] _map){
		map = _map;
	}
	
	private Mask findMask(){
		int mask = 0;
		
		for(int i = 2; i < 5; i++){
			if(map[8][i])
				mask += 5-i;
		}
		
		try {
			return Mask.getByValue(mask);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	public ErrorCorrection getErrorCorrection(){;
		return ec;
	}
	
	private ErrorCorrection findErrorCorrection(){
		int ecLvl = 0;
		
		for(int i = 0; i < 2; i++){
			if(map[8][i])
				ecLvl += 2-i;
		}
		
		try {
			return ErrorCorrection.getByValue(ecLvl);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean[][] unMask(){
		//unmask top and left section				
		for(int y = 0; y < 9; y++){
			if(y != 6){
				for(int x = 9; x < map.length - 7; x++){
					if(mask.invertRequired(x, y))
						map[y][x] = !map[y][x];
					
					if(mask.invertRequired(y, x))
						map[x][y] = !map[x][y];
				}
			}			
		}		
		
		//unmask main section		
		for(int y = 9; y < map.length; y++){
			for(int x = 9; x < map.length; x++){
				if(mask.invertRequired(x, y))
					map[y][x] = !map[y][x];
			}
		}			
		return map;
	}
	
	public boolean[][] getMap(){
		return map;
	}
	
	private int findLength(){
		int len = 0;
		for(int i = 0; i < 8; i++){
			int xDiff = i % 2;
			int yDiff = 1 / 2;
			
			if(map[y - yDiff][x - xDiff])
				len += 2 ^ i;
		}
		return (char)result;

		
		-6
	}
	
	public int getMessageLength(){
		return messageLength;
	}
	
	private EncodingLevel findEncodingLevel(){
		int enc = 0;
		int bit = 1;
		for(int y = map.length - 2; y < map.length; y++){
			for(int x = map.length - 2; x < map.length; x++){
				if(map[y][x])
					enc += bit;
				bit *= 2;
			}
		}
		try {
			return EncodingLevel.getByValue(enc);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}	
	
	public EncodingLevel getEncodingLevel(){
		return encodingLevel;
	}
}
