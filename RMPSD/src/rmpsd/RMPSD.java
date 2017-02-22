/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmpsd;


/**
 *
 * @author g2arc
 */
public class RMPSD {
    

    private static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) {
//      double[] coordinates={-1.5,1.5,-1,0,5,0,4.5,3.5,4.6,-3,0,1,2,3,4,1,4,10,0,10,4,0,2,2,0,0,0,-10,4,-10};
//      int[] index={10,20,30};
      double[] coordinates={-1.5,1.5,-1,0,5,0,4.5,3.5,4.6,-3};
      int[] index={-1};
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
}
}
