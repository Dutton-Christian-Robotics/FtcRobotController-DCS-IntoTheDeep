package org.firstinspires.ftc.teamcode.dcs15815.opmodes;

import org.firstinspires.ftc.teamcode.dcs15815.NautilusBot.NautilusConfiguration;

public abstract class Autonomous5OpMode extends IntoTheDeepAutonomousOpMode {

    public void prepareForSpecimenDelivery() {
        prepareForSpecimenDelivery(0);
    }
    public void prepareForSpecimenDelivery(int delay) {
        bot.shoulder.setPosition(NautilusConfiguration.SHOULDER_POSITION_AUTON_SPECIMEN_DELIVERY_PRE);
        if (delay > 0) {
            sleep(delay);
        }
        bot.arm.setPosition(NautilusConfiguration.ARM_POSITION_AUTON_SPECIMEN_DELIVERY_PRE);
    }

    public void delayedPrepareForSpecimenDelivery(int delay) {
        Runnable b = () -> {
            sleep(delay);
            prepareForSpecimenDelivery();
        };
        b.run();
    }

    public void deliverSpecimen() {
        bot.shoulder.setPosition(NautilusConfiguration.SHOULDER_POSITION_AUTON_SPECIMEN_DELIVERY_POST);
        bot.shoulderOverdrive();
        bot.arm.setPosition(NautilusConfiguration.ARM_POSITION_AUTON_SPECIMEN_DELIVERY_POST);
    }

    public void prepareForSpecimenIntake() {
        bot.shoulder.setPosition(NautilusConfiguration.SHOULDER_POSITION_AUTON_SPECIMEN_INTAKE_PRE);
        bot.arm.setPosition(NautilusConfiguration.ARM_POSITION_AUTON_SPECIMEN_INTAKE_PRE);
    }

    public void intakeSpecimen() {
        bot.shoulder.setPosition(NautilusConfiguration.SHOULDER_POSITION_AUTON_SPECIMEN_INTAKE_POST);
        bot.arm.setPosition(NautilusConfiguration.ARM_POSITION_AUTON_SPECIMEN_INTAKE_POST);

    }

    public void driveWithTimeout(double y, double x, double h, int t1, int t2) {
        bot.driveToBotRelativePositionWithTimeout(y, x, h, t1, t2);
    }

    @Override
    public void performAutonomous() {

        NautilusConfiguration.NAVIGATION_X_MAXPOWER = 0.8;
        NautilusConfiguration.NAVIGATION_Y_MAXPOWER = 0.5;
        NautilusConfiguration.NAVIGATION_R_MAXPOWER = 0.5;

// Specimen 1

//        delayedPrepareForSpecimenDelivery(1500);

        // Deliver pre-loaded specimen
        driveWithTimeout(-12, 0, 0, 750, 2400);
        prepareForSpecimenDelivery(500);
        sleep(900);
        driveWithTimeout(-14, 0, 0, 750, 2400);
        deliverSpecimen();
        sleep(1200);

        // Drive away from the submersible
        driveWithTimeout(24, 0, 0, 750, 4000);

        // Turn so back faces observation zone
        driveWithTimeout(0, 0, 90, 750, 2000);
        bot.navigation.resetOtosAndResetOrigin();

        // Backup to the observation zone
        driveWithTimeout(-56, 0, 0, 750, 5000);

        // Strafe to get flush with walls
        driveWithTimeout(0, -14, 0, 750, 2300);

        // Backup to second specimen and grab it
        driveWithTimeout(-3, 0, 0, 750, 2000);
        intakeSpecimen();
        sleep(1200);

        // Let's go deliver it
        prepareForSpecimenDelivery();
//        driveWithTimeout(0, 6, 0, 750, 2000);
//        bot.correctForAngle(6);
//        bot.navigation.resetOtosAndResetOrigin();
        driveWithTimeout(12, 0, 0, 750, 10000);
        driveWithTimeout(0, 6, 0, 750, 10000);
        bot.correctForAngle(6);
        driveWithTimeout(46, 0, 0, 750, 10000);
        driveWithTimeout(0, 0, -90, 750, 3000);
        bot.navigation.resetOtosAndResetOrigin();
        driveWithTimeout(-21, 0, 0, 750, 4000);
        deliverSpecimen();
        sleep(1200);


        // Goto first sample on floor
//        driveWithTimeout(12, -36, 0, 500, 10000);
//        bot.correctForAngle(6);
//        driveWithTimeout(-34, 0, 0, 500, 10000);
//        driveWithTimeout(0, -10, 0, 500, 10000);
//        bot.correctForAngle(6);

        // Bring first sample to observation zone
//        driveWithTimeout(46, 0, 0, 500, 10000);

//        // Goto second sample on floor
//        driveWithTimeout(-46, 0, 0, 500, 10000);
//        driveWithTimeout(0, -10, 0, 500, 10000);
//        bot.correctForAngle(6);

//        // Bring second sample to observation zone
//        driveWithTimeout(46, 0, 0, 500, 10000);


        // Give time for human player to retrieve first sample
//        driveWithTimeout(-11, 0, 0, 500, 10000);
//        prepareForSpecimenIntake();
//        sleep(500);
//        driveWithTimeout(0, 0, 90, 500, 3000);
//        bot.navigation.resetOtosAndResetOrigin();
//        driveWithTimeout(-14, 0, 0, 500, 3000);
//        driveWithTimeout(0, -14, 0, 500, 5000);
//        intakeSpecimen();
//        sleep(1200);




//        driveWithTimeout(-46, 0, 0, 500, 10000);
//        driveWithTimeout(0, -10, 0, 500, 10000);
//        bot.correctForAngle(6);
//        driveWithTimeout(46, 0, 0, 500, 10000);


//        bot.driveToBotRelativePositionWithTimeout(18, 0, 0, 2000, 3000);

//        bot.intake.intake();


// Specimen 2


    }

}
