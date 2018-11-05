//use for testing near depot

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous
public class BasicDepotAuto extends BasicCraterAuto{
    @Override
    public void start(){

        detachAndTurn();

        forwardDistance(fastSpeed, ticksPerInch(800));
    }
}
