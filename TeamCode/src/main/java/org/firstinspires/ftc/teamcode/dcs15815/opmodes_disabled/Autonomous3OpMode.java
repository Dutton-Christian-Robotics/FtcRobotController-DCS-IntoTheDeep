package org.firstinspires.ftc.teamcode.dcs15815.opmodes_disabled;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.dcs15815.NautilusBot.NautilusConfiguration;
import org.firstinspires.ftc.teamcode.dcs15815.opmodes.IntoTheDeepAutonomousOpMode;

@Disabled
public abstract class Autonomous3OpMode extends IntoTheDeepAutonomousOpMode {

    @Override
    public void performAutonomous() {

        bot.shoulder.gotoAutonTravelPosition();

        bot.driveToBotRelativePositionWithTimeout(14,0, 0, 2000, 2400);

        bot.shoulder.setPosition(2000);
        sleep(500);
        bot.arm.setPosition(700);


        bot.driveToBotRelativePositionWithTimeout(18,0, 0, 2000, 3000);

        bot.intake.intake();

        bot.wrist.setPosition(NautilusConfiguration.WRIST_SERVO_POSITION_BOTTOM);
        sleep(1200);
        bot.arm.setPosition(0);
        sleep(1000);
        bot.intake.stop();

    }

}
