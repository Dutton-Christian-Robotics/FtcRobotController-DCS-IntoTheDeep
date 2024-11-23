package org.firstinspires.ftc.teamcode.dcs15815.opmodes;

import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderAlliance;
import org.firstinspires.ftc.teamcode.dcs15815.NautilusBot.NautilusBot;
import org.firstinspires.ftc.teamcode.dcs15815.NautilusBot.NautilusConfiguration;

/*
Alliance Color: Blue or Red
Level: 4
Preload: neutral sample
Starting Position: touching wall, facing towards Observation Zone,
    back of robot aligned with lower edge of tile A5/A6 border
Actions:
    1)
 */
public abstract class Autonomous6OpMode extends LinearOpMode {
    NautilusBot bot;
    DefenderAlliance.Color allianceColor = DefenderAlliance.Color.UNKNOWN;

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

    public abstract void setAlliance();

    @Override
    public void runOpMode() {
        bot = new NautilusBot(hardwareMap, NautilusConfiguration.class, telemetry);

        setAlliance();
        if (DefenderAlliance.getInstance().isRed()) {
            telemetry.addData("Alliance", "RED");
        } else if (DefenderAlliance.getInstance().isBlue()) {
            telemetry.addData("Alliance", "BLUE");
        } else {
            telemetry.addData("Alliance", "unknown");
        }
        telemetry.update();

        if (DefenderAlliance.getInstance().isRed()) {
            bot.effects.scanRed();
        } else if (DefenderAlliance.getInstance().isBlue()) {
            bot.effects.scanBlue();
        } else {
            bot.effects.wavesParty();
        }

        waitForStart();
        bot.navigation.resetOtosAndResetOrigin();

        if (DefenderAlliance.getInstance().isRed()) {
            bot.effects.heartbeatRed();
        } else if (DefenderAlliance.getInstance().isBlue()) {
            bot.effects.heartbeatBlue();
        } else {
            bot.effects.wavesParty();
        }

// Sample 1
        bot.shoulder.gotoAutonTravelPosition();
        ElapsedTime timer = new ElapsedTime();
        bot.driveToBotRelativePosition(12, -25, 0, () -> {
            SparkFunOTOS.Pose2D velocity = bot.navigation.otos.getVelocity();
            return (Math.abs(velocity.x) < 1 && Math.abs(velocity.y) < 1 && Math.abs(velocity.h) < 1 && timer.milliseconds() > 2000) || (timer.milliseconds() > 2400);
        });
        bot.navigation.resetOrigin();
        deliverSample(0);

// Sample 2

        timer.reset();
        bot.driveToBotRelativePosition(0, 6, 0, () -> {
            SparkFunOTOS.Pose2D velocity = bot.navigation.otos.getVelocity();
            return (Math.abs(velocity.x) < 1 && Math.abs(velocity.y) < 1 && Math.abs(velocity.h) < 1 && timer.milliseconds() > 1000) || (timer.milliseconds() > 1500);
        });
        bot.navigation.resetOrigin();

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
        sleep(1200);

        bot.intake.intake();
        bot.driveToBotRelativePosition(26, 0, angle);
        bot.intake.stop();
        bot.navigation.resetOrigin();
        bot.shoulder.gotoAutonTravelPosition();

        // Back away from neutral samples

        timer.reset();
        bot.driveToBotRelativePosition(-27.5, -7, 0, () -> {
            SparkFunOTOS.Pose2D velocity = bot.navigation.otos.getVelocity();
            return (Math.abs(velocity.x) < 1 && Math.abs(velocity.y) < 1 && Math.abs(velocity.h) < 1 && timer.milliseconds() > 1000) || (timer.milliseconds() > 2500);
        });


        deliverSample(0);

// Sample 3
        bot.navigation.resetOtosAndResetOrigin();
        timer.reset();
        bot.driveToBotRelativePosition(0, 14, 0, () -> {
            SparkFunOTOS.Pose2D velocity = bot.navigation.otos.getVelocity();
            return (Math.abs(velocity.x) < 1 && Math.abs(velocity.y) < 1 && Math.abs(velocity.h) < 1 && timer.milliseconds() > 1000) || (timer.milliseconds() > 2000);
        });


        pose = bot.navigation.otos.getPosition();
        angle = pose.h;
        telemetry.addData("h", angle);
        telemetry.update();

        if (Math.abs(angle) < 3) {
            angle = 0;
        }


        bot.arm.setPosition(500);
        sleep(500);
        bot.gotoAutonIntakePosition();
        sleep(800);

        bot.navigation.resetOtosAndResetOrigin();

        bot.intake.intake();
        timer.reset();
        bot.driveToBotRelativePosition(21, 0, angle, () -> {
            SparkFunOTOS.Pose2D velocity = bot.navigation.otos.getVelocity();
            return (Math.abs(velocity.x) < 1 && Math.abs(velocity.y) < 1 && Math.abs(velocity.h) < 1 && timer.milliseconds() > 1000) || (timer.milliseconds() > 2200);
        });
        bot.intake.stop();
        bot.navigation.resetOrigin();
        bot.shoulder.gotoAutonTravelPosition();

        // Back away from neutral samples
        bot.shoulder.setPosition(NautilusConfiguration.SHOULDER_POSITION_MAX, NautilusConfiguration.SHOULDER_POWER_MAX);
        bot.navigation.resetOtosAndResetOrigin();
        timer.reset();
        bot.driveToBotRelativePosition(-27, -12, 0, () -> {
            SparkFunOTOS.Pose2D velocity = bot.navigation.otos.getVelocity();
            return (Math.abs(velocity.x) < 1 && Math.abs(velocity.y) < 1 && Math.abs(velocity.h) < 1 && timer.milliseconds() > 1000) || (timer.milliseconds() > 2500);
        });
        bot.arm.setPosition(NautilusConfiguration.ARM_POSITION_MAX);
        bot.wrist.gotoUpPosition();
        bot.shoulderOverdrive();
        bot.navigation.resetOtosAndResetOrigin();
        timer.reset();
        bot.driveToBotRelativePosition(0, 0, 45, () -> {
            SparkFunOTOS.Pose2D velocity = bot.navigation.otos.getVelocity();
            return (Math.abs(velocity.x) < 1 && Math.abs(velocity.y) < 1 && Math.abs(velocity.h) < 1 && timer.milliseconds() > 1000) || (timer.milliseconds() > 1200);
        });

//        deliverSample(0);
        bot.intake.expelToTheLeft();
        sleep(1500);







        if (DefenderAlliance.getInstance().isRed()) {
            bot.effects.solidRed();
        } else if (DefenderAlliance.getInstance().isBlue()) {
            bot.effects.solidBlue();
        } else {
            bot.effects.wavesParty();
        }

    }

}
