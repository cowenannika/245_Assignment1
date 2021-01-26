import java.io.*;
import java.util.*;

//Class for reading file input and making either an arraylist or linkedlist of celestial bodies

public class N_Body_List{

    private List<N_Node> n_Body_List;
    private double scale;
    private char type;
    private int size = 0;

    public N_Body_List(String f){
        ArrayList<String> n_Body_Args = Read_File(f);
        this.scale = Double.parseDouble(n_Body_Args.get(1));
        if(n_Body_Args.get(0).equals("ArrayList")){
            this.n_Body_List = new ArrayList<N_Node>();
            this.type = 'a';
            parseArgs(n_Body_Args);
        }
        else if(n_Body_Args.get(0).equals("LinkedList")){
            this.n_Body_List = new LinkedList<N_Node>();
            this.type = 'l';
            parseArgs(n_Body_Args);
        }

        this.scale = Double.parseDouble(n_Body_Args.get(1));

    }

    public double scale(){
        return this.scale;
    }
    public N_Node get(int i){
        return n_Body_List.get(i);
    }
    public int size(){
        return this.size;
    }
    private char getType(){
        return type;
    }

    private void parseArgs(ArrayList<String> list){
        int i = 2;

        while(i < list.size()){
            n_Body_List.add(new N_Node(list.get(i), list.get(i+1), list.get(i+2), list.get(i+3)
                    , list.get(i+4), list.get(i+5), list.get(i+6)));
            i += 7;
            this.size ++;
        }
    }

    private ArrayList<String> Read_File(String f){
        ArrayList<String> n_Body_Args = new ArrayList<String>();
        try{
            File file = new File(f);
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                Scanner scan = new Scanner(scanner.nextLine());
                scan.useDelimiter(",");
                while(scan.hasNext()){
                    n_Body_Args.add(scan.next());
                }
            }
            scanner.close();
        }catch (FileNotFoundException e){
            System.out.println("There is no file!");
            e.printStackTrace();
        }
        return n_Body_Args;
    }

    public String toString(){
        String output = "N_Body List: \n";

        for(int i = 0; i < n_Body_List.size(); i ++){
            output = output + "N_Body: " + i + n_Body_List.get(i).toString() + "\n";
        }

        return output;
    }

    public static void main(String [] args){
        N_Body_List n = new N_Body_List("n_body_input.txt");
        System.out.println(n.toString());
    }
}
