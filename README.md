# Cookbook
The project's goal was to create Cookbook app in Android for personal usage. 

## Features
- User can filter recipes by categories (type of food, time required for cooking, favorites) and search for recipe by recipe name
- Recipes can be marked as favorite and after that user can find it in category Favorite
- Preview of recipe contains:
  - short description with recipe properties (required time, diffficulty, count of servings)
  - needed ingredients
  - cooking process

<div>
  <img src="https://user-images.githubusercontent.com/45421463/189637974-4b2a1087-4cdf-42d7-833a-1dbdd04e2bac.png" width="200" />
  <img src="https://user-images.githubusercontent.com/45421463/189637999-a2bacb61-776b-4b32-8e53-411f6957d59b.png" width="200" />
  <img src="https://user-images.githubusercontent.com/45421463/189635012-aa004b4b-34c8-442e-adb0-8f4ac4127d53.png" width="200" />
  <img src="https://user-images.githubusercontent.com/45421463/189635053-5a2e1f03-8f87-479e-a65d-0694850ad7fc.png" width="200" />
  <img src="https://user-images.githubusercontent.com/45421463/189635078-7a0cad2a-25b4-4ba2-bc8b-549f69213377.png" width="200" />
</div>

## Used technologies and frameworks
- **Jetpack compose** for User interface
- **Navigation Compose** for navigation
- **Room** for accessing recipes localy 
- **Flow**, **LiveData** and **coroutines** for asynchronous operations
- **Koin** for dependency injection
- **Coil** for loading images
