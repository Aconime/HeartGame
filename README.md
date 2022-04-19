# Heart Game - Documentation

## Authentication
### API Script

###### • CONNECTION TO MYSQL

```php
<?php
  // Sever and Database details
  $servername = "localhost";
  $username = "root";
  $password = "";
  $dbname = "heartgame_db";

  // Connect to mysqli using provided details
  $conn = mysqli_connect($servername, $username, $password, $dbname);
?>
```

------

###### • API ENDPOINT

```php
<?php 
  include "db.conn.php"; // Access server & database details
  if ($conn) echo "Connected successfully"; // Check Connection (Only for debug)
?>
```

Check if the connection has been made successfully to the `mysql` server.

------

### Database Details

| FIELD NAMES   | ACCEPTED VALUES         | ADDITIONAL DESCRIPTION                                       |
| ------------- | ----------------------- | ------------------------------------------------------------ |
| player_id     | INT, AUTO_INCREMENT(+1) | This is the key of the table and helps allocate a specific user to a specific row within a table. |
| player_uname  | STRING (VAR_CHAR)       | The username of the player (always unique).                  |
| player_pwd    | STRING (VAR_CHAR)       | The password of the player (used to sign in and play the game). Fully encrypted with `bcrypt` easily implemented via `PHP`. |
| player_email  | STRING (VAR_CHAR)       | The email of the player, required in-case user forgets password. (Optional) |
| player_wins   | INT                     | The number of times the play has won a round of the game. (Guessed the right answer) |
| player_losses | INT                     | The number of times the play has lost a round of the game. (Guessed the wrong answer) |
| player_gp     | INT                     | The number of times the player has played a game. (Might not be included in the final version) |
| player_timing | INT                     | The amount of hours the player has spent playing the game.   |
| player_rank   | STRING (VAR_CHAR)       | The rank of the player, a `static value` custom made by the client app. |

Additional player details will be included in future. For example the ability to track the rank of the user by using experience points.

## Coins & Game Economy
### About Coins

A shopping system will be implemented within the game, where players can use to play the game. Upon registration each player will be given `100` coins to spend for the games. If a player loses all their coins in the games and has an amount of `0` coins, they can purchase more coins by exchanging their `XP`.

### The Game Economy

• For each round in a game will spend `-10` coins. To gain more coins the user must win the round.

• Each round won is `+20` coins (the overall gain is `+10` coins as `10` coins were spent to start the game / round).

• Each round lost is `-10` coins (or the coins initially spent).

• Once player reaches `0` coins they can no longer player games.

• A player can sell their `XP` in exchange for coins (but `XP` **cannot** be purchased with coins)

• If the player has no `XP` to exchange for coins nor `coins` then they can wait for `1 hour` to get an additional `10` coins added to their balance.

------

### Coins & XP Transaction

• `1000` XP -> `10` Coins

• `5000` XP -> `55` Coins

• `10000` XP -> `110` Coins

• `50000` XP -> `600` Coins

## Ranks & Experience
### About Ranks & Experience

The Game will implement a ranking system which will allow the user to advance to more advanced levels. This system is based on experience points, which you can gain by winning games or lose by losing games.

Each game will have a fixed amount of experience points to offer depending on the rank a player has. Once a player selects an answer the game will calculate the `XP` and the player will receive the corresponding amount.

------

### Available Ranks

| RANKS        | EXPERIENCE POINTS  | XP / ROUND WON   | XP / ROUND LOST  |
| ------------ | ------------------ | ---------------- | ---------------- |
| Beginner     | 0 - 1.500          | `+550` / Round   | `-250` / Round   |
| Advanced     | 1.500 - 4.000      | `+600` / Round   | `-400` / Round   |
| Professional | 4.000 - 10.000     | `+800` / Round   | `-650` / Round   |
| Legend       | 10.000 - 15.000    | `+1500` / Round  | `-800` / Round   |
| Genius       | 15.000 - 50.000    | `+4500` / Round  | `-3500` / Round  |
| Undefeated   | 50.000 - 100.000   | `+10000` / Round | `-8500` / Round  |
| Mastermind   | 100.000 - 500.000+ | `+50000` / Round | `-15000` / Round |

Each rank level requires a set amount of experience points to progress to the next level. **All new players start with 0 points.**

## Leaderboards

### About Leaderboards

A leaderboard will display a list of the top 10 best players on specific categories from all registered players. This can include:

- Highest XP.
- Most consecutive wins.
- Most hours spend within the game.
- Most rounds played.
