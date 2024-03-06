$(document).ready(function () {
  let carsElement = $("#car-list-container").html();

  let pickUpLocation = sessionStorage.getItem("pick_up_location");
  let startDateTime = sessionStorage.getItem("startDateTime");
  let endDateTime = sessionStorage.getItem("endDateTime");

  let url =
    "http://localhost:8080/booking/available-cars?pickUpLocation=" +
    pickUpLocation +
    "&startDateTime=" +
    startDateTime +
    "&endDateTime=" +
    endDateTime;
  $.ajax({
    type: "GET",
    dataType: "json",
    url: url,
    headers: {
      Authorization: "Bearer " + document.cookie,
      Accept: "application/json",
    },
    success: function (response) {
      if (response.status_code == 200) {
        let carsHtml = "";
        response.details.forEach(function (car, index) {
          carsHtml += `<div class="col-md-4">
    				<div class="car-wrap rounded">
    					<div class="img rounded d-flex align-items-end" style="background-image: url(images/${
                car.make + "-" + car.model
              }.jpg);">
    					</div>
    					<div class="text">
    						<h2 class="mb-0"><a href="car-single.html">${
                  car.make + " " + car.model
                }</a></h2>
    						<div class="d-flex mb-3">
	    						<span class="cat">${car.make}</span>
	    						<p class="price ml-auto">EGP${car.pricePerDay}<span>/day</span></p>
    						</div>
    						<p class="d-flex mb-0 d-block"><a href="#" class="btn btn-primary py-2 mr-1 book">Book now</a> <a href="/car-single.html?carId=${
                  car.id
                }" class="btn btn-secondary py-2 ml-1">Details</a></p>
    					</div>
    				</div>
    			</div>`;
          // Update the car-list-container with the generated HTML
          $("#car-list-container").html(carsHtml);
        });
        let bookBtns1 = document.querySelectorAll(".book");
        bookBtns1.forEach((btn) => {
          btn.addEventListener("click", function (e) {
            e.target.classList.remove("btn-primary");
            e.target.textContent = "Reserved ✓";
            e.target.classList.add("btn-danger");
            e.target.classList.add("text-dark");
          });
        });
      } else if (response.status_code == 404) {
        $("#car-list-container").text(response.details[0]);
      }
    },
    error: function (error) {
      console.error("Error:", error);
      // Display a generic error message in case of AJAX failure
      $("#error-message").text("An error occurred during login.");
    },
  });
});
////////////////////////////////////////////////////////////////////////////
