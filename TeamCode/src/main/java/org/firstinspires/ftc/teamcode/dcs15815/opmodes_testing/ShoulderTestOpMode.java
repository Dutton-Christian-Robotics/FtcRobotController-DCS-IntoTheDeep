package org.firstinspires.ftc.teamcode.dcs15815.opmodes_testing;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;

@Disabled
@TeleOp(name = "Shoulder Test", group = "Testing")
public class ShoulderTestOpMode extends LinearOpMode {
    DcMotor leftMotor, rightMotor;
    DigitalChannel shoulderUpSensor, shoulderDownSensor;


    public void tiltUp(double p) {
        leftMotor.setPower(p);
        rightMotor.setPower(p);
    }

    public void tiltDown(double p) {
        leftMotor.setPower(-1 * p);
        rightMotor.setPower(-1 * p);
    }

    public void stopShoulder() {
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }

    @Override
    public void runOpMode() {

        shoulderDownSensor = hardwareMap.digitalChannel.get("shoulder_down_sensor");
        shoulderDownSensor.setMode(DigitalChannel.Mode.INPUT);

        shoulderUpSensor = hardwareMap.digitalChannel.get("shoulder_up_sensor");
        shoulderUpSensor.setMode(DigitalChannel.Mode.INPUT);


        leftMotor = hardwareMap.dcMotor.get("shoulder_left_motor");
        rightMotor = hardwareMap.dcMotor.get("shoulder_right_motor");
        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);

        leftMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        leftMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        leftMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        rightMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        waitForStart();

        while (!isStopRequested()) {
            if (gamepad1.dpad_up) {
                tiltUp(1);
            } else if (gamepad1.dpad_down) {
                tiltDown(1);
            } else {
                stopShoulder();
            }
            telemetry.addData("Down", !shoulderDownSensor.getState());
            telemetry.addData("Up", !shoulderUpSensor.getState());
            telemetry.addData("left", leftMotor.getCurrentPosition());
            telemetry.addData("right", rightMotor.getCurrentPosition());
            telemetry.update();



//        leftMotor.setPower(.7);
//        rightMotor.setPower(.7);
//        sleep(1000);
//        leftMotor.setPower(-0.2);
//        rightMotor.setPower(-0.2);
//        sleep(1000);
//        leftMotor.setPower(0);
//        rightMotor.setPower(0);
        }

    }

}