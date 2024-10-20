package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name = "Motor Test", group = "Testing")
public class MotorTestOpMode extends LinearOpMode {
   DcMotorSimple motor;

    @Override
    public void runOpMode() {

        motor = hardwareMap.dcMotor.get("arm_left_motor");

        waitForStart();

//        while (!isStopRequested()) {
            motor.setPower(.8);
            sleep(1250);
            motor.setPower(0);
//        }

    }

}