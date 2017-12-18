# Super Zwierzaki - Rules

# 1. Definitions:
- **Half of x** := x/2 if number is even and (x+1)/2 if number is odd.
- **Discard** := an action of putting a card below card played before.
- **Losing a turn** := being forced to make an empty turn.
- **Hand** := player cards.
- **N** := number of players

# 2. Game preparation:
- Each player gets 5 random cards.
- One card is put face up in the middle (it starts the hip), its colour and class are a given.
- Every player randomly gets natural number from an interval [0, N − 1]. (We call a player who gets k: Player k.)
- Player 0 starts a game.
- ∀n ∈ Z/N : Player n + 1 plays after player n

# 3. Player's turn:
In every turn a player has two possibilities:
- Play a card/cards according to the rules.
- Draw a card

**Exception**: Nonzero red/orc/green/demand points.
The player’s move ends after he says so, or 45 seconds pass.

# 4 Cards:
Cards are divided in two types:
- Functional
- Nonfunctional

Every card has two or three features:
- Colour
- Class
- Function (only functional)

# 4.1. Playing a card:
After every turn the class and the colour are given (might be empty, might more than one). If player decide to play a card, he must play a one with the same colour or class as given. Furthermore, player can play any number of exactly the same cards as he wants.(see 4.3) After player plays a card, class and colour is changed to the ones of a card he has played.

# 4.2. Functional cards:

**“Czerwone karty” (Red)**
- Adds two red points.
- If player starts his turn and amount of red points is nonzero, he is forced to do a move which will change amount of red points. If he don’t, he draws as many cards as there are red points, than red points are reset.
- Can be played in a duel to add two duel points.

**“Orka” (Orc)**
- Adds three orc points.
- If player starts his turn and amount of orc points is nonzero, he is forced to do a move which will change amount of orc points. If he can’t, he draws as many cards as there are orc points, than orc points are reset.
- Player can change colour.

**“Niebieska małpa” (Oth)**
- Causes a duel, (see 5.1). Every player except winner of the duel draws as many cards as there are duel points.
- Can be played in a duel (only caused by (Oth)) to add one duel points.

**“Antylopa/Gepard” (Giv)**
- Causes a duel, (see 5.1). Winner can give to another player as many cards as there are duel points, except, if he has more than one card, he has to keep one of them.
- Can be played in a duel (only caused by (Giv)) to add one duel points.

**“Wielka 5” (All)**
- Has every colour and class.
- After it is played, amount of red points is zero.
- Can be put on red card in a duel, changes number of duel points to zero.

**“Polak” (Pol)**
- Player can discard half of his cards.

**“Kazuar” (Kaz)**
- Player can give half of his cards to another player.

**“Waran” (War)**
- Player must choose two players. They switch their hands.

**“Jeleń” (Cch)**
- Player can change given colour.

**“Wioślak” (Mch)**
- Before end of the player’s turn: ∀n ∈ Z/N : Player n gets number N - n + 1.

**“Mangusta/Ratel” (Dem)**
- Player names a nonfunctional card. One demand point is added. As long as there is a nonzero number of demand points only allowed cards are named cards or (Dem). If on the beginning of player’s next turn there is exactly the same amount of demand points as at the end of the previous one, demand points are reset.

**“Motyl/Pszczoła” (Stp)**
- If player starts his turn and amount of stop points is nonzero, he is forced to do a move which will change amount of green points. If he can’t, he loses as many turns as is the amount of green points and points are reset.

**“Dzięcioł” (Dzi)**
- Can be played to reject "9 kolorów" power. (Before player gives all cards except one).
- Can be played to prevent someone from winning, i.e. If player 1 plays last cars, player 2 can play (Dzi), player 1 has to draw a card.

**“Cyraneczka” (Cyr)**
- Any type of points is reset.
- Can be played in any moment of the game to reject any functional card or combination played.
- Can be played to reject "9 kolorów" power. (Before player gives all cards except one).
- Can be played to prevent someone from winning, i.e. If player 1 plays last cars, player 2 can play (Cyr), player 1 has to draw a card.

# 4.3. Combinations:
**Two exactly the same cards**
- Player discards one card.

**Three exactly the same cards**
- The same as (Pol)

**Four exactly the same cards**
- The same as (Kaz)

**Five exactly the same cards**
- Player can discard all of his cards except one.

**Two (War)**
- Player must choose σ ∈ S(N) and two special players. One of special player chosen by the player, gives randomly chosen half of his cards to another special player. Then players switch their hands with respect to natural bijection between σ and 1, ..., N.

# 5. Another special rules:

# 5.1. Duel:
If card causes a duel, duel starts, number of duel points is set on 1. Every player has two possibilities:
- Fold.
- Play a card.

If player folds, he instantly loses a duel. Otherwise, he can play only a card, which would change number of duel points. Players do their moves with respect to normal queue, starting with the one who caused a duel, as long as amount of duel points is nonzero. If every players except one fold and number of duel points is nonzero, than the player who has not had fold is a winner

# 5.2. 9 kolorów:
If player has a card in every nine colours (Here we assume set of (All)’s colours is empty) he can play that nine cards in one turn. First played card has to be in current given colour or class. Next he chose another player and gives him all his cards except one.

# 6. Codziennie niskie ceny:
If after some action player has only one card, he has to say: **“Codziennie niskie ceny”** . Otherwise, he draws two cards.

# 7. End of the game:
If after his turn a player has no cards, and ten second pass, he wins.
