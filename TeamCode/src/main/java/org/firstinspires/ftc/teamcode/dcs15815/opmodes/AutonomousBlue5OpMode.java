package org.firstinspires.ftc.teamcode.dcs15815.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderAlliance;

@Autonomous(name = "Blue - 1 Specimen", group = "5", preselectTeleOp="TwoGamepadTeleOpMode")
public class AutonomousBlue5OpMode extends Autonomous5OpMode {
    public void setAlliance() {
        DefenderAlliance.getInstance().setColor(DefenderAlliance.Color.BLUE);
    };
}
