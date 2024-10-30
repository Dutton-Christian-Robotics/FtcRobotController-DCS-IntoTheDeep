package org.firstinspires.ftc.teamcode.dcs15815.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderAnalogModifier;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderDebouncer;
import org.firstinspires.ftc.teamcode.dcs15815.NautilusBot.NautilusBot;
import org.firstinspires.ftc.teamcode.dcs15815.NautilusBot.NautilusConfiguration;

@TeleOp(name = "Manipulators", group = "Testing")
public class ManipulatorsOpMode extends LinearOpMode
{
    NautilusBot bot;
    int intakeState = 0;

    @Override
    public void runOpMode() {
        bot = new NautilusBot(hardwareMap, NautilusConfiguration.class, telemetry);


        waitForStart();


        while (opModeIsActive()) {


            if (gamepad2.right_stick_y < 0) {
                bot.arm.extend();
            } else if (gamepad2.right_stick_y > 0) {
                bot.arm.retract();
            } else {
                bot.arm.stop();
            }

            if (gamepad2.left_stick_y < 0) {
                bot.shoulder.tiltUp();
            } else if (gamepad2.left_stick_y > 0) {
                bot.shoulder.tiltDown();
            } else {
                bot.shoulder.stop();
            }

            if (gamepad2.dpad_up) {
                bot.wrist.gotoUpPosition();
            } else if (gamepad2.dpad_down) {
                bot.wrist.gotoDownPosition();
            }


        }

        // ——— AFTER OPMODE ENDS —————————————————————————————————

    }
}