package org.firstinspires.ftc.teamcode.dcs15815.opmodes_testing;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.TouchSensor;

@TeleOp(name = "Arm Test", group = "Testing")
public class ArmTestOpMode extends LinearOpMode {
    DcMotor leftMotor, rightMotor;
    DcMotor shoulderLeftMotor, shoulderRightMotor;
    TouchSensor touchSensor;

    public void extend(double p) {
        leftMotor.setPower(p);
        rightMotor.setPower(p);
    }

    public void retract(double p) {
        leftMotor.setPower(-1 * p);
        rightMotor.setPower(-1 * p);
    }

    public void stopArm() {
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }

    public void setupShoulder() {


        shoulderLeftMotor = hardwareMap.dcMotor.get("shoulder_left_motor");
        shoulderRightMotor = hardwareMap.dcMotor.get("shoulder_right_motor");
        shoulderLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        shoulderRightMotor.setDirection(DcMotor.Direction.REVERSE);

        shoulderLeftMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        shoulderRightMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);



    }

    public void tiltUp(double p) {
        shoulderLeftMotor.setPower(p);
        shoulderRightMotor.setPower(p);
    }

    public void tiltDown(double p) {
        shoulderLeftMotor.setPower(-1 * p);
        shoulderRightMotor.setPower(-1 * p);
    }

    public void stopShoulder() {
        shoulderLeftMotor.setPower(0);
        shoulderRightMotor.setPower(0);
    }
    @Override
    public void runOpMode() {

        setupShoulder();
        leftMotor = hardwareMap.dcMotor.get("arm_left_motor");
        rightMotor = hardwareMap.dcMotor.get("arm_right_motor");
        rightMotor.setDirection(DcMotor.Direction.REVERSE);

        leftMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        leftMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        leftMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        rightMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        touchSensor = hardwareMap.touchSensor.get("arm_retracted_sensor");



        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.dpad_up) {
                tiltUp(.6);
            } else if (gamepad1.dpad_down) {
                tiltDown(.6);
            } else {
                stopShoulder();
            }

            if (gamepad1.dpad_right) {
                extend(1);

            } else if (gamepad1.dpad_left) {
                retract(1);

            } else {
                stopArm();
            }

            telemetry.addData("Sensor", touchSensor.isPressed());
            telemetry.addData("left", leftMotor.getCurrentPosition());
            telemetry.addData("right", rightMotor.getCurrentPosition());
            telemetry.update();
        }


    }

}