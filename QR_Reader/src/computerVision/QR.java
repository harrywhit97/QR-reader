package computerVision;

public class QR {
	
	private boolean[][] map;
	private Mask mask;
	private ErrorCorrection ec;
	
	QR(boolean[][] _map){
		map = _map;
		mask = findMask();
		ec = findErrorCorrection();
	}
	
	public Mask getMask(){
		return mask;
	}
	
	public void setMap(boolean[][] _map){
		map = _map;
	}
	
	private Mask findMask(){
		//TODO
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
}
