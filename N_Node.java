public class N_Node{

    private String name = "";
    private double mass, x, y, velocity_x, velocity_y;
    private int size;

    public N_Node(String name, String mass, String x, String y,
                     String velocity_x, String velocity_y, String size){

        this.name = name;
        this.mass = Double.parseDouble(mass);
        this.x = Double.parseDouble(x);
        this.y = Double.parseDouble(y);
        this.velocity_x = Double.parseDouble(velocity_x);
        this.velocity_y = Double.parseDouble(velocity_y);
        this.size = Integer.parseInt(size);
    }

    public String name(){
        return this.name;
    }
    public double getMass(){
        return this.mass;
    }
    public double getX(){
        return this.x;
    }
    public void setX(double x){
        this.x = x;
    }
    public double getY(){
        return this.y;
    }
    public void setY(double y){
        this.y = y;
    }
    public double get_X_Velocity(){
        return this.velocity_x;
    }
    public void set_X_Velocity(double velocity_x){
        this.velocity_x = velocity_x;
    }
    public double get_Y_Velocity(){
        return this.velocity_y;
    }
    public void set_Y_Velocity(double velocity_y){
        this.velocity_y = velocity_y;
    }
    public int size(){
        return this.size;
    }

    public String toString(){
        String output = "";
        output = output + "\n\tname: " + this.name;
        output = output + "\n\tmass: " + this.mass;
        output = output + "\n\tx coordinate: " + this.x;
        output = output + "\n\ty coordinate: " + this.y;
        output = output + "\n\tx velocity: " + this.velocity_x;
        output = output + "\n\ty velocity: " + this.velocity_y;
        output = output + "\n\tsize: " + this.size;

        return output;
    }
}
