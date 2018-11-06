//use for testing near depot w/marker advantage

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class DepotAuto extends AutoTemplate{
    @Override
    public void runOpMode() throws InterruptedException {

        //motor setup

        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        climber = hardwareMap.get(DcMotor.class, "climber");
        //encoder setup

        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        climber.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //telemetry and servo setup

        servoTest = hardwareMap.get(Servo.class, "servoTest");
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        //code to run

        while (opModeIsActive()) {

            telemetry.addData("Status", "Running");
            telemetry.update();

            //start commands

            waitForStart();
            detachAndTurn();

            forwardDistance(fastSpeed, ticksPerInch(550));
            dropMarker();
            rightDistance(normalSpeed, ticksPerInch(173));
            forwardDistance(fastSpeed, ticksPerInch(1500));

            //end commands

            telemetry.addData("Servo Position", servoTest.getPosition());

            telemetry.addData("Motor Power", leftBack.getPower());
            telemetry.addData("Motor Power", rightBack.getPower());
            telemetry.addData("Motor Power", leftFront.getPower());
            telemetry.addData("Motor Power", rightFront.getPower());

            telemetry.addData("Motor Power", climber.getPower());
        }
    }
}
