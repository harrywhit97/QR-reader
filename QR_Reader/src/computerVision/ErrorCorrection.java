package computerVision;

public enum ErrorCorrection {
	L(3),M(2),Q(1),H(0);
	
	private int value;
	
	ErrorCorrection(int _value){
		this.value = _value;
	}
	
	public int getValue(){
		return value;
	}
	
	public static ErrorCorrection getByValue(int _value) throws Exception{
		for(ErrorCorrection ec : ErrorCorrection.values()){
			if(ec.getValue() == _value)
				return ec;
		}
		throw new Exception("Invalid input value: " + _value);
	}
}
