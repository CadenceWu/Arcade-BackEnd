
# 遊戲場系統後端
		
```mermaid
classDiagram
    class User {
        - Long id
        - String name
        - String email
        + getId() Long
        + setId(Long id)
        + getName() String
        + setName(String name)
        + getEmail() String
        + setEmail(String email)
    }

    class UserController {
        - UserService userService
        + getAllUsers(Model model) String
    }

    class UserService {
        - UserRepository userRepository
        + getAllUsers() List<User>
    }

    class UserRepository {
        + findAll() List<User>
    }

    UserController --> UserService : uses
    UserService --> UserRepository : uses
    User "1" --> "*" UserRepository : dependency


