/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robot.motion.planning;

import java.awt.geom.Line2D;
import static java.lang.Math.sqrt;



/**
 *
 * @author g2arc
 */
public class RobotMotionPlanning {

    /**
     * @param args the command line arguments
     */
    static double[] coordinates={-1.5,1.5,-1,0,5,0,4.5,3.5,4.6,-3,0,1,2,3,4,1,4,10,0,10,4,0,2,2,0,0,0,-10,4,-10};
    static int[] index={10,20,30};

    private static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) {
  
    double x1=-1.5; double y1=1.5;
    double x2=4.5;double y2=3.5;
    System.out.println(Polygon_Intersections(x1,y1,x2,y2,20,29,coordinates));
    System.out.println("length is "+ coordinates.length);
//    System.out.println(findNextIntersectionVerticle_point1_x(x1,y1,x2,y2,0,9,polygon_coordinates));  
//    int index1=findNextIntersectionVerticle_point1_x(x1,y1,x2,y2,0,9,polygon_coordinates);
//    System.out.println(polygon_coordinates[index1]+" "+polygon_coordinates[index1+1]);  
//    System.out.println(findNextIntersectionVerticle_point2_x(x1,y1,x2,y2,0,9,polygon_coordinates));  
//    int index2=findNextIntersectionVerticle_point2_x(x1,y1,x2,y2,0,9,polygon_coordinates);
//    System.out.println(polygon_coordinates[index2]+" "+polygon_coordinates[index2+1]);  
    System.out.println("Distance is "+ distance(-1,0,5,0));
    System.out.println("Testing blocked function");
   if(isBlocked(x1,y1,x2,y2)==1)
   {
       System.out.println("haha blocked!");
   }
   floyd();
   //isBlocked(0,1,5,0);
}
    
//    public static int findNextIntersectionVerticle_point1_x(double x1,double y1,double x2,double y2,int polygon_vertex_from,int polygon_vertex_to,double[] polygon_coordinates)
//    {
//        if (Polygon_Intersections(x1,y1,x2,y2,polygon_vertex_from,polygon_vertex_to,polygon_coordinates)==0)
//        {
//            return 0;
//        }
//        else
//        {
//            int i;
//        if (Line2D.linesIntersect(x1, y1, x2, y2, polygon_coordinates[polygon_vertex_from], polygon_coordinates[polygon_vertex_from+1], polygon_coordinates[polygon_vertex_to-1], polygon_coordinates[polygon_vertex_to]))
//        {
//           return(polygon_vertex_from);
//        }
//       for(i=polygon_vertex_from;i<polygon_vertex_to-2;i+=2)
//        {
//            if (Line2D.linesIntersect(x1, y1, x2, y2, polygon_coordinates[i], polygon_coordinates[i+1], polygon_coordinates[i+2], polygon_coordinates[i+3]))
//            {
//               return(i);
//            }
//        }
//        }
//        return 0;
//    }
//      public static int findNextIntersectionVerticle_point2_x(double x1,double y1,double x2,double y2,int polygon_vertex_from,int polygon_vertex_to,double[] polygon_coordinates)
//    {
//        if (Polygon_Intersections(x1,y1,x2,y2,polygon_vertex_from,polygon_vertex_to,polygon_coordinates)==0)
//        {
//            return 0;
//        }
//        else
//       {
//         int i;
//         if (Line2D.linesIntersect(x1, y1, x2, y2, polygon_coordinates[polygon_vertex_from], polygon_coordinates[polygon_vertex_from+1], polygon_coordinates[polygon_vertex_to-1], polygon_coordinates[polygon_vertex_to]))
//         {
//           return(polygon_vertex_to-1);
//         }
//         for(i=polygon_vertex_from;i<polygon_vertex_to-2;i+=2)
//         {
//            if (Line2D.linesIntersect(x1, y1, x2, y2, polygon_coordinates[i], polygon_coordinates[i+1], polygon_coordinates[i+2], polygon_coordinates[i+3]))
//            {
//               return(i+2);
//            }
//          }   
//        }
//        return 0;
//    }
      //If the intersection point is at one of the vertexs of the polygon, then it returns 0
    public static int Polygon_Intersections(double x1,double y1,double x2,double y2,int polygon_vertex_from,int polygon_vertex_to,double[] polygon_coordinates)
    {
        int intersections=0; int i;

        if (Line2D.linesIntersect(x1, y1, x2, y2, polygon_coordinates[polygon_vertex_from], polygon_coordinates[polygon_vertex_from+1], polygon_coordinates[polygon_vertex_to-1], polygon_coordinates[polygon_vertex_to]))
        {
            intersections++;
            System.out.println(polygon_coordinates[polygon_vertex_from]+" " +polygon_coordinates[polygon_vertex_from+1]+" "+ polygon_coordinates[polygon_vertex_to-1]+" "+ polygon_coordinates[polygon_vertex_to]);
        }
        for(i=polygon_vertex_from;i<polygon_vertex_to-2;i+=2)
        {
            if (Line2D.linesIntersect(x1, y1, x2, y2, polygon_coordinates[i], polygon_coordinates[i+1], polygon_coordinates[i+2], polygon_coordinates[i+3]))
            {
               intersections++;
               System.out.println(polygon_coordinates[i]+" " +polygon_coordinates[i+1]+" "+ polygon_coordinates[i+2]+" "+ polygon_coordinates[i+3]);
            }
        }
      if(isIntersectionAtVerticle(x1,y1,x2,y2,polygon_vertex_from,polygon_vertex_to,polygon_coordinates)==1)
      {intersections-=2;}
        return intersections;
    }
    
public static int isIntersectionAtVerticle(double x1,double y1,double x2,double y2,int polygon_vertex_from,int polygon_vertex_to,double[] polygon_coordinates)
{
    for(int i=polygon_vertex_from;i<polygon_vertex_to;i+=2)
    {
        if((polygon_coordinates[i]==x1)&&(polygon_coordinates[i+1]==y1)) {return 1;}
        
        if((polygon_coordinates[i]==x2)&&(polygon_coordinates[i+1]==y2)) {return 1;}
    }
    return 0;
}

public static double distance(double x1,double y1,double x2,double y2)
{
    return sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
}
public static int isBlocked(double x1,double y1,double x2,double y2)
{
    if(index[0]!=-1)
    {
        int length=index.length;
        for(int i=0;i<length-1;i++)
        {
            if (Polygon_Intersections(x1,y1,x2,y2,index[i],index[i+1]-1,coordinates)>0)
            {
                System.out.println("Blocked!");
                return 1;
            }
        }
        return 0;
    }
    else
    
    {
        return 0;
    }
}
public static int isEdge(double x1,double y1,double x2,double y2)
{
    int length=index.length;
    for(int i=0;i<length-1;i++)
    {
        for(int j=index[i];j<index[i+1]-1;j+=2)
        {
            if (j==index[i]){
                if((x1==coordinates[j])&&(y1==coordinates[j+1])&&(x2==coordinates[index[i+1]-2])&&(y2==coordinates[index[i+1]-1]))
                { return 1;}
                if((x1==coordinates[j])&&(y1==coordinates[j+1])&&(x2==coordinates[j+2])&&(y2==coordinates[j+3]))
                {return 1;}
            } 
            else
            {
                if((x1==coordinates[j])&&(y1==coordinates[j+1])&&(x2==coordinates[j+2])&&(y2==coordinates[j+3]))
                {return 1;}
            }
        }
    }
    return 0;
}
public static void findPath(int p1,int p2,int path[][])
{
    int point=p1;
    while(point!=p2)
    {
        System.out.print(point+"-");
        point=path[point][p2];
    }
    
    System.out.println(p2);
}
public static void floyd() {

    // 初始化
    int length=coordinates.length/2;
    int index_polygon=index[0];
    int points[]=new int[length];
   
    for (int i=0;i<length;i++)
    {
        points[i]=i;
    }
    double [][] mMatrix=new double[length][length];
    int[][] path=new int[length][length];
    double[][] dist=new double[length][length];
    for (int i =0;i<length;i++){
        for(int j=i+1;j<length;j++){
            if(isBlocked(coordinates[i*2],coordinates[i*2+1],coordinates[j*2],coordinates[j*2+1])==1&&isEdge(coordinates[i*2],coordinates[i*2+1],coordinates[j*2],coordinates[j*2+1])==0)
            {
                mMatrix[i][j]=INF;
                mMatrix[j][i]=INF;
            }
            else
            {
                mMatrix[i][j]=distance(coordinates[i*2],coordinates[i*2+1],coordinates[j*2],coordinates[j*2+1]);
                mMatrix[j][i]=distance(coordinates[i*2],coordinates[i*2+1],coordinates[j*2],coordinates[j*2+1]);
            }
        }
    }
    for (int i=0;i<length;i++)
    {
        for(int j=0;j<length;j++)
        {
         if(mMatrix[i][j]!=INF)
         {System.out.println("From "+i+ " to "+j+" is "+mMatrix[i][j]);}
            //System.out.println("If not blocked, distance is "+ distance(coordinates[i*2],coordinates[i*2+1],coordinates[j*2],coordinates[j*2+1]));
        }
    }

    for (int i = 0; i <length; i++) {
        for (int j = 0;j < length; j++) {
            dist[i][j] = mMatrix[i][j];    // "顶点i"到"顶点j"的路径长度为"i到j的权值"。
            if(dist[i][j]!=INF)
            {path[i][j] = j;}
            else {path[i][j]=-1;}// "顶点i"到"顶点j"的最短路径是经过顶点j。
        }
  }
//        for (int i=0; i<length;i++)
//    {
//        for(int j=0;j<length;j++)
//        {
//            System.out.print(path[i][j]+" ");
//        }
//        System.out.println();
//    }
//
    // 计算最短路径
    for (int k = 0; k < length; k++) {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {

                // 如果经过下标为k顶点路径比原两点间路径更短，则更新dist[i][j]和path[i][j]
                double tmp=dist[i][k]+dist[k][j];
                if (dist[i][j] > tmp) {
                    // "i到j最短路径"对应的值设，为更小的一个(即经过k)
                    dist[i][j] = tmp;
                    // "i到j最短路径"对应的路径，经过k
                    path[i][j] = path[i][k];
                }
            }
        }
    }
//
    // 打印floyd最短路径的结果
    System.out.printf("floyd: \n");
    for (int i = 0; i < length; i++) {
        for (int j = 0; j <length; j++)
           // System.out.println("From "+i+" to "+j " is "+ dist[i][i]);
             System.out.println("From "+i+" to "+j+" is "+ dist[i][j]);
        System.out.printf("\n");
    }
    for (int i=0; i<length;i++)
    {
        for(int j=0;j<length;j++)
        {
            System.out.print(path[i][j]+" ");
        }
        System.out.println();
    }
    //find path
    
//    System.out.println(distance(-1.5,1.5,0,1));
//    System.out.println(distance(0,1,2,2));
//    System.out.println(distance(2,2,5,0));
//    System.out.println(distance(-1.5,1.5,0,1)+distance(0,1,2,2)+distance(2,2,5,0));
    findPath(12,8,path);
}
}
