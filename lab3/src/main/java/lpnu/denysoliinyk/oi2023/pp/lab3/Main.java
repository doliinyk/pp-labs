package lpnu.denysoliinyk.oi2023.pp.lab3;

import lpnu.denysoliinyk.oi2023.pp.lab3.droid.Droid;
import lpnu.denysoliinyk.oi2023.pp.lab3.droid.impl.BattleDroid;
import lpnu.denysoliinyk.oi2023.pp.lab3.droid.impl.FireDroid;
import lpnu.denysoliinyk.oi2023.pp.lab3.droid.impl.FreezeDroid;

import java.io.*;
import java.util.*;

public class Main {
	private static final Scanner scanner = new Scanner(System.in);
	private static final List<Droid> droids = new ArrayList<>();
	private static final File battles = new File("battles.dat");
	private static final Random random = new Random();
	private static StringBuilder lastBattle;

	public static void main(String[] args) {
		while (true) {
			System.out.println("Droid Battle menu:");
			System.out.println("1. Create droid.");
			System.out.println("2. List droids.");
			System.out.println("3. Battle droids 1 vs 1.");
			System.out.println("4. Battle droids team vs team.");
			System.out.println("5. Write last battle to file.");
			System.out.println("6. Resume battle from file.");
			System.out.println("0. Exit from program.");

			System.out.println();
			int n = scanner.nextInt();
			System.out.println();
			scanner.nextLine();

			switch (n) {
				case 1:
					createDroid();
					break;
				case 2:
					listDroids();
					break;
				case 3:
					battleDroids();
					break;
				case 4:
					battleDroidTeams();
					break;
				case 5:
					writeLastBattleToFile();
					break;
				case 6:
					readBattleFromFile();
					break;
				case 0:
				default:
					return;
			}
		}
	}

	private static void createDroid() {
		System.out.print("Input droid name: ");
		String name = scanner.nextLine();

		System.out.print("Input droid health: ");
		int health = scanner.nextInt();

		System.out.print("Input droid damage: ");
		int damage = scanner.nextInt();

		System.out.print("Input droid type (1 - Fire, 2 - Freeze, other - Battle): ");
		int type = scanner.nextInt();
		System.out.println();

		Droid droid = switch (type) {
			case 1 -> new FireDroid(name, health, damage);
			case 2 -> new FreezeDroid(name, health, damage);
			default -> new BattleDroid(name, health, damage);
		};
		droids.add(droid);
	}

	private static void listDroids() {
		droids.forEach(System.out::println);
		if (!droids.isEmpty()) {
			System.out.println();
		}
	}

	private static void battleDroids() {
		System.out.print("Input first droid number you want to battle: ");
		Droid droid1 = droids.get(scanner.nextInt());

		System.out.print("Input second droid number you want to battle: ");
		Droid droid2 = droids.get(scanner.nextInt());
		System.out.println();

		lastBattle = new StringBuilder(droid1.getName()).append(" battle against ").append(droid2.getName()).append("\n")
				.append(droid1).append("\n")
				.append(droid2).append("\n");

		while (droid1.getHealth() > 0 && droid2.getHealth() > 0) {
			lastBattle.append(random.nextBoolean()
					                  ? droid2.attack(droid1)
					                  : droid1.attack(droid2));
		}

		lastBattle.append("Winner is ").append(droid1.getHealth() > 0
				                                       ? droid1.getName()
				                                       : droid2.getName())
				.append("\n");
		System.out.println(lastBattle);
	}

	private static void battleDroidTeams() {
		System.out.println("Input droid numbers you want to battle for first team (-1 to stop):");
		Set<Droid> droids1Set = new HashSet<>();
		int n;
		do {
			n = scanner.nextInt();
			if (n >= 0) {
				droids1Set.add(droids.get(n));
			}
		} while (n != -1);
		System.out.println();

		System.out.println("Input droid numbers you want to battle for second team (-1 to stop):");
		Set<Droid> droids2Set = new HashSet<>();
		do {
			n = scanner.nextInt();
			if (n >= 0) {
				Droid droid = droids.get(n);
				if (!droids1Set.contains(droid)) {
					droids2Set.add(droids.get(n));
				}
			}
		} while (n != -1);
		System.out.println(

		);
		lastBattle = new StringBuilder("Team battle. Team 1 has ").append(droids1Set.size())
				.append(" droids and team 2 has ")
				.append(droids2Set.size())
				.append(" droids\n");
		List<Droid> droids1 = new ArrayList<>(droids1Set);
		List<Droid> droids2 = new ArrayList<>(droids2Set);

		while (droids1.stream().anyMatch(droid -> droid.getHealth() > 0)
				|| droids2.stream().anyMatch(droid -> droid.getHealth() > 0)) {
			boolean rand = random.nextBoolean();
			Droid attacker = rand
					? droids1.get(random.nextInt(droids1.size()))
					: droids2.get(random.nextInt(droids2.size()));
			Droid defender = rand
					? droids2.get(random.nextInt(droids2.size()))
					: droids1.get(random.nextInt(droids1.size()));
			lastBattle.append(attacker.attack(defender));
		}

		lastBattle.append("Winner is ");
		if (droids1.stream().anyMatch(droid -> droid.getHealth() > 0)) {
			lastBattle.append(" first team. Droids: ");
			droids1.forEach(droid -> lastBattle.append(droid.getName()).append(" "));
		} else {
			lastBattle.append(" second team. Droids: ");
			droids2.forEach(droid -> lastBattle.append(droid.getName()).append(" "));
		}
		lastBattle.append("\n");
		System.out.println(lastBattle);
	}

	private static void writeLastBattleToFile() {
		try (FileWriter fileWriter = new FileWriter(battles)) {
			fileWriter.write(lastBattle.toString());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void readBattleFromFile() {
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(battles))) {
			String line;
			lastBattle = new StringBuilder();
			while ((line = bufferedReader.readLine()) != null) {
				lastBattle.append(line).append("\n");
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(lastBattle);
	}
}