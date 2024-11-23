package org.firstinspires.ftc.teamcode.dcs15815.opmodes_testing;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.dcs15815.NautilusBot.NautilusBot;
import org.firstinspires.ftc.teamcode.dcs15815.NautilusBot.NautilusConfiguration;

@Disabled
@TeleOp(name = "Distance Test", group = "Testing")
public class DistanceTestOpMode extends LinearOpMode {
    NautilusBot bot;

    @Override
    public void runOpMode() {
        bot = new NautilusBot(hardwareMap, NautilusConfiguration.class, telemetry);

        bot.effects.strobeGold();
        waitForStart();

        bot.drivetrain.driveBackwardByFrontLeftEncoder(0.1, 500);
        sleep(1000);
        bot.drivetrain.turnLeft90ByFrontLeftEncoder(0.1);
    }

}
