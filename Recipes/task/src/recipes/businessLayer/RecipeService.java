package recipes.businessLayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import recipes.Entity.Recipe;
import recipes.persistence.RecipeRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Recipe findRecipeById(Integer id) {
        if (recipeRepository.findById(id).isPresent()) {
            return recipeRepository.findById(id).get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Not found recipe " + id);
        }
    }

    public Recipe save(Recipe recipe,
                       String userName) {
        recipe.setUserName(userName);
        recipe.setDate(LocalDateTime.now());
        return recipeRepository.save(recipe);
    }

    public HttpStatus deleteById(Integer id,
                                 String userName) {
        recipeRepository.findById(id).ifPresentOrElse(recipe -> {

            if (recipe.getUserName().equals(userName)) {
                recipeRepository.deleteById(id);
            } else {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN);
            }
        }, () -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Not found recipe " + id);
        });
        return HttpStatus.NO_CONTENT;
    }

    public HttpStatus updateById(Integer id, Recipe newRecipe, String userName) {
            recipeRepository.findById(id).ifPresentOrElse(recipe -> {
                if (recipe.getUserName().equals(userName)) {
                    recipe = newRecipe;
                    recipe.setId(id);
                    recipe.setUserName(userName);
                    recipe.setDate(LocalDateTime.now());
                    recipeRepository.save(recipe);
                } else {
                    throw new ResponseStatusException(HttpStatus.FORBIDDEN);
                }
            }, () -> {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Not found recipe " + id);
            });
            return HttpStatus.NO_CONTENT;
    }

    public Iterable<Recipe> findAll() {
        return recipeRepository.findAll();
    }

    public List<Recipe> filterByCategory(String category) {
        List<Recipe> recipes = new ArrayList<>();
        for (Recipe recipe:
                findAll()) {
            if (recipe.getCategory().equalsIgnoreCase(category)) {
                recipes.add(recipe);
            }
        }
        recipes.sort(Comparator.comparing(Recipe::getDate).reversed());
        return recipes;
    }

    public List<Recipe> filterByName(String name) {
        List<Recipe> recipes = new ArrayList<>();
        for (Recipe recipe:
                findAll()) {
            if (recipe.getName().toLowerCase().contains(name.toLowerCase())) {
                recipes.add(recipe);
            }
        }
        recipes.sort(Comparator.comparing(Recipe::getDate).reversed());
        return recipes;
    }
}
