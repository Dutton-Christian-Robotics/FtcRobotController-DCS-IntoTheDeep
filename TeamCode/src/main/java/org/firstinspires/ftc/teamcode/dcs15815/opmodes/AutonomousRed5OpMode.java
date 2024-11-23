package org.firstinspires.ftc.teamcode.dcs15815.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderAlliance;

@Autonomous(name = "Red - 2 Specimens", group = "5", preselectTeleOp="TwoGamepadTeleOpMode")
public class AutonomousRed5OpMode extends Autonomous5OpMode {
    public void setAlliance() {
        DefenderAlliance.getInstance().setColor(DefenderAlliance.Color.RED);
    };
}
