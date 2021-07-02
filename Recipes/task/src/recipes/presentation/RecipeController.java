package recipes.presentation;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import recipes.Entity.Recipe;
import recipes.Entity.User;
import recipes.businessLayer.RecipeService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class RecipeController {

    @Autowired
    RecipeService recipeService;

    @GetMapping("/api/recipe/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public Recipe getRecipe(@PathVariable Integer id) {
        return recipeService.findRecipeById(id);
    }

    @RequestMapping(value = "/api/recipe/search/",
                    params = "category",
                    method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('USER')")
    public List<Recipe> searchCategory(@RequestParam String category) {
        return recipeService.filterByCategory(category);
    }

    @RequestMapping(value = "/api/recipe/search/",
                    params = "name",
                    method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('USER')")
    public List<Recipe> searchName(@RequestParam String name) {
        return recipeService.filterByName(name);
    }

    @PostMapping("/api/recipe/new")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Object> postRecipe(@Valid @RequestBody Recipe recipe,
                                             @AuthenticationPrincipal UserDetails userDetails) {
        return new ResponseEntity<>(new JSONObject().put("id",
                recipeService.save(recipe, userDetails.getUsername()).getId()).toMap(),
                HttpStatus.OK);
    }

    @PutMapping("/api/recipe/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Object> putRecipe(@Valid @RequestBody Recipe recipe,
                                            @PathVariable Integer id,
                                            @AuthenticationPrincipal UserDetails userDetails) {

        return new ResponseEntity<>(recipeService.updateById(id,
                                                            recipe,
                                                            userDetails.getUsername()));
    }

    @DeleteMapping("/api/recipe/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Object> deleteRecipe(@PathVariable Integer id,
                                               @AuthenticationPrincipal UserDetails userDetails) {
        return new ResponseEntity<>(recipeService.deleteById(id, userDetails.getUsername()));
    }

}