package org.firstinspires.ftc.teamcode.dcs15815.opmodes_disabled;

import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.dcs15815.NautilusBot.NautilusConfiguration;
import org.firstinspires.ftc.teamcode.dcs15815.opmodes.IntoTheDeepAutonomousOpMode;


public abstract class Autonomous4bOpMode extends IntoTheDeepAutonomousOpMode {

    public void deliverSample(double backup) {
        // Raise the arm and shoulder
        bot.shoulder.setPosition(NautilusConfiguration.SHOULDER_POSITION_MAX, NautilusConfiguration.SHOULDER_POWER_MAX * .7);
        sleep(500);
        bot.arm.setPosition(NautilusConfiguration.ARM_POSITION_MAX);

        // Wait a little bit
        sleep(1500);

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
        bot.intake.stop();
        bot.wrist.gotoAutonIntakePosition();
    }


    @Override
    public void performAutonomous() {

// Sample 1
        bot.shoulder.gotoAutonTravelPosition();
        bot.driveToBotRelativePositionWithTimeout(12, -25, 0, 2000, 5000);
        deliverSample(0);

// Sample 2

        bot.driveToBotRelativePositionWithTimeout(0, 6, 0, 1000, 3000);

        // As it stands I don't think these next lines are doing anything.
        SparkFunOTOS.Pose2D pose = bot.navigation.otos.getPosition();
        double angle = pose.h;
        telemetry.addData("h", angle);
        telemetry.update();

        if (Math.abs(angle) < 3) {
            angle = 0;
        }


        bot.arm.setPosition(500);
        sleep(500);
        bot.gotoAutonIntakePosition();
        sleep(1500);

        bot.intake.intake();
        bot.driveToBotRelativePosition(26, 0, angle);
        bot.intake.stop();
        bot.navigation.resetOrigin();
        bot.shoulder.gotoAutonTravelPosition();

        // Back away from neutral samples

        ElapsedTime timer2 = new ElapsedTime();
        bot.driveToBotRelativePositionWithTimeout(-27.5, -7, 0,2000, 3700);


        deliverSample(0);


    }

}
