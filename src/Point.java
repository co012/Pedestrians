import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

public class Point {

	public ArrayList<Point> neighbors;
	public static Integer []types ={0,1,2,3};
	public int type;
	public float staticField;
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

	public void setStaticField(float val) {
		staticField = val;
	}
	
	public void move(){
		if(!isPedestrian || blocked) return;

		Optional<Point> nextFieldOptional = neighbors.stream()
				.filter(p -> !p.isPedestrian)
				.min(Comparator.comparingDouble(p -> p.staticField));

		if(nextFieldOptional.isEmpty()) return;

		Point nextField = nextFieldOptional.get();
		if(nextField.staticField > this.staticField) nextField = this;

		this.isPedestrian = false;
		if(nextField.type != 2)
			nextField.isPedestrian = true;
		nextField.blocked = true;
	}

	public void addNeighbor(Point nei) {
		neighbors.add(nei);

	}
}