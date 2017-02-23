/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmpsd;

import java.util.ArrayList;


/**
 *
 * @author g2arc
 */
public class RMPSD {
    

    private static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) {
//      double[] coordinates={-1.5,1.5,-1,0,5,0,4.5,3.5,4.6,-3,0,1,2,3,4,1,4,10,0,10,4,0,2,2,0,0,0,-10,4,-10};
//      int[] index={10,20,30};
     // double[] coordinates={-1.5,1.5,-1,0,5,0,4.5,3.5,4.6,-3};
     // int[] index={-1};
     double[] coordinates={0.0, 1.0, 2.0, 0.0, 3.0, 5.0, 6.0, 2.0, 9.0, 0.0, 1.0, 2.0, 1.0, 4.0, 3.0, 4.0, 3.0, 2.0, 8.0, 1.0, 4.0, 1.0, 4.0, 4.0, 5.0, 2.0};
     int[] index={10,18,21};
      rmp newrmp=new rmp(coordinates,index);
      //newrmp.findPath(0, 2);
      System.out.println(newrmp.getShortestDistance(0,4));
      int route[]=newrmp.getShortestRoute(0, 4);
      for(int i=0;i<route.length;i++)
      {
          if(route[i]!=-1)
          {System.out.print(route[i]+" ");}
          else{break;}
      }
      System.out.println();
      double coordinate[]=newrmp.getCoordinates(1);
      for(int i=0;i<coordinate.length;i++)
      {
          System.out.print(coordinate[i]+" ");
      }
      System.out.println();
      ArrayList<int[]> myArray= new ArrayList<int[]>();
      int test1[]=new int[2];
      int test2[]=new int[2];
      int test3[]=new int[2];
      int test4[]=new int[2];
      test1[0]=0;
      test1[1]=1;
      test2[0]=0;
      test2[1]=2;
      test3[0]=1;
      test3[1]=3;
      test4[0]=3;
      test4[1]=4;
      myArray.add(test1);
      myArray.add(test2);
      myArray.add(test3);
      myArray.add(test4);
      newrmp.printResult(myArray,4);
     // System.out.println(myArray.get(3)[1]);
}
    
}
