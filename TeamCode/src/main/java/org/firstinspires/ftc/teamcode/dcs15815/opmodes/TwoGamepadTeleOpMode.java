package org.firstinspires.ftc.teamcode.dcs15815.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderAnalogModifier;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderDebouncer;
import org.firstinspires.ftc.teamcode.dcs15815.NautilusBot.NautilusBot;
import org.firstinspires.ftc.teamcode.dcs15815.NautilusBot.NautilusConfiguration;

@TeleOp(name = "Two Gamepad TeleOp", group = "Competition")
public class TwoGamepadTeleOpMode extends LinearOpMode {
    NautilusBot bot;
    int intakeState = 0;
    private DefenderDebouncer gamepad2LeftBumperDebouncer, gamepad2RightBumperDebouncer;

    @Override
    public void runOpMode() {
        bot = new NautilusBot(hardwareMap, NautilusConfiguration.class, telemetry);

        // ——— SETUP STICK MODIFIERS —————————————————————————————————

        // ——— SETUP GAMEPAD 1 DEBOUNCERS —————————————————————————————————

        // ——— SETUP GAMEPAD 2 DEBOUNCERS —————————————————————————————————
        gamepad2LeftBumperDebouncer = new DefenderDebouncer(500, () -> {
            if (intakeState != -1) {
                bot.intake.expel();
                intakeState = -1;
            } else {
                bot.intake.stop();
                intakeState = 0;
            }
        });
        gamepad2RightBumperDebouncer = new DefenderDebouncer(500, () -> {
            if (intakeState != 1) {
                bot.intake.intake();
                intakeState = 1;
            } else {
                bot.intake.stop();
                intakeState = 0;
            }
        });
        // ——— INITIALIZATION BEFORE START —————————————————————————————————

        waitForStart();


        while (opModeIsActive()) {

            // ——— GAMEPAD 1 FUNCTIONS  —————————————————————————————————

            bot.drivetrain.drive(
                    -1 * gamepad1.left_stick_y,
                    (gamepad1.right_trigger - gamepad1.left_trigger),
                    gamepad1.right_stick_x);



            // ——— GAMEPAD 2 FUNCTIONS  —————————————————————————————————

            if (gamepad2.x) {
                bot.intake.stop();

            } else if (gamepad2.right_bumper) {
                gamepad2RightBumperDebouncer.run();

            } else if (gamepad2.left_bumper) {
                gamepad2LeftBumperDebouncer.run();

            } else {
//                bot.intake.stop();
            }

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


            // ——— TELEMETRY  —————————————————————————————————


//            if (!allowManualLiftControl) {
//                telemetry.addData("Manual Lift", "disabled");
//            } else {
//                telemetry.addData("Manual Lift", "ENABLED");
//            }
//            telemetry.addData("Lift", bot.lift.getPosition());
//            telemetry.addData("Tilt", bot.tilt.getPosition());
//            telemetry.addData("Stickypad", bot.stickyPad.getPosition());
//            telemetry.update();

        }

        // ——— AFTER OPMODE ENDS —————————————————————————————————

    }
}