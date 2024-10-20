package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name = "Sensor Test", group = "Testing")
public class SensorTestOpMode extends LinearOpMode {
    DigitalChannel limitSwitch;

    @Override
    public void runOpMode() {

        limitSwitch = hardwareMap.digitalChannel.get("shoulder_down_sensor");
        limitSwitch.setMode(DigitalChannel.Mode.INPUT);


        waitForStart();

        while (!isStopRequested()) {
            telemetry.addData("Sensor", limitSwitch.getState());
            telemetry.update();
        }

    }

}