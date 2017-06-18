A faire prochainement :
- [ ] Faire un item Satellite et y ajouter des 'metadata' pour différents types de Satellite
(pour commencer en faire 3 : Low, Med, High)
- [ ] Un seul ordinateur de visée permet de tirer soit en cliquant sur le bloc, soit en spécifiant
des coordonnées. Upgrades à prévoir.
- [ ] Un satellite = un ID, le satellite est lancé avec une fusée, lors du lancement le joueur
recoit une plaque avec l'ID du satellite.
- [ ] Ajout de la centrale de controle satellite, multiblock, avec parabole et clignotement (si possible)
permet à partir de la plaque ID du satellite d'en prendre le contrôle et demander des missions de tir à
des coordonnées specs.
- [ ] On doit lier l'ordinateur de visée à la centrale pour pouvoir lier le satellite à l'ordinateur,
si la centrale n'a plus d'énergie ou si elle est détruite, l'ordinateur ne peux plus demander de frappe.
- [ ] Si l'ordinateur est équippé du module 'HyperSpace Communicator', il est indépendant de la centrale.
Cependant la frappe est imprécise à +- 10 blocs, de plus il y a 40% de chance que la demande échoue mais
consomme quand même de la batterie à l'ordinateur.
- [ ] Après chaque tir, selon le type de satellite, il y a un temps de rechargement/refroidissement entre
un autre tir.
- [ ] Update secret : Remplacer les coordonées X Y Z par les 3 parties de l'ID d'un satellite, ce dernier
est détruit si la centrale ordonne le tir et s'il n'est pas pourvu de bouclier.
- [ ] Ajout générateur de bouclier, un générateur = un satellite.
- [ ] Ajout d'un bouton d'autodestruction sur la centrale
- [ ] Tous satellites n'ayant pas reçu de connection au bout de 7j minecraftiens sera automatiquement détruis
- [ ] Ajout d'une structure auto-générer de satellite crashé d'un ancien temps, loot de pièce et d'upgrade
pour satellites dedans, rarité 1 pour un carré de 5 chucks
- [ ] Ajout d'un constructeur de satellite avec une table de craft bien spécifique pour customisé son satellite



Liste d'idées :
---------------
- [ ] Mettre à jour le gestion de la détection du tir: part du bloc le plus
haut en coordonnée Y, descends un par un et le premier bloc touché serra
celui qui subira l'explosion.
- [ ] Implémanter 3 tailles différentes de laser, 1 pour le Low, 2 pour le Med et 3 pour le High
- [ ] Rendre plus difficile d'accès le Med et le High
- [ ] Craft de plusieurs satellites plus complexe, mais à différentes durée de vie.
- [ ] Ajout du bloc brouilleur, impossible de demander un tir ou de tirer dans
ça zone d'action
- [ ] Ajout du bloc relais, permet de communiquer avec un satellite en orbite
(remplace l'overrider actuel), permet de précisé les coordonées de tir manuel,
la désactivation du satellite (pouvant être hacké, offrant alors la possibilité de changer
les sets de réglages et déraglant ainsi la visée, rendant le satellite hors d'état,
il peut être reconfiguré soit en résolvant un casse-tête soit en purgeant l'énergie
du satellite se qui prends 1min par 1000PU)
- [ ] Ajout du bloc surchargeur(en combinaison de relais) + identifiant propre à chaque satellite en orbite
Si un joueur souhaite détruire son satellite, il précise l'identifiant et 
contre une grande quantité d'énergie, surcharge le satellite et le détruit
- [ ] Ajout de la fusée, remplace l'ancier moyen de deploiement des satellites
- [ ] Ajout du scanner, scan dans son rayon d'action toute créatures hostiles dans
un certain rayon et les désintègres s'il est activé, attention les mobs ne looterons
rien car désintégrés, la vitesse de tir et cependant limité selon les modèles de sat
Quasi instantanée pour la version créa
- [ ] Modification du système de prodution de photon:
Il n'y aura plus de générateur de photon, ni d'emetteur, ni de recepteur
Plusieurs classes de satellite chaque avec des capacités de plus en plus
puissantes selon la classe: Niveau 1 - Defense seulement + lightning
			    Niveau 2 - Defense et attaque de petit niveau (creeper boom)
			    Niveau 3 - 2 + Penetring strike + plus de puissance
			    Niveau X(Crea seulement) - Meteor spawner, et super laser !
- [ ] Ajout de dysfonctionnement du satellite temporaire (réglable) désactivant une certain fonction
cela peut touché: la chambre de tir, les panneaux solaires, les batteries ou le système de visée
- [ ] Un satellite ne peut couvrir qu'une zone de 1000 blocs (sauf créa)
- [ ] Ajout d'une bombe à régéneration, elle permet de regenerer un chunk entier contre 16 enderpearl
et d'une énorme quantité d'énergie !
- [ ] Ajout du satellite de soin, crafté à partir d'une étoile du nether tir 
un rayon de soin instantané pour le propriétaire/cible contre de lénergie dans 
celui-ci, limité cependant à 100 coeurs, peut être recharger à l'aide 
d'une étoile du nether
- [ ] EasterEgg: un tir d'un satellite de soin avec 100 coeurs en réserve sur une
bombe à régénération à pleine puissance, créer une balise
régénératrice de santé illimité mais elle détruit cependant le satellite 
