package org.firstinspires.ftc.teamcode.dcs15815.NautilusBot;


import org.firstinspires.ftc.robotcore.external.Telemetry;

import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderPIDController;

import java.util.function.BooleanSupplier;

public class NautilusBot extends DefenderBot {

    public NautilusMecanumDrivetrain drivetrain;
    public NautilusShoulder shoulder;
    public NautilusArm arm;
    public NautilusIntake intake;
    public NautilusWrist wrist;
    public NautilusEffects effects;
    public NautilusNavigation navigation;
    private boolean useDebugging = false;

    //    public DefenderPresets<SBBArmPosition> armPresets;
//    public DefenderDelayedSequence grabPixelSequence;

    public NautilusBot(HardwareMap hm, Class configClass, Telemetry t) {
        super(hm, configClass, t);

        drivetrain = addSystem(NautilusMecanumDrivetrain.class);
        shoulder = addSystem(NautilusShoulder.class);
        arm = addSystem(NautilusArm.class);
        intake = addSystem(NautilusIntake.class);
        wrist = addSystem(NautilusWrist.class);
        effects = addSystem(NautilusEffects.class);
        navigation = addSystem(NautilusNavigation.class);
    }

    public void setUseDebugging(boolean b) {
        useDebugging = b;
    }


    public void gotoAscendPrepPosition() {
        shoulder.setPosition(NautilusConfiguration.SHOULDER_ASCEND_PREP_MAX);
        arm.setPosition(NautilusConfiguration.ARM_POSITION_MAX);
    }

    public void gotoAscendedPosition() {
        arm.setPosition(NautilusConfiguration.ARM_POSITION_ASCENDED);
        sleep(3000);
        shoulder.setPosition(NautilusConfiguration.SHOULDER_POSITION_ASCENDED);
    }

    public void lockManipulatorPositions() {
        int shoulderPosition = shoulder.getPosition();
        int armPosition = arm.getPosition();

        shoulder.setPosition(shoulderPosition);
        arm.setPosition(armPosition);

    }

    public void shoulderOverdrive() {
        int shoulderPosition = shoulder.getPosition() + 800;
        shoulder.setPosition(shoulderPosition, 0.7);

    }

//    public void driveToPositionOld(double targetY, double targetX, double targetH) {
//        double error = 0;
//        double yError, xError, yawError;
//        double forward, strafe, rotate;
//        SparkFunOTOS.Pose2D pose;
//
//        // wondering if x and y are exchanged here?
//        do {
//            pose = navigation.otos.getPosition();
//            yError = targetY - pose.y;
//            xError = targetX - pose.x;
//            yawError = NautilusNavigation.calculateYawError(pose.h, targetH);
//            error = Math.max(Math.abs(xError), Math.abs(yError));
//            error = Math.max(error, Math.abs(yawError / 5));
//            double currentYawRadians = pose.h * 3.1415 / 180;
//            double rotationX = xError * Math.cos(-currentYawRadians) - yError * Math.sin(-currentYawRadians);
//            double rotationY = xError * Math.sin(-currentYawRadians) + yError * Math.cos(-currentYawRadians);
//
//            forward = Range.clip(rotationY * 1, -0.2, 0.2);
//            rotate = Range.clip(yawError * 1, -0.2, 0.2);
//            strafe = Range.clip( rotationX * 1, -0.2, 0.2);
//            telemetry.addData("y error", yError);
//            telemetry.addData("x error", xError);
//            telemetry.addData("h error", yawError);
//            telemetry.update();
//
//            drivetrain.drive(forward, strafe, rotate * -1);
//        } while (error > NautilusConfiguration.NAVIGATION_TOLERANCE);
//
//    }

    // On NautilusBot, the OTOS is mounted so that the y axis measures forward and backward
    public void driveToPosition(double targetY, double targetX, double targetH) {
        driveToPosition(targetY, targetX, targetH, () -> false,
            NautilusConfiguration.NAVIGATION_Y_KP, NautilusConfiguration.NAVIGATION_Y_KI, NautilusConfiguration.NAVIGATION_Y_KD,
            NautilusConfiguration.NAVIGATION_X_KP, NautilusConfiguration.NAVIGATION_X_KI, NautilusConfiguration.NAVIGATION_X_KD,
            NautilusConfiguration.NAVIGATION_R_KP, NautilusConfiguration.NAVIGATION_R_KI, NautilusConfiguration.NAVIGATION_R_KD,
            NautilusConfiguration.NAVIGATION_Y_MAXPOWER, NautilusConfiguration.NAVIGATION_X_MAXPOWER, NautilusConfiguration.NAVIGATION_R_MAXPOWER
        );
    }
    public void driveToPosition(double targetY, double targetX, double targetH, BooleanSupplier abort, double y_maxPower, double x_maxPower, double r_maxPower) {
        driveToPosition(targetY, targetX, targetH, abort,
            NautilusConfiguration.NAVIGATION_Y_KP, NautilusConfiguration.NAVIGATION_Y_KI, NautilusConfiguration.NAVIGATION_Y_KD,
            NautilusConfiguration.NAVIGATION_X_KP, NautilusConfiguration.NAVIGATION_X_KI, NautilusConfiguration.NAVIGATION_X_KD,
            NautilusConfiguration.NAVIGATION_R_KP, NautilusConfiguration.NAVIGATION_R_KI, NautilusConfiguration.NAVIGATION_R_KD,
                y_maxPower, x_maxPower, r_maxPower
        );
    }
    public void driveToPosition(double targetY, double targetX, double targetH, BooleanSupplier abort,
                                double y_kP, double y_kI, double y_kD,
                                double x_kP, double x_kI, double x_kD,
                                double r_kP, double r_kI, double r_kD,
                                double y_maxPower, double x_maxPower, double r_maxPower) {

        double error = 0;
        double yError = 0, xError = 0, rError = 0, sumError;
        double yPower = 0, xPower = 0, rPower = 0, maxPower;
        double forward = 0, strafe = 0, rotate = 0;
        SparkFunOTOS.Pose2D pose;

        DefenderPIDController xPID = new DefenderPIDController(x_kP, x_kI, x_kD);
        DefenderPIDController yPID = new DefenderPIDController(y_kP, y_kI, y_kD);
        DefenderPIDController rPID = new DefenderPIDController(r_kP, r_kI, r_kD);
        rPID.setUsesAngles(true);

        boolean y_include = true;
        boolean x_include = true;
        boolean r_include = true;

        boolean keepLooping = true;
        boolean isSettling = false;
        ElapsedTime settlingTimer = new ElapsedTime();

        do {
            pose = navigation.otos.getPosition();
            if (y_include) {
                yError = targetY - pose.y;
                if (yError == 0) {
                    y_include = false;
                }
            }
            if (x_include) {
                xError = targetX - pose.x;
                if (xError == 0) {
                    x_include = false;
                }
            }
            if (r_include) {
                rError = NautilusNavigation.calculateYawError(-pose.h, targetH);
                if (rError == 0) {
                    r_include = false;
                }
            }

            if (abort.getAsBoolean()) {
                keepLooping = false;
            } else if (
                !isSettling &&
                (Math.abs(yError) <= NautilusConfiguration.NAVIGATION_TOLERANCE_Y) &&
                (Math.abs(xError) <= NautilusConfiguration.NAVIGATION_TOLERANCE_X) &&
                (Math.abs(rError) <= NautilusConfiguration.NAVIGATION_TOLERANCE_R)
            ) {
                isSettling = true;
                settlingTimer.reset();

            } else if (isSettling && (settlingTimer.milliseconds() > NautilusConfiguration.NAVIGATION_SETTLING_TIME)) {
                if (
                    (Math.abs(yError) <= NautilusConfiguration.NAVIGATION_TOLERANCE_Y) &&
                    (Math.abs(xError) <= NautilusConfiguration.NAVIGATION_TOLERANCE_X) &&
                    (Math.abs(rError) <= NautilusConfiguration.NAVIGATION_TOLERANCE_R)
                ) {
                    keepLooping = false;
                } else {
                    isSettling = false;
                }
            }


            sumError = yError + xError + rError;

            if (y_include) {
                yPower = yPID.calculate(targetY, pose.y);
            } else {
                yPower = 0;
            }
            if (x_include) {
                xPower = xPID.calculate(targetX, pose.x);
            } else {
                xPower = 0;
            }
            if (r_include) {
                rPower = rPID.calculate(targetH, -pose.h);
            } else {
                rPower = 0;
            }

            // Not sure this needed to be a sum, nor why I had it as such.
//            maxPower = Math.max(Math.abs(yPower) + Math.abs(xPower) + Math.abs(rPower), 1);
            maxPower = Math.max(Math.abs(yPower), 1);
            maxPower = Math.max(maxPower, Math.abs(xPower));
            maxPower = Math.max(maxPower, Math.abs(rPower));


            forward = NautilusNavigation.scalePower(yPower, maxPower, y_maxPower);
            strafe = NautilusNavigation.scalePower(xPower, maxPower, x_maxPower);
            rotate = NautilusNavigation.scalePower(rPower, maxPower, r_maxPower);

//            drivetrain.driveNoProportional(forward, strafe, rotate);
            drivetrain.drive(forward, strafe, rotate);

            if (useDebugging) {

                telemetry.addData("now", pose.x + ", " + pose.y + ", " + pose.h);
                telemetry.addData("target", targetX + ", " + targetY + ", " + targetH);
                //            telemetry.addData("x", strafe);
                //            telemetry.addData("y", forward);
                //            telemetry.addData("h", rotate);

                telemetry.addData("y", yError + " : " + yPower + " : " + forward);
                telemetry.addData("x", xError + " : " + xPower + " : " + strafe);
                telemetry.addData("h", rError + " : " + rPower + " : " + rotate);
                telemetry.addData("sum error", sumError);

                telemetry.addData("y tol", (Math.abs(yError) > NautilusConfiguration.NAVIGATION_TOLERANCE_Y));
                telemetry.addData("x tol", (Math.abs(xError) > NautilusConfiguration.NAVIGATION_TOLERANCE_X));
                telemetry.addData("r tol", (Math.abs(rError) > NautilusConfiguration.NAVIGATION_TOLERANCE_R));
                telemetry.addData("abort", !abort.getAsBoolean());
                telemetry.addData("isSettling", isSettling);
                telemetry.addData("keepLooping", keepLooping);

                telemetry.update();
            }

        } while (keepLooping);
//        } while (
//            (
//                (Math.abs(yError) > NautilusConfiguration.NAVIGATION_TOLERANCE_Y) ||
//                (Math.abs(xError) > NautilusConfiguration.NAVIGATION_TOLERANCE_X) ||
//                (Math.abs(rError) > NautilusConfiguration.NAVIGATION_TOLERANCE_R)
//            ) && !abort.getAsBoolean()
//        );
        drivetrain.stopDriving();
//        } while (sumError > 0.1);

    }




}