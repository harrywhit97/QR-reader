package computerVision;

public class QR {
	
	private boolean[][] map;
	private Mask mask;
	private ErrorCorrection ec;
	private int messageLength;
	private EncodingLevel encodingLevel;
	private AlignmentPattern[] alignmentPatterns;
	
	QR(boolean[][] _map){
		map = _map;
		mask = findMask();
		ec = findErrorCorrection();
		map = unMask();
		encodingLevel = findEncodingLevel();
		alignmentPatterns = findAlignemntPatterns();
		messageLength = findMessageLength();		
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
	
	public void printMap(int[] h){
		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map.length; j++){
				int[] a = {j,i};
				if(i == h[1] && j == h[0]){
					System.out.print("$");
					
				}else{
					BitType bt = BitType.getBitType(this, a);
					switch(bt){
					case Valid:
						System.out.print("+");
						break;
					case NotValid:
						System.out.print("-");
						break;
					case Alignment:
						System.out.print("a");
						break;
					case Timing:
						System.out.print("|");
						break;
					}
				}
				
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();
	}
	
	public void printMap(){
		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map.length; j++){
				int[] a = {j,i};
				BitType bt = BitType.getBitType(this, a);
				switch(bt){
				case Valid:
					System.out.print("+");
					break;
				case NotValid:
					System.out.print("-");
					break;
				case Alignment:
					System.out.print("a");
					break;
				case Timing:
					System.out.print("|");
					break;
				}				
			}
			System.out.println();
		}
	}
	
	
	private int findMessageLength(){
		int len = 0;
		int x = map.length - 1;
		int y = map.length - 3;
		
		for(int i = 0; i < 8; i++){
			if(map[y][x]){
				len |= 1;
			}
			if(i < 7){
				len = len << 1;
			}
			
			
			if(x % 2 == 0){
				x--;
			}else{
				x++;
				y--;
			}			
		}
		return len;
	}
	
	public AlignmentPattern[] getAlignmentPatterns(){
		return alignmentPatterns;
	}
	
	private AlignmentPattern[] findAlignemntPatterns(){
		//TO DO AlignmentPattern detection - hardcoded for now
		AlignmentPattern[] patterns = new AlignmentPattern[1];		
		patterns[0] = new AlignmentPattern(map.length-7, map.length-7);
		return patterns;
	}
	
	public int getMessageLength(){
		return messageLength;
	}
	
	public int getSize(){
		return map.length;
	}
	
	public int[] getMessageStartCoords(){
		int[] a = {map.length-1, map.length-7};
		return a;
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
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}	
	
	public EncodingLevel getEncodingLevel(){
		return encodingLevel;
	}
}
