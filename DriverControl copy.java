package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class DriverControl extends BasicOpMode_Linear{
    
    public DcMotor leftBack, rightBack, leftFront, rightFront, intakeMove, pinion, intake, climber;
    public Servo servoTest;

    @Override

    public void runOpMode() throws InterruptedException{
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");

        intakeMove = hardwareMap.get(DcMotor.class, "intakeMove");

        pinion = hardwareMap.get(DcMotor.class, "pinion");
        intake = hardwareMap.get(DcMotor.class, "intake");

        pinion.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        climber.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        climber = hardwareMap.get(DcMotor.class, "climber");

        servoTest = hardwareMap.get(Servo.class, "servoTest");

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        double left = 0;
        double right = 0;

        double intakeTrigger = 1.0;

        double inPower = 1.0;

        double Pin = 1.0;

        double intakePower = 0.5;

        double climbPower = 1.0;

        double stopPower = 0;

        while(opModeIsActive()){

            pinion.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            climber.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            left = this.gamepad1.left_stick_y;
            right = -this.gamepad1.right_stick_y;

            leftBack.setPower(left);
            rightBack.setPower(right);
            leftFront.setPower(left);
            rightFront.setPower(right);

            //stop

            if(gamepad1.back){
                intakeStop();
            }else if(gamepad1.start) {
                pinionStop();
            }

            //intake

            else if(gamepad1.left_bumper && !gamepad1.right_bumper){
                intake.setPower(intakePower);
            }
            else if(gamepad1.right_bumper && !gamepad1.left_bumper){
                intake.setPower(-intakePower);
            }else{
                intakeStop();
            }

            //intake move - change to trigger

            if(gamepad1.right_trigger > 0.5){
                intakeMove.setPower(inPower);
            }else if(gamepad1.left_trigger > 0.5){
                intakeMove.setPower(-inPower);
            }else{
                intakeMoveStop();
            }

            //pinion move - rotates once

            if(gamepad1.b && !gamepad1.a){
                pinion.setTargetPosition(1120);
                pinion.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                pinion.setPower(Pin);
                while(pinion.isBusy()){

                }
                pinionStop();
                pinion.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            }else if(gamepad1.a && !gamepad1.b){
                pinion.setTargetPosition(1120);
                pinion.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                pinion.setPower(-Pin);
                while(pinion.isBusy()){

                }
                pinionStop();
                pinion.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            }else{
                pinionStop();
            }

            //climber - rotates once

            if(gamepad1.dpad_up && !gamepad1.dpad_down){
                climber.setTargetPosition(1120);
                climber.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                climber.setPower(-climbPower);
                while(climber.isBusy()){

                }
                climbStop();
                climber.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            }else if(gamepad1.dpad_down && !gamepad1.dpad_up){
                climber.setTargetPosition(1120);
                climber.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                climber.setPower(climbPower);
                while(climber.isBusy()){

                }
                climbStop();
                climber.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            }else{
                climbStop();
            }

            //motor telemetry setup

            telemetry.addData("Servo Position", servoTest.getPosition());
            telemetry.addData("Target Power", left);
            telemetry.addData("Target Power", right);

            telemetry.addData("Motor Power", leftBack.getPower());
            telemetry.addData("Motor Power", rightBack.getPower());
            telemetry.addData("Motor Power", leftFront.getPower());
            telemetry.addData("Motor Power", rightFront.getPower());

            telemetry.addData("Motor Power", intakeMove.getPower());
            telemetry.addData("Motor Power", pinion.getPower());
            telemetry.addData("Motor Power", intake.getPower());
            telemetry.addData("Motor Power", climber.getPower());

            telemetry.addData("Status", "Running");
            telemetry.update();

        }
    }

    public void intakeStop(){
        intake.setPower(0);
    }

    public void pinionStop(){
        pinion.setPower(0);
    }

    public void intakeMoveStop(){
        intakeMove.setPower(0);
    }

    public void climbStop(){
        climber.setPower(0);
    }
}
