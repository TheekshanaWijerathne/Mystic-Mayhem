## Mystic Mayhem – Turn-Based Fantasy Combat Game

**Mystic Mayhem**  is a fully Object-Oriented turn-based battle game between two players, each commanding a custom army of fantasy characters — **Archer, Knight, Mage, Healer, and Mythical Creature**.

The game features:

* **Player Profiles** with unique usernames, changeable display names, and system-generated user IDs.
* **Gold Economy** – Players start with **500 gold coins**, can buy/sell characters and equipment, and earn coins through combat (10% of opponent’s gold on victory).
* **Army Management** – Exactly one character per category, purchasable with gold. Characters have **attack, defence, health, and speed** attributes, which can be enhanced with equipment.
* **Equipment System** – Armour and artefacts modify character stats and affect resale value.
* **Home Grounds & Environment Effects** – Four battleground types with unique buffs/debuffs depending on character origins.
* **Combat Mechanics** – Turn-based fighting, attack targeting rules, healer mechanics, and speed-based attack order with tie-breaking priorities.
* **Result Tracking** – Win increases XP, gold is exchanged, and draws result in no change.
* **Opponent Search** – Players can view limited stats of random opponents and choose to challenge or skip.

### Technical Implementation

* Fully **Object-Oriented Design** (OOP) with modular classes for players, characters, equipment, and battles.
* **Serialization** is used to **save and load player profiles**, preserving data between sessions.
* Detailed **combat log output** matching the required format, including turn-by-turn actions and final battle results.
* Gold and attribute calculations follow game rules, rounded to the nearest whole number or first decimal place as specified.

### Included in the Repository

* **Source Code** (`.java` files)
* **Executable File** (`.jar`) to run the game without compiling
* **Battle Output Log** (`.txt`) containing the full result of a sample battle with "whitewolf"
