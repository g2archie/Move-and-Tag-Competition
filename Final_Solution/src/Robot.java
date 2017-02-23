/**
 * Created by g2arc on 2017/2/23.
 */
public class Robot {
    double x;
    double y;
    String status;
    double distanceAcquiredSinceLastJump;
    double remainingDistanceToClosestTarget; //用来存储到下一个最近点的距离,筛选的时候也要用到
    int closest; //最近的机器人的编号

    public Robot(double x, double y, String status){
        this.x=x;
        this.y=y;
        this.status=status;
        this.distanceAcquiredSinceLastJump=0;
        this.remainingDistanceToClosestTarget=1000000;
        this.closest=-1;

    }
    public double getx(){return this.x;}
    public double gety(){return this.y;}
    public String getstatus(){return this.status;}
    public double getdistanceAcquiredSinceLastJump(){return this.distanceAcquiredSinceLastJump;}
    public double getRemainingDistanceToClosestTarget(){
        return this.remainingDistanceToClosestTarget;
    }


    public void setclosest(int n){
        this.closest=n;
    }
    public void setRemainingDistanceToClosestTarget(double n){
        this.remainingDistanceToClosestTarget=n;
    }
    public void setDistanceAcquiredSinceLastJump(double n){
        this.distanceAcquiredSinceLastJump=n;
    }
}
