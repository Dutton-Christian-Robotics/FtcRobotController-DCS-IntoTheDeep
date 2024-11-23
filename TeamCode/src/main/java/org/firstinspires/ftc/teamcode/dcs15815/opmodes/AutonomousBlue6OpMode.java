package org.firstinspires.ftc.teamcode.dcs15815.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderAlliance;

@Autonomous(name = "Blue - 3 Samples", group = "6", preselectTeleOp="TwoGamepadTeleOpMode")
public class AutonomousBlue6OpMode extends Autonomous6OpMode {

    public void setAlliance() {
        DefenderAlliance.getInstance().setColor(DefenderAlliance.Color.BLUE);
    };

}
