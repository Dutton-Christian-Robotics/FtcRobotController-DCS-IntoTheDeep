package org.firstinspires.ftc.teamcode.dcs15815.opmodes_testing;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderDebouncer;
import org.firstinspires.ftc.teamcode.dcs15815.NautilusBot.NautilusBot;
import org.firstinspires.ftc.teamcode.dcs15815.NautilusBot.NautilusConfiguration;

@Disabled
@TeleOp(name = "Drivetrain PID Tuning Test", group = "Testing")
public class DrivetrainPidTunerOpMode extends LinearOpMode {
    NautilusBot bot;

    boolean tuningX = false;
    boolean tuningY = false;
    boolean tuningR = false;

    boolean changingP = false;
    boolean changingI = false;
    boolean changingD = false;

    double x_kP = NautilusConfiguration.NAVIGATION_X_KP;
    double x_kI = NautilusConfiguration.NAVIGATION_X_KI;
    double x_kD = NautilusConfiguration.NAVIGATION_X_KD;
    double x_maxPower = NautilusConfiguration.NAVIGATION_X_MAXPOWER;

    double y_kP = NautilusConfiguration.NAVIGATION_Y_KP;
    double y_kI = NautilusConfiguration.NAVIGATION_Y_KI;
    double y_kD = NautilusConfiguration.NAVIGATION_Y_KD;
    double y_maxPower = NautilusConfiguration.NAVIGATION_Y_MAXPOWER;

    double r_kP = NautilusConfiguration.NAVIGATION_R_KP;
    double r_kI = NautilusConfiguration.NAVIGATION_R_KI;
    double r_kD = NautilusConfiguration.NAVIGATION_R_KD;
    double r_maxPower = NautilusConfiguration.NAVIGATION_R_MAXPOWER;


    double tuningIncrement = 0.1;
    boolean negativeTestingDirection = false;

    double testDriveDistance = 48;
    boolean isTesting = false;

    private DefenderDebouncer gamepad1xDebouncer, gamepad1yDebouncer, gamepad1aDebouncer, gamepad1bDebouncer;
    private DefenderDebouncer gamepad1startDebouncer, gamepad1backDebouncer;
    private DefenderDebouncer gamepad1dpadUpDebouncer, gamepad1dpadDownDebouncer;
    private DefenderDebouncer gamepad1dpadLeftDebouncer, gamepad1dpadRightDebouncer;
    private DefenderDebouncer gamepad1leftBumperDebouncer, gamepad1rightBumperDebouncer;
    private DefenderDebouncer gamepad1leftTriggerDebouncer, gamepad1rightTriggerDebouncer;

//    private DefenderDebouncer gamepad1;

    public boolean isTuning() {
        return tuningX || tuningY || tuningR;
    }



    @Override
    public void runOpMode() {

        bot = new NautilusBot(hardwareMap, NautilusConfiguration.class, telemetry);
        bot.setUseDebugging(true);
        telemetry.setCaptionValueSeparator(" ");

        gamepad1xDebouncer = new DefenderDebouncer(500, () -> {
            if (isTuning()) {
                changingP = true;
                changingI = false;
                changingD = false;
            } else {
                tuningX = true;
                tuningY = false;
                tuningR = false;
            }
        });
        gamepad1yDebouncer = new DefenderDebouncer(500, () -> {
            if (isTuning()) {
                changingP = false;
                changingI = true;
                changingD = false;
            } else {
                tuningX = false;
                tuningY = true;
                tuningR = false;
            }
        });
        gamepad1aDebouncer = new DefenderDebouncer(500, () -> {
            tuningX = false;
            tuningY = false;
            tuningR = false;
        });
        gamepad1bDebouncer = new DefenderDebouncer(500, () -> {
            if (isTuning()) {
                changingP = false;
                changingI = false;
                changingD = true;
            } else {
                tuningX = false;
                tuningY = false;
                tuningR = true;
            }
        });

        gamepad1backDebouncer = new DefenderDebouncer(500, () -> {
            negativeTestingDirection = !negativeTestingDirection;
        });
        gamepad1dpadUpDebouncer = new DefenderDebouncer(500, () -> {
            if (isTuning()) {
                if (tuningX) {
                    if (changingP) {
                        x_kP += tuningIncrement;
                    } else if (changingI) {
                        x_kI += tuningIncrement;
                    } else if (changingD) {
                        x_kD += tuningIncrement;
                    }
                } else if (tuningY) {
                    if (changingP) {
                        y_kP += tuningIncrement;
                    } else if (changingI) {
                        y_kI += tuningIncrement;
                    } else if (changingD) {
                        y_kD += tuningIncrement;
                    }
                } else if (tuningR) {
                    if (changingP) {
                        r_kP += tuningIncrement;
                    } else if (changingI) {
                        r_kI += tuningIncrement;
                    } else if (changingD) {
                        r_kD += tuningIncrement;
                    }
                }
            }
        });
        gamepad1dpadDownDebouncer = new DefenderDebouncer(500, () -> {
            if (isTuning()) {
                if (tuningX) {
                    if (changingP) {
                        x_kP -= tuningIncrement;
                    } else if (changingI) {
                        x_kI -= tuningIncrement;
                    } else if (changingD) {
                        x_kD -= tuningIncrement;
                    }
                } else if (tuningY) {
                    if (changingP) {
                        y_kP -= tuningIncrement;
                    } else if (changingI) {
                        y_kI -= tuningIncrement;
                    } else if (changingD) {
                        y_kD -= tuningIncrement;
                    }
                } else if (tuningR) {
                    if (changingP) {
                        r_kP -= tuningIncrement;
                    } else if (changingI) {
                        r_kI -= tuningIncrement;
                    } else if (changingD) {
                        r_kD -= tuningIncrement;
                    }
                }
            }
        });

        gamepad1dpadLeftDebouncer = new DefenderDebouncer(500, () -> {
            if (tuningIncrement == 0.01) {
                tuningIncrement = 0.001;
            } else if (tuningIncrement == 0.1) {
                tuningIncrement = 0.01;
            } else if (tuningIncrement == 1) {
                tuningIncrement = 0.1;
            } else if (tuningIncrement == 10) {
                tuningIncrement = 1;
            }

        });
        gamepad1dpadRightDebouncer = new DefenderDebouncer(500, () -> {
            if (tuningIncrement == 0.001) {
                tuningIncrement = 0.01;
            } else if (tuningIncrement == 0.01) {
                tuningIncrement = 0.1;
            } else if (tuningIncrement == 0.1) {
                tuningIncrement = 1;
            } else if (tuningIncrement == 1) {
                tuningIncrement = 10;
            }
        });

        gamepad1leftBumperDebouncer = new DefenderDebouncer(500, () -> {
            if (testDriveDistance == 24) {
                testDriveDistance = 12;
            } else if (testDriveDistance == 48) {
                testDriveDistance = 24;
            }

        });
        gamepad1rightBumperDebouncer = new DefenderDebouncer(500, () -> {
            if (testDriveDistance == 12) {
                testDriveDistance = 24;
            } else if (testDriveDistance == 24) {
                testDriveDistance = 48;
            }
        });

        gamepad1startDebouncer = new DefenderDebouncer(500, () -> {
//        public void testDrive() {
            if (isTesting) {
                isTesting = false;
                return;
            }
            isTesting = true;
            bot.navigation.resetOtos();
            if (tuningX) {
                if (negativeTestingDirection) {
                    bot.effects.strobeWhite();
                    bot.driveToBotRelativePosition(
                            0, -testDriveDistance, 0,
//                        () -> false,
                            () -> !opModeIsActive()  || gamepad1.guide,
                            y_kP, y_kI, y_kD,
                            x_kP, x_kI, x_kD,
                            r_kP, r_kI, r_kD,
                            y_maxPower, x_maxPower, r_maxPower
                    );
                    bot.effects.wavesParty();
                } else {
                    bot.effects.strobeWhite();
                    bot.driveToBotRelativePosition(
                            0, testDriveDistance, 0,
//                        () -> false,
                            () -> !opModeIsActive()  || gamepad1.guide,
                            y_kP, y_kI, y_kD,
                            x_kP, x_kI, x_kD,
                            r_kP, r_kI, r_kD,
                            y_maxPower, x_maxPower, r_maxPower
                    );
                    bot.effects.wavesParty();
                }
            } else if (tuningY) {
                if (negativeTestingDirection) {
                    bot.effects.strobeWhite();
                    bot.driveToBotRelativePosition(
                            -testDriveDistance, 0, 0,
//                        () -> false,
                            () -> !opModeIsActive()  || gamepad1.guide,
                            y_kP, y_kI, y_kD,
                            x_kP, x_kI, x_kD,
                            r_kP, r_kI, r_kD,
                            y_maxPower, x_maxPower, r_maxPower
                    );
                    bot.effects.wavesParty();
                } else {
                    bot.effects.strobeWhite();
                    bot.driveToBotRelativePosition(
                            testDriveDistance, 0, 0,
//                        () -> false,
                            () -> !opModeIsActive()  || gamepad1.guide,
                            y_kP, y_kI, y_kD,
                            x_kP, x_kI, x_kD,
                            r_kP, r_kI, r_kD,
                            y_maxPower, x_maxPower, r_maxPower
                    );
                    bot.effects.wavesParty();
                }
            } else if (tuningR) {
                double angle = 0;
                if (testDriveDistance == 12) {
                    angle = 45;
                } else if (testDriveDistance == 24) {
                    angle = 90;
                } else if (testDriveDistance == 48) {
                    angle = 180;
                }
                if (negativeTestingDirection) {
                    bot.effects.strobeWhite();
                    bot.driveToBotRelativePosition(
                            0, 0, -angle,
//                        () -> false,
                            () -> !opModeIsActive()  || gamepad1.guide,
                            y_kP, y_kI, y_kD,
                            x_kP, x_kI, x_kD,
                            r_kP, r_kI, r_kD,
                            y_maxPower, x_maxPower, r_maxPower
                    );
                    bot.effects.wavesParty();
                } else {
                    bot.effects.strobeWhite();
                    bot.driveToBotRelativePosition(
                            0, 0, angle,
//                        () -> false,
                            () -> !opModeIsActive()  || gamepad1.guide,
                            y_kP, y_kI, y_kD,
                            x_kP, x_kI, x_kD,
                            r_kP, r_kI, r_kD,
                            y_maxPower, x_maxPower, r_maxPower
                    );
                    bot.effects.wavesParty();
                }
            }
//            isTesting = false;
        });
//        }

        gamepad1leftTriggerDebouncer = new DefenderDebouncer(1500, () -> {
            if (isTuning()) {
                if (tuningX) {
                    x_maxPower -= .1;
                    if (x_maxPower > 1) {
                        x_maxPower = 1;
                    } else if (x_maxPower < 0) {
                        x_maxPower = 0;
                    }
                } else if (tuningY) {
                    y_maxPower -= .1;
                    if (y_maxPower > 1) {
                        y_maxPower = 1;
                    } else if (y_maxPower < 0) {
                        y_maxPower = 0;
                    }
                } else if (tuningR) {
                    r_maxPower -= .1;
                    if (r_maxPower > 1) {
                        r_maxPower = 1;
                    } else if (r_maxPower < 0) {
                        r_maxPower = 0;
                    }

                }
            }



        });
        gamepad1rightTriggerDebouncer = new DefenderDebouncer(1500, () -> {
            if (isTuning()) {
                if (tuningX) {
                    x_maxPower += .1;
                    if (x_maxPower > 1) {
                        x_maxPower = 1;
                    } else if (x_maxPower < 0) {
                        x_maxPower = 0;
                    }
                } else if (tuningY) {
                    y_maxPower += .1;
                    if (y_maxPower > 1) {
                        y_maxPower = 1;
                    } else if (y_maxPower < 0) {
                        y_maxPower = 0;
                    }
                } else if (tuningR) {
                    r_maxPower += .1;
                    if (r_maxPower > 1) {
                        r_maxPower = 1;
                    } else if (r_maxPower < 0) {
                        r_maxPower = 0;
                    }

                }
            }
        });




            waitForStart();

        while (opModeIsActive()) {

            if (gamepad1.a) {
                gamepad1aDebouncer.run();

            } else if (gamepad1.b) {
                gamepad1bDebouncer.run();

            } else if (gamepad1.x) {
                gamepad1xDebouncer.run();

            } else if (gamepad1.y) {
                gamepad1yDebouncer.run();
            }
            if (gamepad1.start) {
//                testDrive();
                gamepad1startDebouncer.run();
            }
            if (gamepad1.back) {
                gamepad1backDebouncer.run();
            }
            if (gamepad1.dpad_up) {
                gamepad1dpadUpDebouncer.run();
            } else if (gamepad1.dpad_down) {
                gamepad1dpadDownDebouncer.run();
            }

            if (gamepad1.dpad_left) {
                gamepad1dpadLeftDebouncer.run();
            } else if (gamepad1.dpad_right) {
                gamepad1dpadRightDebouncer.run();
            }

            if (gamepad1.left_bumper) {
                gamepad1leftBumperDebouncer.run();
            } else if (gamepad1.right_bumper) {
                gamepad1rightBumperDebouncer.run();
            }

            if (gamepad1.left_trigger > 0) {
                gamepad1leftTriggerDebouncer.run();
            } else if (gamepad1.right_trigger > 0) {
                gamepad1rightTriggerDebouncer.run();
            }


            addPaddedTelemetry("Y", roundDouble(y_kP) + " • " + roundDouble(y_kI) + " • " + roundDouble(y_kD) + " — " + roundDouble(y_maxPower, 1));
            addPaddedTelemetry("X", roundDouble(x_kP) + " • " + roundDouble(x_kI) + " • " + roundDouble(x_kD) + " — " + roundDouble(x_maxPower, 1));
            addPaddedTelemetry("R", roundDouble(r_kP) + " • " + roundDouble(r_kI) + " • " + roundDouble(r_kD) + " — " + roundDouble(r_maxPower, 1));
//            telemetry.addData(String.format("%[11]s", "X"), x_kP + " • " + x_kI + " • " + x_kD);
//            telemetry.addData(String.format("%[11]s", "Y"), y_kP + " • " + y_kI + " • " + y_kD);
//            telemetry.addData(String.format("%[11]s", "R"), r_kP + " • " + r_kI + " • " + r_kD);

            addPaddedTelemetry("------------", "-----------------");
            if (testDriveDistance == 12) {
                addPaddedTelemetry("Distance", "12in / 45º");
            } else if (testDriveDistance == 24) {
                addPaddedTelemetry("Distance", "24in / 90º");
            } else if (testDriveDistance == 48) {
                addPaddedTelemetry("Distance", "48in / 180º");
            }

            if (negativeTestingDirection) {
                addPaddedTelemetry("Direction", "negative");
//                telemetry.addData(String.format("%[11]s", "Direction"), "negative");
            } else {
                addPaddedTelemetry("Direction", "positive");
//                telemetry.addData(String.format("%[11]s", "Direction"), "positive");
            }

            if (tuningX) {
                addPaddedTelemetry("Tuning", "X");
//                telemetry.addData(String.format("%[11]s", "Tuning"), "X");
            } else if (tuningY) {
                addPaddedTelemetry("Tuning", "Y");
//                telemetry.addData(String.format("%[11]s", "Tuning"), "Y");
            } else if (tuningR) {
                addPaddedTelemetry("Tuning", "R");
//                telemetry.addData(String.format("%[11]s", "Tuning"), "R");
            } else {
                addPaddedTelemetry("Tuning", "n/a");
//                telemetry.addData(String.format("%[11]s", "Tuning"), "n/a");
            }

            if (isTuning()) {
                if (changingP) {
                    addPaddedTelemetry("Changing", "P");
//                    telemetry.addData(String.format("%[11]s", "Changing"), "P");
                } else if (changingI) {
                    addPaddedTelemetry("Changing", "I");
//                    telemetry.addData(String.format("%[11]s", "Changing"), "I");
                } else if (changingD) {
                    addPaddedTelemetry("Changing", "D");
//                    telemetry.addData(String.format("%[11]s", "Changing"), "D");
                } else {
                    addPaddedTelemetry("Changing", "n/a");
//                    telemetry.addData(String.format("%[11]s", "Changing"), "n/a");
                }
                addPaddedTelemetry("Increment", "" + tuningIncrement + "");
//                telemetry.addData(String.format("%[11]s", "Increment"), tuningIncrement);
            }
            if (!isTesting) {
                telemetry.update();
            }
        }

    }
    public void addPaddedTelemetry(String key, String value) {
        addPaddedTelemetry(key, value, 18);
    }
   public void addPaddedTelemetry(String key, String value, int length) {
        telemetry.addData(String.format("%-"+length+"s", key), value);
    }

    public double roundDouble(double n) {
        return roundDouble(n, 3);
    }
    public double roundDouble(double n, int i) {
        double f = Math.pow(10, i);
        return Math.floor(n * f) / f;
    }

}