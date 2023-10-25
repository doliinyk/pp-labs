package lpnu.denysoliinyk.oi2023.pp.lab3.droid;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lpnu.denysoliinyk.oi2023.pp.lab3.effect.Effect;

@Getter
@Setter
@ToString
@AllArgsConstructor
public abstract class Droid {
	private final String name;
	private int health;
	private int damage;
	private final Effect effect;

	public void setHealth(int health) {
		this.health = Math.max(health, 0);
	}

	public String attack(Droid droid) {
		if (getEffect() != null) {
			getEffect().apply(this, droid);
		}

		final int currentDamage = (int) (Math.random() * (getDamage() * 0.3) + getDamage() * 0.85);
		droid.getHit(currentDamage);

		return droid.getName() + " got attacked by " + getName() + " for " + currentDamage + " damage and has remain " + droid.getHealth() + " health\n";
	}

	public void getHit(int damage) {
		setHealth(getHealth() - damage);
	}
}
