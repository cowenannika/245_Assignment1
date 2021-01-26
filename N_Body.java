import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class N_Body{
    private static final double G = 6.673e-11;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        N_Body_List list = new N_Body_List(args[0]);
        new N_Body(list);
    }

    public N_Body(N_Body_List l) {
        N_Body_List list = l;
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }

                JFrame frame = new JFrame("N_Body Simulation");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new N_Body_Pane(list));
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public class N_Body_Pane extends JPanel {
        private N_Body_List list;
        private int [] colors;
        public N_Body_Pane(N_Body_List l) {
            this.list = l;
            this.colors = new int[list.size()];
            this.set_Colors();
            Timer timer = new Timer(1, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    moveBodies();
                    repaint();
                }
            });
            timer.start();

        }

        public void set_Colors(){
            for(int i = 0; i < colors.length; i ++){
                colors[i] = ((int)(Math.random() * 0x1000000));
            }
        }

        public void calculate_New_Velocity(){
            for(int i = 0; i < list.size(); i++){
                N_Node iNode = list.get(i);
                double mass = list.get(i).getMass();
                double force_SumX = 0, force_SumY = 0;
                for(int c = 0; c < list.size(); c++){
                    if(c != i){
                        N_Node cNode = list.get(c);
                        double scale = (double)list.scale();
                        double dist = calculate_Distance(iNode.getX() * scale, cNode.getX() * scale, iNode.getY() * scale, cNode.getY() * scale);

                        double deltaX = ((cNode.getX()  * scale) - (iNode.getX() * scale)), deltaY = ((cNode.getY()  * scale) - (iNode.getY()  * scale));
                        double force = ((G * mass * list.get(c).getMass()) / (dist * dist));

                        force_SumX = force_SumX + (force * (deltaX / dist));
                        force_SumY = force_SumY + (force * (deltaY / dist));

                    }
                }

                double accelerationX = (force_SumX / mass);
                double accelerationY = (force_SumY / mass);

                iNode.set_X_Velocity(list.get(i).get_X_Velocity() + accelerationX/list.scale());
                iNode.set_Y_Velocity(list.get(i).get_Y_Velocity() + accelerationY/list.scale());

            }
        }

        public double calculate_Distance(double x_one, double x_two, double y_one, double y_two){
            double deltaX = x_two - x_one;
            double deltaY = y_two - y_one;

            return Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));
        }

        protected void moveBodies(){
            this.calculate_New_Velocity();
            for(int i = 0; i < list.size(); i++){
                list.get(i).setX( list.get(i).getX() + list.get(i).get_X_Velocity());
                list.get(i).setY( list.get(i).getY() + list.get(i).get_Y_Velocity());
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(768, 768);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d;
            for(int i = 0; i < list.size(); i++){
                g2d = (Graphics2D) g.create();
                g2d.setColor(new Color(colors[i]));
                g2d.fillOval((int)list.get(i).getX(), (int)list.get(i).getY(), (int)list.get(i).size(), (int)list.get(i).size());
                g2d.dispose();
            }
        }

    }

}
