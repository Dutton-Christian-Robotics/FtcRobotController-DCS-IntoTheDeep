package org.firstinspires.ftc.teamcode.dcs15815.opmodes_testing;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DigitalChannel;

@Disabled
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