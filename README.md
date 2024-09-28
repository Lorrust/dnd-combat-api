# D&D Combat API

## Overview
D&D Combat API is a RESTful web service that allows users to simulate battles between characters and monsters from the Dungeons & Dragons universe.
This API provides endpoints for fetching monster details, conducting battles, and retrieving a list of available monsters.

## Features
- Fetch a list of monster names.
- Retrieve monster details by name.
- Simulate combat between user-defined characters and monsters.
- Check a user-defined character.
- Get a template character to use as request body.

## Technologies
- Java
- Spring Boot
- Spring WebFlux
- WebClient

## Endpoints

### 1. Get Monster Names
**GET** `/monsters/{page}`  
Fetches a page of monster names available (1~65).

#### Response
- **200 OK**
```json
[
  "A-mi-kuk",
  "Aalpamac",
  "Aatxe",
  "Abaasy",
  "Abbanith Giant",
  "Aboleth",
  // more monster names...
]
```

### 2. Get Monster Details
**GET** `/monsters/{name}`  
Fetches the character model of a specific monster.

### Response
- **200 OK**
```json
{
  "name": "Goblin",
  "strength": 8,
  "dexterity": 14,
  "hitPoints": 7,
  "armorClass": 15
}
```

### 3. Simulate Battle
**POST** /battle/{monster}
Simulates a battle between a user-defined character and a specified monster.

### Request Body

```json
{
  "name": "Aragorn",
  "strength": 12,
  "dexterity": 14,
  "hitPoints": 30,
  "armorClass": 15
}
```

### Response
- **200 OK**
```json
{
  "winner": "Aragorn",
  "battleLog": [
    "Aragorn attacked Goblin for 4 damage.",
    "Goblin attacked Aragorn for 1 damage.",
    "Aragorn missed.",
    "Goblin missed.",
    "Aragorn missed.",
    "Goblin missed.",
    "Aragorn attacked Goblin for 6 damage.",
    "Aragorn defeated Goblin."
  ]
}
```

### 4. Check Your Character
**POST** `/check`
Returns a string detailing your character details.

### Request Body

```json
{
  "name": "Aragorn",
  "strength": 12,
  "dexterity": 14,
  "hitPoints": 30,
  "armorClass": 15
}
```

### Response
- **200 OK**
```
Your character looks like this:

Name: Aragorn
Strength: 12
Dexterity: 14
Hit Points: 30
Armor Class: 15
```

### 5. Character Example
**GET** `/example`
Returns a character example, which you can copy and use to battle monsters.

### Response
- **200 OK**
```json
{
  "name": "Kaya",
  "strength": 10,
  "dexterity": 7,
  "hitPoints": 11,
  "armorClass": 12
}
```

### 6. About
**GET** `/sobre`
Returns an object cointaining my name, along with the project title.

### Response
- **200 OK**
```json
{
  "projeto": "D&D Combat API",
  "estudante": "Lucas Silva"
}
```

## How to Run

**1. Clone the repository:**
```bash
git clone https://github.com/Lorrust/dnd-combat-api.git
cd dnd-combat-api
```

**2. Build the project:**

```bash
./mvnw clean install
```

**3. Run the application:**
```bash
./mvnw spring-boot:run
```

**4. Access the API at `http://localhost:8080`.**

## Notes
- This project does not claim ownership over any of the D&D content retrieved through Open5e and uses the API solely for educational and non-commercial purposes.
- This project utilizes the [Open5e API](https://api.open5e.com/) to retrieve data on D&D monsters.
- For more information on Open5e, check out their [documentation](https://open5e.com/).
