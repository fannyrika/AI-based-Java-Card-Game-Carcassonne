## Petite note

Nous avons rencontrés certains problèmes lors de l'affichage des jeux à partir du fichier Menu.java.
Nous avons donc créer un menu "de fortune", test.java, afin de pouvoir lancer les jeux.
Cependant, certaines fonctionnalités présentes dans Menu.java ne le sont pas dans test.java.
Nous partons du principe que vous lancerez donc le fichier test.java afin de pouvoir lancer les jeux mais observerez les possiblités de Menu.java

## Lancement des jeux

# Jeu de dominos (Terminal)
    Les commandes à rentrer dans votre terminal sont:
        $ cd src
        $ javac JeuDominoTerminal.java
        $ java JeuDominoTerminal
    Vous pouvez maintenant commencer votre partie
    
# Jeu de Carcassonne et dominos (Graphique)
    -- Veuillez maximiser chaque fenêtre avant jouer. --
    Les commandes à rentrer dans votre terminal sont (pour test.java):
        $ cd src
        $ javac test.java
        $ java test
    et pour Menu.java:
        $ cd src
        $ javac Menu.java
        $ java Menu
        
# Paramétrage du jeu
    Ce menu se présente de la manière suivante : le premier JTextField permet de choisir entre Carcassonne et Domino (qu’il faudra entrer entièrement en majuscules) et la suite des JTextFields permettent de choisir le pseudo et si le joueur sera humain ou une IA. Il y a été fixé un maximum de 4 joueurs pour les deux jeux. Cependant, si on veut diminuer le nombre de joueurs, il suffira de supprimer les contenues des  2 JTextFields correspondant à un joueur autant de fois que voulu. Après avoir fait ces derniers choix, le joueur peut désormais cliquer sur le bouton « VALIDER ».
    
    Voici le page par défaut (Si les 2 images suivantes n'arrivent pas à s'affichent, vous pouvez les trouver dans le dossiers de README)
    
    ![Untitled](README%200096574dfde34d80b5890ba3e7401e4e/Untitled.png)
    
    Veuillez éditer les champs pour choisir le jeu, choisir le nombre de joueurs et sélectionner humain ou IA pour chaque joueur, voici un exemple
    
    ![Untitled](README%200096574dfde34d80b5890ba3e7401e4e/Untitled%201.png)
    
    Vous pouvez maintenant cliquer VALIDER et commencer votre partie



## Comment jouer
# Jeu de Carcassonne
    - Première étape: Choisir la rotation de la pièce et l'objectif est de ne cliquez qu'une seule fois
                   (sinon, il faudra passer son tour)
    - Deuxième étape: Entrez les cordonnées (x, y) dans les emplacements mis à disposition
    - Troisième étape: Si on veut placer le pion, on y entre un numéro comme dans le schèma indiqué sur la
                       scène et cliquez sur le bouton "Placer le pion".
                       Sinon, on ne fait rien.
    - Quatrième étape: Cliquez sur le bouton "Placer"

# Jeu de dominos (Graphique)
    - Première étape: Choisir la rotation de la pièce et l'objectif est de ne cliquez qu'une seule fois
                      (sinon, il faudra passer son tour)
    - Deuxième étape: Entrez les cordonnées (x, y) dans les emplacements mis à disposition
    - Troisième étape: Cliquez sur le bouton "Placer".

# Jeu de dominos (Terminal)
    - Toutes les étapes sont décrits à chaques tours. -