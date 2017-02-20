package classes;

/**
 * Created by fuxiaofeng on 2017/2/20.
 */
import javax.swing.*;
import java.awt.*;

public class Graph extends JPanel {
    int x;
    int y;

    public Graph(){
        setBackground(Color.white);
    }

    public void paint (Graphics g){
        super.paint(g);
        g.drawString(".", x, y);
        g.setColor(Color.black);
    }

    public void setX(int a){
        x = a;
    }
    public void setY(int a){
        y = a;
    }
}
