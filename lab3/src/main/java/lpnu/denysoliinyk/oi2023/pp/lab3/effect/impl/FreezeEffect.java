package lpnu.denysoliinyk.oi2023.pp.lab3.effect.impl;

import lpnu.denysoliinyk.oi2023.pp.lab3.droid.Droid;
import lpnu.denysoliinyk.oi2023.pp.lab3.effect.Effect;

public class FreezeEffect extends Effect {
	public FreezeEffect() {
		super("Freeze Effect");
	}

	@Override
	public void apply(Droid attacker, Droid defender) {
		defender.setDamage((int) (defender.getDamage() * 0.95));
	}
}
