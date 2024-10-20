package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name = "Shoulder Test", group = "Testing")
public class ShoulderTestOpMode extends LinearOpMode {
    DcMotorSimple leftMotor, rightMotor;

    @Override
    public void runOpMode() {

        leftMotor = hardwareMap.dcMotor.get("shoulder_left_motor");
        rightMotor = hardwareMap.dcMotor.get("shoulder_right_motor");
        leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        waitForStart();

//        while (!isStopRequested()) {
        leftMotor.setPower(.7);
        rightMotor.setPower(.7);
        sleep(1000);
        leftMotor.setPower(-0.2);
        rightMotor.setPower(-0.2);
        sleep(1000);
        leftMotor.setPower(0);
        rightMotor.setPower(0);
//        }

    }

}