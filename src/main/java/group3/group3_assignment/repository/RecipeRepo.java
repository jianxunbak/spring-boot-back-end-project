package group3.group3_assignment.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import group3.group3_assignment.entity.Recipe;

public interface RecipeRepo extends JpaRepository<Recipe, Integer> {
    Optional<Recipe> findByUser_IdAndId(Long userId, Integer recipeId);

    Optional<List<Recipe>> findByUser_Id(Long userId);

}
