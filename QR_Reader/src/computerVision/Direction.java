package computerVision;

public enum Direction {
	Up, RightUp, Right, RightDown, Down, Left;
	
	public Direction getRightDiagonal(Direction vertical){
		if(vertical.equals(Down)){
			return RightDown;
		}
		return RightUp;
	}
}
