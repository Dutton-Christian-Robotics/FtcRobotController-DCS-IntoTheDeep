package org.firstinspires.ftc.teamcode.dcs15815.opmodes_testing;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Disabled
@TeleOp(name = "Motor Test", group = "Testing")
public class MotorTestOpMode extends LinearOpMode {
   DcMotorSimple motor;

    @Override
    public void runOpMode() {

        motor = hardwareMap.dcMotor.get("back_right_motor");

        waitForStart();

//        while (!isStopRequested()) {
            motor.setPower(.3);
            sleep(1250);
            motor.setPower(0);
//        }

    }

}