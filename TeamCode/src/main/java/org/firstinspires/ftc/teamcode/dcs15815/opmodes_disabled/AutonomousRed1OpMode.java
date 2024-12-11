package org.firstinspires.ftc.teamcode.dcs15815.opmodes_disabled;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.dcs15815.NautilusBot.NautilusBot;
import org.firstinspires.ftc.teamcode.dcs15815.NautilusBot.NautilusConfiguration;

/*
Alliance Color: Red
Level: 1
Preload: blue sample
Starting Position: touching wall, facing into net,
    front of robot aligned with middle of tile A5
Actions:
    1) strafe away from wall
    2) drive forward to net
    3) expel sample into net (2pts)
    4) navigate to submersible
    5) Level 1 ascent (3pts)
 */

@Disabled
@Autonomous(name = "Red 1", group = "1", preselectTeleOp="TwoGamepadTeleOpMode")
public class AutonomousRed1OpMode  extends LinearOpMode {
    NautilusBot bot;

    @Override
    public void runOpMode() {
        bot = new NautilusBot(hardwareMap, NautilusConfiguration.class, telemetry);

        bot.effects.scanRed();

        waitForStart();

        bot.effects.heartbeatRed();
        bot.drivetrain.strafeRightByFrontLeftEncoder(.4, 100);

        bot.drivetrain.driveForwardByFrontLeftEncoder(0.1, 650);
        bot.intake.expel();
        sleep(3000);
        bot.intake.stop();
        bot.drivetrain.driveBackwardByFrontLeftEncoder(.1, 300);
        bot.drivetrain.turnRight90ByFrontLeftEncoder(.1);

        bot.drivetrain.driveForwardByFrontLeftEncoder(.1,1250);
        bot.drivetrain.turnLeft90ByFrontLeftEncoder(.1);

        bot.shoulder.setPosition(NautilusConfiguration.SHOULDER_POSITION_MAX);

        bot.drivetrain.driveBackwardByFrontLeftEncoder(.1, 500);

        bot.effects.solidRed();


    }

}
