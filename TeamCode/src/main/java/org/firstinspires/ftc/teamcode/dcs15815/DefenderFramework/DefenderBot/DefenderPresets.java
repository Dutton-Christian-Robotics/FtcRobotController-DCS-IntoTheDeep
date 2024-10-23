package org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;

import java.util.*;

public class DefenderPresets<T> {
    private ArrayList<T> presets;// = new ArrayList<T>();
    private int currentPosition;
    private boolean loops = false;

    public DefenderPresets() {
	   presets = new ArrayList<>();
	   currentPosition = 0;
    }

    public DefenderPresets(T... values) {
	   presets = new ArrayList<>(Arrays.asList(values));
	   currentPosition = 0;
    }

    public void setLoops(boolean b) {
	   loops = b;
    }


    public void add(T... values) {
	   presets.addAll(Arrays.asList(values));
    }

    public void select(int i) {
	   if (i < 0) {
		  if (loops) {
			currentPosition = presets.size() - 1;
		  } else {
			 currentPosition = 0;
		  }
	   } else if (i >= presets.size()) {
		  if (loops) {
			 currentPosition = 0;
		  } else {
			 currentPosition = presets.size() - 1;
		  }
	   } else {
		  currentPosition = i;
	   }
    }

    public void selectNext() {
	   select(currentPosition + 1);
    }

    public void selectPrevious() {
	   select(currentPosition - 1);
    }

    public T selected() {
	   return presets.get(currentPosition);
    }


}
