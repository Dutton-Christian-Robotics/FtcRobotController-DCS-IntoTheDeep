package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name = "Intake Test", group = "Testing")
public class IntakeTestOpMode extends LinearOpMode {
    private CRServo leftServo, rightServo;
//    private Servo servo;

    public void intake() {
        leftServo.setPower(-1);
        rightServo.setPower(1);
    }

    public void expel() {
        leftServo.setPower(1);
        rightServo.setPower(-1);
    }

    public void stopIntake() {
        leftServo.setPower(0);
        rightServo.setPower(0);
    }

    @Override
    public void runOpMode() {

//        servo = hardwareMap.servo.get("intake_left_servo");
        leftServo = hardwareMap.crservo.get("intake_left_servo");
        rightServo = hardwareMap.crservo.get("intake_right_servo");

        waitForStart();

        while (!isStopRequested()) {
            intake();
            sleep(3000);
            expel();
            sleep(3000);
            stopIntake();
            sleep(3000);
        }

    }

}