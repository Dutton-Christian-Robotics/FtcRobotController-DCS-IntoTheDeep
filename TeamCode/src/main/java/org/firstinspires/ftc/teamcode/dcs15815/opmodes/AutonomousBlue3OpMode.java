package org.firstinspires.ftc.teamcode.dcs15815.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.dcs15815.NautilusBot.NautilusBot;
import org.firstinspires.ftc.teamcode.dcs15815.NautilusBot.NautilusConfiguration;

/*
Alliance Color: Blue
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

@Autonomous(name = "Blue 3", group = "3")
public class AutonomousBlue3OpMode  extends LinearOpMode {
    NautilusBot bot;

    @Override
    public void runOpMode() {
        bot = new NautilusBot(hardwareMap, NautilusConfiguration.class, telemetry);

        bot.effects.scanBlue();

        waitForStart();

        bot.effects.heartbeatBlue();

        // Raise the shoulder to prevent specimen grabbing the floor
        bot.shoulder.setPosition(200);
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

        bot.drivetrain.driveBackwardByFrontLeftEncoder(.1, 250);
        bot.shoulder.setPosition(NautilusConfiguration.SHOULDER_POSITION_MAX);
        bot.arm.setPosition(0);
        bot.intake.stop();

        // Back away from the submersible
//        bot.drivetrain.driveBackwardByFrontLeftEncoder(.1, 250);

        // Drive around the submersible
        bot.drivetrain.turnLeft90ByFrontLeftEncoder(.1);
        bot.drivetrain.driveForwardByFrontLeftEncoder(.1, 1100);
        bot.drivetrain.turnRight90ByFrontLeftEncoder(.1);
        // Travel towards submersible
        bot.drivetrain.driveForwardByFrontLeftEncoder(.1,1100);
        bot.drivetrain.turnLeft90ByFrontLeftEncoder(.1);

        // Extend the arm
        bot.arm.setPosition(NautilusConfiguration.ARM_POSITION_MAX);
        sleep(1000);

        // Overdrive the shoulder back for contact
        bot.shoulderOverdrive();


        // Backup to the submersible
        bot.drivetrain.driveBackwardByFrontLeftEncoder(.2, 700);


        bot.effects.solidBlue();


    }

}
