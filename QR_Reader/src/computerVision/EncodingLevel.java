package computerVision;

public enum EncodingLevel {
	EndOfMessage(0), Numeric(1), Alphanumeric(2), StructuredAppend(3), ByteEncoding(4), FNC1First(5), ExtendedChannelInterpretarion(7), Kanji(8), FNC1Second(9);
	
	private int value;
	
	EncodingLevel(int _value){
		value = _value;
	}
	
	public static EncodingLevel getByValue(int _value) throws Exception{
		for(EncodingLevel enc : EncodingLevel.values()){
			if(enc.getValue() == _value)
				return enc;
		}
		throw new Exception("Invalid input value: " + _value);
	}
	
	public int getValue(){
		return value;
	}
}
