//use for testing near crater

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous
public class BasicCraterAuto extends CraterBestAutoCV{
    @Override
    public void start(){

        detachAndTurn();

        forwardDistance(fastSpeed, ticksPerInch(1500));
    }
}
