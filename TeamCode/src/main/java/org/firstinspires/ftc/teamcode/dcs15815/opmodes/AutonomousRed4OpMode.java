package org.firstinspires.ftc.teamcode.dcs15815.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.dcs15815.NautilusBot.NautilusBot;
import org.firstinspires.ftc.teamcode.dcs15815.NautilusBot.NautilusConfiguration;

/*
Alliance Color: Red
Level: 4
Preload: blue sample
Starting Position: touching wall, facing towards Observation Zone,
    back of robot aligned with middle of tile A5
Actions:
    1) strafe away from wall
    2) backup to baskets
    3) elevate arm, shoulder
    4) angle for better position
    5) backup
    6) make high basket (8pts)
    7) position for yellow sample 1
    8) backup to basket (8pts)
    9) navigate to submersible
   10) Level 1 ascent (3pts)
 */

@Autonomous(name = "Red 4", group = "4", preselectTeleOp="TwoGamepadTeleOpMode")
public class AutonomousRed4OpMode  extends LinearOpMode {
    NautilusBot bot;

    @Override
    public void runOpMode() {
        bot = new NautilusBot(hardwareMap, NautilusConfiguration.class, telemetry);

        bot.effects.scanRed();

        waitForStart();

        bot.effects.heartbeatRed();

        // Get away from the wall so we don't get stuck
        bot.drivetrain.strafeLeftByFrontLeftEncoder(.4, 250);

        // Backup to the net zone
        bot.drivetrain.driveBackwardByFrontLeftEncoder(0.15, 650, true);

        // Turn so we're perpendicular to the baskets
        bot.drivetrain.turnLeftByFrontLeftEncoder(.1, 425);

        // Raise the arm and shoulder
        bot.shoulder.setPosition(NautilusConfiguration.SHOULDER_POSITION_MAX, NautilusConfiguration.SHOULDER_POWER_MAX * .7);
        bot.arm.setPosition(NautilusConfiguration.ARM_POSITION_MAX);

        // Wait a little bit
        sleep(2000);

        // Wrist flat
        bot.wrist.gotoUpPosition();

        // Snug up to the baskets
        bot.drivetrain.driveBackwardByFrontLeftEncoder(.1, 80);
        sleep(250);

        // Kick out the preloaded sample
        bot.shoulderOverdrive();
        bot.intake.expel();
        sleep(1000);
        bot.intake.stop();

        // Get a safe distance from the baskets
        bot.drivetrain.driveForwardByFrontLeftEncoder(.15, 290);

        // Turn to neutral samples
        bot.drivetrain.turnLeftByFrontLeftEncoder(.13, 320);

        // Get manipulators ready
        bot.arm.setPosition(0); // was 225
        sleep(1200);
        bot.shoulder.setPosition(0, .8); // was 550
        sleep(1750);
//        bot.wrist.gotoMidPosition();





//        bot.wrist.gotoDownPosition();
        // Grab neutral sample
        bot.intake.intake();
        bot.drivetrain.driveForwardByFrontLeftEncoder(.1, 480, true);
        sleep(600);
        bot.intake.stop();


        // Backup
        bot.drivetrain.driveBackwardByFrontLeftEncoder(.1, 700);

        // Get into delivery position
        bot.shoulder.setPosition(NautilusConfiguration.SHOULDER_POSITION_MAX, NautilusConfiguration.SHOULDER_POWER_MAX * .7);
        bot.arm.setPosition(NautilusConfiguration.ARM_POSITION_MAX);
        bot.wrist.gotoUpPosition();

        // Rotate towards basket
        bot.drivetrain.turnRightByFrontLeftEncoder(.1, 220);

        // Snug-up
        bot.drivetrain.driveBackwardByFrontLeftEncoder(.1, 280, true);
        sleep(275);

        bot.shoulderOverdrive();

        // Expel
        bot.intake.expel();
        sleep(1000);
        bot.intake.stop();

        // Get away from basket
        bot.drivetrain.driveForwardByFrontLeftEncoder(.15, 200);

        bot.drivetrain.turnRightByFrontLeftEncoder(.15, 250);

        bot.shoulder.setPosition(2100, .5);
        bot.drivetrain.strafeLeftByFrontLeftEncoder(.4, 1800);

        // Drive towards level 1 ascent
//        bot.drivetrain.turnLeftByFrontLeftEncoder(.1, 100);
//        bot.drivetrain.strafeLeftByFrontLeftEncoder(.2, 200);
        bot.drivetrain.driveForwardByFrontLeftEncoder(.15, 1600);




        bot.effects.solidRed();


    }

}
