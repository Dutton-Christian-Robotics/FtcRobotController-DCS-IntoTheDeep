package org.firstinspires.ftc.teamcode.dcs15815.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.dcs15815.NautilusBot.NautilusBot;
import org.firstinspires.ftc.teamcode.dcs15815.NautilusBot.NautilusConfiguration;

/*
Alliance Color: Red
Level: 2
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
    7) navigate to submersible
    8) Level 1 ascent (3pts)
 */
@Disabled
@Autonomous(name = "Red 2", group = "2", preselectTeleOp="TwoGamepadTeleOpMode")
public class AutonomousRed2OpMode  extends LinearOpMode {
    NautilusBot bot;

    @Override
    public void runOpMode() {
        bot = new NautilusBot(hardwareMap, NautilusConfiguration.class, telemetry);

        bot.effects.scanRed();

        waitForStart();

        bot.effects.heartbeatRed();

        // Get away from the wall so we don't get stuck
        bot.drivetrain.strafeLeftByFrontLeftEncoder(.4, 275);

        // Backup to the net zone
        bot.drivetrain.driveBackwardByFrontLeftEncoder(0.1, 650, true);

        // Turn so we're perpendicular to the baskets
        bot.drivetrain.turnLeftByFrontLeftEncoder(.1, 400);

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

        // Kick out the sample
        bot.intake.expel();
        sleep(2000);
        bot.intake.stop();

        // Get a safe distance from the baskets
        bot.drivetrain.driveForwardByFrontLeftEncoder(.1, 200);

        // Bring the arm down
        bot.arm.setPosition(NautilusConfiguration.ARM_POSITION_MIN);

        // Straighten out
        bot.drivetrain.turnRightByFrontLeftEncoder(.1, 400);


        // Get into driving position
        bot.drivetrain.driveForwardByFrontLeftEncoder(.1, 300);
        bot.drivetrain.turnLeft90ByFrontLeftEncoder(.1);

        // Travel towards submersible
        bot.drivetrain.driveForwardByFrontLeftEncoder(.1,1500);
        bot.drivetrain.turnLeft90ByFrontLeftEncoder(.1);

        // Extend the arm
        bot.arm.setPosition(NautilusConfiguration.ARM_POSITION_MAX);
        sleep(1000);

        // Overdrive the shoulder back for contact
        bot.shoulderOverdrive();


        // Backup to the submersible
        bot.drivetrain.driveBackwardByFrontLeftEncoder(.2, 700);


        bot.effects.solidRed();


    }

}
