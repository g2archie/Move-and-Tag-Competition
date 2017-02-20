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
        ArrayList coordinates = new ArrayList();
        String temp = "";
        for (int i = 0; i < text.length(); i++){
//            while(text.charAt(i) != '#'){
                if (text.charAt(i) == '('){
                    int a = i + 1;
                    String s = "";
                    while(text.charAt(a) != ','){
                        s = s + text.charAt(a);
                        a++;
                    }
                    temp = s;

                }else if (text.charAt(i) == ')'){
                    int b = i - 1;
                    String s1 = "";
                    while(text.charAt(b) != ','){
                        s1 = text.charAt(b) + s1;
                        b--;
                    }
                    temp = temp + ',' + s1;
                    coordinates.add(temp);
                    temp = "";
                }
//            }
        }



        System.out.print(coordinates);

        Container c = getContentPane();
        setBackground(Color.white);
        Graph g = new Graph();
        c.setLayout(new BorderLayout());
        c.add(g,BorderLayout.CENTER);
        setSize(500,500);
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
