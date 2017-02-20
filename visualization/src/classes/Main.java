package classes;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Main extends JFrame{
    final static String path = "/Users/fuxiaofeng/Desktop/a.txt";
    final static int index = 1;

    public Main(){
        FileReader reader = new FileReader();
        ArrayList data = reader.read(path);
        String text = (String)data.get(index);

        //取出点
        ArrayList <String[]> coordinates = new ArrayList <String[]> ();
        String[] temp = new String[2];

        for (int i = 0; i < text.length(); i++){
            if (text.charAt(i) == '('){
                    int a = i + 1;
                    String s = "";

                    while(text.charAt(a) != ','){
                        s = s + text.charAt(a);
                        a++;
                    }

                    temp[0] = s;

            }else if (text.charAt(i) == ')'){
                    int b = i - 1;
                    String s1 = "";

                    while(text.charAt(b) != ','){
                        s1 = text.charAt(b) + s1;
                        b--;
                    }

                    temp[1] = s1;
                    coordinates.add(temp);
                    temp = new String[2];
            }else if (text.charAt(i) == '#'){
                    break;
            }
        }

        for (int i = 0; i < coordinates.size(); i++){
            double x = Double.parseDouble(coordinates.get(i)[0]) * 100 + 500;
            double y = Double.parseDouble(coordinates.get(i)[1]) * 100 + 500;
            int ax = (int)x;
            int ay = (int)y;

            Graph
        }

        Container c = getContentPane();
        setBackground(Color.white);
        Graph g = new Graph();
        c.setLayout(new BorderLayout());
        c.add(g,BorderLayout.CENTER);
        setSize(1000,1000);
        setVisible(true);
        }


    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main();
            }
        });
    }
}
