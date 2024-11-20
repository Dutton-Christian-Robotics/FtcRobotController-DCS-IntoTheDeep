package org.firstinspires.ftc.teamcode.dcs15815.opmodes_testing;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DigitalChannel;

import org.firstinspires.ftc.teamcode.dcs15815.NautilusBot.NautilusBot;
import org.firstinspires.ftc.teamcode.dcs15815.NautilusBot.NautilusConfiguration;

@TeleOp(name = "Odometry Test", group = "Testing")
public class OdometryTestOpMode extends LinearOpMode {
    NautilusBot bot;

    @Override
    public void runOpMode() {

        bot = new NautilusBot(hardwareMap, NautilusConfiguration.class, telemetry);

        waitForStart();

        while (opModeIsActive()) {
//            telemetry.addData("Has Drivetrain", bot.drivetrain != null);
//            telemetry.addData("Has Navigation", bot.navigation != null);
//            telemetry.update();
            bot.driveToPosition(48, 0, 0);

        }

//        bot.drivetrain.drive(0.35, 0, 0);
//        sleep(2000);

    }

}