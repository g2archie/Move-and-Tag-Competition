import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.logging.StreamHandler;

/**
 * Created by harrychen on 17/2/20.
 */
public class FT {

private final double[] testarray;
private final double[] coordinates;
private final int[] index;

public FT(double[] testarray,double[] coordinates,int[] index)
{
    this.testarray=testarray;
    this.coordinates=coordinates;
    this.index=index;
}


    public void getResult(){

        //test
        ArrayList<Robot> robotlist=new ArrayList<Robot>();


        for (int i=0;i<testarray.length/2;i++){
            robotlist.add(new Robot(testarray[i*2],testarray[i*2+1],"sleeping"));
        }
        robotlist.get(0).status="awake";




        rmp newrmp=new rmp(coordinates,index);


        int totalnumber=robotlist.size();//robot的总数

        //初始化target Arraylist
        ArrayList target=new ArrayList();
        int temp[]=new int[2];
        temp[0]=0;
        temp[1]=-1;
        target.add(temp);

//        System.out.println(((int[])target.get(0))[0]);
//        System.out.println(target.size());

        //////结束initialize,开始for loop
        for (int x=1; x<totalnumber;x++){  //最外层loop (注意从1开始遍历) 编号0是初始的机器人

            //calculate distance to nearest robot for all awake robots
            for (int i=0;i<totalnumber;i++){
                if (robotlist.get(i).getstatus()=="awake"){ //对于每个awake的机器人:再遍历一遍机器人list
                    robotlist.get(i).setclosest(-1);
                    robotlist.get(i).setRemainingDistanceToClosestTarget(1000000);
                    for (int j=0;j<totalnumber;j++){ //遍历所有的sleeping机器人,找出离i最近的那个
                        if (robotlist.get(j).getstatus()=="sleeping"){ //如果这个机器人还没有被唤醒
//                            double d =getDistance(i,j)-robotlist.get(i).getdistanceAcquiredSinceLastJump(); //使用getDistance function
//
//                            double temp1= (robotlist.get(i).x - robotlist.get(j).x) * (robotlist.get(i).x - robotlist.get(j).x) +(robotlist.get(i).y - robotlist.get(j).y) *(robotlist.get(i).y - robotlist.get(j).y);

                            double temp1=newrmp.getShortestDistance(i,j);
//                            System.out.println(temp1);
                            double d=temp1-robotlist.get(i).getdistanceAcquiredSinceLastJump();

                            if (d < robotlist.get(i).getRemainingDistanceToClosestTarget()){  //更新去下一个点剩下的距离
                                robotlist.get(i).setRemainingDistanceToClosestTarget(d);
                                robotlist.get(i).setclosest(j);//将离i最近的点设置为j
                            }
                        }
                    }
                }
            }


            // which robot will reach it's target first
            int nextRobotToReachTarget = -1;  //是一个机器人的编号,这个机器人到他的目标最快
            double d=1000000;
            for (int i=0;i<totalnumber;i++){ //遍历所有的robot
                if (robotlist.get(i).getstatus()=="awake" && robotlist.get(i).getRemainingDistanceToClosestTarget()<d){
                    d=robotlist.get(i).getRemainingDistanceToClosestTarget();//更新d
                    nextRobotToReachTarget=i; //更新最近的下一个目标
                }
            }
            if(d==1000000) break;

            // 更新 acquired distance for all awake robots   ??? 这里的d来自于 nextRobotToReachTarget到离他最近的机器人的距离
            for (int i=0;i<totalnumber;i++){
                if (robotlist.get(i).getstatus()=="awake"){
                    robotlist.get(i).setDistanceAcquiredSinceLastJump(robotlist.get(i).getdistanceAcquiredSinceLastJump()+d);
                }
            }

            //reset robot that made the jump
            robotlist.get(nextRobotToReachTarget).setDistanceAcquiredSinceLastJump(0);
            robotlist.get(nextRobotToReachTarget).x=robotlist.get(robotlist.get(nextRobotToReachTarget).closest).x;
            robotlist.get(nextRobotToReachTarget).y=robotlist.get(robotlist.get(nextRobotToReachTarget).closest).y;

            //add the two robots to the target list in numerical order

            if (robotlist.get(nextRobotToReachTarget).closest<nextRobotToReachTarget){
                int[] aa=new int[2];
                aa[0]=robotlist.get(nextRobotToReachTarget).closest;
                aa[1]=-1;
                target.add(aa);
                aa=new int[2];
                aa[0]=nextRobotToReachTarget;
                aa[1]=-1;
                target.add(aa);
            }
            else {

                int[] aa=new int[2];
                aa[0]=nextRobotToReachTarget;

                aa[1]=-1;
                target.add(aa);

                aa=new int[2];
                aa[0]=robotlist.get(nextRobotToReachTarget).closest;
                aa[1]=-1;
                target.add(aa);
            }


//            System.out.println(((int[])target.get(1))[0]);


            // update newly awakened robot
            robotlist.get(robotlist.get(nextRobotToReachTarget).closest).status="awake";
//            System.out.println(robotlist.get(0).status);
            boolean update=false;
//            System.out.println(robotlist.get(nextRobotToReachTarget).closest); 1,2


            //update the target list
            for (int i=0;i<target.size();i++){
                if (((int[])target.get(i))[0]==nextRobotToReachTarget && ((int[])target.get(i))[1]==-1 && update==false){
                    ((int[])target.get(i))[1]=robotlist.get(nextRobotToReachTarget).closest;
                    update=true;
//                    System.out.println("yes");
                }
            }
        }


        ArrayList final1=new ArrayList();
        for (int i=0;i<target.size();i++){
            int temp1=((int[])target.get(i))[0];
            int temp2=((int[])target.get(i))[1];
            if (temp2!=-1){
                final1.add(target.get(i));

            }
        }

        for (int i=0;i<final1.size();i++){
            int temp3=((int[])final1.get(i))[0];
            int temp4=((int[])final1.get(i))[1];
            //System.out.print(temp3+",");
            //System.out.println(temp4);
        }
        newrmp.printResult(final1,totalnumber);
        //////最后返回final1这个Arraylist

        return ;


    }


}



