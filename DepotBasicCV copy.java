//use for testing OpenCV near Depot

package org.firstinspires.ftc.teamcode;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.DogeCV;
import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class DepotBasicCV extends CraterBasicCV {

    @Override
    public void runOpMode(){
        telemetry.addData("Status", "DogeCV 2018.0 - Gold Align Example");

        // Set up detector
        detector = new GoldAlignDetector(); // Create detector
        detector.init(hardwareMap.appContext, CameraViewDisplay.getInstance()); // Initialize it with the app context and camera
        detector.useDefaults(); // Set detector to use default settings

        // Optional tuning
        detector.alignSize = 100; // How wide (in pixels) is the range in which the gold object will be aligned. (Represented by green bars in the preview)
        detector.alignPosOffset = 0; // How far from center frame to offset this alignment zone.
        detector.downscale = 0.4; // How much to downscale the input frames

        detector.areaScoringMethod = DogeCV.AreaScoringMethod.MAX_AREA; // Can also be PERFECT_AREA
        //detector.perfectAreaScorer.perfectArea = 10000; // if using PERFECT_AREA scoring
        detector.maxAreaScorer.weight = 0.005; //

        detector.ratioScorer.weight = 5; //
        detector.ratioScorer.perfectRatio = 1.0; // Ratio adjustment

        detector.enable(); // Start the detector!

        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        climber = hardwareMap.get(DcMotor.class, "climber");

        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        climber.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        servoTest = hardwareMap.get(Servo.class, "servoTest");

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        while (opModeIsActive()) {
            telemetry.addData("Status", "Running");
            telemetry.update();
            telemetry.addData("IsAligned", detector.getAligned()); // Is the bot aligned with the gold mineral?
            telemetry.addData("X Pos", detector.getXPosition()); // Gold X position.

            //start commands

            waitForStart();
            detachAndTurn();

            boolean objective = false;

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

            //end commands

            telemetry.addData("Servo Position", servoTest.getPosition());

            telemetry.addData("Motor Power", leftBack.getPower());
            telemetry.addData("Motor Power", rightBack.getPower());
            telemetry.addData("Motor Power", leftFront.getPower());
            telemetry.addData("Motor Power", rightFront.getPower());

            telemetry.addData("Motor Power", climber.getPower());
        }

        detector.disable();
        stopRobot();

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

