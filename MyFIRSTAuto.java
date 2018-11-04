package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class MyFIRSTAuto extends MyFIRSTOpMode {
    public void main() throws InterruptedException {

        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");

        servoTest = hardwareMap.get(Servo.class, "servoTest");

        waitForStart();

        servoTest.setPosition(0.5);

        waitSeconds(3000);
        driveForwardTime(normalDrive, 5000);
        waitSeconds(3000);
        dropMarker();
        turnRight(slowDrive, 4000);
        waitSeconds(3000);
        driveForwardTime(normalDrive, 5000);

    }

    double slowDrive = 0.4;
    double normalDrive = 0.7;
    double fastDrive = 1.0;

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

    public void dropMarker(){
        servoTest.setPosition(1.0);
    }

    public void stopDrive(){
        drive(0);
    }

    public void waitSeconds(long time) throws InterruptedException{
        Thread.sleep(time);
    }
}
