package classes;

/**
 * Created by fuxiaofeng on 2017/2/20.
 */
import javax.swing.*;
import java.awt.*;

public class Graph extends JPanel {
    public Graph(){
        setBackground(Color.white);
    }

    public void paint (Graphics g){
        g.drawLine(0,0,100,100);
        g.setColor(Color.blue);
    }
}
