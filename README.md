# Explication des Endpoints de l'API
Ce document fournit un aperçu de deux points de terminaison pour l'authentification et l'inscription des utilisateurs dans l'application.

## 1. Endpoint d'Inscription d'Utilisateur
```markdown

### Endpoint
- **Méthode :** POST
- **URL :** `http://localhost:8080/api/v1/auth/register`
- **Content-Type :** `application/json`

### Corps de la Requête
```json
{
  "email": "jouinikk1@gmail",
  "password": "pass",
  "role": "ADMINISTRATEUR",
  "name": "jouini"
}
```

### Description
Ce Endpoint est utilisé pour enregistrer un nouvel utilisateur dans le système. La requête nécessite un objet JSON contenant les champs suivants :
- `email` : L'adresse e-mail de l'utilisateur.
- `password` : Le mot de passe choisi par l'utilisateur.
- `role` : Le rôle de l'utilisateur (par exemple, ADMINISTRATEUR).
- `name` : Le nom de l'utilisateur.


## 2. Endpoint de Connexion
```markdown
### Endpoint
- **Méthode :** POST
- **URL :** `http://localhost:8080/api/v1/auth/login`
- **Content-Type :** `application/json`

### Corps de la Requête
```json
{
  "email": "jouinikk1@gmail",
  "password": "pass"
}
```

### Description
Ce Endpoint est utilisé pour l'authentification et la connexion de l'utilisateur. La requête nécessite un objet JSON contenant les champs suivants :
- `email` : L'adresse e-mail de l'utilisateur.
- `password` : Le mot de passe associé au compte de l'utilisateur.
