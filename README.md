
# 遊戲場系統後端
		
```mermaid
classDiagram
    class Animal {
        +String name
        +int age
        +void eat()
        +void sleep()
    }
    class Dog {
        +String breed
        +void bark()
    }
    Animal <|-- Dog
