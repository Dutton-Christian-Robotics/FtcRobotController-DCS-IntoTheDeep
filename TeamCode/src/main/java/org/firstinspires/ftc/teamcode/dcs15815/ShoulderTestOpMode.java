package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name = "Shoulder Test", group = "Testing")
public class ShoulderTestOpMode extends LinearOpMode {
    DcMotorSimple leftMotor, rightMotor;
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
        leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
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