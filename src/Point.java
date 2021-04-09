import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

public class Point {

	public ArrayList<Point> neighbors;
	public static Integer []types ={0,1,2,3};
	public int type;
	public int staticField;
	public boolean isPedestrian;
	public boolean blocked;

	public Point() {
		type=0;
		staticField = 100000;
		neighbors= new ArrayList<Point>();
		blocked = false;
	}
	
	public void clear() {
		staticField = 100000;
		
	}

	public boolean calcStaticField() {
		if(neighbors.isEmpty())return false;

		Point smallestStaticFieldPoint = neighbors.stream()
				.min(Comparator.comparingInt(p -> p.staticField))
				.orElseThrow();

		if(smallestStaticFieldPoint.staticField + 1 >= this.staticField) return false;

		this.staticField = smallestStaticFieldPoint.staticField + 1;
		return true;
	}
	
	public void move(){
		if(!isPedestrian || blocked) return;

		Optional<Point> nextFieldOptional = neighbors.stream()
				.filter(p -> !p.isPedestrian)
				.min(Comparator.comparingInt(p -> p.staticField));

		if(nextFieldOptional.isEmpty()) return;

		Point nextField = nextFieldOptional.get();
		this.isPedestrian = false;
		if(nextField.type != 2)
			nextField.isPedestrian = true;
		//TODO: Make it possible to stay in place
		nextField.blocked = true;
	}

	public void addNeighbor(Point nei) {
		neighbors.add(nei);

	}
}