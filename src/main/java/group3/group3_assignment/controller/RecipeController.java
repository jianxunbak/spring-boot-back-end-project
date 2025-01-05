package group3.group3_assignment.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import group3.group3_assignment.entity.Recipe;
import group3.group3_assignment.service.FavouritesService;
import group3.group3_assignment.service.RecipeService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService, FavouritesService favouritesService) {
        this.recipeService = recipeService;
    }

    @PostMapping("/{userId}")
    private ResponseEntity<Recipe> createRecipeToUser(@Valid @RequestBody Recipe recipe, @PathVariable Long userId) {
        Recipe newRecipe = recipeService.createRecipeToUser(userId, recipe);
        return new ResponseEntity<>(newRecipe, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        List<Recipe> allRecipes = recipeService.getAllRecipes();
        return new ResponseEntity<>(allRecipes, HttpStatus.OK);
    }

    @GetMapping("/{recipeId}")
    public ResponseEntity<Recipe> getOneRecipe(@PathVariable Integer recipeId) {
        Recipe oneRecipe = recipeService.getOneRecipe(recipeId);
        return new ResponseEntity<>(oneRecipe, HttpStatus.OK);
    }

    @PutMapping("/{recipeId}")
    public ResponseEntity<Recipe> updateOneRecipe(@PathVariable Integer recipeId, @Valid @RequestBody Recipe recipe) {
        Recipe updatedRecipe = recipeService.updateOneRecipe(recipeId, recipe);
        return new ResponseEntity<>(updatedRecipe, HttpStatus.OK);
    }

    @DeleteMapping("/{recipeId}")
    public ResponseEntity<HttpStatus> deleteOneRecipe(@PathVariable Integer recipeId) {
        recipeService.deleteRecipe(recipeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}