public class Vector2d {
    public final int x;
    public final int y;


    public Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double calculateDistanceTo(int x, int y){
        return Math.sqrt(Math.pow(this.x - x,2) + Math.pow(this.y - y,2));
    }

    public double calculateDistanceTo(Vector2d that){
        return calculateDistanceTo(that.x,that.y);
    }
}
