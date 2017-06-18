A faire prochainement :
- [ ] Faire un item Satellite et y ajouter des 'metadata' pour diff�rents types de Satellite
(pour commencer en faire 3 : Low, Med, High)
- [ ] Un seul ordinateur de vis�e permet de tirer soit en cliquant sur le bloc, soit en sp�cifiant
des coordonn�es. Upgrades � pr�voir.
- [ ] Un satellite = un ID, le satellite est lanc� avec une fus�e, lors du lancement le joueur
recoit une plaque avec l'ID du satellite.
- [ ] Ajout de la centrale de controle satellite, multiblock, avec parabole et clignotement (si possible)
permet � partir de la plaque ID du satellite d'en prendre le contr�le et demander des missions de tir �
des coordonn�es specs.
- [ ] On doit lier l'ordinateur de vis�e � la centrale pour pouvoir lier le satellite � l'ordinateur,
si la centrale n'a plus d'�nergie ou si elle est d�truite, l'ordinateur ne peux plus demander de frappe.
- [ ] Si l'ordinateur est �quipp� du module 'HyperSpace Communicator', il est ind�pendant de la centrale.
Cependant la frappe est impr�cise � +- 10 blocs, de plus il y a 40% de chance que la demande �choue mais
consomme quand m�me de la batterie � l'ordinateur.
- [ ] Apr�s chaque tir, selon le type de satellite, il y a un temps de rechargement/refroidissement entre
un autre tir.
- [ ] Update secret : Remplacer les coordon�es X Y Z par les 3 parties de l'ID d'un satellite, ce dernier
est d�truit si la centrale ordonne le tir et s'il n'est pas pourvu de bouclier.
- [ ] Ajout g�n�rateur de bouclier, un g�n�rateur = un satellite.
- [ ] Ajout d'un bouton d'autodestruction sur la centrale
- [ ] Tous satellites n'ayant pas re�u de connection au bout de 7j minecraftiens sera automatiquement d�truis
- [ ] Ajout d'une structure auto-g�n�rer de satellite crash� d'un ancien temps, loot de pi�ce et d'upgrade
pour satellites dedans, rarit� 1 pour un carr� de 5 chucks
- [ ] Ajout d'un constructeur de satellite avec une table de craft bien sp�cifique pour customis� son satellite



Liste d'id�es :
---------------
- [ ] Mettre � jour le gestion de la d�tection du tir: part du bloc le plus
haut en coordonn�e Y, descends un par un et le premier bloc touch� serra
celui qui subira l'explosion.
- [ ] Impl�manter 3 tailles diff�rentes de laser, 1 pour le Low, 2 pour le Med et 3 pour le High
- [ ] Rendre plus difficile d'acc�s le Med et le High
- [ ] Craft de plusieurs satellites plus complexe, mais � diff�rentes dur�e de vie.
- [ ] Ajout du bloc brouilleur, impossible de demander un tir ou de tirer dans
�a zone d'action
- [ ] Ajout du bloc relais, permet de communiquer avec un satellite en orbite
(remplace l'overrider actuel), permet de pr�cis� les coordon�es de tir manuel,
la d�sactivation du satellite (pouvant �tre hack�, offrant alors la possibilit� de changer
les sets de r�glages et d�raglant ainsi la vis�e, rendant le satellite hors d'�tat,
il peut �tre reconfigur� soit en r�solvant un casse-t�te soit en purgeant l'�nergie
du satellite se qui prends 1min par 1000PU)
- [ ] Ajout du bloc surchargeur(en combinaison de relais) + identifiant propre � chaque satellite en orbite
Si un joueur souhaite d�truire son satellite, il pr�cise l'identifiant et 
contre une grande quantit� d'�nergie, surcharge le satellite et le d�truit
- [ ] Ajout de la fus�e, remplace l'ancier moyen de deploiement des satellites
- [ ] Ajout du scanner, scan dans son rayon d'action toute cr�atures hostiles dans
un certain rayon et les d�sint�gres s'il est activ�, attention les mobs ne looterons
rien car d�sint�gr�s, la vitesse de tir et cependant limit� selon les mod�les de sat
Quasi instantan�e pour la version cr�a
- [ ] Modification du syst�me de prodution de photon:
Il n'y aura plus de g�n�rateur de photon, ni d'emetteur, ni de recepteur
Plusieurs classes de satellite chaque avec des capacit�s de plus en plus
puissantes selon la classe: Niveau 1 - Defense seulement + lightning
			    Niveau 2 - Defense et attaque de petit niveau (creeper boom)
			    Niveau 3 - 2 + Penetring strike + plus de puissance
			    Niveau X(Crea seulement) - Meteor spawner, et super laser !
- [ ] Ajout de dysfonctionnement du satellite temporaire (r�glable) d�sactivant une certain fonction
cela peut touch�: la chambre de tir, les panneaux solaires, les batteries ou le syst�me de vis�e
- [ ] Un satellite ne peut couvrir qu'une zone de 1000 blocs (sauf cr�a)
- [ ] Ajout d'une bombe � r�g�neration, elle permet de regenerer un chunk entier contre 16 enderpearl
et d'une �norme quantit� d'�nergie !
- [ ] Ajout du satellite de soin, craft� � partir d'une �toile du nether tir 
un rayon de soin instantan� pour le propri�taire/cible contre de l�nergie dans 
celui-ci, limit� cependant � 100 coeurs, peut �tre recharger � l'aide 
d'une �toile du nether
- [ ] EasterEgg: un tir d'un satellite de soin avec 100 coeurs en r�serve sur une
bombe � r�g�n�ration � pleine puissance, cr�er une balise
r�g�n�ratrice de sant� illimit� mais elle d�truit cependant le satellite 
