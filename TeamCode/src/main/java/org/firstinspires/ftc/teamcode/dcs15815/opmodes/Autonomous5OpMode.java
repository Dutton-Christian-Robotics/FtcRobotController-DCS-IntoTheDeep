package org.firstinspires.ftc.teamcode.dcs15815.opmodes;

import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderAlliance;
import org.firstinspires.ftc.teamcode.dcs15815.NautilusBot.NautilusBot;
import org.firstinspires.ftc.teamcode.dcs15815.NautilusBot.NautilusConfiguration;

public abstract class Autonomous5OpMode extends IntoTheDeepAutonomousOpMode {

    public void prepareForSpecimenDelivery() {
        bot.shoulder.setPosition(NautilusConfiguration.SHOULDER_POSITION_AUTON_SPECIMEN_DELIVERY_PRE);
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

    @Override
    public void performAutonomous() {

// Specimen 1

        delayedPrepareForSpecimenDelivery(750);
        bot.driveToBotRelativePositionWithTimeout(-18, 0, 0, 750, 2400);

        deliverSpecimen();

        bot.driveToBotRelativePosition(16, 0, 0);


//        bot.driveToBotRelativePositionWithTimeout(18, 0, 0, 2000, 3000);

//        bot.intake.intake();


// Specimen 2


    }

}
