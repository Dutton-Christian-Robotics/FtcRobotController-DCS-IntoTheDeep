package org.firstinspires.ftc.teamcode.dcs15815.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.dcs15815.NautilusBot.NautilusBot;
import org.firstinspires.ftc.teamcode.dcs15815.NautilusBot.NautilusConfiguration;

/*
Alliance Color: Red
Level: 3
Preload: blue specimen
Starting Position: touching wall submersible, clip on ,
    clip aligned with tile A3/A4 border
Actions:
    1) drive forward to submersible
    2) attach specimen to high chamber (10pts)
    3) drive around submersible
    4) level 1 (3pts)
 */

@Autonomous(name = "Red 3 Park", group = "3", preselectTeleOp="TwoGamepadTeleOpMode")
public class AutonomousRed3ParkOpMode extends LinearOpMode {
    NautilusBot bot;

    @Override
    public void runOpMode() {
        bot = new NautilusBot(hardwareMap, NautilusConfiguration.class, telemetry);

        bot.effects.scanRed();

        waitForStart();

        bot.effects.heartbeatRed();

        // Raise the shoulder to prevent specimen grabbing the floor
        bot.shoulder.setPosition(300);
        sleep(500);

        // Drive away from the wall
        bot.drivetrain.driveForwardByFrontLeftEncoder(.1, 200);

        // Get arm and shoulder into delivery position
        bot.shoulder.setPosition(2200);
        bot.arm.setPosition(1000);
        sleep(2000);

        // Approach the submersible
        bot.drivetrain.driveForwardByFrontLeftEncoder(.1, 450, true);

        // Spin the intake to help with delivery tension
        bot.intake.intake();

        // Lower the shoulder to latch the speciment
        bot.shoulder.setPosition(1600, 1);
        sleep(2500);

        // Reset from delivery, prepare for travel
        bot.intake.expel();
        bot.shoulder.setPosition(1800, .6);

        bot.drivetrain.driveBackwardByFrontLeftEncoder(.1, 450);
        bot.shoulder.setPosition(NautilusConfiguration.SHOULDER_POSITION_MAX);
        bot.arm.setPosition(0);
        bot.intake.stop();

        bot.drivetrain.strafeRightByFrontLeftEncoder(.4, 2200, true);


        bot.effects.solidRed();


    }

}
