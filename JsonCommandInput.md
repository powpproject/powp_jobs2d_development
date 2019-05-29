# Przykładowy format JSON wymaganego w CommandManager

[
    {
      "commandName": "set",
      "x": "0",
      "y": "0"
    },
    {
      "commandName": "operate",
      "x": "10",
      "y": "10"
    }
]

Opcja "commandName" : "set" umożliwia wskazanie miejsce, w którym rozpocznie się rysowanie.

Opcja "commandName" : "operate" umożliwia podanie wspołrzędych, do których przemieści się wskaźnik, rysując linię od poprzednio podanych współrzędnych.



Jeśli w ciągu poleceń podamy operację set, to wskaźnik przeskoczy do miejsca podanego jako wspołrzędne bez rysowania linii.


Przykład:
[
    {
      "commandName": "set",
      "x": "0",
      "y": "0"
    },
    {
      "commandName": "operate",
      "x": "100",
      "y": "0"
    },    
    {
      "commandName": "operate",
      "x": "100",
      "y": "100"
    },
    {
      "commandName": "operate",
      "x": "0",
      "y": "100"
    },
    {
      "commandName": "operate",
      "x": "0",
      "y": "0"
    }
]

Powyższe polecenie narysuje nam kwadrat.
