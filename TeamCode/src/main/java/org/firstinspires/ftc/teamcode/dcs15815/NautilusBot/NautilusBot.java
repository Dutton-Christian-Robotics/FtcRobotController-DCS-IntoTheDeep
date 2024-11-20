package org.firstinspires.ftc.teamcode.dcs15815.NautilusBot;


import org.firstinspires.ftc.robotcore.external.Telemetry;

import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderOldPIDController;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderPIDController;

public class NautilusBot extends DefenderBot {

    public NautilusMecanumDrivetrain drivetrain;
    public NautilusShoulder shoulder;
    public NautilusArm arm;
    public NautilusIntake intake;
    public NautilusWrist wrist;
    public NautilusEffects effects;
    public NautilusNavigation navigation;

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

//        leds = addSystem(SBBEffectsLeds.class);

    }

//    public void gotoArmPosition(SBBArmPosition p) {
//        lift.setPosition(p.liftPosition);
//        tilt.setPosition(p.tiltPosition);
//        wrist.setPosition(p.wristPosition);
//    }
//
//    public void gotoNextArmPosition() {
//        armPresets.selectNext();
//        gotoArmPosition(armPresets.selected());
//    }
//
//    public void gotoPreviousArmPosition() {
//        armPresets.selectPrevious();
//        gotoArmPosition(armPresets.selected());
//    }
//
//    public void gotoGrabContactArmPosition() {
//        gotoArmPosition(SBBConfiguration.GRAB_CONTACT_POSITION);
//    }
//    public void gotoGrabReadyArmPosition() {
//        gotoArmPosition(SBBConfiguration.GRAB_READY_POSITION);
//    }
//
//    public void runGrabPixelsMacro() {
//        grabPixelSequence.run();
//    }
//
//
//    public void gotoHangArmPosition() {
//        gotoArmPosition(SBBConfiguration.HANG_POSITION);
//    }
//    public void gotoHangingArmPosition() {
//        gotoArmPosition(SBBConfiguration.HANGING_POSITION);
//    }
//
//    public void gotoTravelArmPosition() {
//        gotoArmPosition(SBBConfiguration.TRAVEL_POSITION);
//    }
//
//    public void gotoLeaveStackArmPosition() {
//        gotoArmPosition(SBBConfiguration.LEAVE_STACK_POSITION);
//    }
//
//    public void gotoAutonomousDropArmPosition() {
//        gotoArmPosition(SBBConfiguration.AUTONOMOUS_DROP_POSITION);
//    }
//
//    public void gotoAutonomousFrontDeliveryArmPosition() {
//        gotoArmPosition(SBBConfiguration.AUTONOMOUS_FRONT_DELIVERY_POSITION);
//    }
//
//    public void gotoMidFrontDeliveryPosition() {
//        gotoArmPosition(SBBConfiguration.MID_FRONT_DELIVERY_POSITION);
//    }
//
//    public void gotoHighFrontDeliveryPosition() {
//        gotoArmPosition(SBBConfiguration.HIGH_FRONT_DELIVERY_POSITION);
//    }
//
//
//    public void gotoHighBackDeliveryPosition() {
//        gotoArmPosition(SBBConfiguration.HIGH_BACK_DELIVERY_POSITION);
//    }
//
//    public void gotoStartArmPosition() {
//        gotoArmPosition(SBBConfiguration.START_POSITION);
//    }

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

    public void driveToPositionOld(double targetY, double targetX, double targetH) {
        double error = 0;
        double yError, xError, yawError;
        double forward, strafe, rotate;
        SparkFunOTOS.Pose2D pose;

        // wondering if x and y are exchanged here?
        do {
            pose = navigation.otos.getPosition();
            yError = targetY - pose.y;
            xError = targetX - pose.x;
            yawError = NautilusNavigation.calculateYawError(pose.h, targetH);
            error = Math.max(Math.abs(xError), Math.abs(yError));
            error = Math.max(error, Math.abs(yawError / 5));
            double currentYawRadians = pose.h * 3.1415 / 180;
            double rotationX = xError * Math.cos(-currentYawRadians) - yError * Math.sin(-currentYawRadians);
            double rotationY = xError * Math.sin(-currentYawRadians) + yError * Math.cos(-currentYawRadians);

            forward = Range.clip(rotationY * 1, -0.2, 0.2);
            rotate = Range.clip(yawError * 1, -0.2, 0.2);
            strafe = Range.clip( rotationX * 1, -0.2, 0.2);
            telemetry.addData("y error", yError);
            telemetry.addData("x error", xError);
            telemetry.addData("h error", yawError);
            telemetry.update();

            drivetrain.drive(forward, strafe, rotate * -1);
        } while (error > NautilusConfiguration.NAVIGATION_TOLERANCE);

    }

    // Our sensor is mounted y forward and back
    public void driveToPosition(double targetY, double targetX, double targetH) {
        double error = 0;
        double yError, xError, yawError, maxError, avgError;
        double yPower, xPower, rPower, maxPower;
        double forward, strafe, rotate;
        SparkFunOTOS.Pose2D pose;

        DefenderOldPIDController xPID = new DefenderOldPIDController(1, 0, 0);
        DefenderOldPIDController yPID = new DefenderOldPIDController(0.5, 0, 3);
        DefenderOldPIDController rPID = new DefenderOldPIDController(1, 0, 0);

        do {
            pose = navigation.otos.getPosition();
            yError = targetY - pose.y;
            xError = targetX - pose.x;
            yawError =  NautilusNavigation.calculateYawError(-pose.h, targetH);
            maxError = yError + xError + yawError;
            avgError = maxError / 3;

            yPower = yPID.calculatePower(targetY, pose.y);
            xPower = xPID.calculatePower(targetX, pose.x);
            rPower = rPID.calculatePower(targetH, -pose.h);
            maxPower = Math.max(Math.abs(yPower) + Math.abs(xPower) + Math.abs(rPower), 1);



            forward = Range.clip(Math.floor((yPower / maxPower) * 1000) / 1000, -NautilusConfiguration.NAVIGATION_MAXSPEED_Y, NautilusConfiguration.NAVIGATION_MAXSPEED_Y);
            strafe = Range.clip(Math.floor((xPower / maxPower) * 1000) / 1000, -NautilusConfiguration.NAVIGATION_MAXSPEED_X, NautilusConfiguration.NAVIGATION_MAXSPEED_X);
            rotate = Range.clip(Math.floor((rPower / maxPower) * 1000) / 1000, -NautilusConfiguration.NAVIGATION_MAXSPEED_R, NautilusConfiguration.NAVIGATION_MAXSPEED_R);


            //            yError = targetY - pose.y;
//            xError = targetX - pose.x;
//            yawError = NautilusNavigation.calculateYawError(-pose.h, targetH);
//            max = Math.max(Math.abs(yError) + Math.abs(xError) + Math.abs(yawError), 1);

//            error = Math.abs(yError + xError + yawError);


//            forward = Range.clip(Math.floor((yError / max) * 1000) / 1000, -NautilusConfiguration.NAVIGATION_MAXSPEED_X, NautilusConfiguration.NAVIGATION_MAXSPEED_X);
//            strafe = Range.clip(Math.floor((xError / max) * 1000) / 1000, -NautilusConfiguration.NAVIGATION_MAXSPEED_Y, NautilusConfiguration.NAVIGATION_MAXSPEED_X);
//            rotate = Range.clip(Math.floor((yawError / max) * 1000) / 1000, -NautilusConfiguration.NAVIGATION_MAXSPEED_R, NautilusConfiguration.NAVIGATION_MAXSPEED_R);
            drivetrain.drive(forward, strafe, rotate);

            telemetry.addData("now", pose.x + ", " + pose.y + ", " + pose.h);
            telemetry.addData("target", targetX + ", " + targetY + ", " + targetH);
//            telemetry.addData("x", strafe);
//            telemetry.addData("y", forward);
//            telemetry.addData("h", rotate);

            telemetry.addData("x", xError + " : " + strafe);
            telemetry.addData("y", yError + " : " + forward);
            telemetry.addData("h", yawError + " : " + rotate);
            telemetry.addData("max error", maxError);
            telemetry.addData("error", error);

            telemetry.update();


        } while (avgError > NautilusConfiguration.NAVIGATION_TOLERANCE);
        drivetrain.stopDriving();
//        } while (maxError > 0.1);

    }




}