package lpnu.denysoliinyk.oi2023.pp.lab3.effect;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lpnu.denysoliinyk.oi2023.pp.lab3.droid.Droid;

@Getter
@ToString
@RequiredArgsConstructor
public abstract class Effect {
	protected final String name;

	public abstract void apply(Droid attacker, Droid defender);
}
