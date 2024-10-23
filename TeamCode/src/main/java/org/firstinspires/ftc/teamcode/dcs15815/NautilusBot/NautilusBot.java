package org.firstinspires.ftc.teamcode.dcs15815.NautilusBot;


import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderPresets;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderDelayedSequence;

public class NautilusBot extends DefenderBot {

    public NautilusMecanumDrivetrain drivetrain;
    public NautilusShoulder shoulder;
    public NautilusArm arm;
    public NautilusIntake intake;
    public NautilusWrist wrist;

    //    public DefenderPresets<SBBArmPosition> armPresets;
//    public DefenderDelayedSequence grabPixelSequence;

    public NautilusBot(HardwareMap hm, Class configClass, Telemetry t) {
        super(hm, configClass, t);

        drivetrain = addSystem(NautilusMecanumDrivetrain.class);
        shoulder = addSystem(NautilusShoulder.class);
        arm = addSystem(NautilusArm.class);
        intake = addSystem(NautilusIntake.class);
        wrist = addSystem(NautilusWrist.class);

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

}