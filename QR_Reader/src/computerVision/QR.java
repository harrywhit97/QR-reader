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
		alignmentPatterns = findAlignemntPatterns();
		unMask();
		encodingLevel = findEncodingLevel();		
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
		int bit = 1;
		
		for(int i = 4; i > 2; i--){
			if(map[8][i])
				mask += bit;
			bit *= 2;
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
		int bit = 1;
		
		for(int i = 1; i >= 0; i--){
			if(map[8][i])
				ecLvl += bit;
			bit *= 2;
		}
		 
		
		try {
			return ErrorCorrection.getByValue(ecLvl);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	public void unMask(){
		int[] bit = new int[2];
		for(int y = 0; y < map.length; y++){
			bit[1] = y;
			for(int x = 0; x < map.length; x++){
				bit[0] = x;				
								
				if(BitType.getBitType(this, bit).equals(BitType.Valid)){
					//if(y < 6)
						///bit[1]++;
					
					if(mask.invertRequired(x, bit[1]))
						map[x][y] = !map[x][y];
				}
			}						
		}
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
	
	
	public String getStringMap(int[] h){
		String s = "";
		String newLine = "\r\n";
		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map.length; j++){
				int[] a = {j,i};
				if(i == h[1] && j == h[0]){
					s += "$";
					
				}else{
					BitType bt = BitType.getBitType(this, a);
					switch(bt){
					case Valid:
						s += "+";
						break;
					case NotValid:
						s += "-";
						break;
					case Alignment:
						s += "a";
						break;
					case Timing:
						s += "|";
						break;
					}
				}
				
			}
			s+= newLine;
		}
		s+= newLine+newLine;
		return s;
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
