package org.firstinspires.ftc.teamcode.dcs15815.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderAlliance;

@Autonomous(name = "Red - 1 Specimen", group = "3", preselectTeleOp="TwoGamepadTeleOpMode")
public class AutonomousRed3OpMode extends Autonomous3OpMode {
    public void setAlliance() {
        DefenderAlliance.getInstance().setColor(DefenderAlliance.Color.RED);
    };
}
