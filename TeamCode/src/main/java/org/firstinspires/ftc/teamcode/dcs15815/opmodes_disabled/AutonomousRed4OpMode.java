package org.firstinspires.ftc.teamcode.dcs15815.opmodes_disabled;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderAlliance;

@Disabled
@Autonomous(name = "Red - 2 Samples", group = "4", preselectTeleOp="TwoGamepadTeleOpMode")
public class AutonomousRed4OpMode extends Autonomous4bOpMode {
    public void setAlliance() {
        DefenderAlliance.getInstance().setColor(DefenderAlliance.Color.RED);
    };
}