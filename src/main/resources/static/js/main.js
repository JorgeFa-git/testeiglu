let ingredients = [];

$(() => {
  refreshCartData();
})

function resetIngredients() {
  ingredients = [];
  $("#selected-list").empty();
}

function addIngredient() {
  const ingredientSelected = $("#ingredient-select option:selected");
  ingredients.push(ingredientSelected.val())
  $("#selected-list").append("<div>" + ingredientSelected.text() + "</div>")
}

function addBurgerFromForm() {
  if (!ingredients.length) {
    return;
  }

  $.post({
    url: '/api/burger/personalized',
    dataType: 'json',
    contentType: 'application/json',
    data: JSON.stringify({ingredientIds: [...ingredients]}),
    success: () => {
      resetIngredients();
      refreshCartData();
    }
  })
}

function addBurgerFromOption(optionId) {
  $.post("/api/burger/from-menu-option/" + optionId, () => {
    refreshCartData();
  });
}

function refreshCartData() {
  $.get("/api/cart", data => {
    var currencyFormat = new Intl.NumberFormat('pt-BR', {
      style: 'currency',
      currency: 'BRL'
    });

    $("#cart-total").html(currencyFormat.format(data.total));
    $("#discount").html(currencyFormat.format(data.discount));
    $("#item-count").html(data.burgers.length);
  })
}

$("#options-tab").on("click", () => {
  $("#options-tab").addClass("active");
  $("#personalized-tab").removeClass("active");
  $("#display-options-tab").removeClass("hide");
  $("#display-personalized-tab").addClass("hide");
});

$("#personalized-tab").on("click", () => {
  $("#personalized-tab").addClass("active");
  $("#options-tab").removeClass("active");
  $("#display-options-tab").addClass("hide");
  $("#display-personalized-tab").removeClass("hide");
});