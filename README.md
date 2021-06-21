Ce projet exécute des tests Selenium sur des pages disponibles sur codenpen.io puis effectue des captures avant de 
fermer le navigateur.

Les captures d'écran des tests de la classe AnimatedMenuTest sont enregistrées dans target/subFolder.

Celles de la classe MutationObserverTest sont directement dans le target.

Ces captures ont un nom basé sur le format suivant : nom_de_la_methode.png

La classe AnimatedMenuTest utilise Firefox tandis que MutationObserverTest utilise Chrome.