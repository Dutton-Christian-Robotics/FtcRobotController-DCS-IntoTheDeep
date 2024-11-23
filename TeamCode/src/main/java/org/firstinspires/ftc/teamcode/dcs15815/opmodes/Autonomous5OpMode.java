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
public abstract class Autonomous5OpMode extends LinearOpMode {
    NautilusBot bot;
    DefenderAlliance.Color allianceColor = DefenderAlliance.Color.UNKNOWN;



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


        bot.shoulder.gotoAutonTravelPosition();

        ElapsedTime timer = new ElapsedTime();
        bot.driveToBotRelativePosition(14, 0, 0, () -> {
            SparkFunOTOS.Pose2D velocity = bot.navigation.otos.getVelocity();
            return (Math.abs(velocity.x) < 1 && Math.abs(velocity.y) < 1 && Math.abs(velocity.h) < 1 && timer.milliseconds() > 2000) || (timer.milliseconds() > 2400);
        });
        bot.navigation.resetOrigin();

        bot.shoulder.setPosition(2000);
        sleep(500);
        bot.arm.setPosition(700);


        timer.reset();
        bot.driveToBotRelativePosition(18, 0, 0, () -> {
            SparkFunOTOS.Pose2D velocity = bot.navigation.otos.getVelocity();
            return (Math.abs(velocity.x) < 1 && Math.abs(velocity.y) < 1 && Math.abs(velocity.h) < 1 && timer.milliseconds() > 2000) || (timer.milliseconds() > 3000);
        });
        bot.navigation.resetOrigin();

        bot.intake.intake();

        bot.wrist.setPosition(NautilusConfiguration.WRIST_SERVO_POSITION_BOTTOM);
        sleep(1200);
        bot.arm.setPosition(0);
        sleep(1000);
        bot.intake.stop();

// Specimen 2

        bot.driveToBotRelativePosition(-18, 47, 0);
        bot.navigation.resetOrigin();

        SparkFunOTOS.Pose2D pose = bot.navigation.otos.getPosition();
        double angle = pose.h;
        telemetry.addData("h", angle);
        telemetry.update();

        if (Math.abs(angle) > 3) {
            bot.driveToBotRelativePosition(0, 0, angle);
        }
        bot.navigation.resetOrigin();

        bot.driveToBotRelativePosition(0, 0, 180);
        bot.navigation.resetOrigin();

        bot.shoulder.setPosition(900);
        bot.wrist.setPosition(NautilusConfiguration.WRIST_SERVO_POSITION_TOPMIDDLE);
        bot.intake.intake();

        bot.arm.setPosition(700);
        sleep(3000);

//        bot.driveToBotRelativePosition(6, 0, 0, () -> false,
//                NautilusConfiguration.NAVIGATION_Y_KP, NautilusConfiguration.NAVIGATION_Y_KI, NautilusConfiguration.NAVIGATION_Y_KD,
//                NautilusConfiguration.NAVIGATION_X_KP, NautilusConfiguration.NAVIGATION_X_KI, NautilusConfiguration.NAVIGATION_X_KD,
//                NautilusConfiguration.NAVIGATION_R_KP, NautilusConfiguration.NAVIGATION_R_KI, NautilusConfiguration.NAVIGATION_R_KD,
//                0.1, NautilusConfiguration.NAVIGATION_X_MAXPOWER, NautilusConfiguration.NAVIGATION_R_MAXPOWER
//        );
//        bot.navigation.resetOrigin();


//        bot.driveToBotRelativePosition(4, 0, 0);
        bot.intake.stop();
        bot.shoulder.setPosition(1800);
        sleep(1500);

        bot.navigation.resetOtosAndResetOrigin();
        bot.driveToBotRelativePosition(0, 0, 180);
        bot.navigation.resetOrigin();

        bot.navigation.resetOtosAndResetOrigin();
        bot.driveToBotRelativePosition(10, -52, 0);
        bot.navigation.resetOrigin();

        pose = bot.navigation.otos.getPosition();
        angle = pose.h;
        telemetry.addData("h", angle);
        telemetry.update();

        if (Math.abs(angle) > 3) {
            bot.driveToBotRelativePosition(0, 0, angle);
        }
        bot.navigation.resetOrigin();





        if (DefenderAlliance.getInstance().isRed()) {
            bot.effects.solidRed();
        } else if (DefenderAlliance.getInstance().isBlue()) {
            bot.effects.solidBlue();
        } else {
            bot.effects.wavesParty();
        }

    }

}
