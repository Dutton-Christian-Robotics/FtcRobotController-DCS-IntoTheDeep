package org.firstinspires.ftc.teamcode.dcs15815.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

//import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderAnalogModifier;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderAlliance;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderDebouncer;
import org.firstinspires.ftc.teamcode.dcs15815.NautilusBot.NautilusBot;
import org.firstinspires.ftc.teamcode.dcs15815.NautilusBot.NautilusConfiguration;

@TeleOp(name = "Two Gamepad TeleOp", group = "Competition")
public class TwoGamepadTeleOpMode extends LinearOpMode {
    NautilusBot bot;
    int intakeState = 0;
    boolean shoulderOverdrive = false;
    boolean manualManipulation = true;
    boolean ascendPrepared = false;
    boolean ascending = false;
    private DefenderDebouncer gamepad2LeftBumperDebouncer, gamepad2RightBumperDebouncer;
    private DefenderDebouncer gamepad2DpadUpDebouncer, gamepad2DpadDownDebouncer;
    private DefenderDebouncer gamepad1StartDebouncer;

    public void setup() {
        bot = new NautilusBot(hardwareMap, NautilusConfiguration.class, telemetry);
        // ——— SETUP STICK MODIFIERS —————————————————————————————————

        // ——— SETUP GAMEPAD 1 DEBOUNCERS —————————————————————————————————

        gamepad1StartDebouncer = new DefenderDebouncer(500, () -> {
            manualManipulation = !manualManipulation;
            if (!manualManipulation) {
                bot.lockManipulatorPositions();
            }
        });

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
        gamepad2DpadUpDebouncer = new DefenderDebouncer(500, () -> {
           bot.wrist.gotoNextUp();
        });
        gamepad2DpadDownDebouncer = new DefenderDebouncer(500, () -> {
            bot.wrist.gotoNextDown();
        });
        // ——— INITIALIZATION BEFORE START —————————————————————————————————
        bot.wrist.gotoUpPosition();

    }

    @Override
    public void runOpMode() {
        setup();

        if (DefenderAlliance.getInstance().isRed()) {
            telemetry.addData("RED ALLAINCE", "");
        } else if (DefenderAlliance.getInstance().isBlue()) {
            telemetry.addData("BLUE ALLAINCE", "");
        } else {
            telemetry.addData("NO ALLAINCE", "");
        }


        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {

            // ——— GAMEPAD 1 FUNCTIONS  —————————————————————————————————

            bot.drivetrain.drive(
                    -1 * gamepad1.left_stick_y,
                    (gamepad1.right_trigger - gamepad1.left_trigger),
                    gamepad1.right_stick_x);


            if (gamepad1.x && !shoulderOverdrive) {
                shoulderOverdrive = true;
                bot.shoulderOverdrive();
            } else if (!gamepad1.x && shoulderOverdrive) {
                shoulderOverdrive = false;
            }

            if (gamepad1.start) {
                gamepad1StartDebouncer.run();
            }



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

//            if (gamepad2.a) {
//                ascendPrepared = true;
//                bot.gotoAscendPrepPosition();
//            }
//            if (gamepad2.b && ascendPrepared) {
//                ascending = true;
//                bot.gotoAscendedPosition();
//            }

            if (manualManipulation) {
                if (gamepad2.right_stick_y < 0) {
                    bot.arm.extend(Math.abs(gamepad2.right_stick_y));
                } else if (gamepad2.right_stick_y > 0) {
                    bot.arm.retract(gamepad2.right_stick_y);
                } else {
                    if (!ascending && !ascendPrepared) {
                        bot.arm.stop();
                    }
                }

                if (!shoulderOverdrive) {
                    if (gamepad2.left_stick_y < 0) {
                        shoulderOverdrive = false;
                        bot.shoulder.tiltUp(Math.abs(gamepad2.left_stick_y));
                    } else if (gamepad2.left_stick_y > 0) {
                        shoulderOverdrive = false;
                        bot.shoulder.tiltDown(gamepad2.left_stick_y);
                    } else {
                        if (!ascending && !ascendPrepared) {
//                            bot.shoulder.stop();
                            bot.shoulder.holdCurrentPosition();
                        }
                    }
                }
            }


            if (gamepad2.dpad_up) {
                gamepad2DpadUpDebouncer.run();
            } else if (gamepad2.dpad_down) {
                gamepad2DpadDownDebouncer.run();
            }


            // ——— TELEMETRY  —————————————————————————————————

            if (DefenderAlliance.getInstance().isRed()) {
                telemetry.addData("RED ALLAINCE", "");
            } else if (DefenderAlliance.getInstance().isBlue()) {
                telemetry.addData("BLUE ALLAINCE", "");
            }

            if (shoulderOverdrive) {
                telemetry.addData("Shoulder", "OVERDRIVE");
            } else {
                telemetry.addData("Shoulder", "manual");
            }

            if (manualManipulation) {
                telemetry.addData("Manipulators", "manual");
            } else {
                telemetry.addData("Manipulators", "LOCKED");
            }

//            if (!ascendPrepared) {
//                telemetry.addData("Ascend", "not prepared");
//            } else if (!ascending) {
//                telemetry.addData("Ascend", "PREPARED");
//            } else if (ascending) {
//                telemetry.addData("Ascend", "ACTIVATED");
//            }

            if (bot.intake.isLoaded()) {
                telemetry.addData("Intake", "empty");
                bot.effects.rainbow();
            } else {
                telemetry.addData("Intake", "LOADED");
                bot.effects.strobeGold();
            }
//            telemetry.addData("Lift", bot.lift.getPosition());
//            telemetry.addData("Tilt", bot.tilt.getPosition());
//            telemetry.addData("Stickypad", bot.stickyPad.getPosition());
            telemetry.update();

        }

        // ——— AFTER OPMODE ENDS —————————————————————————————————

    }
}