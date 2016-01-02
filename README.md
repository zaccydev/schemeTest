#Comparaison de la puissance de calcul obtenue à partir de différents langages de programmation

Le code de ce projet est extrait d'un programme plus important que j'ai développé en Java. 
Ce projet à pour but de comparer la rapidité de différents langages de programmation. 
Même si les résultats restent sans surprise majeure il est intéressant de constater que la puissance de calcul de PHP 7 apporte une très bonne amélioration par rapport à PHP 5.

## Description du programme
Le programme réalise une analyse statistique sur un nombre déterminé de combinaisons extraites à partir des résultats d'un jeu de hasard.
Les données utilisées concernent les résultats du jeu de Keno de la Française des Jeux.
Les données sont récupérées depuis un fichier CSV qui contient le résultat des tirages. Le jeu de Keno est basé sur un tirage aléatoire de 20 numéros parmi 70.

## Description de l'algorithme principal
L'algorithme principal utilise une conception objet et analyse les X (avec X = 100 pour les test réalisés) derniers résultats de façon à déterminer quelles sont les combinaison de trois numéros qui obtiennent la fréquence d'apparition la plus élevée.
Par exemple analyser les 100 derniers résultats revient à déterminer la présence de 5,474,000 combinaisons de trois numéros.
Les résultats sont ensuite triés par ordre croissant selon leur fréquence, on affiche les trois combinaisons de trois numéros avec la fréquence la plus haute.
### Résultats obtenus
Les résultats suivant ont été obtenus avec un processeur de type AMD 64 Dual Core 4800+ :
* PHP5 : 145 secondes
* PHP7 : 42 secondes
* JAVA : 3 secondes

## Utiliser ou tester le programme
Pour cela il suffit de récupérer le code du dépôt et de lancer les commandes suivantes:

###Pour une utilisation avec Java :
Depuis le répertoire ./java : `java -jar ./../schemeTestJava.jar`

###Pour une utilisation avec Php :
Depuis le répertoire ./php : `php main.php`
Ou pour lancer les tests unitaires : `phpunit --bootstrap main.php ./test/`

#### Remarques :
Les fichiers CSV utilisés sont situés dans le répertoire ./csvfiles, bien que les tests portent sur les 100 derniers résultats on peut augmenter ou diminuer cette valeur (variable nb_result du fichier principal).
Dans le cas d'une utilisation depuis un autre OS que Linux ou Mac si les liens vers le fichier app.properties sont perdus copier ce fichier depuis ./java/src/ à la racine du projet.
