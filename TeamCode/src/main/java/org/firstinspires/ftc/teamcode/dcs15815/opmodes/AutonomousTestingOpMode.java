package org.firstinspires.ftc.teamcode.dcs15815.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.dcs15815.NautilusBot.NautilusBot;
import org.firstinspires.ftc.teamcode.dcs15815.NautilusBot.NautilusConfiguration;


@Autonomous(name = "Autonomous Testing", group = "Testing")
public class AutonomousTestingOpMode extends LinearOpMode {
    NautilusBot bot;


    @Override
    public void runOpMode() {
        bot = new NautilusBot(hardwareMap, NautilusConfiguration.class, telemetry);

        waitForStart();

        // Suck in the pre-load specimen
        bot.intake.intake();
        sleep(1000);
        bot.intake.stop();

        // Drive away from the wall to allow shoulder movement
        bot.drivetrain.drive(.1, 0, 0);
        sleep(1000);
        bot.drivetrain.stopDriving();

        // Raise the shoulder and arm
        bot.shoulder.setPosition(2100);
        bot.arm.setPosition(1000);
        sleep(5000);

        // Drive to the submersible
        bot.drivetrain.drive(.1, 0, 0);
        sleep(1100);
        bot.drivetrain.stopDriving();
        sleep(1500);


        bot.intake.intake();
        bot.shoulder.setPosition(1700);

        sleep(1000);
        bot.intake.stop();
//        bot.intake.expel();
//        sleep(1500);

        bot.arm.setPosition(0);

        bot.drivetrain.drive(-.1, 0, 0);
        sleep(1000);
        bot.drivetrain.stopDriving();

        bot.drivetrain.drive(0, 0.5, 0);
        sleep(4000);

    }
}