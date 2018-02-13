package computerVision;

public enum Mask {
	m0(0), m1(1),m2(2),m3(3),m4(4),m5(5),m6(6),m7(7);
	
	private int value;
	
	Mask(int _value){
		value = _value;
	}
	
	public int getValue(){
		return value;
	}
	
	public static Mask getByValue(int _value) throws Exception{
		for(Mask mask : Mask.values()){
			if(mask.getValue() == _value)
				return mask;
		}
		throw new Exception("Invalid input value: " + _value);
	}
	
	public boolean invertRequired(int x, int y){
		switch(this){
			case m0:
				return (y * x) % 2 + (y * x) % 3 == 0; 
			case m1:
				return (y / 2 + x / 3 ) % 2 == 0;
			case m2:
				return ((y * x) % 3 + y + x) % 2 == 0;
			case m3:
				return ((y * x) % 3 + y * x) % 2 == 0;
			case m4:
				return y % 2 == 0;
			case m5:
				return (y + x) % 2 == 0;
			case m6:
				return y + x % 3 == 0;
			case m7:
				return x % 3 == 0;	
			default:
				return false;
		}
	}
}
