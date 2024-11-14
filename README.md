
# Jeu Tic-Tac-Toe Client/Serveur

Projet de jeu de Tic-Tac-Toe en architecture client/serveur.

## Description du projet

Ce programme met en œuvre un jeu Tic-Tac-Toe multijoueur en utilisant une architecture client/serveur :
- **Client** : Chaque joueur se connecte en tant que client et peut jouer contre un autre joueur.
- **Serveur** : Le serveur gère les connexions des joueurs, chaque client étant traité dans un thread séparé.

### Fonctionnalités

- **Support multijoueur** : Permet à plusieurs clients de jouer des parties en parallèle.
- **Gestion des threads** : Chaque joueur est géré dans un thread pour permettre la gestion de plusieurs parties en simultané.
- **Historique des coups** : Le serveur garde la trace des coups joués et affiche l’historique.
- **Mises à jour du jeu** : Les clients sont notifiés des changements d’état de la grille après chaque coup.

## Prérequis

- **Java** (JDK 8 ou version supérieure)

## Installation et utilisation

### Étape 1 : Cloner le projet
Clonez le dépôt GitHub pour récupérer les fichiers sources :
```bash
git clone https://github.com/Ismail-Ah/Tic-Tac-Toe.git
cd tic-tac-toe
```

### Étape 2 : Compilation
Compilez les fichiers Java pour générer les exécutables du client et du serveur :
```bash
javac server.java client.java
```

### Étape 3 : Exécution

1. **Démarrer le serveur**
   - Dans un terminal, exécutez le fichier `server.java` pour lancer le serveur.
   ```bash
   java Serveur
   ```

2. **Lancer les clients**
   - Dans d’autres terminaux, exécutez le fichier `client.java` pour démarrer chaque client. 
   ```bash
   java Client
   ```