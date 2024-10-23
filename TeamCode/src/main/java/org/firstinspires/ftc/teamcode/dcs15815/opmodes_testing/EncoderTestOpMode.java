package org.firstinspires.ftc.teamcode.dcs15815.opmodes_testing;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Disabled
@TeleOp(name = "Encoder Test", group = "Testing")
public class EncoderTestOpMode extends LinearOpMode {
    DcMotor motor;

    @Override
    public void runOpMode() {

        motor = hardwareMap.dcMotor.get("back_right_motor");
        motor.setDirection(DcMotorSimple.Direction.FORWARD);
        motor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        motor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        motor.setPower(0);

        waitForStart();

        while (!isStopRequested()) {
            motor.setTargetPosition(1000);
            motor.setPower(.5);
        }
        motor.setPower(0);

    }

}