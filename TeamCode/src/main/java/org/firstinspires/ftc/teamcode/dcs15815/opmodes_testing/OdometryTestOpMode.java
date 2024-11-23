package org.firstinspires.ftc.teamcode.dcs15815.opmodes_testing;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.dcs15815.NautilusBot.NautilusBot;
import org.firstinspires.ftc.teamcode.dcs15815.NautilusBot.NautilusConfiguration;
@Disabled
@TeleOp(name = "Odometry Test", group = "Testing")
public class OdometryTestOpMode extends LinearOpMode {
    NautilusBot bot;

    @Override
    public void runOpMode() {

        bot = new NautilusBot(hardwareMap, NautilusConfiguration.class, telemetry);

        waitForStart();

//        bot.driveToFieldPosition(48, 0, 0);

//        while (opModeIsActive()) {
//            telemetry.addData("Has Drivetrain", bot.drivetrain != null);
//            telemetry.addData("Has Navigation", bot.navigation != null);
//            telemetry.update();


//        bot.driveToBotRelativePosition(96, 0, 0);
//        bot.navigation.resetOtos();
//        bot.driveToBotRelativePosition(0, 0, -90);
//        bot.navigation.resetOtos();
//        bot.driveToBotRelativePosition(84, 0, 0);
//        bot.navigation.resetOtos();
//        bot.driveToBotRelativePosition(0, 0, -90);
//        bot.navigation.resetOtos();
//        bot.driveToBotRelativePosition(84, 0, 0);
//        bot.navigation.resetOtos();
//        bot.driveToBotRelativePosition(0, 0, -90);
//        bot.navigation.resetOtos();
//        bot.driveToBotRelativePosition(84, 0, 0);

//        }

//        bot.drivetrain.drive(0.35, 0, 0);
//        sleep(2000);

    }

}