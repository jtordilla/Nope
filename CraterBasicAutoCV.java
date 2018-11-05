//use for testing OpenCV near crater

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous
public class CraterBasicAutoCV extends CraterBestAutoCV {
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
                forwardDistance(fastSpeed, ticksPerInch(1000));
                objective = true;
            }
        }
    }

    @Override
    public void rightGold(){

        forwardDistance(slowSpeed, ticksPerInch(500));
        backDistance(normalSpeed, ticksPerInch(250));
        leftDistance(normalSpeed, ticksPerInch(40));
        forwardDistance(normalSpeed, ticksPerInch(1500));

    }

    @Override
    public void leftGold(){

        forwardDistance(slowSpeed, ticksPerInch(500));
        backDistance(normalSpeed, ticksPerInch(250));
        leftDistance(normalSpeed, ticksPerInch(40));
        forwardDistance(normalSpeed, ticksPerInch(1500));

    }

    @Override
    public void centerGold(){

        forwardDistance(slowSpeed, ticksPerInch(1500));

    }
}
