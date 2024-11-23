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
public abstract class Autonomous4bOpMode extends LinearOpMode {
    NautilusBot bot;
    DefenderAlliance.Color allianceColor = DefenderAlliance.Color.UNKNOWN;

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

    public void setAlliance() {
        DefenderAlliance.getInstance().setColor(allianceColor);
    };

    @Override
    public void runOpMode() {
        bot = new NautilusBot(hardwareMap, NautilusConfiguration.class, telemetry);

        setAlliance();
        if (DefenderAlliance.getInstance().isRed()) {
            telemetry.addData("Alliance", "RED");
            bot.effects.scanRed();
        } else if (DefenderAlliance.getInstance().isBlue()) {
            telemetry.addData("Alliance", "BLUE");
            bot.effects.scanBlue();
        } else {
            telemetry.addData("Alliance", "unknown");
            bot.effects.wavesParty();
        }
        telemetry.update();

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
        ElapsedTime timer1 = new ElapsedTime();
        bot.driveToBotRelativePosition(12, -25, 0, () -> {
            SparkFunOTOS.Pose2D velocity = bot.navigation.otos.getVelocity();
            return (Math.abs(velocity.x) < 1 && Math.abs(velocity.y) < 1 && Math.abs(velocity.h) < 1 && timer1.milliseconds() > 2000) || (timer1.milliseconds() > 5000);
        });
        bot.navigation.resetOrigin();
        deliverSample(0);

// Sample 2

        timer1.reset();
        bot.driveToBotRelativePosition(0, 6, 0, () -> {
            SparkFunOTOS.Pose2D velocity = bot.navigation.otos.getVelocity();
            return (Math.abs(velocity.x) < 1 && Math.abs(velocity.y) < 1 && Math.abs(velocity.h) < 1 && timer1.milliseconds() > 1000) || (timer1.milliseconds() > 3000);
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
        sleep(1500);

        bot.intake.intake();
        bot.driveToBotRelativePosition(26, 0, angle);
        bot.intake.stop();
        bot.navigation.resetOrigin();
        bot.shoulder.gotoAutonTravelPosition();

        // Back away from neutral samples

        ElapsedTime timer2 = new ElapsedTime();
        bot.driveToBotRelativePosition(-27.5, -7, 0, () -> {
            SparkFunOTOS.Pose2D velocity = bot.navigation.otos.getVelocity();
            return (Math.abs(velocity.x) < 1 && Math.abs(velocity.y) < 1 && Math.abs(velocity.h) < 1 && timer2.milliseconds() > 2000) || (timer2.milliseconds() > 3700);
        });


        deliverSample(0);





        if (DefenderAlliance.getInstance().isRed()) {
            bot.effects.solidRed();
        } else if (DefenderAlliance.getInstance().isBlue()) {
            bot.effects.solidBlue();
        } else {
            bot.effects.wavesParty();
        }

    }

}
