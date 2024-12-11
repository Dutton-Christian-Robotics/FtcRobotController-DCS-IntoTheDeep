package org.firstinspires.ftc.teamcode.dcs15815.opmodes_disabled;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.dcs15815.NautilusBot.NautilusBot;
import org.firstinspires.ftc.teamcode.dcs15815.NautilusBot.NautilusConfiguration;

/*
Alliance Color: Blue
Level: X
Preload: blue sample
Starting Position: touching wall, facing towards Observation Zone,
    back of robot aligned with middle of tile A5
Actions:
    1) strafe away from wall
    2) backup to baskets
    3) elevate arm, shoulder
    4) angle for better position
    5) backup
 */

@Disabled
@Autonomous(name = "Blue X", group = "X", preselectTeleOp="TwoGamepadTeleOpMode")
public class AutonomousBlueXOpMode  extends LinearOpMode {
    NautilusBot bot;

    @Override
    public void runOpMode() {
        bot = new NautilusBot(hardwareMap, NautilusConfiguration.class, telemetry);

        bot.effects.scanBlue();

        waitForStart();

        bot.effects.heartbeatBlue();

        // Get away from the wall so we don't get stuck
        bot.drivetrain.strafeLeftByFrontLeftEncoder(.4, 250);

        // Backup to the net zone
        bot.drivetrain.driveBackwardByFrontLeftEncoder(0.15, 650);

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
        bot.drivetrain.driveForwardByFrontLeftEncoder(.1, 265);

        bot.shoulder.setPosition(550, .6);
        bot.arm.setPosition(225);
        sleep(1500);

        bot.drivetrain.turnLeftByFrontLeftEncoder(.13, 320);
        bot.wrist.gotoMidPosition();

        bot.intake.intake();
        bot.drivetrain.driveForwardByFrontLeftEncoder(.1, 460);
        sleep(600);
        bot.intake.stop();


        bot.drivetrain.driveBackwardByFrontLeftEncoder(.1, 450);

        bot.shoulder.setPosition(NautilusConfiguration.SHOULDER_POSITION_MAX, NautilusConfiguration.SHOULDER_POWER_MAX * .7);
        bot.arm.setPosition(NautilusConfiguration.ARM_POSITION_MAX);
        bot.wrist.gotoUpPosition();

        bot.drivetrain.turnRightByFrontLeftEncoder(.1, 220);


//        sleep(2000);


        bot.drivetrain.driveBackwardByFrontLeftEncoder(.1, 280);
        sleep(275);

        bot.shoulderOverdrive();

        bot.intake.expel();
        sleep(1000);
        bot.intake.stop();

        // Get a safe distance from the baskets
//        bot.drivetrain.driveForwardByFrontLeftEncoder(.1, 265);

        bot.shoulder.setPosition(550, .6);
        bot.arm.setPosition(225);
        sleep(1500);

        bot.drivetrain.turnLeftByFrontLeftEncoder(.13, 320);
        bot.wrist.gotoMidPosition();

        bot.intake.intake();
        bot.drivetrain.driveForwardByFrontLeftEncoder(.1, 460);
        sleep(600);
        bot.intake.stop();

        bot.drivetrain.driveBackwardByFrontLeftEncoder(.1, 450);

        bot.shoulder.setPosition(NautilusConfiguration.SHOULDER_POSITION_MAX, NautilusConfiguration.SHOULDER_POWER_MAX * .7);
        bot.arm.setPosition(NautilusConfiguration.ARM_POSITION_MAX);
        bot.wrist.gotoUpPosition();

        bot.drivetrain.turnRightByFrontLeftEncoder(.1, 220);


//        sleep(2000);


        bot.drivetrain.driveBackwardByFrontLeftEncoder(.1, 280);
        sleep(275);

        bot.shoulderOverdrive();

        bot.intake.expel();
        sleep(1000);
        bot.intake.stop();



        bot.effects.solidBlue();


    }

}
