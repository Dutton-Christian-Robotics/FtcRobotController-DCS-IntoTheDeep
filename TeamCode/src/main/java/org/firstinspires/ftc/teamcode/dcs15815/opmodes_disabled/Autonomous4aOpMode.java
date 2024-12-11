package org.firstinspires.ftc.teamcode.dcs15815.opmodes_disabled;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderAlliance;
import org.firstinspires.ftc.teamcode.dcs15815.NautilusBot.NautilusBot;
import org.firstinspires.ftc.teamcode.dcs15815.NautilusBot.NautilusConfiguration;

/*
Alliance Color: Blue or Red
Level: 4
Preload: neutral sample
Starting Position: touching wall, facing towards Observation Zone,
    back of robot aligned with lower edge of tile A5/A6 border
Actions:
    1)
 */
public abstract class Autonomous4aOpMode extends LinearOpMode {
    NautilusBot bot;
    DefenderAlliance.Color allianceColor = DefenderAlliance.Color.UNKNOWN;

    public void deliverSample() {
        // Raise the arm and shoulder
        bot.shoulder.setPosition(NautilusConfiguration.SHOULDER_POSITION_MAX, NautilusConfiguration.SHOULDER_POWER_MAX * .7);
        sleep(500);
        bot.arm.setPosition(NautilusConfiguration.ARM_POSITION_MAX);

        // Wait a little bit
        sleep(1500);

        // Wrist flat
        bot.wrist.gotoUpPosition();

        // Snug up to the baskets
//        bot.driveToPosition(-3, 0, 0);
//        bot.navigation.resetOrigin();

        // Kick out the sample
        bot.intake.expel();
        sleep(1500);
        bot.intake.stop();
        bot.wrist.gotoAutonIntakePosition();
    }

    public void setAlliance() {
        DefenderAlliance.getInstance().setColor(allianceColor);
    };

    @Override
    public void runOpMode() {
        bot = new NautilusBot(hardwareMap, NautilusConfiguration.class, telemetry);

        setAlliance();
        if (DefenderAlliance.getInstance().isRed()) {
            telemetry.addData("Alliance", "RED");
        } else if (DefenderAlliance.getInstance().isBlue()) {
            telemetry.addData("Alliance", "BLUE");
        } else {
            telemetry.addData("Alliance", "unknown");
        }

        if (DefenderAlliance.getInstance().isRed()) {
            bot.effects.scanRed();
        } else if (DefenderAlliance.getInstance().isBlue()) {
            bot.effects.scanBlue();
        } else {
            bot.effects.wavesParty();
        }

        waitForStart();
        bot.navigation.resetOtosAndResetOrigin();

        if (DefenderAlliance.getInstance().isRed()) {
            bot.effects.heartbeatRed();
        } else if (DefenderAlliance.getInstance().isBlue()) {
            bot.effects.heartbeatBlue();
        } else {
            bot.effects.wavesParty();
        }

// Diagonal away from wall to the netzone
        bot.shoulder.gotoAutonTravelPosition();
        bot.driveToBotRelativePosition(-16.5, -8, 0);
        bot.navigation.resetOrigin();


// Turn so we're perpendicular to the baskets
        bot.driveToBotRelativePosition(0, 0, -45);
        bot.navigation.resetOrigin();
        bot.shoulderOverdrive();

// Score 8 points
        deliverSample();

// Get a safe distance from the baskets
        bot.driveToBotRelativePosition(14.25, 0, 0);
        bot.navigation.resetOrigin();

// Prepare for intake: bring the arm and shoulder down
        bot.arm.setPosition(500);
        sleep(500);
        bot.gotoAutonIntakePosition();

// Turn towards neutral samples
        bot.driveToBotRelativePosition(0, 0, -45);
        bot.navigation.resetOrigin();

// Go get outermost neutral sample
        bot.intake.intake();
        bot.driveToBotRelativePosition(26, 0, 0);
        bot.intake.stop();
        bot.navigation.resetOrigin();
        bot.shoulder.gotoAutonTravelPosition();

// Back away from neutral samples
        bot.driveToBotRelativePosition(-28, 0, 0);
        bot.navigation.resetOrigin();

// Turn to align with baskets
        bot.driveToBotRelativePosition(0, 0, 45);
        bot.navigation.resetOrigin();

// Backup to the baskets
        bot.shoulderOverdrive();
        bot.driveToBotRelativePosition(-16, 0, 0);
        bot.navigation.resetOrigin();

// Score another 8 points
        deliverSample();

// Get away from the basets
        bot.driveToBotRelativePosition(14, 0, 0);
        bot.navigation.resetOrigin();

// Bring the arm down
        bot.arm.setPosition(NautilusConfiguration.ARM_POSITION_MIN);
        bot.shoulder.setPosition(NautilusConfiguration.SHOULDER_POSITION_MIN);
        sleep(1200);


        if (DefenderAlliance.getInstance().isRed()) {
            bot.effects.solidRed();
        } else if (DefenderAlliance.getInstance().isBlue()) {
            bot.effects.solidBlue();
        } else {
            bot.effects.wavesParty();
        }

    }

}
