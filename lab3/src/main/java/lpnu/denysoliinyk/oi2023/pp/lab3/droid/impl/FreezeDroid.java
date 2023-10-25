package lpnu.denysoliinyk.oi2023.pp.lab3.droid.impl;

import lpnu.denysoliinyk.oi2023.pp.lab3.droid.Droid;
import lpnu.denysoliinyk.oi2023.pp.lab3.effect.Effects;

public class FreezeDroid extends Droid {
	public FreezeDroid(String name, int health, int damage) {
		super(name, health, damage, Effects.FREEZE_EFFECT);
	}
}
