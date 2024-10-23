package org.firstinspires.ftc.teamcode.dcs15815.opmodes_testing;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Disabled
@TeleOp(name = "Drivetrain Test", group = "Testing")
public class DrivetrainTestOpMode extends LinearOpMode {
    private DcMotor backLeftMotor, frontLeftMotor, frontRightMotor, backRightMotor;

    @Override
    public void runOpMode() {

        backLeftMotor = hardwareMap.dcMotor.get("back_left_motor");
        frontLeftMotor = hardwareMap.dcMotor.get("front_left_motor");
        frontRightMotor = hardwareMap.dcMotor.get("front_right_motor");
        backRightMotor = hardwareMap.dcMotor.get("back_right_motor");

        backLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        frontLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        while (!isStopRequested()) {

            backLeftMotor.setPower(.2);
            frontLeftMotor.setPower(.2);
            frontRightMotor.setPower(.2);
            backRightMotor.setPower(.2);
            sleep(2000);
            backLeftMotor.setPower(0);
            frontLeftMotor.setPower(0);
            frontRightMotor.setPower(0);
            backRightMotor.setPower(0);
            sleep(1000);
            backLeftMotor.setPower(-.2);
            frontLeftMotor.setPower(-.2);
            frontRightMotor.setPower(-.2);
            backRightMotor.setPower(-.2);
            sleep(2000);
            backLeftMotor.setPower(0);
            frontLeftMotor.setPower(0);
            frontRightMotor.setPower(0);
            backRightMotor.setPower(0);
            sleep(1000);

        }

    }

}