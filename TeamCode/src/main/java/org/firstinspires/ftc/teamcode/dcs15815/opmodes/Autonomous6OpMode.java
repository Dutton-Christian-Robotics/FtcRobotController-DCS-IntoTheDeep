package org.firstinspires.ftc.teamcode.dcs15815.opmodes;

import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderAlliance;
import org.firstinspires.ftc.teamcode.dcs15815.NautilusBot.NautilusBot;
import org.firstinspires.ftc.teamcode.dcs15815.NautilusBot.NautilusConfiguration;


public abstract class Autonomous6OpMode extends IntoTheDeepAutonomousOpMode {

    public void prepareForDelivery() {
        bot.shoulder.setPosition(NautilusConfiguration.SHOULDER_POSITION_MAX, NautilusConfiguration.SHOULDER_POWER_MAX * .7);
        sleep(500);
        bot.arm.setPosition(NautilusConfiguration.ARM_POSITION_MAX);
        bot.wrist.gotoUpPosition();
        bot.shoulderOverdrive();


    }

    public void deliverSample(double backup) {
        // Raise the arm and shoulder
        bot.shoulder.setPosition(NautilusConfiguration.SHOULDER_POSITION_MAX, NautilusConfiguration.SHOULDER_POWER_MAX * .7);
        sleep(500);
        bot.arm.setPosition(NautilusConfiguration.ARM_POSITION_MAX);

        // Wait a little bit
        sleep(1200);

        // Wrist flat
        bot.wrist.gotoUpPosition();

        // Snug up to the baskets
//        bot.driveToPosition(-3, 0, 0);
//        bot.navigation.resetOrigin();

        // Kick out the sample
        bot.shoulderOverdrive();

        if (backup > 0) {
            bot.driveToBotRelativePosition(-backup, 0, 0);
            bot.navigation.resetOrigin();
        }

        bot.intake.expelToTheLeft();
        sleep(1500);
        bot.wrist.gotoAutonIntakePosition();
        bot.intake.stop();
    }

    @Override
    public void performAutonomous() {

// Sample 1
        bot.shoulder.gotoAutonTravelPosition();
        bot.driveToBotRelativePositionWithTimeout(12, -25, 0, 2000, 2400);
        deliverSample(0);

// Sample 2

        bot.driveToBotRelativePositionWithTimeout(0, 7, 0, 1000, 1500); //6

        bot.correctForAngle(3);



        bot.arm.setPosition(500);
        sleep(500);
        bot.gotoAutonIntakePosition();
        sleep(1200);

        bot.intake.intake();
        bot.driveToBotRelativePosition(26, 0, 0);
        bot.intake.stop();
        bot.navigation.resetOrigin();
        bot.shoulder.gotoAutonTravelPosition();

        // Back away from neutral samples

        bot.driveToBotRelativePositionWithTimeout(-26.5, -6, 0, 1000, 2500); // was -27.5 -7

        deliverSample(0);

// Sample 3
        bot.navigation.resetOtosAndResetOrigin();
        bot.driveToBotRelativePositionWithTimeout(0, 18, 0, 1000, 2000); // 16

        bot.correctForAngle(3);

        bot.arm.setPosition(500);
        sleep(500);
        bot.gotoAutonIntakePosition();
        sleep(800);

        bot.navigation.resetOtosAndResetOrigin();

        bot.intake.intake();
        bot.driveToBotRelativePositionWithTimeout(21, 0, 0, 1000, 2200);
        bot.intake.stop();
        bot.shoulder.gotoAutonTravelPosition();

        // Back away from neutral samples
        bot.shoulder.setPosition(NautilusConfiguration.SHOULDER_POSITION_MAX, NautilusConfiguration.SHOULDER_POWER_MAX);
        bot.navigation.resetOtosAndResetOrigin();

        bot.arm.setPosition(NautilusConfiguration.ARM_POSITION_MAX);
        bot.driveToBotRelativePositionWithTimeout(-23, -10, 0, 1000, 2400); //-26, -12

        bot.wrist.gotoUpPosition();
        bot.shoulderOverdrive();

        bot.navigation.resetOtosAndResetOrigin();
//        bot.driveToBotRelativePositionWithTimeout(0, 0, 40, 1000, 1200);

//        deliverSample(0);
//        bot.intake.expelToTheLeft();
        bot.intake.expel();
        sleep(1500);



    }

}
