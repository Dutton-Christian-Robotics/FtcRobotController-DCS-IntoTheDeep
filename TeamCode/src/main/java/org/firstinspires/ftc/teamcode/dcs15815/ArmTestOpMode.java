package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;


@TeleOp(name = "Arm Test", group = "Testing")
public class ArmTestOpMode extends LinearOpMode {
    DcMotorSimple leftMotor, rightMotor;
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

    @Override
    public void runOpMode() {

        leftMotor = hardwareMap.dcMotor.get("arm_left_motor");
        rightMotor = hardwareMap.dcMotor.get("arm_right_motor");
        rightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        touchSensor = hardwareMap.touchSensor.get("arm_retracted_sensor");

        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.dpad_up) {
                extend(1);
            } else if (gamepad1.dpad_down) {
                retract(1);
            } else {
                stopArm();
            }
            telemetry.addData("Sensor", touchSensor.isPressed());
            telemetry.update();
        }


    }

}