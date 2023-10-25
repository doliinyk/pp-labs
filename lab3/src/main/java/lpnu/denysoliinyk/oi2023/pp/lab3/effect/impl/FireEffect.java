package lpnu.denysoliinyk.oi2023.pp.lab3.effect.impl;

import lpnu.denysoliinyk.oi2023.pp.lab3.droid.Droid;
import lpnu.denysoliinyk.oi2023.pp.lab3.effect.Effect;

public class FireEffect extends Effect {
	public FireEffect() {
		super("Fire Effect");
	}

	@Override
	public void apply(Droid attacker, Droid defender) {
		attacker.setDamage((int) (attacker.getDamage() * 1.05));
	}
}
