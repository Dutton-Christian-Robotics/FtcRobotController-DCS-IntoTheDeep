package org.firstinspires.ftc.teamcode.dcs15815.opmodes_disabled;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderAlliance;

@Disabled
@Autonomous(name = "Blue - 2 Samples (a)", group = "4", preselectTeleOp="TwoGamepadTeleOpMode")
public class AutonomousBlue4aOpMode extends Autonomous4aOpMode {
    DefenderAlliance.Color allianceColor = DefenderAlliance.Color.BLUE;
}
