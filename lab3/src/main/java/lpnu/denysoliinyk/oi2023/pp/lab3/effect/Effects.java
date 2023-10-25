package lpnu.denysoliinyk.oi2023.pp.lab3.effect;

import lpnu.denysoliinyk.oi2023.pp.lab3.effect.impl.FireEffect;
import lpnu.denysoliinyk.oi2023.pp.lab3.effect.impl.FreezeEffect;

public abstract class Effects {
	public static final Effect FIRE_EFFECT = new FireEffect();
	public static final Effect FREEZE_EFFECT = new FreezeEffect();
}
