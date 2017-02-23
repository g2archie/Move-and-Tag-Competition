

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Visualization extends JPanel{
    //users variables
    //path of the data
//    private final static String path = "/Users/fuxiaofeng/Desktop/questions.txt";
//    private final static String path2 = "/Users/fuxiaofeng/Desktop/solutions.txt";

    private final static String path = "E:\\questions.txt";
    private final static String path2 = "E:\\solutions.txt";
    //selection of the data
    private final static int index = 5;
    private final static int index2 = 2;
    //size of the graph
    private final static int size = 2000;

    //variables
    private static boolean haveObstacle = true;
    private ArrayList <String[]> coordinates = new ArrayList <String[]> ();
    private ArrayList <String[]>obstacle = new ArrayList <String[]>();
    private ArrayList <ArrayList> obstacles = new ArrayList <ArrayList> ();
    private ArrayList <String[]> solution = new ArrayList<String[]>();
    private ArrayList <ArrayList> solutions = new ArrayList<ArrayList>();
    private ArrayList <Double> list = new ArrayList<Double>();
    private ArrayList <Integer> divider =  new ArrayList<Integer>();
    private Double[] listarray;
    private Integer[] dividerarray;

    //initial Image
    public void paint(Graphics g) {
        Image img = createImg();
        g.drawImage(img, 0,0,this);
    }

    //change into double format
    private void encoding(){
        for (int i = 0; i < coordinates.size(); i++){
            list.add(Double.parseDouble(coordinates.get(i)[0]));
            list.add(Double.parseDouble(coordinates.get(i)[1]));
        }

        if (haveObstacle){
            divider.add(list.size());
        }

        for (int i = 0; i < obstacles.size();i++){
            ArrayList<String[]> temp = obstacles.get(i);
            for (int j = 0; j < temp.size();j++){
                list.add(Double.parseDouble(temp.get(j)[0]));
                list.add(Double.parseDouble(temp.get(j)[1]));
            }
            if (obstacles.size()>1 && i != obstacles.size() - 1){
                divider.add(list.size());
            }
        }

        if (haveObstacle){
            divider.add(list.size());
        }

        listarray = (Double[])list.toArray(new Double[0]);
        dividerarray = (Integer[])divider.toArray(new Integer[0]);
    }

    //change the scale
    private double transfer(ArrayList <String[]> temp, int index, int position){
       return Double.parseDouble(temp.get(index)[position]) * 40 + 300;
    }

    //draw initial coordinate in white color and others in red color
    //draw obstacles in blue color
    private Image createImg(){
        BufferedImage bufferedImage = new BufferedImage(size,size,BufferedImage.TYPE_INT_RGB);
        Graphics g = bufferedImage.getGraphics();

        //draw coordinates
        for (int i = 0; i < coordinates.size(); i++){
            int ax = (int)transfer(coordinates, i, 0);
            int ay = (int)transfer(coordinates, i, 1);
            g.setFont(getFont().deriveFont(20f));
            g.drawOval(ax,ay,2,2);
//            g.drawString(".",ax,ay);
            g.setColor(Color.red);
        }

        //draw obstacles
        for (int i = 0; i < obstacles.size(); i++){
            g.setColor(Color.blue);
            Polygon polygon = new Polygon();
             ArrayList <String[]> temp = obstacles.get(i);
             int tempX = 999999, tempY = 999999;
             int initX = 0, initY = 0;

             for (int j = 0; j < temp.size(); j++){
                 int ax = (int)transfer(temp,j,0);
                 int ay = (int)transfer(temp,j,1);
                 polygon.addPoint(ax,ay);

                 if (tempX == 999999 && tempY == 999999){
                     initX = ax;
                     initY = ay;
                     tempX = ax;
                     tempY = ay;
                 }else {
                     g.drawLine(tempX,tempY,ax,ay);
                     tempX = ax;
                     tempY = ay;
                 }

                 if (j == temp.size() - 1){
                     g.drawLine(ax,ay,initX,initY);
                 }
             }
             g.fillPolygon(polygon);
        }

        //draw solutions path
        for (int i = 0; i < solutions.size(); i++) {
            ArrayList<String[]> temp = solutions.get(i);
            int tempX = 999999, tempY = 999999;
            int initX = 0, initY = 0;

            int max = 255;
            int min = 0;
            Random random = new Random();
            Random random2 = new Random();
            Random random3 = new Random();

            Color c = new Color(random.nextInt(max)%(max-min+1) + min,random2.nextInt(max)%(max-min+1) + min,random3.nextInt(max)%(max-min+1) + min);
            g.setColor(c);

            for (int j = 0; j < temp.size(); j++) {
                int ax = (int) transfer(temp, j, 0);
                int ay = (int) transfer(temp, j, 1);

                if (tempX == 999999 && tempY == 999999) {
                    initX = ax;
                    initY = ay;
                    tempX = ax;
                    tempY = ay;
                } else {
                    g.drawLine(tempX, tempY, ax, ay);
                    tempX = ax;
                    tempY = ay;
                }
            }
        }

        return bufferedImage;
    }


    //initial encoding from question and solution
    private Visualization(){
        //**************Question part***************
        FileReader reader = new FileReader();
        ArrayList data = reader.read(path);
        String text = (String)data.get(index);

        //get coordinates
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
                //get obstacles
                getObstacles(i, text);
                obstacles.add(obstacle);
                break;
            }
            //set divider null
            if (i == text.length() - 1){
                divider.add(-1);
                haveObstacle = false;
            }
        }

        //save data into list
        encoding();

        //**************Solution part***************
        FileReader reader2 = new FileReader();
        reader2.read(path2);
        ArrayList data2 = reader.read(path2);
        String text2 = (String)data2.get(index2);

        getSolutions(0, text2);
        solutions.add(solution);
    }

    //encoding solution part
    private void getSolutions(int divide, String text){
        String[] temp2 = new String[2];

        for (int i = divide; i < text.length(); i++) {
            if (text.charAt(i) == '(') {
                int a = i + 1;
                String s = "";

                while (text.charAt(a) != ',') {
                    s = s + text.charAt(a);
                    a++;
                }

                temp2[0] = s;
            } else if (text.charAt(i) == ')') {
                int b = i - 1;
                String s1 = "";

                while (text.charAt(b) != ',') {
                    s1 = text.charAt(b) + s1;
                    b--;
                }

                temp2[1] = s1;
                solution.add(temp2);
                temp2 = new String[2];
            }else if (text.charAt(i) == ';'){
                solutions.add(solution);
                solution = new ArrayList<String[]>();
                getSolutions(i + 1, text);
                break;
            }
        }
    }

    //encoding part2
     private void getObstacles(int divide, String text){
         String[] temp2 = new String[2];

         for (int i = divide; i < text.length(); i++){
             if (text.charAt(i) == '('){
                 int a = i + 1;
                 String s = "";
                 while(text.charAt(a) != ','){
                     s = s + text.charAt(a);
                     a++;
                 }
                 temp2[0] = s;
             }else if (text.charAt(i) == ')'){
                 int b = i - 1;
                 String s1 = "";
                 while(text.charAt(b) != ','){
                     s1 = text.charAt(b) + s1;
                     b--;
                 }
                 temp2[1] = s1;
                 obstacle.add(temp2);
                 temp2 = new String[2];
             }else if (text.charAt(i) == ';'){
                 obstacles.add(obstacle);
                 obstacle = new ArrayList<String[]>();
                 getObstacles(i + 1, text);
                 break;
             }
         }
     }

//    public void test(){
//        Integer[] array = new Integer[10];
//        Integer[] testarray = new Integer[10];
//
//        for (int i = 0; i < testarray.length; i++){
//            if (testarray[i][1] != -1){
//                array[i][0] = testarray[i][0];
//                array[i][1] = testarray[i][1];
//            }
//
//        }
//    }


    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.getContentPane().add(new Visualization());
        frame.setSize(size, size);
        frame.setVisible(true);
    }
}
