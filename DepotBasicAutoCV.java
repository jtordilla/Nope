//use for testing OpenCV near Depot

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous
public class DepotBasicAutoCV extends DepotBestAutoCV {

    @Override
    public void start(){

        boolean objective = false;

        detachAndTurn();

        while(!objective) {

            if (detector.getXPosition() >= 325) {
                while(detector.getXPosition() >= 325){
                    turnRight(slowSpeed);
                }
                rightGold();
                objective = true;
            }
            else if(detector.getXPosition() <= 225){
                while(detector.getXPosition() <= 225){
                    turnLeft(slowSpeed);
                }
                leftGold();
                objective = true;
            }

            else if (detector.getAligned()) {
                centerGold();
                objective = true;
            }else{
                forwardDistance(fastSpeed, ticksPerInch(700));
                objective = true;
            }
        }
    }

    @Override
    public void rightGold(){

        forwardDistance(slowSpeed, ticksPerInch(500));
        leftDistance(normalSpeed, ticksPerInch(86));
        forwardDistance(normalSpeed, ticksPerInch(300));
        dropMarker();

    }

    @Override
    public void leftGold(){

        forwardDistance(slowSpeed, ticksPerInch(500));
        rightDistance(normalSpeed, ticksPerInch(93));
        forwardDistance(normalSpeed, ticksPerInch(400));
        dropMarker();

    }

    @Override
    public void centerGold(){

        forwardDistance(slowSpeed, ticksPerInch(500));
        forwardDistance(fastSpeed, ticksPerInch(100));
        dropMarker();
    }
}
