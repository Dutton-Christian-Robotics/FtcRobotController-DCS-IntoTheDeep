package org.firstinspires.ftc.teamcode.dcs15815.opmodes_disabled;

import org.firstinspires.ftc.teamcode.dcs15815.opmodes.IntoTheDeepAutonomousOpMode;


public abstract class Autonomous0OpMode extends IntoTheDeepAutonomousOpMode {

    @Override
    public void performAutonomous() {

        bot.driveToBotRelativePosition(0, -4, 0);
        bot.navigation.resetOrigin();
        bot.driveToBotRelativePosition(36, 0, 0);
        bot.navigation.resetOrigin();

    }

}
