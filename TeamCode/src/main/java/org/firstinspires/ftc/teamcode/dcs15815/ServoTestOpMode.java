package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name = "Servo Test", group = "Testing")
public class ServoTestOpMode extends LinearOpMode {
    private Servo servo;
//    private Servo servo;

    @Override
    public void runOpMode() {

//        servo = hardwareMap.servo.get("intake_left_servo");
        servo = hardwareMap.servo.get("wrist_servo");

        waitForStart();

        while (!isStopRequested()) {
            servo.setPosition(.9);
            sleep(3000);
            servo.setPosition(.15);
            sleep(3000);
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