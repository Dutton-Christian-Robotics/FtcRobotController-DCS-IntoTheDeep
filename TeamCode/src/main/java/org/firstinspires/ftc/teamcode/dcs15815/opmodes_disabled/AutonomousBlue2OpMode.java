package org.firstinspires.ftc.teamcode.dcs15815.opmodes_disabled;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderAlliance;
import org.firstinspires.ftc.teamcode.dcs15815.NautilusBot.NautilusBot;
import org.firstinspires.ftc.teamcode.dcs15815.NautilusBot.NautilusConfiguration;

@Disabled
@Autonomous(name = "Blue - 1 Sample", group = "2", preselectTeleOp="TwoGamepadTeleOpMode")
public class AutonomousBlue2OpMode  extends LinearOpMode {
    NautilusBot bot;

    @Override
    public void runOpMode() {
        bot = new NautilusBot(hardwareMap, NautilusConfiguration.class, telemetry);

        DefenderAlliance.getInstance().setColor(DefenderAlliance.Color.BLUE);

        bot.effects.scanBlue();

        waitForStart();

        bot.effects.heartbeatBlue();

//        // Get away from the wall so we don't get stuck
//        bot.driveToPosition(0, -8, 0);
//        bot.navigation.resetOrigin();
//
//        // Backup to the net zone
//        bot.driveToPosition(-16, 0, 0);
//        bot.navigation.resetOrigin();

        // Diagonal away from wall to the netzone
        bot.driveToBotRelativePosition(-16, -8, 0);
        bot.navigation.resetOrigin();


        // Turn so we're perpendicular to the baskets
//        bot.drivetrain.turnLeftByFrontLeftEncoder(.1, 400);
        bot.driveToBotRelativePosition(0, 0, -45);
        bot.navigation.resetOrigin();

        // Raise the arm and shoulder
        bot.shoulder.setPosition(NautilusConfiguration.SHOULDER_POSITION_MAX, NautilusConfiguration.SHOULDER_POWER_MAX * .7);
        bot.arm.setPosition(NautilusConfiguration.ARM_POSITION_MAX);

        // Wait a little bit
        sleep(2000);

        // Wrist flat
        bot.wrist.gotoUpPosition();

        // Snug up to the baskets
//        bot.drivetrain.driveBackwardByFrontLeftEncoder(.1, 80);
//        sleep(250);
        bot.driveToBotRelativePosition(-4, 0, 0);
        bot.navigation.resetOrigin();

        // Kick out the sample
        bot.intake.expel();
        sleep(2000);
        bot.intake.stop();

        // Get a safe distance from the baskets
//        bot.drivetrain.driveForwardByFrontLeftEncoder(.1, 200);
        bot.driveToBotRelativePosition(14, 0, 0);
        bot.navigation.resetOrigin();

        // Bring the arm down
        bot.arm.setPosition(NautilusConfiguration.ARM_POSITION_MIN);
        sleep(2000);



        bot.effects.solidBlue();


    }

}
