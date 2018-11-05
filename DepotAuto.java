//use for testing near depot w/marker advantage

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous
public class DepotAuto extends BasicDepotAuto{
    @Override
    public void start(){

        detachAndTurn();
        forwardDistance(fastSpeed, ticksPerInch(550));
        dropMarker();
        rightDistance(normalSpeed, ticksPerInch(173));
        forwardDistance(fastSpeed, ticksPerInch(1500));
    }
}
