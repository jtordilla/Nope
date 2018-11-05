//use for testing near crater w/marker advantage

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous
public class CraterAuto extends BasicCraterAuto{

    @Override
    public void start(){

        detachAndTurn();

        forwardDistance(fastSpeed, ticksPerInch(500));
        leftDistance(normalSpeed, ticksPerInch(128));
        forwardDistance(fastSpeed, ticksPerInch(700));
        leftDistance(normalSpeed, ticksPerInch(40));
        forwardDistance(fastSpeed, ticksPerInch(1000));
        dropMarker();
        leftDistance(normalSpeed, ticksPerInch(240));
    }
}
