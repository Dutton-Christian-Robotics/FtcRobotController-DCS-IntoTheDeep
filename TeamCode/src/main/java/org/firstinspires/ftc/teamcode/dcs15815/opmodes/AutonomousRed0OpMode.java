package org.firstinspires.ftc.teamcode.dcs15815.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.dcs15815.NautilusBot.NautilusBot;
import org.firstinspires.ftc.teamcode.dcs15815.NautilusBot.NautilusConfiguration;

/*
Alliance Color: Red
Level: 0
Preload: none
Starting Position: touching wall, facing into Observation Zone,
    front of robot aligned with tile A2/A3 border
Actions:
    1) drive forward into Observation Zone
    2) park (3pts)
 */

@Autonomous(name = "Red 0", group = "0", preselectTeleOp="TwoGamepadTeleOpMode")
public class AutonomousRed0OpMode  extends LinearOpMode {
    NautilusBot bot;

    @Override
    public void runOpMode() {
        bot = new NautilusBot(hardwareMap, NautilusConfiguration.class, telemetry);

        bot.effects.scanRed();

        waitForStart();

        bot.effects.heartbeatRed();
        bot.drivetrain.strafeLeftByFrontLeftEncoder(.4, 200);
        bot.drivetrain.driveForwardByFrontLeftEncoder(0.1, 900);
        bot.effects.solidRed();


    }

}
