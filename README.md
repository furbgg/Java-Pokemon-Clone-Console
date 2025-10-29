> **Note: Language Context**
>
> Please note that this project was written in **German** as part of my `Ausbildung` (German vocational training). Therefore, all variable names, comments, and package names are in German.

---

# Javamon Battle v1 (Java Console RPG)

## About This Project

This is a complete, feature-rich, turn-based RPG battle simulation, heavily inspired by Pokémon, built entirely in Java for the console. 🎮

This project showcases **Object-Oriented Programming (OOP)** principles, clean architecture, and advanced console manipulation techniques.

## Key Features

### 1. Robust OOP Design

The project is built using a clean, multi-layered architecture:
* **`Javamon.java` / `PokAttacke.java`**: Core data classes (POJOs) representing the creatures and their attacks. Includes a **copy constructor** in `Javamon` for safe instance creation.
* **`JavamonRepository.java` / `PokAttackeRepository.java`**: Data layer responsible for loading and providing access to game data (currently hardcoded, parses `;`-separated strings). Uses `Map`s for efficient lookups by ID and name.
* **`SpielLogik.java` (Game Logic Layer)**: Contains the core "brain" of the game, including:
    * Player/Computer selection.
    * Damage calculation logic.
    * Random attack assignment.
    * Turn-based battle loop management (`while (hp > 0)`).
    * Speed-based turn determination.
* **`KonsolenIU.java` (Console UI Layer)**: Handles all user interaction and visual output:
    * **ASCII Art:** Displays detailed ASCII representations of the Javamon.
    * **Animated Welcome/Attack/Winner Screens:** Uses `Thread.sleep` and console clearing for dynamic animations. Includes a cool `printSideBySide` utility.
    * **Robust User Input (`tryRead*`)**: Crash-proof methods for reading integers and strings from the user.
    * **Colored Health Bars (`drawHealthBar`, `ConsoleColors.java`)**: Dynamically draws and colors health bars based on current HP using ANSI escape codes.
* **`SoundManager.java`**: Adds audio feedback by playing `.wav` sound files for key events. 🔊
* **`App.java` (Main Entry Point)**: Ties all the components together and runs the main game loop, including the "Play Again?" feature.

### 2. Gameplay Mechanics

* Select your Javamon by ID or Name.
* The computer selects a random opponent.
* Each Javamon gets 4 random attacks.
* The faster Javamon attacks first.
* Basic damage calculation formula is implemented.
* The battle continues until one Javamon's HP reaches 0.

## Project Evolution: v2 (In Progress)

This `v1` uses hardcoded data within the `Repository` classes. The **next version (v2)** is currently under development and will introduce a more robust **Data Management** system, likely involving reading game data from external files (e.g., CSV, JSON) or potentially a database. This will make the game much more scalable and easier to modify.
