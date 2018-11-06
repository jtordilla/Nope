package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class AutoTemplate extends DriverControl {
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

            //end commands

            telemetry.addData("Servo Position", servoTest.getPosition());

            telemetry.addData("Motor Power", leftBack.getPower());
            telemetry.addData("Motor Power", rightBack.getPower());
            telemetry.addData("Motor Power", leftFront.getPower());
            telemetry.addData("Motor Power", rightFront.getPower());

            telemetry.addData("Motor Power", climber.getPower());
        }
    }

    double slowSpeed = 0.4;
    double normalSpeed = 0.7;
    double fastSpeed = 1.0;
    double dropPosition = 1.0; //changable

    public void driveForwardTime(double power, long time) throws InterruptedException{
        leftBack.setPower(power);
        rightBack.setPower(-power);
        leftFront.setPower(power);
        rightFront.setPower(-power);
        Thread.sleep(time);
    }

    public void drive(double power){
        leftBack.setPower(power);
        rightBack.setPower(-power);
        leftFront.setPower(power);
        rightFront.setPower(-power);
    }

    public void turnRight(double power, long time) throws InterruptedException{
        leftBack.setPower(-power);
        rightBack.setPower(power);
        leftFront.setPower(-power);
        rightBack.setPower(power);
        Thread.sleep(time);
    }

    public void stopDrive(){
        drive(0);
    }

    public void waitSeconds(long time) throws InterruptedException{
        Thread.sleep(time);
    }

    public void turnRight(double power) {
        leftBack.setPower(power);
        rightBack.setPower(power);
        leftFront.setPower(power);
        rightFront.setPower(power);
    }

    public void turnLeft(double power) {
        leftBack.setPower(-power);
        rightBack.setPower(power);
        leftFront.setPower(-power);
        rightFront.setPower(power);
    }

    public void detachClimber(int distance) throws InterruptedException{

        climber.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        climber.setTargetPosition(distance);
        climber.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        moveClimber(normalSpeed);
        Thread.sleep(4000);

        while (climber.isBusy()) {
            //waits
        }

        stopRobot();

        climber.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void forwardDistance(double power, int distance) {

        //reset encoders

        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //set target position

        leftBack.setTargetPosition(distance);
        rightBack.setTargetPosition(distance);
        leftFront.setTargetPosition(distance);
        rightFront.setTargetPosition(distance);

        //set run to position

        leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //set power

        driveForward(power);

        while (leftBack.isBusy() && rightBack.isBusy() && leftFront.isBusy() && rightFront.isBusy()) {
            //waits
        }

        //stops and reverts to normal

        stopRobot();

        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void backDistance(double power, int distance) {

        //reset encoders

        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //set target position

        leftBack.setTargetPosition(distance);
        rightBack.setTargetPosition(distance);
        leftFront.setTargetPosition(distance);
        rightFront.setTargetPosition(distance);

        //set run to position

        leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //set power

        driveBack(power);

        while (leftBack.isBusy() && rightBack.isBusy() && leftFront.isBusy() && rightFront.isBusy()) {
            //waits
        }

        //stops and reverts to normal

        stopRobot();

        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void leftDistance(double power, int distance) {

        //reset encoders

        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //set target position

        leftBack.setTargetPosition(-distance);
        rightBack.setTargetPosition(distance);
        leftFront.setTargetPosition(-distance);
        rightFront.setTargetPosition(distance);

        //set run to position

        leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //set power

        driveForward(power);

        while (leftBack.isBusy() && rightBack.isBusy() && leftFront.isBusy() && rightFront.isBusy()) {
            //waits
        }

        //stops and reverts to normal

        stopRobot();

        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void rightDistance(double power, int distance) {

        //reset encoders

        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //set target position

        leftBack.setTargetPosition(distance);
        rightBack.setTargetPosition(-distance);
        leftFront.setTargetPosition(distance);
        rightFront.setTargetPosition(-distance);

        //set run to position

        leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //set power

        driveForward(power);

        while (leftBack.isBusy() && rightBack.isBusy() && leftFront.isBusy() && rightFront.isBusy()) {
            //waits
        }

        //stops and reverts to normal

        stopRobot();

        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void driveForward(double power) {
        leftBack.setPower(normalSpeed);
        rightBack.setPower(-normalSpeed);
        leftFront.setPower(normalSpeed);
        rightFront.setPower(-normalSpeed);
    }

    public void driveBack(double power) {
        leftBack.setPower(-normalSpeed);
        rightBack.setPower(normalSpeed);
        leftFront.setPower(-normalSpeed);
        rightFront.setPower(normalSpeed);
    }

    public void moveClimber(double power) {
        climber.setPower(-power);
    }

    public void stopRobot() {
        leftBack.setPower(0);
        rightBack.setPower(0);
        leftFront.setPower(0);
        rightFront.setPower(0);
    }

    public void dropMarker() {
        servoTest.setPosition(dropPosition);
    }

    public int ticksPerInch(int inchesDecimal) {
        return inchesDecimal * 28;
    }

    public int seconds(int seconds) {
        return seconds * 1000;
    }

    public void detachAndTurn() throws InterruptedException{

        detachClimber(ticksPerInch(120)); //pretend there's a decimal point
        leftDistance(normalSpeed, ticksPerInch(120)); //90 degrees
    }
}
