package org.firstinspires.ftc.teamcode.dcs15815.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderAlliance;

@Autonomous(name = "Red - 2 Samples", group = "4", preselectTeleOp="TwoGamepadTeleOpMode")
public class AutonomousRed4OpMode extends Autonomous4bOpMode {
    public void setAlliance() {
        DefenderAlliance.getInstance().setColor(DefenderAlliance.Color.RED);
    };
}
