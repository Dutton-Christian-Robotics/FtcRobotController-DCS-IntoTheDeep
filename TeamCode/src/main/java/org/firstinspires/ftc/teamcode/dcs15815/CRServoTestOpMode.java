package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;


@TeleOp(name = "CRServo Test", group = "Testing")
public class CRServoTestOpMode extends LinearOpMode {
    private CRServo servo;
//    private Servo servo;

    @Override
    public void runOpMode() {

//        servo = hardwareMap.servo.get("intake_left_servo");
        servo = hardwareMap.crservo.get("intake_left_servo");

        waitForStart();

        while (!isStopRequested()) {
            servo.setPower(1);
            sleep(3000);
            servo.setPower(-1);
            sleep(3000);
            servo.setPower(0);
            sleep(3000);
//            servo.setPosition(0);
//            sleep(3000);
//            servo.setPosition(.25);
//            sleep(3000);
//            servo.setPosition(.5);
//            sleep(3000);
//            servo.setPosition(.75);
//            sleep(3000);
//            servo.setPosition(1);
//            sleep(3000);
        }

    }

}