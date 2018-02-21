package computerVision;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Reader {
	
	private final int X = 0;
	private final int Y = 1;
	
	private QR qr;
	private int currentBit[];
	private boolean[][] seen;
	
	private String bitLog = "";
	private PrintWriter writer = null;
	
	Reader(){
		try {
			writer = new PrintWriter("runLog.txt", "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String read(QR _qr) throws Exception{
		qr = _qr;
		String message = "";
		initSeen(qr.getMap().length);		
		currentBit = qr.getMessageStartCoords();
		
		for(int i = 0; i < qr.getMessageLength(); i++){
			bitLog += "byte " + i + "\r\n";
			
			int nextByte = readNextByte();
			if(nextByte != -1){
				System.out.println( i + " - " +nextByte);
				message += (char)nextByte;
			}else{
				break;
			}
			
		}
		
		writeToFile();
		 
		return message;
	}
	
	private void initSeen(int length){
		seen = new boolean[length][length];
		for(int i = 0; i < length; i++){
			for(int j = 0; j < length; j++){
				seen[i][j] = false;
			}
		}
	}
	
	private void writeToFile(){
		writer.println(bitLog);		
		writer.close();
	}
	
	
	public int readNextByte(){
		int b = 0;
		
		for(int i = 0; i < 8; i++){
			seen[currentBit[Y]][currentBit[X]] = true;
			
			//qr.printMap(currentBit);
			bitLog += "bit " + i + " is - " + qr.getMap()[currentBit[Y]][currentBit[X]] + "\r\n"; 
			bitLog += qr.getStringMap(currentBit);
			
			
			if(qr.getMap()[currentBit[Y]][currentBit[X]])
				b |= 1;
			if(i < 7){
				b = b << 1;
			}			
			
			try {
				if(getNextBit() == false){
					return -1;
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
		
		return b;
	}
	
	/**
	 * 
	 * @param x
	 * @return
	 */
	private boolean isUp(int x){
		if(x%2==1)
			x++;
		if(x % 4 == 0)
			return true;
		return false;		
	}
	
	private boolean getNextBit() throws Exception{
		int[] bit = {currentBit[X], currentBit[Y]};		
		
		//zigzag
		if(bit[X] % 2 == 0){
			bit = moveLeft(bit);
		}else{
			bit = moveRight(bit);
			bit = isUp(bit[X]) ? moveUp(bit) : moveDown(bit);
		}
		
		BitType bitType = BitType.getBitType(qr, bit);
		
		switch(bitType){
			case Valid:
				if(seen[bit[Y]][bit[X]] == true){
					currentBit = moveLeft(currentBit);
				}else{
					currentBit = bit;
				}
				
				break;
			case NotValid:
				currentBit = nextBitFromNotValid(bit);
				break;
				
			case Alignment:
				currentBit = nextBitFromAllignment(bit);		
				if(currentBit[X] == -1 && currentBit[Y] == -1)
					return false;
				break;
				
			case Timing:
				currentBit = nextBitFromTiming(bit);
				break;				
				
			default:
				break;	
		}
		return true;
	}
	
	private int[] nextBitFromTiming(int[] bit){
		if(bit[Y] == 6){
			bit[Y] = isUp(bit[X]) ? bit[Y] - 1 : bit[Y] + 1;
		}else{
			bit = moveLeft(bit);
		}
		return bit;
	}
	
	private int[] nextBitFromAllignment(int[] bit){
		int[] tempBit = {bit[X], bit[Y]};
		if(isValid(qr, moveLeft(tempBit))){
			bit = moveLeft(bit);
		}else{
			if(isUp(currentBit[X])){
				while(!isValid(qr, bit)){
					bit = moveUp(bit);
				}
				
			}else{
				while(!isValid(qr, bit)){
					bit = moveDown(bit);
				}
			}
		}	
		return bit;
	}
	
	private int[] nextBitFromNotValid(int[] bit) throws Exception{
		bit = moveLeft(currentBit);
		
		if(!isValid(qr, bit)){
			
			if(bit[X] >= 0){
				//bit is (10,x)
				bit[Y] = qr.getSize()-10;
				if(!isValid(qr, bit))
					throw new Exception("ERROR : UNKNOWN BIT, CURRENT BIT IS - (" + bit[X] + ", " + bit[Y] + ")");
			}else{
				//There is no next bit
				bit[X] = -1;
				bit[Y] = -1;
			}
		}
		return bit;
	}
	
	private boolean isValid(QR qr, int[] bit){
		return BitType.getBitType(qr, bit).equals(BitType.Valid);
	}
	
	private int[] moveUp(int[] bit){
		bit[Y]--;
		return bit;
	}
	
	private int[] moveDown(int[] bit){
		bit[Y]++;
		return bit;
	}
	
	private int[] moveLeft(int[] bit){
		bit[X]--;
		return bit;
	}
	
	private int[] moveRight(int[] bit){
		bit[X]++;
		return bit;
	}
	
	public void setQR(QR _qr){
		qr = _qr;
	}
	
	private int toAscii(int n){
		if(n < 10){
			return n;			
		}else if(n < 36){
			return n + 55;
		}else if(n >= 41 && n <= 43){
			return n + 4;
		}
		// :
		return n + 14;
	}
}
